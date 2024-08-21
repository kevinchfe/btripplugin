package com.fengchaoit.component.feishu.datasync.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
public class TableMeta {
    @JsonProperty(value = "tableName", index = 0)
    private String name;

    @JsonProperty(value = "fields", index = 1)
    private List<Field> fields;
}
