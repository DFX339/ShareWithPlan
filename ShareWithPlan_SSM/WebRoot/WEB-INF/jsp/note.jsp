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
  	  <table border="0" style="width:100%" >
  	  	<tr class="nav">
  	  		<td><a href="toMain.do">首页</a></td>
  	  		<td><a href="toMeeting.do">会议记录</a></td>
  	  		<td><a href="toNote.do">笔记分享</a></td>
  	  		<td><a href="toTodayPlan.do">今日计划</a></td>
  	  	</tr>
  	  	<tr>
  	  		<td colspan="4" class="contentForm">
  	  			
  	  			<form action="addNote.do" method="post">
  	  			<textarea rows="6" cols="135" name="ncontent" style="overflow:auto;margin-left:6px;margin-top:34px;">请在这里编辑笔记内容</textarea><br>
  	  			<label>笔记状态：</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  				<input type="radio" name="nstate" value="PUBLIC" checked>公开&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  				<input type="radio" name="nstate" value="PRIVATE"/>私密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	  				<input type="submit" value="保存笔记"/>
  	  			</form>
  	  			<hr style="margin-left:6px"/>
  	  			<center>
  	  			<table border="0" width="100%" class="contentTable">
  	  			
  	  			<c:forEach items="${notes}" var="note">
  	  			
  	  			<tr  style="color:red;"> 
  	  				<td colspan="100%"><pre>${note.ncontent }</pre></td>
  	  			</tr>
  	  			<tr>
  	  				<td style="font-size:15px;font-weight:normal;" colspan="4" >笔记发布时间：${note.nsendTime }&nbsp;&nbsp;&nbsp;
  	  					笔记状态：${note.nstate }&nbsp;&nbsp;&nbsp;
  	  					<a href="deleteNote.do?nid=${note.nid }">删除</a>&nbsp;&nbsp;&nbsp;
  	  					<a href="toUpdateNote.do?nid=${note.nid }">编辑</a>
  	  				</td>
  	  			</tr>
  	  			
	  	  		
	  	  		<c:forEach items="${noteComments}" var="noteComment1">
	  	  		<c:forEach items="${noteComment1}" var="noteComment">
	  	  		<c:if test="${noteComment.ncnid == note.nid}">
  	  			<tr>
  	  				<td style="font-size:13px" colspan="4">笔记评论内容：${noteComment.ncContent }&nbsp;&nbsp;&nbsp;
				  	  		笔记评论时间：${noteComment.ncTime }&nbsp;&nbsp;&nbsp;
				  	  		笔记评论者：${noteComment.ncCommentName }&nbsp;&nbsp;&nbsp;
				  	  		<a href="deleteNoteComment.do?ncid=${noteComment.ncid}">删除</a>
  	  				</td>
  	  			</tr>	
  	  			</c:if>
  	  			</c:forEach>
  	  			</c:forEach>
  	  			<tr>
	  	  			<td>
		  	  			<form action="addNoteComment.do?nid=${note.nid }" method="post">
		  	  			<input type="text" name="ncContent" style="font-size:15px;width:240;height:25px;overflow:auto;margin-left:16px;"/>&nbsp;&nbsp;
		  	  			<input type="submit" value="评论" style="font-size:15px;font-family:楷体;background-color:pink;padding-top:2px;"/>
		  	  			</form>
	  	  			</td>
	  	  		</tr>
	  	  		
	  	  		<tr><td><br><br></td></tr>
  	  			</c:forEach>
  	  			</table>
  	  			</center>
  	  		</td>
  	  	</tr>
  	  </table>
  </body>
</html>
