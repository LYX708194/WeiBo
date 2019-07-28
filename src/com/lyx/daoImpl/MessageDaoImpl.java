package com.lyx.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lyx.dao.MessageDao;
import com.lyx.entity.Message;
import com.lyx.util.DateUtil;
import com.lyx.util.DbUtil;

public class MessageDaoImpl implements MessageDao{

	
	
	@Override
	public boolean insertMessage(Message message) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into message(messagecomtent,senduser,receiveuser,sendtime) values(?,?,?,?)"  ; 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, message.getMessageComtent());
			pstmt.setString(2, message.getSendUser());
			pstmt.setString(3, message.getReceiveUser());
			pstmt.setString(4, DateUtil.getDateToSecond());
			
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
	public boolean updateRead(Message message) {
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "update  message set isread = 1 where senduser = ? and receiveuser = ?"  ; 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, message.getSendUser());
			pstmt.setString(2, message.getReceiveUser());
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
	public int getUnReadCount(Message message) {
		int count = -1; 
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(1) from message where senduser = ? and receiveuser = ? and isread = 0" ;
			pstmt = con.prepareStatement(sql);	
			pstmt.setString(1, message.getSendUser());
			pstmt.setString(2, message.getReceiveUser());
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
	public String getLastestMsg(String sendUser,String receiveUser) {
		String msg = null;
		Connection con = DbUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			String sql = "select messagecomtent from message where senduser = ? and receiveuser = ? order by messageid desc limit 1 " ;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sendUser);
			pstmt.setString(2,receiveUser);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				msg = rs.getString(1);
			}
			return msg ;   
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs, pstmt, con);
		} 
		return msg;
	}
	
	
	
	
	
	
	
}
