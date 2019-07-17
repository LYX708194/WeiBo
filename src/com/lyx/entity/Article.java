package com.lyx.entity;

/**
 * 文章表，与数据库中表对应，用于发表微博
 * @author 刘彦享
 *
 */
public class Article {
		
		private int articleId;
		//文章的作者，即用户名
		private String author;
		//文章的作者的昵称
		private String authorNickname;
		//作者的头像
		private String authorPortrait;
		//文章的发表时间
		private String publicedTime;
		//文章的图片地址
		private String photo;
		//文件的内容
		private String comtent;
		//点赞的数目
		private long likeNum;
		//转发的数目
		private long forwordNum;
		//评论的数目
		private long commentNum;
		//收藏的数目
		private long collectNum;
		//星标状态，管理员用于置顶文章
		private int star;
		
		private boolean like;  //前端使用，判断是否点赞
		
		private boolean collect; //前端使用，判断是否收藏
		
		
		
		public Article() {
			super();
		}
		public Article(int articleId, String author, String authorNickname, String authorPortrait, String publicedTime,
				String photo, String comtent, long likeNum, long forwordNum, long commentNum, long collectNum, int star,
				boolean like, boolean collect) {
			super();
			this.articleId = articleId;
			this.author = author;
			this.authorNickname = authorNickname;
			this.authorPortrait = authorPortrait;
			this.publicedTime = publicedTime;
			this.photo = photo;
			this.comtent = comtent;
			this.likeNum = likeNum;
			this.forwordNum = forwordNum;
			this.commentNum = commentNum;
			this.collectNum = collectNum;
			this.star = star;
			this.like = like;
			this.collect = collect;
		}





		public int getArticleId() {
			return articleId;
		}
		public void setArticleId(int articleId) {
			this.articleId = articleId;
		}
		public String getAuthor() {
			return author;
		}
		public String getAuthorPortrait() {
			return authorPortrait;
		}
		public void setAuthorportrait(String authorPortrait) {
			this.authorPortrait = authorPortrait;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public String getAuthorNickname() {
			return authorNickname;
		}
		public void setAuthorNickname(String authorNickname) {
			this.authorNickname = authorNickname;
		}
		public String getPublicedTime() {
			return publicedTime;
		}
		public String getPhoto() {
			return photo;
		}
		public void setPhoto(String photo) {
			this.photo = photo;
		}
		public void setPublicedTime(String publicedTime) {
			this.publicedTime = publicedTime;
		}
		public String getComtent() {
			return comtent;
		}
		public void setComtent(String comtent) {
			this.comtent = comtent;
		}
		public long getLikeNum() {
			return likeNum;
		}
		public void setLikeNum(long likeNum) {
			this.likeNum = likeNum;
		}
		public long getForwordNum() {
			return forwordNum;
		}
		public void setForwordNum(long forwordNum) {
			this.forwordNum = forwordNum;
		}
		public long getCommentNum() {
			return commentNum;
		}
		public void setCommentNum(long commentNum) {
			this.commentNum = commentNum;
		}
		public long getCollectNum() {
			return collectNum;
		}
		public void setCollectNum(long collectNum) {
			this.collectNum = collectNum;
		}
		public int getStar() {
			return star;
		}
		public void setStar(int star) {
			this.star = star;
		}	
		public void setAuthorPortrait(String authorPortrait) {
			this.authorPortrait = authorPortrait;
		}
		public boolean isLike() {
			return like;
		}
		public void setLike(boolean like) {
			this.like = like;
		}
		public boolean isCollect() {
			return collect;
		}
		public void setCollect(boolean collect) {
			this.collect = collect;
		}
		
		@Override
		public String toString() {
			return "Article [articleId=" + articleId + ", author=" + author + ", authorNickname=" + authorNickname
					+ ", authorPortrait=" + authorPortrait + ", publicedTime=" + publicedTime + ", photo=" + photo
					+ ", comtent=" + comtent + ", likeNum=" + likeNum + ", forwordNum=" + forwordNum + ", commentNum="
					+ commentNum + ", collectNum=" + collectNum + ", star=" + star + ", like=" + like + ", collect="
					+ collect + "]";
		}
		

		
		
		
		
}
