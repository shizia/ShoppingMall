package com.cn.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.service.OrderService;
import com.cn.service.impl.OrderServiceImpl;
import com.cn.subject.Cart;
import com.cn.subject.Order;
import com.cn.subject.OrderItem;
import com.cn.subject.User;

import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;
import javax.mail.*;


public class OrderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 获取Service层的对象
	private OrderService orderService = new OrderServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String useridentity = (String) request.getSession().getAttribute("useridentity");
		if (action.equals("createOrder")) {
			createOrder(request, response);
		}
		else if (action.equals("myorder")) {
			List<Order> list = null;
			try {
				list=orderService.queryUserOrders(useridentity);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getSession().setAttribute("orderList", list);
			System.out.println("orderlist!!"+list);
			response.sendRedirect(request.getContextPath() + "/pages/user/order.jsp");
		}
		else if (action.equals("theorder")) {
			String detailorderid = request.getParameter("detailorderid");
			List<OrderItem> list = null;
			list=orderService.showOrderDetail(detailorderid);

			request.getSession().setAttribute("orderdetailList", list);
			System.out.println("orderdetailList"+list);
			response.sendRedirect(request.getContextPath() + "/pages/user/orderdetail.jsp");
		}
		
	}	
	
	
	protected void createOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 先获取 Cart 购物车对象
		Cart cart = (Cart) request.getSession().getAttribute("cart");

		// 获取Session域中的user对象
		User user = (User) request.getSession().getAttribute("user");
		System.out.println("kkkkkkk"+user);

		// 判断user对象是空
		if (user == null) {
			// 请求转发到登录页面
			request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
			// 结束Servlet方法
			return;
		}

		// user对象不为空，获取userId
		String useridentity = user.getIdentity();

		// 调用 orderService.createOrder(Cart,UserId);生成订单
		String orderId = orderService.createOrder(cart, useridentity);

		// 将订单号保存到Session域中
		request.getSession().setAttribute("orderId", orderId);
		
		List<OrderItem> orderUserDetails =orderService.showOrderDetail(orderId);
		request.getSession().setAttribute("orderUserDetails",orderUserDetails);
		System.out.println("whywhywhy"+orderUserDetails);
		
		//发送邮箱！！
		String toemail=(String)request.getSession().getAttribute("useremail");

		String smtphost = "smtp.163.com"; // 发送邮件服务器
		String senduser = "shiziaemail@163.com"; // 邮件服务器登录用户名
		String password = "VAGRTAYVGYTFITXD"; // 邮件服务器登录密码
		String from = "shiziaemail@163.com"; // 发送人邮件地址
		String to = toemail; // 接受人邮件地址
		String subject = "订单确认"; // 邮件标题
		String body = "您已成功下单！047的杂货店感谢您的惠顾！"; // 邮件内容
			
			
			// 以下为发送程序
			try {
			//初始化Properties类对象
			Properties props = new Properties();
			
			//阿里云禁用了25端口
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.setProperty("mail.smtp.socketFactory.port", "465");
			props.setProperty("mail.smtp.port", "465");
			
			//设置mail.smtp.host属性
			props.put("mail.smtp.host", smtphost);
			//设置使用验证
			props.put("mail.smtp.auth","true");
			// 获取非共享的session对象
			Session ssn= Session.getInstance(props,null);
			//创建一个默认的MimeMessage对象。
			MimeMessage message = new MimeMessage(ssn);
			//创建InternetAddress对象
			InternetAddress fromAddress = new InternetAddress(from);
			//设置From: 头部的header字段
			message.setFrom(fromAddress);
			//创建InternetAddress对象
			InternetAddress toAddress = new InternetAddress(to);
			//设置 To: 头部的header字段
			message.addRecipient(Message.RecipientType.TO, toAddress);
			//设置 Subject: header字段
			message.setSubject(subject);
			// 现在设置的实际消息
			message.setText(body);
			//定义发送协议
			Transport transport = ssn.getTransport("smtp");
			//建立与服务器的链接
			transport.connect(smtphost, senduser, password);
			//发送邮件
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			//transport.send(message);
			//关闭邮件传输
			transport.close();
			request.getSession().setAttribute("cart",null);
			//response.getWriter().write("<script > alert(\"订单确认邮件已发送到您的邮箱,请注意查收！\");location='cart.jsp';</script>");
			
			} catch(Exception m) //捕获异常
			{
			System.out.println(m.toString());
			m.printStackTrace();
			}

		// 跳到结算成功页面,使用重定向，防止表单被重复提交。
		response.sendRedirect(request.getContextPath() + "/pages/user/checkorder.jsp");
	}

	/**
	 * @MethodName: 获取所有订单的方法
	 * @param: [request, response]
	 * @Return: void
	 **/
	/*
	 * protected void showAllOrders(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // 获取Session域中的user对象 User
	 * user = (User) request.getSession().getAttribute("user");
	 * 
	 * // 判断user如果不是管理员。 if ("admin".equals(user.getUsername())) { //
	 * 调用Service来获取所有的订单项,管理员查看所有订单 List<Order> orders =
	 * orderService.showAllOrders(); // 保存到request域中 request.setAttribute("orders",
	 * orders); // 转发到order_manager.jsp显示数据
	 * request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(
	 * request, response); } else { // 其他用户查看自己的订单。 List<Order> user_orders =
	 * orderService.showUserOrders(user.getId()); // 保存到request域中
	 * request.setAttribute("user_orders", user_orders); // 转发到order_manager.jsp显示数据
	 * request.getRequestDispatcher("/pages/order/order.jsp").forward(request,
	 * response); } }
	 */

	/**
	 * @MethodName: 修改订单状态，状态为1已发货。
	 * @param: [request, response]
	 * @Return: void
	 **/
	/*
	 * protected void changeOrderStatus(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException {
	 * //获取前台传来的 String orderId = request.getParameter("orderId");
	 * 
	 * //调用Service来修改指定ID的订单项 orderService.sendOrder(orderId);
	 * 
	 * //请求重定向到原来的页面。 response.sendRedirect(request.getContextPath() +
	 * "/orderServlet?action=showAllOrders"); }
	 * 
	 *//**
		 * @MethodName: 用户签收订单，状态为2
		 * @param: [request, response]
		 * @Return: void
		 **/
	/*
	 * protected void receiverOrder(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { //获取前台传来的 String orderId =
	 * request.getParameter("orderId");
	 * 
	 * //调用Service将指定的订单ID修改状态为已签收。 orderService.receiverOrder(orderId);
	 * 
	 * //请求重定向到原来的页面。 response.sendRedirect(request.getContextPath() +
	 * "/orderServlet?action=showAllOrders"); }
	 * 
	 *//**
		 * @MethodName: 查看订单详情
		 * @param: [request, response]
		 * @Return: void
		 */
	/*
	 * protected void showOrderDetail(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException {
	 * 
	 * 
	 * 
	 * //如果是管理员，则跳到管理员查看详情页面。 if("admin".equals(user.getUsername())){
	 * List<OrderItem> orderAdminDetails = orderService.showOrderDetail(orderId);
	 * request.setAttribute("orderAdminDetails",orderAdminDetails);
	 * request.getRequestDispatcher("/pages/manager/orderItem_manager.jsp").forward(
	 * request, response); } else{ //否则就是用户查看详情页面 List<OrderItem> orderUserDetails
	 * =orderService.showOrderDetail(orderId);
	 * request.setAttribute("orderUserDetails",orderUserDetails);
	 * request.getRequestDispatcher("/pages/order/orderItem_user.jsp").forward(
	 * request, response); } }
	 */
			 


}
