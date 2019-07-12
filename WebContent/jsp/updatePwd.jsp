<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>微博/修改密码</title>
</head>
<style type="text/css">
 body{
   background:url('http://localhost:8080/WeiBo/image/background.jpg')  no-repeat center center;
   background-size:cover;
   background-attachment:fixed;
    background-color: #daecda;

}
 #updatePwd{
    height: 500px;
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
#updatePwd>input{
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
#uname,#upwd1,#upwd2,#uemail{
    padding-left: 10px;
}
#entry{
    background-color: #0091ff;
    color: white;
}
#h4{
	 padding: 20px 128px;
	 color: #2ca160;
     font-color: red;
}
#uname:focus {
    background: white;
    border: 1px solid grey;
}
/* hover属性，鼠标在上面的表现*/
#entry:hover{
    background: blue;
} 
  a:link,a:visited{
  text-decoration:none;
  } 
</style>


<body>
		<form action="/WeiBo/UpdatePwdServlet" method = "post" id = "updatePwd_form">
		<div id = "updatePwd">
			<div style="height:60px">
				<h1>修改密码</h1>
			</div>
			<!-- 用户名表单验证 -->
			<input type = "text" id = "uname" name = "uname" placeholder = "请输入你的手机号码"
			title = "请输入你的电话号码" onblur = "isPhone(this.value)"><br/>
			<input type = "password" id = "upwd1" name = "upwd1" placeholder = "请输入你的密码"
			 title = "请输入6-20个字母、数字组合" onblur = "isPwd(this.value)"
			 pattern = "^(?![^a-zA-Z]+$)(?!\D+$){6,20}" ><br/>
			 <input type = "password" id = "upwd2" name = "upwd2" placeholder = "请再次输入你的密码"
			 onblur = "isSame()"><br/>
			
			<input type = "button" id = "entry" value = "修改"  onclick = "check()" ><br/>
			<a href = "http://localhost:8080/WeiBo/jsp/login.jsp"><font color = blue>返回登录</font></a>
			<a href = "http://localhost:8080/WeiBo/jsp/self.jsp"><font color = blue>返回主页</font></a>
			<a href = "http://localhost:8080/WeiBo/jsp/register.jsp"><font color = blue>重新注册</font></a>
			<div id = "msg" class = "register_level"> <!-- 表单提供数据给后台，返回信息 -->
			<!-- 提示信息 -->
				<h4  id = "tip">${requestScope.msg }"</h4>
			</div>
		</div>
	
	</form>

	
	<script type="text/javascript">
	//判空函数	
	function check() {
			var uname = document.getElementById("uname");
			var upwd1 = document.getElementById("upwd1");
			var upwd2 = document.getElementById("upwd2");
			if(uname.value == ""){
				alert("请输入用户名");
			}else if(upwd1.value == ""){
				alert("请输入密码");
			}else if(upwd2.value == ""){
				alert("请再次输入密码");
			}else{
				document.forms[0].submit();
			}		
		}
	//判断密码是否符合规范,必须包含数字和字母
	function isPwd(strPwd) {
		var Pwd = /^(?![^a-zA-Z]+$)(?!\D+$)/;
		if(strPwd == "" || strPwd.search(Pwd)== -1){
			alert("请输入正确的密码格式");
			return false;
		}
	}
	//判断两个密码是否相同
	function isSame() {
		if(upwd1.value != upwd2.value) {
			alert("两次输入密码不一致！")
			upwd1.value  = "";
			upwd2.value = "";
		}
	}
	</script>

</body>
</html>