<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>userLogin.jsp</title>
	
  </head>
  <style>
  
  	.nav{
    	width:100%;
		text-align:center;
		background-color: #c5b6c3;
		height: 40px;
		padding-left:20px;
		background:url(images/4.jpg) repeat;
		
	}
	.nav>td{
		width:24%;
		line-height: 30px;
		list-style:none;
		display:inline-block;
		margin-top:5px;
		border-top:2px solid white;
		border-bottom:2px solid white;
		text-align:center;
		color:white;
		cursor:pointer;
		text-align:center;
	}
	.nav>td.lastchild{
		float:right;
	}
	.nav>td:hover{
		border-top:0;
		border-left:2px solid green;
		border-bottom:2px solid green;
	}
	
	.nav>td>a:link{
		font-size:18px;
		font-color:red;
		text-decoration:none;
		
	}
	.nav>td>a:visited{
		font-size:18px;
		text-decoration:none;
		
	}
	.nav>td>a:hover{
		font-size:18px;
		text-decoration:none;
		
	}
	.nav>td>a:active{
		font-size:18px;
		text-decoration:none;
		
	}
	
	.login{
  		text-align:center;
  		font-size:24px;
  		width:100%;
  		height:100%;
		background-color: #c5b6c3;
		padding-left:20px;
		background:url(images/4.jpg) repeat;
  	}
  </style>
  
  <script>
   	
  </script>
  <body>
  <table width="100%" height="100%">
  <tr  class="nav">
  	<td>
 		<a href="toLogin.do">会员登录</a>
 	</td> 
	 <td>
 		 <a href="toCompanyUserLogin.do">企业用户登录</a>
 	 </td>
 	 <td>
 	 	<a href="toUserRegist.do">马上注册</a>
 	 </td>
 </tr> 
 <tr ><td>
  	<h1 style="text-align:center;padding-left:10px;background-color: pink;width:100%">会员登录</h1>
  	</td>
  	</tr>
  	<hr>
  	
  	<tr width="100%" height="100%">
  	<td> 
    	<form action="userLogin.do" method="post" class="login">
   			 账号 ：<input type="text" name="userNum" style="width:240px;height:32px;margin-top:10px;margin-left:30px;"/><br><br>
   			 密码 ：<input type="text" name="password" style="width:240px;height:32px;margin-top:10px;margin-left:30px;"/><br><br>
  	 		<input type="submit" value="登录" style="font-size:28px;font-family:楷体;background-color:pink;margin-left:80px;"/><br>
     	</form>
     </td>
     </tr>
    </table>
  </body>
</html>
