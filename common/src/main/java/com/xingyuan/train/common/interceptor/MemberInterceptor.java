package com.xingyuan.train.common.interceptor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.xingyuan.train.common.context.LoginMemberContext;
import com.xingyuan.train.common.resp.MemberLoginResp;
import com.xingyuan.train.common.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author Xingyuan Huang
 * @since 2023/7/10 20:39
 */
@Component
public class MemberInterceptor implements HandlerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(MemberInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(StrUtil.isNotBlank(token)){
            LOG.info("获取会员登录token：{}", token);
            JSONObject object = JwtUtil.getJsonObject(token);
            LOG.info("当前登录会员：{}", object);
            MemberLoginResp member = BeanUtil.copyProperties(object, MemberLoginResp.class);
            LoginMemberContext.setMember(member);
        }
        return true;
    }
}
