package com.xingyuan.train.member.controller;

import com.xingyuan.train.common.resp.CommonResp;
import com.xingyuan.train.member.req.MemberLoginReq;
import com.xingyuan.train.member.req.MemberSendCodeReq;
import com.xingyuan.train.member.req.PassengerSaveReq;
import com.xingyuan.train.member.resp.MemberLoginResp;
import com.xingyuan.train.member.service.MemberService;
import com.xingyuan.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xingyuan Huang
 * @since 2023/7/2 15:47
 */
@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Resource
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody PassengerSaveReq req){
        passengerService.save(req);
        return new CommonResp<>();
    }

}
