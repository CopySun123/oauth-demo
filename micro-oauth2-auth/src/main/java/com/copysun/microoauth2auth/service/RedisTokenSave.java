package com.copysun.microoauth2auth.service;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author copysun
 */
@Service
public class RedisTokenSave {


    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * redis存储token
     * @return
     */
    /*@Bean
    public TokenStore tokenStore() {
        //使用redis存储token
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        //设置redis token存储中的前缀
        redisTokenStore.setPrefix("auth-token:");
        return redisTokenStore;
    }*/

}
