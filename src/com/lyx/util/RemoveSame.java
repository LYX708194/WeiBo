package com.lyx.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.lyx.entity.User;

/**
 * 去重操作
 * @author 刘彦享
 *
 */
public class RemoveSame {
	
	

	/**
	 * 由于得到的消息列表对象中有对象是重复的，在得到的对象中进行去除操作
	 * @param users
	 * @return 返回一个新的没有重复对象的集合
	 */
	public static List<User> removeSame(List<User> users){
		Set<User> set = new HashSet<User>();    
	    List<User> newList = new ArrayList<User>();    
	   for (Iterator<User> iter = users.iterator(); iter.hasNext();) {    
	         Object element = iter.next();    
	         if (set.add((User) element)) {   
		            newList.add((User) element);   
	         } 
	      }     
	   	users.clear();    
	   	users.addAll(newList);    
	     return users;
	}
	
	
	
}
