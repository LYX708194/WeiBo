package com.lyx.service;

import com.lyx.dao.UserDao;
import com.lyx.daoImpl.UserDaoImpl;
import com.lyx.entity.User;
import com.lyx.util.ValidateUtil;

public class UserService {
		 
		
		private  UserDao ud = new UserDaoImpl();
		
		
		/**
		 * 通过Email搜索该用户的个人信息
		 * @param email 
		 * @return 成功返回该对象。失败为空
		 */
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
		
		/**
		 * 修改用户的账号权限，即管理员处理封号或解除封号
		 * @param username
		 * @param status
		 * @return 成功为true，失败为false
		 */
		public boolean updateUserStatus(String username,int status) {
			if(status==1) {  // 传参为1，解除封号
				return ud.setUserOk(username); 
			}else if(status==2) {  //传参为2，将该用户封号
				return ud.setUserBlack(username);
			}
			return false;  //若传参不为1或2，则失败
		}
		
		
		/**
		 * 得到用户间关系的逻辑处理
		 * @param fromUsername
		 * @param toUsername
		 * @return  返回0代表A不关注B，返回1代表A关注B，返回2代表AB互粉
		 */
		public int getRelation(String fromUsername,String toUsername) {
			if(ud.getRelation(fromUsername, toUsername)==1) {
				return 1;  //A关注B
			}else if(ud.getRelation(fromUsername, toUsername)==2) {
				return 2; //AB互粉
			}
			return 0;  //A不关注B，但B是否关注B不知道
		}
		
		/**
		 * 关注朋友的操作
		 * @param fromUsername
		 * @param toUsername
		 * @return 成功返回true，失败返回false
		 */
		public boolean insertFriend(String fromUsername,String toUsername) {
			if(ud.queryFriend(fromUsername, toUsername)) {//如果存在此关系
				return false;  
			}else {  //如果不存在此关系
				
				if(ud.queryFriend(toUsername, fromUsername)) {   //查找另一方是否关注我
					 //若对方关注我，则将对方与我的关注关系转为互相关注，将我的关注数据插入表中
					//将二人关系设为互相关注	
					if(ud.insertFriend(fromUsername, toUsername, 2)&&ud.updateRelation(toUsername, fromUsername, 2) ) {
						return true;
					}
				}else {   //另一方并不关注我，则插入friend表中并设为单向关注
					if(ud.insertFriend(fromUsername, toUsername, 1))
							return true;
				}
					
			}
			return false;
		}
		
		/**
		 * 取消关注的操作，逻辑处理
		 * @param fromUsername
		 * @param toUsername
		 * @return 成功返回true，失败返回false
		 */
		public boolean deleteFocus(String fromUsername,String toUsername) {
			if(ud.queryFriend(fromUsername, toUsername)) {
				if(ud.queryFriend(toUsername, fromUsername)) {   //若二人是互粉关系
					ud.updateRelation(toUsername, fromUsername, 1);    //修改对方的关注信息为单向关注
					return ud.deleteFriend(fromUsername, toUsername);  //再删除我的关注信息
				}else {  //二人只是单向关注
					return ud.deleteFriend(fromUsername, toUsername);  //那么只需要删除关注信息即可
				}
			}
			return false;
		}
		
}
