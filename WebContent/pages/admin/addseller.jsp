<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date"%>
<%@ page import="java.sql.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>首页</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/htmleaf-demo.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/head.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/indexpic.css">
<link type="text/css" rel="stylesheet" href="<%=basePath%>static/css/style.css" >
<link type="text/css" rel="stylesheet" href="<%=basePath%>css/H-ui.css" >
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script src="<%=basePath%>js/indexpic.js"></script>

<style type="text/css">
.menu ul{
	margin-top: 0px;
	margin-bottom: 0px;
}
.menu ul li{
	margin-top: 5px;
}
</style>


</head>
<body style="height:1100px;overflow:visible;">

<%String username=(String)session.getAttribute("username"); %>
<%String producttype=(String)session.getAttribute("producttype"); %>
<%String productarea=(String)session.getAttribute("productarea_ch"); %>
<%String updatesellerid=(String)session.getAttribute("updatesellerid"); %>

<div  class="head">
		<div class="menu">
			<ul>
			
				<li>
					<div class="div3">
						<img src="<%=basePath%>img/head.png" style="height: 20px; width: 20px; ">
					</div>
					<div class="div2">
					<c:if test="${not empty username }">
						欢迎回来，销售员<span><%=username%></span>&nbsp;&nbsp;
					</c:if>
					<c:if test="${empty username}">
					<a href="<%=basePath%>pages/register.jsp"><span>注册</span></a>
					<a href="<%=basePath%>pages/login.jsp"><span>登录</span></a>
					</c:if>
					
					</div>
				</li>			
				
				<li>
					<div class="div3">
					<c:if test="${not empty username }">
						<img src="<%=basePath%>img/head.png" style="height: 20px; width: 20px; ">
					</c:if>
					</div>
					<div class="div2">
					<c:if test="${not empty username }">
					<a href="<%=basePath%>UserServlet?action=logout"><span>退出登录</span></a>
					</c:if>
					</div>
				</li>
				
			</ul>
		</div>
		<div class="head2" style="border:0px;">
			<div class="div4">
				<img src="<%=basePath%>img/timg.png" style="height: 94px; width: 538px; ">
			</div>
			<div class="div5">
			
			</div>
		</div>
	</div>
       
    <!-- 分类信息 -->
	<div class="htmleaf-container">
		<nav>
		<ul id="main" style=" width: 1200px;">
			<li ><a class="a1" href="<%=basePath%>pages/admin/index.jsp">网站主页</a></li>
			<li style="background-color:black"><a class="a1" href="<%=basePath%>UpdateSellerServlet?action=showallseller">销售员信息</a></li>
			<li><a class="a1" href="<%=basePath%>UpdateSellerServlet?action=showallseller_enterip">销售员在线记录</a></li>
			<li><a class="a1" href="<%=basePath%>UpdateSellerServlet?action=showallseller_action">销售员操作日志</a></li>
			<li><a class="a1" href="<%=basePath%>UpdateSellerServlet?action=showallseller_sell">销售员销售统计</a></li>
			<li><a class="a1" href="<%=basePath%>pages/admin/productarea_sell.jsp">商品销售统计</a></li>
           <!-- 选中条 -->
		<!-- 	<div id="marker"></div> -->
		</ul>
		</nav>
	</div>

<div style=" margin-left: 300px;" class="page-container">
	
	<form action="<%=basePath%>AddSellerServlet" method="post" class="form form-horizontal" id="form-member-add">
			<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span></label>
			<div class="formControls col-xs-8 col-sm-9" style="width:300px;margin-left: 40px;">
				<h3> 添加销售员 </h3>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">身份证号：</label>
			<div class="formControls col-xs-8 col-sm-9" style="width:300px;">
			<input type="text" class="input-text" style="width:270px;" id="id" name="id">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">姓名：</label>
			<div class="formControls col-xs-8 col-sm-9" style="width:300px;">
			<input type="text" class="input-text" style="width:270px;" id="name" name="name">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">初始密码：</label>
			<div class="formControls col-xs-8 col-sm-9" style="width:300px;">
			<input type="text"   disabled="disabled" class="input-text" style="width:270px;" value="12345678seller" >
			<input type="hidden" id="pwd" name="pwd" value="12345678seller">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">负责商品区域：</label>
			<div class="formControls col-xs-8 col-sm-9" style="width:300px;">
			<%-- <input type="text"   disabled="disabled" class="input-text" style="width:270px;" value="${producttype}" >
			<input type="hidden" id="area" name="area" value="${productarea}"> --%>
			<select class="input-text" style="width:270px;" id="area" name="area">
			<option value="书籍">书籍</option>
			<option value="食品">食品</option>
			<option value="数码">数码</option>
			</select>
			
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">负责商品类型：</label>
			<div class="formControls col-xs-8 col-sm-9" style="width:300px;">
			<input type="text" class="input-text" style="width:270px;" id="type" name="type">
			</div>
		</div>

		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;确认&nbsp;&nbsp;">
			</div>
		</div>
	</form>
	
	
	
</div>
	
</body>
</html>