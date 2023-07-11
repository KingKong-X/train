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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", namePinyin=").append(namePinyin);
        sb.append(", namePy=").append(namePy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}