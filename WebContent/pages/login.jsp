<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/login.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script> 
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/head.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/indexpic.css">


</head>
<body>
<%String username=(String)session.getAttribute("username"); %>

<div class="div4">
				<img src="<%=basePath%>img/timg.png" style="height: 94px; width: 538px; ">
	</div>

<div style="margin-top:50px;margin-right:450px;" id="r_content">
			<div class="login">
				<div class="hd">
					<h2 id="login-hd">账户登录</h2>
					<hr />
				</div>
				<br>
				<div class="login-form">
					<form action="<%=basePath%>UserServlet?action=login" id="login-form" method="post">
						<input type="hidden" name="action" value="login">
						<div class="txt">
							<span class="usernameError"></span>
							<label class="userName">
								<input
										class="input-block"
										id="useridentity"
										name="useridentity"
										type="text"
										placeholder="点此输入18位身份证号"
										autocomplete="off"
										tabindex="1"
									/>
							</label>
							<br><br>
							<span class="pswError"></span>
							<label class="passWord">
								<input
										class="input-block"
										id="password"
										name="userpassword"
										type="password"
										placeholder="输入您的密码"
										autocomplete="off"
										tabindex="1"
								/>
							</label>
							
				</div>			
				<div style="margin-top:30px;margin-bottom:30px;" class="radio">
					
							<input 
								class="status"
								name="status" 
								type="radio" 
								value="user"
								/>用户
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input 
								class="status"
								name="status" 
								type="radio" 
								value="seller"
								/>销售员
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input 
								class="status"
								name="status" 
								type="radio" 
								value="admin"
								/>管理员
					
				</div>
							
							<div class="loginError">
								<span name="loginError" class="loginError">
									<%=request.getAttribute("loginError")==null?"":request.getAttribute("loginError")%>
								</span>
							</div>
						
						<div class="btn-wr text-primary">
							<input style="margin-left:60px;" class="btn btn-primary btn-md" id="Submit" type="submit" value="立即登录">
							<a href="<%=basePath%>pages/index.jsp"><span style="color:#323232;margin-top:20px;margin-left:20px;">回主页</span></a>
							<a href="<%=basePath%>pages/register.jsp"><span style="color:#323232;margin-top:20px;margin-left:20px;">注册</span></a>
							
						</div>
						
					</form>
				</div>
			</div>
		</div>
</body>
</html>