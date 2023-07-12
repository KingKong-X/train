package com.xingyuan.train.business.req;

import com.xingyuan.train.common.req.PageReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TrainCarriageQueryReq extends PageReq {
    private String trainCode;
}
