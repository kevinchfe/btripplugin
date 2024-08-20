package com.fengchaoit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 请求uri
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 9:30 2024/4/15
 */
@AllArgsConstructor(staticName = "of")
@Getter
class Uri {
    /**
     * 请求类型
     */
    private String type;
    /**
     * 请求地址
     */
    private String uri;
}
