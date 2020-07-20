package com.copysun.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 设置队列和exchange绑定关系
 * @author copysun
 */
@Configuration
@EnableRabbit
public class RabbitConfig {

    /**
     * 创建一个交换机
     * @return
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange-test-01");
    }


    /**
     * 创建一个队列
     * @return
     */
    @Bean
    public Queue queue1(){
        return new Queue("queue-01",true);
    }

    /**
     * 队列绑定到交换机上面
     * @return
     */
    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(queue1()).to(directExchange()).with("exchange-queue-01");
    }


    /**
     * 创建一个队列
     * @return
     */
    @Bean
    public Queue queue2(){
        return new Queue("queue-02",true);
    }

    /**
     * 队列绑定到交换机上面
     * @return
     */
    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(queue1()).to(directExchange()).with("exchange-queue-02");}

}
