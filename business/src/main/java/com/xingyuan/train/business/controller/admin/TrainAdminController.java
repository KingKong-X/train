package com.xingyuan.train.business.controller.admin;

import com.xingyuan.train.business.req.TrainQueryReq;
import com.xingyuan.train.business.req.TrainSaveReq;
import com.xingyuan.train.business.resp.TrainQueryResp;
import com.xingyuan.train.business.service.TrainSeatService;
import com.xingyuan.train.business.service.TrainService;
import com.xingyuan.train.common.resp.CommonResp;
import com.xingyuan.train.common.resp.PageResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Xingyuan Huang
 * @since 2023/7/11 21:17
 */
@RestController
@RequestMapping("/admin/train")
public class TrainAdminController {
    @Resource
    private TrainService trainService;

    @Resource
    private TrainSeatService trainSeatService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody TrainSaveReq req) {
        trainService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<TrainQueryResp>> queryList(@Valid TrainQueryReq req) {
        PageResp<TrainQueryResp> list = trainService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        trainService.delete(id);
        return new CommonResp<>();
    }

    @GetMapping("/query-all")
    public CommonResp<List<TrainQueryResp>> queryList(){
        List<TrainQueryResp> list = trainService.queryAll();
        return new CommonResp<>(list);
    }

    @GetMapping("/gen-seat/{trainCode}")
    public CommonResp<Object> genSeat(@PathVariable String trainCode){
        trainSeatService.genTrainSeat(trainCode);
        return new CommonResp<>();
    }
}
