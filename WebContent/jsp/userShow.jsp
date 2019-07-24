<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    <!-- jstl --> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>微博|用户列表</title>
 <link rel="stylesheet" type="text/css" href="/WeiBo/css/myarticle.css">
<!-- 展示用户的页面 -->
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
					<c:if test='${sessionScope.userInfo.username=="admin"}'>
					<a href="/WeiBo/UserShowServlet?method=all" >所有用户</a>
					</c:if>
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
							<!-- 搜索框部分 -->
							<div class="search">
									<form action="UserShowServlet?method=mysearch&currentPage=1" id="searchUser" name="searchUser" method="post">
										<input type="text" id="searchUser" name="searchUser" placeholder="通过关键词搜索用户">
										<input  type="submit" value="搜索">
									</form>
							</div>
							
					</nav>
			</header>

			<div class = "show" id = "show">
				<div class = "left">
				<!-- 提示消息 -->
				<c:if test='${requestScope.msg!=null||requestScope.msg!="" }'>
				<font color=blue size=50>${requestScope.msg}</font>
				</c:if>
				<div>
					<c:if test='${requestScope.method=="myfocus" }'><h1>我的关注</h1>  </c:if>
					<c:if test='${requestScope.method=="myfans" }'><h1>我的粉丝</h1>  </c:if>
					<c:if test='${requestScope.method=="all" }'><h1>所有用户</h1>  </c:if>
				</div>		
				<c:forEach items="${requestScope.p.users }" var="a">
						<div class = "item">
							<div>
							<div  style="float:left; width:70px;height:70px;"><!-- 点击头像可跳转到他的主页 -->
								<a href="ArticleShowServlet?method=other&currentPage=1&thisUser=${a.username }" target="_blank">
								<img alt="ta的头像" src="${a.portrait }" style="width: 50px;height: 50px;border-radius: 2px;">
								</a>
							</div>
								<div class = "usermsg"><!-- 展示用户信息，如昵称，地址，个性签名和关注关系等 -->
									<ul>
										<li> ${a.nickname } </li>
										<li> 地址:：${a.address }  </li>
										<li> 个性签名: ：${a.signature } </li>
										<li>	 自我介绍：	${a.selfIntroduction }	</li>
									</ul>
								</div>
								<div>
									<font color="green">你们的关系是：
									<c:if test="${a.relation==2 }">互粉好友</c:if>
									<c:if test='${requestScope.method=="myfocus"  }'>你的关注</c:if>
									<c:if test='${requestScope.method=="myfans"  }'>你的粉丝</c:if>
									</font>
									
									<c:if test="${a.relation!=1&&a.relation!=2 }"><!-- 没有关注关系 -->
									<a href="UserShowServlet?premethod=focus&method=${requestScope.method}&thisUser=${a.username }&currentPage=1" onclick= "return confirm('关注ta')">关注</a>
									</c:if>
									<c:if test="${a.relation==1||a.relation==2 }"><!-- 为互粉关系或者关注关系 -->
									<a href="UserShowServlet?premethod=unfocus&method=${requestScope.method}&thisUser=${a.username}&currentPage=1" onclick= "return confirm('确定你要取消关注ta吗')">取消关注</a>
									</c:if>
									<c:if test='${sessionScope.userInfo.username=="admin"}'><!-- 如果为管理员 -->
									<c:if test="${a.status==1 }"><a href="UserShowServlet?method=${requestScope.method}&premethod=black&thisUser=${a.username}&currentPage=1"onclick= "return confirm('确定要对ta封号吗')">封号</a></c:if>
									<c:if test="${a.status==2 }"><a href="UserShowServlet?method=${requestScope.method}&premethod=out&thisUser=${a.username}&currentPage=1"onclick= "return confirm('确定要解除ta的封号吗')">解除封号</a></c:if>
									</c:if>
									<a href="#" >发送私信</a>
								</div>
							</div>
						<hr>
						</div>
				</c:forEach>
				
				
							<!-- 分页选择链接     -->
							 <div id="paging">
							 <c:if test="${requestScope.p.totalPage!=0}">
							 		<span id="page_number" >当前第 ${requestScope.p.currentPage} 页，总共 ${requestScope.p.totalPage} 页             </span>		
									<c:if test="${requestScope.p.currentPage>1}">
									<a href="UserShowServlet?method=${requestScope.method}&currentPage=1" id="first_page" class="page_controller">首页</a>					
									<!-- 利用el表达式的三目运算符进行判断 -->
									<a href="UserShowServlet?method=${requestScope.method}&currentPage=${(requestScope.p.currentPage==1)?1:requestScope.p.currentPage-1}" id="pervious_page"  class="page_controller">上一页</a>
									</c:if>
									<c:if test="${requestScope.p.currentPage < requestScope.p.totalPage}">
									<a href="UserShowServlet?method=${requestScope.method}&currentPage=${(requestScope.p.currentPage==requestScope.p.totalPage)?requestScope.p.totalPage:requestScope.p.currentPage+1}" id="next_page"  class="page_controller">下一页</a>
									<a href="UserShowServlet?method=${requestScope.method}&currentPage=${requestScope.p.totalPage}" id="last_page"  class="page_controller">尾页</a>						
							 		</c:if>
							</c:if>
							 </div>

				</div>
			</div>
			
			<div id = "down"> </div>
	
</body>
</html>