package com.xingyuan.train.business.feign;

import com.xingyuan.train.common.req.MemberTicketReq;
import com.xingyuan.train.common.resp.CommonResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Xingyuan Huang
 * @since 2023/7/18 11:34
 */
@FeignClient(name = "member", url = "http://localhost:8001")
public interface MemberFeign {
    @GetMapping("/member/feign/ticket/save")
    CommonResp<Object> save(@RequestBody MemberTicketReq req);
}
