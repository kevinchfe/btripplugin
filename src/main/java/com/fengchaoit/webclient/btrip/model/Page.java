package com.fengchaoit.webclient.btrip.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 分页
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 22:20 2024/7/7
 */
@Getter
@Setter
@ToString
public class Page<T> {
    /**
     * 总数
     */
    private Integer total;
    /**
     * 是否有下一页
     */
    @JsonProperty("has_more")
    private Boolean hasMore;
    /**
     * 数据项
     */
    private List<T> items;
}
