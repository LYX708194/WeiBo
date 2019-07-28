package com.lyx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lyx.dao.UserShowDao;
import com.lyx.entity.Page;
import com.lyx.entity.User;
import com.lyx.service.UserService;
import com.lyx.util.DbUtil;

public class UserShowDaoImpl implements UserShowDao{
	
	UserService us = new UserService();
	
	@Override
	public int getAllUserCount() {
		int count = -1; 
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(1) from user" ;
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

	@Override
	public int getMyFocusCount(User user) {
		int count = -1; 
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(1) from friend where fromusername = ? " ;
			pstmt = con.prepareStatement(sql);	
			pstmt.setString(1, user.getUsername());  //我自己
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
	public int getMyFansCount(String username) {
		int count = -1; 
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(1) from friend where  tousername = ?" ;
			pstmt = con.prepareStatement(sql);	 
			pstmt.setString(1, username);  //被关注的人的用户名，即找出谁关注了此用户
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
	public int getSearchUserCount(String searchMsg) {
		int count = -1; 
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(1) from user where  username = ? or nickname = ?" ;
			pstmt = con.prepareStatement(sql);	 
			pstmt.setString(1, "%"+searchMsg+ "%");
			pstmt.setString(2, "%"+searchMsg+ "%");
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
	public List<User> getAllUserByPage(Page page,String username) {
		List<User> userList  = new ArrayList<User>();
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {  
			//分页使用limit m,n 表示从第m个开始搜索，搜索n个就停止
			//先排序再搜索，所以order by在前面     
			String sql = "select * from user where userid != 1 order by userid desc limit ?,? " ;
			pstmt = con.prepareStatement(sql);	
			int begin = (page.getCurrentPage() -1) * page.getPageSize();   //开始搜索的数，mysql从0开始，为（当前页-1）*页面大小		
			pstmt.setInt(1,begin); 								   //开始的单位   
			pstmt.setInt(2,page.getPageSize()); 		  //搜索的数据数，即页面大小
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));  						//用户名
				user.setPortrait(rs.getString("portrait"));	                                //头像的地址
				user.setNickname(rs.getString("nickname"));							//昵称				
				user.setSignature(rs.getString("signature"));							//个性签名
				user.setAddress(rs.getString("address"));	 								//地址
				user.setSelfIntroduction(rs.getString("selfintroduction"));  //自我介绍
				user.setStatus(rs.getInt("status"));
				
				user.setRelation(us.getRelation(username, rs.getString("username")));   //得到此用户与username的关系
				
				userList.add(user);          //将得到的数据封装成一个对象放入集合中
			}
			return userList;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		}
		return userList;
	}
	
	
	@Override
	public List<User> getAllUser() {
		List<User> userList  = new ArrayList<User>();
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {  
			String sql = "select * from user where userid != 1  " ;
			pstmt = con.prepareStatement(sql);	
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();							//用于群发消息，只需要得到用户名即可
				user.setUsername(rs.getString("username"));  						//用户名
				
				userList.add(user);          //将得到的数据封装成一个对象放入集合中
			}
			return userList;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		}
		return userList;
	}

