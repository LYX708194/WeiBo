package com.lyx.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页的辅助类，用于封装分类的属性，
 * @author 刘彦享
 *
 */
public class Page {
		//总页数
		private int totalPage;
		//页面大小，即页面显示的数据数量
		private int pageSize = 10;    //默认每页10条数据    
		//总数据量
		private int totalCount;
		//当前页
		private int currentPage;
		//存放查询到的文章集合
		private List<Article>  object = new ArrayList<Article>();
		//存放查询到的用户集合
		private List<User>   users = new ArrayList<User>();
		//存放查询到的评论集合
		private List<Comment> comments = new ArrayList<Comment>();
		
				
		
		//构造器
		public Page(int totalPage, int pageSize, int totalCount, int currentPage, List<Article> object,List<User> users,List<Comment> comments) {
			super();
			this.totalPage = totalPage;
			this.pageSize = pageSize;
			this.totalCount = totalCount;
			this.currentPage = currentPage;
			this.object = object;
			this.users = users;
			this.comments = comments;
		}
		public Page(int pageSize, int totalCount, int currentPage) {
			super();
			if(this.totalCount< this.pageSize) {
				this.totalPage = 1;
			}else {
				this.totalPage = this.totalCount % this.pageSize ==0 ? this.totalCount/this.pageSize : this.totalCount/this.pageSize +1   ;
			}
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
		public void setTotalPage(int totalCount ,int pageSize) {
			if(totalCount< pageSize) {
				this.totalPage = 1;
			}else {
				this.totalPage = totalCount %pageSize ==0 ? totalCount/pageSize : totalCount/pageSize +1   ;
			}
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
		public List<Article> getObject() {
			return object;
		}
		public void setObject(List<Article> articleList) {
			this.object = articleList;
		}
		public List<User> getUsers() {
			return users;
		}
		public void setUsers(List<User> users) {
			this.users = users;
		}
		public List<Comment> getComments() {
			return comments;
		}
		public void setComments(List<Comment> comments) {
			this.comments = comments;
		}
		@Override
		public String toString() {
			return "Page [totalPage=" + totalPage + ", pageSize=" + pageSize + ", totalCount=" + totalCount
					+ ", currentPage=" + currentPage + "]";
		}
		
		
		
}
