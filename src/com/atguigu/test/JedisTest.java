package com.atguigu.test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

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


        long l = System.currentTimeMillis();
        System.out.println("l = " + l);
        Date date = new Date(l);
        System.out.println("date = " + date);


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        System.out.println(format.format(date1));

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