	@Override
	public List<User> getMyFocusByPage(Page page, User user) {
		List<User> userList  = new ArrayList<User>();
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {  
			//分页使用limit m,n 表示从第m个开始搜索，搜索n个就停止
			//先排序再搜索，所以order by在前面     
			String sql = "select * from user where username in (select tousername from friend where fromusername = ?) order by userid desc limit ?,? " ;
			pstmt = con.prepareStatement(sql);	
			int begin = (page.getCurrentPage() -1) * page.getPageSize();   //开始搜索的数，mysql从0开始，为（当前页-1）*页面大小
			pstmt.setString(1, user.getUsername()); 						//friend表中关注者的用户名，找出被关注者，并在user表中找到ta
			pstmt.setInt(2,begin); 								   //开始的单位   
			pstmt.setInt(3,page.getPageSize()); 		  //搜索的数据数，即页面大小
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user1 = new User();
				user1.setUserId(rs.getInt("userid"));
				user1.setUsername(rs.getString("username"));  						//用户名
				user1.setPortrait(rs.getString("portrait"));	                                //头像的地址
				user1.setNickname(rs.getString("nickname"));							//昵称				
				user1.setSignature(rs.getString("signature"));							//个性签名
				user1.setSelfIntroduction(rs.getString("selfintroduction"));
				user1.setAddress(rs.getString("address"));	 								//地址
				user.setStatus(rs.getInt("status"));	 											//状态
				
				user1.setRelation(us.getRelation(user.getUsername(), rs.getString("username")));   //得到此用户与username的关系
				
				userList.add(user1);          //将得到的数据封装成一个对象放入集合中
			}
			return userList;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		}
		return userList;
	}

	@Override
	public List<User> getMyFansByPage(Page page, String username) {
		List<User> userList  = new ArrayList<User>();
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {  
			//分页使用limit m,n 表示从第m个开始搜索，搜索n个就停止
			//先排序再搜索，所以order by在前面     
			String sql = "select * from user where username in (select fromusername from friend where tousername = ?) order by userid desc limit ?,? " ;
			pstmt = con.prepareStatement(sql);	
			int begin = (page.getCurrentPage() -1) * page.getPageSize();   //开始搜索的数，mysql从0开始，为（当前页-1）*页面大小
			pstmt.setString(1, username); 						//friend表中被关注者的用户名，找出关注者，并在user表中找到ta
			pstmt.setInt(2,begin); 								   //开始的单位   
			pstmt.setInt(3,page.getPageSize()); 		  //搜索的数据数，即页面大小
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));  						//用户名
				user.setPortrait(rs.getString("portrait"));	                                //头像的地址
				user.setNickname(rs.getString("nickname"));							//昵称				
				user.setSignature(rs.getString("signature"));							//个性签名
				user.setSelfIntroduction(rs.getString("selfintroduction"));
				user.setAddress(rs.getString("address"));	 								//地址
				user.setStatus(rs.getInt("status"));	 											//状态
				
				user.setRelation(us.getRelation(username ,rs.getString("username")) ); 		//得到此用户与username的关系
				
				userList.add(user);          //将得到的数据封装成一个对象放入集合中
			}
			return userList;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		}
		return userList;
	}

	
	@Override
	public List<User> queryUserByPage(Page page, String searchMsg,String username) {
			List<User> users = new ArrayList<User>();
			Connection con = DbUtil.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				String sql = "select * from user where username = ? or nickname = ? order by userid desc limit ?,?" ;
				pstmt = con.prepareStatement(sql);
				int begin = (page.getCurrentPage() -1) * page.getPageSize();   //开始搜索的数，mysql从0开始，为（当前页-1）*页面大小
				pstmt.setString(1, "%"+searchMsg+ "%");
				pstmt.setString(2, "%"+searchMsg+ "%");
				pstmt.setInt(3,begin); 								   //开始的单位   
				pstmt.setInt(4,page.getPageSize()); 		  //搜索的数据数，即页面大小
				rs = pstmt.executeQuery();
				while(rs.next()) {
					User user = new User();
					user.setUserId(rs.getInt("userid"));
					user.setUsername(rs.getString("username"));  
					user.setPortrait(rs.getString("portrait"));	  								//头像的地址
					user.setNickname(rs.getString("nickname"));							//昵称
					user.setSignature(rs.getString("signature"));							//个性签名
					user.setAddress(rs.getString("address"));	 								//地址
					user.setStatus(rs.getInt("status"));	 											//状态
					
					user.setRelation(us.getRelation(username, rs.getString("username")) );   //得到此用户与username的关系
					
					users.add(user);   
				}
				return users;   
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DbUtil.close(rs, pstmt, con);
			} 	
			return null;
		
	}
	
	
	
	
	
}
