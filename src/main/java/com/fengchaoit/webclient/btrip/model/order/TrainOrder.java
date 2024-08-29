package com.fengchaoit.webclient.btrip.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 火车订单
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午5:28 2024/8/28
 */
@NoArgsConstructor
@Data
public class TrainOrder extends Order {
    @JsonProperty("gmt_create")
    private LocalDateTime gmtCreate;
    @JsonProperty("seat_type")
    private String seatType;
    @JsonProperty("arr_city")
    private String arrCity;
    @JsonProperty("arr_city_ad_code")
    private String arrCityAdCode;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("arr_station")
    private String arrStation;
    @JsonProperty("depart_id")
    private String departId;
    @JsonProperty("dep_city_ad_code")
    private String depCityAdCode;
    @JsonProperty("dep_city")
    private String depCity;
    @JsonProperty("gmt_modified")
    private LocalDateTime gmtModified;
    @JsonProperty("price_info_list")
    private List<PriceInfoListDTO> priceInfoList;
    @JsonProperty("depart_name")
    private String departName;
    @JsonProperty("contact_name")
    private String contactName;
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
    @JsonProperty("apply_id")
    private Integer applyId;
    @JsonProperty("user_affiliate_list")
    private List<UserAffiliateListDTO> userAffiliateList;
    @JsonProperty("thirdpart_itinerary_id")
    private String thirdpartItineraryId;
    @JsonProperty("run_time")
    private String runTime;
    @JsonProperty("ticket_no12306")
    private String ticketNo12306;
    @JsonProperty("train_number")
    private String trainNumber;
    @JsonProperty("ticket_count")
    private Integer ticketCount;
    @JsonProperty("dep_time")
    private LocalDateTime depTime;
    @JsonProperty("corp_name")
    private String corpName;
    @JsonProperty("btrip_title")
    private String btripTitle;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("cost_center")
    private CostCenterDTO costCenter;
    @JsonProperty("train_type")
    private String trainType;
    @JsonProperty("rider_name")
    private String riderName;
    @JsonProperty("arr_time")
    private LocalDateTime arrTime;
    @JsonProperty("invoice")
    private InvoiceDTO invoice;
    @JsonProperty("corp_id")
    private String corpId;
    @JsonProperty("dep_station")
    private String depStation;
    @JsonProperty("status")
    private Integer status;

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
        @JsonProperty("start_city")
        private String startCity;
        @JsonProperty("end_city")
        private String endCity;
        @JsonProperty("start_time")
        private LocalDateTime startTime;
        @JsonProperty("end_time")
        private LocalDateTime endTime;
        @JsonProperty("seat_type")
        private String seatType;
        @JsonProperty("train_no")
        private String trainNo;
        @JsonProperty("original_train_no")
        private String originalTrainNo;
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