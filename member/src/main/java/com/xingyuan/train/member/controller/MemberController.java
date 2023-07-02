package com.xingyuan.train.member.controller;

import com.xingyuan.train.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
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
    public long register(String mobile){
        return memberService.register(mobile);
    }
}