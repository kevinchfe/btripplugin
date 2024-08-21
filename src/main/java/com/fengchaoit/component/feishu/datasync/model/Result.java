package com.fengchaoit.component.feishu.datasync.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

/**
 * 数据同步响应结果
 *
 * @author kevin
 * @since Created in 14:49 2024/8/20
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class Result {
    /**
     * 响应编码
     */
    private final Integer code;
    /**
     * 响应数据消息
     */
    private final String msg;
    /**
     * 响应数据内容
     */
    private final Object data;

    Result(Builder builder) {
        this.code = builder.code;
        this.msg = builder.msg;
        this.data = builder.data;
    }

    public static Builder success() {
        return new Builder(0, "");
    }

    public static Builder fail() {
        return fail("操作失败");
    }

    public static Builder fail(String msg) {
        return new Builder(500, msg);
    }

    public static class Builder {
        /**
         * 响应编码
         */
        private final Integer code;
        /**
         * 响应数据消息
         */
        private String msg;
        /**
         * 响应数据内容
         */
        private Object data;

        Builder(Integer code) {
            this.code = code;
        }

        Builder(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }


        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public Result build() {
            return new Result(this);
        }
    }
}
