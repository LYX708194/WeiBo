<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>微博/忘记密码</title>
<!-- 忘记密码的功能实现，向邮箱发送验证码 -->
</head>
<style type="text/css">
 body{
   background:url('http://localhost:8080/WeiBo/image/background.jpg')  no-repeat center center;
   background-size:cover;
   background-attachment:fixed;
    background-color: #daecda;
}
 #forgetPwd{
    height: 450px;
    width: 400px;
    background: #f8f8f8;
    text-align: center;
    position: absolute;
    left: 50%;
    top: 50%;
	border-radius: 20px;
    transform: translate(-50%,-50%);
}
/*输入框属性*/
#forgetPwd>input{
    outline: none;
    border-radius: 3px;
    text-decoration: none;
    border-style: none;
    width: 300px;
    height: 42px;
    display: block;
    margin: auto;
    color: black;
    font-size: 14px;
}
#uemail,#mail{
    padding-left: 10px;
}
#entry,#send{
    background-color: #0091ff;
    color: white;
}
#uemail:focus {
    background: white;
    border: 1px solid grey;
}
/* hover属性，鼠标在上面的表现*/
#send:hover{
    background: blue;
}
#entry:hover{
    background: blue;
} 
  a:link,a:visited{
  text-decoration:none;
  } 
</style>


<body>
		
		
		<form action="/WeiBo/ForgetPwdServlet" method = "post" id = "forget_form">
		<div id = "forgetPwd">
			<div style="height:100px">
				<h1>忘记密码</h1>
				<h3>我们将通过向你注册的邮箱发送验证码修改密码</h3>
			</div>
			<!-- 用户名表单验证 -->
			
			<input type = "text" id = "uemail" name = "uemail" value = "${requestScope.email }" placeholder = "请输入你的邮箱"
			title = "请输入正确的邮箱地址确保能收到短信" ><br/>
			<input type = "button" id = "send" value = "发送验证码"   onclick = "check1()" ><br/>
			
			<input type = "text" id = "mail" name = "mail" placeholder = "请输入你收到的的验证码" ><br/>
			 			 			
			<input type = "button" id = "entry" value = "确定"  onclick = "check2()" ><br/>
			
			<a href = "http://localhost:8080/WeiBo/jsp/login.jsp"><font color = blue>返回登录</font></a>
			
			<div id = "msg" class = "register_level"> <!-- 表单提供数据给后台，返回信息 -->
			<!-- 提示信息 -->
				<font color = red >${requestScope.msg }</font>		
			</div>
		</div>
	
	</form>
		
		<script type="text/javascript">

		function check1() {
		var uemail = document.getElementById("uemail");
		var mail = document.getElementById("mail");
		if(uemail.value == ""){
			alert("请输入你注册的邮箱");
		}else{
			document.forms[0].submit();
		}		 		
	}
	//判空函数	
	function check2() {
		var uemail = document.getElementById("uemail");
		var mail = document.getElementById("mail");
		if(uemail.value == ""){
			alert("请输入你注册的邮箱");
		}else if(mail.value == ""){
			alert("请输入验证码");
		}else{
			document.forms[0].submit();
		}		 		
	}
	</script>
	
</body>
</html>