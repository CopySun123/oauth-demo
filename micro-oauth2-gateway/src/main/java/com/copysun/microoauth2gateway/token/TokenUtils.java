package com.copysun.microoauth2gateway.token;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.codec.binary.Base64;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author copysun
 */
public class TokenUtils {

    public static void main(String[] args) throws Exception {


        System.out.println(createToken());
        Map<String, Claim> claim=verifyToken(createToken());
        Claim name=claim.get("name");
        System.out.println(name.asString());
    }



    /**
     * 生成token
     * @return
     */
    public static String createToken() throws Exception {

        String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEA" +
                "AoGBAILUTAY1OlSdWx9/keoH6KYr6/t8nfTh8HtIPaXU1S5O7u7Py/p" +
                "5TbhIr5lBQNh0C4xmqcYGAT5aMlAeIUPYRDnVuEP/ho8CT4tfx1WZ/w" +
                "91Mo9zr7Vnt7/DHDiUlaGU/B2ENJ+e9PV0UpZ8v+Qp9upox4ZLLSKp9E" +
                "cEfLhtDuSpAgMBAAECgYEAgZoBPd0YzZ2cGVXsnCP9ii/Uc6r9ZwsziU" +
                "0FFPQDwv4an6Rl8tib70TB/1WvAT3j+s/wuuRT8wqy8L8IQQdKfXb2su" +
                "sVp7vqoyOLtXgW/Q9w+NtIXFtVlBoEr4zNDxjfHHSu2iMvbEN128n50H" +
                "G+81Bkzf7N5pnM/g1+eil58iECQQDekFJy66GfxcwXhZ1eXqKAe/m8Xa" +
                "jkXOihobkt5EUK+mYL9qfQ5klRIqk3P5me+77lMWse12oYocbsCTysDw" +
                "VVAkEAlnvrE0tl0dJIMnSVNdQPpn7z/SxegE2V53CB3yGd4Mmlqb1gkU" +
                "lbcMHaA3pV1oLuENzUaQFWxxPV8d5qkdSiBQJAZAac54U6+GGfHhsw5M7" +
                "HXdNaI+w8EZYn/yx920AyP4LWBh5uwZYd8VcQkgIFesxNuRgCurO5vSRI" +
                "EKD+IZ4GmQJAahSqzywsd6/r0x2Z/Zsi+TSvXL6FU57d2IvYEWpKOCcOE" +
                "Ez+oQx8WE1fGWqIzjH4Do0Nc4RNHwNdoyYuK+Uf3QJBALosyE/PVaD0cF" +
                "Zt9YxjRblXCZXZupNIAzNTNL1r0hjU6X+V+97ku62j4rRppaouhTvnch21zsPconJEGOGhs34=";

        //设置签发时间
        Date iatDate=new Date();

        //设置过期时间(15天有效期)
        Date nowDate= DateUtil.date();
        Date expiredDate=DateUtil.offsetDay(nowDate,15);

        //设置头部
        Map<String,Object> header=new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        String token= JWT.create()
                .withHeader(header)
                .withIssuedAt(iatDate)
                .withExpiresAt(expiredDate)
                .withClaim("name","copysun")
                .withSubject("subject")
                .sign(Algorithm.RSA256(null,getPrivateKey(privateKey)));

        return token;
    }

    /**
     * 生成PKCS8私钥
     * @param str
     * @return
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String str) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(str);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(pkcs8EncodedKeySpec);
    }

    /**
     * 获取公钥
     * @param str
     * @return
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String str) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(str);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        return (RSAPublicKey)KeyFactory.getInstance("RSA").generatePublic(x509EncodedKeySpec);
    }

    /**
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Claim> verifyToken(String token) throws Exception {
        String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCC1EwGNTpUn" +
                "Vsff5HqB+imK+v7fJ304fB7SD2l1NUuTu7uz8v6eU24SK+ZQUDYdAuMZqnG" +
                "BgE+WjJQHiFD2EQ51bhD/4aPAk+LX8dVmf8PdTKPc6+1Z7e/wxw4lJWhlPw" +
                "dhDSfnvT1dFKWfL/kKfbqaMeGSy0iqfRHBHy4bQ7kqQIDAQAB";
        JWTVerifier verifier = JWT.require(Algorithm.RSA256(getPublicKey(publicKey),null)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jwt.getClaims();
    }

}
