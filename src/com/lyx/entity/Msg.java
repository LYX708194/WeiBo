package com.lyx.entity;

/**
 * 信息类，用于传输一些信息
 * @author 刘彦享
 *
 */
public class Msg {
		/**
		 * 返回的值
		 */
		private String result;
		/**
		 * 返回的对象
		 */
		private Object  message;
		
		
		public Msg(String result, Object message) {
			super();
			this.result = result;
			this.message = message;
		}
		
		public Msg() {
			super();
		}
		
		public String getResult() {
			return result;
		}
		public void setResult(String result) {
			this.result = result;
		}
		public Object getMessage() {
			return message;
		}
		public void setMessage(Object message) {
			this.message = message;
		}
		
		
		
		
		
}
