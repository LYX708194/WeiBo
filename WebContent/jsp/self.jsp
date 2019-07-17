<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>微博|我的主页</title>
<!-- 登录成功后，进行个人信息的查看 -->
	<!-- 我的页面的样式 -->
 <link rel="stylesheet" type="text/css" href="/WeiBo/css/self.css">
</head>

<body>
	<!-- 顶部的菜单栏 -->
			<nav id="first">
				<div id="first_menu">
					<!-- 跳到servlet，对用户的cookie进行注销 -->
				    <a  href="/WeiBo/ClearLoginServlet">退出登录</a>
					<a  href="/WeiBo/jsp/self.jsp">个人信息</a>
					<a href="#">我的关注</a>
					<a href="#">我的粉丝</a>
					<!-- 先跳转servlet 查询第一页的所有人信息 -->
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
									<li><a href="#">我的主页</a></li>
									<li><a href="#">消息</a></li>
									<li><a href="/WeiBo/jsp/update.jsp">修改个人信息</a></li>
									<li><a href="/WeiBo/jsp/updatePwd.jsp">修改密码</a></li>
									<li><a href="/WeiBo/ArticleShowServlet?method=all&currentPage=1">发现微博</a></li>
								</ul>	
							</div>
							<!-- 搜索框部分 -->
							<div class="search">
									<form action="ArticleShowServlet?method=searchAll&currentPage=1">
										<input type="text" placeholder="搜索你感兴趣的内容和人">
										<input  type="submit" value="搜索">
									</form>
							</div>
							
					</nav>
			</header>
			<!-- 显示主要内容的地方 -->
			<div id="main_content">
				
						<!-- 放置个人信息 -->
						<div id="main_content-left-top">
							<!-- 个人信息 -->
							<div id="user_info_show">
								<br/><br/>
								<div style = "margin : center"><h1>个人信息</h1>  </div>
								<br/>
								<div>账			号：${sessionScope.userInfo.username}</div><br/>
								<div>邮			箱：${sessionScope.userInfo.email}</div><br/>
								<div>昵			称：${sessionScope.userInfo.nickname}</div><br/>
								<div>家庭地址：${sessionScope.userInfo.address}</div><br/>
								<div>个性签名：${sessionScope.userInfo.signature}</div><br/>
								<div>自我介绍：${sessionScope.userInfo.selfIntroduction}</div><br/>
								<div>注册时间：${sessionScope.userInfo.time}</div><br/><br/>
							 </div> 
								 <div id = "link" >
									<a href="/WeiBo/jsp/updatePwd.jsp" id="forget" ><font color="blue" >修改密码</font></a>
									<a href="/WeiBo/jsp/update.jsp" id="register"><font color="blue" >修改个人信息</font></a>
								</div>
						</div>
			</div>
			
			<div id = "down"> </div>
</body>
</html>