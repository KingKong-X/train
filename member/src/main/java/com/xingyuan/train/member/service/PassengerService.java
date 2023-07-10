package com.xingyuan.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.xingyuan.train.common.util.SnowUtil;
import com.xingyuan.train.member.domain.Passenger;
import com.xingyuan.train.member.mapper.PassengerMapper;
import com.xingyuan.train.member.req.PassengerSaveReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author Xingyuan Huang
 * @since 2023/7/10 19:24
 */
@Service
public class PassengerService {
    @Resource
    PassengerMapper passengerMapper;

    public void save(PassengerSaveReq req){
        DateTime now = DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }
}
