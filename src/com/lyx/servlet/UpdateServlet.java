package com.lyx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lyx.entity.User;
import com.lyx.service.UserService;

/**
 * 更新个人信息
 * @author 刘彦享
 *
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    User user = new User();
    UserService us = new UserService();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//获得session中的用户名
		HttpSession session = request.getSession();
		
		//拿到session中的user的全部信息
		user = (User) request.getSession().getAttribute("userInfo");
		//拿到form表中的数据，添加到user对象中
		user.setNickname(request.getParameter("nickname"));
		user.setAddress(request.getParameter("address"));
		user.setSignature(request.getParameter("signature"));
		user.setSelfIntroduction(request.getParameter("selfIntroduction"));
		//修改数据库中的数据
		boolean result = us.updateContent(user);
		//如果修改数据库成功，则查找修改后的user的数据，重新放到session中
		if(result) {
			User user1 = us.userInfo(user.getUsername());
			//将修改后的user信息放到session中
			session.setAttribute("userInfo", user1);
			request.getRequestDispatcher("/jsp/self.jsp").forward(request, response);
		}
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
