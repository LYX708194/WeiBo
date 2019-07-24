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
		
		/**
		 * 得到用户的信息，是否被封号
		 * @param username
		 * @return 返回1代表是普通用户，返回2代表被封号用户
		 */
		public int getUserStatus(String username);
		
		/**
		 * 封号
		 * @param username
		 * @return 成功返回true，失败返回false
		 */
		public boolean setUserBlack(String username);
		
		/**
		 * 解除封号
		 * @param username
		 * @return 成功返回true，失败返回false
		 */
		public boolean setUserOk(String username);
		
		/**
		 * 搜索friend表中是否有此关系
		 * @param fromUsername
		 * @param toUsername
		 * @return 有返回true，没有返回false
		 */
		public boolean queryFriend(String fromUsername,String toUsername);
		
		/**
		 * 得到俩个用户之间的关系
		 * @param fromUsername  关注人的用户名
		 * @param toUsername	被关注人的用户名
		 * @return 返回1代表前者关注后者，返回2代表二者互粉
		 */
		public int getRelation(String fromUsername,String toUsername);
		
		/**
		 * 修改二者之间的关系，设置friend表中的状态值，设为1代表取消互粉状态，设为2代表设置二人为互粉状态
		 * @param fromUsername
		 * @param toUsername
		 * @return 成功返回true，失败返回false
		 */
		public boolean updateRelation(String fromUsername,String toUsername,int status);
		
		/**
		 * 关注操作
		 * @param fromUsername 用户
		 * @param toUsername 被关注的人
		 * @return 成功返回true，失败返回false
		 */
		public boolean insertFriend(String fromUsername,String toUsername,int status);
		
		/**
		 * 取消关注
		 * @param fromUsername 用户
		 * @param toUsername 被关注的人
		 * @return 成功返回true，失败返回false
		 */
		public boolean deleteFriend(String fromUsername,String toUsername);
		
		
}
