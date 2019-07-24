package com.lyx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lyx.dao.CommentShowDao;
import com.lyx.dao.UserDao;
import com.lyx.entity.Comment;
import com.lyx.entity.Page;
import com.lyx.entity.Reply;
import com.lyx.entity.User;
import com.lyx.util.DbUtil;

public class CommentShowDaoImpl implements CommentShowDao{

	
	UserDao ud = new UserDaoImpl();
	
	@Override
	public int getCommentCount(int articleId) {
		int count = -1; 
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(1) from comment where articleid = ?" ;
			pstmt = con.prepareStatement(sql);	
			pstmt.setInt(1, articleId);
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
	public int getReplyCount(int commentId) {
		int count = -1; 
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(1) from reply where commentid = ?" ;
			pstmt = con.prepareStatement(sql);	
			pstmt.setInt(1, commentId);
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
	public List<Comment> getCommentByPage(int articleId,Page page) {
		List<Comment> commentList  = new ArrayList<Comment>();
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {  
			//分页使用limit m,n 表示从第m个开始搜索，搜索n个就停止
			//先排序再搜索，所以order by在前面     
			String sql = "select * from comment where articleid = ?  limit ?,? " ;
			pstmt = con.prepareStatement(sql);	
			int begin = (page.getCurrentPage() -1) * page.getPageSize();   //开始搜索的数，mysql从0开始，为（当前页-1）*页面大小		
			pstmt.setInt(1, articleId);
			pstmt.setInt(2,begin); 								   //开始的单位   
			pstmt.setInt(3,page.getPageSize()); 		  //搜索的数据数，即页面大小
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Comment comment = new Comment();
				comment.setArticleId(articleId);
				comment.setCommentId(rs.getInt("commentid"));
				comment.setUsername(rs.getString("username"));
				comment.setCommentMsg(rs.getString("commentmsg"));
				comment.setCommentTime(rs.getString("commenttime")); 
				
				User user = ud.userInfo(rs.getString("username"));   //通过用户名获得该评论者的个人信息
				
				comment.setNickname(user.getNickname());		//获得评论者的昵称
				comment.setUserPortrait(user.getPortrait());   //获得评论者的头像
				
				
				
				commentList.add(comment);          //将得到的数据封装成一个对象放入集合中
			}
			return commentList;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		}
		return commentList;
	}

	@Override
	public List<Reply> getReplyByCommentId(int commentId) {
		List<Reply> replyList  = new ArrayList<Reply>();
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {  
			//分页使用limit m,n 表示从第m个开始搜索，搜索n个就停止
			//先排序再搜索，所以order by在前面     
			String sql = "select * from reply where commentid = ?" ;
			pstmt = con.prepareStatement(sql);	
			pstmt.setInt(1, commentId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Reply reply = new Reply();
				reply.setReplyId(rs.getInt("replyid"));
				reply.setArticleId(rs.getInt("articleid"));
				reply.setCommentId(rs.getInt("commentid"));
				reply.setBeReplyUsername(rs.getString("bereplyusername"));
				reply.setReplyUsername(rs.getString("replyusername"));
				reply.setReplyMsg(rs.getString("replymsg"));
				reply.setReplyTime(rs.getString("replytime"));
				
				User replyUser = ud.userInfo(rs.getString("replyusername"));
				User beReplyUser = ud.userInfo(rs.getString("bereplyusername"));
				
				//设置回复人的昵称和头像
				reply.setReplyNickname(replyUser.getNickname());
				reply.setReplyPortrait(replyUser.getPortrait());
				//设置被恢复人的昵称和头像
				reply.setBeReplyNickname(beReplyUser.getNickname());
				reply.setBeReplyPortrait(beReplyUser.getPortrait());
				
				replyList.add(reply);          //将得到的数据封装成一个对象放入集合中
			}
			return replyList;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		}
		return replyList;
	}
	
		
		
		
		
		
		
}
