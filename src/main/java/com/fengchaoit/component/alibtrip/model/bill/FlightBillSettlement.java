package com.fengchaoit.component.alibtrip.model.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fengchaoit.component.feishu.datasync.annotation.TableField;
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
public class FlightBillSettlement extends SettlementRecord {
    /**
     * 起飞日期
     */
    @TableField(fieldName = "起飞日期", description = "起飞日期", fieldType = 5, order = 4)
    @JsonProperty("dept_date")
    private LocalDate deptDate;

    /**
     * 起飞时间
     */
    @TableField(fieldName = "起飞时间", description = "起飞时间")
    @JsonProperty("dept_time")
    private String deptTime;

    /**
     * 到达日期
     */
//    @TableField(fieldName = "到达日期", description = "到达日期", fieldType = 5)
//    @JsonProperty("arr_date")
//    private String arrDate;

    /**
     * 到达时间
     */
    @TableField(fieldName = "到达时间", description = "到达时间")
    @JsonProperty("arr_time")
    private String arrTime;

    /**
     * 起飞城市
     */
    @TableField(fieldName = "起飞城市", description = "起飞城市", order = 10)
    @JsonProperty("dept_city")
    private String deptCity;

    /**
     * 到达城市
     */
    @TableField(fieldName = "到达城市", description = "到达城市", order = 11)
    @JsonProperty("arr_city")
    private String arrCity;

    /**
     * 起飞机场三字码
     */
    @TableField(fieldName = "起飞机场三字码", description = "起飞机场三字码")
    @JsonProperty("dep_airport_code")
    private String depAirportCode;

    /**
     * 起飞机场名称
     */
    @TableField(fieldName = "起飞机场名称", description = "起飞机场名称")
    @JsonProperty("dept_station")
    private String deptStation;

    /**
     * 到达机场三字码
     */
    @TableField(fieldName = "到达机场三字码", description = "到达机场三字码")
    @JsonProperty("arr_airport_code")
    private String arrAirportCode;

    /**
     * 到达机场名称
     */
    @TableField(fieldName = "到达机场名称", description = "到达机场名称", order = 13)
    @JsonProperty("arr_station")
    private String arrStation;

    /**
     * 航空公司名称
     */
    @TableField(fieldName = "航空公司", description = "航空公司", order = 14)
    @JsonProperty("airline_corp_name")
    private String airlineCorpName;

    /**
     * 航空公司二字码
     */
    @TableField(fieldName = "航空公司二字码", description = "航空公司二字码")
    @JsonProperty("airline_corp_code")
    private String airlineCorpCode;

    /**
     * 航班号
     */
    @TableField(fieldName = "航班号", description = "航班号", order = 15)
    @JsonProperty("flight_no")
    private String flightNo;

    /**
     * 仓位代码
     */
    @TableField(fieldName = "仓位代码", description = "仓位代码")
    private String cabin;

    /**
     * 仓位
     */
    @TableField(fieldName = "仓位", description = "仓位", order = 16)
    @JsonProperty("cabin_class")
    private String cabinClass;

    /**
     * 行程单号
     */
    @TableField(fieldName = "行程单号", description = "行程单号")
    @JsonProperty("ticket_id")
    private String ticketId;

    /**
     * 订单金额
     */
    @TableField(fieldName = "订单金额", description = "订单金额", fieldType = 8)
    @JsonProperty("corp_pay_order_fee")
    private BigDecimal corpPayOrderFee;

    /**
     * 销售价
     */
    @TableField(fieldName = "销售价", description = "销售价", fieldType = 8)
    @JsonProperty("seal_price")
    private BigDecimal sealPrice;

    /**
     * 基建费
     */
    @TableField(fieldName = "基建费", description = "基建费", fieldType = 8)
    @JsonProperty("build_fee")
    private BigDecimal buildFee;

    /**
     * 燃油费
     */
    @TableField(fieldName = "燃油费", description = "燃油费", fieldType = 8)
    @JsonProperty("oil_fee")
    private BigDecimal oilFee;

    /**
     * 保险费
     */
    @JsonProperty("insurance_fee")
    @TableField(fieldName = "保险费", description = "保险费", fieldType = 8)
    private BigDecimal insuranceFee;

