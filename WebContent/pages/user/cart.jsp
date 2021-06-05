<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">

</style>
<%
//  通过request域对象，动态获取base标签
    String basePath = request.getScheme() //获取请求的协议
                        + "://"
                        + request.getServerName()  //获取请求的服务器 ip 或域名
                        + ":"
                        + request.getServerPort()  //获取请求的服务器端口号
                        + request.getContextPath() + "/";  //获取当前工程路径
%>
<!--写 base 标签， 永远固定相对路径跳转的结果-->
<base href="<%=basePath%>">
<%--将公共的css和jqery标签抽取出来--%>
<link type="text/css" rel="stylesheet" href="<%=basePath%>static/css/cart.css" >
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>

    <meta charset="UTF-8">
    <title>购物车</title>

    <script type="text/javascript">
        $(function () {
            //给【删除】绑定单击事件
            $("a.deleteItem").click(function () {
                return confirm("你确定要删除【" + $("#itemName").text() + "】吗？")
            });

            //给清空购物车绑定单击事件。
            $("#clearCart").click(function () {
                return confirm("你确定要清空购物车吗？")
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
<body>
<%String username=(String)session.getAttribute("username"); %>
<div id="header">

    <span class="wel_word">购物车</span>
    <img src="<%=basePath%>img/11.png" style="height: 70px; width: 70px ">
    <div >
       <span><%=username%>的购物车&nbsp;&nbsp;</span>
        <a href="<%=basePath%>OrderServlet?action=myorder">我的订单</a>
        <a href="<%=basePath%>RecommendServlet">返回</a>
    </div>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>商品余量</td>
            <td>购买数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>

        <%--购物车，没有东西的情况下--%>
        <c:if test="${empty sessionScope.cart.items}">
            <tr>
                <td colspan="5"><a href="<%=basePath%>/pages/user/index.jsp">购物车空空如也，再去逛逛吧~</a></td>
            </tr>
        </c:if>

        <%--购物车，有东西的情况下--%>
        <c:if test="${not empty sessionScope.cart.items}">
            <c:forEach items="${sessionScope.cart.items}" var="item">
                <tr>
                    <td id="itemName">${item.value.name}</td>
                    <td id="remain">${item.value.remain}</td>
                    <td>
                        <input class="updateCount"
                               itemid = "${item.value.id}"
                               type="text" style="width: 80px;" value="${item.value.count}">
                    </td>
                    <td>${item.value.price}</td>
                    <td>${item.value.totalPrice}</td>
                    <td><a class="deleteItem" href="CartServlet?action=delete&itemid=${item.value.id}">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>

    <%--		如果购物车非空，就显示下面的总金额信息。--%>
    <c:if test="${not empty sessionScope.cart.items}">
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a id="clearCart" href="CartServlet?action=clearAll">清空购物车</a></span>
            <span class="cart_span"><a href="OrderServlet?action=createOrder">去结账</a></span>
        </div>
    </c:if>

</div>


</body>
</html>