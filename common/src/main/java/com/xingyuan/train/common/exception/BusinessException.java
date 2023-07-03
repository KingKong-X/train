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

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
