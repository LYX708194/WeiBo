package com.lyx.dao;

import java.util.List;

import com.lyx.entity.Page;
import com.lyx.entity.User;

/**
 * 展示用户的dao接口
 * @author 刘彦享
 *
 */
public interface UserShowDao {
	
	/**
	 * 获得所有用户的数量	
	 * @return 
	 */
	public int getAllUserCount();
	
	/**
	 * 获得我的关注的人的总数量
	 * @param user 关注的人，即在线用户
	 * @param username 被关注人的用户名
	 * @return
	 */
	public int getMyFocusCount(User user);
	
	/**
	 * 获得我搜索的用户数，模糊搜索
	 * @param searchMsg
	 * @return
	 */
	public int getSearchUserCount(String searchMsg);
	
	/**
	 * 获得我的粉丝的总数量
	 * @param username 我的用户名
	 * @return
	 */
	public int getMyFansCount(String username);
	
	/**
	 * 获得所有用户列表并分页
	 * @return 成功返回一个对象集合，失败返回空
	 */
	public List<User> getAllUserByPage(Page page,String username);
	
	/**
	 * 获得我的关注的用户集合并分页
	 * @param page
	 * @param username
	 * @return 成功返回一个对象集合，失败返回空
	 */
	public List<User> getMyFocusByPage(Page page,User user);
	
	/**
	 * 获得我的粉丝的用户集合并分页
	 * @param page
	 * @param username
	 * @return 成功返回一个对象集合，失败返回空
	 */
	public List<User> getMyFansByPage(Page page,String username);
	
	
	
	/**
	 * 查找数据库中用户，模糊搜索字段
	 * @param searchMsg
	 * @return 成功返回一个对象集合，失败返回空
	 */
	public List<User> queryUserByPage(Page page,String searchMsg,String username);
	
	
	
}
