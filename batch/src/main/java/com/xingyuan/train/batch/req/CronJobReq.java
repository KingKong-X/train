package com.xingyuan.train.batch.req;

import lombok.Data;

/**
 * @author Xingyuan Huang
 * @since 2023/7/13 10:40
 */
@Data
public class CronJobReq {
    private String group;

    private String name;

    private String description;

    private String cronExpression;
}
