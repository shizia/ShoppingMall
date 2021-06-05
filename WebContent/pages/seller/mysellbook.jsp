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
 <script type="text/javascript">
        $(function () {
            //给【删除】绑定单击事件
            $("a.deleteproduct").click(function () {
            	var name = $(this).parent().parent().find("td:first").text();
                return confirm("你确定要下架【 " + name + "】吗？")
            });
        });
    </script>
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
<%String productarea=(String)session.getAttribute("productarea"); %>

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
			<li style="background-color:black"><a class="a1" href="<%=basePath%>SellServlet?action=showmysell">销售的商品</a></li>
			<li><a class="a1" href="<%=basePath%>SellServlet?action=showhavesell">用户购买记录</a></li>
			<!--<li><a class="a1" href="<%=basePath%>SellServlet?action=showuserscan">用户浏览记录</a></li> -->			
		  <li><a class="a1" href="<%=basePath%>VisitRecordServlet?action=productvisit&sellerarea=<%=sellerarea %>&sellertype=<%=sellertype%>">用户浏览记录</a></li> 	
	
           <!-- 选中条 -->
		<!-- 	<div id="marker"></div> -->
		</ul>
		</nav>
	</div>

<div class="page-container">
	<div style="margin-left:150px;;margin-top:30px;"> 
		销售员 <%=username%> 所销售的商品为 <%=productarea%>区 的 <%=producttype%>类，
		<span class="r">共有数据：<strong>${productList.size()}</strong> 条</span> 
		
	<form style="margin-top:30px;" action="<%=basePath%>BookServlet?action=getBookByName" method="post">
	<label class="form-label col-xs-8 col-xs-8">根据书名搜索：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" style="width:270px;" name="getbybookname">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;确认&nbsp;&nbsp;">
			</div>
	</form>
		
	</div>
	<div style="margin-top:50px;">
	<a style="color:black;margin-left:80%;" href="<%=basePath%>pages/seller/addbook.jsp">上架商品</a>
	</div>
	<div>
		<table class="sellproduct" style="width: 80%;margin-left:150px;margin-top:10px;" border="1" >
			<tr bgcolor="#CCCCCC">
			<thead>
					<tr bgcolor="#CCCCCC">
					<th width="180">书名</th>
					<th width="180">作者</th>
					<th width="180">简介</th>
					<th width="180">价格</th>
					<th width="180">余量</th>
					<th width="180">图片</th>
					<th width="180">操作</th>
				</tr>
			</thead>
			<tbody>
			<%=request.getAttribute("getByNameError")==null?"":request.getAttribute("getByNameError")%>
				<c:forEach  items="${productList}" var="book" varStatus="status">
				<tr >
					<td id="productname" height="180">${book.bookname}</td>
					<td>${book.bookauthor}</td>
					<td>${book.bookdescribe}</td>
					<td style="text-align: center;">${book.bookprice}</td>
					<td style="text-align: center;">${book.booktotalnum-book.booksoldnum}</td>
					<td ><img src="<%=basePath%>${book.bookpic}" style="height: 150px; width: 150px; "></td>
					
					<td style="text-align: center;">
					<a style="color:black; " href="<%=basePath%>UpdateProductServlet?action=updateproduct&productid=${book.bookid}">修改</a>
					&nbsp;&nbsp;<a class="deleteproduct" style="color:black; " href="<%=basePath%>UpdateProductServlet?action=deleteproduct&productid=${book.bookid}">下架</a>
					</td> 
					
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
	
<%-- 	<div id="page-main">

	
    <div id="product">
        <div class="product_cond">
            <form action="client/bookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>
     

        <c:forEach  items="${productList}" var="book" varStatus="status">
            <div class="product_list">
                <div class="img_div">
                    <img class="product_img" alt="" src="${book.bookpic}"/>
                </div>
                <div class="product_info">
                    <div style="margin-bottom: 10px;" class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.bookname}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.bookauthor}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">${book.bookprice}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.booktotalnum-book.booksoldnum}</span>
                    </div>
                    <div class="book_add">
                        <a style="color:blue; " href="<%=basePath%>??Servlet?action=add&itemid=${book.bookid}">修改</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
	</div> --%>
</body>
</html>