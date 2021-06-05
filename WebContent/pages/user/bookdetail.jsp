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
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script src="<%=basePath%>js/getstaytime.js"></script>


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
<body style="height:1100px;overflow:visible;" onLoad="init(); window.setTimeout('show_secs()',1);">


<!-- <form name="fm0" onSubmit="0">
<font COLOR="#888888" size="-1">您在本网页的停留时间:</font>

<input type="text" name="time_spent" size=7 onFocus="this.blur()">
</form>
<form  name="fm1" id="fm1" class="spendtime" method="post" action="BookServlet">
秒数
<input type="text" class="visitsecond" name="visitsecond">
type="hidden"
</form> -->




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
			<li style="background-color:black"><a class="a1" href="<%=basePath%>BookServlet?action=showall">图书区</a></li>
			<li><a class="a1" href="<%=basePath%>FoodServlet?action=showall">食品区</a></li>
			<li><a class="a1" href="<%=basePath%>DigitalServlet?action=showall">数码区</a></li>
		</ul>
		</nav>
	</div>
	


	<div style="float:left;margin-top: 50px;margin-left: 140px;" class="type-tab">
	 <ul>
      <li><a href="<%=basePath%>BookServlet?action=showall">全部</a></li>
     <li><a  href="<%=basePath%>BookServlet?action=showliterature">文学</a></li>
      <li><a href="<%=basePath%>BookServlet?action=showphilosophy">哲学</a></li>
      <li><a href="<%=basePath%>BookServlet?action=showhistory">历史</a></li>
     <li><a href="<%=basePath%>BookServlet?action=showsociety">社会科学</a></li>
     <li><a href="<%=basePath%>BookServlet?action=showcomputer">计算机科学</a></li>
    </ul>
   </div>
	<div id="page-main"  style="height:600px;overflow-y:hidden;">
	
    <div id="product">
                <div style="float: left;margin-left:100px;margin-top:80px;" class="img_div">
                    <img  style="height:350px;width:350px;" alt="" src="${visitproduct.bookpic}"/>
                </div>
                <div  style="float: left;margin-top:70px;" class="product_info">
                    
                    <div style="margin-bottom: 20px;height:45px;width:500px;border-bottom:2px solid #ccc;" class="book_name">
                        <!-- <span class="sp1">书名:</span> -->
                        <span style="color: gray;font-size:30px;" class="sp2">${visitproduct.bookname}</span>
                    </div>
                    
                    <div style="margin-bottom: 20px;" class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${visitproduct.bookauthor}</span>
                    </div>
                    <div style="width:500px;" class="book_descripe">
                        <span class="sp1">简介:</span>
                        <span class="sp2">${visitproduct.bookdescribe}</span>
                        <br>
                        <div style="margin-bottom: 20px;" >
                        <span class="sp1">价格:</span>
                        <span class="sp2">${visitproduct.bookprice}</span>
                    	</div>
                    	<div>
                        <span class="sp1">库存:</span>
                        <span class="sp2">${visitproduct.booktotalnum-visitproduct.booksoldnum}</span>
                    </div>
                     <br>   <%-- <button bookId="${book.bookid}" class="addToCart">加入购物车</button> --%>
                        <a style="color:blue; " href="<%=basePath%>CartServlet?action=add&itemid=${visitproduct.bookid}">加入购物车</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   <a style="color:blue; " class="a1" href="<%=basePath%>BookServlet?action=showall">返回图书区</a>
                    </div>
                </div>
            
    </div>
    

	</div>
<%-- 	     <%
        long nowtime=session.getLastAccessedTime(); 
        long firsttime=session.getCreationTime();
        long lasttime=(long)session.getAttribute("lasttime");
        if(nowtime==firsttime || lasttime==1){
        	lasttime=firsttime;
        }
     %>
     <h1>初次会话创建时间：<%=new Date(firsttime) %></h1>
     <h1>上次会话创建时间：<%=new Date(lasttime) %></h1>
      <h1>本次会话创建访问时间：<%=new Date(nowtime)%></h1>
     <h1>在线时长：<%=(nowtime-lasttime)/1000 %>秒</h1>

	<%request.getSession().setAttribute("lasttime", nowtime);%> --%>
	<span style="white-space:pre">		</span>
	
</body>
</html>