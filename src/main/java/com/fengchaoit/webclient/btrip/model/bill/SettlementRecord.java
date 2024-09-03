package com.fengchaoit.webclient.btrip.model.bill;

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
    @JsonProperty("order_id")
    private String orderId;

    /**
     * 序号
     */
    private String index;

    /**
     * 入账状态 0、待入账  1、已入账  -1、个人支付不入账
     */
    @JsonProperty("status")
    private Integer status;

    /**
     * 费用类型
     */
    @JsonProperty("fee_type")
    private Integer feeType;

    /**
     * 结算类型
     */
    @JsonProperty("settlement_type")
    private Integer settlementType;

    /**
     * 结算金额
     */
    @JsonProperty("settlement_fee")
    private BigDecimal settlementFee;

    /**
     * 结算金额--预存赠送部分
     */
    @JsonProperty("settlement_grant_fee")
    private BigDecimal settlementGrantFee;

    /**
     * 服务费
     */
    @JsonProperty("service_fee")
    private BigDecimal serviceFee;

    /**
     * 主键 id，遇到相同 id，已最新为准（数据会更新）
     */
    @JsonProperty("primary_id")
    private String primaryId;

    /**
     * 预订时间
     */
    @JsonProperty("book_time")
    private String bookTime;

    /**
     * 预定人姓名
     */
    @JsonProperty("booker_name")
    private String bookerName;

    /**
     * 预定人用户 id
     */
    @JsonProperty("booker_id")
    private String bookerId;

    /**
     * 预订人工号
     */
    @JsonProperty("booker_job_no")
    private String bookerJobNo;

    /**
     * 出行人名称
     */
    @JsonProperty("traveler_name")
    private String travelerName;

    /**
     * 出行人用户 Id
     */
    @JsonProperty("traveler_id")
    private String travelerId;

    /**
     * 出行人工号
     */
    @JsonProperty("traveler_job_no")
    private String travelerJobNo;

    /**
     * 联级部门
     */
    @JsonProperty("cascade_department")
    private String cascadeDepartment;

    /**
     * 末级部门
     */
    @JsonProperty("department")
    private String department;

    /**
     * 部门 id
     */
    @JsonProperty("department_id")
    private String departmentId;

    /**
     * 成本中心名称
     */
    @JsonProperty("cost_center")
    private String costCenter;

    /**
     * 成本中心编号
     */
    @JsonProperty("cost_center_number")
    private String costCenterNumber;

    /**
     * 项目名称
     */
    @JsonProperty("project_name")
    private String projectName;

    /**
     * 项目编码
     */
    @JsonProperty("project_code")
    private String projectCode;

    /**
     * 发票抬头
     */
    @JsonProperty("invoice_title")
    private String invoiceTitle;

    /**
     * 结算时间
     */
    @JsonProperty("settlement_time")
    private String settlementTime;

    /**
     * 资金方向  1、支出  2、收入
     */
    @JsonProperty("capital_direction")
    private Integer capitalDirection;

    /**
     * 支付流水号
     */
    @JsonProperty("alipay_trade_no")
    private String alipayTradeNo;

    /**
     * 审批单号
     */
    @JsonProperty("apply_id")
    private String applyId;

    /**
     * 超标审批单号
     */
    @JsonProperty("over_apply_id")
    private String overApplyId;

    /**
     * 备注
     */
    @JsonProperty("remark")
    private String remark;

    /**
     * 税率
     */
    @JsonProperty("tax_rate")
    private String taxRate;

    /**
     * 第三方行程 id
     */
    @JsonProperty("third_itinerary_id")
    private String thirdItineraryId;

    /**
     * 入账时间 注意 时间格式 yyyy-MM-dd'T'HH:mm'Z'
     */
    @JsonProperty("bill_record_time")
    private String billRecordTime;

    /**
     * 票据类型
     */
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
}
