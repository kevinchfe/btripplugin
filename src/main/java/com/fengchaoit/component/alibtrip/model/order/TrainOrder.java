package com.fengchaoit.component.alibtrip.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fengchaoit.component.feishu.datasync.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 火车票订单
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 上午10:25 2024/8/27
 */
@Getter
@Setter
@ToString
public class TrainOrder extends Order {
    /**
     * 用户所在部门id
     */
    @JsonProperty("depart_id")
    @TableField(fieldName = "用户所在部门id", description = "用户所在部门id")
    private String departId;

    /**
     * 用户所在部门名称
     */
    @JsonProperty("depart_name")
    @TableField(fieldName = "用户所在部门名称", description = "用户所在部门名称")
    private String departName;

    /**
     * 联系人
     */
    @JsonProperty("contact_name")
    @TableField(fieldName = "联系人", description = "联系人")
    private String contactName;

    /**
     * 始发站
     */
    @JsonProperty("dep_station")
    @TableField(fieldName = "始发站", description = "始发站")
    private String depStation;

    /**
     * 到达站
     */
    @JsonProperty("arr_station")
    @TableField(fieldName = "到达站", description = "到达站")
    private String arrStation;

    /**
     * 始发城市
     */
    @JsonProperty("dep_city")
    @TableField(fieldName = "始发城市", description = "始发城市")
    private String depCity;

    /**
     * 到达城市
     */
    @JsonProperty("arr_city")
    @TableField(fieldName = "到达城市", description = "到达城市")
    private String arrCity;

    /**
     * 出发时间
     */
    @JsonProperty("dep_time")
    @TableField(fieldName = "出发时间", description = "出发时间", fieldType = 5)
    private String depTime;

    /**
     * 到达时间
     */
    @JsonProperty("arr_time")
    @TableField(fieldName = "到达时间", description = "到达时间", fieldType = 5)
    private String arrTime;

    /**
     * 车次号
     */
    @JsonProperty("train_number")
    @TableField(fieldName = "车次号", description = "车次号")
    private String trainNumber;

    /**
     * 车次类型
     */
    @JsonProperty("train_type")
    @TableField(fieldName = "车次类型", description = "车次类型")
    private String trainType;

    /**
     * 座位类型
     */
    @JsonProperty("seat_type")
    @TableField(fieldName = "座位类型", description = "座位类型")
    private String seatType;

    /**
     * 运行时长
     */
    @JsonProperty("run_time")
    @TableField(fieldName = "运行时长", description = "运行时长")
    private String runTime;

    /**
     * 12306 票号
     */
    @JsonProperty("ticket_no12306")
    @TableField(fieldName = "12306票号", description = "12306票号")
    private String ticketNo12306;

    /**
     * 乘车人
     */
    @JsonProperty("rider_name")
    @TableField(fieldName = "乘车人", description = "乘车人")
    private String riderName;

    /**
     * 票数
     */
    @JsonProperty("ticket_count")
    @TableField(fieldName = "票数", description = "票数")
    private Integer ticketCount;

    /**
     * 状态
     * 0：待支付。
     * 1：出票中。
     * 2：已关闭。
     * 3：改签成功。
     * 4：有退票单。
     * 5：出票完成。
     * 6：退票申请中。
     * 7：改签申请中。
     * 8：已出票，已发货。
     * 9：出票失败。
     * 10：改签失败。
     * 11：退票失败。
     * 12：有线下改签。
     * 13：有线下退票。
     * 14：有线下退改。
     */
    @JsonProperty("status")
    @TableField(fieldName = "状态", description = "状态", fieldType = 2)
    private String status;

    /**
     * 第三方项目 id
     */
    @JsonProperty("third_part_project_id")
    @TableField(fieldName = "第三方项目 id", description = "第三方项目 id")
    private String thirdPartProjectId;

    /**
     * 申请单名称
     */
    @JsonProperty("btrip_title")
    @TableField(fieldName = "申请单名称", description = "申请单名称")
    private String btripTitle;

    /**
     * 出发城市六字码
     */
    @JsonProperty("dep_city_ad_code")
    @TableField(fieldName = "出发城市六字码", description = "出发城市六字码")
    private String depCityAdCode;

    /**
     * 到达城市六字码
     */
    @JsonProperty("arr_city_ad_code")
    @TableField(fieldName = "到达城市六字码", description = "到达城市六字码")
    private String arrCityAdCode;

    @JsonProperty("thirdpart_business_id")
    @TableField(fieldName = "用户展示的外部审批单 ID", description = "用户展示的外部审批单 ID")
    private String thirdpartBusinessId;

    /**
     * 成本中心信息
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
     * 商旅发票 id
     */
    @JsonProperty("invoice_id")
    @TableField(fieldName = "商旅发票 id", description = "商旅发票 id")
    private String invoiceId;

    /**
     * 商旅发票抬头
     */
    @JsonProperty("invoice_title")
    @TableField(fieldName = "商旅发票抬头", description = "商旅发票抬头")
    private String invoiceTitle;

    // 订单价格类目
    // 预订
    @JsonProperty("book")
    @TableField(fieldName = "预订", description = "预订", fieldType = 8)
    private BigDecimal book;

    // 退票成功
    @JsonProperty("refund")
    @TableField(fieldName = "退票成功", description = "退票成功", fieldType = 8)
    private BigDecimal refund;

    // 改签成功
    @JsonProperty("change")
    @TableField(fieldName = "改签成功", description = "改签成功", fieldType = 8)
    private BigDecimal change;

    // 差额退款
    @JsonProperty("diff_refund")
    @TableField(fieldName = "差额退款", description = "差额退款", fieldType = 8)
    private BigDecimal diffRefund;

    // 改签手续费
    @JsonProperty("change_fee")
    @TableField(fieldName = "改签手续费", description = "改签手续费", fieldType = 8)
    private BigDecimal changeFee;

    // 线下退票改签退款
    @JsonProperty("offline_refund")
    @TableField(fieldName = "线下退票改签退款", description = "线下退票改签退款", fieldType = 8)
    private BigDecimal offlineRefund;

    // 火车票服务费
    @JsonProperty("service_fee")
    @TableField(fieldName = "火车票服务费", description = "火车票服务费", fieldType = 8)
    private BigDecimal serviceFee;

    // 火车票赔付
    @JsonProperty("compensation")
    @TableField(fieldName = "火车票赔付", description = "火车票赔付", fieldType = 8)
    private BigDecimal compensation;

    // 火车票改签服务费
    @JsonProperty("change_service_fee")
    @TableField(fieldName = "火车票改签服务费", description = "火车票改签服务费", fieldType = 8)
    private BigDecimal changeServiceFee;

    // 出票失败退款
    @JsonProperty("ticket_fail_refund")
    @TableField(fieldName = "出票失败退款", description = "出票失败退款", fieldType = 8)
    private BigDecimal ticketFailRefund;

    // 火车票服务费退款
    @JsonProperty("service_fee_refund")
    @TableField(fieldName = "火车票服务费退款", description = "火车票服务费退款", fieldType = 8)
    private BigDecimal serviceFeeRefund;

    // 冲正
    @JsonProperty("reversal")
    @TableField(fieldName = "冲正", description = "冲正", fieldType = 8)
    private BigDecimal reversal;
}
