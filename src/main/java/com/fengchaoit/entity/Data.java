package com.fengchaoit.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 返回数据
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午3:25 2024/8/20
 */
@Getter
@Setter
@AllArgsConstructor
public class Data {
    private String nextPageToken;

    private boolean hasMore;

    private List<PrimaryOrder> records;
}
