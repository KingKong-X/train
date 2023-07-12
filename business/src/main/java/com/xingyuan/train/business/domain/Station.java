package com.xingyuan.train.business.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Station {
    private Long id;

    private String name;

    private String namePinyin;

    private String namePy;

    private Date createTime;

    private Date updateTime;
}