package com.lyx.entity;

public class Friend {
		
		private int friendId;
		/**
		 * 执行好友分组操作的用户id
		 */
		private int fromUserId;
		/**
		 * 被执行分组对象的id
		 */
		private int toUserId;
		/**
		 * 状态量，默认值为1，表示A关注B，2表示AB互粉，3表示B为A的黑名单
		 */
		private int status = 1;
		/**
		 * 好友分组的id
		 */
		private int groupId;
		
		
		public int getFriendId() {
			return friendId;
		}
		public void setFriendId(int friendId) {
			this.friendId = friendId;
		}
		public int getFromUserId() {
			return fromUserId;
		}
		public void setFromUserId(int fromUserId) {
			this.fromUserId = fromUserId;
		}
		public int getToUserId() {
			return toUserId;
		}
		public void setToUserId(int toUserId) {
			this.toUserId = toUserId;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public int getGroupId() {
			return groupId;
		}
		public void setGroupId(int groupId) {
			this.groupId = groupId;
		}
		
		/**
		 * 构造器
		 * @param friendId
		 * @param fromUserId
		 * @param toUserId
		 * @param status
		 * @param groupId
		 */
		public Friend(int friendId, int fromUserId, int toUserId, int status, int groupId) {
			super();
			this.friendId = friendId;
			this.fromUserId = fromUserId;
			this.toUserId = toUserId;
			this.status = status;
			this.groupId = groupId;
		}
		public Friend() {
			super();
		}
		
		
		
		
	
		
		
}
