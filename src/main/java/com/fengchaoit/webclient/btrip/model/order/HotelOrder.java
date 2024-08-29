package com.fengchaoit.webclient.btrip.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
public class HotelOrder extends Order {
    @JsonProperty("gmt_create")
    private LocalDateTime gmtCreate;
    @JsonProperty("city")
    private String city;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("depart_id")
    private String departId;
    @JsonProperty("gmt_modified")
    private LocalDateTime gmtModified;
    @JsonProperty("price_info_list")
    private List<PriceInfoListDTO> priceInfoList;
    @JsonProperty("depart_name")
    private String departName;
    @JsonProperty("city_ad_code")
    private String cityAdCode;
    @JsonProperty("check_out")
    private LocalDateTime checkOut;
    @JsonProperty("order_status")
    private Integer orderStatus;
    @JsonProperty("room_num")
    private Integer roomNum;
    @JsonProperty("thirdpart_project_id")
    private String thirdpartProjectId;
    @JsonProperty("project_id")
    private Integer projectId;
    @JsonProperty("thirdpart_apply_id")
    private String thirdpartApplyId;
    @JsonProperty("hotel_support_vat_invoice_type")
    private Integer hotelSupportVatInvoiceType;
    @JsonProperty("apply_id")
    private Integer applyId;
    @JsonProperty("order_type")
    private Integer orderType;
    @JsonProperty("user_affiliate_list")
    private List<UserAffiliateListDTO> userAffiliateList;
    @JsonProperty("thirdpart_itinerary_id")
    private String thirdpartItineraryId;
    @JsonProperty("contact_name")
    private String contactName;
    @JsonProperty("check_in")
    private LocalDateTime checkIn;
    @JsonProperty("night")
    private Integer night;
    @JsonProperty("project_code")
    private String projectCode;
    @JsonProperty("project_title")
    private String projectTitle;
    @JsonProperty("corp_name")
    private String corpName;
    @JsonProperty("hotel_name")
    private String hotelName;
    @JsonProperty("btrip_title")
    private String btripTitle;
    @JsonProperty("order_type_desc")
    private String orderTypeDesc;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("extend_field")
    private String extendField;
    @JsonProperty("cost_center")
    private CostCenterDTO costCenter;
    @JsonProperty("guest")
    private String guest;
    @JsonProperty("invoice")
    private InvoiceDTO invoice;
    @JsonProperty("order_status_desc")
    private String orderStatusDesc;
    @JsonProperty("corp_id")
    private String corpId;
    @JsonProperty("room_type")
    private String roomType;

    @NoArgsConstructor
    @Data
    public static class CostCenterDTO {
        @JsonProperty("name")
        private String name;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("corp_id")
        private String corpId;
        @JsonProperty("number")
        private String number;
    }

    @NoArgsConstructor
    @Data
    public static class InvoiceDTO {
        @JsonProperty("invoice_type")
        private Integer invoiceType;
        @JsonProperty("id")
        private Long id;
        @JsonProperty("title")
        private String title;
    }

    @NoArgsConstructor
    @Data
    public static class PriceInfoListDTO {
        @JsonProperty("gmt_create")
        private LocalDateTime gmtCreate;
        @JsonProperty("trade_id")
        private String tradeId;
        @JsonProperty("category_code")
        private Integer categoryCode;
        @JsonProperty("price")
        private BigDecimal price;
        @JsonProperty("category_type")
        private Integer categoryType;
        @JsonProperty("pay_type")
        private Integer payType;
        @JsonProperty("type")
        private Integer type;
        @JsonProperty("passenger_name")
        private String passengerName;
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
