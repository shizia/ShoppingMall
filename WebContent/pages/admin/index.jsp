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
<script src="<%=basePath%>js/indexpic.js"></script>

<style type="text/css">
.container {
	/* max-width: 1024px; */
	width: 100%;
	margin: 0 auto;
	padding: 0px;
	font-size: 14px;
}

.preview {
	text-align: center;
	padding: 5px;
}

.preview a {
	display: inline-block;
	margin: 5px;
}

.preview img {
	display: block;
	width: 100%;
	height: auto;
}

.active {
	outline: 3px solid #196cd2;
	box-shadow: 2px 2px 20px 4px rgba(0, 0, 0, .5);
}
.menu ul{
	margin-top: 0px;
	margin-bottom: 0px;
}
.menu ul li{
	margin-top: 5px;
}
</style>

</head>
<body>

<%String username=(String)session.getAttribute("username"); %>

<div class="head">
		<div class="menu">
			<ul>
			
				<li>
					<div class="div3">
						<img src="<%=basePath%>img/head.png" style="height: 20px; width: 20px; ">
					</div>
					<div class="div2">
					<c:if test="${not empty username }">
						欢迎回来，管理员<span><%=username%></span>&nbsp;&nbsp;
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
			<li style="background-color:black"><a class="a1" href="<%=basePath%>pages/admin/index.jsp">网站主页</a></li>
			<li><a class="a1" href="<%=basePath%>UpdateSellerServlet?action=showallseller">销售员信息</a></li>
			<li><a class="a1" href="<%=basePath%>UpdateSellerServlet?action=showallseller_enterip">销售员在线记录</a></li>
			<li><a class="a1" href="<%=basePath%>UpdateSellerServlet?action=showallseller_action">销售员操作日志</a></li>
			<li><a class="a1" href="<%=basePath%>UpdateSellerServlet?action=showallseller_sell">销售员销售统计</a></li>
			<li><a class="a1" href="<%=basePath%>pages/admin/productarea_sell.jsp">商品销售统计</a></li>
			<%-- <li><a class="a1" href="<%=basePath%>UpdateSellerServlet?showsellerip">销售员登录信息</a></li> --%>
						
		  
           <!-- 选中条 -->
		<!-- 	<div id="marker"></div> -->
		</ul>
		</nav>
		</div>
    
	<!-- 轮播图 -->
    <div class="show_pic">
        <img id="pic_change" src="<%=basePath%>pages/admin/indexpic/pic5.png">
    </div>
    
    
<!--     <div >
    <p class="my"> 18级网络工程 林诗琪<br>网络应用开发实验<br>2020-11
    </div>
 -->
	
</body>
</html>