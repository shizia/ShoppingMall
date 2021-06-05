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

    <span class="wel_word">我的订单</span>
    
    <div >
       <span><%=username%>的订单&nbsp;&nbsp;</span>
        <a href="<%=basePath%>RecommendServlet">返回主页</a>
        &nbsp;&nbsp;<a href="<%=basePath%>pages/user/cart.jsp">去购物车</a>
    </div>
</div>

<div id="main">

    <table>
        <tr>
            <td>订单号</td>
            <td>创建时间</td>
            <td>总额</td>
            <td>订单详情</td>

        </tr>
			<tbody>
				<c:forEach  items="${orderList}" var="order" varStatus="status">
				<tr >
					<td id="sellerid" height="100">${order.orderId}</td>
					<td style="text-align: center;">${order.createTime}</td>
					<td style="text-align: center;">${order.price}</td>
					<td style="text-align: center;">
					<a style="color:#3b80c0; " href="<%=basePath%>OrderServlet?action=theorder&detailorderid=${order.orderId}">查看</a>
					</td> 
					
				</tr>
				</c:forEach>
			</tbody>

    </table>


</div>


</body>
</html>