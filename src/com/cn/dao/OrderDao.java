package com.cn.dao;

import com.cn.subject.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {

    //添加订单到数据库中
    public int saveOrder(Order order) throws SQLException;

    public List<Order> queryUserOrders(String useridentity) throws SQLException;
	/*
	 * //查询所有订单 public List<Order> queryOrders();
	 * 
	 * //修改订单发货状态 public int changeOrderStatus(String orderId, int status);
	 * 
	 * 
	 */
}
