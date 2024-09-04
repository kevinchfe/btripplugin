package com.fengchaoit.component.alibtrip.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fengchaoit.component.feishu.datasync.annotation.TableField;
import com.fengchaoit.component.feishu.datasync.model.PrimaryKey;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 商旅订单
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午4:23 2024/8/26
 */
@Getter
@Setter
@ToString
public class Order implements PrimaryKey, Serializable {
    /**
     * 订单编号
     */
    @JsonProperty("id")
    @TableField(fieldName = "订单编号", description = "订单编号", primary = true)
    private String id;

    /**
     * 订单创建时间
     */
    @JsonProperty("gmt_create")
    @TableField(fieldName = "订单创建时间", description = "订单创建时间")
    private String gmtCreate;

    /**
     * 订单修改时间
     */
    @JsonProperty("gmt_modify")
    @TableField(fieldName = "订单修改时间", description = "订单修改时间")
    private String gmtModify;

    /**
     * 商旅企业 id
     */
    @JsonProperty("corp_id")
    @TableField(fieldName = "商旅企业 id", description = "商旅企业 id")
    private String corpId;

    /**
     * 企业名称
     */
    @JsonProperty("corp_name")
    @TableField(fieldName = "企业名称", description = "企业名称")
    private String corpName;

    /**
     * 用户id
     */
    @JsonProperty("user_id")
    @TableField(fieldName = "用户id", description = "用户id")
    private String userId;

    /**
     * 出行人姓名
     */
    @JsonProperty("user_name")
    @TableField(fieldName = "出行人姓名", description = "出行人姓名")
    private String userName;

    /**
     * 商旅审批单 id
     */
    @JsonProperty(value = "apply_id")
    @TableField(fieldName = "商旅审批单 id", description = "商旅审批单 id")
    private String applyId;

    /**
     * 第三方行程 id
     */
    @JsonProperty("thirdpart_itinerary_id")
    @TableField(fieldName = "第三方行程 id", description = "第三方行程 id")
    private String thirdpartItineraryId;

    /**
     * 项目id
     */
    @JsonProperty("project_id")
    @TableField(fieldName = "项目id", description = "项目id")
    private String projectId;

    /**
     * 项目code
     */
    @JsonProperty("project_code")
    @TableField(fieldName = "项目code", description = "项目code")
    private String projectCode;

    /**
     * 项目名称
     */
    @JsonProperty("project_title")
    @TableField(fieldName = "项目名称", description = "项目名称")
    private String projectTitle;


    /**
     * 第三方申请单 id
     */
    @JsonProperty("thirdpart_apply_id")
    @TableField(fieldName = "第三方申请单 id", description = "第三方申请单 id")
    private String thirdpartApplyId;

    @Override
    public String primary() {
        return this.id.toString();
    }


    /**
     * 描述接口
     */
    public interface Describable {
        String getDescription();
    }

    /**
     * 机票订单行程类型
     */
    public enum FlightTripTypeEnum implements Describable {
        ONE_WAY(0, "单程"),
        ROUND_TRIP(1, "往返"),
        TRANSIT(2, "中转");

        private final int code;
        private final String description;
        private static final Map<Integer, FlightTripTypeEnum> CODE_MAP = new HashMap<>();

        static {
            for (FlightTripTypeEnum tripTypeEnum : FlightTripTypeEnum.values()) {
                CODE_MAP.put(tripTypeEnum.code, tripTypeEnum);
            }
        }

