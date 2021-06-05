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

    <script type="text/javascript">
        $(function () {
            //给【删除】绑定单击事件
            $("a.deleteItem").click(function () {
                return confirm("你确定要删除【" + $("#itemName").text() + "】吗？")
            });

            //给购物车中商品修改数量绑定事件。
            $(".updateCount").change(function () {
                //获取商品名称
                var name = $(this).parent().parent().find("td:first").text();
                //获取商品id的值。
                var itemid= $(this).attr("itemid");
                //获取商品库存
                var remain=parseInt($("#remain").text());
                //获取商品数量
                var count = this.value;
                if(confirm("你确定要将商品【" + name + "】修改数量为："+count+"吗？")) {
                	if (count > remain) {
                		alert("已超过商品余量！");
                		this.value = this.defaultValue;
                	}
                	else{
                    //发起请求，给服务器保存修改
                    location.href = "<%=basePath%>CartServlet?action=updateCount&count="+count+"&itemid="+itemid;
                    }
                }else{
                    //defaultValue属性是表单项dom对象的属性，它表示默认的value属性值
                    this.value = this.defaultValue;
                }
            });
        });
    </script>


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
	<div id="page-main">
	
    <div id="product">
        <div class="product_cond">
            <%-- <form action="<%=basePath%>BookServlet?action=showall" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询"/>
            </form> --%>
    <form style="margin-top:30px;" action="<%=basePath%>BookServlet?action=getBookByName_user" method="post">
	<label class="form-label col-xs-8 col-xs-8">根据书名搜索：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" style="width:270px;" value="${searchname}"  name="getbybookname">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;确认&nbsp;&nbsp;">
			</div>
	</form>
        </div>
        <div style="margin-top:20px;" >

            <%--购物车中没有商品，前台不再显示购物车近期信息--%>
            <c:if test="${not empty username }">
            <c:if test="${empty sessionScope.cart.items}">
                <span id="cartTotalCount"> </span>
                <div>
                    <span style="color: blue;margin-left:20px;" id="cartLastName">您的购物车中没有商品</span>
                </div>
            </c:if>
            </c:if>

            <%-- 购物车中有商品，前台显示购物车近期信息--%>
            <c:if test="${not empty sessionScope.cart.items}">
<%--                 <span id="cartTotalCount">您的购物车中有${sessionScope.cart.totalCount}件商品</span> --%>
                <div>
                    <span style="margin-left:20px;" id="cartLastName"><span style="color:blue" id="cartLastName">${sessionScope.addName}</span>已加入购物车</span>
                </div>
            </c:if>
        </div>

        <c:forEach  items="${bookList}" var="book" varStatus="status">
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
                        <%-- <button bookId="${book.bookid}" class="addToCart">加入购物车</button> --%>
                        <c:if test="${not empty username }">
                         <a style="color:blue; " href="<%=basePath%>VisitRecordServlet?action=visitproduct_record&productid=${book.bookid}&productarea=book">商品详情</a>
                        </c:if>
                        <c:if test="${empty username }">
                         <a style="color:blue; " href="<%=basePath%>VisitRecordServlet?action=visitproduct&productid=${book.bookid}&productarea=book">商品详情</a>
                        </c:if>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style="color:blue; " href="<%=basePath%>CartServlet?action=add&itemid=${book.bookid}">加入购物车</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
	</div>

</body>
</html>