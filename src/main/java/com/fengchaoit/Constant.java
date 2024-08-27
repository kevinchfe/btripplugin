package com.fengchaoit;

/**
 * 常量
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午3:54 2024/8/21
 */
public interface Constant {
    String CACHE_FOREVER_KEY = "forever";

    String CACHE_ONE_MINUTES_KEY = "oneminutes";

    // 飞书订阅缓存key
    String CACHE_FEISHU_SUBSCRIBE_KEY = "feishu_subscribe";

    // 飞书packid缓存key
    String CACHE_FEISHU_PACKID_KEY = "feishu_packid";

    // 飞书账单
    int FEISHU_BILL = 1;
    // 飞书订单
    int FEISHU_ORDER = 2;

}
