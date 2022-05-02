package com.atguigu.test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class JedisTest {
    public static void main(String[] args) {
        //链接Linux中的redis数据库
        Jedis jedis =new Jedis("192.168.0.128",6379);
        //获取返回值并赋值给ping
        String ping = jedis.ping();
        System.out.println(ping);
        String key = "phoneno:123:count";
        String s = jedis.get(key);
        System.out.println("s = " + s);
        jedis.incr(key);

       //key
        //获取所有的keys并将集合付给keys
/*        Set<String> keys = jedis.keys("*");
        for (String next : keys) {
            System.out.println(keys);
        }
        System.out.println(jedis.exists("k2"));
        System.out.println(jedis.ttl("k1"));*/
    }



}
