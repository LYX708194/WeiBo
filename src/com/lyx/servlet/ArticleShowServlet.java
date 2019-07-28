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

import com.lyx.dao.ArticleEditDao;
import com.lyx.dao.ArticleShowDao;
import com.lyx.dao.UserDao;
import com.lyx.daoImpl.ArticleEditDaoImpl;
import com.lyx.daoImpl.ArticleShowDaoImpl;
import com.lyx.daoImpl.UserDaoImpl;
import com.lyx.entity.Article;
import com.lyx.entity.ArticleLike;
import com.lyx.entity.Collect;
import com.lyx.entity.Comment;
import com.lyx.entity.Page;
import com.lyx.entity.User;
import com.lyx.service.ArticleEditService;
import com.lyx.service.ArticleShowService;
import com.lyx.service.UserService;
import com.lyx.util.DateUtil;
import com.lyx.util.HTMLUtil;

/**
 * 微博文章展示的页面，根据传递的方法判断是展示我的微博还是全部微博
 * @author 刘彦享
 *
 */
@WebServlet("/ArticleShowServlet")
public class ArticleShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	ArticleShowService  ass = new ArticleShowService();
    ArticleEditService aes = new ArticleEditService();
    ArticleShowDao asd = new ArticleShowDaoImpl();
    ArticleEditDao aed = new ArticleEditDaoImpl();
    UserDao ud = new UserDaoImpl();
    UserService us = new UserService();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
		 response.setCharacterEncoding("utf-8");
		 
	     HttpSession session = request.getSession();  //拿到session，获得当前登录用户的信息
	     User user = (User) session.getAttribute("userInfo");  
	     
	     Article article = new Article();
	     int currentPage = 1;
	     //取得前端页面中的当前页的数据
	     String currentPageStr = request.getParameter("currentPage");
		if(currentPageStr == null ||"".equals(currentPageStr)) {
			currentPage = 1;
		}else {
			//将前端收到的当前页的数据转化为int类型赋给当前页
			currentPage = Integer.parseInt(currentPageStr);
		}
		
	
		
		
		
		//获得请求的premethod，即文章的收藏转发点赞和删除
		String preMethod = request.getParameter("premethod");
		if(preMethod!=null) {
			
			int articleId = Integer.parseInt(request.getParameter("articleId"));  //将得到的文章id转化为int
			article.setArticleId(articleId);
			switch(preMethod) {
			case "delete" :{  //删除文章，把与该文章有关的评论收藏都删除
					ArticleLike like = new ArticleLike();
					like.setArticleId(articleId);
					like.setUsername(user.getUsername());
					Collect collect = new Collect();
					collect.setArticleId(articleId);
					collect.setUsername(user.getUsername());
					Comment comment = new Comment();
					comment.setArticleId(article.getArticleId());
					
					aes.deleteLike(like, user.getUsername(), article);//删除点赞	
					aes.deleteCollect(collect, user.getUsername(), article);  //删除收藏
					aes.deleteComment(comment, article);   //删除评论
					
					aes.articleDelete(article);		//删除文章
			}	break;
			case "like" :{   //点赞
					ArticleLike like = new ArticleLike();
					like.setArticleId(articleId);
					like.setUsername(user.getUsername());
					if(aed.likeQuery(article, user.getUsername())) { //查询是否点赞过
						//已点赞,点击取消点赞
						aes.deleteLike(like, user.getUsername(), article);//取消点赞	
					}else {  //还未点赞
						aes.insertLike(like, user.getUsername(), article); //点赞
					}
	
			}	break;
			case "collect" :{  //收藏
				Collect collect = new Collect();
				collect.setArticleId(articleId);
				collect.setUsername(user.getUsername());
				//查询该文章是否收藏过
				if(aed.collectQuery(article, user.getUsername())) {  //已被收藏，则点击取消收藏				
					aes.deleteCollect(collect, user.getUsername(), article);   //取消收藏				
				}else {  //未被收藏，则点击收藏
					aes.insertCollect(collect, user.getUsername(), article) ;//收藏			
				}		
				
			}	break;
			case "comment":{
				String commentMsg = request.getParameter("commentMsg");
				Comment comment = new Comment();
				System.out.println(commentMsg);
				comment.setArticleId(articleId);
				comment.setCommentMsg(commentMsg);
				comment.setUsername(user.getUsername());
				comment.setCommentTime(DateUtil.getDateToSecond());
				boolean rs = aes.insertComment(comment, article);
				if(rs) {
					request.setAttribute("commentSuccess", "评论成功");
				}else {
					request.setAttribute("commentSuccess", "评论失败");
				}	
			}	break;
			case "star":{  //设为热搜
				int star = aed.queryStar(article);
				if(star==1) {  
					//星标为1，则为取消热搜操作
					aes.downArticle(article);
				}
				if(star==0) {	
					//星标为0，则设置为热搜
					aes.upArticle(article);
				}
			}	break;
			default: break;
			}	
			
		}
		//得到请求的method值
		String method = request.getParameter("method");
		//重新放回request域中，方便分页操作
		request.setAttribute("method", method);
		if(method !=null) {
			switch (method) {
			case "my":{		//展示我的所有微博
				int totalCount = asd.getMyTotalCount(user);
				if(totalCount==0) {
					request.setAttribute("msg", "对不起，你还没有发过微博");
				}else {
					Page page = new Page(5, totalCount, currentPage);//默认将页面大小设置为5
					page.setTotalCount(totalCount);
					page.setTotalPage(totalCount, 5);
					page.setCurrentPage(currentPage);           					
					//拿到数据集合，分页
					List<Article> articleList = new ArrayList<Article>();
					articleList = ass.queryMyArticleByPage(page, user);
					
					page.setObject(articleList);
					//将数据传给request
					request.setAttribute("p", page);
				}
				
				
			}break;
			case "searchMy":{   //根据搜索字段搜索我自己的微博
				String search = request.getParameter("searchComtent");
				int totalCount = asd.getArticleSearchCount(search);  //得到搜索得到的数据总条数
				if(totalCount==0) {
					request.setAttribute("msg", "对不起，没有找到你想要的东西");
				}else {
					Page page = new Page(5, totalCount, currentPage);
					page.setTotalCount(totalCount);
					page.setTotalPage(totalCount, 5);
					page.setCurrentPage(currentPage);
					//拿到数据集合，分页
					List<Article> articleList = new ArrayList<Article>();
					articleList = ass.queryMySearchArticleByPage(page, user.getUsername(), HTMLUtil.HTMLEncod(search));
					page.setObject(articleList);
					//将数据传给request
					request.setAttribute("p", page);
				}	
					
			}break;
			case "all":{   //根据全部的微博
				int totalCount = asd.getTotalConut();         //得到所有的微博条数
				if(totalCount==0) {
					request.setAttribute("msg", "对不起，没有找到你想要的东西");
				}else {
					Page page = new Page(5, totalCount, currentPage);
					page.setTotalCount(totalCount);
					page.setCurrentPage(currentPage);
					page.setTotalPage(totalCount, 5);
					List<Article> articleList = new ArrayList<Article>();
					articleList = ass.queryAllArticleByPage(page,user.getUsername());
					page.setObject(articleList);
					request.setAttribute("p", page);
				}
						
			}break;
			case "searchAll":{   //根据搜索字段搜索全部微博
				String search = request.getParameter("searchComtent");   //得到所有符合搜索条件的微博条数
				int totalCount = asd.getArticleSearchCount(search);
				if(totalCount==0) {
					request.setAttribute("msg", "对不起，没有找到你想要的东西");
				}else {
					Page page = new Page(5, totalCount, currentPage);
					page.setTotalCount(totalCount);
					page.setCurrentPage(currentPage);
					page.setTotalPage(totalCount, 5);
					page.setPageSize(5);                                        					//默认将页面大小设置为5
					//拿到数据集合，分页
					List<Article> articleList = new ArrayList<Article>();
					articleList = ass.queryAllSearchArticleByPage(page, HTMLUtil.HTMLEncod(search),user.getUsername());
					page.setObject(articleList);
					//将数据传给request
					request.setAttribute("p", page);
				}				
								
			}break;
			case "showMyCollect":{  //查找我的全部收藏文章并分页输出
				int totalCount = asd.getMyCollectCount(user);
				if(totalCount==0) {
					request.setAttribute("msg", "你还没有收藏文章");
				}else {
					Page page = new Page(5, totalCount, currentPage);
					page.setTotalCount(totalCount);
					page.setCurrentPage(currentPage);
					page.setPageSize(5);                                        					//默认将页面大小设置为5
					//拿到数据集合，分页
					List<Article> articleList = new ArrayList<Article>();
					articleList = ass.queryMyCollect(page, user);
					
					page.setObject(articleList);
					//将数据传给request
					request.setAttribute("p", page);
				}
				
			}break;
			case "other":{   //搜索其他人的微博
				String username = request.getParameter("thisUser");
				User user1 = new User();
				user1 = ud.userInfo(username);
				int relation = us.getRelation(user.getUsername(), username);  //得到两人关系
				user1.setRelation(relation);
				request.setAttribute("user1", user1);  //将此微博的作者的个人信息放入request域中，通过点击可以查看他的个人信息
				
				
				int totalCount = asd.getMyTotalCount(user1);
				if(totalCount==0) {
					request.setAttribute("msg", "对不起，他还没有发过微博");
				}else {
					Page page = new Page(5, totalCount, currentPage);//默认将页面大小设置为5
					page.setTotalCount(totalCount);
					page.setTotalPage(totalCount, 5);
					page.setCurrentPage(currentPage);           					
					//拿到数据集合，分页
					List<Article> articleList = new ArrayList<Article>();
					articleList = ass.queryMyArticleByPage(page, user1);
					
					page.setObject(articleList);
					//将数据传给request
					request.setAttribute("p", page);
				} 
			}	break;
			case "star":{		//搜索热搜文章
				int totalCount = asd.getStarArticleCount();
				if(totalCount==0) {
					request.setAttribute("msg", "暂时还没有热搜微博");
				}else {
					Page page = new Page(5, totalCount, currentPage);
					
					List<Article> stars = new ArrayList<Article>();
					stars = ass.queryStarArticle(page, user);
					page.setObject(stars);
					request.setAttribute("p", page);
				}
				
			} 	break;

			default:   break;
			}
			
		}
		request.getRequestDispatcher("/jsp/articleShow.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
