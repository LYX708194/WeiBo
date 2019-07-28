package com.lyx.service;


import java.util.List;

import com.lyx.dao.MessageDao;
import com.lyx.dao.MessageShowDao;
import com.lyx.dao.UserShowDao;
import com.lyx.daoImpl.MessageDaoImpl;
import com.lyx.daoImpl.MessageShowDaoImpl;
import com.lyx.daoImpl.UserShowDaoImpl;
import com.lyx.entity.Message;
import com.lyx.entity.User;
import com.lyx.util.RemoveSame;
import com.lyx.util.ValidateUtil;

public class MessageShowService {

	MessageDao md = new MessageDaoImpl();
	MessageShowDao msd = new MessageShowDaoImpl();
	UserShowDao usd = new UserShowDaoImpl();
			
	
	/**
	 * 发送消息
	 * @param message
	 * @return 成功返回true，失败为false
	 */
	public boolean sendMessage(Message message) {
		if(ValidateUtil.isNullString(message.getMessageComtent())) {
			return false;
		}
		return md.insertMessage(message);
	}
	
	/**
	 * 管理员群发消息
	 * @param sendUser群发消息的用户名，即管理员
	 * @param messageComtent群发的消息内容
	 * @return 成功返回true，失败为false
	 */
	public  boolean sendMsgToAll(String sendUser,String messageComtent) {
		if(ValidateUtil.isNullString(messageComtent)||ValidateUtil.isNullString(sendUser)) {
			return false;		
		}else {
			List<User> users = usd.getAllUser();
			//用循环得到对象，每得到一个对象则向数据库中插入一条信息
			for(User user:users) {  
				//构建message对象，将信息的内容发送者和接收者放进对象中
				Message message = new Message();
				message.setMessageComtent(messageComtent);
				message.setSendUser(sendUser);
				message.setReceiveUser(user.getUsername());
				//调用发送信息的功能
				md.insertMessage(message);
			}
			return true;
		}			
		
	}
	
	
	/**
	 * 修改信息为已读
	 * @param message
	 * @return 成功返回true，失败为false
	 */
	public boolean updateRead(Message message) {
		if(ValidateUtil.isNullString(message.getSendUser())||ValidateUtil.isNullString(message.getReceiveUser())) {
			return false;
		}
		return md.updateRead(message);
	}
	
	/**
	 * 得到与我有消息记录的对象集合
	 * @param username
	 * @return 成功返回一个对象集合，失败返回空
	 */
	public List<User> getMsgUser(String username){
		if(ValidateUtil.isNullString(username)) {
			return null;
		}
		//将得到的对象集合进行去重操作
		List<User> users = RemoveSame.removeSame(msd.getHaveMsgUser(username));
		return users;
	}
	
	/**
	 * 通过发送者与被发送者的用户名得到两个人之间的信息
	 * @param sendUser
	 * @param receiveUser
	 * @return 成功返回一个对象集合，失败返回空
	 */
	public List<Message> getMsgByTwo(String sendUser,String receiveUser){
		if(ValidateUtil.isNullString(sendUser)||ValidateUtil.isNullString(receiveUser)) {
			return null;
		}
		return msd.getMessageByTwo(sendUser, receiveUser);
	}
	
	
	
	
}
