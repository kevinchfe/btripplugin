package com.fengchaoit.webclient.btrip;

import com.fengchaoit.component.alibtrip.model.bill.*;
import com.fengchaoit.component.alibtrip.param.BillSettlementParam;
import com.fengchaoit.starter.webclient.WebClient;
import com.fengchaoit.webclient.Result;
import com.fengchaoit.webclient.btrip.model.CorpToken;
import com.fengchaoit.webclient.btrip.model.order.*;
import com.fengchaoit.webclient.btrip.param.CorpTokenParam;
import com.fengchaoit.webclient.btrip.param.order.OrderListQueryParam;
import org.springframework.stereotype.Service;
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
@Service
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
     * 机票账单结果
     *
     * @param param 请求参数
     * @return 结果集
     */
    @GetExchange(value = "/flight/v1/bill-settlement")
    Result<BillSettlement<FlightBillSettlementRecord>> flightBillSettlement(@RequestBody BillSettlementParam param);
//    Result<com.fengchaoit.webclient.btrip.model.bill.BillSettlement<com.fengchaoit.webclient.btrip.model.bill.FlightBillSettlementRecord>> flightBillSettlement(@RequestBody BillSettlementParam param);

    /**
     * 酒店账单结果
     *
     * @param param 请求参数
     * @return 结果集
     */
    @GetExchange(value = "/hotel/v1/bill-settlement")
    Result<BillSettlement<HotelBillSettlementRecord>> hotelBillSettlement(@RequestBody BillSettlementParam param);

    /**
     * 火车账单结果
     *
     * @param param 请求参数
     * @return 结果集
     */
    @GetExchange(value = "/train/v1/bill-settlement")
    Result<BillSettlement<TrainBillSettlementRecord>> trainBillSettlement(@RequestBody BillSettlementParam param);

    /**
     * 打车账单结果
     *
     * @param param 请求参数
     * @return 结果集
     */
    @GetExchange(value = "/car/v1/bill-settlement")
    Result<BillSettlement<CarBillSettlementRecord>> carBillSettlement(@RequestBody BillSettlementParam param);

    /**
     * 机票订单列表查询
     *
     * @param param 请求参数
     * @return 结果集
     */
    @GetExchange("/flight/v1/order-list")
    OrderResult<List<FlightOrder>> flightOrderListQuery(@RequestBody OrderListQueryParam param);

    /**
     * 酒店订单列表查询
     *
     * @param param 请求参数
     * @return 结果集
     */
    @GetExchange("/hotel/v1/order-list")
    OrderResult<List<HotelOrder>> hotelOrderListQuery(@RequestBody OrderListQueryParam param);

    /**
     * 火车订单列表查询
     *
     * @param param 请求参数
     * @return 结果集
     */
    @GetExchange("/train/v1/order-list")
    OrderResult<List<TrainOrder>> trainOrderListQuery(@RequestBody OrderListQueryParam param);

    /**
     * 打车订单列表查询
     *
     * @param param 请求参数
     * @return 结果集
     */
    @GetExchange("/car/v1/order-list")
    OrderResult<List<CarOrder>> carOrderListQuery(@RequestBody OrderListQueryParam param);

}
