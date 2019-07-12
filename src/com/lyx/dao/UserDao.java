package com.lyx.dao;


import com.lyx.entity.User;

public interface UserDao {
		
		/**
		 * 用户和管理员的登录
		 * @param user
		 * @return  0表示管理员，1为普通用户，2为黑名单用户，3为登录失败
		 */
		public int login(User user);
		
		/**
		 * 注册功能 ，注意注册的时间，需将注册时间拷到user中的time中
		 * @param user
		 * @return  注册成功为true，失败为false
		 */
		public boolean register(User user);
		
		/**
		 * 通过用户名查找个人信息
		 * @param username
		 * @return 返回一个存放用户信息的user对象
		 */
		public User userInfo(String username);
		
		/**
		 * 通过邮箱查找个人信息
		 * @param username
		 * @return 返回一个存放用户信息的user对象
		 */
		public User emailInfo(String email);
		
		/**
		 * 修改密码
		 * @param username
		 * @return  成功为true，失败为false
		 */
		public boolean updatePwd(User user);
		
		/**
		 * 修改个人信息
		 * @param user
		 * @return  成功为true，失败为false
		 */
		public boolean updateContent(User user);
		
		/**
		 * 修改头像的地址
		 * @param user
		 * @return 成功为true，失败为false
		 */
		public boolean updatePortrait(User user);
		
		/**
		 * 检查是否存在此username的用户
		 * @param username
		 * @return 返回真表示存在，假为不存在
		 */
		public boolean repetitionUsername(String username);
		
		/**
		 * 检查是否存在此email的用户
		 * @param username
		 * @return 返回真表示存在，假为不存在
		 */
		public boolean repetitionEmail(String email);
		
}
