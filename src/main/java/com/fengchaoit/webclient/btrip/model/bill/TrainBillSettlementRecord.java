package com.fengchaoit.webclient.btrip.model.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fengchaoit.component.feishu.datasync.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 火车票记账记录
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午4:11 2024/8/23
 */
@Getter
@Setter
@ToString(callSuper = true)
public class TrainBillSettlementRecord extends SettlementRecord {
    /**
     * 发车日期
     */
    @JsonProperty("dept_date")
    private LocalDate deptDate;

    /**
     * 发车时间
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
     * 发车站
     */
    @JsonProperty("dept_station")
    private String deptStation;

    /**
     * 到达站
     */
    @JsonProperty("arr_station")
    private String arrStation;

    /**
     * 运行时长
     */
    @JsonProperty("run_time")
    private String runTime;

    /**
     * 车次
     */
    @JsonProperty("trainNo")
    private String trainNo;

    /**
     * 车次类型
     */
    @JsonProperty("train_type")
    private String trainType;

    /**
     * 坐席
     */
    @JsonProperty("seat_type")
    private String seatType;

    /**
     * 座位号
     */
    @JsonProperty("seat_no")
    private String seatNo;

    /**
     * 票面票号
     */
    @JsonProperty("ticket_no")
    private String ticketNo;

    /**
     * 订单金额
     */
    @JsonProperty("order_price")
    private BigDecimal orderPrice;

    /**
     * 票价
     */
    @JsonProperty("ticket_price")
    private BigDecimal ticketPrice;

    /**
     * 改签手续费
     */
    @JsonProperty("change_fee")
    private BigDecimal changeFee;

    /**
     * 退票手续费
     */
    @JsonProperty("refund_fee")
    private BigDecimal refundFee;

    /**
     * 优惠券
     */
    @JsonProperty("coupon")
    private BigDecimal coupon;

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
     * 车厢号
     */
    @JsonProperty("coach_no")
    private String coachNo;

    /**
     * 取票号
     */
    @JsonProperty("short_ticket_no")
    private String shortTicketNo;

    /**
     * 行程类型
     */
    @JsonProperty("is_transfer_order")
    private String transferOrder;

    /**
     * 预定类型
     */
    @JsonProperty("reserve_mode")
    private String reserveMode;

    /**
     * 企业支付金额
     */
    @JsonProperty("ticket_corp_pay_price")
    private BigDecimal ticketCorpPayPrice;

    /**
     * 个人支付金额
     */
    @JsonProperty("ticket_person_pay_price")
    private BigDecimal ticketPersonPayPrice;

    /**
     * 出发城市
     */
    @JsonProperty("dep_city_name")
    private String depCityName;

    /**
     * 到达城市
     */
    @JsonProperty("arr_city_name")
    private String arrCityName;

    /**
     * 改签事由
     */
    @JsonProperty("change_result")
    private String changeResult;

    /**
     * 退订原因
     */
    @JsonProperty("refund_reason")
    private String refundReason;

    /**
     * 出差事由
     */
    @JsonProperty("business_trip_result")
    private String businessTripResult;

    /**
     * 超标原因
     */
    @JsonProperty("exceed_reason")
    private String exceedReason;

    /**
     * 出行人类型
     */
    @JsonProperty("traveler_member_type_name")
    private String travelerMemberTypeName;

    /**
     * 票面票价
     */
    @JsonProperty("print_ticket_price")
    private BigDecimal printTicketPrice;

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
     * 抢票加速包
     */
    @JsonProperty("speed_package_fee")
    private BigDecimal speedPackageFee;

    /**
     * 改签费用归属
     */
    @JsonProperty("change_affiliate_no")
    private String changeAffiliateNo;

    /**
     * 退票费用归属
     */
    @JsonProperty("refund_affiliate_no")
    private String refundAffiliateNo;

    /**
     * 改签审批单号
     */
    @JsonProperty("change_apply_id")
    private String changeApplyId;

    /**
     * 退款审批单号
     */
    @JsonProperty("refund_apply_id")
    private String refundApplyId;
}
