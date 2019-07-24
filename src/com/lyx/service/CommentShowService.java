package com.lyx.service;

import java.util.List;

import com.lyx.dao.ArticleEditDao;
import com.lyx.dao.CommentShowDao;
import com.lyx.daoImpl.ArticleEditDaoImpl;
import com.lyx.daoImpl.CommentShowDaoImpl;
import com.lyx.entity.Comment;
import com.lyx.entity.Page;
import com.lyx.entity.Reply;

public class CommentShowService {

	CommentShowDao csd = new CommentShowDaoImpl();
	ArticleEditDao aed = new ArticleEditDaoImpl();
	
	
	/**
	 * 根据文章id获得该文章的评论
	 * @param page
	 * @param articleId
	 * @return 成功返回一个对象，失败返回空
	 */
	public List<Comment> getCommentByPage(Page page,int articleId){
		page.setTotalCount(csd.getCommentCount(articleId));
		if(page.getCurrentPage()!=0) {
			return csd.getCommentByPage(articleId, page);
		}
		return null;
	}
	
	/**
	 * 根据评论id获得该评论的回复
	 * @param commentId
	 * @return 成功返回一个对象，失败返回空
	 */
	public List<Reply> getReplyByComment(int commentId){
		if(aed.queryComment(commentId)) {   
			return csd.getReplyByCommentId(commentId);
		}
		return null;
	}
	
	
}
