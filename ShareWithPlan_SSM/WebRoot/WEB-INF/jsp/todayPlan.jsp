<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>UserMain.jsp</title>
	
  </head>
  
  <style>
    .nav{
    	width:100%;
		background-color: #c5b6c3;
		height: 40px;
		padding-left:20px;
		background:url(images/a3.jpg) repeat;
		
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
	}
	.nav>td.lastchild{
		float:right;
	}
	.nav>td:hover{
		border-top:0;
		border-left:2px solid pink;
		border-bottom:2px solid pink;
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
	
	.contentForm{
		width:100%;
		background-color: #d5d3e0;
		height: 40px;
		padding-left:20px;
		background:url(images/a41.jpg) repeat;
	}
	
	.contentForm>form>label{
		font-size:18px;
		color:green;	
		margin-left:18px;
		font-family:楷体;
		font-weight:normal;	
	}
	.contentForm>form>input{
		font-size:15px;
		font-family:楷体;
		background-color:pink;
		font-weight:normal;	
	}
	
	.contentTable{
		margin-left:6px;
		font-family:楷体;
		font-weight:normal;	
		font-size:21px;
	}
	
	
  </style>
  
  <body>
  	  <table border="0" width="100%">
  	  	<tr class="nav">
  	  		<td><a href="toMain.do">首页</a></td>
  	  		<td><a href="toMeeting.do">会议记录</a></td>
  	  		<td><a href="toNote.do">笔记分享</a></td>
  	  		<td><a href="toTodayPlan.do">今日计划</a></td>
  	  	</tr>
  	  	<tr>
  	  		<td colspan="4" class="contentForm">
  	  			
  	  			<form action="addTodayPlan.do" method="post">
  	  			<textarea rows="6" cols="135" name="tpContent" style="overflow:auto;margin-left:6px;margin-top:30px;">请在这里输入今日计划内容</textarea><br>
  	  			<label style="margin-left:6px;">计划多少分钟完成：</label><input type="text" name="tpEndTime"  value="60" style="margin-left:6px;background-color:white;"/>&nbsp;&nbsp;
  	  			<input type="submit" value="添加计划"/>
  	  			</form>
  	  			<hr/>
  	  			
  	  			<table border="0" class="contentTable">
  	  			<c:forEach items="${todayPlans}" var="todayPlan">
  	  			<tr> 
  	  				<td style="color:red;margin-left:1px;text-align:left;margin-top:10px;" align="left"><pre >${todayPlan.tpContent }</pre></td>
  	  			</tr>
  	  			<tr>
  	  				<td  style="font-size:15px;font-fimaly:楷体;margin-top:10px;padding-bottom:20px;">计划时间：${todayPlan.tpCreateTime }&nbsp;&nbsp;&nbsp;
  	  					<a href="deleteTodayPlan.do?tpid=${todayPlan.tpid }">删除</a>&nbsp;&nbsp;&nbsp;
  	  					<a href="toUpdateTodayPlan.do?tpid=${todayPlan.tpid }">编辑</a>
  	  				</td>
  	  			</tr>	
  	  			<tr><td><br><br></td></tr>
  	  			</c:forEach>
  	  			</table>
  	  		</td>
  	  	</tr>
  	  </table>
  </body>
</html>
