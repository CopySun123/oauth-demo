package com.copysun.rabbitmq.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 设置json反序列化
 * @author copysun
 */
@Configuration
public class RabbitTemplateConfig {

    @Bean
    public MessageConverter json2Converter(){
        return new Jackson2JsonMessageConverter();
    }
}
