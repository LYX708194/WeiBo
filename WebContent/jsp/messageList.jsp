<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    <!-- jstl -->     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>微博|我的消息</title>
<link rel="stylesheet" type="text/css" href="/WeiBo/css/myarticle.css">
</head>
<body>
<nav id="first">
				<div id="first_menu">
					<!-- 跳到servlet，对用户的cookie进行注销 -->
				    <a  href="/WeiBo/ClearLoginServlet">退出登录</a>
					<a  href="/WeiBo/jsp/self.jsp">个人信息</a>
					<a href="/WeiBo/UserShowServlet?method=myfocus">我的关注</a>
					<a href="/WeiBo/UserShowServlet?method=myfans" >我的粉丝</a>
					<!-- 如果为管理员，则可查看所有人，进行操作 -->
					
					<a href="/WeiBo/UserShowServlet?method=all" >所有用户</a>
				
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
									<li><a href="/WeiBo/jsp/self.jsp">我的主页</a></li>
									<li><a href="#">消息</a></li>
									<li><a href="/WeiBo/jsp/update.jsp">修改个人信息</a></li>
									<li><a href="/WeiBo/jsp/updatePwd.jsp">修改密码</a></li>
									<li><a href="/WeiBo/ArticleShowServlet?method=all">发现微博</a></li>
									<li><a href="/WeiBo/ArticleShowServlet?method=star">热搜</a></li>
								</ul>	
							</div>
							
							
					</nav>
			</header>
			
			
		<div class = "show" id = "show">
			<div class = "left">
				<!-- 提示消息 -->
				<c:if test='${requestScope.sendMsg!=null||requestScope.sendMsg!="" }'>
				<font color=blue size=50>${requestScope.sendMsg}</font>
				</c:if>
				<c:if test='${requestScope.msg!=null||requestScope.msg!="" }'>
				<font color=blue size=50>${requestScope.msg}</font>
				</c:if>
			<c:if test='${sessionScope.userInfo.username=="admin" }'><!-- 如果为管理员 -->
				<!-- 消息发送框 -->
			<div>
				<h1>群发消息</h1>
				<form action="MessageListServlet?method=sendAll" method="post" id="sendsm" name = "sendsm">
					<input type="text" name="sendmsg" id="sendmsg" style="width:300px; height:40px">
					<input type="submit" value = "发送">
				</form>		
			</div>	
			</c:if>	
				<div><h1>我的消息</h1></div>
				<c:forEach items="${requestScope.users}" var = "a" >
					<div class = "item">
							<div ><br>
							<div  style="float:left; width:70px;height:70px;"><!-- 点击头像可跳转到他的主页 -->
								<a href="ArticleShowServlet?method=other&currentPage=1&thisUser=${a.username }" target="_blank">
								<img  src="${a.portrait }" style="width: 50px;height: 50px;border-radius: 2px;">
								</a>
								</div>
																
								<div class = "usermsg"  style="height:70px;" >
									
									<ul>
											<li><font color=black size=5>${a.nickname } </font> </li>
											<!-- 点击可跳转前往发送消息页面 -->
											<li><a href="SendMessageServlet?thisUser=${a.username }">
											 <font color=green size=6> ${a.lastestMsg }  </font>
											<c:if test="${a.unRead!=0}"><!-- 如果未读不为0 -->
											<font color=red size=5 >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${a.unRead }</font><!-- 未读消息的数量 -->
									</c:if></a></li> <!-- 最后一条消息 -->										
									</ul>
									
									
								</div>
								
								
								<br><hr>
							</div>
					</div>
				</c:forEach>
			</div>
		</div>
			
			
			<div id = "down"> </div>
</body>
</html>