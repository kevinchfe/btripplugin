package com.fengchaoit.component.feishu.datasync.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * 表字段信息
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午2:44 2024/8/20
 */
@Getter
public class Field {
    /**
     * 字段
     */
    @JsonIgnore
    private java.lang.reflect.Field field;

    /**
     * 字段id
     */
    @JsonProperty("fieldID")
    private String id;

    /**
     * 字段名
     */
    @JsonProperty("fieldName")
    private String name;

    /**
     * 字段类型
     */
    @JsonProperty("fieldType")
    private Integer type;

    /**
     * 是否是索引列
     */
    @JsonProperty("isPrimary")
    private boolean primary;

    /**
     * 字段描述
     */
    @JsonProperty("description")
    private String description;


    private Field(String id, String name, Integer type, boolean primary, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.primary = primary;
        this.description = description;
    }

    private Field(java.lang.reflect.Field field, String id, String name, Integer type, boolean primary, String description) {
        this(id, name, type, primary, description);
        this.field = field;
    }

    public static Field of(String id, String name, Integer type, boolean primary, String description) {
        return new Field(id, name, type, primary, description);
    }

    public static Field of(java.lang.reflect.Field field, String id, String name, Integer type, boolean primary, String description) {
        return new Field(field, id, name, type, primary, description);
    }
}
