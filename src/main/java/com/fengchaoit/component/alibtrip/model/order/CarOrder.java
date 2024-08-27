package com.fengchaoit.component.alibtrip.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fengchaoit.component.feishu.datasync.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 打车订单
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 上午10:36 2024/8/27
 */
@Getter
@Setter
@ToString
public class CarOrder extends Order {
    /**
     * 乘客姓名
     */
    @JsonProperty("passenger_name")
    @TableField(fieldName = "乘客姓名", description = "乘客姓名")
    private String passengerName;

    /**
     * 用户所在部门 id
     */
    @JsonProperty("dept_id")
    @TableField(fieldName = "用户所在部门 id", description = "用户所在部门 id")
    private String deptId;

    /**
     * 用户所在部门名称
     */
    @JsonProperty("dept_name")
    @TableField(fieldName = "用户所在部门名称", description = "用户所在部门名称")
    private String deptName;

    /**
     * 商旅审批单展示 id(非审批 api 对接企业)
     */
    @JsonProperty("apply_show_id")
    @TableField(fieldName = "商旅审批单展示 id", description = "商旅审批单展示 id")
    private String applyShowId;

    /**
     * 真实出发地
     */
    @JsonProperty("real_from_address")
    @TableField(fieldName = "真实出发地", description = "真实出发地")
    private String realFromAddress;

    /**
     * 真实目的地
     */
    @JsonProperty("real_to_address")
    @TableField(fieldName = "真实目的地", description = "真实目的地")
    private String realToAddress;

    /**
     * 真实出发城市名称
     */
    @JsonProperty("real_from_city_name")
    @TableField(fieldName = "真实出发城市名称", description = "真实出发城市名称")
    private String realFromCityName;

    /**
     * 真实目的城市名称
     */
    @JsonProperty("real_to_city_name")
    @TableField(fieldName = "真实目的城市名称", description = "真实目的城市名称")
    private String realToCityName;

    /**
     * 出发地
     */
    @JsonProperty("from_address")
    @TableField(fieldName = "出发地", description = "出发地")
    private String fromAddress;

    /**
     * 目的地
     */
    @JsonProperty("to_address")
    @TableField(fieldName = "目的地", description = "目的地")
    private String toAddress;

    /**
     * 出发城市名称
     */
    @JsonProperty("from_city_name")
    @TableField(fieldName = "出发城市名称", description = "出发城市名称")
    private String fromCityName;

    /**
     * 目的城市名称
     */
    @JsonProperty("to_city_name")
    @TableField(fieldName = "目的城市名称", description = "目的城市名称")
    private String toCityName;

    /**
     * 打车事由
     */
    @JsonProperty("memo")
    @TableField(fieldName = "打车事由", description = "打车事由")
    private String memo;

    /**
     * 订单状态
     * 0：初始化状态。
     * 1：已超时。
     * 2：派单成功。
     * 3：派单失败。
     * 4：已退款。
     * 5：已支付。
     * 6：已取消。
     */
    @JsonProperty("order_status")
    @TableField(fieldName = "订单状态", description = "订单状态", fieldType = 2)
    private String orderStatus;

    /**
     * 车类型级别
     * 1：经济型。
     * 2：舒适型。
     * 3：豪华型。
     */
    @JsonProperty("car_level")
    @TableField(fieldName = "车类型级别", description = "车类型级别", fieldType = 2)
    private String carLevel;

    /**
     * 车辆信息
     */
    @JsonProperty("car_info")
    @TableField(fieldName = "车辆信息", description = "车辆信息")
    private String carInfo;

    /**
     * 订单预估价格
     */
    @JsonProperty("estimate_price")
    @TableField(fieldName = "订单预估价格", description = "订单预估价格", fieldType = 8)
    private BigDecimal estimatePrice;

    /**
     * 乘客发布用车时间
     */
    @JsonProperty("publish_time")
    @TableField(fieldName = "乘客发布用车时间", description = "乘客发布用车时间", fieldType = 5)
    private String publishTime;

    /**
     * 乘客上车时间
     */
    @JsonProperty("taken_time")
    @TableField(fieldName = "乘客上车时间", description = "乘客上车时间", fieldType = 5)
    private String takenTime;

    /**
     * 司机到达目的地时间
     */
    @JsonProperty("arrive_time")
    @TableField(fieldName = "司机到达目的地时间", description = "司机到达目的地时间", fieldType = 5)
    private String driverConfirmTime;

    /**
     * 取消时间
     */
    @JsonProperty("cancel_time")
    @TableField(fieldName = "取消时间", description = "取消时间", fieldType = 5)
    private String cancelTime;

    /**
     * 支付时间
     */
    @JsonProperty("pay_time")
    @TableField(fieldName = "支付时间", description = "支付时间", fieldType = 5)
    private String payTime;

