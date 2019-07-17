package com.lyx.entity;

/**
 * 与数据库点赞表对应的实体类，不能直接用like，是关键字
 * @author hasee
 *
 */
public class ArticleLike {
	
	
	private int likeId;
	//被点赞的文章id
	private int articleId;
	//点赞的用户
	private String username;
	
	
	public ArticleLike(int likeId, int articleId, String username) {
		super();
		this.likeId = likeId;
		this.articleId = articleId;
		this.username = username;
	}
	public ArticleLike() {
		super();
	}
	
	
	public int getLikeId() {
		return likeId;
	}
	public void setLikeId(int likeId) {
		this.likeId = likeId;
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
	
	
	
	
	
}
