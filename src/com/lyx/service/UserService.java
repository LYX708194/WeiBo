package com.lyx.service;

import com.lyx.dao.UserDao;
import com.lyx.daoImpl.UserDaoImpl;
import com.lyx.entity.User;
import com.lyx.util.ValidateUtil;

public class UserService {
		 
		
		private  UserDao ud = new UserDaoImpl();
		
		
		
		public User emailInfo(String email) {
			if(ValidateUtil.isNullString(email)) {
				return null;
			}else {
				return ud.emailInfo(email);
			}
		}
		
		/**
		 * 登录的逻辑处理，先判断是否存在此用户，再进行登录
		 * @param user
		 * @return  0表示管理员，1为普通用户，2为黑名单用户，3为登录失败
		 */
		public int login(User user) {
			/**
			 * 判空
			 */
			if(ValidateUtil.isNullString(user.getUsername())&&ValidateUtil.isNullString(user.getPassword())) {
				return 3;
			}else if(ud.repetitionUsername(user.getUsername())) {  //检查数据库中是否存在此用户，若存在则登录
				return ud.login(user);
			}
			return 3;
		}
		
		/**
		 * 对用户个人信息的查询
		 * @param username
		 * @return 查询成功则返回此用户信息的对象，失败返回空
		 */
		public User userInfo(String username) {
			if(ud.repetitionUsername(username)) {
				return ud.userInfo(username);
			}else {
				return null;
			}
		}
		
		/**
		 * 注册的逻辑处理，判断是否存在用户名和邮箱，再进行是否注册，若存在则不能注册
		 * @param user
		 * @return 成功为true，失败为false
		 */
		public boolean register(User user) {
			if(ValidateUtil.isNullString(user.getUsername())&&ValidateUtil.isNullString(user.getPassword())&&ValidateUtil.isNullString(user.getEmail())) {
				return false;
			}else if(ud.repetitionUsername(user.getUsername())&&ud.repetitionEmail(user.getEmail())) {  //检查数据库中是否存在此用户，若存在则不能注册
				return false;
			}
			return ud.register(user);
		}
		
		/**
		 * 更新用户个人信息的逻辑处理 ，头像和密码单独处理
		 * 检查数据库中是否存在此用户名
		 * @param user
		 * @return  成功为true，失败为false
		 */
		public boolean updateContent(User user) {
			if(ud.repetitionUsername(user.getUsername()) ) {
				return ud.updateContent(user);
			}else {
				return false;
			}
		}
		
		/**
		 * 修改用户的头像在数据库中的地址的逻辑处理
		 * @param user
		 * @return 成功为true，失败为false
		 */
		public boolean updatePortrait(User user) {
			if(ud.repetitionUsername(user.getUsername())) {
				return ud.updatePortrait(user);
			}else {
				return false;
			}
		}
		
		/**
		 * 修改密码的逻辑处理
		 * @param user
		 * @return  成功为true，失败为false
		 */
		public boolean updatePwd(User user) {
			if(ud.repetitionUsername(user.getUsername())) {
				return ud.updatePwd(user);
			}else {
				return false;
			}
		}
}