    /**
     * 行驶公里数
     */
    @JsonProperty("travel_distance")
    @TableField(fieldName = "行驶公里数", description = "行驶公里数")
    private Double travelDistance;

    /**
     * 打车服务类型
     * 1：出租车。
     * 2：专车。
     * 3：快车。
     */
    @JsonProperty("service_type")
    @TableField(fieldName = "打车服务类型", description = "打车服务类型", fieldType = 2)
    private String serviceType;

    /**
     * 用车原因
     * TRAFFIC：市内交通。
     * OTHER：其他。
     * TRAVEL：差旅。
     * WORK：加班。
     */
    @JsonProperty("reason")
    @TableField(fieldName = "用车原因", description = "用车原因", fieldType = 2)
    private String businessCategory;

    /**
     * 商旅成本中心 ID
     */
    @JsonProperty("cost_center_id")
    @TableField(fieldName = "商旅成本中心 ID", description = "商旅成本中心 ID")
    private String costCenterId;

    /**
     * 商旅成本中心编号
     */
    @JsonProperty("cost_center_number")
    @TableField(fieldName = "商旅成本中心编号", description = "商旅成本中心编号")
    private String costCenterNumber;

    /**
     * 商旅成本中心名称
     */
    @JsonProperty("cost_center_name")
    @TableField(fieldName = "商旅成本中心名称", description = "商旅成本中心名称")
    private String costCenterName;

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

    /**
     * 用户确认状态
     * 0：未确认。
     * 1：已确认。
     * 2：有异议。
     * 3：系统检查不合理。
     */
    @JsonProperty("user_confirm")
    @TableField(fieldName = "用户确认状态", description = "用户确认状态", fieldType = 2)
    private String userConfirm;

    /**
     * 服务商（可能会有其他服务商的增加）
     * 2：滴滴。
     * 3：曹操。
     * 4：首汽。
     * 5：阳光。
     */
    @JsonProperty("provider")
    @TableField(fieldName = "服务商", description = "服务商", fieldType = 2)
    private String provider;

    /**
     * 申请单名称
     */
    @JsonProperty("apply_title")
    @TableField(fieldName = "申请单名称", description = "申请单名称")
    private String btripTitle;

    /**
     * 是否特殊订单
     */
    @JsonProperty("is_special")
    @TableField(fieldName = "是否特殊订单", description = "是否特殊订单")
    private Boolean isSpecial;

    /**
     * 实际出发城市六字码
     */
    @JsonProperty("real_from_city_ad_code")
    @TableField(fieldName = "实际出发城市六字码", description = "实际出发城市六字码")
    private String realFromCityAdCode;

    /**
     * 实际目的城市六字码
     */
    @JsonProperty("real_to_city_ad_code")
    @TableField(fieldName = "实际目的城市六字码", description = "实际目的城市六字码")
    private String realToCityAdCode;

    /**
     * 出发城市六字码
     */
    @JsonProperty("from_city_ad_code")
    @TableField(fieldName = "出发城市六字码", description = "出发城市六字码")
    private String fromCityAdCode;

    /**
     * 目的城市六字码
     */
    @JsonProperty("to_city_ad_code")
    @TableField(fieldName = "目的城市六字码", description = "目的城市六字码")
    private String toCityAdCode;

    /**
     * 用户展示的外部审批单 ID
     */
    @JsonProperty("thirdpart_business_id")
    @TableField(fieldName = "用户展示的外部审批单 ID", description = "用户展示的外部审批单 ID")
    private String thirdpartBusinessId;

    // 价格信息类目
    // 用车支付
    @JsonProperty("price_info")
    @TableField(fieldName = "用车支付", description = "用车支付", fieldType = 8)
    private BigDecimal princeInfo;

    // 用车服务费
    @JsonProperty("service_price_info")
    @TableField(fieldName = "用车服务费", description = "用车服务费", fieldType = 8)
    private BigDecimal servicePriceInfo;

    // 用车取消后收费
    @JsonProperty("cancel_price_info")
    @TableField(fieldName = "用车取消后收费", description = "用车取消后收费", fieldType = 8)
    private BigDecimal cancelPriceInfo;

    // 用车调账扣款
    @JsonProperty("adjust_price_info")
    @TableField(fieldName = "用车调账扣款", description = "用车调账扣款", fieldType = 8)
    private BigDecimal adjustPriceInfo;

    // 退款
    @JsonProperty("refund_price_info")
    @TableField(fieldName = "退款", description = "退款", fieldType = 8)
    private BigDecimal refundPriceInfo;

    // 赔付
    @JsonProperty("compensation_price_info")
    @TableField(fieldName = "赔付", description = "赔付", fieldType = 8)
    private BigDecimal compensationPriceInfo;
}
