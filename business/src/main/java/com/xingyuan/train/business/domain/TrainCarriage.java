package com.xingyuan.train.business.domain;

import lombok.Data;

import java.util.Date;

@Data
public class TrainCarriage {
    private Long id;

    private String trainCode;

    private Integer index;

    private String seatType;

    private Integer seatCount;

    private Integer rowCount;

    private Integer colCount;

    private Date createTime;

    private Date updateTime;
}