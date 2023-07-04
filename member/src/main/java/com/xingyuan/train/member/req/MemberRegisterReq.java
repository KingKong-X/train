package com.xingyuan.train.member.req;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * @author Xingyuan Huang
 * @since 2023/7/2 17:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRegisterReq {
    @NotBlank(message = "手机号不能为空")
    private String mobile;
}
