package com.fengchaoit.component.alibtrip.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fengchaoit.component.feishu.datasync.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

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
public class Order {
    /**
     * 订单编号
     */
    @JsonProperty("order_id")
    @TableField(fieldName = "订单编号", description = "订单编号", fieldType = 2)
    private String orderId;

    /**
     * 订单创建时间
     */
    @JsonProperty("gmt_create")
    @TableField(fieldName = "订单创建时间", description = "订单创建时间")
    private Long gmtCreate;

    /**
     * 订单修改时间
     */
    @JsonProperty("gmt_modify")
    @TableField(fieldName = "订单修改时间", description = "订单修改时间")
    private Long gmtModify;

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

}
