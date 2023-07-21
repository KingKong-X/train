package com.xingyuan.train.common.controller;

import cn.hutool.core.util.StrUtil;
import com.xingyuan.train.common.exception.BusinessException;
import com.xingyuan.train.common.resp.CommonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xingyuan Huang
 * @since 2023/7/3 22:36
 */
@ControllerAdvice
@RestController
public class ControllerExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 所有异常统一处理
     */
    @ExceptionHandler(value = Exception.class)
    public CommonResp exceptionHandler(Exception e) throws Exception{
//        LOG.info("seata全局事务ID: {}", RootContext.getXID());
//        // 如果是在一次全局事务里出异常了，就不要包装返回值，将异常抛给调用方，让调用方回滚事务
//        if (StrUtil.isNotBlank(RootContext.getXID())) {
//            throw e;
//        }
        CommonResp commonResp = new CommonResp();
        LOG.error("系统异常", e);
        commonResp.setSuccess(false);
        commonResp.setMessage("系统出现异常，请联系管理员");
        return commonResp;
    }

    /**
     * 业务异常统一处理
     */
    @ExceptionHandler(value = BusinessException.class)
    public CommonResp exceptionHandler(BusinessException e){
        CommonResp commonResp = new CommonResp();
        LOG.error("业务异常：{}", e.getE().getDesc());
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getE().getDesc());
        return commonResp;
    }

    /**
     * 校验异常统一处理
     */
    @ExceptionHandler(value = BindException.class)
    public CommonResp exceptionHandler(BindException e){
        CommonResp commonResp = new CommonResp();
        LOG.error("校验异常：{}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return commonResp;
    }

}
