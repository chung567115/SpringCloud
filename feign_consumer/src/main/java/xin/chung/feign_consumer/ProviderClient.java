package xin.chung.feign_consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Chung
 * @Date 2018/11/20 11:29
 */
@FeignClient(name = "provider")
public interface ProviderClient {
    @RequestMapping(value = "/hello/chung")
    String hello();
}
