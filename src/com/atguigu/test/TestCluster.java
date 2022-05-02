package com.atguigu.test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class TestCluster {
    public static void main(String[] args) {

        Set<HostAndPort> set =new HashSet<HostAndPort>();
        set.add(new HostAndPort("192.168.146.128",6379));
        set.add(new HostAndPort("192.168.146.128",6380));
        set.add(new HostAndPort("192.168.146.128",6381));

        JedisCluster jedisCluster=new JedisCluster(set);

        /*jedisCluster.set("k1", "v1");*/
        System.out.println(jedisCluster.get("k1"));


    }
}
