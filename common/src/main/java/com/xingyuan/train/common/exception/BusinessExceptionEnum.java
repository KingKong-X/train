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

    MEMBER_MOBILE_CODE_ERROR("短信验证码错误"),

    BUSINESS_STATION_NAME_UNIQUE_ERROR("车站已存在"),

    BUSINESS_TRAIN_CODE_UNIQUE_ERROR("车次编号已存在"),

    BUSINESS_TRAIN_STATION_INDEX_UNIQUE_ERROR("同车次站序已存在"),

    BUSINESS_TRAIN_STATION_NAME_UNIQUE_ERROR("同车次站名已存在"),

    BUSINESS_TRAIN_CARRIAGE_INDEX_UNIQUE_ERROR("同车次厢号已存在");

    private String desc;
}
