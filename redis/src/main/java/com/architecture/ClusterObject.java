package com.architecture;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ClusterObject {
    private static JedisCluster jedisCluster =null;
    private static  String ip="192.168.1.1";
    public static JedisCluster getJedisCluster()
    {
        if(jedisCluster!=null ) return jedisCluster;
        // 数据库链接池配置
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(100);

        config.setMaxIdle(50);

        config.setMinIdle(20);

        config.setMaxWaitMillis(6 * 1000);

        config.setTestOnBorrow(true);

        // Redis集群的节点集合

        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();

        jedisClusterNodes.add(new HostAndPort(ip, 7000));

        jedisClusterNodes.add(new HostAndPort(ip, 7001));

        jedisClusterNodes.add(new HostAndPort(ip, 7002));

        jedisClusterNodes.add(new HostAndPort(ip, 7003));

        jedisClusterNodes.add(new HostAndPort(ip, 7004));

        jedisClusterNodes.add(new HostAndPort(ip, 7005));


        // 节点，超时时间，最多重定向次数，链接池
        jedisCluster = new JedisCluster(jedisClusterNodes, 2000, 100,config);
        return jedisCluster;
    }
    public static void after(){
        try {
            if(jedisCluster != null) jedisCluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
