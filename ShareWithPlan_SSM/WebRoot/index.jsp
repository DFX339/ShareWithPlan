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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	.login{
    	width:100%;
		background-color: #d2f0e6;
		height: 40px;
		padding-left:20px;
		text-align:center;		
	}
	.login>td{
		width:24%;
		line-height: 30px;
		list-style:none;
		display:inline-block;
		margin-top:5px;
		text-align:center;
		color:white;
		cursor:pointer;
		
	}
	.login>td.lastchild{
		float:right;
	}
	.login>td:hover{
		border-top:0;
		border-left:2px solid white;
		border-bottom:2px solid white;
	}
	
	.login>td>a:link{
		font-size:18px;
		font-color:red;
		text-decoration:none;
		
	}
	.login>td>a:visited{
		font-size:18px;
		text-decoration:none;
	}
	.login>td>a:hover{
		font-size:18px;
		text-decoration:none;
	}
	.login>td>a:active{
		font-size:18px;
		text-decoration:none;
	}
	
	
	.contentForm{
		width:100%;
		background-color: #d5d3e0;
		height: 22px;
		padding-left:20px;
		background:url(images/a41.jpg) repeat;
	}
	
	
	.lunbo{
	height: 500px;
	position:relative;
	left:0;
	top:0;
	overflow:hidden;
	background:url(images/a41.jpg) repeat;
	background-size:100% 100%;
	}
	.lunbo>ul{
		display:inline-block;
	}
	.lunbo>.img{
		width: 100%;
		height: 300px;
		position:absolute;
		list-style:none;
		display:inline-block;
		right:0;
		z-index:99;
	
	
	}
	.lunbo li{
		list-style:none;
	}
	.lunbo>ul>li{
		display:inline-block;
	
	}
	.lunbo>ul>li>img{
		position:absolute;
		left:100%;
		width: 100%;
		height: 520px;
		animation-duration:0.6s;
		animation-timing-function:ease;
		animation-fill-mode:forwards;
	}
	.lunbo>ul>li>img:first-child{
		left:-100%;
	}
	.lunbo>ul>li>img:nth-child(2){
		left:0%;
	}
	.lunbo>ul>li>img.show{animation-name:show;}
	.lunbo>ul>li>img.hide{animation-name:hide;}
	@keyframes hide{
		from{left:0;}
		to{left:-100%;}
	}
	@keyframes show{
		from{left:100%;}
		to{left:0;}
	}
	
  </style>
  
  <script>
		window.onload=function(){
			var img=document.getElementById('imga');
			var imgs=img.getElementsByTagName('img');
			var curIndex=0;
			var handler=null;
			handler=setInterval(changeImg,2000);
			function changeImg(){
						var img=document.getElementById('imga');
						var imgs=img.getElementsByTagName('img');
						imgs[curIndex].className='hide';
						var nextIndex=curIndex+1>=imgs.length?0:curIndex+1;

						imgs[nextIndex].className='show';
						curIndex=nextIndex;

			}
	}
	</script>
  
  <body>
  	  <table border="0" width="100%" >
  	  	<tr class="nav">
  	  		<td><a href="toMain.do">首页</a></td>
  	  		<td><a href="toMeeting.do">会议记录</a></td>
  	  		<td><a href="toNote.do">笔记分享</a></td>
  	  		<td><a href="toTodayPlan.do">今日计划</a></td>
  	  	</tr>
  	  	<tr>
  	  		<td colspan="4" class="contentForm" ><h1 style="font-size:38;font-family:楷体;text-align:center;margin-top:38px;">WELCOME TO SHAREWITHPLAN</h1></td>
  	  	</tr>
  	  	
  	  	<tr class="login">
  	  		<td colspan="2">
	  	  			<a href="toLogin.do" >普通用户登录</a>
	  	  	</td>
	  	  	<td colspan="2">
	  	  			<a href="toCompanyUserLogin.do">企业用户登录</a>
  	  		</td>
  	  	</tr>
  	  	
  	  	<tr>
  	  		<td>
  	  		<div class="lunbo">
  	  			<ul class="img" id="imga">
  	  			
				<li><img src="images/3.jpg" alt=""></li>
				<li><img src="images/4.jpg" alt=""></li>
				<li><img src="images/5.jpg" alt=""></li>
				<li><img src="images/6.jpg" alt=""></li>
				<li><img src="images/b.jpg" alt=""></li>
			</ul>
			</div>
  	  		</td>
  	  	</tr>
  	  </table>
  </body>
</html>
