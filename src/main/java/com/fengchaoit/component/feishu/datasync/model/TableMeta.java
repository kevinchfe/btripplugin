package com.fengchaoit.component.feishu.datasync.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * 表元素
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午2:43 2024/8/20
 */
@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class TableMeta {
    /**
     * 表名
     */
    @JsonProperty(value = "tableName", index = 0)
    private String name;
    /**
     * 字段名
     */
    @JsonProperty(value = "fields", index = 1)
    private List<Field> fields;
}

