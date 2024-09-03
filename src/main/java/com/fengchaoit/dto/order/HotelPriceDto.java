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
public class HotelPriceDto {
    private Long id;

    /**
     * 资金流向 1-支出 2-收入
     */
    private Integer type;

    /**
     * 交易类目编码
     * 1：酒店预订。
     * 2：酒店服务费。
     * 99：酒店调账。
     * 101：酒店退款。
     * 102：酒店赔付。
     * 110：福豆抵扣。
     * 111：福豆退款。
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
