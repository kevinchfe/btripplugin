package com.fengchaoit.component.alibtrip.model.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fengchaoit.component.feishu.datasync.annotation.TableField;
import com.fengchaoit.component.feishu.datasync.model.PrimaryKey;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 记账记录
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午1:55 2024/8/23
 */
@Getter
@Setter
@ToString
public class SettlementRecord implements PrimaryKey, Serializable {
    /**
     * 订单号
     */
    @TableField(fieldName = "订单号", description = "订单号")
    @JsonProperty("order_id")
    private String orderId;

    /**
     * 序号
     */
    @TableField(fieldName = "序号", description = "序号", primary = true)
    private String index;

    /**
     * 入账状态 0、待入账  1、已入账  -1、个人支付不入账
     */
    @TableField(fieldName = "入账状态", description = "入账状态")
    @JsonProperty("status")
    private Integer status;

    /**
     * 费用类型
     */
    @TableField(fieldName = "费用类型", description = "费用类型")
    @JsonProperty("fee_type")
    private Integer feeType;

    /**
     * 结算类型
     */
    @TableField(fieldName = "结算类型", description = "结算类型")
    @JsonProperty("settlement_type")
    private Integer settlementType;

    /**
     * 结算金额
     */
    @TableField(fieldName = "结算金额", description = "结算金额", fieldType = 8)
    @JsonProperty("settlement_fee")
    private BigDecimal settlementFee;

    /**
     * 结算金额--预存赠送部分
     */
    @TableField(fieldName = "结算金额--预存赠送部分", description = "结算金额--预存赠送部分", fieldType = 8)
    @JsonProperty("settlement_grant_fee")
    private BigDecimal settlementGrantFee;

    /**
     * 服务费
     */
    @TableField(fieldName = "服务费", description = "服务费", fieldType = 8)
    @JsonProperty("service_fee")
    private BigDecimal serviceFee;

    /**
     * 主键 id，遇到相同 id，已最新为准（数据会更新）
     */
    @JsonProperty("primary_id")
    @TableField(fieldName = "主键 id", description = "主键 id")
    private String primaryId;

    /**
     * 预订时间
     */
    @TableField(fieldName = "预订时间", description = "预订时间")
    @JsonProperty("book_time")
    private String bookTime;

    /**
     * 预定人姓名
     */
    @TableField(fieldName = "预定人姓名", description = "预定人姓名", order = 3)
    @JsonProperty("booker_name")
    private String bookerName;

    /**
     * 预定人用户 id
     */
    @TableField(fieldName = "预定人用户 id", description = "预定人用户 id")
    @JsonProperty("booker_id")
    private String bookerId;

    /**
     * 预订人工号
     */
    @TableField(fieldName = "预订人工号", description = "预订人工号")
    @JsonProperty("booker_job_no")
    private String bookerJobNo;

    /**
     * 出行人名称
     */
    @TableField(fieldName = "出行人名称", description = "出行人名称")
    @JsonProperty("traveler_name")
    private String travelerName;

    /**
     * 出行人用户 Id
     */
    @TableField(fieldName = "出行人用户 Id", description = "出行人用户 Id")
    @JsonProperty("traveler_id")
    private String travelerId;

    /**
     * 出行人工号
     */
    @TableField(fieldName = "出行人工号", description = "出行人工号")
    @JsonProperty("traveler_job_no")
    private String travelerJobNo;

    /**
     * 联级部门
     */
    @TableField(fieldName = "联级部门", description = "联级部门", order = 4)
    @JsonProperty("cascade_department")
    private String cascadeDepartment;

    /**
     * 末级部门
     */
    @TableField(fieldName = "末级部门", description = "末级部门")
    @JsonProperty("department")
    private String department;

    /**
     * 部门 id
     */
    @TableField(fieldName = "部门 id", description = "部门 id")
    @JsonProperty("department_id")
    private String departmentId;

    /**
     * 成本中心名称
     */
    @TableField(fieldName = "成本中心名称", description = "成本中心名称")
    @JsonProperty("cost_center")
    private String costCenter;

    /**
     * 成本中心编号
     */
    @TableField(fieldName = "成本中心编号", description = "成本中心编号")
    @JsonProperty("cost_center_number")
    private String costCenterNumber;

    /**
     * 项目名称
     */
    @TableField(fieldName = "项目名称", description = "项目名称")
    @JsonProperty("project_name")
    private String projectName;

    /**
     * 项目编码
     */
    @TableField(fieldName = "项目编码", description = "项目编码")
    @JsonProperty("project_code")
    private String projectCode;

