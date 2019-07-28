package com.lyx.entity;

public class Message {
	
	//发送消息的id
	private int messageId;
	//发送消息的内容
	private String messageComtent;
	//发送消息的用户
	private String sendUser;
	//接受消息的用户
	private String receiveUser;
	//发送消息的时间
	private String sendTime;
	//消息是否已读，默认为0，未读，已读设置为1
	private int isread;
	
	
	
	
	
	public Message() {
		super();
		
	}
	public Message(int messageId, String messageComtent, String sendUser, String receiveUser, String sendTime, int isread) {
		super();
		this.messageId = messageId;
		this.messageComtent = messageComtent;
		this.sendUser = sendUser;
		this.receiveUser = receiveUser;
		this.sendTime = sendTime;
		this.isread = isread;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getMessageComtent() {
		return messageComtent;
	}
	public void setMessageComtent(String messageComtent) {
		this.messageComtent = messageComtent;
	}
	public String getSendUser() {
		return sendUser;
	}
	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}
	public String getReceiveUser() {
		return receiveUser;
	}
	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public int getIsread() {
		return isread;
	}
	public void setIsread(int isread) {
		this.isread = isread;
	}
	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", messageComtent=" + messageComtent + ", sendUser=" + sendUser
				+ ", receiveUser=" + receiveUser + ", sendTime=" + sendTime + ", isread=" + isread + "]";
	}
	
	
	
	
	
	
	
	
}
