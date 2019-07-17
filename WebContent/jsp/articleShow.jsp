<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    <!-- jstl -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>微博|发现生活中的美</title>
 <link rel="stylesheet" type="text/css" href="/WeiBo/css/myarticle.css">
</head>
<body>


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
									<li><a href="/WeiBo/jsp/self.jsp">我的主页</a></li>
									<li><a href="#">消息</a></li>
									<li><a href="/WeiBo/jsp/update.jsp">修改个人信息</a></li>
									<li><a href="/WeiBo/jsp/updatePwd.jsp">修改密码</a></li>
									<li><a href="/WeiBo/ArticleShowServlet?method=all">发现微博</a></li>
								</ul>	
							</div>
							<!-- 搜索框部分 -->
							<div class="search">
									<form action="ArticleShowServlet?method=searchMy&currentPage=1" id="searchComtent" name="searchComtent" method="post">
										<input type="text" id="searchComtent" name="searchComtent" placeholder="通过关键词搜索微博">
										<input  type="submit" value="搜索">
									</form>
							</div>
							
					</nav>
			</header>
			<!-- 显示数据 -->
			<div class = "show" id = "show">
				<div class = "left">
				<c:if test='${requestScope.msg!=null||requestScope.msg!="" }'>
				<font color=blue size=50>${requestScope.msg}</font>
				</c:if>
				<!--  如果点击头像，则展示用户资料-->
				<c:if test='${requestScope.method=="other" }'> 
					<div>
								<h1>个人信息</h1>  <br/>
								<div>账			号：${requestScope.user1.username}</div>
								<div>昵			称：${requestScope.user1.nickname}</div>
								<div>邮			箱：${requestScope.user1.email}</div>
								<div>家庭地址：${requestScope.user1.address}</div>
								<div>个性签名：${requestScope.user1.signature}</div>
								<div>自我介绍：${requestScope.user1.selfIntroduction}</div>
								<div>注册时间：${requestScope.user1.time}</div><br/>
					</div>
					<hr>
				</c:if>
				<h1>ta的微博</h1>  
					<c:forEach items ="${requestScope.p.object}"  var = "a" >
						<div class = "item">
							<!--文章作者的头像和昵称 -->
								        <div class="header">
								        	<!--作者的头像 -->
								            <div class="author-img">
								            	<!--点击跳转到此人的个人空间 -->
								                <a href="ArticleShowServlet?method=other&currentPage=1&username=${a.author }" target="_blank" style="margin: 5px 0px 5px 5px;">
								                	<img src="${a.authorPortrait}" style="width: 40px;height: 40px;border-radius: 2px;">
								                </a>
								            <!--作者的昵称，可点击跳转到此人的个人空间 -->
								            <a href="ArticleShowServlet?method=other&currentPage=1&username=${a.author }" target="_blank"  id="authornickname" style="color:black;">${a.authorNickname} </a>
								            <font color=gray> ${a.publicedTime }</font>
								            </div>
										</div>
										<!--文章内容和图片 -->
										<div class="comtent">
										<a href="#待做" target="_blank"><!-- 打开新窗口，跳转到单篇微博展示页面 -->
											${a.comtent }		<br/>
											<c:if test='${a.photo!=null||a.photo!=""}'>
											<div><img src="${a.photo}" style="width:200px;border-radius: 2px;"></div>
											</c:if>
											</a>
										</div>
										<!--文章底部的转发，评论点赞和收藏 -->
										<div class="botton">
											<ul>
												<li style="display:inline"><a href = "ArticleShowServlet?premethod=collect&method=${requestScope.method}&articleId=${a.articleId}&currentPage=${requestScope.p.currentPage}" onclick= "return confirm('收藏/取消收藏这篇微博')">
													<c:if test="${a.collect }"><em style="color : yellow">收藏${a.collectNum }</em></c:if>
													<c:if test="${a.collect==false }"><em style="color : black">收藏${a.collectNum }</em></c:if>
												</a></li>   
												<!-- 点击评论会跳到显示单篇文章的页面，在那个页面进行评论，此页面只能进行点赞和收藏操作和显示数据 -->
												<li style="display:inline"><a href = "#"><em style="color : black">评论${a.commentNum }</em></a></li>
												<li style="display:inline"><a href = "ArticleShowServlet?premethod=like&method=${requestScope.method}&articleId=${a.articleId}&currentPage=${requestScope.p.currentPage}">
													<c:if test="${a.like }"><em style="color : yellow">点赞${a.likeNum }</em></c:if>
													<c:if test="${a.like ==false}"><em style="color : black">点赞${a.likeNum }</em></c:if>
												</a></li>
											</ul>
								        </div>
								       					<!-- 如果这条微博是此正在登录的用户所发或者为管理员，则可以删除这篇文章 -->
									       <div ><c:if test='${a.author==sessionScope.userInfo.username||sessionScope.userInfo.username=="admin" }'>
											  <a href="ArticleShowServlet?premethod=delete&method=my&articleId=${a.articleId}&currentPage=${requestScope.p.currentPage}" class="delete" id="delete" style="color: red;"  onclick= "return confirm('你确定要删除这篇文章吗？删除后不可恢复。')">删除</a>
											 </c:if>
											 <c:if test='${sessionScope.userInfo.username=="admin" }'><!-- 如果为管理员，则可以置顶热搜 -->
											 <a href = "#" style="color: green;">设为热搜</a>
											 </c:if>
											 </div>
									 <hr>	
						</div>
					</c:forEach>
					
							 <!-- 分页选择链接     -->
							 <div id="paging">
							 <c:if test="${requestScope.p.totalPage!=0}">
							 		<span id="page_number" >当前第 ${requestScope.p.currentPage} 页，总共 ${requestScope.p.totalPage} 页             </span>		
									<c:if test="${requestScope.p.currentPage>1}">
									<a href="ArticleShowServlet?method=${requestScope.method}&currentPage=1" id="first_page" class="page_controller">首页</a>					
									<!-- 利用el表达式的三目运算符进行判断 -->
									<a href="ArticleShowServlet?method=${requestScope.method}&currentPage=${(requestScope.p.currentPage==1)?1:requestScope.p.currentPage-1}" id="pervious_page"  class="page_controller">上一页</a>
									</c:if>
									<c:if test="${requestScope.p.currentPage < requestScope.p.totalPage}">
									<a href="ArticleShowServlet?method=${requestScope.method}&currentPage=${(requestScope.p.currentPage==requestScope.p.totalPage)?requestScope.p.totalPage:requestScope.p.currentPage+1}" id="next_page"  class="page_controller">下一页</a>
									<a href="ArticleShowServlet?method=${requestScope.method}&currentPage=${requestScope.p.totalPage}" id="last_page"  class="page_controller">尾页</a>						
							 		</c:if>
							</c:if>
							 </div>
					
		
					
				</div>
			</div>	


			<div id = "down"> </div>
</body>
</html>