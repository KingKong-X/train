package com.xingyuan.train.member.controller;

import com.xingyuan.train.common.resp.CommonResp;
import com.xingyuan.train.member.req.MemberLoginReq;
import com.xingyuan.train.member.req.MemberRegisterReq;
import com.xingyuan.train.member.req.MemberSendCodeReq;
import com.xingyuan.train.member.resp.MemberLoginResp;
import com.xingyuan.train.member.service.MemberService;
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
@RequestMapping("/member")
public class MemberController {
    @Resource
    private MemberService memberService;

    @PostMapping("/register")
    public CommonResp<Long> register(@Valid MemberRegisterReq req){
        long register = memberService.register(req);
        return new CommonResp<>(register);
    }

    @PostMapping("/send-code")
    public CommonResp sendCode(@Valid @RequestBody MemberSendCodeReq req){
        memberService.sendCode(req);
        return new CommonResp<>();
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid @RequestBody MemberLoginReq req){
        return new CommonResp<>(memberService.login(req));
    }
}
