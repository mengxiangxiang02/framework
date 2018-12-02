package com.architecture;

import redis.clients.jedis.JedisCluster;

public class ListOperator {
    public static  void main(String args[])
    {
        JedisCluster jedisCluster = ClusterObject.getJedisCluster();
        for(int i=0;i<10;i++)
        {

            jedisCluster.rpush("listtest",i+"");
        }
        for(int i=0;i<=10;i++)
        {

            String listtest = jedisCluster.lpop("listtest");
            System.out.println(listtest);
        }
    }
}
