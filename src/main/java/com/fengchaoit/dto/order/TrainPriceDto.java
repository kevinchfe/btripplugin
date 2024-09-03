package com.fengchaoit.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 机票价格dto
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午5:27 2024/8/30
 */
@Getter
@Setter
public class TrainPriceDto {
    private Long id;

    /**
     * 资金流向 1-支出 2-收入
     */
    private Integer type;

    /**
     * 交易类目编码
     * 1：预订成功。
     * 2：退票成功。
     * 3：改签成功。
     * 4：差额退款。
     * 5：改签手续费(已废弃2024-05-16)。
     * 6：线下退票改签退款。
     * 7：火车票服务费。
     * 8：火车票赔付。
     * 9：火车票改签服务费。
     * 10：出票失败退款。
     * 11：火车票服务费退款。
     * 99：冲正。
     */
    private Integer categoryCode;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 结算方式
     * 1-个人现付 2-企业现付 3-企业月结 4-企业预存
     */
    private Integer payType;
}
