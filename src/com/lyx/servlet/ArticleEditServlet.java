package com.lyx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyx.entity.Article;
import com.lyx.entity.User;
import com.lyx.service.ArticleEditService;
import com.lyx.util.DateUtil;
import com.lyx.util.ValidateUtil;

/**
 * Servlet implementation class ArticleEditServlet
 */
@WebServlet("/ArticleEditServlet")
public class ArticleEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ArticleEditService aes = new ArticleEditService();   
    Article article = new Article();
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			
			//拿到session中的user的全部信息
			User user = (User) request.getSession().getAttribute("userInfo");
			
			//获得微博内容
			String comtent = request.getParameter("comtent");
			//获得照片路径
			String photo = request.getParameter("photo");
			//获得当前发表时间
			String publicedTime = DateUtil.getDateToSecond();
			System.out.println(comtent);
			//如果作者和发表内容不为空
			if(!ValidateUtil.isNullString(user.getUsername())&&!ValidateUtil.isNullString(comtent)) {
				
				article.setAuthor(user.getUsername());
				article.setAuthorNickname(user.getNickname());
				article.setAuthorportrait(user.getPortrait());
				article.setComtent(comtent);
				article.setPhoto(photo);
				article.setPublicedTime(publicedTime);
				System.out.println(article);
				
				boolean result = aes.articleInsert(article);
				System.out.println(result);
				if(result) {  //如果成功增加微博
					request.setAttribute("msg", "发表成功！");
				}else {
					
					request.setAttribute("msg", "发表失败！");
				}
				request.getRequestDispatcher("/jsp/articleEdit.jsp").forward(request, response);
			}else {
				request.setAttribute("msg", "发表失败！");
				request.getRequestDispatcher("/jsp/articleEdit.jsp").forward(request, response);
			}
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
