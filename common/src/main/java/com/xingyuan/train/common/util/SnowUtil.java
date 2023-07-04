package com.xingyuan.train.common.util;

import cn.hutool.core.util.IdUtil;

/**
 * @author Xingyuan Huang
 * @since 2023/7/4 23:26
 */
public class SnowUtil {
    private static final long dataCenterId = 1;  //数据中心
    private static final long workerId = 1;     //机器标识

    public static long getSnowflakeNextId() {
        return IdUtil.getSnowflake(workerId, dataCenterId).nextId();
    }

    public static String getSnowflakeNextIdStr() {
        return IdUtil.getSnowflake(workerId, dataCenterId).nextIdStr();
    }
}
