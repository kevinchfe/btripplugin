package com.fengchaoit.component.alibtrip.model.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fengchaoit.component.feishu.datasync.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 酒店账单结算类
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午3:06 2024/8/23
 */
@Getter
@Setter
@ToString(callSuper = true)
public class HotelBillSettlementRecord extends SettlementRecord {
    /**
     * 入住时间
     */
    @JsonProperty("check_in_date")
    @TableField(fieldName = "入住时间", description = "入住时间", fieldType = 5)
    private String checkInDate;

    /**
     * 离店时间
     */
    @JsonProperty("check_out_date")
    @TableField(fieldName = "离店时间", description = "离店时间", fieldType = 5)
    private String checkOutDate;

    /**
     * 入住城市
     */
    @TableField(fieldName = "入住城市", description = "入住城市")
    private String city;

    /**
     * 入住城市编码
     */
    @TableField(fieldName = "入住城市编码", description = "入住城市编码")
    @JsonProperty("city_code")
    private String cityCode;

    /**
     * 酒店名称
     */
    @TableField(fieldName = "酒店名称", description = "酒店名称")
    @JsonProperty("hotel_name")
    private String hotelName;

    /**
     * 房型
     */
    @TableField(fieldName = "房型", description = "房型")
    @JsonProperty("room_type")
    private String roomType;

    /**
     * 房间序号
     */
    @TableField(fieldName = "房间序号", description = "房间序号", fieldType = 2)
    @JsonProperty("room_number")
    private Integer roomNumber;

    /**
     * 入住天数
     */
    @TableField(fieldName = "入住天数", description = "入住天数", fieldType = 2)
    @JsonProperty("nights")
    private Integer nights;

    /**
     * 总间夜数
     */
    @TableField(fieldName = "总间夜数", description = "总间夜数", fieldType = 2)
    @JsonProperty("total_nights")
    private Integer totalNights;

    /**
     * 订单类型
     */
    @TableField(fieldName = "订单类型", description = "订单类型")
    @JsonProperty("order_type")
    private String orderType;

    /**
     * 订单金额
     */
    @TableField(fieldName = "订单金额", description = "订单金额", fieldType = 8)
    @JsonProperty("order_price")
    private BigDecimal orderPrice;

    /**
     * 企业支付金额
     */
    @TableField(fieldName = "企业支付金额", description = "企业支付金额", fieldType = 8)
    @JsonProperty("corp_total_fee")
    private BigDecimal corpTotalFee;

    /**
     * 个人结算费用
     */
    @TableField(fieldName = "个人结算费用", description = "个人结算费用", fieldType = 8)
    @JsonProperty("personal_settle_price")
    private BigDecimal personalSettlePrice;

    /**
     * 福豆支付
     */
    @TableField(fieldName = "福豆支付", description = "福豆支付", fieldType = 8)
    @JsonProperty("fu_point_price")
    private BigDecimal fuPointPrice;


    /**
     * 房价
     */
    @TableField(fieldName = "房价", description = "房价", fieldType = 8)
    @JsonProperty("room_price")
    private BigDecimal roomPrice;

    /**
     * 杂费
     */
    @TableField(fieldName = "杂费", description = "杂费", fieldType = 8)
    @JsonProperty("fees")
    private BigDecimal fees;

    /**
     * 企业退款金额
     */
    @TableField(fieldName = "企业退款金额", description = "企业退款金额", fieldType = 8)
    @JsonProperty("corp_refund_fee")
    private Double corpRefundFee;

    /**
     * 个人退款金额
     */
    @TableField(fieldName = "个人退款金额", description = "个人退款金额", fieldType = 8)
    @JsonProperty("personal_refund_fee")
    private Double personalRefundFee;

    /**
     * 优惠券
     */
    @TableField(fieldName = "优惠券", description = "优惠券", fieldType = 8)
    @JsonProperty("promotion_fee")
    private Double promotionFee;

    /**
     * 是否合住
     */
    @TableField(fieldName = "是否合住", description = "是否合住")
    @JsonProperty("is_share_str")
    private String isShareStr;

