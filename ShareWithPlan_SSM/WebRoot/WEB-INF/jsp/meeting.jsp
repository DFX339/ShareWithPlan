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
  	  <table border="0"  width="100%">
  	  	<tr class="nav">
  	  		<td><a href="toMain.do">首页</a></td>
  	  		<td><a href="toMeeting.do">会议记录</a></td>
  	  		<td><a href="toNote.do">笔记分享</a></td>
  	  		<td><a href="toTodayPlan.do">今日计划</a></td>
  	  	</tr>
  	  	<tr>
  	  		<td colspan="4" class="contentForm">
  	  			
  	  			<form action="addMeeting.do" method="post">
  	  			<br><textarea rows="6" cols="135" name="mContent" style="overflow:auto;margin-left:6px;margin-top:11px;">请在这里输入会议内容</textarea><br>
  	  			<input type="submit" value="保存会议内容" style="margin-left:16px;margin-top:3px;"/>
  	  			</form>
  	  			<hr style="margin-left:12px;"/>
  	  			<center>
  	  			<table border="0" width="100%" class="contentTable">
  	  			
  	  			<c:forEach items="${meetings}" var="meeting">
  	  			<tr style="color:red;">
  	  				<td><pre>${meeting.mContent }<pre/></td>
  	  			</tr>
  	  			<tr>	
  	  				<td style="width:240;font-size:16px">会议发布时间：${meeting.mtime }&nbsp;&nbsp;&nbsp;
  	  					<a href="deleteMeeting.do?mid=${meeting.mid}">删除</a>&nbsp;&nbsp;&nbsp;
  	  					<a href="toUpdateMeeting.do?mid=${meeting.mid}">编辑</a>
  	  				</td>
  	  			</tr>	
  	  				
  	  			
	  	  		<c:forEach items="${meetingComments}" var="meetingComment1">
	  	  		<c:forEach items="${meetingComment1}" var="meetingComment">
	  	  		<c:if test="${meetingComment.mcmid ==meeting.mid }">
  	  			<tr>
  	  				<td style="width:240;font-size:13px" colspan="4">会议评论内容：${meetingComment.mcContent }&nbsp;&nbsp;&nbsp;
  	  						会议评论时间：${meetingComment.mcTime }&nbsp;&nbsp;&nbsp;
  	  						<a href="deleteMeetingComment.do?mcid=${meetingComment.mcid}">删除</a>
  	  				</td>
  	  			</tr>
  	  			</c:if>	
  	  			</c:forEach>
  	  			</c:forEach>
  	  			<tr>
	  	  			<td>
		  	  			<form action="addMeetingComment.do?mid=${meeting.mid }" method="post">
		  	  			<input type="text" name="mcContent" style="width:320;font-size:13px" /><input type="submit" value="评论"/>
		  	  			</form>
	  	  			</td>
	  	  		</tr>
	  	  		
  	  				<tr><td colspan="4"><br><br></td></tr>
  	  			<tr></tr>
  	  			</c:forEach>
  	  			</table>
  	  			</center>
  	  		</td>
  	  	</tr>
  	  </table>
  </body>
</html>
