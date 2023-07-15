package com.xingyuan.train.business.domain;

import lombok.Data;

import java.util.Date;

@Data
public class TrainSeat {
    private Long id;

    private String trainCode;

    private Integer carriageIndex;

    private String row;

    private String col;

    private String seatType;

    private Integer carriageSeatIndex;

    private Date createTime;

    private Date updateTime;

}