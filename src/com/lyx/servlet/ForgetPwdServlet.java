package com.lyx.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyx.entity.User;
import com.lyx.service.UserService;
import com.lyx.util.SendMailUtil;
import com.lyx.util.ValidateUtil;


@WebServlet("/ForgetPwdServlet")
public class ForgetPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	User user = new User();
	UserService us = new UserService();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("uemail");
		String mail = request.getParameter("mail");
		//实例化一个发送邮件的对象
		SendMailUtil  sm = new SendMailUtil();
		//根据邮箱找到用户信息
		user = us.emailInfo(email);
		if(user != null&&email!=null&& ValidateUtil.isNullString(mail)) {                                            
			//设置收件人和消息内容
			//验证码为加密的密码
			request.setAttribute("msg", "验证码已发送");  
			sm.sendMail(email, "微博|你的验证码为："+user.getPassword());
			request.setAttribute("email", email);
			request.getRequestDispatcher("/jsp/forgetPwd.jsp").forward(request, response);
			}
			//如果验证码提交不为空，则判断
			if(mail!=null&&mail!="") {
				//如果验证码和密码相同，证明邮箱正确，可以修改密码
				if(mail == user.getPassword()) {
					request.getRequestDispatcher("/jsp/updatePwd.jsp").forward(request, response);
				}else {
					//验证码错误，发送错误信息到jsp中，重新回到忘记密码界面
					request.setAttribute("msg", "验证码错误");  
					request.getRequestDispatcher("/jsp/forgetPwd.jsp").forward(request, response);
				}
			}
				
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
