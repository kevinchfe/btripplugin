package com.fengchaoit.dto.feishu.datasync;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * todo
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午2:39 2024/8/20
 */
@Getter
@Setter
@ToString
public class OrderDto {
    @JsonProperty("order_no")
    private String orderNo;

    @JsonProperty("order_name")
    private String orderName;
}
