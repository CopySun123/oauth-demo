package com.copysun.microoauth2auth.service;

import com.copysun.microoauth2auth.domain.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义生成令牌
 * @author copysun
 */
@Component
public class CustomizeTokenEnhancer {

    @Bean
    public TokenEnhancer MyTokenEnhancer()
    {
        return (accessToken, authentication) -> {
            if (authentication.getUserAuthentication() != null)
            {
                Map<String, Object> additionalInformation = new HashMap<>(16);
                UserDto user = (UserDto) authentication.getUserAuthentication().getPrincipal();
                additionalInformation.put("user_id", user.getId());
                additionalInformation.put("user_name", user.getUsername());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
            }
            return accessToken;
        };
    }
}
