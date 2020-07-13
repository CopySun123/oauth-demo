package com.copysun.microoauth2gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * 资源服务器配置
 * @author copysun
 */
@EnableWebFluxSecurity
public class MyResourceServerConfig {


    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .pathMatchers("/actuator/**","oauth/**").permitAll()
                .anyExchange().authenticated();

        http.oauth2ResourceServer().jwt().jwkSetUri("http://localhost:9401/rsa/publicKey");

        return http.build();
    }
}
