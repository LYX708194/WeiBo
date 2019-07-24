package com.lyx.dao;

import java.util.List;

import com.lyx.entity.Article;
import com.lyx.entity.Page;
import com.lyx.entity.User;

/**
 * 展示微博的dao层接口，如用户的展示我的微博，管理员的展示全部微博
 * 处理对数据库中微博的查询和分页
 * @author 刘彦享
 *
 */
public interface ArticleShowDao {
	
		/**
		 * 通过articleid获得整个文章的信息
		 * @param articleId
		 * @return 
		 */
		public Article getArticleByArticleId(int articleId);
		
		/**
		 * 查询列表的总数据数 article
		 * @return 返回列表的总数据数
		 */
		public int getTotalConut();
		
		/**
		 * 查询我的微博的总数据数 article
		 * @return 返回列表的总数据数
		 */
		public int getMyTotalCount(User user);
		
		/**
		 * 查询我收藏的微博的数据数
		 * @param user
		 * @return 返回列表的总数据数
		 */
		public int getMyCollectCount(User user);
		
		/**
		 * 得到符合搜索条件的微博数量
		 * @param searchComtent 搜索的字段，模糊搜索
		 * @return 返回符合搜索条件的微博总数
		 */
		public int getArticleSearchCount(String searchComtent);
		
		/**
		 * 获得热搜的微博数量
		 * @return
		 */
		public int getStarArticleCount();
		
		/**
		 * 分页搜素所有微博，管理员或者发现微博页面的查询
		 * @param page 分页的具体内容
		 * @return 返回一个对象集合
		 */
		public List<Article> queryAllArticleByPage(Page page,String username);	
			
		/**
		 * 根据用户名分页搜素我的微博，将数据转化为一个集合返回
		 * @return 返回一个对象集合
		 */
		public List<Article>  queryMyArticleByPage(Page page,User user);
		
		/**
		 * 得到符合搜索条件的微博
		 * @param page
		 * @param searchCemtent
		 * @return 返回符合条件的对象集合
		 */
		public List<Article> querySearchArticle(Page page,String searchComtent,String username);
		
		/**
		 * 在该用户所发的微博中搜索符合搜索条件的微博
		 * @param page
		 * @param author
		 * @param searchComtent
		 * @return 返回符合条件的对象集合
		 */
		public List<Article> queryMySearchArticle(Page page,String author,String searchComtent);
		
		/**
		 * 搜索该用户收藏的微博文章
		 * @param page
		 * @param user
		 * @return 返回符合条件的对象集合
		 */
		public List<Article> queryMyCollectArticle(Page page,User user);
		
		/**
		 * 搜索热搜的微博文章
		 * @param page
		 * @return 返回一个对象集合
		 */
		public List<Article> queryStarArticle(Page page, User user);
		
		
		
}
