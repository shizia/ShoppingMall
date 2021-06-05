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
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/recommend.css">

<link type="text/css" rel="stylesheet" href="<%=basePath%>static/css/style.css" >
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
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
						欢迎回来，<span><%=username%></span>&nbsp;&nbsp;
					</c:if>
					<c:if test="${empty username}">
					<a href="<%=basePath%>pages/register.jsp"><span>注册</span></a>
					<a href="<%=basePath%>pages/login.jsp"><span>登录</span></a>
					</c:if>
					</div>
				</li>			
			
				<li>
					<div class="div2" style="margin-bottom:0px;margin-top:4px;">
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
					&nbsp;&nbsp;<a  href="<%=basePath%>pages/login.jsp"><span>登陆后解锁更多功能~</span></a>
					</c:if>
					</div>
				</li>
				
				<li>
					<div class="div3" >
					<c:if test="${not empty username }">
						<img src="<%=basePath%>img/head.png" style="height: 20px; width: 20px; ">
					</c:if>
					</div>
					<div class="div2" style="margin-top:5px;">
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
		<li style="background-color:black"><a class="a1" href="<%=basePath%>RecommendServlet">网站主页</a></li>
			<li><a class="a1" href="<%=basePath%>BookServlet?action=showall">图书区</a></li>
			<li><a class="a1" href="<%=basePath%>FoodServlet?action=showall">食品区</a></li>
			<li><a class="a1" href="<%=basePath%>DigitalServlet?action=showall">数码区</a></li>
						
		  
           <!-- 选中条 -->
		<!-- 	<div id="marker"></div> -->
		</ul>
		</nav>
		</div>
    
	<!-- 轮播图 -->
	
    <div class="show_pic">
        <img id="pic_change" src="<%=basePath%>pages/user/indexpic/pic5.png">
    </div>
    

<%--     <div id="box">
    
    <c:forEach  items="${recommendList}" var="recommend" varStatus="status">
    <ul class="ul_list">
        <li class="li_list"><img src="${recommend.pic}" alt=""/></li>
    </ul>
    <div class="odiv_nav">
        <span class="span_list"></span>
    </div>
  </c:forEach>
    <div class="left_click"></div>
    <div class="right_click">></div>
    <div class="btn_left"></div>
    <div class="btn_right"></div>
</div> --%>
<div style="margin-left:150px;margin-top:30px;width:1200px" id="product">
    <h3>猜你喜欢</h3>
     <c:if test="${ empty recommendList}">
     <p style="margin-top:30px;">到处逛逛，才能猜到您的喜好哦 ~</p>
     </c:if>
    <c:if test="${ not empty recommendList}">
    
 <c:forEach  items="${recommendList}" var="recommend" varStatus="status">
  <div class="product_list" style="height:250px;margin-top:30px;">
<div class="product_info">

                    <div class="img_div">
                    <img class="product_img" alt=""  src="${recommend.pic}"/>
                    
                   <p class="sp2">${recommend.name}</p>
                   <c:if test="${recommend.area eq '书籍'}">
                    <a style="color:blue; " href="<%=basePath%>VisitRecordServlet?action=visitproduct_record&productid=${recommend.id}&productarea=book">去看看</a>
                	</c:if>
                	<c:if test="${recommend.area eq '数码'}">
                    <a style="color:blue; " href="<%=basePath%>VisitRecordServlet?action=visitproduct_record&productid=${recommend.id}&productarea=digital">去看看</a>
                	</c:if>
                	<c:if test="${recommend.area eq '食品'}">
                    <a style="color:blue; " href="<%=basePath%>VisitRecordServlet?action=visitproduct_record&productid=${recommend.id}&productarea=food">去看看</a>
                	</c:if>
                </div>
		<div style="height:300px"></div>
 		</div>
                </div>
                        </c:forEach>
                        
        </c:if>
                 
    </div>
    


	
</body>
</html>