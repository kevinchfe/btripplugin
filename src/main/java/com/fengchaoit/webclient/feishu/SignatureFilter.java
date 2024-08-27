package com.fengchaoit.webclient.feishu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 飞书token过滤器
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 16:40 2024/8/19
 */
@Slf4j
public class SignatureFilter implements ExchangeFilterFunction {

    private final ObjectMapper objectMapper;

    public SignatureFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @NonNull
    @Override
    public Mono<ClientResponse> filter(@NonNull ClientRequest request, @NonNull ExchangeFunction next) {
        // 获取当前时间13位时间戳
        long timestamp = System.currentTimeMillis();
        String nonce = UUID.randomUUID().toString();
        HttpMethod method = request.method();
        if (method != HttpMethod.POST) {
            return next.exchange(request);
        }
        BodyInserter<?, ? super ClientHttpRequest> body = request.body();
        Optional<Field> contentOpt = getBodyValueField(body);
        if (contentOpt.isEmpty()) {
            log.warn("请求内容体中无有效参数，请求内容体为空");
            return next.exchange(request);
        }
        MediaType mediaType = request.headers().getContentType();
        if (Objects.isNull(mediaType) || !mediaType.includes(MediaType.APPLICATION_JSON)) {
            return next.exchange(request);
        }
        Optional<String> bodyJsonOpt = getRequestBody(body, contentOpt.get());
        if (bodyJsonOpt.isEmpty()) {
            log.warn("请求内容体中无有效参数，请求内容体为空");
            return next.exchange(request);
        }
        String eventContent = getBodyEventContent(bodyJsonOpt.get());
        System.out.println("eventContent = " + eventContent);
        if (eventContent.isEmpty()) {
            return next.exchange(request);
        }

        String secretKey = "DJ1IbH22daQxoRsy4VfcDRrQnTh";
        String sign = getSign(secretKey, timestamp, nonce, eventContent);
        System.out.println("sign = " + sign);
        ClientRequest newRequest = ClientRequest.from(request)
                .header("X-Base-Request-Timestamp", String.valueOf(timestamp))
                .header("X-Base-Request-Nonce", nonce)
                .header("X-Base-Request-Signature", sign)
                .build();
        return next.exchange(newRequest);
    }

    /**
     * 生成签名
     *
     * @param secretKey 密钥
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param body      请求体
     * @return 签名
     */
    private String getSign(String secretKey, long timestamp, String nonce, String body) {
        String sb = timestamp +
                nonce +
                secretKey +
                body;
        System.out.println(sb);
        return DigestUtils.sha1Hex(sb);
    }

    /**
     * 获取请求体中的eventContent
     *
     * @param body 请求体
     * @return eventContent
     */
    private String getBodyEventContent(String body) {
        try {
            TypeReference<Map<String, Object>> type = new TypeReference<>() {
            };
            Map<String, Object> bodyKv = objectMapper.readValue(body, type);
            if (bodyKv.containsKey("eventContent")) {
                return bodyKv.get("eventContent").toString();
            }

        } catch (JsonProcessingException e) {
            log.warn("请求内容体中未包含eventContent，请求内容体为：{}", body, e);
        }
        return "";
    }

    /**
     * 获取请求体
     *
     * @param body 请求体对象
     * @return 字符串
     */
    private Optional<String> getRequestBody(BodyInserter<?, ? super ClientHttpRequest> body, Field field) {
        try {
            Object value = field.get(body);
            if (Objects.isNull(value)) {
                return Optional.empty();
            }
            if (value instanceof Publisher) {
                // fixme 需要修复此处因传递为ReactiveDataBuffer导致的问题
                Publisher<DataBuffer> publisher = (Publisher<DataBuffer>) value;
                return Optional.ofNullable(publisher.toString());
            }
            String json = objectMapper.writeValueAsString(value);
            return Optional.of(json);
        } catch (IllegalAccessException | JsonProcessingException e) {
            log.warn("获取请求内容体失败", e);
        }
        return Optional.empty();
    }

    /**
     * 获取参数字段
     *
     * @param body 请求体
     * @return 参数字段
     */
    private Optional<Field> getBodyValueField(BodyInserter<?, ? super ClientHttpRequest> body) {
        AtomicReference<Field> fieldRef = new AtomicReference<>();
        ReflectionUtils.doWithFields(body.getClass(), objField -> {
            objField.setAccessible(true);
            fieldRef.set(objField);
        });
        Field field = fieldRef.get();
        return Optional.ofNullable(field);
    }

}
