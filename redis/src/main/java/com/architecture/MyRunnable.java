package com.architecture;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MyRunnable implements Runnable {
    String userinfo;
    JedisCluster jedisCluster=null;
    public MyRunnable() {
    }
    public MyRunnable(String uinfo,JedisCluster jedisCluster) {
        this.userinfo=uinfo;
        this.jedisCluster=jedisCluster;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long incr = jedisCluster.incr("watchkeys");
        if(incr<=100)
        {
            System.out.println("恭喜你成功了"+userinfo);
        }else
        {
            System.out.println("很抱歉"+userinfo);
        }

    }
}
