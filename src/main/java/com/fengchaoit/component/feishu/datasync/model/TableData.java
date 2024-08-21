package com.fengchaoit.component.feishu.datasync.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 表格数据
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 11:38 2024/4/15
 */
@Getter
public class TableData {
    /**
     * 下一页token
     */
    private final String nextPageToken;
    /**
     * 是否还有下一页
     */
    private final boolean hasMore;
    /**
     * 对象数组
     */
    private final List<TableRecord> records;

    TableData(Builder builder) {
        this.nextPageToken = builder.nextPageToken;
        this.hasMore = builder.hasMore;
        this.records = builder.records;
    }

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {
        /**
         * 下一页token
         */
        private String nextPageToken = "";
        /**
         * 是否还有下一页
         */
        private boolean hasMore;
        /**
         * 对象数组
         */
        private final List<TableRecord> records;

        Builder() {
            records = new ArrayList<>(2);
        }

        public Builder nextPageToken(String nextPageToken) {
            this.nextPageToken = nextPageToken;
            return this;
        }

        public Builder hasMore(boolean hasMore) {
            this.hasMore = hasMore;
            return this;
        }

        public Builder record(String primaryID, Consumer<TableRecord> consumer) {
            TableRecord tableRecord = new TableRecord(primaryID);
            consumer.accept(tableRecord);
            records.add(tableRecord);
            return this;
        }

        public TableData build() {
            return new TableData(this);
        }
    }
}
