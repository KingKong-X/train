package com.xingyuan.train.member.domain;

public class Member {
    private Long id;

    private String mobile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}