<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date"%>
<%@ page import="java.sql.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.cn.util.*" %>
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

.type-tab{
	margin-top: 5px;
     width:120px;
     font-size: 16px;
     font-family: "宋体";
  }
.type-tab ul{
    margin:0px;
    padding:0px; 
    list-style-type: none;
 }
.type-tab li{
   border-bottom: white solid 1px;
    }
.type-tab li a{
   display: block;
  padding: 5px 5px 5px 8px;
  text-decoration: none;
   border-left: 12px solid black;
    border-right: 1px solid black;
 }
   /*动态菜单的效果*/
.type-tab li a:link{
   background-color: #666;
    color:#ffffff;
 }
.type-tab li  a:active{
   background-color: black;
     color:#ffffff;
  }

</style>
</head>
<body style="height:1100px;overflow:visible;">

<%String username=(String)session.getAttribute("username"); %>
<%String producttype=(String)session.getAttribute("producttype"); %>
<%String searchname=(String)session.getAttribute("searchname"); %>

<div  class="head">
		<div class="menu">
			<ul>
			
				<li>
					<div class="div3">
						<img src="<%=basePath%>img/head.png" style="height: 20px; width: 20px; ">
					</div>
					<div class="div2">
					<c:if test="${not empty username }">
						欢迎回来，<span><%=username%></span>&nbsp;&nbsp;
					</c:if>
					<c:if test="${empty username}">
					<a href="<%=basePath%>pages/register.jsp"><span>注册</span></a>
					<a href="<%=basePath%>pages/login.jsp"><span>登录</span></a>
					</c:if>
					
					</div>
				</li>			
			
				<li>

					<div class="div2">
					<c:if test="${not empty username }">
						<img src="<%=basePath%>img/11.png" style="height: 20px; width: 20px; ">
						<a href="<%=basePath%>pages/user/cart.jsp"><span>我的购物车</span></a>
						&nbsp;&nbsp;
						<img src="<%=basePath%>img/foot.png" style="height: 20px; width: 15px; ">
						<a href="<%=basePath%>VisitRecordServlet?action=myvisit"><span>我的足迹</span></a>
						&nbsp;&nbsp;
						<img src="<%=basePath%>img/order.png" style="height: 20px; width: 20px; ">
						<a href="<%=basePath%>OrderServlet?action=myorder">我的订单</a>
					</c:if>
					<c:if test="${empty username}">
					<a href="<%=basePath%>pages/login.jsp"><span>登陆后解锁更多功能~</span></a>
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
			<li><a class="a1" href="<%=basePath%>RecommendServlet">网站主页</a></li>
			<li><a class="a1" href="<%=basePath%>BookServlet?action=showall">图书区</a></li>
			<li><a class="a1" href="<%=basePath%>FoodServlet?action=showall">食品区</a></li>
			<li><a class="a1" href="<%=basePath%>DigitalServlet?action=showall">数码区</a></li>
		</ul>
		</nav>
	</div>
	
<div style="overflow: hidden;">
<c:if test="${empty visitList}">
		<p style="margin-left:600px;margin-top:50px;">无在线记录及ip信息</p>
		</c:if>
		<c:if test="${not empty visitList}">
                
            <table class="sellproduct" style="width: 80%;margin-left:150px;margin-top:50px;" border="1" >
			<tr bgcolor="#CCCCCC">
			<thead>
					<tr bgcolor="#CCCCCC">
					<th width="180">商品区域</th>
					<th width="180">商品类型</th>
					<th width="180">商品名称</th>
					<th width="180">浏览日期</th>
				</tr>
			</thead>
			<tbody>
			
				<c:forEach  items="${visitList}" var="visit" varStatus="status">
				<tr >
					<td style="text-align: center;height:80px">${visit.productarea}</td>
					<td style="text-align: center;">${visit.producttype}</td>
					<td style="text-align: center;">${visit.productname}</td>
					<td style="text-align: center;">${visit.enterdatetime}</td>
					
					
					
				</tr>
				</c:forEach>
			</tbody>
		</table>
</c:if>
            </div>


</body>
</html>