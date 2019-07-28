<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    <!-- jstl -->     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>微博|我和ta的聊天</title>
<link rel="stylesheet" type="text/css" href="/WeiBo/css/myarticle.css">
</head>
<body>

双方之间的具体消息展示
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
									<li><a href="/WeiBo/MessageListServlet">消息</a></li>
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
		<c:if test='${requestScope.msg!=null||requestScope.msg!="" }'>
		<font color=blue size=30>${requestScope.msg}</font>
		</c:if>
		<!-- 展示聊天对象的昵称 ,点击可以跳转到用户的主页<a href="ArticleShowServlet?method=other&currentPage=1&thisUser=${requestScope.thisUser.username }"> </a>-->
		<div><h1> ${requestScope.thisUser.nickname } </h1></div>
		<hr><br>
		<!-- 消息的展示 -->
		<c:forEach items="${requestScope.msgList }" var="a">
			
				<div >
				<c:if test="${a.sendUser!=sessionScope.userInfo.username}">
					<a href="ArticleShowServlet?method=other&currentPage=1&thisUser=${requestScope.thisUser.username}" >
								<img alt="ta的头像" src="${requestScope.thisUser.portrait }" style="width: 30px;height: 30px;border-radius: 2px;">
					</a>&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;${a.sendTime }
					<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color=blue size=5>${a.messageComtent }</font></c:if>
					<c:if test="${a.sendUser==sessionScope.userInfo.username}">
					<a href="/WeiBo/jsp/self.jsp" >
								<img alt="我的头像" src="${sessionScope.userInfo.portrait }" style="width: 30px;height: 30px;border-radius: 2px;">
					</a>&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;${a.sendTime }
					<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color=blue size=5>${a.messageComtent }</font></c:if>
				</div>
			
							
		</c:forEach>		<hr><br>
		<!-- 消息发送框 -->
		<div>
			<h4>发送消息：</h4>
			<form action="SendMessageServlet?method=send&thisUser=${requestScope.thisUser.username }" method="post" id="sendsm" name = "sendsm">
				<input type="text" name="sendmsg" id="sendmsg" style="width:300px; height:40px">
				<input type="submit" value = "发送" style="display:inline-block;background-color:#808080 ;width:70px;height:30px;">
			</form>		<br>
			
		</div>		
		<c:if test='${requestScope.sMsg!=null||requestScope.sMsg!="" }'>
				<font color=blue size=30>${requestScope.sendMsg}</font>
		</c:if>
		</div>
	</div>
			
			<div id = "down"> </div>
			
</body>
</html>