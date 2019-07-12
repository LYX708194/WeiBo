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
		 * 获得当前时间并转化为特定格式的string输出
		 * @return
		 */
		public static String getDate() {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		}
}
