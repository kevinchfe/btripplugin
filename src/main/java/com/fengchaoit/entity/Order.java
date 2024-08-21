package com.fengchaoit.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午3:07 2024/8/20
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Order {

    private String orderNo;

    private String orderName;

    private String price;


    public static Order of(String orderNo, String orderName, String price) {
        return new Order(orderNo, orderName, price);
    }
}
