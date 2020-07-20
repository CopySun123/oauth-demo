package com.copysun.rabbitmq.service;

import com.copysun.rabbitmq.domain.UserDto;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.util.Map;

/**直连模式
 * @author copysun
 */
@Service
@RabbitListener(
        bindings = @QueueBinding(
                exchange = @Exchange(value = "directExchange-test-01"),
                value = @Queue(value="queue-03",durable ="true"),
                key = "exchange-queue-03"))
public class MQService {


    @RabbitHandler
    public void test(UserDto UserDto, Channel channel,@Header(name = "amqp_deliveryTag") long deliveryTag,
                     @Header("amqp_redelivered") boolean redelivered, @Headers Map<String, String> head) {
        System.out.println("收到消息："+UserDto.toString());
        try {
            //手动接受成回调
            channel.basicAck(deliveryTag,redelivered);
        }catch (Exception e){
            e.printStackTrace();
            try {
                //手动接受失败
                channel.basicReject(deliveryTag,!redelivered);
            }catch (Exception ex){
                e.printStackTrace();
            }
        }
    }
}
