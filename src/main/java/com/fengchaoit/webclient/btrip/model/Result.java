package com.fengchaoit.webclient.btrip.model;

import lombok.*;

/**
 * 企业token
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 21:56 2024/4/12
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Result<T> {
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
     * 返回数据
     */
    private T module;

}
