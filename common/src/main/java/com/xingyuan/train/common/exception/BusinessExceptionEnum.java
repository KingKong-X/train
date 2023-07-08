package com.xingyuan.train.common.exception;

import lombok.*;

/**
 * @author Xingyuan Huang
 * @since 2023/7/3 22:56
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum BusinessExceptionEnum {
    MEMBER_MOBILE_EXIST("手机号已注册"),

    MEMBER_MOBILE_NOT_EXIST("请先获取短信验证码"),

    MEMBER_MOBILE_CODE_ERROR("短信验证码错误");

    private String desc;
}
