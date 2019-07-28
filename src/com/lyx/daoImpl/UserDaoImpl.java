package com.lyx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lyx.dao.UserDao;
import com.lyx.entity.User;
import com.lyx.util.DbUtil;

public class UserDaoImpl implements UserDao{
	
	
	/**
	 * 用户登录，连接数据库，成功返回用户的状态值
	 * 默认返回3，表示失败
	 */
	@Override
	public int login(User user) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			String sql = "select * from user where username = ? and password = ?" ;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());		
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("status");   //返回此用户的状态值
			}
			return 3;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		}
		return 3;   
	}

	/**
	 * 查找个人信息，返回一个用户对象
	 */
	@Override
	public User userInfo(String username) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		User user = new User();
		try {
			String sql = "select * from user where username = ?" ;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user.setUserId(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));  	
				user.setPassword(rs.getString("password"));
				user.setPortrait(rs.getString("portrait"));	                                //头像的地址
				user.setEmail(rs.getString("email"));	  										//注册的邮箱，不可修改
				user.setTime(rs.getString("time"));                								//微博注册时间
				user.setNickname(rs.getString("nickname"));							//昵称
				user.setSignature(rs.getString("signature"));							//个性签名
				user.setSelfIntroduction(rs.getString("selfintroduction"));    //自我简介
				user.setAddress(rs.getString("address"));									//地址
				
				
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		}
		return user;
	}
	
	/**
	 * 修改密码
	 */
	@Override
	public boolean updatePwd(User user) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "update user set password = ? where username = ?"  ;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getUsername());
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
	 * 修改个人信息，不能修改密码，修改密码单独处理
	 */
	@Override
	public boolean updateContent(User user) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "update user set signature = ? , selfintroduction = ?,nickname = ?,address = ?  where username = ?"  ;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getSignature());
			pstmt.setString(2, user.getSelfIntroduction());
			pstmt.setString(3, user.getNickname());
			pstmt.setString(4, user.getAddress());
			pstmt.setString(5, user.getUsername());
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
	 * 修改头像，将数据库中头像的地址替换
	 */
	@Override
	public boolean updatePortrait(User user) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "update user set portrait = ? where username = ?"  ;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getPortrait());
			pstmt.setString(2, user.getUsername());
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
	 * 注册，成功返回真，失败返回假
	 */
	@Override
	public boolean register(User user) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into user(username,password,time,nickname,email) values(?,?,?,?,?)"  ;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getTime());
			pstmt.setString(4, user.getUsername());   //注册默认将昵称设为用户名
			pstmt.setString(5, user.getEmail());
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
	 * 查找数据库是否存在此对象，判断是否重复
	 * 若存在则返回真，不存在返回假
	 */
	@Override
	public boolean repetitionUsername(String username) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			String sql = "select * from user where username = ? " ;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
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
	
	/**
	 * 通过email查找用户信息，返回一个user对象
	 */
	@Override
	public User emailInfo(String email) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		User user = new User();
		try {
			String sql = "select * from user where email = ?" ;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user.setUserId(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));  
				user.setPassword(rs.getString("password"));
				user.setPortrait(rs.getString("portrait"));	  								//头像的地址
				user.setEmail(rs.getString("email"));	  										//注册的邮箱
				user.setTime(rs.getString("time"));                								//微博注册时间
				user.setNickname(rs.getString("nickname"));							//昵称
				user.setSignature(rs.getString("signature"));							//个性签名
				user.setSelfIntroduction(rs.getString("selfintroduction"));    //自我简介
				user.setAddress(rs.getString("address"));									//家庭地址
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		}
		return user;
	}
	
	@Override
	public String getUserPortrait(String username) {
		String portrait = null;
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			String sql = "select portrait from user where username = ?" ;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,username);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				portrait =rs.getString(1);
			}
			return portrait;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		} 
		return portrait;
	}
	
	/**
	 * 查找数据库是否存在此对象，判断是否重复
	 * 若存在则返回真，不存在返回假
	 */
	@Override
	public boolean repetitionEmail(String email) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			String sql = "select * from user where email = ? " ;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
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
	public boolean setUserBlack(String username) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "update user set status = 2 where username = ?"  ; //设置为封号状态
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
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
	public boolean setUserOk(String username) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "update user set status = 1 where username = ?"  ; //设置为普通用户状态
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
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
	public boolean queryFriend(String fromUsername, String toUsername) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			String sql = "select * from friend where fromusername = ? and tousername = ?" ;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fromUsername);
			pstmt.setString(2, toUsername);
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
	public int getRelation(String fromUsername, String toUsername) {
		int status = -1;
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			String sql = "select status from friend where fromusername = ? and tousername = ?" ;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fromUsername);
			pstmt.setString(2, toUsername);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				status =rs.getInt(1);
			}
			return status;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		} 
		return status;
	}

	
	
	@Override
	public boolean updateRelation(String fromUsername, String toUsername, int status) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "update friend set status = ? where fromusername = ? and tousername = ?"  ; 
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, status);  
			pstmt.setString(2, fromUsername);
			pstmt.setString(3, toUsername);
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
	public boolean insertFriend(String fromUsername, String toUsername,int status) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into friend(fromusername,tousername,status) values(?,?,?)"  ;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fromUsername);
			pstmt.setString(2, toUsername);
			pstmt.setInt(3, status);
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
	public boolean deleteFriend(String fromUsername, String toUsername) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from friend where fromusername = ? and tousername = ?"  ;  
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fromUsername);
			pstmt.setString(2, toUsername);
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
	public int getUserStatus(String username) {
		int status = -1;
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			String sql = "select status from user where username = ?" ;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				status = rs.getInt(1);
			}
			return status;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		}
		return status;
	}

	
	

}
