package com.fengchaoit.webclient.btrip.param.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 机票订单列表查询参数
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 14:32 2024/4/28
 */
@Builder
@Getter
public class OrderListQueryParam implements Serializable {
    /**
     * 三方用户 ID
     */
    @JsonProperty("user_id")
    private String userId;

    /**
     * 用户所在部门 id
     */
    @JsonProperty("depart_id")
    private String departId;

    /**
     * 开始时间 yyyy-MM-dd(或 yyyy-MM-dd HH:mm:ss)
     */
    @JsonProperty("start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startTime;

    /**
     * 结束时间 yyyy-MM-dd(或 yyyy-MM-dd HH:mm:ss)
     */
    @JsonProperty("end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;

    /**
     * 更新开始时间 yyyy-MM-dd(或 yyyy-MM-dd HH:mm:ss)
     */
    @JsonProperty("update_start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updateStartTime;

    /**
     * 更新结束时间 yyyy-MM-dd(或 yyyy-MM-dd HH:mm:ss)
     */
    @JsonProperty("update_end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updateEndTime;

    /**
     * 页码
     */
    @Builder.Default
    @JsonProperty("page")
    private int page = 1;

    /**
     * 每页大小
     */
    @Builder.Default
    @JsonProperty("page_size")
    private int pageSize = 100;
}
