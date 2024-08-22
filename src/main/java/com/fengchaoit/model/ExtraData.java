package com.fengchaoit.model;

import lombok.Getter;

/**
 * 额外数据
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 9:22 2024/4/15
 */
@Getter
public class ExtraData {
    /**
     * 同步模式 manual-手动同步 ，periodic-定时同步， trigger-事件更新同步(同时会24h全量同步一次兜底保证数据最终一致)
     */
    private final String syncType;
    /**
     * 定时同步
     */
    private final boolean periodicSync;
    /**
     * 数据员配置UI地址
     */
    private final String dataSourceConfigUiUri;
    /**
     * 初始化的配置窗口内容高度，最小226，最大606
     */
    private final int initHeight;
    /**
     * 初始化的配置窗口内容宽度，最小420，最大840
     */
    private final int initWeight;

    public ExtraData(Builder builder) {
        this.syncType = builder.syncType;
        this.periodicSync = builder.periodicSync;
        this.dataSourceConfigUiUri = builder.dataSourceConfigUiUri;
        this.initHeight = builder.initHeight;
        this.initWeight = builder.initWeight;
    }

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {
        public String syncType;
        /**
         * 定时同步
         */
        private boolean periodicSync;
        /**
         * 数据员配置UI地址
         */
        private String dataSourceConfigUiUri;
        /**
         * 初始化的配置窗口内容高度，最小226，最大606
         */
        private int initHeight;
        /**
         * 初始化的配置窗口内容宽度，最小420，最大840
         */
        private int initWeight;

        public Builder syncType(SyncType syncType) {
            this.syncType = syncType.getType();
            return this;
        }

        public Builder periodicSync(boolean periodicSync) {
            this.periodicSync = periodicSync;
            return this;
        }

        public Builder dataSourceConfigUiUri(String dataSourceConfigUiUri) {
            this.dataSourceConfigUiUri = dataSourceConfigUiUri;
            return this;
        }

        public Builder initHeight(int initHeight) {
            this.initHeight = initHeight;
            return this;
        }

        public Builder initWeight(int initWeight) {
            this.initWeight = initWeight;
            return this;
        }

        ExtraData build() {
            return new ExtraData(this);
        }
    }
}
