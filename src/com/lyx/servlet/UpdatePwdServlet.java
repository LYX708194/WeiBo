package com.lyx.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyx.entity.User;
import com.lyx.service.UserService;
import com.lyx.util.MD5Util;

/**
 * Servlet implementation class UpdatePwdServlet
 */
@WebServlet("/UpdatePwdServlet")
public class UpdatePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
		
	User user = new User();
	UserService us = new UserService();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("uname");
		String upwd = request.getParameter("upwd1");
		//将密码MD5加密
		String pwd = MD5Util.MD5Encode(upwd, "utf-8");
		
		user.setUsername(username);
		user.setPassword(pwd);
		//传入进行修改
		boolean result = us.updatePwd(user);
		
		if(result) {
			//修改成功，重新登录
			request.setAttribute("msg","修改成功，请重新登录" );
			//跳转到登录页面
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "修改密码失败");
			request.getRequestDispatcher("/jsp/updatePwd.jsp").forward(request, response);
			
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
