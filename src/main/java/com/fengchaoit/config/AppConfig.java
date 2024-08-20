package com.fengchaoit.config;

import com.fengchaoit.config.props.AliBtripProperties;
import com.fengchaoit.starter.webclient.EnableWebClients;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 应用配置
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 16:22 2024/8/19
 */
@Configuration
@EnableWebClients("com.fengchaoit.webclient")
@EnableConfigurationProperties(AliBtripProperties.class)
public class AppConfig {
}
