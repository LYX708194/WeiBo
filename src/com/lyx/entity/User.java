package com.lyx.entity;


/**
 * 与数据库中的user表相映射 存放与用户登录注册和个人信息有关的数据
 * @author 刘彦享
 *
 */
public class User {
		
		private int userId;
		//用户名，为电话号码，唯一值，不可重复
		private String username;
		 //密码
		private String password;
		 //用户的邮箱，用于验证和修改密码
		private String email;
		 // 用户状态值，0为管理员，1为普通用户，2为被封号用户，默认为普通用户
		private int status = 1;
		 //被其他用户举报的状态值，默认为0正常，1为被举报
		private int reported = 0;
		 //用户头像——图片地址
		private String portrait;
		 //用户昵称，可重复
		private String nickname;
		 // 个性签名
		private String signature;
		 //自我介绍
		private String selfIntroduction;
		// 地址
		private String address;
		// 用户注册时间
		private String time;
		
		//此值与friend表中的status等同    	默认值为1，表示A关注B，2表示AB互粉
		private int relation;   //好友关系，与数据库中的表无关，用于方便前端显示用户之间信息
		
		
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public int getReported() {
			return reported;
		}
		public void setReported(int reported) {
			this.reported = reported;
		}
		public String getPortrait() {
			return portrait;
		}
		public void setPortrait(String portrait) {
			this.portrait = portrait;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public String getSignature() {
			return signature;
		}
		public void setSignature(String signature) {
			this.signature = signature;
		}
		public String getSelfIntroduction() {
			return selfIntroduction;
		}
		public void setSelfIntroduction(String selfIntroduction) {
			this.selfIntroduction = selfIntroduction;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String date) {
			this.time = date;
		}
		public int getRelation() {
			return relation;
		}
		public void setRelation(int relation) {
			this.relation = relation;
		}
		
		public User() {
			super();
		}
		public User(int userId, String username, String password, String email,int status, int reported, String portrait,
				String nickname, String signature, String selfIntroduction, String address, String time,int relation) {
			super();
			this.userId = userId;
			this.username = username;
			this.password = password;
			this.email= email;
			this.status = status;
			this.reported = reported;
			this.portrait = portrait;
			this.nickname = nickname;
			this.signature = signature;
			this.selfIntroduction = selfIntroduction;
			this.address = address;
			this.time = time;
			this.relation = relation;
		}
		@Override
		public String toString() {
			return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
					+ ", status=" + status + ", reported=" + reported + ", portrait=" + portrait + ", nickname="
					+ nickname + ", signature=" + signature + ", selfIntroduction=" + selfIntroduction + ", address="
					+ address + ", time=" + time + ", relation=" + relation + "]";
		}
		
		
		


}
