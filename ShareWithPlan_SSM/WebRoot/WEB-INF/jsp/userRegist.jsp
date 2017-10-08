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
  	
  	.regist{
  		text-align:center;
  		font-size:24px;
  		width:100%;
  		height:100%;
		background-color: #c5b6c3;
		padding-left:20px;
		background:url(images/a3.jpg) repeat;
  	}
  		
  	
  
  </style>
  
  <body>
 	<h1 style="text-align:center;padding-left:10px;background-color: pink;width:100%"> UserRegist</h1><hr>
    <form action="userRegist.do" method="post" class="regist">
	<label >账号 ：</label>     <input type="text" name="userNum" style="width:300px;height:32px;margin-top:10px;margin-left:30px;"/><br><br>
    <label>用户名 ：</label>   <input type="text" name="username" style="width:300px;height:32px;margin-top:10px;margin-left:5px;"/><br><br>
    <label>密码 ：</label>      <input type="text" name="password" style="width:300px;height:32px;margin-top:10px;margin-left:30px;"/><br><br>
    <label>职业 ：</label>      <input type="text" name="professional" style="width:300px;height:32px;margin-top:10px;margin-left:30px;"/><br><br>
  	 <input type="submit" value="注册" style="font-size:28px;font-family:楷体;background-color:pink;margin-left:80px;"/><br>
     </form>
  </body>
</html>
