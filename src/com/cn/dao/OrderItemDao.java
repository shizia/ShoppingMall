package com.cn.dao;

import com.cn.subject.OrderItem;

import java.sql.SQLException;
import java.util.List;


public interface OrderItemDao  {
    //保存订单细项
    public int saveOrderItem(OrderItem orderItem) throws Exception;

    //查询订单细项
    public  List<OrderItem> queryOrderItemsByOrderId(String orderId) throws SQLException;
}
