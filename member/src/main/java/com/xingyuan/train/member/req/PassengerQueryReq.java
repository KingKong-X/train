package com.xingyuan.train.member.req;

import com.xingyuan.train.common.req.PageReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Xingyuan Huang
 * @since 2023/7/11 9:49
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class PassengerQueryReq extends PageReq {
    private Long memberId;
}
