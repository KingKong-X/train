package com.xingyuan.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.xingyuan.train.common.exception.BusinessException;
import com.xingyuan.train.common.exception.BusinessExceptionEnum;
import com.xingyuan.train.member.domain.Member;
import com.xingyuan.train.member.domain.MemberExample;
import com.xingyuan.train.member.mapper.MemberMapper;
import com.xingyuan.train.member.req.MemberRegisterReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xingyuan Huang
 * @since 2023/7/2 15:45
 */
@Service
public class MemberService {
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
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);
        memberMapper.insert(member);

        return member.getId();
    }
}
