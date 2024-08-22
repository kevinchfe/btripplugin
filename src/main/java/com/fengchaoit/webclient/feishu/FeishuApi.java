package com.fengchaoit.webclient.feishu;

import com.fengchaoit.starter.webclient.WebClient;
import com.fengchaoit.webclient.feishu.param.EventParam;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

/**
 * 飞书接口
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午5:54 2024/8/20
 */
@Service
@WebClient(url = "https://m45he5vd5s.feishu.cn", configuration = FeishuConfiguration.class)
//@WebClient(url = "http://un5mw8.natappfree.cc", configuration = FeishuConfiguration.class)
public interface FeishuApi {

    @PostExchange(value = "/space/api/bitable/connector/event")
    String syncEvent(@RequestBody EventParam body);
}
