package com.xingyuan.train.common.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xingyuan Huang
 * @since 2023/7/11 10:29
 */
@Data
public class PageResp<T> implements Serializable {
    /**
     * 总条数
     */
    private Long total;


    /**
     * 当前页的列表
     */
    private List<T> list;
}
