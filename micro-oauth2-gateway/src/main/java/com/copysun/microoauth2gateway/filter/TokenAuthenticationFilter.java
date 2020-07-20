package com.copysun.microoauth2gateway.filter;

import cn.hutool.core.util.StrUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义校验token
 * @author copysun
 */
@Configuration
public class TokenAuthenticationFilter implements GlobalFilter, Ordered {

    private static final String LOGIN_PATH="/oauth/token";
    private static final String AUTHORIZE_TOKEN="Authorization";
    private static final String TOKEN="token";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest serverRequest=exchange.getRequest();
//        ServerHttpResponse serverHttpResponse=exchange.getResponse();
//        HttpHeaders httpHeaders=serverRequest.getHeaders();
//
//        //跳过登录获取token接口
//        if(serverRequest.getURI().getPath().contains(LOGIN_PATH)){
//            return chain.filter(exchange);
//        }
//
//        String token=httpHeaders.getFirst(AUTHORIZE_TOKEN);
//        if(token==null){
//            token=serverRequest.getQueryParams().getFirst(TOKEN);
//        }
//
//        if(StrUtil.hasBlank(token)){
//            serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
//            return serverHttpResponse.setComplete();
//        }
//        token=token.substring(7);
//        System.out.println(token);

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
