package com.lyx.entity;

import java.util.List;

public class Comment {

	private int commentId;
	//被评论的文章id
	private int articleId;
	//评论此微博的用户
	private String username;
	//评论的内容
	private String commentMsg;
	//评论的时间
	private String commentTime;
	
	//下面的属性不同于数据库，主要用于前端展示，故数据库中表无下面属性
	
	//评论人的昵称
	private String nickname;   		
	//评论人的头像
	private String userPortrait;
	//回复的内容  存放查询到的该评论的回复
	private List<Reply> replys;
	
	
	
	public Comment() {
		super();
		
	}	

	public Comment(int commentId, int articleId, String username, String commentMsg, String commentTime,
			String nickname, String userPortrait, List<Reply> replys) {
		super();
		this.commentId = commentId;
		this.articleId = articleId;
		this.username = username;
		this.commentMsg = commentMsg;
		this.commentTime = commentTime;
		this.nickname = nickname;
		this.userPortrait = userPortrait;
		this.replys = replys;
	}


	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCommentMsg() {
		return commentMsg;
	}
	public void setCommentMsg(String commentMsg) {
		this.commentMsg = commentMsg;
	}
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String comtentTime) {
		this.commentTime = comtentTime;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUserPortrait() {
		return userPortrait;
	}
	public void setUserPortrait(String userPortrait) {
		this.userPortrait = userPortrait;
	}
	
	public List<Reply> getReplys() {
		return replys;
	}
	public void setReplys(List<Reply> replys) {
		this.replys = replys;
	}
	@Override
	public String toString() {
		return "comment [commentId=" + commentId + ", articleId=" + articleId + ", username=" + username
				+ ", commentMsg=" + commentMsg + ", commentTime=" + commentTime + "]";
	}
	
	
	
	
	
	
}
