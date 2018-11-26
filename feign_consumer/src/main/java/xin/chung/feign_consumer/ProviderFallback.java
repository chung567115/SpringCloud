package xin.chung.feign_consumer;

import org.springframework.stereotype.Component;

/**
 * @Author Chung
 * @Date 2018/11/26 13:41
 */
//@Component
public class ProviderFallback implements ProviderClient {
    @Override
    public String hello() {
        return "ProviderFallback";
    }
}
