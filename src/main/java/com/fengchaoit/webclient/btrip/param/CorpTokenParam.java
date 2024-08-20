package com.fengchaoit.webclient.btrip.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 请求token参数
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 16:06 2024/4/15
 */
@AllArgsConstructor(staticName = "of")
@Getter
public class CorpTokenParam {
    /**
     * 应用secret
     */
    @JsonProperty("app_secret")
    private String appSecret;
    /**
     * 类型
     */
    @JsonProperty("type")
    private int type;
    /**
     * 企业ID
     */
    @JsonProperty("corp_id")
    private String corpId;
}
