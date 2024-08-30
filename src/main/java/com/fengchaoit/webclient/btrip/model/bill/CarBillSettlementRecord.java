package com.fengchaoit.webclient.btrip.model.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fengchaoit.component.feishu.datasync.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 用车记账记录
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午4:31 2024/8/23
 */
@Getter
@Setter
@ToString(callSuper = true)
public class CarBillSettlementRecord extends SettlementRecord {
    /**
     * 出发日期
     */
    @JsonProperty("dept_date")
    private LocalDate deptDate;

    /**
     * 出发时间
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
     * 出发城市
     */
    @JsonProperty("dept_city")
    private String deptCity;

    /**
     * 出发地
     */
    @JsonProperty("dept_location")
    private String deptLocation;

    /**
     * 实际上车点
     */
    @JsonProperty("real_from_addr")
    private String realFromAddr;

    /**
     * 到达城市
     */
    @JsonProperty("arr_city")
    private String arrCity;

    /**
     * 到达地
     */
    @JsonProperty("arr_location")
    private String arrLocation;

    /**
     * 实际下车点
     */
    @JsonProperty("real_to_addr")
    private String realToAddr;

    /**
     * 预估公里数
     */
    @JsonProperty("estimate_drive_distance")
    private String estimateDriveDistance;

    /**
     * 实际行驶公里数
     */
    @JsonProperty("real_drive_distance")
    private String realDriveDistance;

    /**
     * 订单金额
     */
    @JsonProperty("order_price")
    private BigDecimal orderPrice;

    /**
     * 个人支付金额
     */
    @JsonProperty("person_settle_fee")
    private BigDecimal personSettleFee;

    /**
     * 优惠券
     */
    @JsonProperty("coupon")
    private BigDecimal coupon;

    /**
     * 预估金额
     */
    @JsonProperty("estimate_price")
    private BigDecimal estimatePrice;

    /**
     * 商旅优惠金额
     */
    @JsonProperty("coupon_price")
    private BigDecimal couponPrice;

    /**
     * 用车原因
     */
    @JsonProperty("business_category")
    private String businessCategory;

    /**
     * 用车事由
     */
    @JsonProperty("memo")
    private String memo;

    /**
     * 员工是否认可
     */
    @JsonProperty("user_confirm_desc")
    private String userConfirmDesc;

    /**
     * 供应商车型
     */
    @JsonProperty("provider_name")
    private String providerName;

    /**
     * 车型
     */
    @JsonProperty("car_level")
    private String carLevel;

    /**
     * 特别关注订单
     */
    @JsonProperty("special_order")
    private String specialOrder;

    /**
     * 特别关注原因
     */
    @JsonProperty("special_reason")
    private String specialReason;

    /**
     * 子订单 id
     */
    @JsonProperty("sub_order_id")
    private String subOrderId;

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
     * 用车性质
     */
    @JsonProperty("book_model")
    private String bookModel;

    /**
     * 订单类型
     */
    @JsonProperty("time_type")
    private String timeType;

    /**
     * 行后审批单号
     */
    @JsonProperty("supplement_apply_id")
    private String supplementApplyId;

    /**
     * 供应商车型
     */
    @JsonProperty("level_name")
    private String levelName;

    /**
     * 司机添加费总额
     */
    @JsonProperty("driver_add_fee")
    private BigDecimal driverAddFee;

    /**
     * 司机添加费用明细
     */
    @JsonProperty("driver_add_detail")
    private String driverAddDetail;

    /**
     * 出行人类型
     */
    @JsonProperty("traveler_member_type_name")
    private String travelerMemberTypeName;

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

    /**
     * 开票方
     */
    @JsonProperty("billing_entity")
    private String billingEntity;
}