    /**
     * 发票抬头
     */
    @TableField(fieldName = "发票抬头", description = "发票抬头")
    @JsonProperty("invoice_title")
    private String invoiceTitle;

    /**
     * 结算时间
     */
    @TableField(fieldName = "结算时间", description = "结算时间")
    @JsonProperty("settlement_time")
    private String settlementTime;


    /**
     * 资金方向  1、支出  2、收入
     */
    @TableField(fieldName = "资金方向", description = "资金方向")
    @JsonProperty("capital_direction")
    private Integer capitalDirection;

    /**
     * 支付流水号
     */
    @TableField(fieldName = "支付流水号", description = "支付流水号")
    @JsonProperty("alipay_trade_no")
    private String alipayTradeNo;

    /**
     * 审批单号
     */
    @TableField(fieldName = "审批单号", description = "审批单号")
    @JsonProperty("apply_id")
    private String applyId;

    /**
     * 超标审批单号
     */
    @TableField(fieldName = "超标审批单号", description = "超标审批单号")
    @JsonProperty("over_apply_id")
    private String overApplyId;

    /**
     * 备注
     */
    @TableField(fieldName = "备注", description = "备注")
    @JsonProperty("remark")
    private String remark;

    /**
     * 税率
     */
    @TableField(fieldName = "税率", description = "税率")
    @JsonProperty("tax_rate")
    private String taxRate;

    /**
     * 第三方行程 id
     */
    @TableField(fieldName = "第三方行程 id", description = "第三方行程 id")
    @JsonProperty("third_itinerary_id")
    private String thirdItineraryId;

    /**
     * 入账时间 注意 时间格式 yyyy-MM-dd'T'HH:mm'Z'
     */
    @TableField(fieldName = "入账时间", description = "入账时间")
    @JsonProperty("bill_record_time")
    private String billRecordTime;

    /**
     * 票据类型
     */
    @TableField(fieldName = "票据类型", description = "票据类型")
    @JsonProperty("voucher_type")
    private Integer voucherType;

    @Override
    public String primary() {
        return String.valueOf(index);
    }

    /**
     * 描述接口
     */
    public interface Describable {
        String getDescription();
    }

    /**
     * 入账状态枚举
     */
    public enum StatusEnum implements Describable {
        NOT_POSTED(-1, "个人支付不入账"),
        PENDING(0, "待入账"),
        SUCCESS(1, "入账成功");

        private final int code;
        private final String description;
        private static final Map<Integer, StatusEnum> CODE_MAP = new HashMap<>();

        static {
            for (StatusEnum statusEnum : StatusEnum.values()) {
                CODE_MAP.put(statusEnum.code, statusEnum);
            }
        }

        StatusEnum(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }

        public static StatusEnum fromCode(int code) {
            return CODE_MAP.get(code);
        }
    }

    /**
     * 费用类型枚举
     */
    public enum FeeTypeEnum implements Describable {
        COMPENSATION(1201, "赔付"),
        REVERSAL(2001, "冲正"),

        // 国内机票类目费用类型
        TICKET_BOOKING(10101, "机票预订"),
        TICKET_REBOOKING_FEE(10202, "机票改签手续费"),
        TICKET_REBOOKING_DIFFERENCE(10203, "机票改签差价"),
        TICKET_REFUND(10301, "机票退款"),
        TICKET_REBOOKING_REFUND(10302, "机票改签退款"),
        TICKET_SUPPLEMENTARY_REFUND(10303, "机票补退"),
        INSURANCE_PURCHASE(10401, "机票保险-航意险购买"),
        INSURANCE_REFUND(10501, "机票保险-航意险退保"),
        TICKET_BOOKING_SERVICE_FEE(11001, "机票预订服务费"),
        TICKET_REBOOKING_SERVICE_FEE(11002, "机票改签服务费"),
        TICKET_SERVICE_FEE_REFUND(10304, "机票票据服务费退款"),
        TICKET_SERVICE_FEE(11003, "机票票据服务费"),
        TICKET_TRUST_AGREEMENT_FEE(11004, "机票托管协议服务费"),
        TICKET_REBOOKING_TRUST_AGREEMENT_FEE(11005, "机票改签托管协议服务费"),