    /**
     * 改签手续费
     */
    @JsonProperty("change_fee")
    @TableField(fieldName = "改签手续费", description = "改签手续费", fieldType = 8)
    private BigDecimal changeFee;

    /**
     * 改签差价
     */
    @JsonProperty("upgrade_cost")
    @TableField(fieldName = "改签差价", description = "改签差价", fieldType = 8)
    private BigDecimal upgradeCost;

    /**
     * 退票手续费
     */
    @JsonProperty("refund_fee")
    @TableField(fieldName = "退票手续费", description = "退票手续费", fieldType = 8)
    private BigDecimal refundFee;

    /**
     * 改签退票手续费
     */
    @JsonProperty("refund_upgrade_cost")
    @TableField(fieldName = "改签退票手续费", description = "改签退票手续费", fieldType = 8)
    private BigDecimal refundUpgradeCost;

    /**
     * 优惠券
     */
    @TableField(fieldName = "优惠券", description = "优惠券", fieldType = 8)
    private BigDecimal coupon;

    /**
     * 折扣率
     */
    @TableField(fieldName = "折扣率", description = "折扣率")
    private String discount;

    /**
     * 商旅价优惠金额
     */
    @JsonProperty("btrip_coupon_fee")
    @TableField(fieldName = "商旅价优惠金额", description = "商旅价优惠金额", fieldType = 8)
    private BigDecimal btripCouponFee;

    /**
     * 协议价优惠金额
     */
    @JsonProperty("negotiation_coupon_fee")
    @TableField(fieldName = "协议价优惠金额", description = "协议价优惠金额", fieldType = 8)
    private BigDecimal negotiationCouponFee;

    /**
     * 提前预定天数
     */
    @JsonProperty("advance_day")
    @TableField(fieldName = "提前预定天数", description = "提前预定天数")
    private String advanceDay;

    /**
     * 低价航班价格
     */
    @JsonProperty("most_price")
    @TableField(fieldName = "低价航班价格", description = "低价航班价格", fieldType = 8)
    private BigDecimal mostPrice;

    /**
     * 低价提醒(与最低价差额)
     */
    @JsonProperty("most_difference_price")
    @TableField(fieldName = "低价提醒(与最低价差额)", description = "低价提醒(与最低价差额)", fieldType = 8)
    private BigDecimal mostDifferencePrice;

    /**
     * 低价提醒(航班号)
     */
    @JsonProperty("most_difference_flight_no")
    @TableField(fieldName = "低价提醒(航班号)", description = "低价提醒(航班号)")
    private String mostDifferenceFlightNo;

    /**
     * 低价提醒（折扣）
     */
    @JsonProperty("most_difference_discount")
    @TableField(fieldName = "低价提醒（折扣）", description = "低价提醒（折扣）")
    private String mostDifferenceDiscount;

    /**
     * 低价提醒（起飞时间）
     */
    @JsonProperty("most_difference_dept_time")
    @TableField(fieldName = "低价提醒（起飞时间）", description = "低价提醒（起飞时间）")
    private String mostDifferenceDeptTime;
    /**
     * 不选低价原因
     */
    @JsonProperty("most_difference_reason")
    @TableField(fieldName = "不选低价原因", description = "不选低价原因")
    private String mostDifferenceReason;

    /**
     * 是否重复退
     */
    @JsonProperty("repeat_refund")
    @TableField(fieldName = "是否重复退", description = "是否重复退")
    private String repeatRefund;

    /**
     * 行程单打印序号
     */
    @JsonProperty("itinerary_num")
    @TableField(fieldName = "行程单打印序号", description = "行程单打印序号")
    private String itineraryNum;

    /**
     * 行程单金额
     */
    @JsonProperty("itinerary_price")
    @TableField(fieldName = "行程单金额", description = "行程单金额", fieldType = 8)
    private BigDecimal itineraryPrice;

    /**
     * 审批扩展自定义字段
     */
    @TableField(fieldName = "审批扩展自定义字段", description = "审批扩展自定义字段")
    @JsonProperty("apply_extend_field")
    private String applyExtendField;

    /**
     * 出发城市(6 字码)
     */
    @TableField(fieldName = "出发城市(6 字码)", description = "出发城市(6 字码)")
    @JsonProperty("dep_city_code")
    private String depCityCode;

