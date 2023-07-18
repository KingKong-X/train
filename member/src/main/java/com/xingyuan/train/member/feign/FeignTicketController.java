package com.xingyuan.train.member.feign;

import com.xingyuan.train.common.req.MemberTicketReq;
import com.xingyuan.train.common.resp.CommonResp;
import com.xingyuan.train.member.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xingyuan Huang
 * @since 2023/7/18 11:24
 */

@RestController
@RequestMapping("/feign/ticket")
public class FeignTicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody MemberTicketReq req) throws Exception{
        ticketService.save(req);
        return new CommonResp<>();
    }
}
