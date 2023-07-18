package com.xingyuan.train.member.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Ticket {
    private Long id;

    private Long memberId;

    private Long passengerId;

    private String passengerName;

    private Date date;

    private String trainCode;

    private Integer carriageIndex;

    private String row;

    private String col;

    private String start;

    private Date startTime;

    private String end;

    private Date endTime;

    private String seatType;

    private Date createTime;

    private Date updateTime;
}