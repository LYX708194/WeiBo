<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>微博/注册账号</title>
</head>
<style type="text/css">
 body{
   background:url('http://localhost:8080/WeiBo/image/background.jpg')  no-repeat center center;
   background-size:cover;
   background-attachment:fixed;
    background-color: #daecda;
}
 #register{
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
#register>input{
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
	
	<form action="/WeiBo/RegisterServlet" method = "post" id = "register_form">
		<div id = "register">
			<div style="height:60px">
				<h1>注册账号</h1>
			</div>
			<!-- 用户名表单验证 -->
			<input type = "text" id = "uname" name = "uname" placeholder = "请输入你的手机号码"
			title = "请输入11个数字的电话号码" onblur = "isPhone(this.value)"><br/>
			<input type = "text" id="uemail" name ="uemail" placeholder="请输入你的邮箱"  
			    	 onblur="isEmail(this.value)" ><br/> 
			<input type = "password" id = "upwd1" name = "upwd1" placeholder = "请输入你的密码,6-20位，包括字母和数字"
			 title = "请输入6-20个字母、数字组合" onblur = "isPwd(this.value)"
			 pattern = "^(?![^a-zA-Z]+$)(?!\D+$){6,20}" ><br/>
			 <input type = "password" id = "upwd2" name = "upwd2" placeholder = "请再次输入你的密码"
			 onblur = "isSame()"><br/>
			
			<input type = "button" id = "entry" value = "注册"  onclick = "check()" ><br/>
			<a href = "http://localhost:8080/WeiBo/jsp/login.jsp"><font color = blue>已有账号？登录</font></a>
			
			<div id = "msg" class = "register_level"> <!-- 表单提供数据给后台，返回信息 -->
			<!-- 提示信息 -->
				<font color = red >${requestScope.msg }</font>		
			</div>
		</div>
	
	</form>
	
	
	<!-- js代码 -->
	<script type="text/javascript">
	//判空函数	
	function check() {
			var uname = document.getElementById("uname")
			var uemail = document.getElementById("uemail")
			var upwd1 = document.getElementById("upwd1")
			var upwd2 = document.getElementById("upwd2")
			if(uname.value == ""){
				alert("请输入用户名");
			}else if(uemail.value ==""){
				alert("请输入邮箱");
			}else if(upwd1.value == ""){
				alert("请输入密码");
			}else if(upwd2.value == ""){
				alert("请再次输入密码");
			}else{
				document.forms[0].submit();
			}		
		}
	
	//onblur失去焦点事件，用户离开输入框时执行 JavaScript 代码
	//判断用户名是否为11位的电话号码
	function isPhone(strPhone) {
		var phone = /\d{11}/ ; 
		if(strPhone ==""||strPhone.search(phone) == -1){
			alert("请输入正确的11位电话号码");
			return false;
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
	//函数：验证邮箱格式
  	function isEmail(strEmail){
  		//定义正则表达式的变量:邮箱正则
  		var reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
  		//文本框不为空，并且验证邮箱正则成功
  		if(strEmail == "" || strEmail.search(reg) == -1)
  		{ 
  			alert("请输入正确的邮箱格式");
  			document.getElementById("uemail").value="";
  			return false;
  		}
  	}
	
	</script>
	
	
</body>
</html>