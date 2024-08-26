package com.fengchaoit.scheduler.btrip;

import com.fengchaoit.component.alibtrip.model.bill.BillSettlement;
import com.fengchaoit.component.alibtrip.model.bill.SettlementRecord;
import com.fengchaoit.webclient.btrip.AliBtripApi;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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

    @PostConstruct
    public void init() {
        log.info("阿里商旅订单定时任务初始化");
    }

    // 定时拉取阿里商旅订单
    // @Scheduled(cron = "0 0 0/1 * * ?")
    public void pullOrder() {

        log.info("拉取阿里商旅订单");
    }

    /**
     * 拉取阿里商旅账单
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void pullBill() {
        log.info("拉取阿里商旅账单");
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
         * @return
         */
        BillSettlement<? extends T> fetchBillHandle(AliBtripApi api, LocalDateTime startTime, LocalDateTime endTime, int pageNo);
    }


}