    /**
     * 是否协议价
     */
    @TableField(fieldName = "是否协议价", description = "是否协议价")
    @JsonProperty("is_negotiation")
    private String isNegotiation;

    /**
     * 审批扩展自定义字段
     */
    @TableField(fieldName = "审批扩展自定义字段", description = "审批扩展自定义字段")
    @JsonProperty("apply_extend_field")
    private String applyExtendField;

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
     * 房间序号
     */
    @TableField(fieldName = "房间序号", description = "房间序号")
    @JsonProperty("room_no")
    private String roomNo;

    /**
     * 平均间夜数
     */
    @TableField(fieldName = "平均间夜数", description = "平均间夜数")
    @JsonProperty("average_nights")
    private String averageNights;

    /**
     * 差旅标准
     */
    @TableField(fieldName = "差旅标准", description = "差旅标准")
    @JsonProperty("reserve_rule")
    private String reserveRule;

    /**
     * 是否提前离店
     */
    @TableField(fieldName = "是否提前离店", description = "是否提前离店")
    @JsonProperty("is_early_departure")
    private String earlyDeparture;

    /**
     * 罚金
     */
    @TableField(fieldName = "罚金", description = "罚金")
    @JsonProperty("fines")
    private BigDecimal fines;

    /**
     * 入住县级市
     */
    @TableField(fieldName = "入住县级市", description = "入住县级市")
    @JsonProperty("city_county")
    private String cityCountry;

    /**
     * 入住县级市 code
     */
    @TableField(fieldName = "入住县级市 code", description = "入住县级市 code")
    @JsonProperty("city_county_code")
    private String cityCountryCode;

    /**
     * 出差事由
     */
    @TableField(fieldName = "出差事由", description = "出差事由")
    @JsonProperty("business_trip_result")
    private String businessTripResult;

    /**
     * 超标原因
     */
    @TableField(fieldName = "超标原因", description = "超标原因")
    @JsonProperty("exceed_reason")
    private String exceedReason;

    /**
     * 预定非协议价原因
     */
    @TableField(fieldName = "预定非协议价原因", description = "预定非协议价原因")
    @JsonProperty("book_reason")
    private String bookReason;

    /**
     * 酒店集团
     */
    @TableField(fieldName = "酒店集团", description = "酒店集团")
    @JsonProperty("brand_group")
    private String brandGroup;

    /**
     * 酒店品牌
     */
    @TableField(fieldName = "酒店品牌", description = "酒店品牌")
    @JsonProperty("brand_name")
    private String brandName;

    /**
     * 酒店星级
     */
    @TableField(fieldName = "酒店星级", description = "酒店星级")
    @JsonProperty("star")
    private String star;

    /**
     * 出行人类型
     */
    @TableField(fieldName = "出行人类型", description = "出行人类型")
    @JsonProperty("traveler_member_type_name")
    private String travelerMemberTypeName;

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

    /**
     * 保险险种
     */
    @TableField(fieldName = "保险险种", description = "保险险种")
    @JsonProperty("insurance_product_name")
    private String insuranceProductName;

    /**
     * 保险费
     */
    @TableField(fieldName = "保险费", description = "保险费", fieldType = 8)
    @JsonProperty("insurance_price")
    private BigDecimal insurancePrice;

    /**
     * 保险订单号
     */
    @TableField(fieldName = "保险订单号", description = "保险订单号")
    @JsonProperty("ins_order_id")
    private String insOrderId;

    /**
     * 保单号
     */
    @TableField(fieldName = "保单号", description = "保单号")
    @JsonProperty("insurance_number")
    private String insuranceNumber;

    /**
     * 协议价优惠金额
     */
    @TableField(fieldName = "协议价优惠金额", description = "协议价优惠金额", fieldType = 8)
    @JsonProperty("agreement_promotion_fee")
    private BigDecimal agreementPromotionFee;

    /**
     * 开票方
     */
    @TableField(fieldName = "开票方", description = "开票方")
    @JsonProperty("billing_entity")
    private String billingEntity;

}