        FlightTripTypeEnum(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    /**
     * 机票订单状态
     */
    public enum FlightStatusEnum implements Describable {
        WAIT_PAY(0, "待支付"),
        TICKETING(1, "出票中"),
        CLOSED(2, "已关闭"),
        CHANGE_TICKET(3, "有改签单"),
        REFUND_TICKET(4, "有退票"),
        TICKET_SUCCESS(5, "出票成功"),
        REFUND_APPLY(6, "退票申请中"),
        CHANGE_APPLY(7, "改签申请中");

        private final int code;
        private final String description;
        private static final Map<Integer, FlightStatusEnum> CODE_MAP = new HashMap<>();

        static {
            for (FlightStatusEnum statusEnum : FlightStatusEnum.values()) {
                CODE_MAP.put(statusEnum.code, statusEnum);
            }
        }

        FlightStatusEnum(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    /**
     * 火车订单状态
     */
    public enum TrainStatusEnum implements Describable {
        WAIT_PAY(0, "待支付"),
        TICKETING(1, "出票中"),
        CLOSED(2, "已关闭"),
        CHANGE_SUCCESS(3, "改签成功"),
        REFUND_ORDER(4, "有退票单"),
        TICKET_SUCCESS(5, "出票完成"),
        REFUND_APPLY(6, "退票申请中"),
        CHANGE_APPLY(7, "改签申请中"),
        TICKET_DELIVERED(8, "已出票，已发货"),
        TICKET_FAIL(9, "出票失败"),
        CHANGE_FAIL(10, "改签失败"),
        REFUND_FAIL(11, "退票失败"),
        OFFLINE_CHANGE(12, "有线下改签"),
        OFFLINE_REFUND(13, "有线下退票"),
        OFFLINE_REFUND_CHANGE(14, "有线下退改");

        private final int code;
        private final String description;
        private static final Map<Integer, TrainStatusEnum> CODE_MAP = new HashMap<>();

        static {
            for (TrainStatusEnum statusEnum : TrainStatusEnum.values()) {
                CODE_MAP.put(statusEnum.code, statusEnum);
            }
        }

        TrainStatusEnum(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    /**
     * 酒店开票支持类型
     */
    public enum HotelSupportVatInvoiceTypeEnum implements Describable {
        VAT_INVOICE(11, "仅支持增值税普通发票"),
        VAT_INVOICE_SPECIAL(12, "支持增值税专用发票和增值税普通发票");

        private final int code;
        private final String description;
        private static final Map<Integer, HotelSupportVatInvoiceTypeEnum> CODE_MAP = new HashMap<>();

        static {
            for (HotelSupportVatInvoiceTypeEnum hotelSupportVatInvoiceTypeEnum : HotelSupportVatInvoiceTypeEnum.values()) {
                CODE_MAP.put(hotelSupportVatInvoiceTypeEnum.code, hotelSupportVatInvoiceTypeEnum);
            }
        }

        HotelSupportVatInvoiceTypeEnum(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    /**
     * 酒店订单状态
     */
    public enum HotelStatusEnum implements Describable {
        WAIT_CONFIRM(1, "待确认"),
        WAIT_PAY(2, "待付款"),
        BOOK_SUCCESS(3, "预订成功"),
        REFUND_WAIT_CONFIRM(4, "退款待确认"),
        CANCELLED(5, "已取消(主动退款)"),
        CLOSED(6, "已关闭"),
        CANCELLED_NO_ROOM(9, "已取消(确认无房)"),
        REFUND_APPLY_FAIL(10, "退款申请未通过"),
        SUCCESS(11, "交易成功"),
        INVISIBLE(-1, "不可见");

        private final int code;
        private final String description;
        private static final Map<Integer, HotelStatusEnum> CODE_MAP = new HashMap<>();

        static {
            for (HotelStatusEnum orderStatusEnum : HotelStatusEnum.values()) {
                CODE_MAP.put(orderStatusEnum.code, orderStatusEnum);
            }
        }

        HotelStatusEnum(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    /**
     * 打车订单状态
     */
    public enum CarStatusEnum implements Describable {
        INIT(0, "初始化状态"),
        TIMEOUT(1, "已超时"),
        DISPATCH_SUCCESS(2, "派单成功"),
        DISPATCH_FAIL(3, "派单失败"),
        REFUNDED(4, "已退款"),
        PAID(5, "已支付"),
        CANCELLED(6, "已取消"),
        UNKNOWN(null, "未知");

        private final Integer code;
        private final String description;
        private static final Map<Integer, CarStatusEnum> CODE_MAP = new HashMap<>();

        static {
            for (CarStatusEnum statusEnum : CarStatusEnum.values()) {
                CODE_MAP.put(statusEnum.code, statusEnum);
            }
        }

        CarStatusEnum(Integer code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    /**
     * 打车类型级别
     */
    public enum CarLevelEnum implements Describable {
        ECONOMY(1, "经济型"),
        COMFORT(2, "舒适型"),
        LUXURY(3, "豪华型"),
        TEST(12524077,"测试"),
        UNKNOWN(null, "未知");

        private final int code;
        private final String description;
        private static final Map<Integer, CarLevelEnum> CODE_MAP = new HashMap<>();

        static {
            for (CarLevelEnum levelEnum : CarLevelEnum.values()) {
                CODE_MAP.put(levelEnum.code, levelEnum);
            }
        }

        CarLevelEnum(Integer code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    /**
     * 打车服务类型
     */
    public enum CarServiceTypeEnum implements Describable {
        TAXI(1, "出租车"),
        PREMIUM(2, "专车"),
        EXPRESS(3, "快车"),
        UNKNOWN(null, "未知");

        private final Integer code;
        private final String description;
        private static final Map<Integer, CarServiceTypeEnum> CODE_MAP = new HashMap<>();

        static {
            for (CarServiceTypeEnum serviceTypeEnum : CarServiceTypeEnum.values()) {
                CODE_MAP.put(serviceTypeEnum.code, serviceTypeEnum);
            }
        }

        CarServiceTypeEnum(Integer code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    /**
     * 用车原因
     */
    public enum CarReasonEnum implements Describable {
        TRAFFIC("TRAFFIC", "市内交通"),
        OTHER("OTHER", "其他"),
        TRAVEL("TRAVEL", "差旅"),
        WORK("WORK", "加班"),
        UNKNOWN(null, "未知");

        private final String code;
        private final String description;
        private static final Map<String, CarReasonEnum> CODE_MAP = new HashMap<>();

        static {
            for (CarReasonEnum reasonEnum : CarReasonEnum.values()) {
                CODE_MAP.put(reasonEnum.code, reasonEnum);
            }
        }

        CarReasonEnum(String code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }

        public static CarReasonEnum fromCode(String code) {
            return CODE_MAP.get(code);
        }
    }

    /**
     * 打车用户确认状态
     */
    public enum CarUserConfirmEnum implements Describable {
        UNCONFIRMED(0, "未确认"),
        CONFIRMED(1, "已确认"),
        DISPUTED(2, "有异议"),
        SYSTEM_CHECK_FAILED(3, "系统检查不合理");

        private final int code;
        private final String description;
        private static final Map<Integer, CarUserConfirmEnum> CODE_MAP = new HashMap<>();

        static {
            for (CarUserConfirmEnum statusEnum : CarUserConfirmEnum.values()) {
                CODE_MAP.put(statusEnum.code, statusEnum);
            }
        }

        CarUserConfirmEnum(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    /**
     * 打车服务商
     */
    public enum CarProviderEnum implements Describable {
        DIDI(2, "滴滴"),
        CAOCAO(3, "曹操"),
        SHOUQI(4, "首汽"),
        YANGGUANG(5, "阳光");

        private final int code;
        private final String description;
        private static final Map<Integer, CarProviderEnum> CODE_MAP = new HashMap<>();

        static {
            for (CarProviderEnum supplierEnum : CarProviderEnum.values()) {
                CODE_MAP.put(supplierEnum.code, supplierEnum);
            }
        }

        CarProviderEnum(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }

    /**
     * 枚举工具类
     */
    public static class EnumUtils {
        public static <T extends Enum<T> & Describable> String getDescription(Class<T> enumClass, int code) {
            for (T enumConstant : enumClass.getEnumConstants()) {
                if (enumConstant instanceof TrainStatusEnum && ((TrainStatusEnum) enumConstant).code == code) {
                    return enumConstant.getDescription();
                } else if (enumConstant instanceof FlightStatusEnum && ((FlightStatusEnum) enumConstant).code == code) {
                    return enumConstant.getDescription();
                } else if (enumConstant instanceof FlightTripTypeEnum && ((FlightTripTypeEnum) enumConstant).code == code) {
                    return enumConstant.getDescription();
                } else if (enumConstant instanceof HotelSupportVatInvoiceTypeEnum && ((HotelSupportVatInvoiceTypeEnum) enumConstant).code == code) {
                    return enumConstant.getDescription();
                } else if (enumConstant instanceof HotelStatusEnum && ((HotelStatusEnum) enumConstant).code == code) {
                    return enumConstant.getDescription();
                } else if (enumConstant instanceof CarStatusEnum && ((CarStatusEnum) enumConstant).code == code) {
                    return enumConstant.getDescription();
                } else if (enumConstant instanceof CarLevelEnum && ((CarLevelEnum) enumConstant).code == code) {
                    return enumConstant.getDescription();
                } else if (enumConstant instanceof CarServiceTypeEnum && ((CarServiceTypeEnum) enumConstant).code == code) {
                    return enumConstant.getDescription();
                } else if (enumConstant instanceof CarUserConfirmEnum && ((CarUserConfirmEnum) enumConstant).code == code) {
                    return enumConstant.getDescription();
                } else if (enumConstant instanceof CarProviderEnum && ((CarProviderEnum) enumConstant).code == code) {
                    return enumConstant.getDescription();
                }
            }
            return "未知";
        }
    }

    /**
     * 字符串枚举工具类
     */
    public static class StringEnumUtils {
        public static <T extends Enum<T> & Describable> String getDescription(Class<T> enumClass, String code) {
            for (T enumConstant : enumClass.getEnumConstants()) {
                if (enumConstant instanceof CarReasonEnum) {
                    return enumConstant.getDescription();
                }
            }
            return "未知";
        }
    }

}
