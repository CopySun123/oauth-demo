package com.copysun.rabbitmq.controller;

import com.copysun.rabbitmq.domain.UserDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author copysun
 */
@RestController
public class MQTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/mq")
    public void testMq(){
        UserDto userDto=new UserDto();
        userDto.setId("16820003");
        userDto.setName("copysun");

        //发送消息,直连模式
//        rabbitTemplate.convertAndSend("directExchange-test-01","exchange-queue-03",userDto);


        //广播模式
        rabbitTemplate.convertAndSend("fanoutExchange-test-02","",userDto);
    }
}
