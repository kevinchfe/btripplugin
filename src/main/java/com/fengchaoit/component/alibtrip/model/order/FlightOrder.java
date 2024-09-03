package com.fengchaoit.component.alibtrip.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fengchaoit.component.feishu.datasync.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 机票订单
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午4:24 2024/8/26
 */
@Getter
@Setter
@ToString
public class FlightOrder extends Order {
    /**
     * 到达城市
     */
    @JsonProperty("arr_city")
    @TableField(fieldName = "到达城市", description = "到达城市")
    private String arrCity;

    /**
     * 到达城市adcode
     */
    @JsonProperty("arr_city_ad_code")
    @TableField(fieldName = "到达城市六字码", description = "到达城市六字码")
    private String arrCityAdCode;

    /**
     * 用户所在部门
     */
    @JsonProperty("depart_id")
    @TableField(fieldName = "用户所在部门", description = "用户所在部门")
    private String departId;

    /**
     * 出发城市六字码
     */
    @JsonProperty("dep_city_ad_code")
    @TableField(fieldName = "出发城市六字码", description = "出发城市六字码")
    private String depCityAdCode;

    /**
     * 出发城市
     */
    @JsonProperty("dep_city")
    @TableField(fieldName = "出发城市", description = "出发城市")
    private String depCity;

    /**
     * 乘客数量
     */
    @JsonProperty("passenger_count")
    @TableField(fieldName = "乘客数量", description = "乘客数量")
    private String passengerCount;

    /**
     * 改签折扣
     */
    @JsonProperty("discount")
    @TableField(fieldName = "折扣", description = "折扣")
    private String discount;

    /**
     * 出发日期
     */
    @JsonProperty("dep_date")
    @TableField(fieldName = "出发日期", description = "出发日期", fieldType = 5)
    private Long depDate;

    /**
     * 部门名称
     */
    @JsonProperty("depart_name")
    @TableField(fieldName = "部门名称", description = "部门名称")
    private String departName;

    /**
     * 行程类型
     * 0：单程。
     * 1：往返。
     * 2：中转。
     */
    @JsonProperty("trip_type")
    @TableField(fieldName = "行程类型", description = "行程类型")
    private Integer tripType;

    /**
     * 航班号
     */
    @JsonProperty("flight_no")
    @TableField(fieldName = "航班号", description = "航班号")
    private String flightNo;

    /**
     * 第三方项目 id
     */
    @JsonProperty("third_part_project_id")
    @TableField(fieldName = "第三方项目 id", description = "第三方项目 id")
    private String thirdPartProjectId;

    /**
     * 舱等
     */
    @JsonProperty("cabin_class")
    @TableField(fieldName = "舱等", description = "舱等")
    private String cabinClass;

    /**
     * 联系人
     */
    @JsonProperty("contact_name")
    @TableField(fieldName = "联系人", description = "联系人")
    private String contactName;

    /**
     * 到达机场
     */
    @JsonProperty("arr_airport")
    @TableField(fieldName = "到达机场", description = "到达机场")
    private String arrAirport;

    /**
     * 乘客姓名
     */
    @JsonProperty("passenger_name")
    @TableField(fieldName = "乘客姓名", description = "乘客姓名")
    private String passengerName;

    /**
     * 出发机场
     */
    @JsonProperty("dep_airport")
    @TableField(fieldName = "出发机场", description = "出发机场")
    private String depAirport;

    /**
     * 申请单名称
     */
    @JsonProperty("btrip_title")
    @TableField(fieldName = "申请单名称", description = "申请单名称")
    private String btripTitle;

    /**
     * 成本中心 id
     */
    @JsonProperty("cost_center_id")
    @TableField(fieldName = "成本中心id", description = "成本中心id")
    private String costCenterId;

    /**
     * 成本中心名称
     */
    @JsonProperty("cost_center_name")
    @TableField(fieldName = "商旅成本中心名称", description = "商旅成本中心名称")
    private String costCenterName;

    /**
     * 成本中心编号
     */
    @JsonProperty("cost_center_number")
    @TableField(fieldName = "商旅成本中心编号", description = "商旅成本中心编号")
    private String costCenterNumber;

    /**
     * 发票 id
     */
    @JsonProperty("invoice_id")
    @TableField(fieldName = "商旅发票 id", description = "商旅发票 id")
    private String invoiceId;

    /**
     * 发票抬头
     */
    @JsonProperty("invoice_title")
    @TableField(fieldName = "商旅发票抬头", description = "商旅发票抬头")
    private String invoiceTitle;

    /**
     * 到达日期
     */
    @JsonProperty("ret_date")
    @TableField(fieldName = "到达日期", description = "到达日期", fieldType = 5)
    private Long retDate;

    @JsonProperty("thirdpart_business_id")
    @TableField(fieldName = "用户展示的外部审批单 ID", description = "用户展示的外部审批单 ID")
    private String thirdpartBusinessId;

    /**
     * 订单状态
     * 0：待支付。
     * 1：出票中。
     * 2：已关闭。
     * 3：有改签单。
     * 4：有退票。
     * 5：出票成功。
     * 6：退票申请中。
     * 7：改签申请中。
     */
    @JsonProperty("status")
    @TableField(fieldName = "订单状态", description = "订单状态")
    private Integer status;

    // 订单价格类目
    // 预订
    @JsonProperty("book")
    @TableField(fieldName = "机票预订", description = "机票预订", fieldType = 8, order = 30)
    private BigDecimal book;

    @JsonProperty("book_pay_type")
    @TableField(fieldName = "机票预订支付方式", description = "机票预订支付方式", order = 31)
    private String bookPayType;

