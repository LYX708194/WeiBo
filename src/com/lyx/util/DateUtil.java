package com.lyx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 对时间的转化类
 * @author 刘彦享
 *
 */
public class DateUtil {
	
//	//测试代码	
//	public static void main(String[] args) {
//		System.out.println(getDate());
//	}
		/**
		 * 获得当前时间并转化为只到天的string输出
		 * @return 返回一个装有时间的字符串
		 */
		public static String getDateToDate() {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		}
		/**
		 * 获得当前时间并转化为到秒的string输出
		 * @return 返回一个装有时间的字符串
		 */
		public static String getDateToSecond() {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(date);
		}
		
}
