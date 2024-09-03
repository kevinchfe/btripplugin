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
public class FlightPriceDto {
    private Long id;

    /**
     * 资金流向 1-支出 2-收入
     */
    private Integer type;

    /**
     * 交易类目编码 1-机票预订 2-机票改签 3-保险 4-行程单邮费 5-机票订单服务费 6-机票退票手续费 99-机票调账扣款 101-预订退款 102-改签退款
     * 103-保险退款 104-行程单邮费退款 105-机票赔付 106-机票改签订单服务费 107-机票服务费退款
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
