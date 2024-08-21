package com.fengchaoit.webclient.btrip.param.feishu;

import lombok.Getter;
import lombok.Setter;

/**
 * 事件回调参数
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午5:58 2024/8/20
 */
@Getter
@Setter
public class EventParam {

    private String subscribeKey;
    private String packID;
    private EventContent eventContent;

    private EventParam(Builder builder) {
        this.subscribeKey = builder.subscribeKey;
        this.packID = builder.packID;
        this.eventContent = builder.eventContent;
    }

    public static class Builder {
        private String subscribeKey;
        private String packID;
        private EventContent eventContent;

        public Builder subscribeKey(String subscribeKey) {
            this.subscribeKey = subscribeKey;
            return this;
        }

        public Builder packID(String packID) {
            this.packID = packID;
            return this;
        }

        public Builder eventContent(EventContent eventContent) {
            this.eventContent = eventContent;
            return this;
        }

        public EventParam build() {
            return new EventParam(this);
        }
    }

    public static class EventContent {
        private String schema;
        private Header header;
        private String event;

        private EventContent(Builder builder) {
            this.schema = builder.schema;
            this.header = builder.header;
            this.event = builder.event;
        }

        public static class Builder {
            private String schema;
            private Header header;
            private String event;

            public Builder schema(String schema) {
                this.schema = schema;
                return this;
            }

            public Builder header(Header header) {
                this.header = header;
                return this;
            }

            public Builder event(String event) {
                this.event = event;
                return this;
            }

            public EventContent build() {
                return new EventContent(this);
            }
        }

        public static class Header {
            private long ts;
            private String eventID;
            private int eventType;

            public Header(long ts, String eventID, int eventType) {
                this.ts = ts;
                this.eventID = eventID;
                this.eventType = eventType;
            }
        }
    }

}
