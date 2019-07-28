package com.lyx.util;

/**
 * 将前端获得字符串html转义
 * @author 刘彦享
 *
 */
public class HTMLUtil {
	
	
	public static String HTMLEncod(String html) {
		String getStr = html;
		getStr = getStr.replace("<", "&lt;");				
		getStr = getStr.replace(">", "&gt;");
		getStr = getStr.replace("&", "and");
		getStr = getStr.replace(":", "&quot;");
		getStr = getStr.replace("%", "&nbsp;");
		
		return getStr;
	}
	
	
	
}
