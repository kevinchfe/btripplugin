package com.fengchaoit.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 表元素局参数
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午2:25 2024/8/20
 */
@Getter
@Setter
@ToString
public class TableMetaParam {
    /**
     * 请求参数
     */
    private String params;
    /**
     * 同步插件上下文
     */
    private String context;
}
