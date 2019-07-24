package com.lyx.service;

import java.util.List;

import com.lyx.dao.ArticleShowDao;
import com.lyx.daoImpl.ArticleShowDaoImpl;
import com.lyx.entity.Article;
import com.lyx.entity.Page;
import com.lyx.entity.User;

public class ArticleShowService {
	

		ArticleShowDao asd = new ArticleShowDaoImpl();
		
		/**
		 * 查找我的微博总数
		 * @param page
		 * @param user
		 * @return 成功返回一个对象集合，失败返回空
		 */
		public List<Article> queryMyArticleByPage(Page page,User user){
			page.setTotalCount(asd.getMyTotalCount(user));   //将我查询到的我的微博总数赋给page			
			if(page.getCurrentPage()!= 0&&user!=null) {
				return asd.queryMyArticleByPage(page, user);
			}
			return null;	
		}
	
		/**
		 * 查询所有微博，其中分页处理
		 * @param page
		 * @return 成功返回一个对象集合，失败返回空
		 */
		public List<Article>  queryAllArticleByPage(Page page,String username){
			page.setTotalCount(asd.getTotalConut());									//得到所有数据的总个数
			page.setPageSize(5);								//默认将页面大小设置为5
			if(page.getCurrentPage()!=0) {
				return asd.queryAllArticleByPage(page,username);
			}
			return null;
		}
	
		/**
		 * 在我的微博中查找符合搜索条件的微博
		 * @param page
		 * @param searchComtent
		 * @return 成功返回一个对象集合，失败返回空
		 */
		public List<Article> queryMySearchArticleByPage(Page page,String author,String searchComtent){
			page.setTotalCount(asd.getArticleSearchCount(searchComtent));
			if(page.getCurrentPage()!=0) {
				return asd.queryMySearchArticle(page, author, searchComtent);  
			}
			return null;
		}
		
		/**
		 *  在所有微博中查找符合搜索条件的微博
		 * @param page
		 * @param searchComtent
		 * @param username
		 * @return 成功返回一个对象集合，失败返回空
		 */
		public List<Article> queryAllSearchArticleByPage(Page page,String searchComtent,String username){
			page.setTotalCount(asd.getArticleSearchCount(searchComtent));
			if(page.getCurrentPage()!=0) {
				return asd.querySearchArticle(page, searchComtent,username);
			}
			return null;
		}
		
		/**
		 * 得到我的收藏的微博
		 * @param page
		 * @param username
		 * @return 成功返回一个对象集合，失败返回空
		 */
		public List<Article> queryMyCollect(Page page,User user){
			page.setTotalCount(asd.getMyCollectCount(user));
			if(page.getCurrentPage()!=0) {
				return asd.queryMyCollectArticle(page, user);
			}
			return null;
		}
		
		/**
		 * 得到被设置为热搜的文章
		 * @param page
		 * @param user
		 * @return 成功返回一个对象集合，失败返回空
		 */
		public List<Article> queryStarArticle(Page page,User user){
			page.setTotalCount(asd.getStarArticleCount());
			if(page.getCurrentPage()!=0) {
				return asd.queryStarArticle(page, user);
			}
			return null;
		}
		
		
}
