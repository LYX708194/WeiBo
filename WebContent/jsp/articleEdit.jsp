<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>写微博</title>
<style type="text/css">
h1{
		display: inline-block;
	    padding: 0;
	    vertical-align: middle;/*设置元素的垂直对齐方式--把此元素放置在父元素的中部*/
	    line-height: 40px;/*行高*/}
body {
	margin: 0;
    background-image: url("http://localhost:8080/WeiBo/image/background.jpg");  
	background-repeat:no-repeat;
    background-position:0% 0%;
	background-size:cover;
	background-attachment:fixed;
}
#show{
	MARGIN: 30px 56px;
}
#all{
	background-color: white;
	margin: 20px auto;
	width:1040px;
	height:700px;
	border-radius: 5px;
	background-color: #e1dbdb;
	opacity:0.8;  /*设置透明度*/
}
button{
	background-color: #0091ff;
    width: 50px;
    height: 42px;
    margin-bottom: 10px;
    margin-left:50px;  
    outline: none;
    border-radius: 3px;
    text-decoration: none;
    border-style: none;
    font-size: 14px;
}
#textarea{
	height:150px;
	font-size: 25px;
	magin-left:px;
	margin-right:100px;
	background-color: white;
}
input{
			border:0;/*将input的边框改为0*/
			box-sizing:border-box;
			font:50px;
		}
</style>

</head>
<body>
<div id = "all" class = "all" >
	
	<div  id ="show">
		<h1 align="center">发表微博</h1><br/><br/>
	
		<div>
		微博文字：<br/>
			<textarea rows="3" cols="30" id ="textarea">
			</textarea>
			<div id="showphoto">
			<img src="${requestScope.articlephoto}"  id="articlephoto"  height ="70px" />
			</div>		   
		</div>
		
		
		<div id ="edit" >
			<form action="/WeiBo/UploadServlet?method=articlesubmit" method = "post" id = "formphoto" enctype = "multipart/form-data" class = "formMsg">
							选择照片：<input type = "file" name = "photo"  >
							<input type = "submit" name  ="updatePhone"  id ="submitPhoto" value = "确定">	
			</form>
		</div>	<br/>
		<div>
		<button type ="button" onclick="check()">发表</button>
		<button type ="button" onclick="out()">返回</button>
		</div >		
		<form action="/WeiBo/ArticleEditServlet" method="post" id="onSubmit" name = "allsubmit" style="display: none;" >
			<div>
				<input type="hidden" name="author" value="${sessionScope.userInfo.username}" id="author">
				 <input type="hidden" name="comtent" value="" id="comtent">
				<input type="hidden" name = "photo" value = "${ requestScope.articlephoto}" id = "photo">			
			</div>
		</form>
		
		<div id="msg" class="msg">
	    	<!-- 提示信息 -->
	    	<font color="red" >${requestScope.msg}</font>    	
	    </div>
	    
	</div>
</div>




<script type="text/javascript">

	
	function check(){
	  //拿到文章的内容
		var comtent = document.getElementById("textarea").value;
  	  if(comtent == ""||comtent== null){
   		     alert("请填写微博内容");
  	  }else{	
  				document.getElementById("comtent").value = comtent;
 		       document.forms['allsubmit'].submit();  //提交隐藏表单
  	  }
	  
	}

	function out() {
		window.location.href="/WeiBo/jsp/self.jsp";
	}

</script>

</body>

</html>