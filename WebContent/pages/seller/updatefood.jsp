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
<%String updatefoodid=(String)session.getAttribute("updatefoodid"); %>

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
			<li><a class="a1" href="<%=basePath%>SellServlet?action=showhavesell">用户购买记录</a></li>
			<!--<li><a class="a1" href="<%=basePath%>SellServlet?action=showuserscan">用户浏览记录</a></li> -->		
		  <li><a class="a1" href="<%=basePath%>VisitRecordServlet?action=productvisit&sellerarea=<%=sellerarea %>&sellertype=<%=sellertype%>">用户浏览记录</a></li> 	
	
           <!-- 选中条 -->
		<!-- 	<div id="marker"></div> -->
		</ul>
		</nav>
	</div>

<div style=" margin-left: 300px;" class="page-container">
	<!-- <div style="margin-left:150px;;margin-top:30px;"> 
		修改商品信息
	</div> -->
	<%-- <div>
		<table class="sellproduct" style="width: 80%;margin-left:150px;margin-top:80px;" border="1" >
			<tr bgcolor="#CCCCCC">
			<thead>
					<tr bgcolor="#CCCCCC">
					<th width="180">书名</th>
					<th width="180">作者</th>
					<th width="180">简介</th>
					<th width="180">价格</th>
					<th width="180">余量</th>
					<th width="180">图片</th>
				</tr>
			</thead>
			<tbody style="border:none;" >
				<c:forEach  items="${updatefood}" var="food" varStatus="status">
				<tr >
					<td height="180">${food.foodname}</td>
					<td height="180">hahaha</td>
					<td>${food.foodauthor}</td>
					<td>${food.fooddescribe}</td>
					<td style="text-align: center;">${food.foodprice}</td>
					<td style="text-align: center;">${food.foodtotalnum-food.foodsoldnum}</td>
					<td ><img src="<%=basePath%>${food.foodpic}" style="height: 150px; width: 150px; "></td>
					<td style="border:none;" ><a style="color:black; " href="<%=basePath%>SellServlet?action=update&productid=${food.foodid}">确认修改</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div> --%>
	
	
	<form action="<%=basePath%>UpdateProductServlet" method="post" class="form form-horizontal" id="form-member-add">
			<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span></label>
			<div class="formControls col-xs-8 col-sm-9" style="width:300px;margin-left: 40px;">
				<h3> 修改商品信息 </h3>
			</div>
		</div>

		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">名称：</label>
			<div class="formControls col-xs-8 col-sm-9" style="width:300px;">
			<input type="text" class="input-text" style="width:270px;" value="${updatefood.foodname}" id="name" name="name">
			<input type="hidden" name="productid" value="${updatefood.foodid}">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">类型：</label>
			<div class="formControls col-xs-8 col-sm-9" style="width:300px;">
			<input type="text"   disabled="disabled" class="input-text" style="width:270px;" value="${updatefood.foodtype}" >
			<input type="hidden" id="type" name="type" value="${updatefood.foodtype}">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">简介：</label>
			<div class="formControls col-xs-8 col-sm-9" style="width:300px;">
			<input type="text" class="input-text" style="width:270px;" value="${updatefood.fooddescribe}" id="describe" name="describe">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">价格：</label>
			<div class="formControls col-xs-8 col-sm-9" style="width:300px;">
				<input type="number" class="input-text" style="width:270px;" value="${updatefood.foodprice}" id="price" name="price">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">库存余量：</label>
			<div class="formControls col-xs-8 col-sm-9" style="width:300px;">
				<input type="number" class="input-text" style="width:270px;" value="${updatefood.foodtotalnum-updatefood.foodsoldnum}" id="leftnum" name="leftnum">
				<%-- <input type="hidden" name="soldnum" value="${updatefood.foodsoldnum}"> --%>
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