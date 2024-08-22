package com.fengchaoit.webclient.feishu.model;

import lombok.*;

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