    /**
     * 到达城市(6 字码)
     */
    @TableField(fieldName = "到达城市(6 字码)", description = "到达城市(6 字码)")
    @JsonProperty("arr_city_code")
    private String arrCityCode;

    /**
     * 申请出发城市 code
     */
    @TableField(fieldName = "申请出发城市 code", description = "申请出发城市 code")
    @JsonProperty("apply_dep_city_code")
    private String applyDepCityCode;

    /**
     * 申请出发城市名称
     */
    @TableField(fieldName = "申请出发城市名称", description = "申请出发城市名称")
    @JsonProperty("apply_dep_city_name")
    private String applyDepCityName;

    /**
     * 申请到达城市 code
     */
    @TableField(fieldName = "申请到达城市 code", description = "申请到达城市 code")
    @JsonProperty("apply_arr_city_code")
    private String applyArrCityCode;

    /**
     * 申请到达城市名称
     */
    @TableField(fieldName = "申请到达城市名称", description = "申请到达城市名称")
    @JsonProperty("apply_arr_city_name")
    private String applyArrCityName;

    /**
     * 行程(城市)
     */
    @TableField(fieldName = "行程(城市)", description = "行程(城市)")
    @JsonProperty("voyage_name")
    private String voyageName;

    /**
     * 行程类型(0 直达 1 中转 2 往返)
     */
    @TableField(fieldName = "行程类型(0 直达 1 中转 2 往返)", description = "行程类型(0 直达 1 中转 2 往返)")
    @JsonProperty("item_type")
    private String itemType;

    /**
     * 退订原因
     */
    @TableField(fieldName = "退订原因", description = "退订原因")
    @JsonProperty("refund_result")
    private String refundResult;

    /**
     * 企业支付金额
     */
    @TableField(fieldName = "企业支付金额", description = "企业支付金额", fieldType = 8)
    @JsonProperty("corp_settle_price")
    private BigDecimal corpSettlePrice;

    /**
     * 个人支付金额
     */
    @TableField(fieldName = "个人支付金额", description = "个人支付金额", fieldType = 8)
    @JsonProperty("person_settle_price")
    private BigDecimal personSettlePrice;

    /**
     * 行程起始城市机场三字码
     */
    @TableField(fieldName = "行程", description = "行程")
    @JsonProperty("trade")
    private String trade;

    /**
     * 出差事由
     */
    @TableField(fieldName = "出差事由", description = "出差事由")
    @JsonProperty("business_trip_result")
    private String businessTripResult;

    /**
     * 改签事由
     */
    @TableField(fieldName = "改签事由", description = "改签事由")
    @JsonProperty("change_result")
    private String changeResult;

    /**
     * 保险订单号
     */
    @TableField(fieldName = "保险订单号", description = "保险订单号")
    @JsonProperty("ins_order_id")
    private String insOrderId;

    /**
     * 超标原因
     */
    @TableField(fieldName = "超标原因", description = "超标原因")
    @JsonProperty("exceed_reason")
    private String exceedReason;

    /**
     * 保单号
     */
    @TableField(fieldName = "保单号", description = "保单号")
    @JsonProperty("insurance_number")
    private String insuranceNumber;

    /**
     * 里程
     */
    @TableField(fieldName = "里程", description = "里程")
    @JsonProperty("mileage")
    private String mileage;

    /**
     * 未提前预定原因
     */
    @TableField(fieldName = "未提前预定原因", description = "未提前预定原因")
    @JsonProperty("pre_book_tip")
    private String preBookTip;

    /**
     * 出行人类型
     */
    @TableField(fieldName = "出行人类型", description = "出行人类型")
    @JsonProperty("traveler_member_type_name")
    private String travelerMemberTypeName;

    /**
     * 支付宝 PID
     */
    @TableField(fieldName = "支付宝 PID", description = "支付宝 PID")
    @JsonProperty("alipay_id")
    private String alipayId;

    /**
     * 费用归属部门 id
     */
    @TableField(fieldName = "费用归属部门 id", description = "费用归属部门 id")
    @JsonProperty("payment_department_id")
    private String paymentDepartmentId;

    /**
     * 费用归属部门名称
     */
    @TableField(fieldName = "费用归属部门名称", description = "费用归属部门名称")
    @JsonProperty("payment_department_name")
    private String paymentDepartmentName;
}
