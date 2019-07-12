package com.lyx.entity;

public class Group {
		/**
		 * 分组的id
		 */
		private int groupId;
		/**
		 * 分组的名称
		 */
		private String groupName;
		
		/**
		 * 构造器
		 * @param groupId
		 * @param groupName
		 */
		public Group(int groupId, String groupName) {
			super();
			this.groupId = groupId;
			this.groupName = groupName;
		}
		public Group() {
			super();
		}
		
		
		public int getGroupId() {
			return groupId;
		}
		public void setGroupId(int groupId) {
			this.groupId = groupId;
		}
		public String getGroupName() {
			return groupName;
		}
		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
		
		
}
