package com.atguigu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.soap.AddressingFeature.Responses;


/**
 * Servlet implementation class miaosha
 */
public class SecKillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecKillServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//随机生成用户ID
		String userid = new Random().nextInt(50000) +"" ; 
		//获取隐藏域中的物品ID
		String prodid =request.getParameter("prodid");
		//将用户ID和物品ID传给doSecKill方法 并将返回值赋给if_success
		boolean if_success=SecKill_redis.doSecKill(userid,prodid);
 		//将返回值返回到首页
		response.getWriter().print(if_success);
	}
	

}
