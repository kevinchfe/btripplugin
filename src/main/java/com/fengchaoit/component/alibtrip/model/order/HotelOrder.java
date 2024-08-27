package com.fengchaoit.component.alibtrip.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fengchaoit.component.feishu.datasync.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 酒店订单
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午5:45 2024/8/26
 */
@Getter
@Setter
@ToString
public class HotelOrder extends Order {
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
     * 联系人姓名
     */
    @JsonProperty("contact_name")
    @TableField(fieldName = "联系人姓名", description = "联系人姓名")
    private String contactName;

    /**
     * 酒店所在城市
     */
    @JsonProperty("city")
    @TableField(fieldName = "酒店所在城市", description = "酒店所在城市")
    private String city;

    /**
     * 酒店名称
     */
    @JsonProperty("hotel_name")
    @TableField(fieldName = "酒店名称", description = "酒店名称")
    private String hotelName;

    /**
     * 入住时间
     */
    @JsonProperty("check_in")
    @TableField(fieldName = "入住时间", description = "入住时间")
    private Long checkIn;

    /**
     * 离店时间
     */
    @JsonProperty("check_out")
    @TableField(fieldName = "离店时间", description = "离店时间")
    private Long checkOut;

    /**
     * 房型
     */
    @JsonProperty("room_type")
    @TableField(fieldName = "房型", description = "房型")
    private String roomType;

    /**
     * 房间数
     */
    @JsonProperty("room_num")
    @TableField(fieldName = "房间数", description = "房间数")
    private Integer roomNum;

    /**
     * 总共住几晚
     */
    @JsonProperty("night")
    @TableField(fieldName = "总共住几晚", description = "总共住几晚")
    private Integer night;

    /**
     * 入住顾客，多个用','分割
     */
    @JsonProperty("guest")
    @TableField(fieldName = "入住顾客", description = "入住顾客")
    private String guest;

    /**
     * 订单类型描述
     */
    @JsonProperty("order_type_desc")
    @TableField(fieldName = "订单类型描述", description = "订单类型描述")
    private String orderTypeDesc;

    /**
     * 订单状态描述
     */
    @JsonProperty("order_status_desc")
    @TableField(fieldName = "订单状态描述", description = "订单状态描述")
    private String orderStatusDesc;

    /**
     * 订单类型
     */
    @JsonProperty("order_type")
    @TableField(fieldName = "订单类型", description = "订单类型")
    private String orderType;

    /**
     * 订单状态
     * 1：待确认。
     * 2：待付款。
     * 3：预订成功。
     * 4：退款待确认。
     * 5：已取消(主动退款)。
     * 6：已关闭。
     * 9：已取消(确认无房)。
     * 10：退款申请未通过。
     * 11：交易成功。
     * -1：不可见。
     */
    @JsonProperty("order_status")
    @TableField(fieldName = "订单状态", description = "订单状态")
    private Integer orderStatus;

    /**
     * 第三方项目 id
     */
    @JsonProperty("thirdpart_project_id")
    @TableField(fieldName = "第三方项目 id", description = "第三方项目 id")
    private String thirdpartProjectId;

    /**
     * 申请单名称
     */
    @JsonProperty("btrip_title")
    @TableField(fieldName = "申请单名称", description = "申请单名称")
    private String btripTitle;

    /**
     * 酒店支持的发票类型
     * 11：仅支持增值税普通发票。
     * 12：支持增值税专用发票和增值税普通发票。
     */
    @JsonProperty("hotel_support_vat_invoice_type")
    @TableField(fieldName = "酒店支持的发票类型", description = "酒店支持的发票类型")
    private Integer hotelSupportVatInvoiceType;

    /**
     * 审批单扩展字段
     */
    @JsonProperty("extend_field")
    @TableField(fieldName = "审批单扩展字段", description = "审批单扩展字段")
    private String extendField;

    /**
     * 城市六字码
     */
    @JsonProperty("city_ad_code")
    @TableField(fieldName = "城市六字码", description = "城市六字码")
    private String cityAdCode;

    /**
     * 成本中心 id
     */
    @JsonProperty("cost_center_id")
    @TableField(fieldName = "成本中心id", description = "成本中心id")
    private String costCenterId;

    /**
     * 成本中心企业id
     */
    @JsonProperty("cost_center_corp_id")
    @TableField(fieldName = "企业id", description = "企业id")
    private String  costCenterCorpId;

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

    /**
     * 发票类型
     */
    @JsonProperty("invoice_type")
    @TableField(fieldName = "发票类型", description = "发票类型")
    private String invoiceType;

    // 订单价格类目
    // 预订
    @JsonProperty("book")
    @TableField(fieldName = "预订", description = "预订")
    private BigDecimal book;

    // 酒店服务费
    @JsonProperty("service_fee")
    @TableField(fieldName = "酒店服务费", description = "酒店服务费")
    private BigDecimal serviceFee;

    // 酒店调账
    @JsonProperty("adjustment")
    @TableField(fieldName = "酒店调账", description = "酒店调账")
    private BigDecimal adjustment;

    // 酒店退款
    @JsonProperty("refund")
    @TableField(fieldName = "酒店退款", description = "酒店退款")
    private BigDecimal refund;

    // 酒店赔付
    @JsonProperty("compensation")
    @TableField(fieldName = "酒店赔付", description = "酒店赔付")
    private BigDecimal compensation;

    // 福豆抵扣
    @JsonProperty("fudou_deduction")
    @TableField(fieldName = "福豆抵扣", description = "福豆抵扣")
    private BigDecimal fudouDeduction;

    // 福豆退款
    @JsonProperty("fudou_refund")
    @TableField(fieldName = "福豆退款", description = "福豆退款")
    private BigDecimal fudouRefund;
}
