package com.fengchaoit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用启动类
 *
 * @author wangchuan
 * @since 2024-08-19 16:22
 */
@SpringBootApplication(scanBasePackages = "com.fengchaoit.config")
public class Bootstrap {
    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class);
    }
}
