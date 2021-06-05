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
	
	     <%String sellerarea=(String)request.getSession().getAttribute("productarea"); %>
      <%String sellertype=(String)request.getSession().getAttribute("producttype"); %>
       
    <!-- 分类信息 -->
	<div class="htmleaf-container">
		<nav>
		<ul id="main" style=" width: 1200px;">
			<li ><a class="a1" href="<%=basePath%>pages/seller/index.jsp">网站主页</a></li>
			<li><a class="a1" href="<%=basePath%>SellServlet?action=showmysell">销售的商品</a></li>
			<li><a class="a1" href="<%=basePath%>SellServlet?action=showhavesell">用户购买记录</a></li>
			<li style="background-color:black"><a class="a1" href="<%=basePath%>VisitRecordServlet?action=productvisit&sellerarea=<%=sellerarea %>&sellertype=<%=sellertype%>>">用户浏览记录</a></li> 
		</ul>
		</nav>
	</div>
	
<div style="overflow: hidden;">
<c:if test="${empty visitList}">
		<p style="margin-left:600px;margin-top:50px;">所负责的商品中无用户浏览信息</p>
		</c:if>
		<c:if test="${not empty visitList}">
                
            <table class="sellproduct" style="width: 80%;margin-left:150px;margin-top:50px;" border="1" >
			<tr bgcolor="#CCCCCC">
			<thead>
					<tr bgcolor="#CCCCCC">
					<th width="180" >商品id</th>
					<th width="180">商品名称</th>
					<th width="180">浏览次数</th>
				</tr>
			</thead>
			<tbody>
			
				<c:forEach  items="${visitList}" var="visit" varStatus="status">
				<tr >
					<td height="80" style="text-align: center;">${visit.productid}</td>
					<td style="text-align: center;">${visit.productname}</td>
					<td style="text-align: center;">${visit.count}</td>
					
					
					
				</tr>
				</c:forEach>
			</tbody>
		</table>
</c:if>
            </div>


</body>
</html>