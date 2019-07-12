<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>微博/登录微博</title>

<style type="text/css">
body {
	margin: 0;
    background-image: url("http://localhost:8080/WeiBo/image/background.jpg");  
	background-repeat:no-repeat;
    background-position:0% 0%;
	background-size:cover;
	background-attachment:fixed;
}

/*样式初始化*/
	
		*{
			margin:0;
			padding:0;/*清除内外边距*/
		}
		ul{
			list-style:none;/*去掉列表中的样式的小点*/
		}
		.clearfix:before,.clear:after{/*清除浮动*/
			display:table;
			content:"";
		}
		.clearfix:after{
			clear:both;
		}
		.clearfix{
			*zoom:1;
		}	
		 a:link,a:visited{/*去掉下划线*/
		  text-decoration:none;
		  } 
</style>

</head>

<style type="text/css">
/*登录框的绝对定位*/
#login {
    background-color: #f8f8f8;
    width: 400px;
    height: 350px;
    float: right;
    margin-right: 100px;
    border-radius: 15px;
    margin-top: 150px;
}
/*登录标题*/
#h1{
	 padding: 20px 128px;
	 color: #2ca160;
     font-color: green;
}
/*对表单元素中的input的输入框进行统一格式处理*/
#uname,#upwd {
    margin-bottom: 10px;
    outline: none;
    border-radius: 3px;
    text-decoration: none;
    border-style: none;
    width: 300px;
    height: 42px;
    display: block;/*显示为块级元素，此元素前后会带有换行符*/
    color: black;
    font-size: 14px;
}

#uname,#upwd{/*输入框的初始化布局设置*/
    background-color: ;
    padding-left: 10px;
}
/*登录按钮的样式*/
#entry{
    background-color: #0091ff;
    width: 300px;
    height: 42px;
    margin-bottom: 10px;
    margin-left:50px;  
    outline: none;
    border-radius: 3px;
    text-decoration: none;
    border-style: none;
    font-size: 14px;
}

/*输入框被点击时发生反应*/
#uname:focus {
    background: white;/*输入框背景颜色*/
    border: 1px solid grey;/*边框大小和颜色*/
}
#upwd:focus {
    background: white;
    border: 1px solid grey;
}
#entry:hover{ /*鼠标移动到链接上时*/
    background: blue;
}

/*div的设置统一长方形方块*/
.login_level{
	 width: 350px;
	 height: 50px;
	 margin-left:50px;
	 center:center;
}
/*按钮的处理*/
#select{
	 width: 350px;
	 height: 30px;
	 margin-left:50px;
	 center:center;
}

/*底部两个链接的位置*/
#forget{  /*忘记密码*/
    margin-left: 50px;
    border-style: none;
    width: 300px;
    height: 42px;
    font-size: 14px;
}
#register{   /*注册账号*/
    margin-left:180px;
    border-style: none;
    width: 300px;
    height: 42px;
    font-size: 14px;
}
</style>


<body>

<form action="/WeiBo/LoginServlet"  method="post" id="login_form">
	<div id = "login">
		<div><h1 id = "h1">登录微博</h1>
		</div>
 		<!-- 登录的长方形边框 -->	
		<div id = "name" class = "login_level">
		<!-- 获得cookie中对象的值 -->
		<input  type = "text" id = "uname" name = "uname" value = "${cookie.uname.value }" placeholder = "请输入用户名">
		</div >
		<div id="pwd" class ="login_level" >
		<input  type = "password" id = "upwd" name = "upwd" value = "${cookie.upwd.value }"  placeholder = "请输入密码">
		</div>
		<div id="select">
	  <label>
	    	<input  type="checkbox" name="auto" id="auto"/>自动登录
	  </label>
	  <label>
	   		<input  type="checkbox" name="remember" id="remember"/>记住密码
	  </label>	
	</div>
	<div>
			<input type="button" onclick="check()" value="登录" id="entry"/>
	</div>

	<div>
		<a href="http://localhost:8080/WeiBo/jsp/forgetPwd.jsp" id="forget" ><font color="blue" >忘记密码</font></a>
		<a href="http://localhost:8080/WeiBo/jsp/register.jsp" id="register"><font color="blue" >注册账号</font></a>
	</div>
	 <!-- 表单输入数据给后台，返回的消息提示功能 -->
	    <div id="msg" class="login_level">
	    	<!-- 提示信息 -->
	    	<font color="red" >${requestScope.Msg}</font>    	
	    </div>
	</div>

</form>

<!-- JS代码-->
	<script type="text/javascript">
     //检查用户输入是否为空
    function check(){
        var username = document.getElementById("uname")
        var password = document.getElementById("upwd")
        if(username.value == ""){
            alert("请输入用户名");
        }else if(password.value == ""){
            alert("请输入密码");
        }else{
            document.forms[0].submit();
        }
    }
     </script>
</body>
</html>