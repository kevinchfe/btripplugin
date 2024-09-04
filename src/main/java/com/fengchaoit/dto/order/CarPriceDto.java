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
public class CarPriceDto {
    private Long id;

    /**
     * 资金流向 1-支出 2-收入
     */
    private Integer type;

    /**
     * 交易类目编码
     * 1：用车支付。
     * 2：用车服务费。
     * 3：用车取消后收费。
     * 99：用车调账扣款。
     * 101：用车退款。
     * 102：用车赔付。
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
