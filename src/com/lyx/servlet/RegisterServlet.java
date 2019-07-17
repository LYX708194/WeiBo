package com.lyx.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyx.dao.UserDao;
import com.lyx.daoImpl.UserDaoImpl;
import com.lyx.entity.User;
import com.lyx.service.UserService;
import com.lyx.util.DateUtil;
import com.lyx.util.MD5Util;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    UserService us = new UserService();
    User user = new User();
    UserDao ud = new UserDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		//获得request中的信息，即表单中的用户名和密码
		String username = request.getParameter("uname");
		String email = request.getParameter("uemail");
		String pwd1 = request.getParameter("upwd1");
		//将密码MD5加密
		String pwd = MD5Util.MD5Encode(pwd1, "utf-8");
		String time = DateUtil.getDateToDate();    //得到注册时间，只到日期
		
		user.setUsername(username);
		user.setPassword(pwd);
		user.setEmail(email);
		user.setTime(time);
		
		boolean result = us.register(user);
		
		if(result) {
			//返回值位true，注册成功,返回登录页
			request.setAttribute("msg", "注册成功，请登录");
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);		
		}else if(ud.repetitionUsername(username)){   //查询到数据库已存在此用户名
			request.setAttribute("msg", "此用户已存在，请用别的手机号码注册");
			request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
		}else if(ud.repetitionEmail(email)){   //查询到数据库已存在此邮箱
			request.setAttribute("msg", "此邮箱已被注册，请用别的邮箱注册");
			request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "注册失败，请重试");
			request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
