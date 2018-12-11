package com.architecture;

/**
 * @Auther: mengxiangxiang
 * @Date: 2018/12/11 14:54
 * @Description:
 */
import java.security.Key;

import java.security.KeyPair;

import java.security.KeyPairGenerator;

import java.security.interfaces.RSAPrivateKey;

import java.security.interfaces.RSAPublicKey;

import java.util.HashMap;

import java.util.Map;

import sun.misc.BASE64Decoder;

import sun.misc.BASE64Encoder;

@SuppressWarnings("unused")

public class RSA {

    public static final String KEY_ALGORITHM = "RSA";

    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    private static final String PUBLIC_KEY = "RSAPublicKey";

    private static final String PRIVATE_KEY = "RSAPrivateKey";



    public static void main(String[] args) {

        Map<String, Object> keyMap;

        try {

            keyMap = initKey();

            String publicKey =  getPublicKey(keyMap);

            System.out.println(publicKey);

            String privateKey =  getPrivateKey(keyMap);

            System.out.println(privateKey);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {

        Key key = (Key) keyMap.get(PUBLIC_KEY);

        byte[] publicKey = key.getEncoded();

        return encryptBASE64(key.getEncoded());

    }

    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {

        Key key = (Key) keyMap.get(PRIVATE_KEY);

        byte[] privateKey =key.getEncoded();

        return encryptBASE64(key.getEncoded());

    }



    public static byte[] decryptBASE64(String key) throws Exception {

        return (new BASE64Decoder()).decodeBuffer(key);

    }



    public static String encryptBASE64(byte[] key) throws Exception {

        return (new BASE64Encoder()).encodeBuffer(key);

    }



    public static Map<String, Object> initKey() throws Exception {

        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);

        keyPairGen.initialize(2048);

        KeyPair keyPair = keyPairGen.generateKeyPair();

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Object> keyMap = new HashMap<String, Object>(2);

        keyMap.put(PUBLIC_KEY, publicKey);

        keyMap.put(PRIVATE_KEY, privateKey);

        return keyMap;

    }

    /**
     MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq4Ektn62LmHqm4XyVbnzSY/P7WsMEyz+
     1SmN1V9/qA6tKpGvqcT7GEh1QBajiY76HMFLXhj9Fotduc7x6xFzLQ3neoadEIWi5CFtPx/tY2d6
     JR/UKg6sbbI3ZjNw3HsxD/IFhR/fu3o5nJTzNFn2grpVgfj9OljA632T1EXhYlmFpyr8Knybg99y
     4GosABxxxO8obAyMLUxw7A3YTQJ/pMxXYmqXXpQ31griPvrOLwC5xglVWmm9c6rvq+ioHrrIf87c
     Xc+/TIhwfxR7Tqd5GME3vCEHes/IKaG+6r5fLXDx0uQ5s83+5igSMusAHPg7CpZuwrxPD33gkpDj
     ui68BwIDAQAB

     MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCrgSS2frYuYeqbhfJVufNJj8/t
     awwTLP7VKY3VX3+oDq0qka+pxPsYSHVAFqOJjvocwUteGP0Wi125zvHrEXMtDed6hp0QhaLkIW0/
     H+1jZ3olH9QqDqxtsjdmM3DcezEP8gWFH9+7ejmclPM0WfaCulWB+P06WMDrfZPUReFiWYWnKvwq
     fJuD33LgaiwAHHHE7yhsDIwtTHDsDdhNAn+kzFdiapdelDfWCuI++s4vALnGCVVaab1zqu+r6Kge
     ush/ztxdz79MiHB/FHtOp3kYwTe8IQd6z8gpob7qvl8tcPHS5Dmzzf7mKBIy6wAc+DsKlm7CvE8P
     feCSkOO6LrwHAgMBAAECggEAUhcBhJyIBRF3IzPs952G1roQU1q+r9sEqvE142DPkJhdyJdtFnyj
     l07vWFq9slUOYH8g3Qxm78iLdymX3+U9VN5n4tzWBxeADWP2j5VvofngnC5s9iiJ3gPEwpVyuwX4
     SOKWQ88q3ui09L7S3kcFWDNqREjypF/hD5bPEHbWV6Up3mjrCBj4ypUukHUw2O0uw3m0mb5p4IXv
     18WHyoGGdA7Jwlms+BeWI0GqeqfcGf5LWagjU5KlcYkwgjuIelFAF+KrDjXn3LAU7+LUdenKC1Su
     QhPmZuZ2vUadUVwUtzNm7fhwmXmODpy4IOOTYIelSWFm6c6A+I6hb2MVJUKDYQKBgQDsw15FgQeT
     NmUDXX41cr+3iJJcGD6eH/wpnkhMU+NBUCT28iqPCJG9LXAFP01vyikLRkn8MOJUEeAnueCA2r4X
     v+zAaIfvOMwYnWZrZUy5PuCJVx8zG6w2Yv0vMgsbqcF1VvPf+F6s44OrwExi7QATIFJY/y83bLIw
     3S0NcPNdFwKBgQC5cGfsKjIY8ke48CyL0ISpUiw9c04yvjYWTtiKcl3F1oqFbDLh+S7Mmo2taObC
     ITfGEXLW+0XnXmy1r54vp/5FMhS7Xc5SDzcLu3TA2cHF9FiFAzJ7KHY7/6TbyBBjLS3CWMU7US2p
     UIP6nMwy4eCWsw07qVfPvNk7ymiGdhZOkQKBgFO3XO0Mi6Yk9KC0/Txe36VAuCeyNunoe1wn6H58
     pv1gHkHnLN84c3fFyjdAw9vESrD+4Ig4rL53N6A0XEqTjeWN6mO6Ul5m4dvOQ/mzbuaNcg197b0/
     iqwMYmO+uSjF5G7eGQtXofpV1Cf1rczI6l7Y7eUkDgj1+SfH/PckuhhvAoGBAKdZke/HhL6Op7t2
     xDzJSW+lANfUH1Yb4qPvM+x9mbLZEtml86WLQ3jSYdmFOfsoDzVoucLZsRREWsb8Xj2T1hyMPbl5
     xqC3qm2kFpGT8xO6l8gyQnRy54DuDNFrk55tP0aM5jHJ6PNL/k9FAeNJhW162Q5mnSCUxVb4K6xt
     FWSBAoGATNmLqgQuwqAPfU9+EJzVZwPD04nNBzMy9/iVTN0nKitqlQyeGmYqsFPTtutQMum+W7NZ
     wh5NL4A5vZWEqHeZNe8xCB1Fi0kctxOPfbbEMQNvBxzrK8D3cPyIFSEs8mleajExpYYoXHsLdWZr
     c/UVTxkJnair7SnbcAyaNvt05mc=

     */

}