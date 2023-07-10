package com.xingyuan.train.member.resp;

import lombok.Data;

/**
 * @author Xingyuan Huang
 * @since 2023/7/7 22:35
 */
@Data
public class MemberLoginResp {
    private Long id;

    private String mobile;

    private String token;
}
