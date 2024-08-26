package com.fengchaoit.component.alibtrip.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * 账单请求参数
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 上午9:53 2024/8/26
 */
@Builder
@Getter
public class BillSettlementParam {
    /**
     * 记账数据起始时  yyyy-MM-dd(或 yyyy-MM-dd HH:mm:ss)
     */
    @JsonProperty("period_start")
    private String periodStart;

    /**
     * 记账数据截止时间 yyyy-MM-dd(或 yyyy-MM-dd HH:mm:ss),最大值为当前时间之前一小时,且与 period_start 相差不超过一天
     */
    @JsonProperty("period_end")
    private String periodEnd;

    /**
     * 页数，从 1 开始
     */
    @Builder.Default
    @JsonProperty("page_no")
    private int pageNo = 1;

    /**
     * 每页数据量
     */
    @Builder.Default
    @JsonProperty("page_size")
    private int pageSize = 100;

}
