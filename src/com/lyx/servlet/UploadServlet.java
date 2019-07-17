package com.lyx.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.lyx.entity.Article;
import com.lyx.entity.User;
import com.lyx.service.ArticleEditService;
import com.lyx.service.UserService;

/**
 * 上传照片的servlet处理
 * @author 刘彦享
 *
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserService us = new UserService();
    User user = new User();
    Article article = new Article();
    ArticleEditService aes = new ArticleEditService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求和响应编码	
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");	
		
		
			try {
				//上传
				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				if(isMultipart) {//判断前台的form是否有multipart属性
					//DiskFileItemFactory是创建fileitem对象的工厂/实现类，主要用于临时内存和临时目录
					FileItemFactory factory = new DiskFileItemFactory();
					//ServletFileUpload负责上传的文件数据，并将表单中每个输入项包装成一个fileitem对象
					ServletFileUpload upload = new ServletFileUpload(factory);
					//通过parseRequest解析form中的所有请求字段，并保存到items集合中
					List< FileItem> items = upload.parseRequest(request);
					//遍历items中的数据,迭代器
					Iterator<FileItem> iter = items.iterator();
					while(iter.hasNext()) {
						FileItem  item = iter.next(); 
						//判断表单字段是文件字段还是普通form表单字段
						if(item.isFormField()) {    //普通表单字段
								//此类只处理上传文件的form表单，故不会有普通表单字段的处理
						}else {   //文件表单字段
							//文件上传
							// getFieldName是获取 普通表单字段的name值item.getFieldName()
							//getName()是获取 文件名 ,即图片的名称
							String fileName  = item.getName();
							//获取内容并上传
							//定义文件路径：指定上传的位置
							//获得表单的值，通过getstring表单中的name值item.getString().equals("name值");
							//因为这里只有一个表单提交为照片，故没有用到这个
							//获取服务器路径
//							String path = request.getSession().getServletContext().getRealPath("upload");
							String path =  "D:/apache-tomcat-9.0.17/upload";
							File file = new File(path,fileName);   //路径和文件名
							
							item.write(file); //上传
							System.out.println(fileName+"上传成功");
							//得到session对象
							HttpSession session = request.getSession();
							
							String method = request.getParameter("method");  //获得method参数
							//如果method参数为空，则为用户头像上传
							if(method==null||"".equals(method)) {
								user = (User) session.getAttribute("userInfo");
								//添加文件路径到userInfo中
								user.setPortrait("/upload/"+fileName);
//								System.out.println(user);
								//连接数据库修改路径
								if(us.updatePortrait(user) ) { //如果修改成功
									//将session的userInfo修改
									session.setAttribute("userInfo", user);
									// 修改完成后跳转到个人信息的界面
									request.getRequestDispatcher("/jsp/self.jsp").forward(request, response);
								}
								
							}else {  //否则为微博文章提交照片
								request.setAttribute("articlephoto", "/upload/"+fileName); //获得图片路径放入request域中
								request.getRequestDispatcher("/jsp/articleEdit.jsp").forward(request, response);
								
							}
							
							
							
						}
						
					}
					
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
