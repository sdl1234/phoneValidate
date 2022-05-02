package com.atguigu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.utils.VerifyCodeConfig;

import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class CodeVerifyServlet
 */
public class CodeVerifyServlet extends HttpServlet {


    /*private static final long serialVersionUID = 1L;

     *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public CodeVerifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    */

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String phoneNo = request.getParameter("phone_no");
        String verifyCode = request.getParameter("verify_code");

        if (phoneNo.equals("") || verifyCode.equals("")) {
            return;
        }

        //redis连接

        Jedis jedis = new Jedis(VerifyCodeConfig.HOST, VerifyCodeConfig.PORT);
        String verifyCodeKey = VerifyCodeConfig.PHONE_PREFIX + phoneNo + VerifyCodeConfig.CODE_SUFFIX;
        String code = jedis.get(verifyCodeKey);
        if (code.equals(verifyCode)){
            response.getWriter().write("true");
            System.out.println("验证成功");
        }else {
            response.getWriter().write("");
            System.out.println("验证失败");
        }
        jedis.close();















        /*
        Jedis jedis = new Jedis("192.168.44.129", 6379);
        String phoneNo = request.getParameter("phone_no");
        //获取客户端的验证码
        String code = request.getParameter("verify_code");
        if (phoneNo == null || code == null) {
            return;
        }
        //获取redis中的验证码
        String codeKey = VerifyCodeConfig.PHONE_PREFIX + phoneNo + VerifyCodeConfig.CODE_SUFFIX;
        String redisCode = jedis.get(codeKey);
        //比较
        if (redisCode.equals(code)) {
            response.getWriter().write("true");
        }

    }*/


       /* Jedis jedis=new Jedis(VerifyCodeConfig.HOST,VerifyCodeConfig.PORT);

        String verify_code = request.getParameter("verify_code");
        String phoneNo = request.getParameter("phone_no");
        if (phoneNo==null||verify_code==null){
            return;
        }

        String codeKey =VerifyCodeConfig.PHONE_PREFIX+phoneNo+VerifyCodeConfig.CODE_SUFFIX;
        String s = jedis.get(codeKey);
        if (s.equals(verify_code)){
            response.getWriter().write("true");
        }else {
            response.getWriter().write("");
        }
        jedis.close();*/
    }

}
