package com.lyx.entity;

public class Reply {

	
	private int replyId;
	//被回复评论的文章id
	private int articleId;
	//被回复的用户名
	private String beReplyUsername;
	//回复评论的用户名
	private String replyUsername;
	//被回复评论的评论id
	private int commentId;
	//回复评论的内容
	private String replyMsg;
	//回复的时间
	private String replyTime;
	
	
	//下面的属性不同于数据库，主要用于前端展示，故数据库中表无下面属性
	
	
	//回复人的昵称
	private String replyNickname;   		
	//回复人的头像
	private String replyPortrait;
	//被回复人的昵称
	private String beReplyNickname;   		
	//被回复人的头像
	private String beReplyPortrait;
	
	
	
	public Reply() {
		super();
		
	}
	public Reply(int replyId, int articleId, String beReplyUsername, String replyUsername, int commentId,
			String replyMsg, String replyTime, String replyNickname, String replyPortrait, String beReplyNickname,
			String beReplyPortrait) {
		super();
		this.replyId = replyId;
		this.articleId = articleId;
		this.beReplyUsername = beReplyUsername;
		this.replyUsername = replyUsername;
		this.commentId = commentId;
		this.replyMsg = replyMsg;
		this.replyTime = replyTime;
		this.replyNickname = replyNickname;
		this.replyPortrait = replyPortrait;
		this.beReplyNickname = beReplyNickname;
		this.beReplyPortrait = beReplyPortrait;
	}



	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getReplyMsg() {
		return replyMsg;
	}
	public void setReplyMsg(String replyMsg) {
		this.replyMsg = replyMsg;
	}
	public String getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
	public String getReplyUsername() {
		return replyUsername;
	}
	public void setReplyUsername(String replyUsername) {
		this.replyUsername = replyUsername;
	}
	public String getBeReplyUsername() {
		return beReplyUsername;
	}
	public void setBeReplyUsername(String beReplyUsername) {
		this.beReplyUsername = beReplyUsername;
	}
	public String getReplyNickname() {
		return replyNickname;
	}
	public void setReplyNickname(String replyNickname) {
		this.replyNickname = replyNickname;
	}
	public String getReplyPortrait() {
		return replyPortrait;
	}
	public void setReplyPortrait(String replyPortrait) {
		this.replyPortrait = replyPortrait;
	}
	public String getBeReplyNickname() {
		return beReplyNickname;
	}
	public void setBeReplyNickname(String beReplyNickname) {
		this.beReplyNickname = beReplyNickname;
	}
	public String getBeReplyPortrait() {
		return beReplyPortrait;
	}
	public void setBeReplyPortrait(String beReplyPortrait) {
		this.beReplyPortrait = beReplyPortrait;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Reply [replyId=" + replyId + ", articleId=" + articleId + ", beReplyUsername=" + beReplyUsername
				+ ", replyUsername=" + replyUsername + ", commentId=" + commentId + ", replyMsg=" + replyMsg
				+ ", replyTime=" + replyTime + "]";
	}
	

	
	
	
	

}