        // 国际及港澳台机票相关
        INTERNATIONAL_TICKET_BOOKING(110101, "国际城市以及中国香港、中国澳门、中国台湾机票预订"),
        INTERNATIONAL_TICKET_REBOOKING(110201, "国际城市以及中国香港、中国澳门、中国台湾机票改签"),
        INTERNATIONAL_TICKET_REFUND(110301, "国际城市以及中国香港、中国澳门、中国台湾机票退款"),
        INTERNATIONAL_TICKET_REBOOKING_REFUND(110302, "国际城市以及中国香港、中国澳门、中国台湾机票改签退款"),
        INTERNATIONAL_TICKET_SUPPLEMENTARY_REFUND(110303, "国际城市以及中国香港、中国澳门、中国台湾机票补退"),
        INTERNATIONAL_TICKET_BOOKING_SERVICE_FEE(111001, "国际城市以及中国香港、中国澳门、中国台湾机票预订服务费"),
        INTERNATIONAL_TICKET_REBOOKING_SERVICE_FEE(111002, "国际城市以及中国香港、中国澳门、中国台湾机票改签服务费"),

        // 国内酒店类目费用类型
        HOTEL_BOOKING(20101, "酒店预订"),
        HOTEL_REFUND(20103, "酒店退款"),
        HOTEL_BOOKING_SERVICE_FEE(20111, "酒店预订服务费"),
        HOTEL_TRUST_SERVICE_FEE(20112, "酒店托管服务费"),
        HOTEL_BOOKING_SUPPLEMENTARY(20102, "酒店预订补扣"),
        HOTEL_BOOKING_SERVICE_FEE_SUPPLEMENTARY(20113, "酒店预订服务费补扣"),
        HOTEL_TRUST_SERVICE_FEE_SUPPLEMENTARY(20114, "酒店协议托管服务费补扣"),
        HOTEL_INSURANCE_PURCHASE(20301, "酒店保险购买"),
        HOTEL_INSURANCE_REFUND(20401, "酒店保险退保"),

        // 国际酒店费用类型
        INTERNATIONAL_HOTEL_BOOKING(120101, "国际城市以及中国香港、中国澳门、中国台湾酒店预订"),
        INTERNATIONAL_HOTEL_REFUND(120103, "国际城市以及中国香港、中国澳门、中国台湾酒店退款"),
        INTERNATIONAL_HOTEL_BOOKING_SERVICE_FEE(120111, "国际城市以及中国香港、中国澳门、中国台湾酒店预订服务费"),
        INTERNATIONAL_HOTEL_TRUST_SERVICE_FEE(120112, "国际城市以及中国香港、中国澳门、中国台湾酒店托管服务费"),

        // 火车票类目费用类型
        TRAIN_TICKET_BOOKING(6001, "火车票预订"),
        TRAIN_TICKET_RESCHEDULE_DIFFERENCE(6003, "火车票改签差价"),
        TRAIN_TICKET_RESCHEDULE_FEE(6004, "火车票改签手续费"),
        TRAIN_TICKET_REFUND(6005, "火车票退票"),
        TRAIN_TICKET_BOOKING_SERVICE_FEE(6007, "火车票预订服务费"),
        TRAIN_TICKET_RESCHEDULE_SERVICE_FEE(6008, "火车票改签服务费"),
        TRAIN_TICKET_BOOKING_REFUND(6009, "火车票预订退款"),
        TRAIN_TICKET_RESCHEDULE_REFUND(6010, "火车票改签退款"),
        TRAIN_TICKET_ACCELERATION_PACKAGE(6101, "火车票抢票加速包"),
        TRAIN_TICKET_INVOICE_SERVICE_FEE(6011, "火车票票据服务费"),
        TRAIN_TICKET_TECHNICAL_SERVICE_FEE(6012, "火车票技术服务费"),
        TRAIN_TICKET_INVOICE_SERVICE_FEE_REFUND(6013, "火车票票据服务费退款"),
        TRAIN_TICKET_OFFLINE_REFUND(6014, "火车票线下退改退款"),

        // 用车类目费用类型
        CAR_SERVICE_BOOKING(40101, "用车预订"),
        CAR_SERVICE_REFUND(40103, "用车退款"),
        CAR_SERVICE_CANCELLATION_FEE(40107, "用车取消订单收取费用"),
        CAR_SERVICE_BOOKING_SERVICE_FEE(40111, "用车预订服务费"),

        // 用餐类目费用类型
        DINING_SERVICE_BOOKING(70101, "用餐预订"),
        DINING_SERVICE_REFUND(70103, "用餐退款"),
        DINING_SERVICE_BOOKING_SERVICE_FEE(71001, "用餐预订服务费"),
        DINING_SERVICE_BOOKING_SERVICE_FEE_REFUND(71003, "用餐预订服务费退款"),

        // 福豆类目费用类型
        FUDOU_GRANT(100101, "福豆发放");

