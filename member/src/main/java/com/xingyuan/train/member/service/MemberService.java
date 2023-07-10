package com.xingyuan.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.xingyuan.train.common.exception.BusinessException;
import com.xingyuan.train.common.exception.BusinessExceptionEnum;
import com.xingyuan.train.common.util.SnowUtil;
import com.xingyuan.train.member.domain.Member;
import com.xingyuan.train.member.domain.MemberExample;
import com.xingyuan.train.member.mapper.MemberMapper;
import com.xingyuan.train.member.req.MemberLoginReq;
import com.xingyuan.train.member.req.MemberSendCodeReq;
import com.xingyuan.train.member.resp.MemberLoginResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xingyuan Huang
 * @since 2023/7/2 15:45
 */
@Service
public class MemberService {
    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);
    @Resource
    private MemberMapper memberMapper;

    public void sendCode(MemberSendCodeReq req){
        String mobile=req.getMobile();

        Member memberDB = selectByMobile(mobile);

        // 如果手机号不存在，则插入一条记录
        if(ObjectUtil.isNull(memberDB)){
            LOG.info("手机号不存在，插入一条记录");
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(req.getMobile());
            memberMapper.insert(member);
        }
        else{
            LOG.info("手机号已存在，不插入记录");
        }

//        String code = RandomUtil.randomString(4);
        String code = "8888";
        LOG.info("生成短信验证码：{}", code);
    }

    public MemberLoginResp login(MemberLoginReq req){
        String mobile = req.getMobile();
        String code = req.getCode();

        Member memberDB = selectByMobile(mobile);

        if(ObjectUtil.isNull(memberDB)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }

        // 校验短信验证码
        if(!"8888".equals(code)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }

        return BeanUtil.copyProperties(memberDB, MemberLoginResp.class);

    }

    private Member selectByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if(CollUtil.isEmpty(list)){
            return null;
        }
        else{
            return list.get(0);
        }
    }
}
