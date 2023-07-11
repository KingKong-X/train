package com.xingyuan.train.member.controller;

import com.xingyuan.train.common.context.LoginMemberContext;
import com.xingyuan.train.common.resp.CommonResp;
import com.xingyuan.train.common.resp.PageResp;
import com.xingyuan.train.member.req.MemberLoginReq;
import com.xingyuan.train.member.req.MemberSendCodeReq;
import com.xingyuan.train.member.req.PassengerQueryReq;
import com.xingyuan.train.member.req.PassengerSaveReq;
import com.xingyuan.train.member.resp.MemberLoginResp;
import com.xingyuan.train.member.resp.PassengerQueryResp;
import com.xingyuan.train.member.service.MemberService;
import com.xingyuan.train.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/query-list")
    public CommonResp<PageResp<PassengerQueryResp>> queryList(@Valid PassengerQueryReq req){
        req.setMemberId(LoginMemberContext.getId());
        PageResp<PassengerQueryResp> respList = passengerService.queryList(req);
        return new CommonResp<>(respList);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id){
        passengerService.delete(id);
        return new CommonResp<>();
    }

}
