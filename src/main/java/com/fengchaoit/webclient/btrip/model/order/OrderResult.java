package com.fengchaoit.webclient.btrip.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

/**
 * 请求返回值
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 上午10:53 2024/8/21
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderResult<T> {
    /**
     * 本次请求的唯一标识
     */
    private String requestId;
    /**
     * 跟踪请求全局的标识符
     */
    private String traceId;
    /**
     * 状态码
     */
    private String code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 是否请求成功
     */
    private boolean success;

    /**
     * 分页信息
     */
    @JsonProperty("page_info")
    private PageInfo pageInfo;

    @Data
    public static class PageInfo {
        private Integer page;

        /**
         * 每页大小
         */
        @JsonProperty("page_size")
        private Integer pageSize;
        /**
         * 总数
         */
        @JsonProperty("total_number")
        private Integer totalNumber;
    }

    /**
     * 返回数据
     */
    private T module;

}
