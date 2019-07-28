package com.lyx.dao;

import java.util.List;

import com.lyx.entity.Message;
import com.lyx.entity.User;

public interface MessageShowDao {

	/**
	 * 获得两人间的所有消息，并返回一个集合
	 * @param sendUser
	 * @param receiveUser
	 * @return 返回一个集合
	 */
	public List<Message> getMessageByTwo(String sendUser,String receiveUser);
	
	/**
	 * 通过用户名找到与该用户有过信息交流的用户集合
	 * @param username
	 * @return 返回一个集合
	 */
	public List<User> getHaveMsgUser(String username);
	
	
	
	
	
}
