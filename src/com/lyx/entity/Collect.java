package com.lyx.entity;

public class Collect {

	//收藏id
	private int collectId;
	//收藏的文章id
	private int articleId;
	//收藏文章的用户
	private String username;
	
	
	
	public Collect() {
		super();
	}
	
	public Collect(int collectId, int articleId, String username) {
		super();
		this.collectId = collectId;
		this.articleId = articleId;
		this.username = username;
	}
	public int getCollectId() {
		return collectId;
	}
	public void setCollectId(int collectId) {
		this.collectId = collectId;
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

	@Override
	public String toString() {
		return "Collect [collectId=" + collectId + ", articleId=" + articleId + ", username=" + username + "]";
	}
	
	
	
	
	
}
