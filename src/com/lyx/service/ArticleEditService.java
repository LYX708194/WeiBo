package com.lyx.service;

import com.lyx.dao.ArticleEditDao;
import com.lyx.daoImpl.ArticleEditDaoImpl;
import com.lyx.entity.Article;
import com.lyx.entity.ArticleLike;
import com.lyx.entity.Collect;
import com.lyx.entity.Comment;
import com.lyx.entity.Reply;

/**
 * 编辑微博文章的逻辑处理
 *  * @author 刘彦享
 *
 */
public class ArticleEditService {
		
		
		ArticleEditDao aed = new ArticleEditDaoImpl();
		
		/**
		 * 增加微博文章的逻辑处理
		 * @param article
		 * @return 成功返回真，失败返回假
		 */
		public boolean articleInsert(Article article) {
			if(aed.articleQuery(article)) {
				return false;   //查到已有此文章，插入失败
			}else {
				return aed.articleInsert(article);
			}	
		}
		
		/**
		 * 删除微博文章的逻辑处理
		 * @param article
		 * @return 成功返回真，失败返回假
		 */
		public boolean articleDelete(Article article) {
			if(aed.articleQuery(article)) {  //删除文章则将文章的评论的点赞收藏信息都删除
				Comment comment = new Comment();
				Reply reply = new Reply();
				comment.setArticleId(article.getArticleId());
				reply.setArticleId(article.getArticleId());
				aed.deleteComment(comment);
				aed.articleDeleteComment(article);
				aed.deleteReply(reply);
				return  aed.articleDelete(article);  //查到已有此文章，进行删除
			}else {
				return false;  //查不到这篇文章，删除失败
			}
		}
		
		/**
		 * 设置为热搜
		 * @param article
		 * @return 成功返回真，失败返回假
		 */
		public boolean upArticle(Article article) {
			if(aed.articleQuery(article)) {
				return aed.upArticle(article);
			}
			return false;
		}
		
		/**
		 * 取消设置热搜
		 * @param article
		 * @return 成功返回真，失败返回假
		 */
		public boolean downArticle(Article article) {
			if(aed.articleQuery(article)) {
				return aed.downArticle(article);
			}
			return false;
		}
		
		/**
		 * 给微博点赞的逻辑处理
		 * @param like 
		 * @param username 点赞的用户
		 * @param article 
		 * @return 成功返回真，失败返回假
		 */
		public boolean insertLike(ArticleLike like,String username,Article article) {
			if(aed.likeQuery(article, username)) {
				return false;   //如果存在此点赞，失败
			}else {
				if(aed.articleLike(article)&&aed.insertLike(like)) {//更新点赞数和插入点赞表信息
						return true;  
				}
			}
			return false;    
		}
		
		/**
		 * 取消点赞的逻辑处理
		 * @param like
		 * @param username
		 * @param article
		 * @return 成功返回真，失败返回假
		 */
		public boolean deleteLike(ArticleLike like,String username,Article article) {
			if(aed.likeQuery(article, username)) { //如果存在此点赞，则进行操作
				if(aed.articleUnLike(article)&&aed.deleteLike(like, username)) {
					return true;   //更新点赞数和删除点赞表信息
				}
			}else {
				return false;
			}
			return false;
		}
		
		/**
		 * 给微博收藏的逻辑处理
		 * @param collect 
		 * @param username 点赞的用户
		 * @param article 
		 * @return 成功返回真，失败返回假
		 */
		public boolean insertCollect(Collect collect,String username,Article article) {
			if(aed.collectQuery(article, username)) {  //查询是否收藏过
				return false;   //如果存在此收藏，失败
			}else {
				if(aed.articleCollect(article)&&aed.insertCollect(collect)) {//更新收藏数和插入收藏表信息
						return true;  
				}
			}
			return false;    
		}
		
		
		/**
		 * 取消收藏的逻辑处理
		 * @param like
		 * @param username
		 * @param article
		 * @return 成功返回真，失败返回假
		 */
		public boolean deleteCollect(Collect collect,String username,Article article) {
			if(aed.collectQuery(article, username)) { //查询是否收藏过
				if(aed.articleUnCollect(article)&&aed.deleteCollect(collect, username)) {
					return true;   //更新收藏数和删除收藏表信息
				}
			}
			return false;
		}
		
		/**
		 * 对微博执行评论的操作，分别向两个表中增加信息
		 * @param comtent
		 * @param artilce
		 * @return 成功返回真，失败返回假
		 */
		public boolean insertComment(Comment comment,Article artilce ) {
			if(aed.insertComment(comment)) {
				
				return aed.articleComment(artilce);
			}			
			return false;
		}
		
		/**
		 * 删除评论的操作，对两个表进行评论的删除和更新处理
		 * @param comtent
		 * @param artilce
		 * @return 成功返回真，失败返回假
		 */
		public boolean deleteComment(Comment comment,Article article) {
			if(aed.queryComment(comment.getCommentId())) {
				if(aed.deleteComment(comment)) {
					return aed.articleDeleteComment(article);
				}
			}
			return false;
		}
		
		/**
		 * 插入回复并增加评论数
		 * @param reply
		 * @param article
		 * @return 成功返回真，失败返回假
		 */
		public boolean insertReply(Reply reply,Article article) {
			if(aed.insertReply(reply)&&aed.articleComment(article)) {
				return true;
			}
			return false;
		}
		
		/**
		 * 删除回复并较少评论数
		 * @param reply
		 * @param article
		 * @return 成功返回真，失败返回假
		 */
		public boolean deleteReply(Reply reply,Article article) {
			if(aed.queryReply(reply)) {
				if(aed.deleteReply(reply)&&aed.articleUnComment(article)) {
					return true;
				}
			}
			return false;
		}
		
		
		
}
