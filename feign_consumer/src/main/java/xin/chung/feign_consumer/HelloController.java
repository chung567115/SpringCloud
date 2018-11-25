package xin.chung.feign_consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Chung
 * @Date 2018/11/20 11:28
 */
@RestController
@RequestMapping("hello")
public class HelloController {
    @Autowired
    private ProviderClient providerClient;

    @GetMapping("/consumer")
    public String hello(){
        return providerClient.hello();
    }
}
