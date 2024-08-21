package com.fengchaoit.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * todo
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午4:18 2024/8/20
 */
@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class PrimaryOrder {
    private String primaryID;

    private Order data;
}
