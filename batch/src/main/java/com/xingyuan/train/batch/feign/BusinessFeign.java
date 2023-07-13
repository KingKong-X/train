package com.xingyuan.train.batch.feign;

import com.xingyuan.train.common.resp.CommonResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

/**
 * @author Xingyuan Huang
 * @since 2023/7/13 9:46
 */
@FeignClient(name = "business", url = "http://localhost:8002/business")
public interface BusinessFeign {

    @GetMapping("/admin/daily-train/gen-daily/{date}")
    CommonResp<Object> genDaily(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date);
}
