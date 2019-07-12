package com.lyx.util;

public class ValidateUtil {
		
	/**
	 * 判断字符串是否为空
	 * @param m
	 * @return
	 */
	public static boolean isNullString(String m) {
		if(m==null||m.equals("")) {
			return true;
		}
		return false;
	}
	
}
