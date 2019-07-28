<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    <!-- jstl -->     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>微博|修改个人信息</title>
 <link rel="stylesheet" type="text/css" href="/WeiBo/css/self.css">
 <style type="text/css">
 .formMsg{
			font-size:25px;
		}
		#submitPhoto,#submitInfo{
			display:inline-block;/*显示成盒子大小*/
			background-color:#808080 ;
			width:70px;
			height:30px;
		}
	input{
			border:0;/*将input的边框改为0*/
			box-sizing:border-box;
			font:50px;
		}		
		#usershow,#userphoto{
			MARGIN: 30px 56px;
		}
 </style>
  <script type="text/javascript">
 function openwindow() {
	window.open("/jsp/articleEdit.jsp", "写微博", "height=500, width=500, toolbar =no, menubar=no, scrollbars=no, resizable=yes, location=no, status=no");
}
 
 </script>
</head>
<body>
			<!-- 顶部的菜单栏 -->
			<nav id="first">
				<div id="first_menu">
					<!-- 跳到servlet，对用户的cookie进行注销 -->
				    <a  href="http://localhost:8080/WeiBo/ClearLoginServlet">退出登录</a>
					<a  href="/WeiBo/jsp/self.jsp" >个人信息</a>
					<a href="/WeiBo/UserShowServlet?method=myfocus">我的关注</a>
					<a href="/WeiBo/UserShowServlet?method=myfans">我的粉丝</a>
					<!-- 如果为管理员，则可查看所有人，进行操作 -->
					
					<a href="/WeiBo/UserShowServlet?method=all" >所有用户</a>
					
					<!-- 编辑文章 -->
					<a href="/WeiBo/jsp/articleEdit.jsp" >写微博</a>
					<a href="/WeiBo/ArticleShowServlet?method=my" >我的微博</a>
					<a href="/WeiBo/ArticleShowServlet?method=showMyCollect">我的收藏</a> 
					
				</div>
			</nav>
			<!-- 第二个导航栏 -->
			
			<header id="second">
			<!-- 显示头像 -->
							 <div id = "portrait">
								 <div id="welcome_msg">欢迎您：<font color = green> ${sessionScope.userInfo.nickname}</font>
								<img src="${sessionScope.userInfo.portrait}"  alt="我的头像" id="user_img"  /></div>
							 </div>
							 <!-- logo部分 -->
							<div class="logo"><!-- 如无法显示图片，则显示alt中的内容 -->
							 	<img alt="微博logo" src="/WeiBo/image/WeiBoLogo.jpg" width=150px height=135px>
							</div>
					<nav  id="second_menu">
						<!-- 导航栏部分_可选择部分 -->
							<div class="navbar">
								<ul>
									<li><a href="/WeiBo/jsp/self.jsp">我的主页</a></li>
									<li><a href="/WeiBo/MessageListServlet">消息</a></li>
									<li><a href="#">修改个人信息</a></li>
									<li><a href="/WeiBo/jsp/updatePwd.jsp">修改密码</a></li>
									<li><a href="/WeiBo/ArticleShowServlet?method=all&currentPage=1">发现微博</a></li>
									<li><a href="/WeiBo/ArticleShowServlet?method=star">热搜</a></li>
								</ul>	
							</div>					
					</nav>
					
			</header>
			<!-- 显示主要内容的地方 -->
			<div id="main_content">
					<br/><br/>
				<div id = "userphoto">
				<!-- 用户更换头像 -->
					<form action="/WeiBo/UploadServlet" method = "post" enctype = "multipart/form-data" class = "formMsg">
							修改头像：<input type = "file" name = "portrait" value ="">
							<input type = "submit" name  ="updatePhone"  id ="submitPhoto" value = "上传头像">
					</form>
				
				<!-- 用作个人主页的信息展示 -->
						<div id = "usershow">
								<form action="/WeiBo/UpdateUserInfoServlet" method="post" class = "formMsg">
											 我的昵称：	<input type = "text" name ="nickname" value = "${sessionScope.userInfo.nickname}" style="width:300px; height:35px" ><br/><br/>
											 家庭地址：	<input type = "text" name ="address" value = "${sessionScope.userInfo.address}"style="width:300px; height:35px" ><br/><br/>
											 个性签名：<input type = "text" name ="signature" value = "${sessionScope.userInfo.signature}"style="width:300px; height:35px" ><br/><br/>
											 自我介绍：<input type = "text" name ="selfIntroduction" value = "${sessionScope.userInfo.selfIntroduction}"style="width:300px; height:35px" ><br/><br/>
											
									 	  			  <input type = "submit" value = "修改"  id="submitInfo"><br/>
								 </form>
						</div>			
			</div>
			</div>
			<div id = "down"> </div>
			
					
					
</body>
</html>