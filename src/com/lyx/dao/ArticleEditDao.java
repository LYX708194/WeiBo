package com.lyx.dao;

import com.lyx.entity.Article;
import com.lyx.entity.ArticleLike;
import com.lyx.entity.Collect;

/**
 * 编辑微博文章的dao层的接口处理
 * @author 刘彦享
 *
 */
public interface ArticleEditDao {
		/**
		 * 向数据库中增加微博文章
		 * @param article
		 * @return 成功返回真，失败返回假
		 */
		public boolean articleInsert(Article article);
		/**
		 * 删除一条微博文章
		 * @param article
		 * @return 成功返回真，失败返回假
		 */
		public boolean articleDelete(Article article);
		/**
		 * 查询文章是否存在
		 * @param article
		 * @return 若存在返回真，不存在返回假
		 */
		public boolean articleQuery(Article article);
		
		/**
		 * 给微博点赞的处理,在article表中
		 * @param article
		 * @param like
		 * @return 成功返回真，失败返回假
		 */
		public boolean articleLike(Article article);
		
		/**
		 * 给微博取消点赞的处理,在article表中
		 * @param article
		 * @return 成功返回真，失败返回假
		 */
		public boolean articleUnLike(Article article);
		
		/**
		 * 给微博点赞的处理,在articlelike表中插入点赞数据
		 * @param like
		 * @return 成功返回真，失败返回假
		 */
		public boolean insertLike(ArticleLike like);
		
		/**
		 * 给微博取消点赞的处理,在articlelike表中删除点赞数据
		 * @param like
		 * @return 成功返回真，失败返回假
		 */
		public boolean deleteLike(ArticleLike like,String username);
		
		/**
		 *查询是否点赞过 
		 * @param article
		 * @param username
		 * @return 点过赞返回真，没有返回假
		 */
		public boolean likeQuery(Article article,String username);
		
		/**
		 * 给微博收藏的处理,在article表中
		 * @param article
		 * @return 成功返回真，失败返回假
		 */
		public boolean articleCollect(Article article);
		
		/**
		 * 给微博取消收藏的处理,在article表中
		 * @param article
		 * @return 成功返回真，失败返回假
		 */
		public boolean articleUnCollect(Article article);
		
		/**
		 *查询是否收藏过 
		 * @param article
		 * @param username
		 * @return 收藏过返回真，没有返回假
		 */
		public boolean collectQuery(Article article,String username);
		
		/**
		 * 给微博收藏的处理,在collect表中插入点赞数据
		 * @param like
		 * @return 成功返回真，失败返回假
		 */
		public boolean insertCollect(Collect collect);
		
		/**
		 * 给微博取消收藏的处理,在collect表中删除收藏数据
		 * @param like
		 * @return 成功返回真，失败返回假
		 */
		public boolean deleteCollect(Collect collect,String username);
		
		
		
		
		
}
