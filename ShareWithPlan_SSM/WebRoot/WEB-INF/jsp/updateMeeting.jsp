<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>UserMain.jsp</title>
  </head>
  <style>
  	
	
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
  </style>
  <body>
  	  <table border="0" width="100%" >
  	<tr >
  		<td>
  			<h1 style="text-align:center;padding-left:10px;background-color: pink;width:100%">UpdateMeetingContent</h1>
  		</td>
  	</tr>
  	  	<tr >
  	  		<td colspan="4">
  	  			<form action="updateMeeting.do" method="post" class="contentForm">
  	  			<input type="hidden" name="mid" value="${mid} " read-only="true"/>
  	  			<label style="font-size:24px;font-family:楷体;color:green;margin-left:12px; margin-bottom:200px;width:120px;height:32px;"/>会议内容:</label><br>
  	  			<br><textarea rows="12" cols="135" name="mContent"  style="overflow:auto;margin-left:6px;padding-top:10px;">${meeting.mContent }</textarea><br>
  	  			<input type="submit" value="更新会议" style="font-size:22px;font-family:楷体;background-color:pink;margin-left:520px;margin-top:10px;"/>
  	  			</form>
  	  			
  	  			
  	  		</td>
  	  	</tr>
  	  </table>
  </body>
</html>
