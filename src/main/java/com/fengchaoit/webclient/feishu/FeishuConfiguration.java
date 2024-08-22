package com.fengchaoit.webclient.feishu;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 飞书配置
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 16:39 2024/8/19
 */
public class FeishuConfiguration {

    /**
     * 定义签名过滤器
     *
     * @return 签名过滤器
     */
    @Bean
    public SignatureFilter signatureFilter(ObjectMapper objectMapper) {
        return new SignatureFilter(objectMapper);
    }

}
