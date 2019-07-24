package com.lyx.service;

import java.util.List;

import com.lyx.dao.UserShowDao;
import com.lyx.daoImpl.UserShowDaoImpl;
import com.lyx.entity.Page;
import com.lyx.entity.User;

/**
 * 展示用户的逻辑处理
 * @author 刘彦享
 *
 */
public class UserShowService {

	UserShowDao usd = new UserShowDaoImpl();
	
	/**
	 * 搜索所有的用户
	 * @param page
	 * @return 成功返回一个对象集合，失败返回空
	 */
	public List<User>  getAllUserByPage(Page page,String username){
		page.setTotalCount(usd.getAllUserCount());
		if(page.getCurrentPage()!=0) {
			return usd.getAllUserByPage(page,username);
		}
		return null;	
	}
	
	
	/**
	 * 搜索我的关注的人，得到一个集合
	 * @param page
	 * @param user 用户
	 * @param username  被关注的人
	 * @return 成功返回一个对象集合，失败返回空
	 */
	public List<User> getMyFocusByPage(Page page,User user){
		page.setTotalCount(usd.getMyFocusCount(user));
		if(page.getCurrentPage()!=0) {
			return usd.getMyFocusByPage(page, user);
		}
		return null;
	}
	
	/**
	 * 搜索我的粉丝，得到一个集合
	 * @param page
	 * @param user 用户
	 * @param username  关注的人
	 * @return 成功返回一个对象集合，失败返回空
	 */
	public List<User> getMyFansByPage(Page page,User user){
		page.setTotalCount(usd.getMyFansCount( user.getUsername()));
		if(page.getCurrentPage()!=0) {
			return usd.getMyFansByPage(page, user.getUsername());
		}
		return null;
	}
	
	/**
	 * 模糊搜索，根据字段在user表中找到相似的user对象
	 * @param page
	 * @param user
	 * @param searchMsg
	 * @return 成功返回一个对象集合，失败返回空
	 */
	public List<User> getSearchUserByPage(Page page,User user,String searchMsg){
		page.setTotalCount(usd.getSearchUserCount(searchMsg));
		if(page.getCurrentPage()!=0&&searchMsg!=null) {
			return usd.queryUserByPage(page, searchMsg,user.getUsername());
		}
		return null;
	}
	
}
