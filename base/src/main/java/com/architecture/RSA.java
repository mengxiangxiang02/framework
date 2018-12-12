package com.architecture;

/**
 * @Auther: mengxiangxiang
 * @Date: 2018/12/11 14:54
 * @Description:
 */
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.*;
import sun.misc.BASE64Encoder;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

@SuppressWarnings("unused")

public class RSA {

    /** 指定加密算法为RSA */
    private static final String ALGORITHM = "RSA";
    /** 密钥长度，用来初始化 */
    private static final int KEYSIZE = 2048;
    /** 指定公钥存放文件 */
    private static String PUBLIC_KEY_FILE = "merkey.public";
    /** 指定私钥存放文件 */
    private static String PRIVATE_KEY_FILE = "merkey.private";

    public static void main(String[] args) throws Exception {
        // 生成公私钥文件
        generateKeyPair();
        // 生成公私钥字符串
        genKeyPair();
    }

    /**
     * 生成密钥对
     * @throws Exception
     */
    private static void generateKeyPair() throws Exception {

        //     /** RSA算法要求有一个可信任的随机数源 */
        //     SecureRandom secureRandom = new SecureRandom();
        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);

        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        //     keyPairGenerator.initialize(KEYSIZE, secureRandom);
        keyPairGenerator.initialize(KEYSIZE);

        /** 生成密匙对 */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        /** 得到公钥 */
        Key publicKey = keyPair.getPublic();

        /** 得到私钥 */
        Key privateKey = keyPair.getPrivate();

