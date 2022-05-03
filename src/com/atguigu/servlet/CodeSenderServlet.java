package com.atguigu.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.utils.VerifyCodeConfig;
import com.sun.java_cup.internal.runtime.virtual_parse_stack;

import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class VerifiCodeServlet
 */
public class CodeSenderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CodeSenderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //输入手机号 生成6位验证码 两分钟有效
        //获取手机号
        String phoneNo = request.getParameter("phone_no");
        //判空
        if (phoneNo.equals("")) {
            return;
        }

        //每个手机号每天只能输入三次
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());

        Jedis jedis = new Jedis(VerifyCodeConfig.HOST, VerifyCodeConfig.PORT);
        String countKey = VerifyCodeConfig.PHONE_PREFIX + phoneNo + VerifyCodeConfig.COUNT_SUFFIX;
        String dateKey = VerifyCodeConfig.PHONE_PREFIX + phoneNo + VerifyCodeConfig.COUNT_DATE;
        String date = jedis.get(dateKey);
        if (!format.equals(date)){
            jedis.del(countKey);
        }
        String count = jedis.get(countKey);
        if (count == null) {
            //当天第一次
            jedis.setex(countKey, VerifyCodeConfig.SECONDS_PER_DAY, "1");
            jedis.setex(dateKey,VerifyCodeConfig.SECONDS_PER_DAY,format);
        } else {
            int integer = Integer.parseInt(count);
            if (integer < VerifyCodeConfig.COUNT_TIMES_1DAY) {
                //每次+1
                jedis.incr(countKey);
            } else {
                System.out.println("超过三次了");
                response.getWriter().write("limit");
                return;
            }
        }

        //生成验证码
        String code = getCode(VerifyCodeConfig.CODE_LEN);
        String codeKey = VerifyCodeConfig.PHONE_PREFIX + phoneNo + VerifyCodeConfig.CODE_SUFFIX;
        //存入redis 两分钟有效
        jedis.setex(codeKey, VerifyCodeConfig.CODE_TIMEOUT, code);
        response.getWriter().write("true");
        jedis.close();

















        /*Jedis jedis=new Jedis("192.168.44.129",6379);


        //获取手机号
        String phoneNo = request.getParameter("phone_no");
        if(phoneNo==null){
            return;
        }
*//*        3、每个手机号每天只能输入3次*//*

        String countKey=VerifyCodeConfig.PHONE_PREFIX+phoneNo+VerifyCodeConfig.COUNT_SUFFIX;
        String count = jedis.get(countKey);
        if(count==null){//第一次发送
            jedis.setex(countKey,VerifyCodeConfig.SECONDS_PER_DAY,"1");
        }else{//不是第一次发送
            int countint = Integer.parseInt(count);
            if(countint<3){
                jedis.incr(countKey);//每次加1
            }else{
                System.out.println("超过3次");
                response.getWriter().write("limit");
                return;
            }
        }

        *//*        1、输入手机号，点击发送后随机生成6位数字码，2分钟有效*//*

        //生成6位的验证码
        String code = genCode(6);
        System.out.println(code);//向手机发送验证码
        //把手机号存到redis中 2分钟有效
       *//* jedis.set("code",code);
        jedis.expire("code",120);*//*
        //phoneNo:1212123:code
        String codeKey= VerifyCodeConfig.PHONE_PREFIX+phoneNo+VerifyCodeConfig.CODE_SUFFIX;
        jedis.setex(codeKey,120,code);
        response.getWriter().write("true");



    }


    private String genCode(int len) {
        String code = "";
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            int rand = random.nextInt(10);
            code += rand;
        }
        return code;
    }*/

      /*  //获取手机号
        String phoneNo = request.getParameter("phone_no");
        //判断手机号是否为空
        if (phoneNo==null){
            return ;
        }

        //链接redis数据库
        Jedis jedis=new Jedis(VerifyCodeConfig.HOST,VerifyCodeConfig.PORT);
        //获取手机号发送验证码次数
        String countKey =VerifyCodeConfig.PHONE_PREFIX+phoneNo+VerifyCodeConfig.COUNT_SUFFIX;
        String count = jedis.get(countKey);
        if (count==null){
            jedis.setex(countKey,VerifyCodeConfig.SECONDS_PER_DAY,"1");
        }else{
            int countIn = Integer.parseInt(count);
            if (countIn<VerifyCodeConfig.COUNT_TIMES_1DAY){
                jedis.incr(countKey);
            }else {
                response.getWriter().write("limit");
                return;
            }
        }

        //获取验证码
        String code = getCode(VerifyCodeConfig.CODE_LEN);
        //想手机发送验证码
        System.out.println(code);
        //将验证码存入redis
        String codeKey =VerifyCodeConfig.PHONE_PREFIX+phoneNo+VerifyCodeConfig.CODE_SUFFIX;
        jedis.setex(codeKey,VerifyCodeConfig.CODE_TIMEOUT,code);
        //给主页面返回值
        response.getWriter().write("true");
        //关闭连接
        jedis.close();*/

    }

    /**
     * \
     * 生成验证码
     *
     * @param i
     * @return
     */
    private String getCode(int i) {
        String code = "";
        Random random = new Random();
        for (int j = 0; j < i; j++) {
            code += random.nextInt(10);
        }
        System.out.println("code = " + code);
        return code;
    }

/*    private String getCode(int ln){
        //生成6位的随机验证码 code
        String code ="";
        Random random =new Random();
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(10);
            code +=rand;
        }
        return code;
    }*/
}
