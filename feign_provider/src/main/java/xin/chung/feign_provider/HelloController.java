package xin.chung.feign_provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Chung
 * @Date 2018/11/20 11:24
 */
@RestController
@RequestMapping("/hello")
@RefreshScope // 使用该注解可在SpringCloudConfig配置刷新时自动更新本类配置
@EnableBinding(Source.class)
public class HelloController {
    @Autowired
    private IPConfig ipConfig;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private Source source;

    @Value("${env}")
    private String env;

    @GetMapping("/chung")
    public String helloChung() throws InterruptedException {
        Thread.sleep(2000);
        return "Hello Chung By Provider on env:" + env + " and port:" + ipConfig.getPort();
    }

    @GetMapping("/send")
    public void sendMsg() {
        amqpTemplate.convertAndSend("myQueue", "MSG from provider on port:" + ipConfig.getPort());
    }

    @GetMapping("/order")
    public void orderMsg() {
        amqpTemplate.convertAndSend("OrderExchange", "order", "Order MSG from provider on port:" + ipConfig.getPort());
    }

    @GetMapping("/payment")
    public void paymentMsg() {
        amqpTemplate.convertAndSend("PaymentExchange", "payment", "Payment MSG from provider on port:" + ipConfig.getPort());
    }

    @GetMapping("/stream")
    public void stream() {
        source.output().send(MessageBuilder.withPayload("Stream MSG from provider on port:" + ipConfig.getPort()).build());
    }
}
