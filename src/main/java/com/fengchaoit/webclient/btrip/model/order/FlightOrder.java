package com.fengchaoit.webclient.btrip.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
public class FlightOrder extends AliBtripOrder{
    @JsonProperty("gmt_create")
    private LocalDateTime gmtCreate;
    @JsonProperty("arr_city")
    private String arrCity;
    @JsonProperty("arr_city_ad_code")
    private String arrCityAdCode;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("depart_id")
    private String departId;
    @JsonProperty("dep_city_ad_code")
    private String depCityAdCode;
    @JsonProperty("dep_city")
    private String depCity;
    @JsonProperty("passenger_count")
    private Integer passengerCount;
    @JsonProperty("discount")
    private String discount;
    @JsonProperty("gmt_modified")
    private LocalDateTime gmtModified;
    @JsonProperty("dep_date")
    private LocalDateTime depDate;
    @JsonProperty("price_info_list")
    private List<PriceInfoListDTO> priceInfoList;
    @JsonProperty("depart_name")
    private String departName;
    @JsonProperty("trip_type")
    private Integer tripType;
    @JsonProperty("flight_no")
    private String flightNo;
    @JsonProperty("project_id")
    private Integer projectId;
    @JsonProperty("project_code")
    private String projectCode;
    @JsonProperty("project_title")
    private String projectTitle;
    @JsonProperty("third_part_project_id")
    private String thirdPartProjectId;
    @JsonProperty("thirdpart_apply_id")
    private String thirdpartApplyId;
    @JsonProperty("insure_info_list")
    private List<InsureInfoListDTO> insureInfoList;
    @JsonProperty("apply_id")
    private Integer applyId;
    @JsonProperty("user_affiliate_list")
    private List<UserAffiliateListDTO> userAffiliateList;
    @JsonProperty("thirdpart_itinerary_id")
    private String thirdpartItineraryId;
    @JsonProperty("cabin_class")
    private String cabinClass;
    @JsonProperty("contact_name")
    private String contactName;
    @JsonProperty("arr_airport")
    private String arrAirport;
    @JsonProperty("corp_name")
    private String corpName;
    @JsonProperty("passenger_name")
    private String passengerName;
    @JsonProperty("dep_airport")
    private String depAirport;
    @JsonProperty("btrip_title")
    private String btripTitle;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("cost_center")
    private CostCenterDTO costCenter;
    @JsonProperty("ret_date")
    private LocalDateTime retDate;
    @JsonProperty("invoice")
    private InvoiceDTO invoice;
    @JsonProperty("corp_id")
    private String corpId;
    @JsonProperty("status")
    private Integer status;

    @NoArgsConstructor
    @Data
    public static class CostCenterDTO {
        @JsonProperty("name")
        private String name;
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("number")
        private String number;
        @JsonProperty("corp_id")
        private String corpId;
    }

    @NoArgsConstructor
    @Data
    public static class InvoiceDTO {
        @JsonProperty("id")
        private Integer id;
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
        @JsonProperty("trade_no")
        private String tradeNo;
        @JsonProperty("ticket_no")
        private String ticketNo;
        @JsonProperty("original_ticket_no")
        private String originalTicketNo;
        @JsonProperty("change_flight_no")
        private String changeFlightNo;
        @JsonProperty("discount")
        private String discount;
        @JsonProperty("start_time")
        private LocalDateTime startTime;
        @JsonProperty("end_time")
        private LocalDateTime endTime;
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

    @NoArgsConstructor
    @Data
    public static class InsureInfoListDTO {
        @JsonProperty("insure_no")
        private String insureNo;
        @JsonProperty("status")
        private Integer status;
        @JsonProperty("name")
        private String name;
    }

}
