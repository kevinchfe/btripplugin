package com.fengchaoit.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * http请求协议
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 9:30 2024/4/15
 */
@Getter
class HttpProtocol {
    /**
     * 请求地址
     */
    private final List<Uri> uris = new ArrayList<>();

    /**
     * 添加请求地址
     *
     * @param type 请求类型
     * @param uri  请求地址
     */
    public void appendUri(String type, String uri) {
        uris.add(Uri.of(type, uri));
    }
}
