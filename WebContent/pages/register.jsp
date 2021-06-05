<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="utf-8" />
	<title>注册</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/styles.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/login.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript">
		$(function (){

			//给注册绑定单击事件
			$("#Submit").click(function (){
				var legal = true;
				//验证身份证号码，由18位数字组成
				//获取身份证号码输入框里的内容
				var identityText = $("#identity").val();
				//创建正则表达式对象
				var identityPatt = /^[0-9]{18}/;
				//使用test方法验证
				if(!identityPatt.test(identityText)){
					//提示结果
					$("span.identityError").text("请输入合法的身份证号码！");
					legal = false;
					$("span.registError").text("");
					//return false;
				}
				else $("span.identityError").text("");

				//验证密码，由字母、数字、下划线组成，长度为8-12位
				//获取密码输入框里的内容
				var pswText = $("#password").val();
				//创建正则表达式对象
				var pswPatt = /^\w{8,12}$/;
				//使用test方法验证
				if(!pswPatt.test(pswText)){
					//提示结果
					$("span.pswError").text("请输入合法的密码！");
					legal = false;
					$("span.registError").text("");
					//return false;
				}
				else $("span.pswError").text("");

				//确认密码：和密码相同
				//获取确认密码输入框里的内容
				var repwdText = $("#repwd").val();
				//判断两次密码是否相同
				if(repwdText != pswText){
					//提示结果
					$("span.repswError").text("两次输入密码不一致！");
					legal = false;
					$("span.registError").text("");
					//return false;
				}
				else $("span.repswError").text("");

				//姓名验证：不为空
				//获取真实姓名输入框里的内容
				var nameText = $("#name").val();
				//去掉前后空格
				nameText = $.trim(nameText);
				if (nameText == null || nameText == "") {
					//4 提示用户
					$("span.nameError").text("姓名不能为空！");
					legal = false;
					$("span.registError").text("");
				}
				else $("span.nameError").text("");
				
				//身份验证：不为空
				//获取身份输入框里的内容
				var statusText = "";
				var temp = document.getElementsByName("status");
				for(i = 0; i < temp.length; i++){
					if(temp[i].checked){
						statusText = temp[i].value;
					}
				}
				if (statusText == null || statusText == "") {
					//4 提示用户
					$("span.statusError").text("请选择您的身份！");
					legal = false;
					$("span.registError").text("");
				}
				else $("span.statusError").text("");
				

				return legal;
			})
		})
	</script>

</head>
<body id="register">
	<!--顶部导航-->
<div style="margin-left:100px;" class="div4">
				<img src="<%=basePath%>img/timg.png" style="height: 94px; width: 538px; ">
	</div>
	<div class="register">
		<div class="hd2">
			<h1 id="regis">用户注册</h1>
			<hr />
		</div>

		<div class="register-form">
			<form action="<%=basePath%>UserServlet?action=regist" id="register-form" method="post">
				<input type="hidden" name="action" value="regist">
				
				<div class="txt">
					<p class="register-form">身份证号码:
						<span class="identityError"></span>
					</p>
					<label>
						<input
								class="input-block"
								id="identity"
								name="identity"
								type="text"
								placeholder="点此输入18位身份证号码"
								autocomplete="off"
								tabindex="1"
								value="<%=request.getAttribute("identity")==null?"":request.getAttribute("identity")%>"

						/>
					</label>
					<br><br>
					
					<p class="register-form">用户名:
						<span class="nameError"></span>
					</p>
					<label>
						<input
								class="input-block"
								id="name"
								name="name"
								type="text"
								placeholder="点此输入您的用户名"
								autocomplete="off"
								tabindex="1"
								value="<%=request.getAttribute("name")==null?"":request.getAttribute("name")%>"
						/>
					</label>
					<br><br>
					
					<p class="register-form">邮箱:
						<span class="pswError"></span>
					</p>
					<label>
						<input
								class="input-block"
								id="email"
								name="email"
								type="text"
								placeholder="点此输入您的邮箱"
								autocomplete="off"
								tabindex="1"
								value="<%=request.getAttribute("email")==null?"":request.getAttribute("email")%>"
						/>
					</label>
					<br><br>
					
					<p class="register-form">用户密码:
						<span class="pswError"></span>
					</p>
					<label>
						<input
								class="input-block"
								id="password"
								name="password"
								type="password"
								placeholder="点此输入您的密码，长度为6-16位"
								autocomplete="off"
								tabindex="1"
								value="<%=request.getAttribute("password")==null?"":request.getAttribute("password")%>"
						/>
					</label>
					<br><br>
					
					<p class="register-form">确认密码:
						<span class="repswError"></span>
					</p>
					<label>
						<input
								class="input-block"
								id="repwd"
								name="repwd"
								type="password"
								placeholder="点此确认密码"
								autocomplete="off"
								tabindex="1"
						/>
					</label>
					<!-- <br><br>
					<p class="register-form">身份:
						<span class="statusError"></span>
					</p> -->
				</div>
				
				<!-- <div class="radio">
					<label>
					<input 
								id="status"
								name="status" 
								type="radio" 
								value="user"
								/>用户
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input 
								id="status"
								name="status" 
								type="radio" 
								value="seller"
								/>销售员
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input 
								id="status"
								name="status" 
								type="radio" 
								value="admin"
								/>管理员
					</label>
				</div> -->
				
				<div class="txt">
					<div class="loginError">
						<span name="registError" class="registError">
							<%=request.getAttribute("registError")==null?"":request.getAttribute("registError")%>
						</span>
					</div>
				</div>
				<br>
				<div class="btn-wr text-primary">
					<input class="btn btn-primary btn-md" id="Submit" type="submit" value="点击注册">
					<a href="<%=basePath%>pages/index.jsp"><span style="color:#323232;margin-top:20px;margin-left:20px;">回主页</span></a>
					<a href="<%=basePath%>pages/login.jsp"><span style="color:#323232;margin-top:20px;margin-left:20px;">登录</span></a>
				</div>
				
			</form>
		</div>
	</div>


</body>
</html>