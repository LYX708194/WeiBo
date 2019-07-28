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

import com.lyx.dao.UserShowDao;
import com.lyx.daoImpl.UserShowDaoImpl;
import com.lyx.entity.Page;
import com.lyx.entity.User;
import com.lyx.service.UserService;
import com.lyx.service.UserShowService;
import com.lyx.util.HTMLUtil;

/**
 * 用户的展示，可进行关注/取消关注的操作，管理员可进行封号的操作
 * @author 刘彦享
 *
 */
@WebServlet("/UserShowServlet")
public class UserShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    UserService us = new UserService();
    UserShowDao usd = new UserShowDaoImpl();
	UserShowService uss = new UserShowService();
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式
		 request.setCharacterEncoding("utf-8");
		 response.setCharacterEncoding("utf-8");
		 
		 HttpSession session = request.getSession();  //拿到session，获得当前登录用户的信息
	     User user = (User) session.getAttribute("userInfo");  
		
	     int currentPage = 1;
	     //取得前端页面中的当前页的数据
	     String currentPageStr = request.getParameter("currentPage");
		if(currentPageStr == null ||"".equals(currentPageStr)) {
			currentPage = 1;
		}else {
			//将前端收到的当前页的数据转化为int类型赋给当前页
			currentPage = Integer.parseInt(currentPageStr);
		}
		
		String back = request.getParameter("back");
		

		//获得请求的premethod，即用户的关注和取消关注操作
		String preMethod = request.getParameter("premethod");
		String toUsername = request.getParameter("thisUser"); //获得传参
		if(preMethod!=null) {
			
			switch(preMethod) {
			case "focus":{   //关注操作
				if(us.insertFriend(user.getUsername(), toUsername)) {  //关注成功
					request.setAttribute("Msg", "关注成功");
				}else {  //关注失败
					request.setAttribute("Msg", "关注失败");
				}
			}break;
			case "unfocus":{   //取消关注操作
				if(us.deleteFocus(user.getUsername(), toUsername)) {	//取消关注成功
					request.setAttribute("Msg", "取消关注成功");
				}else{	//取消关注失败
					request.setAttribute("Msg", "取消关注失败");
				}
	
			}break;
			case "black":{  //封号
				if(us.updateUserStatus(toUsername, 2)) {
					request.setAttribute("Msg", "封号成功");
				}else {
					request.setAttribute("Msg", "封号失败");
				}
			} break;
			case "out":{  //解除封号
				if(us.updateUserStatus(toUsername, 1)) {
					request.setAttribute("Msg", "解除封号成功");
				}else {
					request.setAttribute("Msg", "解除封号成功");
				}
			} break;
			default: break;
			}

		}
		
		//得到请求的method值
		String method = request.getParameter("method");
		//重新放回request域中，方便分页操作
		request.setAttribute("method", method);
		
		if(method != null) {
			switch(method) {
			case "all":{
				int totalCount = usd.getAllUserCount();
				if(totalCount==0) {
					request.setAttribute("msg", "对不起，没有找到你要找的用户");
				}else {
					Page page = new Page(10, totalCount, currentPage);  //设置页面大小为10个人
					//获得user并分页
					List<User> users = new ArrayList<User>();
					users = uss.getAllUserByPage(page, user.getUsername());
					//加入对象中
					page.setUsers(users);
					//将数据传给request
					request.setAttribute("p", page);
				}
				
			}break;
			case "myfocus":{
				int totalCount = usd.getMyFocusCount(user);
				if(totalCount==0) {
					request.setAttribute("msg", "对不起，没有找到你要找的用户");
				}else {
					Page page = new Page(10, totalCount, currentPage);  //设置页面大小为10个人
					//获得user并分页
					List<User> users = new ArrayList<User>();
					users = uss.getMyFocusByPage(page, user);
					//加入对象中
					page.setUsers(users);
					//将数据传给request
					request.setAttribute("p", page);
				}
			} break ;
			case "myfans":{
				int totalCount = usd.getMyFansCount(user.getUsername());
				if(totalCount==0) {
					request.setAttribute("msg", "对不起，没有找到你要找的用户");
				}else {
					Page page = new Page(10, totalCount, currentPage);  //设置页面大小为10个人
					//获得user并分页
					List<User> users = new ArrayList<User>();
					users = uss.getMyFansByPage(page, user);
					//加入对象中
					page.setUsers(users);
					//将数据传给request
					request.setAttribute("p", page);
				}
				
			}break;
			case "mysearch":{
				String searchMsg = request.getParameter("searchUser");
				int totalCount = usd.getSearchUserCount(searchMsg);
				if(totalCount==0) {
					request.setAttribute("msg", "对不起，没有找到你要找的用户");
				}else {
					Page page = new Page(10, totalCount, currentPage);  //设置页面大小为10个人
					//获得user并分页
					List<User> users = new ArrayList<User>();
					users = uss.getSearchUserByPage(page, user, HTMLUtil.HTMLEncod(searchMsg));
					//加入对象中
					page.setUsers(users);
					//将数据传给request
					request.setAttribute("p", page);
				}
			}break;
			
			default: break;
			}		
			
		}
		if(back=="article") {  //如果是在article页面做关注操作，返回该页面
			request.getRequestDispatcher("/jsp/articleShow.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/jsp/userShow.jsp").forward(request, response);
		}
		
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
