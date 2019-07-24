package com.lyx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lyx.dao.ArticleShowDao;
import com.lyx.entity.Article;
import com.lyx.entity.Page;
import com.lyx.entity.User;
import com.lyx.util.DbUtil;

public class ArticleShowDaoImpl implements ArticleShowDao{
	
	ArticleEditDaoImpl aed = new ArticleEditDaoImpl();
	
	
	@Override
	public Article getArticleByArticleId(int articleId) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		Article article = new Article();
		try {
			String sql = "select * from article where articleid = ?" ;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, articleId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article.setArticleId(articleId);
				article.setAuthor(rs.getString("author"));									//作者
				article.setAuthorNickname(rs.getString("authornickname"));//作者的昵称
				article.setAuthorportrait(rs.getString("authorportrait"));	   	//作者的头像
				article.setComtent(rs.getString("comtent"));								//微博内容
				article.setPublicedTime(rs.getString("publicedtime"));			//发表时间
				article.setPhoto(rs.getString("photo"));										//配图地址
				article.setLikeNum(rs.getLong("likenum"));								//点赞数
				article.setCommentNum(rs.getLong("commentnum"))	;		//评论数
				article.setForwordNum(rs.getLong("forwordnum"));			//转发数
				article.setCollectNum(rs.getLong("collectnum"));	 				//收藏数
				article.setStar(rs.getInt("star"));                                            	 	//星标
			}
			return article;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		}
		return article;
	}
	
	/**
	 * 得到总数据数
	 */
	@Override
	public int getTotalConut() {
		int count = -1; 
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(1) from article" ;
			pstmt = con.prepareStatement(sql);	
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			return count;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		}
		return count;
	}
	/**
	 * 满足搜索条件的数据总数
	 */
	@Override
	public int getArticleSearchCount(String searchComtent) {
		int count = -1; 
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) from article where comtent like ? " ;
			pstmt = con.prepareStatement(sql);	
			pstmt.setString(1, "%"+searchComtent+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			return count;   //返回总数据数
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		}
		return count;
	}
	
	@Override
	public int getMyCollectCount(User user) {
		int count = -1; 
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(1) from collect where username = ?" ;
			pstmt = con.prepareStatement(sql);	
			pstmt.setString(1, user.getUsername());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			return count;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		}
		return count;
	}
	
	@Override
		public int getMyTotalCount(User user) {
			int count = -1; 
			Connection con = DbUtil.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select count(1) from article where author = ?" ;
				pstmt = con.prepareStatement(sql);	
				pstmt.setString(1, user.getUsername());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
				}
				return count;   
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DbUtil.close(rs, pstmt, con);
			}
			return count;
		}
	
	@Override
	public int getStarArticleCount() {
		int count = -1; 
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(1) from article where star = 1" ;
			pstmt = con.prepareStatement(sql);	
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			return count;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		}
		return count;
	}
	
	/**
	 * 得到我的微博
	 * @param page
	 * @param user
	 * @return
	 */
	@Override
	public List<Article> queryMyArticleByPage(Page page, User user) {
		List<Article> articleList  = new ArrayList<Article>();
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {  
			//因为微博是随时间插入表中，id越大越新发表，用order by articleid desc根据时间倒叙输出
			//分页使用limit m,n 表示从第m个开始搜索，搜索n个就停止
			//先排序再搜索，所以order by在前面     
			String sql = "select * from article where author = ? order by articleid desc limit ?,? " ;
			pstmt = con.prepareStatement(sql);	
			int begin = (page.getCurrentPage() -1) * page.getPageSize();   //开始搜索的数，mysql从0开始，为（当前页-1）*页面大小
			pstmt.setString(1, user.getUsername());   //微博的作者为用户名
			pstmt.setInt(2,begin); 								   //开始的单位   
			pstmt.setInt(3,page.getPageSize()); 		  //搜索的数据数，即页面大小
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Article article = new Article();
				article.setArticleId(rs.getInt("articleid"));
				article.setAuthor(rs.getString("author"));									//作者
				article.setAuthorNickname(rs.getString("authornickname"));//作者的昵称
				article.setAuthorportrait(rs.getString("authorportrait"));	   	//作者的头像
				article.setComtent(rs.getString("comtent"));								//微博内容
				article.setPublicedTime(rs.getString("publicedtime"));			//发表时间
				article.setPhoto(rs.getString("photo"));										//配图地址
				article.setLikeNum(rs.getLong("likenum"));								//点赞数
				article.setCommentNum(rs.getLong("commentnum"))	;		//评论数
				article.setForwordNum(rs.getLong("forwordnum"));			//转发数
				article.setCollectNum(rs.getLong("collectnum"));	 				//收藏数
				article.setStar(rs.getInt("star"));                                            	 	//星标
				
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
				
				articleList.add(article);          //将得到的数据封装成一个对象放入集合中
			}
			return articleList;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		}
		return articleList;
	}
	
	
	/**
	 * 得到所有的微博文章集合
	 */
	@Override
	public List<Article> queryAllArticleByPage(Page page,String username) {
		List<Article> articleList  = new ArrayList<Article>();
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {  
			//因为微博是随时间插入表中，id越大越新发表，用order by articleid desc根据时间倒叙输出
			//分页使用limit m,n 表示从第m个开始搜索，搜索n个就停止
			//先排序再搜索，所以order by在前面     
			String sql = "select * from article  order by articleid desc limit ?,? " ;
			pstmt = con.prepareStatement(sql);	
			int begin = (page.getCurrentPage() -1) * page.getPageSize();   //开始搜索的数，mysql从0开始，为（当前页-1）*页面大小
			pstmt.setInt(1,begin); 								   //开始的单位   
			pstmt.setInt(2,page.getPageSize()); 		  //搜索的数据数，即页面大小
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Article article = new Article();
				article.setArticleId(rs.getInt("articleid"));
				article.setAuthor(rs.getString("author"));									//作者
				article.setAuthorNickname(rs.getString("authornickname"));//作者的昵称
				article.setAuthorportrait(rs.getString("authorportrait"));	   	//作者的头像
				article.setComtent(rs.getString("comtent"));								//微博内容
				article.setPublicedTime(rs.getString("publicedtime"));			//发表时间
				article.setPhoto(rs.getString("photo"));										//配图地址
				article.setLikeNum(rs.getLong("likenum"));								//点赞数
				article.setCommentNum(rs.getLong("commentnum"))	;		//评论数
				article.setForwordNum(rs.getLong("forwordnum"));			//转发数
				article.setCollectNum(rs.getLong("collectnum"));	 				//收藏数
				article.setStar(rs.getInt("star"));                                            	 	//星标
				
				if(aed.likeQuery(article, username)) {  //查询是否点过赞
					article.setLike(true);
				}else {
					article.setLike(false);  
				}
				if(aed.collectQuery(article, username)) { //查询是否收藏过
					article.setCollect(true);
				}else {
					article.setCollect(false);
				}
				articleList.add(article);          //将得到的数据封装成一个对象放入集合中
			}
			return articleList;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		}
		return null;
	}
	
	
	
	/**
	 * 模糊搜索取得符合条件的对象集合
	 */
	@Override
	public List<Article> querySearchArticle(Page page, String searchComtent,String username) {
		List<Article> articleList  = new ArrayList<Article>();
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {  
			//因为微博是随时间插入表中，id越大越新发表，用order by articleid desc根据时间倒叙输出
			//分页使用limit m,n 表示从第m个开始搜索，搜索n个就停止
			//先排序再搜索，所以order by在前面     
			String sql = "select * from article where comtent like ?  order by articleid desc limit ?,? " ;
			pstmt = con.prepareStatement(sql);	
			int begin = (page.getCurrentPage() -1) * page.getPageSize();   //开始搜索的数，mysql从0开始，为（当前页-1）*页面大小
			pstmt.setString(1, "%"+searchComtent+"%");
			pstmt.setInt(2,begin); 								   //开始的单位   
			pstmt.setInt(3,page.getPageSize()); 		  //搜索的数据数，即页面大小
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Article article = new Article();
				article.setArticleId(rs.getInt("articleid"));
				article.setAuthor(rs.getString("author"));									//作者
				article.setAuthorNickname(rs.getString("authornickname"));//作者的昵称
				article.setAuthorportrait(rs.getString("authorportrait"));	   	//作者的头像
				article.setComtent(rs.getString("comtent"));								//微博内容
				article.setPublicedTime(rs.getString("publicedtime"));			//发表时间
				article.setPhoto(rs.getString("photo"));										//配图地址
				article.setLikeNum(rs.getLong("likenum"));								//点赞数
				article.setCommentNum(rs.getLong("commentnum"))	;		//评论数
				article.setForwordNum(rs.getLong("forwordnum"));			//转发数
				article.setCollectNum(rs.getLong("collectnum"));	 				//收藏数
				article.setStar(rs.getInt("star"));                                            	 	//星标
				
				if(aed.likeQuery(article, username)) {  //查询是否点过赞
					article.setLike(true);
				}else {
					article.setLike(false);  
				}
				if(aed.collectQuery(article, username)) { //查询是否收藏过
					article.setCollect(true);
				}else {
					article.setCollect(false);
				}
				
				articleList.add(article);          //将得到的数据封装成一个对象放入集合中
			}
			return articleList;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		}
		return null;
	}
	
		@Override
		public List<Article> queryMySearchArticle(Page page, String author, String searchComtent) {
			List<Article> articleList  = new ArrayList<Article>();
			Connection con = DbUtil.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {  
				//因为微博是随时间插入表中，id越大越新发表，用order by articleid desc根据时间倒叙输出
				//分页使用limit m,n 表示从第m个开始搜索，搜索n个就停止
				//先排序再搜索，所以order by在前面     
				String sql = "select * from article where comtent like ? and author = ? order by articleid desc limit ?,? " ;
				pstmt = con.prepareStatement(sql);	
				int begin = (page.getCurrentPage() -1) * page.getPageSize();   //开始搜索的数，mysql从0开始，为（当前页-1）*页面大小
				pstmt.setString(1, "%"+searchComtent+"%");
				pstmt.setString(2, author);
				pstmt.setInt(3,begin); 								   //开始的单位   
				pstmt.setInt(4,page.getPageSize()); 		  //搜索的数据数，即页面大小
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Article article = new Article();
					article.setArticleId(rs.getInt("articleid"));
					article.setAuthor(rs.getString("author"));									//作者
					article.setAuthorNickname(rs.getString("authornickname"));//作者的昵称
					article.setAuthorportrait(rs.getString("authorportrait"));	   	//作者的头像
					article.setComtent(rs.getString("comtent"));								//微博内容
					article.setPublicedTime(rs.getString("publicedtime"));			//发表时间
					article.setPhoto(rs.getString("photo"));										//配图地址
					article.setLikeNum(rs.getLong("likenum"));								//点赞数
					article.setCommentNum(rs.getLong("commentnum"))	;		//评论数
					article.setForwordNum(rs.getLong("forwordnum"));			//转发数
					article.setCollectNum(rs.getLong("collectnum"));	 				//收藏数
					article.setStar(rs.getInt("star"));                                            	 	//星标
					
					if(aed.likeQuery(article, author)) {  //查询是否点过赞
						article.setLike(true);
					}else {
						article.setLike(false);  
					}
					if(aed.collectQuery(article, author)) { //查询是否收藏过
						article.setCollect(true);
					}else {
						article.setCollect(false);
					}
					
					articleList.add(article);          //将得到的数据封装成一个对象放入集合中
				}
				return articleList;   
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DbUtil.close(rs, pstmt, con);
			}
			return null;
		}
		
	
		@Override //我的收藏微博列表
		public List<Article> queryMyCollectArticle(Page page, User user) {
			List<Article> articleList  = new ArrayList<Article>();
			Connection con = DbUtil.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {  
				//因为微博是随时间插入表中，id越大越新发表，用order by articleid desc根据时间倒叙输出
				//分页使用limit m,n 表示从第m个开始搜索，搜索n个就停止
				//先排序再搜索，所以order by在前面     
				String sql = "select * from article where articleid in (select articleid from collect where username = ?) order by articleid desc limit ?,? " ;
				pstmt = con.prepareStatement(sql);	
				int begin = (page.getCurrentPage() -1) * page.getPageSize();   //开始搜索的数，mysql从0开始，为（当前页-1）*页面大小
				pstmt.setString(1, user.getUsername());
				pstmt.setInt(2,begin); 								   //开始的单位   
				pstmt.setInt(3,page.getPageSize()); 		  //搜索的数据数，即页面大小
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Article article = new Article();
					article.setArticleId(rs.getInt("articleid"));
					article.setAuthor(rs.getString("author"));									//作者
					article.setAuthorNickname(rs.getString("authornickname"));//作者的昵称
					article.setAuthorportrait(rs.getString("authorportrait"));	   	//作者的头像
					article.setComtent(rs.getString("comtent"));								//微博内容
					article.setPublicedTime(rs.getString("publicedtime"));			//发表时间
					article.setPhoto(rs.getString("photo"));										//配图地址
					article.setLikeNum(rs.getLong("likenum"));								//点赞数
					article.setCommentNum(rs.getLong("commentnum"))	;		//评论数
					article.setForwordNum(rs.getLong("forwordnum"));			//转发数
					article.setCollectNum(rs.getLong("collectnum"));	 				//收藏数
					article.setStar(rs.getInt("star"));                                            	 	//星标
					
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
					
					articleList.add(article);          //将得到的数据封装成一个对象放入集合中
				}
				return articleList;   
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DbUtil.close(rs, pstmt, con);
			}
			return null;
		}
		
		
		
		@Override
		public List<Article> queryStarArticle(Page page, User user) {
			List<Article> articleList  = new ArrayList<Article>();
			Connection con = DbUtil.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {  
				//因为微博是随时间插入表中，id越大越新发表，用order by articleid desc根据时间倒叙输出
				//分页使用limit m,n 表示从第m个开始搜索，搜索n个就停止
				//先排序再搜索，所以order by在前面     
				String sql = "select * from article where star = 1  order by articleid desc limit ?,? " ;
				pstmt = con.prepareStatement(sql);	
				int begin = (page.getCurrentPage() -1) * page.getPageSize();   //开始搜索的数，mysql从0开始，为（当前页-1）*页面大小	
				pstmt.setInt(1,begin); 								   //开始的单位   
				pstmt.setInt(2,page.getPageSize()); 		  //搜索的数据数，即页面大小
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Article article = new Article();
					article.setArticleId(rs.getInt("articleid"));
					article.setAuthor(rs.getString("author"));									//作者
					article.setAuthorNickname(rs.getString("authornickname"));//作者的昵称
					article.setAuthorportrait(rs.getString("authorportrait"));	   	//作者的头像
					article.setComtent(rs.getString("comtent"));								//微博内容
					article.setPublicedTime(rs.getString("publicedtime"));			//发表时间
					article.setPhoto(rs.getString("photo"));										//配图地址
					article.setLikeNum(rs.getLong("likenum"));								//点赞数
					article.setCommentNum(rs.getLong("commentnum"))	;		//评论数
					article.setForwordNum(rs.getLong("forwordnum"));			//转发数
					article.setCollectNum(rs.getLong("collectnum"));	 				//收藏数
					article.setStar(rs.getInt("star"));                                            	 	//星标
					
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
					
					articleList.add(article);          //将得到的数据封装成一个对象放入集合中
				}
				return articleList;   
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DbUtil.close(rs, pstmt, con);
			}
			return null;
		}
		
		
		
		
		
		
}