    // 改签
    @JsonProperty("change")
    @TableField(fieldName = "机票改签", description = "机票改签", fieldType = 8, order = 32)
    private BigDecimal change;

    @JsonProperty("change_pay_type")
    @TableField(fieldName = "机票改签支付方式", description = "机票改签支付方式", order = 33)
    private String changePayType;

    // 保险
    @JsonProperty("insurance")
    @TableField(fieldName = "保险", description = "保险", fieldType = 8, order = 34)
    private BigDecimal insurance;

    @JsonProperty("insurance_pay_type")
    @TableField(fieldName = "保险支付方式", description = "保险支付方式", order = 35)
    private String insurancePayType;

    // 行程单邮费
    @JsonProperty("itinerary_postage")
    @TableField(fieldName = "行程单邮费", description = "行程单邮费", fieldType = 8, order = 36)
    private BigDecimal itineraryPostage;

    @JsonProperty("itinerary_postage_pay_type")
    @TableField(fieldName = "行程单邮费支付方式", description = "行程单邮费支付方式", order = 37)
    private String itineraryPostagePayType;

    // 机票订单服务费
    @JsonProperty("ticket_order_service_fee")
    @TableField(fieldName = "机票订单服务费", description = "机票订单服务费", fieldType = 8, order = 38)
    private BigDecimal ticketOrderServiceFee;

    @JsonProperty("ticket_order_service_fee_pay_type")
    @TableField(fieldName = "机票订单服务费支付方式", description = "机票订单服务费支付方式", order = 39)
    private String ticketOrderServiceFeePayType;

    // 机票退票手续费
    @JsonProperty("ticket_refund_fee")
    @TableField(fieldName = "机票退票手续费", description = "机票退票手续费", fieldType = 8, order = 40)
    private BigDecimal ticketRefundFee;

    @JsonProperty("ticket_refund_fee_pay_type")
    @TableField(fieldName = "机票退票手续费支付方式", description = "机票退票手续费支付方式", order = 41)
    private String ticketRefundFeePayType;

    // 机票调账扣款
    @JsonProperty("ticket_adjustment_deduction")
    @TableField(fieldName = "机票调账扣款", description = "机票调账扣款", fieldType = 8, order = 42)
    private BigDecimal ticketAdjustmentDeduction;

    @JsonProperty("ticket_adjustment_deduction_pay_type")
    @TableField(fieldName = "机票调账扣款支付方式", description = "机票调账扣款支付方式", order = 43)
    private String ticketAdjustmentDeductionPayType;

    // 预订退款
    @JsonProperty("booking_refund")
    @TableField(fieldName = "预订退款", description = "预订退款", fieldType = 8, order = 44)
    private BigDecimal bookingRefund;

    @JsonProperty("booking_refund_pay_type")
    @TableField(fieldName = "预订退款支付方式", description = "预订退款支付方式", order = 45)
    private String bookingRefundPayType;

    // 改签退款
    @JsonProperty("change_refund")
    @TableField(fieldName = "改签退款", description = "改签退款", fieldType = 8, order = 46)
    private BigDecimal changeRefund;

    @JsonProperty("change_refund_pay_type")
    @TableField(fieldName = "改签退款支付方式", description = "改签退款支付方式", order = 47)
    private String changeRefundPayType;

    // 保险退款
    @JsonProperty("insurance_refund")
    @TableField(fieldName = "保险退款", description = "保险退款", fieldType = 8, order = 48)
    private BigDecimal insuranceRefund;

    @JsonProperty("insurance_refund_pay_type")
    @TableField(fieldName = "保险退款支付方式", description = "保险退款支付方式", order = 49)
    private String insuranceRefundPayType;

    // 行程单邮费退款
    @JsonProperty("itinerary_postage_refund")
    @TableField(fieldName = "行程单邮费退款", description = "行程单邮费退款", fieldType = 8, order = 50)
    private BigDecimal itineraryPostageRefund;

    @JsonProperty("itinerary_postage_refund_pay_type")
    @TableField(fieldName = "行程单邮费退款支付方式", description = "行程单邮费退款支付方式", order = 51)
    private String itineraryPostageRefundPayType;

    // 机票赔付
    @JsonProperty("ticket_compensation")
    @TableField(fieldName = "机票赔付", description = "机票赔付", fieldType = 8, order = 52)
    private BigDecimal ticketCompensation;

    @JsonProperty("ticket_compensation_pay_type")
    @TableField(fieldName = "机票赔付支付方式", description = "机票赔付支付方式", order = 53)
    private String ticketCompensationPayType;

    // 机票改签订单服务费
    @JsonProperty("ticket_change_order_service_fee")
    @TableField(fieldName = "机票改签订单服务费", description = "机票改签订单服务费", fieldType = 8, order = 54)
    private BigDecimal ticketChangeOrderServiceFee;

    @JsonProperty("ticket_change_order_service_fee_pay_type")
    @TableField(fieldName = "机票改签订单服务费支付方式", description = "机票改签订单服务费支付方式", order = 55)
    private String ticketChangeOrderServiceFeePayType;

    // 机票服务费退款
    @JsonProperty("ticket_service_fee_refund")
    @TableField(fieldName = "机票服务费退款", description = "机票服务费退款", fieldType = 8, order = 56)
    private BigDecimal ticketServiceFeeRefund;

    @JsonProperty("ticket_service_fee_refund_pay_type")
    @TableField(fieldName = "机票服务费退款支付方式", description = "机票服务费退款支付方式", order = 57)
    private String ticketServiceFeeRefundPayType;
}
