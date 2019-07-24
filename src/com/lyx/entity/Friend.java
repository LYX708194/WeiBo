package com.lyx.entity;

public class Friend {
		
		private int friendId;
		/**
		 * 关注人的用户名
		 */
		private String fromUsername;
		/**
		 * 被关注的用户名
		 */
		private String toUsername;
		/**
		 * 状态量，默认值为1，表示A关注B，2表示AB互粉
		 */
		private int status = 1;
		
		
		
		public int getFriendId() {
			return friendId;
		}
		public void setFriendId(int friendId) {
			this.friendId = friendId;
		}	
		public String getFromUsername() {
			return fromUsername;
		}
		public void setFromUsername(String fromUsername) {
			this.fromUsername = fromUsername;
		}
		public String getToUsername() {
			return toUsername;
		}
		public void setToUsername(String toUsername) {
			this.toUsername = toUsername;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		
		
		
		public Friend() {
			super();
		}
		public Friend(int friendId, String fromUsername, String toUsername, int status) {
			super();
			this.friendId = friendId;
			this.fromUsername = fromUsername;
			this.toUsername = toUsername;
			this.status = status;
		}
		@Override
		public String toString() {
			return "Friend [friendId=" + friendId + ", fromUsername=" + fromUsername + ", toUsername=" + toUsername
					+ ", status=" + status + "]";
		}
		
		
		
		
	
		
		
}
