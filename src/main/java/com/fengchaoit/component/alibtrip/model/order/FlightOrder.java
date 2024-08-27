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
    @TableField(fieldName = "乘客数量", description = "乘客数量", fieldType = 2)
    private Integer passengerCount;

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
    private String depDate;

    /**
     * 部门名称
     */
    @JsonProperty("depart_name")
    @TableField(fieldName = "部门名称", description = "部门名称")
    private String departName;

    /**
     * todo 转换
     * 行程类型
     * 0：单程。
     * 1：往返。
     * 2：中转。
     */
    @JsonProperty("trip_type")
    @TableField(fieldName = "行程类型", description = "行程类型")
    private String tripType;

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
    private String retDate;

    @JsonProperty("thirdpart_business_id")
    @TableField(fieldName = "用户展示的外部审批单 ID", description = "用户展示的外部审批单 ID")
    private String thirdpartBusinessId;

    /**
     * todo insure_info_list数据转换
     * todo price_info_list数据转换
     * todo user_affiliate_list数据转换
     * todo 转换
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
    private String status;

    // 订单价格类目
    // 预订
    @JsonProperty("book")
    @TableField(fieldName = "机票预订", description = "机票预订", fieldType = 8)
    private BigDecimal book;

    // 改签
    @JsonProperty("change")
    @TableField(fieldName = "机票改签", description = "机票改签", fieldType = 8)
    private BigDecimal change;

    // 保险
    @JsonProperty("insurance")
    @TableField(fieldName = "保险", description = "保险", fieldType = 8)
    private BigDecimal insurance;

    // 行程单邮费
    @JsonProperty("itinerary_postage")
    @TableField(fieldName = "行程单邮费", description = "行程单邮费", fieldType = 8)
    private BigDecimal itineraryPostage;

    // 机票订单服务费
    @JsonProperty("ticket_order_service_fee")
    @TableField(fieldName = "机票订单服务费", description = "机票订单服务费", fieldType = 8)
    private BigDecimal ticketOrderServiceFee;

    // 机票退票手续费
    @JsonProperty("ticket_refund_fee")
    @TableField(fieldName = "机票退票手续费", description = "机票退票手续费", fieldType = 8)
    private BigDecimal ticketRefundFee;

    // 机票调账扣款
    @JsonProperty("ticket_adjustment_deduction")
    @TableField(fieldName = "机票调账扣款", description = "机票调账扣款", fieldType = 8)
    private BigDecimal ticketAdjustmentDeduction;

    // 预订退款
    @JsonProperty("booking_refund")
    @TableField(fieldName = "预订退款", description = "预订退款", fieldType = 8)
    private BigDecimal bookingRefund;

    // 改签退款
    @JsonProperty("change_refund")
    @TableField(fieldName = "改签退款", description = "改签退款", fieldType = 8)
    private BigDecimal changeRefund;

    // 保险退款
    @JsonProperty("insurance_refund")
    @TableField(fieldName = "保险退款", description = "保险退款", fieldType = 8)
    private BigDecimal insuranceRefund;

    // 行程单邮费退款
    @JsonProperty("itinerary_postage_refund")
    @TableField(fieldName = "行程单邮费退款", description = "行程单邮费退款", fieldType = 8)
    private BigDecimal itineraryPostageRefund;

    // 机票赔付
    @JsonProperty("ticket_compensation")
    @TableField(fieldName = "机票赔付", description = "机票赔付", fieldType = 8)
    private BigDecimal ticketCompensation;

    // 机票改签订单服务费
    @JsonProperty("ticket_change_order_service_fee")
    @TableField(fieldName = "机票改签订单服务费", description = "机票改签订单服务费", fieldType = 8)
    private BigDecimal ticketChangeOrderServiceFee;

    // 机票服务费退款
    @JsonProperty("ticket_service_fee_refund")
    @TableField(fieldName = "机票服务费退款", description = "机票服务费退款", fieldType = 8)
    private BigDecimal ticketServiceFeeRefund;
}
