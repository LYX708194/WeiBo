package com.lyx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lyx.dao.ArticleEditDao;
import com.lyx.entity.Article;
import com.lyx.entity.ArticleLike;
import com.lyx.entity.Collect;
import com.lyx.entity.Comment;
import com.lyx.entity.Reply;
import com.lyx.util.DateUtil;
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
	public int queryStar(Article article) {
		int star = -1;
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			String sql = "select star from article where articleid = ? " ;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, article.getArticleId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				star = rs.getInt(1);
			}
			return star;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		} 
		return star;
	}
	
	@Override
	public boolean upArticle(Article article) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "update article set star = ? where articleid = ?"  ;  
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 1);  					 //将星标状态修改为1
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
	public boolean downArticle(Article article) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "update article set star = ? where articleid = ?"  ;  
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 0); 				  //将星标状态修改为0
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
			String sql = "delete from collect where articleid = ? and username = ? "  ;  //通过微博文章的id和收藏的用户删除
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

	@Override
	public boolean articleComment(Article article) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "update article set commentnum = ? where articleid = ?"  ;  //通过微博文章的id增加评论数
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, article.getCommentNum()+1);  //评论数+1
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
	public boolean articleUnComment(Article article) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "update article set commentnum = ? where articleid = ?"  ;  //通过微博文章的id减少评论数
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, article.getCommentNum()-1);  //评论数-1
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
	public boolean insertComment(Comment comment) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into comment(articleid,username,commentmsg,commenttime) values(?,?,?,?)"  ;  //插入comtent表评论的信息
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comment.getArticleId());
			pstmt.setString(2, comment.getUsername());
			pstmt.setString(3, comment.getCommentMsg());
			pstmt.setString(4, DateUtil.getDateToSecond());  //获得当前时间并装入数据库中
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
	public boolean deleteComment(Comment comment) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from comment where commentid = ? or articleid = ?"  ;  //通过评论的id删除
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comment.getCommentId());
			pstmt.setInt(2, comment.getArticleId());  
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
	public boolean articleDeleteComment(Article article) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "update article set commentnum = ? where articleid = ?"  ;  //通过微博文章的id减少评论数
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, article.getCommentNum()-1);  //评论数-1
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
	public boolean queryComment(int commentId) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			String sql = "select * from comment where commentid = ? " ;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commentId);
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
	public boolean insertReply(Reply reply) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into reply(articleid,commentid,bereplyusername,replyusername,replymsg,replytime) values(?,?,?,?,?,?)"  ;  //插入reply表回复的信息
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reply.getArticleId());
			pstmt.setInt(2, reply.getCommentId());
			pstmt.setString(3, reply.getBeReplyUsername());
			pstmt.setString(4, reply.getReplyUsername());
			pstmt.setString(5, reply.getReplyMsg());
			pstmt.setString(6, DateUtil.getDateToSecond());  //获得当前时间
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
	public boolean deleteReply(Reply reply) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from reply where replyid = ? or articleid = ?"  ;  //通过replyid删除回复
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reply.getReplyId());
			pstmt.setInt(2, reply.getArticleId());
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
	public boolean queryReply(Reply reply) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			String sql = "select * from reply where replyid = ? " ;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reply.getReplyId());
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
	
	
	
	
	
}
