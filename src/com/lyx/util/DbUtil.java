package com.lyx.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DbUtil {

		//数据库名称
		private static final String CON="jdbc:mysql://localhost:3306/weibo?&useUnicode = TRUE&characterEncoding = utf-8&useSSL=FALSE&serverTimezone = GMT";
		//用户名
		private static final String USERNAME = "root";
		//密码
		private static final String PASSWORD = "123456";
		//驱动名称
		private static final String JDBCNAME = "com.mysql.jdbc.Driver";
		
		//创建集合放置connection（连接池） 
		private static ArrayList<Connection> conlist = new ArrayList<Connection>();
		//首先执行	创建连接池
	    static {
	    	for(int i = 0;i<5;i++) {
	    		Connection con = creatConnection();  //连接池创建多个连接，使用时不用新创建
	    		conlist.add(con);
	    	} 
	    }
				
		 /**
		  * 创建连接
		  * @return
		  */
	    private static Connection creatConnection() {
			try {
				Class.forName(JDBCNAME);	
				return (Connection) DriverManager.getConnection(CON, USERNAME, PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			return null;        //try中return可能被跳过
		}
		
	    /**
	     * 得到连接
	     * @return
	     */
	    public static Connection getConnection() {
	    	if(conlist.isEmpty()==false) {
	    		Connection con = conlist.get(0); //从最后一个获取，然后删除
	    		conlist.remove(0);
	    		return con;
	    	}else {
	    		return creatConnection();   //如果为空，创建新连接
	    	}
	    }
	    
	    /**
	     * 关闭连接，close的优化，关闭三个连接
	     * @param rs
	     * @param pstmt
	     * @param con
	     */
	    public static void close(ResultSet rs,Statement stmt,Connection con) {
	    	closeResultSet(rs);
	    	closeStatement(stmt);
	    	closeConnection(con);
	    }
	    /**
	     * 关闭两个连接，
	     * @param pstmt
	     * @param con
	     */
	    public static void close(Statement stmt,Connection con) {
	    	closeStatement(stmt);
	    	closeConnection(con);
	    }
	    
	    private static void closeResultSet(ResultSet rs) {
	    	try {
				if(rs!=null)
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    private static void closeStatement(Statement stmt) {
	    	try {
				if(stmt!=null)
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
		private static void closeConnection(Connection con) {
			conlist.add(con);  //归还连接池资源
		}
}
