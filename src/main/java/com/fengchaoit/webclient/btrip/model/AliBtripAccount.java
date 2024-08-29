package com.fengchaoit.webclient.btrip.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 阿里商旅账号信息
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午5:55 2024/8/29
 */
@AllArgsConstructor(staticName = "of")
@Getter
public class AliBtripAccount {

    private final String appKey;

    private final String appSecret;

    private final String corpId;
}
