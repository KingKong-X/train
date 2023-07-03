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
    MEMBER_MOBILE_EXIST("手机号已注册");

    private String desc;
}
