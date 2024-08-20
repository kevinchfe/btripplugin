package com.fengchaoit.model;

import lombok.Getter;

/**
 * 协议内容
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 9:29 2024/4/15
 */

@Getter
public class Protocol {
    /**
     * 协议内容
     */
    private final String type;
    /**
     * 请求地址配置
     */
    private final HttpProtocol httpProtocol;

    public Protocol(Builder builder) {
        this.type = builder.type;
        this.httpProtocol = builder.httpProtocol;
    }

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {

        private String type;

        private final HttpProtocol httpProtocol = new HttpProtocol();

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        /**
         * 添加请求地址
         *
         * @param type 请求类型
         * @param uri  请求地址
         */
        public Builder appendUri(String type, String uri) {
            httpProtocol.appendUri(type, uri);
            return this;
        }

        Protocol build() {
            return new Protocol(this);
        }

    }


}
