package com.fengchaoit.scheduler.feishu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 多维表格同步定时任务
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午5:43 2024/8/20
 */
@Slf4j
public class DwbgSyncSchedule {

    @Scheduled(cron = "0/5 * * * * ?")
    public void syncEvent() {
        // 调取连接器监听事件





    }

}
