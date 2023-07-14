package com.xingyuan.train.business.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DailyTrainTicket {
    private Long id;

    private Date date;

    private String trainCode;

    private String start;

    private String startPinyin;

    private Date startTime;

    private Integer startIndex;

    private String end;

    private String endPinyin;

    private Date endTime;

    private Integer endIndex;

    private Integer ydz;

    private BigDecimal ydzPrice;

    private Integer edz;

    private BigDecimal edzPrice;

    private Integer rw;

    private BigDecimal rwPrice;

    private Integer yw;

    private BigDecimal ywPrice;

    private Date createTime;

    private Date updateTime;

}