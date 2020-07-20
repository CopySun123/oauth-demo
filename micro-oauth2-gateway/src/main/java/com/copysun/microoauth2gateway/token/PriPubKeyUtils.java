package com.copysun.microoauth2gateway.token;

import org.apache.commons.codec.binary.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成随机私钥和公钥工具
 * @author copysun
 */
public class PriPubKeyUtils {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Map<Integer,String> keyMap=genKeyPair();
        System.out.println(keyMap.get(0));
        System.out.println(keyMap.get(1));

    }

    public static Map<Integer, String> genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        Map<Integer, String> keyMap = new HashMap<Integer, String>();
        // 0表示公钥
        keyMap.put(0, publicKeyString);
        // 1表示私钥
        keyMap.put(1, privateKeyString);

        return keyMap;
    }
}
