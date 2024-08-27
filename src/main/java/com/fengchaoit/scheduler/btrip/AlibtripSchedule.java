package com.fengchaoit.scheduler.btrip;

import com.fengchaoit.component.alibtrip.model.bill.BillSettlement;
import com.fengchaoit.component.alibtrip.model.bill.FlightBillSettlementRecord;
import com.fengchaoit.component.alibtrip.model.bill.SettlementRecord;
import com.fengchaoit.component.alibtrip.param.BillSettlementParam;
import com.fengchaoit.exception.BusinessException;
import com.fengchaoit.utils.DateTimeFormatter;
import com.fengchaoit.webclient.btrip.AliBtripApi;
import com.fengchaoit.webclient.btrip.model.Result;
import com.fengchaoit.webclient.btrip.model.order.FlightOrder;
import com.fengchaoit.webclient.btrip.param.order.FlightOrderListQueryParam;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 阿里商旅订单定时任务
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 上午10:10 2024/8/23
 */
@Slf4j
@Component
public class AlibtripSchedule {

    private final AliBtripApi aliBtripApi;

    public AlibtripSchedule(AliBtripApi aliBtripApi) {
        this.aliBtripApi = aliBtripApi;
    }

    @PostConstruct
    public void init() {
        log.info("阿里商旅订单定时任务初始化");
    }

    /**
     * 拉取阿里商旅机票账单
     */
//    @Scheduled(cron = "0/5 * * * * ?")
    public void pullFlightBill() {
        log.info("拉取阿里商旅机票账单");

        // 定义一个map，key为账单类型，value为账单处理器
        Map<String, FetchBillHandler<? extends SettlementRecord>> handlerMap = new HashMap<>();
        // 机票账单处理器
        Instant start = Instant.now();

        BiFunction<AliBtripApi, BillSettlementParam, Result<BillSettlement<FlightBillSettlementRecord>>> flightBillSettlement = AliBtripApi::flightBillSettlement;

//        FetchBillHandler<? extends SettlementRecord> handler = (api, startTime, endTime, pageNo) -> {
//            return billSettlementQuery(api::flightBillSettlement, startTime, endTime, pageNo, "机票");
//        };

        // 当月第一天
        LocalDateTime startTime = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        // 加一天
        LocalDateTime endTime = startTime.plusDays(1);

        Result<BillSettlement<FlightBillSettlementRecord>> records = aliBtripApi.flightBillSettlement(BillSettlementParam.builder()
                .periodStart(DateTimeFormatter.dateTimeToString(startTime))
                .periodEnd(DateTimeFormatter.dateTimeToString(endTime))
                .pageNo(1)
                .build()
        );

        System.out.println(records);



        // 获取账单
//        billSettlementQuery(null, startTime, endTime, 1, "机票");


//        List<SettlementRecord> records = loadBillRecords(handler, startTime, endTime, 1);
        log.info("拉取阿里商旅机票账单耗时：{}ms", Instant.now().toEpochMilli() - start.toEpochMilli());

    }

    /**
     * 账单查询
     *
     * @param func      执行器
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param pageNo    页码
     * @param desc      描述 1:机票 2:酒店 3:火车 4:打车
     * @param <T>
     * @return 账单
     */
    private <T extends SettlementRecord> BillSettlement<T> billSettlementQuery(Function<BillSettlementParam, Result<BillSettlement<T>>> func,
                                                                               LocalDateTime startTime,
                                                                               LocalDateTime endTime,
                                                                               int pageNo,
                                                                               String desc) {
        System.out.println("开始时间：" + startTime);
        BillSettlementParam billSettlementParam = BillSettlementParam.builder()
                .periodStart(DateTimeFormatter.dateTimeToString(startTime))
                .periodEnd(DateTimeFormatter.dateTimeToString(endTime))
                .pageNo(Math.max(1, pageNo))
                .build();
        Result<BillSettlement<T>> result = func.apply(billSettlementParam);
        if (result.isSuccess()) {
            System.out.println(result.getModule());
            return result.getModule();
        }
        log.error("拉取{}账单失败，原因：{}", desc, result.getMessage());
        throw new BusinessException("获取" + desc + "账单异常， 响应编码为：" + result.getCode() + "，响应消息为：" + result.getMessage());
    }

    /**
     * 加载账单记录
     *
     * @param handler   账单处理器
     * @param api       阿里商旅接口
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param pageNo    页码
     * @param <T>       账单记录类型 泛型
     * @return 账单记录
     */
    private <T extends SettlementRecord> List<T> loadBillRecords(FetchBillHandler<T> handler,
                                                                 LocalDateTime startTime,
                                                                 LocalDateTime endTime,
                                                                 int pageNo) {
        BillSettlement<? extends T> module = handler.fetchBillHandle(startTime, endTime, pageNo);
        List<T> records = new ArrayList<>(module.getRecords());
        if (Objects.nonNull(module.getTotalNum()) && module.getTotalNum() > (pageNo * 100L)) {
            List<T> nextRecords = loadBillRecords(handler, startTime, endTime, pageNo + 1);
            records.addAll(nextRecords);
        }
        return records;
    }


    @FunctionalInterface
    interface FetchBillHandler<T extends SettlementRecord> {
        /**
         * 获取账单处理器
         *
         * @param api       阿里商旅接口
         * @param startTime 开始时间
         * @param endTime   结束时间
         * @param pageNo    页码
         * @return 账单
         */
        BillSettlement<? extends T> fetchBillHandle(LocalDateTime startTime, LocalDateTime endTime, int pageNo);
    }


}
