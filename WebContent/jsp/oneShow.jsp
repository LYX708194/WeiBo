<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    <!-- jstl -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>微博|具体信息</title>
<!-- 一条微博的具体内容，显示具体内容和评论 -->
 <link rel="stylesheet" type="text/css" href="/WeiBo/css/myarticle.css">
 <style type="text/css">
 .rp{
 	display:inline-block;/*显示成盒子大小*/
 	color:#808000;
 	text-decoration:none;
 	
 }
.rp:hover{/*鼠标经过a链接*/
			background-color: white;
		}
 </style>
</head>
<body>

<nav id="first">
				<div id="first_menu">
					<!-- 跳到servlet，对用户的cookie进行注销 -->
				    <a  href="/WeiBo/ClearLoginServlet">退出登录</a>
					<a  href="/WeiBo/jsp/self.jsp">个人信息</a>
					<a href="/WeiBo/UserShowServlet?method=myfocus">我的关注</a>
					<a href="/WeiBo/UserShowServlet?method=myfans">我的粉丝</a>
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
							
					</nav>
			</header>
			
			<div class = "show" id = "show">
				<div>
						<div class="header">
							<!--作者的头像 -->
							<div class="author-img">
								<!--点击跳转到此人的个人空间 -->
							<a href="ArticleShowServlet?method=other&currentPage=1&thisUser=${requestScope.thisArticle.author }"  style="margin: 5px 0px 5px 5px;">
								<img src="${requestScope.thisArticle.authorPortrait}" style="width: 40px;height: 40px;border-radius: 2px;">
							</a>
							<!--作者的昵称，可点击跳转到此人的个人空间 -->
							<a href="ArticleShowServlet?method=other&currentPage=1&thisUser=${requestScope.thisArticle.author }"  id="authornickname" style="color:black; ">${requestScope.thisArticle.authorNickname} </a>
							&nbsp;&nbsp;&nbsp;
							<font color=gray> ${requestScope.thisArticle.publicedTime }</font>&nbsp;&nbsp;&nbsp;
							<c:if test="${a.star==1 }"><font color="red" size=50px;>hot</font></c:if>
							</div>
							</div><br/>
							
							<!--文章内容和图片 -->
							<div class="comtent">
							<font color=black> ${requestScope.thisArticle.comtent }	</font>	<br/>
							<c:if test='${requestScope.thisArticle.photo!=null||requestScope.thisArticle.photo!=""}'>
							<div><img src="${requestScope.thisArticle.photo}" style="width:200px;border-radius: 2px;"></div>
							</c:if>
							</div>
							<!--文章底部的转发，评论点赞和收藏 -->
										<div class="botton">
											<ul>
												<li style="display:inline"><a href = "OneArticleShowServlet?premethod=collect&articleId=${requestScope.thisArticle.articleId}&currentPage=${requestScope.p.currentPage}" onclick= "return confirm('收藏/取消收藏这篇微博')">
													<c:if test="${requestScope.thisArticle.collect }"><em style="color : yellow">收藏${requestScope.thisArticle.collectNum }</em></c:if>
													<c:if test="${requestScope.thisArticle.collect==false }"><em style="color : black">收藏${requestScope.thisArticle.collectNum }</em></c:if>
												</a></li>   
												<li style="display:inline"><em style="color : black">评论${requestScope.thisArticle.commentNum }</em></li>
												<li style="display:inline"><a href = "OneArticleShowServlet?premethod=like&articleId=${requestScope.thisArticle.articleId}&currentPage=${requestScope.p.currentPage}">
													<c:if test="${requestScope.thisArticle.like }"><em style="color : yellow">点赞${requestScope.thisArticle.likeNum }</em></c:if>
													<c:if test="${requestScope.thisArticle.like ==false}"><em style="color : black">点赞${requestScope.thisArticle.likeNum }</em></c:if>
												</a></li>
											</ul>
										
											
										
							</div>
							<!-- 如果这条微博是此正在登录的用户所发或者为管理员，则可以删除这篇文章 -->
							<div >
							<c:if test='${requestScope.thisArticle.author==sessionScope.userInfo.username||sessionScope.userInfo.username=="admin" }'>
							<a href="OneArticleShowServlet?premethod=delete&articleId=${requestScope.thisArticle.articleId}&currentPage=${requestScope.p.currentPage}" class="delete" id="delete" style="color: red;"  onclick= "return confirm('你确定要删除这篇文章吗？删除后不可恢复。')">删除</a>
							</c:if>
					 </div><hr>	
					 <div >
								<form action="OneArticleShowServlet?premethod=comment&articleId=${requestScope.thisArticle.articleId}&currentPage=${requestScope.p.currentPage}" method="post" id="commentsm" name = "commentsm" >
												<input type="text" name="commentMsg" value="" id="commentMsg" style="width:300px; height:35px">
												<input type="submit"value="发表评论" style="display:inline-block;background-color:#808080 ;width:70px;height:30px;">
								</form>
						</div>
						
					
										
						<!-- 进行评论的分页展示 -->
						<c:if test="${requestScope.p.comments == null }">
							<div><font color=blue size=50>该微博暂时还没有评论</font></div>
						</c:if>
						<h4>评论</h4>
						<br/>
						<c:if test="${requestScope.p.comments!=null }">
						<c:forEach items="${requestScope.p.comments }" var = "a">
						
							<!-- 设置一个隐藏表单来提交回复，但不显示 -->
										<form action="OneArticleShowServlet?premethod=reply&articleId=${requestScope.thisArticle.articleId}&commentid=${a.commentId}&currentPage=${requestScope.p.currentPage}&replyuser=${sessionScope.userInfo.username}&bereplyuser=${a.username}" method="post" id="replyform" name = "replyform" style=" display: none; " >
												<input type="hidden" name="replyMsg1" value="" id="replyMsg1" />
										</form>
										
										
						
							<div class = "item">
								<div  style="float:left; width:70px;height:70px;"><!-- 点击头像可跳转到他的主页 -->
								<a href="ArticleShowServlet?method=other&currentPage=1&thisUser=${a.username }">
								<img alt="ta的头像" src="${a.userPortrait }" style="width: 50px;height: 50px;border-radius: 2px;">
								</a>
							</div>
							
							<div class = "usermsg"><!-- 展示评论信息 -->
									<ul >
										<li> ${a.nickname }  &nbsp;&nbsp;&nbsp;<em style="color:black">${a.commentTime }</em></li> 
										<li><font style="color:#ff8000;"> ${a.commentMsg }</font> </li><!-- 评论内容 -->
									</ul>
									<input type="button" onclick="reply1()" value="回复该评论" class="rp">
									<!-- 若为管理员或者该评论的用户或者为此微博的作者，可以删除 该评论-->
									<c:if test='${a.username=="admin"||requestScope.thisArticle.author==sessionScope.userInfo.username||a.username==sessionScope.userInfo.username }'>
									<a href="OneArticleShowServlet?premethod=deletecomment&articleId=${requestScope.thisArticle.articleId}&commentid=${a.commentId }&currentPage=${requestScope.p.currentPage}"> 删除评论</a>
									</c:if>
							</div>
							<c:if test="${a.replys!=null}"><!-- 若回复不为0，则展示回复 -->
								<c:forEach items="${a.replys}" var  ="b">
									
										<form action="OneArticleShowServlet?premethod=reply&articleId=${requestScope.thisArticle.articleId}&commentid=${a.commentId}&currentPage=${requestScope.p.currentPage}&replyuser=${sessionScope.userInfo.username}&bereplyuser=${b.replyUsername}" method="post" id="replyform2" name = "replyform2" style=" display: none; " >
												<input type="hidden" name="replyMsg2" value="" id="replyMsg2">
										</form>  
										
									<div>
										<font color=black; >
										<!-- 可通过点击昵称跳到该用户的主页去 -->
										<a href="ArticleShowServlet?method=other&currentPage=1&thisUser=${b.replyUsername}" style="color:black;">${b.replyNickname }</a>&nbsp;&nbsp;回复&nbsp;&nbsp;
										<a href="ArticleShowServlet?method=other&currentPage=1&thisUser=${b.beReplyUsername}" style="color:black;">${b.beReplyNickname }</a>&nbsp;&nbsp;&nbsp;<em style="color:black;" >${b.replyTime }</em> <br>
										<!-- 点击回复内容可以进行回复 -->
											 &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;	
											 <font style="color:#ff8000;"> ${b.replyMsg }</font>
											<br> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;	 
										<input type="button" onclick="reply2()" value="回复该评论" class="rp">
										<!-- 若为管理员或者该回复的用户或者为此微博的作者，可以删除该回复-->
										<c:if test='${a.username=="admin"||requestScope.thisArticle.author==sessionScope.userInfo.username||b.replyUsername==sessionScope.userInfo.username }'>
										<a href="OneArticleShowServlet?premethod=deletereply&articleId=${requestScope.thisArticle.articleId}&commentid=${a.commentId}&replyid=${b.replyId}&currentPage=${requestScope.p.currentPage}">删除回复</a>
										</c:if>
										
										</font>
										
										
										
										
										
									</div>
								</c:forEach>
							</c:if>
							
							</div><hr>
							
						</c:forEach>
						</c:if>
						
						<!-- 分页选择链接     -->
							 <div id="paging">
							 <c:if test="${requestScope.p.totalPage!=0}">
							 		<span id="page_number" >当前第 ${requestScope.p.currentPage} 页，总共 ${requestScope.p.totalPage} 页             </span>		
									<c:if test="${requestScope.p.currentPage>1}">
									<a href="OneArticleShowServlet?currentPage=1" id="first_page" class="page_controller">首页</a>					
									<!-- 利用el表达式的三目运算符进行判断 -->
									<a href="OneArticleShowServlet?currentPage=${(requestScope.p.currentPage==1)?1:requestScope.p.currentPage-1}" id="pervious_page"  class="page_controller">上一页</a>
									</c:if>
									<c:if test="${requestScope.p.currentPage < requestScope.p.totalPage}">
									<a href="OneArticleShowServlet?currentPage=${(requestScope.p.currentPage==requestScope.p.totalPage)?requestScope.p.totalPage:requestScope.p.currentPage+1}" id="next_page"  class="page_controller">下一页</a>
									<a href="OneArticleShowServlet?currentPage=${requestScope.p.totalPage}" id="last_page"  class="page_controller">尾页</a>						
							 		</c:if>
							</c:if>
							 </div>
				</div>
			</div>
			

			<div id = "down"> </div>
			
			
	

</body>
<script type="text/javascript">
	function reply1() {
		var replyM = prompt("请输入你的回复","");		
		document.getElementById("replyMsg1").value = replyM
		
		document.forms['replyform'].submit();  //提交隐藏的回复表单
			
	}
	
	function reply2() {
		var replyMsg = prompt("请输入你的回复","");
		document.getElementById("replyMsg2").value= replyMsg;
		document.forms['replyform2'].submit();  //提交隐藏的回复表单
				
	}

</script>		
</html>