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
     * 字段类型
     *
     * @return 字段类型
     * 1：多行文本
     * 2：数字
     * 3：单选
     * 4：多选
     * 5：日期
     * 6: 条码
     * 7：复选框
     * 8: 货币
     * 9：电话号码
     * 10：超链接
     * 11:  进度
     * 12:  评分
     * 13:  地理位置
     */
    int fieldType() default 1;

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

    /**
     * 字段排序
     *
     * @return 字段排序
     */
    int order() default -1;
}
