package com.fengchaoit.webclient.btrip.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
public class CarOrder extends Order {

    @JsonProperty("gmt_create")
    private LocalDateTime gmtCreate;
    @JsonProperty("gmt_modified")
    private LocalDateTime gmtModified;
    @JsonProperty("passenger_name")
    private String passengerName;
    @JsonProperty("corp_id")
    private String corpId;
    @JsonProperty("corp_name")
    private String corpName;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("dept_id")
    private Integer deptId;
    @JsonProperty("dept_name")
    private String deptName;
    @JsonProperty("apply_id")
    private Integer applyId;
    @JsonProperty("apply_show_id")
    private String applyShowId;
    @JsonProperty("real_from_address")
    private String realFromAddress;
    @JsonProperty("real_to_address")
    private String realToAddress;
    @JsonProperty("real_from_city_name")
    private String realFromCityName;
    @JsonProperty("real_to_city_name")
    private String realToCityName;
    @JsonProperty("from_address")
    private String fromAddress;
    @JsonProperty("to_address")
    private String toAddress;
    @JsonProperty("from_city_name")
    private String fromCityName;
    @JsonProperty("to_city_name")
    private String toCityName;
    @JsonProperty("memo")
    private String memo;
    @JsonProperty("order_status")
    private Integer orderStatus;
    @JsonProperty("car_level")
    private Integer carLevel;
    @JsonProperty("car_info")
    private String carInfo;
    @JsonProperty("estimate_price")
    private BigDecimal estimatePrice;
    @JsonProperty("publish_time")
    private String publishTime;
    @JsonProperty("taken_time")
    private String takenTime;
    @JsonProperty("driver_confirm_time")
    private String driverConfirmTime;
    @JsonProperty("cancel_time")
    private String cancelTime;
    @JsonProperty("pay_time")
    private String payTime;
    @JsonProperty("travel_distance")
    private BigDecimal travelDistance;
    @JsonProperty("service_type")
    private Integer serviceType;
    @JsonProperty("business_category")
    private String businessCategory;
    @JsonProperty("cost_center_id")
    private Integer costCenterId;
    @JsonProperty("cost_center_number")
    private String costCenterNumber;
    @JsonProperty("cost_center_name")
    private String costCenterName;
    @JsonProperty("invoice_id")
    private Integer invoiceId;
    @JsonProperty("invoice_title")
    private String invoiceTitle;
    @JsonProperty("project_id")
    private Integer projectId;
    @JsonProperty("project_code")
    private String projectCode;
    @JsonProperty("project_title")
    private String projectTitle;
    @JsonProperty("price_info_list")
    private List<PriceInfoListDTO> priceInfoList;
    @JsonProperty("thirdpart_itinerary_id")
    private String thirdpartItineraryId;
    @JsonProperty("user_affiliate_list")
    private List<UserAffiliateListDTO> userAffiliateList;
    @JsonProperty("user_confirm")
    private Integer userConfirm;
    @JsonProperty("provider")
    private Integer provider;
    @JsonProperty("thirdpart_apply_id")
    private String thirdpartApplyId;
    @JsonProperty("btrip_title")
    private String btripTitle;
    @JsonProperty("is_special")
    private Boolean isSpecial;
    @JsonProperty("real_from_city_ad_code")
    private String realFromCityAdCode;
    @JsonProperty("real_to_city_ad_code")
    private String realToCityAdCode;
    @JsonProperty("from_city_ad_code")
    private String fromCityAdCode;
    @JsonProperty("to_city_ad_code")
    private String toCityAdCode;
    @JsonProperty("special_types")
    private List<String> specialTypes;

    @NoArgsConstructor
    @Data
    public static class PriceInfoListDTO {
        @JsonProperty("price")
        private BigDecimal price;
        @JsonProperty("person_price")
        private BigDecimal personPrice;
        @JsonProperty("type")
        private Integer type;
        @JsonProperty("category_type")
        private Integer categoryType;
        @JsonProperty("category_code")
        private Integer categoryCode;
        @JsonProperty("pay_type")
        private Integer payType;
        @JsonProperty("passenger_name")
        private String passengerName;
        @JsonProperty("trade_id")
        private String tradeId;
        @JsonProperty("gmt_create")
        private LocalDateTime gmtCreate;
    }

    @NoArgsConstructor
    @Data
    public static class UserAffiliateListDTO {
        @JsonProperty("user_id")
        private String userId;
        @JsonProperty("user_name")
        private String userName;
    }
}
