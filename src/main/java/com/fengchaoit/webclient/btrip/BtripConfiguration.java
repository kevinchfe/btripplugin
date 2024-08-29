package com.fengchaoit.webclient.btrip;

import com.fengchaoit.config.props.AliBtripProperties;
import org.springframework.context.annotation.Bean;

/**
 * 商旅配置
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 16:39 2024/8/19
 */
public class BtripConfiguration {

    /**
     * 定义token过滤器
     *
     * @return 商旅过滤器
     */
    @Bean
    public BtripTokenFilter btripTokenFilter() {
        return new BtripTokenFilter();
    }
}
