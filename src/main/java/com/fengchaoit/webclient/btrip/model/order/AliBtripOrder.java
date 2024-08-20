package com.fengchaoit.webclient.btrip.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 阿里商旅基础订单信息
 *
 * @author chengfei
 * @version 1.0
 * @since Created in 14:17 2024/5/9
 */
@Getter
@Setter
@ToString
public class AliBtripOrder {

    @JsonProperty("id")
    private Long id;

}
