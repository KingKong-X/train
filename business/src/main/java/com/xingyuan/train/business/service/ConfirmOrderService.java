package com.xingyuan.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xingyuan.train.business.domain.*;
import com.xingyuan.train.business.enums.ConfirmOrderStatusEnum;
import com.xingyuan.train.business.enums.SeatColEnum;
import com.xingyuan.train.business.enums.SeatTypeEnum;
import com.xingyuan.train.business.mapper.ConfirmOrderMapper;
import com.xingyuan.train.business.req.ConfirmOrderQueryReq;
import com.xingyuan.train.business.req.ConfirmOrderDoReq;
import com.xingyuan.train.business.req.ConfirmOrderTicketReq;
import com.xingyuan.train.business.resp.ConfirmOrderQueryResp;
import com.xingyuan.train.common.context.LoginMemberContext;
import com.xingyuan.train.common.exception.BusinessException;
import com.xingyuan.train.common.exception.BusinessExceptionEnum;
import com.xingyuan.train.common.resp.PageResp;
import com.xingyuan.train.common.util.SnowUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ConfirmOrderService {

    private static final Logger LOG = LoggerFactory.getLogger(ConfirmOrderService.class);

    @Resource
    private ConfirmOrderMapper confirmOrderMapper;

    @Resource
    private DailyTrainTicketService dailyTrainTicketService;

    @Resource
    private DailyTrainCarriageService dailyTrainCarriageService;

    @Resource
    private DailyTrainSeatService dailyTrainSeatService;

    public void save(ConfirmOrderDoReq req) {
        DateTime now = DateTime.now();
        ConfirmOrder confirmOrder = BeanUtil.copyProperties(req, ConfirmOrder.class);
        if (ObjectUtil.isNull(confirmOrder.getId())) {
            confirmOrder.setId(SnowUtil.getSnowflakeNextId());
            confirmOrder.setCreateTime(now);
            confirmOrder.setUpdateTime(now);
            confirmOrderMapper.insert(confirmOrder);
        } else {
            confirmOrder.setUpdateTime(now);
            confirmOrderMapper.updateByPrimaryKey(confirmOrder);
        }
    }

    public PageResp<ConfirmOrderQueryResp> queryList(ConfirmOrderQueryReq req) {
        ConfirmOrderExample confirmOrderExample = new ConfirmOrderExample();
        confirmOrderExample.setOrderByClause("id desc");
        ConfirmOrderExample.Criteria criteria = confirmOrderExample.createCriteria();

        LOG.info("查询页码：{}", req.getPage());
        LOG.info("每页条数：{}", req.getSize());
        PageHelper.startPage(req.getPage(), req.getSize());
        List<ConfirmOrder> confirmOrderList = confirmOrderMapper.selectByExample(confirmOrderExample);

        PageInfo<ConfirmOrder> pageInfo = new PageInfo<>(confirmOrderList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        List<ConfirmOrderQueryResp> list = BeanUtil.copyToList(confirmOrderList, ConfirmOrderQueryResp.class);

        PageResp<ConfirmOrderQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void delete(Long id) {
        confirmOrderMapper.deleteByPrimaryKey(id);
    }

    public void doConfirm(ConfirmOrderDoReq req) {
        // 省略业务数据校验，如：车次是否存在，余票是否存在，车次是否在有效期内，tickets条数>0，同乘客同车次是否已买过
        // 保存确认订单表，状态初始
        DateTime now = DateTime.now();
        ConfirmOrder confirmOrder = new ConfirmOrder();
        confirmOrder.setId(SnowUtil.getSnowflakeNextId());
        confirmOrder.setCreateTime(now);
        confirmOrder.setUpdateTime(now);
        confirmOrder.setMemberId(LoginMemberContext.getId());
        confirmOrder.setDate(req.getDate());
        confirmOrder.setStart(req.getStart());
        confirmOrder.setEnd(req.getEnd());
        confirmOrder.setDailyTrainTicketId(req.getDailyTrainTicketId());
        confirmOrder.setStatus(ConfirmOrderStatusEnum.INIT.getCode());
        confirmOrder.setTickets(JSON.toJSONString(req.getTickets()));
        confirmOrder.setTrainCode(req.getTrainCode());
        confirmOrderMapper.insert(confirmOrder);

        // 查出余票记录，需要得到真实的库存
        DailyTrainTicket dailyTrainTicket = dailyTrainTicketService.selectByUnique(req.getDate(), req.getTrainCode(), req.getStart(), req.getEnd());
        LOG.info("查出余票记录：{}", dailyTrainTicket);

        // 扣减余票数量，并判断余票是否足够
        reduceTickets(req, dailyTrainTicket);

        // 计算相对第一个座位的偏移值
        // 比如选择的是C1，D2，则偏移值是：[0,5]
        // 比如选择的是A1，B1，C1，则偏移值是：[0,1,2]
        ConfirmOrderTicketReq ticketReq = req.getTickets().get(0);
        if (StrUtil.isNotBlank(ticketReq.getSeat())) {
            LOG.info("本次购票有选座");
            // 查出本次选座的座位类型都有哪些列，用于计算所选座位与第一个座位的偏离值
            List<SeatColEnum> colEnumList = SeatColEnum.getColsByType(ticketReq.getSeatTypeCode());
            LOG.info("本次选座的座位类型包含的列：{}", colEnumList);

            // 组成和前端两排选座一样的列表，用于作参照的座位列表，例：referSeatList = {A1,C1,D1,F1,A2,C2,D2,F2}
            List<String> referSeatList = new ArrayList<>();
            for (int i = 1; i <= 2; i++) {
                for (SeatColEnum seatColEnum : colEnumList) {
                    referSeatList.add(seatColEnum.getCode() + i);
                }
            }
            LOG.info("用于参照的两排座位：{}", referSeatList);

            // 绝对偏移值，即：在参照列表中的位置
            List<Integer> absoluteOffsetList = new ArrayList<>();
            for (ConfirmOrderTicketReq confirmOrderTicketReq : req.getTickets()) {
                int index = referSeatList.indexOf(confirmOrderTicketReq.getSeat());
                absoluteOffsetList.add(index);
            }
            LOG.info("计算得到所有座位的绝对偏移值：{}", absoluteOffsetList);

            // 相对偏移值
            List<Integer> offsetList = new ArrayList<>();
            for (Integer index : absoluteOffsetList) {
                offsetList.add(index - absoluteOffsetList.get(0));
            }
            LOG.info("计算得到所有座位的相对第一个座位的偏移值：{}", offsetList);

            getSeat(req.getDate(), req.getTrainCode(), ticketReq.getSeatTypeCode(),
                    ticketReq.getSeat().split("")[0], offsetList);
        } else {
            LOG.info("本次购票没有选座");
            for(ConfirmOrderTicketReq confirmOrderTicketReq : req.getTickets()){
                getSeat(req.getDate(), req.getTrainCode(), confirmOrderTicketReq.getSeatTypeCode(), null, null);
            }
        }


        // 选座

        // 一个车箱一个车箱的获取座位数据

        // 挑选符合条件的座位，如果这个车箱不满足，则进入下个车箱（多个选座应该在同一个车厢）

        // 选中座位后事务处理：

        // 座位表修改售卖情况sell；
        // 余票详情表修改余票；
        // 为会员增加购票记录
        // 更新确认订单为成功
    }

    private void getSeat(Date date, String trainCode, String seatType, String column, List<Integer> offsetList) {
        List<DailyTrainCarriage> carriages = dailyTrainCarriageService.selectBySeatType(date, trainCode, seatType);
        LOG.info("共查出{}个符合条件的车厢", carriages.size());

        // 一个车厢一个车厢的获取座位数据
        for (DailyTrainCarriage dailyTrainCarriage : carriages) {
            LOG.info("开始从车厢{}选座", dailyTrainCarriage.getIndex());
            // 寻找这个车厢的所有座位
            List<DailyTrainSeat> dailyTrainSeats = dailyTrainSeatService.selectByCarriage(date, trainCode, dailyTrainCarriage.getIndex());
            LOG.info("车厢{}的座位数{}", carriages.size(), dailyTrainSeats.size());

        }

    }

    private static void reduceTickets(ConfirmOrderDoReq req, DailyTrainTicket dailyTrainTicket) {
        for (ConfirmOrderTicketReq ticketReq : req.getTickets()) {
            String seatTypeCode = ticketReq.getSeatTypeCode();
            SeatTypeEnum seatTypeEnum = EnumUtil.getBy(SeatTypeEnum::getCode, seatTypeCode);
            switch (seatTypeEnum) {
                case YDZ -> {
                    int countLeft = dailyTrainTicket.getYdz() - 1;
                    if (countLeft < 0) {
                        throw new BusinessException(BusinessExceptionEnum.CONFIRM_ORDER_TICKET_COUNT_ERROR);
                    }
                    dailyTrainTicket.setYdz(countLeft);
                }
                case EDZ -> {
                    int countLeft = dailyTrainTicket.getEdz() - 1;
                    if (countLeft < 0) {
                        throw new BusinessException(BusinessExceptionEnum.CONFIRM_ORDER_TICKET_COUNT_ERROR);
                    }
                    dailyTrainTicket.setEdz(countLeft);
                }
                case RW -> {
                    int countLeft = dailyTrainTicket.getRw() - 1;
                    if (countLeft < 0) {
                        throw new BusinessException(BusinessExceptionEnum.CONFIRM_ORDER_TICKET_COUNT_ERROR);
                    }
                    dailyTrainTicket.setRw(countLeft);
                }
                case YW -> {
                    int countLeft = dailyTrainTicket.getYw() - 1;
                    if (countLeft < 0) {
                        throw new BusinessException(BusinessExceptionEnum.CONFIRM_ORDER_TICKET_COUNT_ERROR);
                    }
                    dailyTrainTicket.setYw(countLeft);
                }
            }
        }
    }

}
