<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date"%>
<%@ page import="java.sql.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%String sellerarea=(String)request.getSession().getAttribute("productarea"); %>
<%String sellertype=(String)request.getSession().getAttribute("producttype"); %>
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
</style>
</head>
<body style="height:1100px;overflow:visible;">

<%String username=(String)session.getAttribute("username"); %>
<%String producttype=(String)session.getAttribute("producttype"); %>
<%String productarea=(String)session.getAttribute("productarea_ch"); %>
<%double totalsell=(Double)session.getAttribute("totalsell"); %>

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
			<li><a class="a1" href="<%=basePath%>/pages/seller/index.jsp">网站主页</a></li>
			<li><a class="a1" href="<%=basePath%>SellServlet?action=showmysell">销售的商品</a></li>
			<li style="background-color:black;"><a class="a1" href="<%=basePath%>SellServlet?action=showhavesell">用户购买记录</a></li>
			<!--<li><a class="a1" href="<%=basePath%>SellServlet?action=showuserscan">用户浏览记录</a></li> -->		
		  <li><a class="a1" href="<%=basePath%>VisitRecordServlet?action=productvisit&sellerarea=<%=sellerarea %>&sellertype=<%=sellertype%>">用户浏览记录</a></li> 	
	
           <!-- 选中条 -->
		<!-- 	<div id="marker"></div> -->
		</ul>
		</nav>
	</div>

<div class="page-container">

	<div style="margin-left:150px;;margin-top:30px;"> 
		<form style="margin-top:30px;" action="<%=basePath%>SellServlet?action=showhavesellByTime" method="post">
	<label class="form-label col-xs-8 col-xs-8">根据时间搜索：</label>
			<div class="formControls col-xs-8 col-sm-8">
				从 <input type="date"  id="time1" name="time1" min=""/>
				&nbsp;&nbsp;&nbsp;&nbsp;到 <input type="date"  id="time2" name="time2" min=""/>
				<!-- <input type="text" class="input-text" style="width:270px;" name="getname"> -->
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;确认&nbsp;&nbsp;">
			</div>
	</form>
		
	</div>

	<div style="margin-left:150px;;margin-top:30px;"> 
		<span class="r"><%=username%> 负责过的商品范围内，用户购买记录共有：<strong>${buyrecordList.size()}</strong> 条</span> 
		
	</div>
	<div>
		<table class="sellproduct" style="width: 80%;margin-left:150px;margin-top:80px;" border="1" >
			<tr bgcolor="#CCCCCC">
			<thead>
					<tr bgcolor="#CCCCCC">
					<th width="120">商品分区</th>
					<th width="120">商品类型</th>
					<th width="80">商品价格</th>
					<th width="80">购买数量</th>
					<th width="180">总金额</th>
					<th width="180">购买时间</th>
					<th width="180">用户id</th>
					
				</tr>
			</thead>
			<tbody>
			<%=request.getAttribute("getByNameError")==null?"":request.getAttribute("getByNameError")%>
			<c:set value="0" var="sum" />  
				<c:forEach  items="${buyrecordList}" var="product" varStatus="status">
				<tr >
					<td style="text-align: center;" height="80">${product.productarea}</td>
					<td style="text-align: center;">${product.producttype}</td>
					<td style="text-align: center;">${product.productprice}</td>
					<td style="text-align: center;">${product.productnum}</td>
					<td style="text-align: center;">${product.productnum*product.productprice}</td>
					<td style="text-align: center;">${product.date}</td>
					<td style="text-align: center;">${product.useridentity}</td>
					<c:set value="${sum + product.productnum*product.productprice}" var="sum" /> 
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div style="margin-left:1200px;margin-top:30px;">销售总额:${sum} 元</div>
		
	</div>
</div>
	
</body>
</html>