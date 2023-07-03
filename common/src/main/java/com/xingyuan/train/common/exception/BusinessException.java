package com.xingyuan.train.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xingyuan Huang
 * @since 2023/7/3 22:57
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BusinessException extends RuntimeException{
    private BusinessExceptionEnum e;

    /**
     * 重写方法，这样不用写入堆栈
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