        ObjectOutputStream oos1 = null;
        ObjectOutputStream oos2 = null;
        try {
            /** 用对象流将生成的密钥写入文件 */
            oos1 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
            oos2 = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE));
            oos1.writeObject(publicKey);
            oos2.writeObject(privateKey);
        } catch (Exception e) {
            throw e;
        } finally {
            /** 清空缓存，关闭文件输出流 */
            if(oos1 != null){
                oos1.close();
            }
            if(oos2 != null){
                oos2.close();
            }
        }
    }

    private static void genKeyPair() throws NoSuchAlgorithmException {

        /** RSA算法要求有一个可信任的随机数源 */
        SecureRandom secureRandom = new SecureRandom();

        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);

        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        keyPairGenerator.initialize(KEYSIZE, secureRandom);
        //keyPairGenerator.initialize(KEYSIZE);

        /** 生成密匙对 */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        /** 得到公钥 */
        Key publicKey = keyPair.getPublic();

        /** 得到私钥 */
        Key privateKey = keyPair.getPrivate();

        byte[] publicKeyBytes = publicKey.getEncoded();
        byte[] privateKeyBytes = privateKey.getEncoded();

        String publicKeyBase64 = new BASE64Encoder().encode(publicKeyBytes);
        String privateKeyBase64 = new BASE64Encoder().encode(privateKeyBytes);

        System.out.println("publicKeyBase64.length():" + publicKeyBase64.length());
        System.out.println("publicKeyBase64:" + publicKeyBase64);

        System.out.println("privateKeyBase64.length():" + privateKeyBase64.length());
        System.out.println("privateKeyBase64:" + privateKeyBase64);
    }


    /**
     * MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCjNqLxvyWQCZ76BLOL99P628nh27EWc10dyi47AeUoquGVJItaXu1nJDC+e+1YPDh/Vu8Qa+0hdxsI3sTya4alKMqIjVjKaTdIwjvbBr+4j6+/xmeB+uSXaPUlJLprl4JCMfQwvXzTiDLqnwUgFRvMYZUA+xNGIrdZhr59Q5G0G1khAYJyaikHUA0pkEjwEkbDDsQCAMoqpjAdiApK/LwGS/M4DTVdQE7NHwEwJN8Wq7mu2LEeRDHr0sxdvckyhz7xAP4EXYjZPaAYKnJqL4fs/0T1GsXX4xkKRmcCcItr0uWkylMER+ZXs2Dln8FrunTY8a9pnSezr+n1D4OzQDebAgMBAAECggEBAIWR9zQknJcOaJ5wLohD36WTLdq7MmgPLYttpPwBhwsLHeYyrCDfQyZ/xax+OzVUw1/jQ26LBNu4X9WYilFikiSkB2xNUPcFuIrA+r86/LgMT2aA9sC3FVCcQtONBUt8CMwnmZIx4PA0rIa6cTVaUQ+oZIsS7ykczDRmQ4q8O4fK8rMIp5ctB+uW9+Ux8essXcUCkAk8U4tGOqN7FNDOI/ymOjDkDAZ3Em2y0Jy4SStggOMEf0w89097PZQ+N0BUIG4J1qDD5I32yFy188nh2+TmFe2dr/4kCwmyMg5p2SCE3JRUe+XNsW+xkkB6U/81S/x0QL58TR138GlzWS3VjAECgYEA2OohdTrFYcDjbMVyH4Q9zmfsYJpVB1+XxaEfyyQZ/vKs6L+5MLdhS+nKdXhciDiT0e164v69w3qgJ6H37DthEZeobfeVyzN8Z+p5/RS04sVslhWtuQcV6ri4iCf1hSIAYWZcTT/cW5nOXogLfog0LIPFpfj5DQEmaLZHjSsvbwECgYEAwJ9e7U4mjbVp3sFp1rBLOFyB5JNJJSZAiKmvpCQ6L/iCUmx/1K0I+0kbERSOwdzffuIc3tql7SpmlEdAz5Q+fNbbnnsaXcQsDJpBrCiOceqmOs/KiabiT4CZ93rMtDLwr9N4IX9f3uAOOGpx03B1TcGRWrZygRTR0VmlLHiqApsCgYAC5/AWz/jA/IKPfH2w6mahk0bZbHfjJ2azIRt57zvh7IJgg+EJRAurprAtrrUephNk5peU3vDP456tqT/XDFyajbuV7ZE2LbRm9/82Ysahmx9/Sz8OkYe7o0nlmRNy+yeiJmrqJgbfu0nGfnAY+94nEoTdWl6LzA4lrPt8aq2yAQKBgH1PcMb6v3QTIBPNSdhkChtZextDgiNL1lHR6jTjaINKrSWH9kVaaGxpa9VZxWFzGmaGHPgR0D4RBnjSPeSM8XXvpSJdvZJgi7OLKswf+kM7vvaltYKFcdb8t61bUw6r0Q7hiNZp8emhYz7qaLCACaATHEeiR62X6MPECQN8YEWfAoGBANUZsd6Ose/VVQBMpPNrOKFZB6QGjOREPCGE2RlqtlXx4yehNDdIlI0yRTqMZeirpxTU4nCbXaN3Sxtg8yQIQsY2EA1OKeWNA8SPCqehVGIJqVb7m1i82ClBAZu0NI7f524Vq0nQaLrC/zp7GUGajZ1myfgA//nDaZCkXUuXwnrJ
     */

    /**
     * MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAozai8b8lkAme+gSzi/fT+tvJ4duxFnNd
     HcouOwHlKKrhlSSLWl7tZyQwvnvtWDw4f1bvEGvtIXcbCN7E8muGpSjKiI1Yymk3SMI72wa/uI+v
     v8Zngfrkl2j1JSS6a5eCQjH0ML1804gy6p8FIBUbzGGVAPsTRiK3WYa+fUORtBtZIQGCcmopB1AN
     KZBI8BJGww7EAgDKKqYwHYgKSvy8BkvzOA01XUBOzR8BMCTfFqu5rtixHkQx69LMXb3JMoc+8QD+
     BF2I2T2gGCpyai+H7P9E9RrF1+MZCkZnAnCLa9LlpMpTBEfmV7Ng5Z/Ba7p02PGvaZ0ns6/p9Q+D
     s0A3mwIDAQAB
     */
}