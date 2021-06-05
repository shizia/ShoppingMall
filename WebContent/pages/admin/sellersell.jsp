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
<%String thissellersell=(String)session.getAttribute("thissellersell"); %>
<div  class="head">
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
		<li><a class="a1" href="<%=basePath%>pages/admin/index.jsp">网站主页</a></li>
			<li><a class="a1" href="<%=basePath%>UpdateSellerServlet?action=showallseller">销售员信息</a></li>
			<li ><a class="a1" href="<%=basePath%>UpdateSellerServlet?action=showallseller_enterip">销售员在线记录</a></li>
			<li ><a class="a1" href="<%=basePath%>UpdateSellerServlet?action=showallseller_action">销售员操作日志</a></li>
			<li style="background-color:black"><a class="a1" href="<%=basePath%>UpdateSellerServlet?action=showallseller_sell">销售员销售统计</a></li>
			<li><a class="a1" href="<%=basePath%>pages/admin/productarea_sell.jsp">商品销售统计</a></li></ul></ul>
		</nav>
	</div>

<div class="page-container">
	<div style="margin-left:150px;margin-top:30px;"> 
		<form style="margin-top:30px;" action="<%=basePath%>UpdateSellerServlet?action=getSellByTime" method="post">
	<label class="form-label col-xs-8 col-xs-8">根据时间搜索：</label>
			<div class="formControls col-xs-8 col-sm-8">
				从 <input type="date"  id="logintime" name="logintime1" min=""/>
				&nbsp;&nbsp;&nbsp;&nbsp;到 <input type="date"  id="logintime" name="logintime2" min=""/>
				<!-- <input type="text" class="input-text" style="width:270px;" name="getname"> -->
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;确认&nbsp;&nbsp;">
				<input type="hidden" name="selleridentity" value="<%=thissellersell %>">
				<span><a style="color:#666; " href="<%=basePath%>SellServlet?action=sellBySellerid&selleridentity=<%=thissellersell %>">&nbsp;&nbsp;全部记录</a></span>
			</div>
	</form>
	
		
	</div>
	<div style="margin-top:50px;">

	</div>
	<div>
		<c:if test="${empty sellersellList}">
		<p style="margin-left:650px;margin-top:50px;">无销售记录</p>
		</c:if>
		<c:if test="${not empty sellersellList}">
		<table class="sellproduct" style="width: 80%;margin-left:150px;margin-top:10px;" border="1" >
			<tr bgcolor="#CCCCCC">
			<thead>
					<tr bgcolor="#CCCCCC">
					<th width="180">商品区域</th>
					<th width="180">商品类型</th>
					<th width="180">商品id</th>
					<th width="180">商品名称</th>
					<th width="180">单价</th>
					<th width="180">数量</th>
					<th width="180">总价</th>
					<th width="180">时间</th>
				</tr>
			</thead>
			<tbody>
			<%=request.getAttribute("getByNameError")==null?"":request.getAttribute("getByNameError")%>
			<c:set value="0" var="sum" />  
				<c:forEach  items="${sellersellList}" var="sellersell" varStatus="status">
				<tr >
					<td id="sellerid" height="100">${sellersell.productarea}</td>
					<td id="sellerid" height="100">${sellersell.producttype}</td>
					<td id="sellerid" height="100">${sellersell.productid}</td>
					<td style="text-align: center;">${sellersell.productname}</td>
					<td style="text-align: center;">${sellersell.productprice}</td>
					<td style="text-align: center;">${sellersell.productnum}</td>
					<td style="text-align: center;">${sellersell.productnum*sellersell.productprice}</td>
					<td style="text-align: center;">${sellersell.date}</td>
					<%-- <c:if test="${empty seller.logouttime}">
					<td style="text-align: center;">仍在线</td>
					</c:if>	 --%>	
					<c:set value="${sum + sellersell.productnum*sellersell.productprice}" var="sum" /> 
					
				</tr>
				</c:forEach>
			
			</tbody>
		</table>
		<div style="margin-left:1200px;margin-top:30px;">销售总额:${sum} 元</div>
		</c:if>
	</div>
</div>

</body>
</html>