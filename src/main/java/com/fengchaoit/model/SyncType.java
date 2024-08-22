package com.fengchaoit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 同步类型
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 11:08 2024/8/21
 */
@AllArgsConstructor
@Getter
public enum SyncType {
    /**
     * 手动
     */
    MANUAL("manual"),
    /**
     * 定时同步
     */
    PERIODIC("periodic"),
    /**
     * 事件更新同步(同时会24h全量同步一次兜底保证数据最终一致)
     */
    TRIGGER("trigger");

    private final String type;

}
