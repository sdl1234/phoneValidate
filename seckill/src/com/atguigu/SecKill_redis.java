package com.atguigu;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Transaction;


public class SecKill_redis {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SecKill_redis.class);

    public static void main(String[] args) {

        Jedis jedis = new Jedis("192.168.146.128", 6379);

        for (int i = 100; i > 0; i--) {
            jedis.rpush("sk:0101:qt", String.valueOf(i));
        }
        jedis.close();
    }

    public static boolean doSecKill(String uid, String prodid) throws IOException {

        //链接数据库
        //Jedis jedis = new Jedis("192.168.146.128", 6379);
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        Jedis jedis = jedisPool.getResource();
        //获取传过来的用户ID和物品ID
        String usrKey = "sk:" + uid + ":usr";
        String qtKey = "sk:" + prodid + ":qt";
        String result = jedis.lpop(qtKey);
        if (result==null){
            System.out.println("活动结束");
            jedis.close();
            return false;
        }
        System.out.println("秒杀成功");
        return true;














        /*//链接数据库
        Jedis jedis = new Jedis("192.168.146.128", 6379);
        //获取传过来的用户ID和物品ID
        String usrKey = "sk:" + uid + ":usr";
        String qtKey = "sk:" + prodid + ":qt";
        jedis.watch(qtKey);
        //判断是否有重复ID存在
        if (jedis.sismember(usrKey,uid)){
            System.out.println("已经秒杀过了。滚蛋!!!");
            jedis.close();
            return false;
        }

        //获取库存
        String countStr = jedis.get(qtKey);
        //将获取的String类型转换成int类型
        int count =Integer.parseInt(countStr);
        //判断库存是否小于等于0
        if (count<=0){
            System.out.println("卖完了，手速有待提升！");
            jedis.close();
            return false;
        }
        //判断物品库存是否存在
        if (countStr == null){
            System.out.println("物品还未上架");
            jedis.close();
            return false;
        }

        //开启事务
        Transaction multi = jedis.multi();
        //减库存
        multi.decr(qtKey);
        //添加用户ID 使用set集合添加
        multi.sadd(usrKey,uid);
       //运行事务
        List<Object> rul = multi.exec();
        if (rul==null||rul.size()==0){
            System.out.println("活动未开始");
            jedis.close();
            return false;
        }
        System.out.println("秒杀成功");

          return true;
    }*/















      /*Jedis jedis=new Jedis("192.168.44.129",6379);
        //库存的key second kill
        String qtKey="sk:"+prodid+":qt";
        //人的key
        String usrKey="sk:"+prodid+":usr";
        //使用list集合 集合中的元素吐完集合为null
        String lpop = jedis.lpop(qtKey);
        if(lpop==null){
            System.out.println("活动结束,活动还没有开始!!");
            jedis.close();
            return false;
        }*/



       /* jedis.watch(qtKey);//库存的初始版本 1.0

        //set集合中 1 2 3 4 5
        if(jedis.sismember(usrKey,uid)){
            System.out.println("不能重复秒杀!!");
            jedis.close();
            return false;
        }

        String qtStr = jedis.get(qtKey);//获取库存数量
        if(qtStr==null){//没有库存
            System.out.println("莫着急,活动还没有开始");
            jedis.close();
            return false;
        }
        int qt = Integer.parseInt(qtStr);
        if(qt<=0){
            System.out.println("活动结束");
            jedis.close();
            return false;
        }

        Transaction multi = jedis.multi();//开启事务
        //减库存
        multi.decr(qtKey);
        //加人
        multi.sadd(usrKey,uid);
        List<Object> exec = multi.exec();//提交事务
        if(exec==null||exec.size()==0){
            System.out.println("秒杀失败!!");
            jedis.close();
            return false;
        }*/
        /*System.out.println("秒杀成功");
        return true;*/



        /*  ab -n 1000 -c 200 -p /postfile -T "application/x-www-form-urlencoded" http://192.168.20.36:8080/seckill/doseckill*/





    }
}
















