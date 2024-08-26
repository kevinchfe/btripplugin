package com.fengchaoit.component.feishu.datasync.annotation;

import java.lang.annotation.*;

/**
 * 表字段注解
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午2:02 2024/8/23
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableField {
    /**
     * 字段显示名称
     *
     * @return 字段名称
     */
    String fieldName();

    /**
     * 是否是索引列
     *
     * @return 是否是索引列
     */
    boolean primary() default false;

    /**
     * 字段描述
     *
     * @return 字段描述
     */
    String description() default "";

    int order() default -1;
}
