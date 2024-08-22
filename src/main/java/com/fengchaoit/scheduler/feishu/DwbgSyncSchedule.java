package com.fengchaoit.scheduler.feishu;

import com.fengchaoit.Constant;
import com.fengchaoit.webclient.feishu.FeishuApi;
import com.fengchaoit.webclient.feishu.param.EventParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 多维表格同步定时任务
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午5:43 2024/8/20
 */
@Slf4j
@Component
public class DwbgSyncSchedule {

    private final FeishuApi feishuApi;
    private final StringRedisTemplate stringRedisTemplate;

    public DwbgSyncSchedule(FeishuApi feishuApi, StringRedisTemplate stringRedisTemplate) {
        this.feishuApi = feishuApi;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Scheduled(cron = "0/20 * * * * ?")
    public void syncEvent() {
        List<String> primaryKeys = Arrays.asList("1", "2");
        String subscribeKey = stringRedisTemplate.opsForValue().get(Constant.CACHE_FEISHU_SUBSCRIBE_KEY);
        System.out.println("subscribeKey = " + subscribeKey);

        // 调取连接器监听事件
        EventParam param = new EventParam.Builder()
                .subscribeKey(subscribeKey)
                .packID("packID")
                .eventContent(new EventParam.EventContent.Builder()
                        .schema("1.0")
                        .header(new EventParam.Header.Builder()
                                .ts(System.currentTimeMillis())
                                .eventID("eventID")
                                .eventType(1)
                                .build())
                        .event(new EventParam.EventDetail.Builder()
                                .primaryKeys(primaryKeys)
                                .build())
                        .build())
                .build();
        String json = feishuApi.syncEvent(param);
    }
}

