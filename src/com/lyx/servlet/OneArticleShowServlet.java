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
import com.lyx.dao.CommentShowDao;
import com.lyx.daoImpl.ArticleEditDaoImpl;
import com.lyx.daoImpl.ArticleShowDaoImpl;
import com.lyx.daoImpl.CommentShowDaoImpl;
import com.lyx.entity.Article;
import com.lyx.entity.ArticleLike;
import com.lyx.entity.Collect;
import com.lyx.entity.Comment;
import com.lyx.entity.Page;
import com.lyx.entity.Reply;
import com.lyx.entity.User;
import com.lyx.service.ArticleEditService;
import com.lyx.service.CommentShowService;
import com.lyx.util.DateUtil;
import com.lyx.util.ValidateUtil;

/**
 * 单篇文章的展示，可进行查看和评论的操作
 * @author 刘彦享
 *
 */
@WebServlet("/OneArticleShowServlet")
public class OneArticleShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	ArticleEditService aes = new ArticleEditService();
	ArticleShowDao asd = new ArticleShowDaoImpl();
    ArticleEditDao aed = new ArticleEditDaoImpl();
    CommentShowDao csd = new CommentShowDaoImpl();
    CommentShowService css = new CommentShowService();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
		 response.setCharacterEncoding("utf-8");
		 
		 HttpSession session = request.getSession();  //拿到session，获得当前登录用户的信息
	     User user = (User) session.getAttribute("userInfo");  
		 
		String articleIdStr = request.getParameter("articleId");
		 //将传来的值转化为int类型
		int articleId = Integer.parseInt(articleIdStr); 
		//通过传来的articleid获得整篇文章的信息
		Article article = asd.getArticleByArticleId(articleId); 
		
		if(aed.likeQuery(article, user.getUsername())) {  //查询是否点过赞
			article.setLike(true);
		}else {
			article.setLike(false);  
		}
		if(aed.collectQuery(article, user.getUsername())) { //查询是否收藏过
			article.setCollect(true);
		}else {
			article.setCollect(false);
		}
		
		//将获得的article对象放入request域中
		request.setAttribute("thisArticle", article);   			
		
		 int currentPage = 1;
	     //取得前端页面中的当前页的数据
	     String currentPageStr = request.getParameter("currentPage");
		if(currentPageStr == null ||"".equals(currentPageStr)) {
			currentPage = 1;
		}else {
			//将前端收到的当前页的数据转化为int类型赋给当前页
			currentPage = Integer.parseInt(currentPageStr);
		}
		
		//得到预处理请求，如点赞收藏评论删除
	    String preMethod = request.getParameter("premethod");
	    //如果预处理方法不为空
	    if(preMethod!=null) {
	    	
	    	switch (preMethod) {
			case "like":{  //点赞
				ArticleLike like = new ArticleLike();
				like.setArticleId(articleId);
				like.setUsername(user.getUsername());
				if(aed.likeQuery(article, user.getUsername())) {	//查询是否点赞过
					//已点赞,点击取消点赞
					aes.deleteLike(like, user.getUsername(), article);
					
				}else {		
					//还未点赞,则点击进行点赞操作
					aes.insertLike(like, user.getUsername(), article); 
					
				}
			}	break;
			case "comment":{			//评论
				
				String commentMsg = request.getParameter("commentMsg");
				Comment comment = new Comment();
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
				
			}break;
			case "collect":{  //收藏
				Collect collect = new Collect();
				collect.setArticleId(articleId);
				collect.setUsername(user.getUsername());
				//查询该文章是否收藏过
				if(aed.collectQuery(article, user.getUsername())) {  
					//已被收藏，则点击取消收藏				
					aes.deleteCollect(collect, user.getUsername(), article);  
				}else {  //未被收藏，则点击收藏
					aes.insertCollect(collect, user.getUsername(), article) ;
				}		
				
			}	break;
			case "delete":{		//删除微博，同时删除所有相关的东西
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
			case "reply":{  //回复评论
				//获得表单传值的信息
				System.out.println(123);
				String replyUser = request.getParameter("replyuser");
				String beReplyUser = request.getParameter("bereplyuser");
				String replyMsg1 = request.getParameter("replyMsg1");
				String replyMsg2 = request.getParameter("replyMsg2");
				String commentIdStr = request.getParameter("commentid");
				int commentId = Integer.parseInt(commentIdStr);  //将commentid转化为int类型
				
				Reply reply = new Reply();
				reply.setArticleId(articleId);
				
				if(ValidateUtil.isNullString(replyMsg1)) {
					if(!ValidateUtil.isNullString(replyMsg2)) {
						reply.setReplyMsg(replyMsg2);
					}
				}else {
					reply.setReplyMsg(replyMsg1);
				}
				
				reply.setReplyUsername(replyUser);
				reply.setBeReplyUsername(beReplyUser);
				reply.setCommentId(commentId);
				reply.setReplyTime(DateUtil.getDateToSecond());
				
				aes.insertReply(reply, article);
				
				
			}	break;
			case "deletecomment":{   //删除评论
				String commentIdStr = request.getParameter("commentid");
				int commentId = Integer.parseInt(commentIdStr);  //将commentid转化为int类型
				
				Comment comment = new Comment();
				comment.setCommentId(commentId);
				Reply reply = new Reply();
				reply.setCommentId(commentId);
				
				
				aes.deleteReply(reply, article);
				
				aes.deleteComment(comment, article);
			}	break;
			case "deletereply":{   //删除回复
				String commentIdStr = request.getParameter("commentid");
				String replyIdStr = request.getParameter("replyid");
				int commentId = Integer.parseInt(commentIdStr);  //将commentid转化为int类型
				int replyId = Integer.parseInt(replyIdStr);
				
				Reply reply = new Reply();
				reply.setCommentId(commentId);
				reply.setReplyId(replyId);
				
				aes.deleteReply(reply, article);
				
			}	break;

			default:	break;
			}	
	    }
	     
	    //根据id获得总评论数
	    int totalCount = csd.getCommentCount(articleId);
	    Page page = new Page(10, totalCount, currentPage);  //构造器，每页10个评论
	    
	    List<Comment> comments = css.getCommentByPage(page, articleId);
		
		List<Comment> commentsWithReplys = new ArrayList<Comment>();
		
		//使用循环将每个评论的回复添加到该评论对象中去
		for (Comment comment : comments) {
			int commentId = comment.getCommentId();
			comment.setReplys(css.getReplyByComment(commentId));
			commentsWithReplys.add(comment);
		}
		
		
		page.setComments(commentsWithReplys);
		//将数据传给request
		request.setAttribute("p", page);  
	
	
		
		request.getRequestDispatcher("/jsp/oneShow.jsp").forward(request, response);
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
