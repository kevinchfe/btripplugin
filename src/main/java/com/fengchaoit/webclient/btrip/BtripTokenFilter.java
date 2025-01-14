package com.fengchaoit.webclient.btrip;

import com.fengchaoit.exception.BusinessException;
import com.fengchaoit.utils.DateTimeFormatter;
import com.fengchaoit.utils.DateTimeUtils;
import com.fengchaoit.webclient.btrip.model.AliBtripAccount;
import com.fengchaoit.webclient.btrip.model.CorpToken;
import com.fengchaoit.webclient.Result;
import com.fengchaoit.webclient.btrip.param.CorpTokenParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 商旅token过滤器
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 16:40 2024/8/19
 */
@Slf4j
public class BtripTokenFilter implements ExchangeFilterFunction, ApplicationContextAware {

    private final Map<String,CorpToken> corpTokenMap = new ConcurrentHashMap<>(2);

    private ApplicationContext context;

    private AliBtripApi aliBtripApi;


    @Override
    public void setApplicationContext(@NonNull ApplicationContext context) throws BeansException {
        this.context = context;
    }

    @NonNull
    @Override
    public Mono<ClientResponse> filter(@NonNull ClientRequest request, @NonNull ExchangeFunction next) {
        Optional<AliBtripAccount> opt = AliBtripAccountHolder.getAccount();
        if(opt.isEmpty()){
            log.error("传入的账号信息为空，请检查");
            throw new BusinessException("账号信息不正确");
        }
        AliBtripAccount account = opt.get();
        URI uri = request.url();
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(uri);
        uriBuilder.queryParam("app_key", account.getAppKey());
        if (isCreateTokenUrl(uri.toString())) {
            URI newUri = uriBuilder.build().toUri();
            ClientRequest newRequest = ClientRequest.from(request).url(newUri).build();
            return next.exchange(newRequest);
        }
        CorpToken corpToken = corpTokenMap.get(getKey(account));
        if (checkTokenStatus(account)) {
            synchronized (this) {
                if (checkTokenStatus(account)) {
                    CorpToken tempToken = corpTokenMap.get(getKey(account));
                    corpToken = getCorpToken(account);
                    if (log.isDebugEnabled()) {
                        log.debug("商旅token判定未过期，当前时间为：{} 已对token进行刷新，过期token为：{}，当前token为：{}",
                                DateTimeFormatter.dateTimeToString(),
                                Objects.isNull(tempToken) ? null : tempToken.getToken(),
                                corpToken.getToken());
                    }
                }
            }
        }
        uriBuilder.queryParam("so_corp_token", corpToken.getToken()).build();
        URI newUri = uriBuilder.build().toUri();
        ClientRequest newRequest = ClientRequest.from(request).url(newUri).build();
        return next.exchange(newRequest);
    }

    /**
     * 获取接口
     *
     * @return 接口
     */
    private AliBtripApi getAliBtripApi() {
        if (Objects.isNull(aliBtripApi)) {
            synchronized (this) {
                if (Objects.isNull(aliBtripApi)) {
                    aliBtripApi = context.getBean(AliBtripApi.class);
                }
            }
        }
        return aliBtripApi;
    }

    /**
     * 判断是否是创建token URL
     *
     * @param url 请求url
     * @return boolean
     */
    private boolean isCreateTokenUrl(String url) {
        return StringUtils.contains(url, "/btrip-open-auth/v1/corp-token/action/take");
    }

    /**
     * 判断token状态
     *
     * @return 判断token状态
     */
    private boolean checkTokenStatus(AliBtripAccount account) {
        boolean bool = true;
        String key = getKey(account);
        if (corpTokenMap.containsKey(key)) {
            CorpToken corpToken = corpTokenMap.get(key);
            if(Objects.nonNull(corpToken)) {
                long start = corpToken.getStart();
                long expire = corpToken.getExpire();
                bool = (start + expire) <= (Instant.now().toEpochMilli() - TimeUnit.MILLISECONDS.convert(Duration.ofMinutes(30)));
                if (log.isDebugEnabled()) {
                    log.debug("商旅token生效时间为：{}，过期时间为：{}，过期前30分钟认证token过期，当前判断为：{}  当前token为：{}",
                            DateTimeFormatter.dateTimeToString(DateTimeUtils.milliSecondToDateTime(start)),
                            DateTimeFormatter.dateTimeToString(DateTimeUtils.milliSecondToDateTime(start + expire)),
                            bool,
                            corpToken.getToken());
                }
            }
        }
        return bool;
    }

    /**
     * 获取应用token
     */
    private CorpToken getCorpToken(AliBtripAccount account) {
        CorpTokenParam param = CorpTokenParam.of(account.getAppSecret(), 0, account.getCorpId());
        Result<CorpToken> result = getAliBtripApi().corpToken(param);
        if (result.isSuccess()) {
            CorpToken tempCorpToken = result.getModule();
            corpTokenMap.put(getKey(account), tempCorpToken);
            return tempCorpToken;
        }
        throw new BusinessException("获取corpToken异常，响应编码为：" + result.getCode() + "，响应消息为：" + result.getMessage());
    }

    private String getKey(AliBtripAccount account) {
        return String.format("%s-%s", account.getAppKey(), account.getCorpId());
    }


}
