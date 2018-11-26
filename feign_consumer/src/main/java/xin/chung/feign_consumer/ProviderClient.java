package xin.chung.feign_consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author Chung
 * @Date 2018/11/20 11:29
 */
@FeignClient(name = "provider")//, fallback = ProviderFallback.class)
public interface ProviderClient {
    @GetMapping(value = "/hello/chung")
    String hello();
}
