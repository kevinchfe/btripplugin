package com.fengchaoit.webclient.feishu.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 事件回调参数
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午5:58 2024/8/20
 */
@Getter
@Setter
@ToString
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

    @Getter
    @Setter
    @ToString
    public static class EventContent {
        private String schema;
        private Header header;
        private EventDetail event;

        private EventContent(Builder builder) {
            this.schema = builder.schema;
            this.header = builder.header;
            this.event = builder.event;
        }

        public static class Builder {
            private String schema;
            private Header header;
            private EventDetail event;

            public Builder schema(String schema) {
                this.schema = schema;
                return this;
            }

            public Builder header(Header header) {
                this.header = header;
                return this;
            }

            public Builder event(EventDetail eventDetail) {
                this.event = eventDetail;
                return this;
            }

            public EventContent build() {
                return new EventContent(this);
            }
        }
    }

    @Getter
    @Setter
    @ToString
    public static class EventDetail {
        private List<String> primaryKeys;

        private EventDetail(List<String> primaryKeys) {
            this.primaryKeys = primaryKeys;
        }

        public static class Builder {
            private List<String> primaryKeys;

            public Builder primaryKeys(List<String> primaryKeys) {
                this.primaryKeys = primaryKeys;
                return this;
            }

            public EventDetail build() {
                return new EventDetail(primaryKeys);
            }
        }
    }

    @Getter
    @Setter
    @ToString
    public static class Header {
        private long ts;
        private String eventID;
        private int eventType;

        private Header(long ts, String eventID, int eventType) {
            this.ts = ts;
            this.eventID = eventID;
            this.eventType = eventType;
        }

        public static class Builder {
            private long ts;
            private String eventID;
            private int eventType;

            public Builder ts(long ts) {
                this.ts = ts;
                return this;
            }

            public Builder eventID(String eventID) {
                this.eventID = eventID;
                return this;
            }

            public Builder eventType(int eventType) {
                this.eventType = eventType;
                return this;
            }

            public Header build() {
                return new Header(ts, eventID, eventType);
            }
        }
    }
}