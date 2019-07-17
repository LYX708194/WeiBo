package com.lyx.service;

import com.lyx.dao.ArticleEditDao;
import com.lyx.daoImpl.ArticleEditDaoImpl;
import com.lyx.entity.Article;
import com.lyx.entity.ArticleLike;
import com.lyx.entity.Collect;

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
			if(aed.articleQuery(article)) {
				return  aed.articleDelete(article);  //查到已有此文章，进行删除
			}else {
				return false;  //查不到这篇文章，删除失败
			}
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
			}else {
				return false;
			}
			return false;
		}
		
}
