package com.lyx.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *  MD5加密，单向加密，将密码转化为MD5转化后的32位字符串
 * @author 刘彦享
 *
 */
public class MD5Util {
	
		public static void main(String[] args) {
			String admin = MD5Encode("dgqb708194", "utf-8");
			System.out.println(admin);
		}
		
		/**
		 * 字符数组---储存返回的MD5处理结果的32位中的十六进制数字
		 */
		private static final String hexDigits[] = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
		
		/**
		 * MD5加密方法
		 * @param origin 要加密的密码字符串
		 * @param charsetname  编码
		 * @return 加密后的32位的字符串---大写MD5
		 */
		public static String MD5Encode(String origin,String charsetname) {
			String resultString = null;
			try {
				resultString = new String(origin);
				// 获取MessageDigest实例，并指定加密算法类型----生成一个MD5加密计算概要
				MessageDigest  md = MessageDigest.getInstance("MD5");
				if(charsetname == null || "".equals(charsetname)) { //字符串不存在或者字符串内容为空
					resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
				}else {
					// digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是实际上就是8位的字符
	            	//MD5的计算结果是一个128位的长整数，用字节表示为---16个字节       128 = 16 * 8
	                //一个byte是八位二进制bit，也就是2位十六进制字符（2的8次方等于16的2次方==256）
					resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return resultString.toUpperCase();	
		}
		
		/**
		 * 遍历8个byte，转化为16位进制的字符，即0-F
		 * @param b数组
		 * @return 32位16进制的字符串
		 */
		private static String byteArrayToHexString(byte[] b) {
			StringBuffer resultSb = new StringBuffer();
			for(int i=0;i<b.length;i++) {
	        	//调用处理单个byte的方法，拼接成一个字符串
				resultSb.append(byteToHexString(b[i]));
			}
			//将StringBuffer转换成String
			return resultSb.toString();
		}
		
		/**
		 * 一个byte（256位）-(-128~127)-通过16拆分为d1和d2（两个16以内的数字-----0<=x<16）充当数组下标
		 * @param b 单个byte
		 * @return
		 */
		private static String byteToHexString(byte b) {
			int n = b;
			if(n<0) {
				n += 256;
			}
			int d1 = n  / 16;
			int d2 = n % 16; 
			
			return hexDigits[d1] + hexDigits[d2];
		}
		
}
