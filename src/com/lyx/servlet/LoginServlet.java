package com.lyx.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyx.entity.User;
import com.lyx.service.UserService;
import com.lyx.util.MD5Util;
import com.lyx.util.ValidateUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	UserService us = new UserService();
	User user = new User();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取用户信息
		String username = request.getParameter("uname");
		//得到的直接密码，未加密
		String pwd1 = request.getParameter("upwd");
		if (ValidateUtil.isNullString(username)) 
        {
            // 保存错误信息到request中, 然后转发到login.jsp中, 提醒登录
            request.setAttribute("Msg", "用户名为空!");
           // 转发到登录页面
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            return;
        }else if(ValidateUtil.isNullString(pwd1)) {
        	// 保存错误信息到request中, 然后转发到login.jsp中, 提醒登录
            request.setAttribute("msg", "密码为空!");
           // 转发到登录页面
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            return;
        }
		
		
		//将获得密码MD5加密
		String pwd = MD5Util.MD5Encode(pwd1, "utf-8");
		user.setUsername(username);
		user.setPassword(pwd);  //将加密的密码放入user对象中
		
		//获得用户的状态量
		int status = us.login(user);
		
		if(status == 0) {  //管理员
			//请求转发到管理员的jsp页面
			request.getRequestDispatcher("/jsp/admin.jsp").forward(request, response);
		}else if(status == 1) {   //普通用户
			
			//登录成功，将用户的username和密码放入session中
			request.getSession().setAttribute("uname", username);
			request.getSession().setAttribute("upwd", pwd);
			
			//取出此用户的全部信息，放入session中
			user = us.userInfo(username);
			request.getSession().setAttribute("userInfo", user);
			System.out.println(user);
			//记住密码的操作
			if(request.getParameter("remember") != null) {  //根据input标签的name属性值取value属性值
				//勾选了记住密码
				//用cookie保存，密码为未加密时密码
				Cookie cookie1 = new Cookie("upwd", pwd1);
				cookie1.setMaxAge(7*60*60*24);  //设置cookie时长为7天，单位为秒
				//放在响应中
				response.addCookie(cookie1);
			}else if(request.getParameter("remember") == null) {  //没有记住密码
				Cookie cookie1 = new Cookie("upwd", "");
				cookie1.setMaxAge(7*60*60*24);  //设置cookie时长为7天，单位为秒
				//放在响应中
				response.addCookie(cookie1);
			}
			
			//自动登录
			if(request.getParameter("auto") != null) {
				Cookie cookie2 = new Cookie("auto", "auto");
				cookie2.setMaxAge(7);
				response.addCookie(cookie2);
			}
			
			//记住用户名自动填充
			Cookie cookie = new Cookie("uname", username);
			cookie.setMaxAge(7*26*60*60); //7天时长
			
			response.addCookie(cookie);
			
			response.sendRedirect(request.getContextPath()+"/jsp/self.jsp");
			
		}else if(status == 2) {  //被封号用户
			// 保存错误信息到request中
			request.setAttribute("msg", "对不起，此用户已被封号");
			//请求转发到登录页面
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
		}else {  //status = 3,登录失败
			request.setAttribute("msg", "登录失败");
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
