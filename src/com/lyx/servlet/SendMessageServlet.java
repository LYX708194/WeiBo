package com.lyx.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lyx.dao.MessageDao;
import com.lyx.daoImpl.MessageDaoImpl;
import com.lyx.entity.Message;
import com.lyx.entity.User;
import com.lyx.service.MessageShowService;
import com.lyx.service.UserService;


@WebServlet("/SendMessageServlet")
public class SendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    MessageShowService mss = new MessageShowService();
    MessageDao md = new MessageDaoImpl();
    UserService us = new UserService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式
		 request.setCharacterEncoding("utf-8");
		 response.setCharacterEncoding("utf-8");
		
		 HttpSession session = request.getSession();  //拿到session，获得当前登录用户的信息
	     User user = (User) session.getAttribute("userInfo");  
		
	     String thisUser = request.getParameter("thisUser");
	    
		if(thisUser != null) {
			//得到此对象的信息并放入request域中
			User  receiveUser = us.userInfo(thisUser);
			request.setAttribute("thisUser", receiveUser);
					
		
			String method = request.getParameter("method");
			if("send".equals(method)) {
				String sendMsg = request.getParameter("sendmsg");
				Message message = new Message();
				message.setMessageComtent(sendMsg);
				message.setSendUser(user.getUsername());
				message.setReceiveUser(thisUser);
				
				boolean result = mss.sendMessage(message);
				if(result) {				
					request.setAttribute("sMsg", "发送消息成功");
				}else {
					request.setAttribute("sMsg", "发送消息失败");
				}
			}
			
			Message msg = new Message();
			//为对方发送的消息设置为已读，即对方为发送者，我为接收者
			msg.setSendUser(thisUser);   
			msg.setReceiveUser(user.getUsername());
			//搜索是否有未读消息
			if(md.getUnReadCount(msg)!=0) {
				//有未读消息，将两人之间的未读消息改为已读
				mss.updateRead(msg);				
				
			}
			
			
			
			
			//获得两人间的消息并放入request域中
			List<Message> msgs = mss.getMsgByTwo(user.getUsername(), thisUser);
			if(msgs.isEmpty()) {
				request.setAttribute("msg", "你和ta还没有发过信息");
			}
//			System.out.println(msgs);
			request.setAttribute("msgList", msgs);
			
			
					
		}
		
		request.getRequestDispatcher("/jsp/twoShow.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
