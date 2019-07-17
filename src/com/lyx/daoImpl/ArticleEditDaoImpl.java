package com.lyx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lyx.dao.ArticleEditDao;
import com.lyx.entity.Article;
import com.lyx.entity.ArticleLike;
import com.lyx.entity.Collect;
import com.lyx.util.DbUtil;

public class ArticleEditDaoImpl implements ArticleEditDao{
	public static void main(String[] args) {
		ArticleEditDaoImpl aed= new ArticleEditDaoImpl();
		ArticleLike like = new ArticleLike();
		like.setArticleId(3);
		like.setUsername("12345678912");
		System.out.println(aed.insertLike(like));
	}
	
	/**
	 * 向数据库中增加微博文章
	 * @param article
	 * @return 成功返回真，失败返回假
	 */
	@Override
	public boolean articleInsert(Article article) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into article(author,authornickname,authorportrait,publicedtime,comtent,photo) values(?,?,?,?,?,?)"  ;  //插入微博文章的作者发布时间和内容
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getAuthor());
			pstmt.setString(2, article.getAuthorNickname());
			pstmt.setString(3, article.getAuthorPortrait());
			pstmt.setString(4, article.getPublicedTime());
			pstmt.setString(5, article.getComtent());
			pstmt.setString(6, article.getPhoto());
			int result = pstmt.executeUpdate();
			if(result>0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(pstmt, con);
		}
		return false;
	}
	
	/**
	 * 删除一条微博文章
	 * @param article
	 * @return 成功返回真，失败返回假
	 */
	@Override
	public boolean articleDelete(Article article) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from article where articleid = ?"  ;  //通过微博文章的id删除
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, article.getArticleId());
			int result = pstmt.executeUpdate();
			if(result>0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(pstmt, con);
		}
		return false;
	}
	/**
	 * 查询文章是否存在
	 * @param article
	 * @return 成功返回真，失败返回假
	 */
	@Override
	public boolean articleQuery(Article article) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			String sql = "select * from article where articleid = ? " ;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, article.getArticleId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;   
			}
			return false;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		} 
		return false;
	}
	
	@Override
	public boolean articleLike(Article article) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "update article set likenum = ? where articleid = ?"  ;  //通过微博文章的id增加点赞数
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, article.getLikeNum()+1); //点赞+1
			pstmt.setInt(2, article.getArticleId());
			int result = pstmt.executeUpdate();
			if(result>0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(pstmt, con);
		}
		return false;
	}
	
	@Override
	public boolean insertLike(ArticleLike like) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into articlelike(articleid,username) values(?,?)"  ;  //插入like表点赞的信息
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, like.getArticleId());
			pstmt.setString(2, like.getUsername());
			int result = pstmt.executeUpdate();
			if(result>0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(pstmt, con);
		}
		return false;
	}
	
	@Override
	public boolean likeQuery(Article article,String username) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			String sql = "select * from articlelike where articleid = ? and username = ? " ;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, article.getArticleId());
			pstmt.setString(2, username);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;   
			}
			return false;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		} 
		return false;
	}

	@Override  
	public boolean articleUnLike(Article article) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "update article set likenum = ? where articleid = ?"  ;  //通过微博文章的id减少点赞数
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, article.getLikeNum()); 	//点赞-1
			pstmt.setInt(2, article.getArticleId());
			int result = pstmt.executeUpdate();
			if(result>0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(pstmt, con);
		}
		return false;
	}

	@Override
	public boolean deleteLike(ArticleLike like,String username) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from articlelike where articleid = ? and username = ?"  ;  //通过微博文章的id和点赞的用户删除
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, like.getArticleId());
			pstmt.setString(2, username);
			int result = pstmt.executeUpdate();
			if(result>0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(pstmt, con);
		}
		return false;
	}

	@Override
	public boolean articleCollect(Article article) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "update article set collectnum = ? where articleid = ?"  ;  //通过微博文章的id增加收藏数
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, article.getCollectNum()+1); //收藏+1
			pstmt.setInt(2, article.getArticleId());
			int result = pstmt.executeUpdate();
			if(result>0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(pstmt, con);
		}
		return false;
	}

	@Override
	public boolean articleUnCollect(Article article) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "update article set  collectnum = ? where articleid = ?"  ;  //通过微博文章的id减少收藏数
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, article.getCollectNum()-1); //收藏-1
			pstmt.setInt(2, article.getArticleId());
			int result = pstmt.executeUpdate();
			if(result>0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(pstmt, con);
		}
		return false;
	}

	@Override
	public boolean collectQuery(Article article, String username) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			String sql = "select * from collect where articleid = ? and username = ? " ;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, article.getArticleId());
			pstmt.setString(2, username);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;   
			}
			return false;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		} 
		return false;
	}

	@Override
	public boolean insertCollect(Collect collect) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into collect(articleid,username) values(?,?)"  ;  //插入collect表收藏的信息
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, collect.getArticleId());
			pstmt.setString(2, collect.getUsername());
			int result = pstmt.executeUpdate();
			if(result>0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(pstmt, con);
		}
		return false;
	}

	@Override
	public boolean deleteCollect(Collect collect, String username) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from collect where articleid = ? and username = ?"  ;  //通过微博文章的id和收藏的用户删除
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, collect.getArticleId());
			pstmt.setString(2, username);
			int result = pstmt.executeUpdate();
			if(result>0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(pstmt, con);
		}
		return false;
	}
	
	
}
