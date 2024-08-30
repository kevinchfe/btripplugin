package com.fengchaoit.webclient.btrip.model.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 机票账单数据
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午1:57 2024/8/23
 */
@Getter
@Setter
@ToString(callSuper = true)
public class FlightBillSettlementRecord extends SettlementRecord {
    /**
     * 起飞日期
     */
    @JsonProperty("dept_date")
    private LocalDate deptDate;

    /**
     * 起飞时间
     */
    @JsonProperty("dept_time")
    private String deptTime;

    /**
     * 到达日期
     */
    @JsonProperty("arr_date")
    private LocalDate arrDate;

    /**
     * 到达时间
     */
    @JsonProperty("arr_time")
    private String arrTime;

    /**
     * 起飞城市
     */
    @JsonProperty("dept_city")
    private String deptCity;

    /**
     * 到达城市
     */
    @JsonProperty("arr_city")
    private String arrCity;

    /**
     * 起飞机场三字码
     */
    @JsonProperty("dep_airport_code")
    private String depAirportCode;

    /**
     * 起飞机场名称
     */
    @JsonProperty("dept_station")
    private String deptStation;

    /**
     * 到达机场三字码
     */
    @JsonProperty("arr_airport_code")
    private String arrAirportCode;

    /**
     * 到达机场名称
     */
    @JsonProperty("arr_station")
    private String arrStation;

    /**
     * 航空公司名称
     */
    @JsonProperty("airline_corp_name")
    private String airlineCorpName;

    /**
     * 航空公司二字码
     */
    @JsonProperty("airline_corp_code")
    private String airlineCorpCode;

    /**
     * 航班号
     */
    @JsonProperty("flight_no")
    private String flightNo;

    /**
     * 仓位代码
     */
    private String cabin;

    /**
     * 仓位
     */
    @JsonProperty("cabin_class")
    private String cabinClass;

    /**
     * 行程单号
     */
    @JsonProperty("ticket_id")
    private String ticketId;

    /**
     * 订单金额
     */
    @JsonProperty("corp_pay_order_fee")
    private BigDecimal corpPayOrderFee;

    /**
     * 销售价
     */
    @JsonProperty("seal_price")
    private BigDecimal sealPrice;

    /**
     * 基建费
     */
    @JsonProperty("build_fee")
    private BigDecimal buildFee;

    /**
     * 燃油费
     */
    @JsonProperty("oil_fee")
    private BigDecimal oilFee;

    /**
     * 保险费
     */
    @JsonProperty("insurance_fee")
    private BigDecimal insuranceFee;

    /**
     * 改签手续费
     */
    @JsonProperty("change_fee")
    private BigDecimal changeFee;

    /**
     * 改签差价
     */
    @JsonProperty("upgrade_cost")
    private BigDecimal upgradeCost;

    /**
     * 退票手续费
     */
    @JsonProperty("refund_fee")
    private BigDecimal refundFee;

    /**
     * 改签退票手续费
     */
    @JsonProperty("refund_upgrade_cost")
    private BigDecimal refundUpgradeCost;

    /**
     * 优惠券
     */
    private BigDecimal coupon;

    /**
     * 折扣率
     */
    private String discount;

    /**
     * 商旅价优惠金额
     */
    @JsonProperty("btrip_coupon_fee")
    private BigDecimal btripCouponFee;

    /**
     * 协议价优惠金额
     */
    @JsonProperty("negotiation_coupon_fee")
    private BigDecimal negotiationCouponFee;

    /**
     * 提前预定天数
     */
    @JsonProperty("advance_day")
    private String advanceDay;

    /**
     * 低价航班价格
     */
    @JsonProperty("most_price")
    private BigDecimal mostPrice;

    /**
     * 低价提醒(与最低价差额)
     */
    @JsonProperty("most_difference_price")
    private BigDecimal mostDifferencePrice;

    /**
     * 低价提醒(航班号)
     */
    @JsonProperty("most_difference_flight_no")
    private String mostDifferenceFlightNo;

    /**
     * 低价提醒（折扣）
     */
    @JsonProperty("most_difference_discount")
    private String mostDifferenceDiscount;

    /**
     * 低价提醒（起飞时间）
     */
    @JsonProperty("most_difference_dept_time")
    private String mostDifferenceDeptTime;
    /**
     * 不选低价原因
     */
    @JsonProperty("most_difference_reason")
    private String mostDifferenceReason;

    /**
     * 是否重复退
     */
    @JsonProperty("repeat_refund")
    private String repeatRefund;

    /**
     * 行程单打印序号
     */
    @JsonProperty("itinerary_num")
    private String itineraryNum;

    /**
     * 行程单金额
     */
    @JsonProperty("itinerary_price")
    private BigDecimal itineraryPrice;

    /**
     * 审批扩展自定义字段
     */
    @JsonProperty("apply_extend_field")
    private String applyExtendField;

    /**
     * 出发城市(6 字码)
     */
    @JsonProperty("dep_city_code")
    private String depCityCode;

    /**
     * 到达城市(6 字码)
     */
    @JsonProperty("arr_city_code")
    private String arrCityCode;

    /**
     * 申请出发城市 code
     */
    @JsonProperty("apply_dep_city_code")
    private String applyDepCityCode;

    /**
     * 申请出发城市名称
     */
    @JsonProperty("apply_dep_city_name")
    private String applyDepCityName;

    /**
     * 申请到达城市 code
     */
    @JsonProperty("apply_arr_city_code")
    private String applyArrCityCode;

    /**
     * 申请到达城市名称
     */
    @JsonProperty("apply_arr_city_name")
    private String applyArrCityName;

    /**
     * 行程(城市)
     */
    @JsonProperty("voyage_name")
    private String voyageName;

    /**
     * 行程类型(0 直达 1 中转 2 往返)
     */
    @JsonProperty("item_type")
    private String itemType;

    /**
     * 退订原因
     */
    @JsonProperty("refund_result")
    private String refundResult;

    /**
     * 企业支付金额
     */
    @JsonProperty("corp_settle_price")
    private BigDecimal corpSettlePrice;

    /**
     * 个人支付金额
     */
    @JsonProperty("person_settle_price")
    private BigDecimal personSettlePrice;

    /**
     * 行程起始城市机场三字码
     */
    @JsonProperty("trade")
    private String trade;

    /**
     * 出差事由
     */
    @JsonProperty("business_trip_result")
    private String businessTripResult;

    /**
     * 改签事由
     */
    @JsonProperty("change_result")
    private String changeResult;

    /**
     * 保险订单号
     */
    @JsonProperty("ins_order_id")
    private String insOrderId;

    /**
     * 超标原因
     */
    @JsonProperty("exceed_reason")
    private String exceedReason;

    /**
     * 保单号
     */
    @JsonProperty("insurance_number")
    private String insuranceNumber;

    /**
     * 里程
     */
    @JsonProperty("mileage")
    private String mileage;

    /**
     * 未提前预定原因
     */
    @JsonProperty("pre_book_tip")
    private String preBookTip;

    /**
     * 出行人类型
     */
    @JsonProperty("traveler_member_type_name")
    private String travelerMemberTypeName;

    /**
     * 支付宝 PID
     */
    @JsonProperty("alipay_id")
    private String alipayId;

    /**
     * 费用归属部门 id
     */
    @JsonProperty("payment_department_id")
    private String paymentDepartmentId;

    /**
     * 费用归属部门名称
     */
    @JsonProperty("payment_department_name")
    private String paymentDepartmentName;
}
