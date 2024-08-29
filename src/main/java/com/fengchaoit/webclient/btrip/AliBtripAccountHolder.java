package com.fengchaoit.webclient.btrip;

import com.fengchaoit.webclient.btrip.model.AliBtripAccount;

import java.util.Optional;

/**
 * 账号存储类
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午5:54 2024/8/29
 */
public class AliBtripAccountHolder {

    private static ThreadLocal<AliBtripAccount> accountHolder = new ThreadLocal<>();

    public static void setAccount(String appKey,String appSecret,String cropId){
        accountHolder.set(AliBtripAccount.of(appKey,appSecret,cropId));
    }

    public static Optional<AliBtripAccount> getAccount(){
        AliBtripAccount account = accountHolder.get();
        return Optional.ofNullable(account);
    }

    public static void clear(){
        accountHolder.remove();
    }
}
