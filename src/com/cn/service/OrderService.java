package com.cn.service;

import com.cn.subject.Cart;
import com.cn.subject.Order;
import com.cn.subject.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {

	public String createOrder(Cart cart, String userIdentity);
	
	public List<Order> queryUserOrders(String useridentity) throws SQLException;
	
	public List<OrderItem> showOrderDetatil(String orderid) throws SQLException;
	/*
	 * public List<Order> showAllOrders();
	 * 
	 * 
	 *//**
		 * @MethodName: 将订单状态改为发货
		 * @param: [orderId]
		 * @Return: int
		 **/
	/*
	 * public int sendOrder(String orderId);
	 * 
	 *//**
		 * @MethodName: 查询指定用户的订单
		 * @param: [user_id]
		 * @Return: java.util.List<com.learn.pojo.Order>
		 **//*

			 * @MethodName: 修改订单状态为签收
			 * @param: [orderId]
			 * @Return: int
			 **/
	/*
	 * public int receiverOrder(String orderId);
	 * 
	 *//**
		 * @MethodName: 查询指定订单号的订单细项
		 * @param: [orderId]
		 * @Return: java.util.List<com.learn.pojo.OrderItem>
		 **/
		public List<OrderItem> showOrderDetail(String orderId);
	
}
