package com.lyx.dao;


import com.lyx.entity.Message;

public interface MessageDao {

	/**
	 * 向数据库中插入信息
	 * @param message
	 * @return 成功返回true，失败返回false
	 */
	public boolean insertMessage(Message message);
	
	/**
	 * 更新信息为已读
	 * @param message
	 * @return 成功返回true，失败返回false
	 */
	public boolean updateRead(Message message);
	
	/**
	 * 通过用户名查找我未读的信息，我为接受者
	 * @param username
	 * @return 返回得到的数据数
	 */
	public int getUnReadCount(Message message);
	
	/**
	 * 获得两人间最后的消息
	 * @param sendUser
	 * @param receiveUser
	 * @return 返回得到的信息
	 */
	public String getLastestMsg(String sendUser,String receiveUser);
	
	
	
	
	
}
