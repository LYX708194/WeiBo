package com.lyx.entity;


/**
 * 分页的辅助类，用于封装分类的属性，
 * @author 刘彦享
 *
 */
public class Page {
		//总页数
		private int totalPage;
		//页面大小，即页面显示的数据数量
		private int pageSize;
		//总数据量
		private int totalCount;
		//当前页
		private int currentPage;
		
				
		public Page(int totalPage, int pageSize, int totalCount, int currentPage) {
			super();
			this.totalPage = totalPage;
			this.pageSize = pageSize;
			this.totalCount = totalCount;
			this.currentPage = currentPage;
		}
		public Page() {
			super();
		}
		
		public int getTotalPage() {
			return totalPage;
		}
		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public int getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}
		public int getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}
	
		
}
