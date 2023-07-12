package com.xingyuan.train.business.req;

import com.xingyuan.train.common.req.PageReq;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class DailyTrainQueryReq extends PageReq {
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date date;

    private String code;
}
