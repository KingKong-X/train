package com.xingyuan.train.common.context;

import com.xingyuan.train.common.resp.MemberLoginResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xingyuan Huang
 * @since 2023/7/10 20:32
 */
public class LoginMemberContext {
    private static final Logger LOG = LoggerFactory.getLogger(LoginMemberContext.class);

    private static ThreadLocal<MemberLoginResp> member = new ThreadLocal<>();

    public static MemberLoginResp getMember(){
        return member.get();
    }

    public static void setMember(MemberLoginResp resp){
        LoginMemberContext.member.set(resp);
    }

    public static Long getId() {
        try {
            return member.get().getId();
        } catch (Exception e) {
            LOG.error("获取登录会员信息异常", e);
            throw e;
        }
    }

}
