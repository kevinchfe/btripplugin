package com.fengchaoit.component.feishu.datasync.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 表数据项
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 11:39 2024/4/15
 */
@Getter
public class TableRecord {
    /**
     * 主键 id，在同步表中能唯一标识一条记录
     */
    @JsonProperty(value = "primaryID", index = 0)
    private final String primaryId;
    /**
     * 字段数据
     */
    private final Map<String, Object> data;

    TableRecord(String primaryId) {
        this.primaryId = primaryId;
        this.data = new HashMap<>(2);
    }

    public void append(String key, Object val) {
        data.put(key, val);
    }
}
