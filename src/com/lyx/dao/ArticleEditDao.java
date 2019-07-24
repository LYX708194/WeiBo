package com.lyx.dao;

import com.lyx.entity.Article;
import com.lyx.entity.ArticleLike;
import com.lyx.entity.Collect;
import com.lyx.entity.Comment;
import com.lyx.entity.Reply;

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
		 * 查询文章的星标状态
		 * @param article
		 * @return 	返回该文章的星标状态
		 */
		public int queryStar(Article article);
		
		/**
		 * 设置为热搜，改变star状态
		 * @param article
		 * @return 成功返回真，失败返回假
		 */
		public boolean upArticle(Article article);
		
		/**
		 * 取消热搜，改变star状态
		 * @param article
		 * @return 成功返回真，失败返回假
		 */
		public boolean downArticle(Article article);
		
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
		
		/**
		 * 给微博评论的处理,在article表中，即增加评论数
		 * @param article
		 * @return 成功返回真，失败返回假
		 */
		public boolean articleComment(Article article);
		
		/**
		 * 删除微博评论的操作，在article表中，即减少评论数
		 * @param article
		 * @return 成功返回真，失败返回假
		 */
		public boolean articleUnComment(Article article);
		
		/**
		 * 给微博增加评论的功能,在comment表中插入数据
		 * @param comment
		 * @return 成功返回真，失败返回假
		 */
		public boolean insertComment(Comment comment);
		
		/**
		 * 删除评论的功能，在comment表中删除数据
		 * @param comment
		 * @param username
		 * @return 成功返回真，失败返回假
		 */
		public boolean deleteComment(Comment comment);
		
		/**
		 * 在article表中更新评论数，删除评论的操作
		 * @param article
		 * @return 成功返回真，失败返回假
		 */
		public boolean articleDeleteComment(Article article);
		
		/**
		 * 在comment表中搜索是否存在此评论。通过commentid搜索
		 * @param comment
		 * @return 若存在返回真，不存在返回假
		 */
		public boolean queryComment(int commentId);
		
		/**
		 * 插入回复
		 * @param reply
		 * @return 成功返回真，失败返回假
		 */
		public boolean insertReply(Reply reply);
		
		/**
		 * 删除回复，通过replyid删除
		 * @param reply
		 * @return 成功返回真，失败返回假
		 */
		public boolean deleteReply(Reply reply);
		
		/**
		 * 搜索是否存在此回复,通过replyid搜索
		 * @param reply
		 * @return 若存在返回真，不存在返回假
		 */
		public boolean queryReply(Reply reply);
		
}