        private final int code;
        private final String description;
        private static final Map<Integer, FeeTypeEnum> CODE_MAP = new HashMap<>();

        static {
            for (FeeTypeEnum feeTypeEnum : FeeTypeEnum.values()) {
                CODE_MAP.put(feeTypeEnum.code, feeTypeEnum);
            }
        }

        FeeTypeEnum(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }

        public static FeeTypeEnum fromCode(int code) {
            return CODE_MAP.get(code);
        }
    }

    /**
     * 结算类型枚举
     */
    public enum SettlementTypeEnum implements Describable {
        PERSONAL_CASH(1, "个人现付"),
        CORPORATE_CASH(2, "企业现付"),
        CORPORATE_MONTHLY(4, "企业月结"),
        CORPORATE_PREPAID(8, "企业预存"),
        FUDOU_PAYMENT(64, "福豆支付"),
        FUDOU_REFUND_ACCOUNT(16, "福豆退回账户"),
        FUDOU_GIFT_ACCOUNT(32, "福豆赠送账户");

        private final int code;
        private final String description;
        private static final Map<Integer, SettlementTypeEnum> CODE_MAP = new HashMap<>();

        static {
            for (SettlementTypeEnum settlementTypeEnum : SettlementTypeEnum.values()) {
                CODE_MAP.put(settlementTypeEnum.code, settlementTypeEnum);
            }
        }

        SettlementTypeEnum(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }

        public static SettlementTypeEnum fromCode(int code) {
            return CODE_MAP.get(code);
        }
    }

    /**
     * 票据类型枚举
     */
    public enum VoucherTypeEnum implements Describable {
        DIGITAL_GENERAL_INVOICE(11, "数字化电子普票"),
        DIGITAL_SPECIAL_INVOICE(12, "数字化电子专票"),
        DIGITAL_GENERAL_INSURANCE_INVOICE(13, "数字化电子普票（保险）"),
        VAT_INVOICE(1, "增值税发票"),
        AIR_TICKET_ITINERARY(2, "机票行程单"),
        TRAIN_TICKET_RECEIPT(5, "火车票凭证"),
        FIXED_AMOUNT_INVOICE(6, "定额发票"),
        NO_INVOICE_REQUIRED(99, "无需提供票据"),
        OFFLINE_INVOICE(7, "线下制票");

        private final int code;
        private final String description;
        private static final Map<Integer, VoucherTypeEnum> CODE_MAP = new HashMap<>();

        static {
            for (VoucherTypeEnum voucherTypeEnum : VoucherTypeEnum.values()) {
                CODE_MAP.put(voucherTypeEnum.code, voucherTypeEnum);
            }
        }

        VoucherTypeEnum(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }

        public static VoucherTypeEnum fromCode(int code) {
            return CODE_MAP.get(code);
        }
    }

    /**
     * 资金方向枚举
     */
    public enum CapitalDirectionEnum implements Describable {
        EXPENSE(1, "支出"),
        INCOME(2, "收入");

        private final int code;
        private final String description;
        private static final Map<Integer, CapitalDirectionEnum> CODE_MAP = new HashMap<>();

        static {
            for (CapitalDirectionEnum capitalDirectionEnum : CapitalDirectionEnum.values()) {
                CODE_MAP.put(capitalDirectionEnum.code, capitalDirectionEnum);
            }
        }

        CapitalDirectionEnum(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }

        public static CapitalDirectionEnum fromCode(int code) {
            return CODE_MAP.get(code);
        }
    }


    /**
     * 枚举工具类
     */
    public static class EnumUtils {
        public static <T extends Enum<T> & Describable> String getDescription(Class<T> enumClass, int code) {
            for (T enumConstant : enumClass.getEnumConstants()) {
                if (enumConstant instanceof StatusEnum && ((StatusEnum) enumConstant).code == code) {
                    return enumConstant.getDescription();
                } else if (enumConstant instanceof FeeTypeEnum && ((FeeTypeEnum) enumConstant).code == code) {
                    return enumConstant.getDescription();
                } else if (enumConstant instanceof SettlementTypeEnum && ((SettlementTypeEnum) enumConstant).code == code) {
                    return enumConstant.getDescription();
                } else if (enumConstant instanceof VoucherTypeEnum && ((VoucherTypeEnum) enumConstant).code == code) {
                    return enumConstant.getDescription();
                } else if (enumConstant instanceof CapitalDirectionEnum && ((CapitalDirectionEnum) enumConstant).code == code) {
                    return enumConstant.getDescription();
                }
            }
            return "未知";
        }
    }


}
