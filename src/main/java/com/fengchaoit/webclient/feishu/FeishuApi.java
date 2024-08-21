package com.fengchaoit.webclient.feishu;

import com.fengchaoit.starter.webclient.WebClient;
import com.fengchaoit.webclient.btrip.param.feishu.EventParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

/**
 * 飞书接口
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午5:54 2024/8/20
 */
@WebClient(url = "https://open.feishu.cn/open-apis")
public interface FeishuApi {

    @PostExchange(value = "/space/api/bitable/connector/event")
    void syncEvent(@RequestBody EventParam body);

}
