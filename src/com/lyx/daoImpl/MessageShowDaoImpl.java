package com.lyx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lyx.dao.MessageDao;
import com.lyx.dao.MessageShowDao;
import com.lyx.dao.UserDao;
import com.lyx.entity.Message;
import com.lyx.entity.User;
import com.lyx.util.DbUtil;

public class MessageShowDaoImpl implements MessageShowDao{
	
	UserDao ud = new UserDaoImpl();
	MessageDao md = new MessageDaoImpl();
	

	@Override
	public List<Message> getMessageByTwo(String sendUser, String receiveUser) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		List<Message> msgs = new ArrayList<Message>();
		try {
			String sql = "select * from message where (senduser = ? and receiveuser = ?) or (senduser = ? and receiveuser = ?)" ;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sendUser);
			pstmt.setString(2,receiveUser);
			pstmt.setString(3,receiveUser);
			pstmt.setString(4, sendUser);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Message message = new Message();
				message.setMessageId(rs.getInt("messageid"));
				message.setMessageComtent(rs.getString("messagecomtent"));
				message.setSendUser(rs.getString("senduser"));
				message.setReceiveUser(rs.getString("receiveuser"));
				message.setIsread(rs.getInt("isread"));
				message.setSendTime(rs.getString("sendtime"));
				
				//将此信息内容放进集合中
				msgs.add(message);
			}
			return msgs ;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		} 
		return msgs;
	}

	@Override
	public List<User> getHaveMsgUser(String username) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		List<User> users = new ArrayList<User>();
		try {
			String sql = "select * from message where senduser = ? or receiveuser = ? order by messageid desc " ;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2,username);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User();
				Message message = new Message();
				//根据是信息的发送人还是接收人搜索用户信息
				if(rs.getString("senduser")==username||rs.getString("senduser").equals(username)) {
					user = ud.userInfo(rs.getString("receiveuser"));
					//得到发送的最新一条消息内容
					user.setLastestMsg(md.getLastestMsg(username, rs.getString("receiveuser")));
					//最后一条发送消息是我发送的，说明没有未读消息
					user.setUnRead(0);  
				}else if(rs.getString("receiveuser")==username||rs.getString("receiveuser").equals(username)) {
					user = ud.userInfo(rs.getString("senduser"));
					//得到发送的最新一条消息内容
					user.setLastestMsg(md.getLastestMsg(rs.getString("senduser"), username));
					
					message.setSendUser(rs.getString("senduser"));
					message.setReceiveUser(username);
					//将未读消息条数放入user对象中
					user.setUnRead(md.getUnReadCount(message));
				}				
				
				//将得到的用户放入集合中
				users.add(user);
			}
			return users ;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		} 
		return users;
	}

	
	
}
