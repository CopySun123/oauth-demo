package com.copysun.microoauth2auth.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 * 生成秘钥对
 * @author copysun
 */
public class RsaUtils {
    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 生成密钥对(公钥和私钥)
     *
     * @return
     * @throws Exception
     */
    public static KeyPair genKeyPair()  {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGen.initialize(1024);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            return keyPair;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
