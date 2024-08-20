package com.fengchaoit.webclient.btrip.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 企业token
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 21:56 2024/4/12
 */
@Getter
@Setter
@ToString
public class CorpToken {
    /**
     * 过期时间（毫秒）
     */
    public Long expire;
    /**
     * 开始时间（毫秒）
     */
    public Long start;
    /**
     * token信息
     */
    public String token;
}
