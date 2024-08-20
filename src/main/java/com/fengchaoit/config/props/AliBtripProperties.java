package com.fengchaoit.config.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里商旅参数
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 16:55 2024/8/19
 */
@Getter
@Setter
@ConfigurationProperties("webclient.alibtrip")
public class AliBtripProperties {
    private String appKey;

    private String appSecret;

    private String corpId;
}
