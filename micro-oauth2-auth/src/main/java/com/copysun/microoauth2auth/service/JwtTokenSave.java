package com.copysun.microoauth2auth.service;

import com.copysun.microoauth2auth.utils.RsaUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;

/**
 * @author copysun
 */
@Service
public class JwtTokenSave {


    private final String SigningKey="copysun";
    /**
     * jwtToken
     * @return
     */
    @Bean
    public TokenStore tokenStore() {

        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 生成token
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
            jwtAccessTokenConverter.setSigningKey(SigningKey);
//            jwtAccessTokenConverter.setVerifier(new RsaVerifier(SigningKey));
            jwtAccessTokenConverter.setVerifier(new MacSigner(SigningKey));
            return jwtAccessTokenConverter;
    }


}
