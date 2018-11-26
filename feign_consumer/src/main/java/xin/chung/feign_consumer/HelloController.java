package xin.chung.feign_consumer;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Chung
 * @Date 2018/11/20 11:28
 */
@RestController
@RequestMapping("hello")
@DefaultProperties(defaultFallback = "defaultFallback")
public class HelloController {
    @Autowired
    private ZipkinTestService zipkinTestService;

    // 降级
//    @HystrixCommand(
//            fallbackMethod = "helloFallback",
//            commandProperties = {
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")  //超时时间
//            }
//    )
    // 熔断
//    @HystrixCommand(
//            fallbackMethod = "helloFallback",
//            commandProperties = {
//                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),                //开启熔断
//                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),    //请求数达到后才计算
//                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠时间窗
//                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),    //错误率
//            }
//    )

    // 搭配配置文件写法
    @HystrixCommand
    @GetMapping("/consume")
    public String hello(@RequestParam Integer num) {
        if (num % 2 == 0) {
            return "双数直接返回success";
        }
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForObject("http://localhost:8081/hello/chung", String.class);
        return zipkinTestService.test();
    }

    private String helloFallback(Integer num) {
        return "网络拥堵@Hystrix helloFallback! " + num;
    }

    private String defaultFallback() {
        return "网络拥堵@Hystrix defaultFallback!";
    }
}
