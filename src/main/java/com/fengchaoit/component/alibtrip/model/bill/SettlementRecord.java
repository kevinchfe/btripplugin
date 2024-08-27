package com.fengchaoit.component.alibtrip.model.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fengchaoit.component.feishu.datasync.annotation.TableField;
import com.fengchaoit.component.feishu.datasync.model.PrimaryKey;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

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
public class SettlementRecord implements PrimaryKey {
    /**
     * 订单号
     */
    @TableField(fieldName = "订单号", description = "订单号")
    @JsonProperty("order_id")
    private Long orderId;

    /**
     * 入账状态 0、待入账  1、已入账  -1、个人支付不入账
     */
    @TableField(fieldName = "入账状态", description = "入账状态", fieldType = 2)
    @JsonProperty("status")
    private Integer status;

    /**
     * 费用类型
     */
    @TableField(fieldName = "费用类型", description = "费用类型")
    @JsonProperty("fee_type")
    private String feeType;

    /**
     * 结算类型
     */
    @TableField(fieldName = "结算类型", description = "结算类型")
    @JsonProperty("settlement_type")
    private String settlementType;

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
    @TableField(fieldName = "主键 id", description = "主键 id", fieldType = 2)
    private Long primaryId;

    /**
     * 序号
     */
    @TableField(fieldName = "序号", description = "序号", primary = true)
    private String index;

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
    private String capitalDirection;

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
    @TableField(fieldName = "票据类型", description = "票据类型", fieldType = 2)
    @JsonProperty("voucher_type")
    private String voucherType;

    @Override
    public String primary() {
        return String.valueOf(index);
    }
}
