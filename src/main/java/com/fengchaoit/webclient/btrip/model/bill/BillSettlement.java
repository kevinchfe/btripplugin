package com.fengchaoit.webclient.btrip.model.bill;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 账单结算类
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 上午11:17 2024/8/23
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BillSettlement<T> {
    /**
     * 企业ID
     */
    @JsonProperty("corp_id")
    private String corpId;

    /**
     * 总数量
     */
    @JsonAlias({"total_size", "total_num"})
    private Long totalNum;

    /**
     * 记账数据查询起始时间。
     */
    @JsonProperty("period_start")
    private String periodStart;

    /**
     * 记账查询截止时间
     */
    @JsonProperty("period_end")
    private String periodEnd;

    /**
     * 数据所属类目 1：机票；2：酒店；（暂不支持）3：火车  4：用车  6：商旅火车票  11: 国际机票
     */
    private Integer category;

    /**
     * 数据列表
     */
    @JsonAlias({"data_list", "items"})
    private List<T> records;
}
