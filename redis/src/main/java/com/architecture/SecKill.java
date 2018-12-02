package com.architecture;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SecKill {
    public static void main(String args[])
    {
        JedisCluster jedisCluster = ClusterObject.getJedisCluster();

        ExecutorService executor = Executors.newFixedThreadPool(40);  //20个线程池并发数

        jedisCluster.set("watchkeys","0");

        for (int i = 0; i < 200; i++) {//设置1000个人来发起抢购
            executor.execute(new MyRunnable("user"+getRandomString(6),jedisCluster));
        }
        executor.shutdown();
        //jedisCluster.del("watchkeys");
        //ClusterObject.after();
    }
    public static String getRandomString(int length) { //length是随机字符串长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
