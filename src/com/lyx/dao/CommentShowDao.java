package com.lyx.dao;

import java.util.List;

import com.lyx.entity.Comment;
import com.lyx.entity.Page;
import com.lyx.entity.Reply;

public interface CommentShowDao {
		
		/**
		 * 根据文章id获得评论数
		 * @param articleId
		 * @return
		 */
		public int getCommentCount(int articleId);
		
		/**
		 * 根据评论id获得该评论的回复数
		 * @param commentId
		 * @return
		 */
		public int getReplyCount(int commentId);
		
		/**
		 * 通过文章id获得该文章的评论
		 * @param articleId
		 * @return 
		 */
		public List<Comment> getCommentByPage(int articleId,Page page);
		
		/**
		 * 通过评论id找到该评论的回复
		 * @param commentId
		 * @return
		 */
		public List<Reply> getReplyByCommentId(int commentId);
		
		
		
		
}
