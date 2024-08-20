package com.fengchaoit.model;

import lombok.Getter;

import java.util.function.Consumer;

/**
 * 数据同步插件元数据
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 9:19 2024/4/15
 */
@Getter
public class Meta {
    /**
     * schema版本
     */
    private final Integer schemaVersion;
    /**
     * 版本号
     */
    private final String version;
    /**
     * 类型
     */
    private final String type;
    /**
     * 额外数据
     */
    private final ExtraData extraData;
    /**
     * 协议
     */
    private final Protocol protocol;

    public Meta(Builder builder) {
        this.schemaVersion = builder.schemaVersion;
        this.version = builder.version;
        this.type = builder.type;
        this.extraData = builder.extraData;
        this.protocol = builder.protocol;
    }

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {
        /**
         * schema版本
         */
        private Integer schemaVersion = 1;
        /**
         * 版本号
         */
        private String version = "1.0.0";
        /**
         * 类型
         */
        private String type = "data_connector";
        /**
         * 额外数据
         */
        private ExtraData extraData;
        /**
         * 协议信息
         */
        private Protocol protocol;

        public Builder schemaVersion(int schemaVersion) {
            this.schemaVersion = schemaVersion;
            return this;
        }

        public Builder version(String version) {
            this.version = version;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }


        public Builder extraData(Consumer<ExtraData.Builder> consumer) {
            ExtraData.Builder builder = ExtraData.create();
            consumer.accept(builder);
            this.extraData = builder.build();
            return this;
        }


        public Builder protocol(Consumer<Protocol.Builder> consumer) {
            Protocol.Builder builder = Protocol.create();
            consumer.accept(builder);
            this.protocol = builder.build();
            return this;
        }

        public Meta build() {
            return new Meta(this);
        }
    }
}
