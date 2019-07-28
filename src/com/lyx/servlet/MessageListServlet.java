package com.lyx.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lyx.entity.User;
import com.lyx.service.MessageShowService;



@WebServlet("/MessageListServlet")
public class MessageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    MessageShowService mss = new MessageShowService();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		//设置编码格式
		 request.setCharacterEncoding("utf-8");
		 response.setCharacterEncoding("utf-8");
		
		 HttpSession session = request.getSession();  //拿到session，获得当前登录用户的信息
	     User user = (User) session.getAttribute("userInfo");  
		
	     List<User> users = new ArrayList<User>();
	     users = mss.getMsgUser(user.getUsername());   //获得与正在登录的用户的消息记录
	     
	     String method = request.getParameter("method");
	     
	     if("sendAll".equals(method)) {
	    	 String messageComtent = request.getParameter("sendmsg");
	    	 
	    	if( mss.sendMsgToAll(user.getUsername(), messageComtent)) {
	    		request.setAttribute("sendMsg", "群发消息成功");
	    	}else {
	    		request.setAttribute("sendMsg", "群发消息失败");
	    	}
	    	 
	     }
	     
	     if(users.isEmpty()) {   //如果没有消息记录，则发送提示消息
	    	 request.setAttribute("msg", "你暂时还没有消息哦");
	     }
	     
	     request.setAttribute("users", users);  //将对象集合放进request域中
		
		request.getRequestDispatcher("/jsp/messageList.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
