package xin.chung.feign_consumer;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @Author Chung
 * @Date 2018/11/24 18:05
 */
@Component
@EnableBinding(Sink.class)
public class MQReceiver {
    //    @RabbitListener(queues = "my_queue")
    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    public void process(String msg) {
        System.out.println(msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("OrderExchange"),
            key = "order",
            value = @Queue("OrderQueue")
    ))
    public void consumeOrder(String msg) {
        System.out.println("order-" + msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("PaymentExchange"),
            key = "payment",
            value = @Queue("PaymentQueue")
    ))
    public void consumePayment(String msg) {
        System.out.println("payment-" + msg);
    }

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("stream：" + message.getPayload());
    }

}
