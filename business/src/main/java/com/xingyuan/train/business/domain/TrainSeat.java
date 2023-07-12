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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }
}