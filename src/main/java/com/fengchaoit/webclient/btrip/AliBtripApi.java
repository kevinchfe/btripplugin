package com.fengchaoit.webclient.btrip;

import com.fengchaoit.starter.webclient.WebClient;
import com.fengchaoit.webclient.btrip.model.CorpToken;
import com.fengchaoit.webclient.btrip.model.Result;
import com.fengchaoit.webclient.btrip.model.order.FlightOrder;
import com.fengchaoit.webclient.btrip.param.CorpTokenParam;
import com.fengchaoit.webclient.btrip.param.order.FlightOrderListQueryParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

/**
 * 阿里商旅接口
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 16:23 2024/8/19
 */
@WebClient(url = "https://btripopen.alibtrip.com/api", configuration = BtripConfiguration.class)
public interface AliBtripApi {

    /**
     * 企业token
     *
     * @param param 请求参数
     * @return 结果集
     */
    @GetExchange(value = "/btrip-open-auth/v1/corp-token/action/take")
    Result<CorpToken> corpToken(@RequestBody CorpTokenParam param);

    /**
     * 机票订单列表查询
     *
     * @param param 请求参数
     * @return 结果集
     */
    @GetExchange("/flight/v1/order-list")
    Result<List<FlightOrder>> flightOrderListQuery(@RequestBody FlightOrderListQueryParam param);

}
