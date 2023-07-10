package com.xingyuan.train.member.domain;

import lombok.Data;

@Data
public class Member {
    private Long id;

    private String mobile;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}