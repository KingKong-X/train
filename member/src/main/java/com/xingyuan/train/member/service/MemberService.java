package com.xingyuan.train.member.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.log.Log;
import com.xingyuan.train.common.exception.BusinessException;
import com.xingyuan.train.common.exception.BusinessExceptionEnum;
import com.xingyuan.train.common.util.SnowUtil;
import com.xingyuan.train.member.domain.Member;
import com.xingyuan.train.member.domain.MemberExample;
import com.xingyuan.train.member.mapper.MemberMapper;
import com.xingyuan.train.member.req.MemberRegisterReq;
import com.xingyuan.train.member.req.MemberSendCodeReq;
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

    public long register(MemberRegisterReq req){
        String mobile = req.getMobile();

        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);

        if(CollUtil.isNotEmpty(list)){
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insert(member);

        return member.getId();
    }

    public void sendCode(MemberSendCodeReq req){
        String mobile=req.getMobile();

        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);

        // 如果手机号不存在，则插入一条记录
        if(CollUtil.isEmpty(list)){
            LOG.info("手机号不存在，插入一条记录");
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(req.getMobile());
            memberMapper.insert(member);
        }
        else{
            LOG.info("手机号已存在，不插入记录");
        }

        String code = RandomUtil.randomString(4);
        LOG.info("生成短信验证码：{}", code);
    }
}
