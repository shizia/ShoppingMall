package com.cn.dao.impl;

import com.cn.dao.OrderDao;
import com.cn.subject.BuyRecord;
import com.cn.subject.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 类描述：订单Dao实现类
 *
 * @author
 * @create
 */
public class OrderDaoImpl implements OrderDao {
	private String url = "jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC";
	private String dbuser = "root";
	private String dbpwd = "L0S4Q701047root";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	/**
	 * @throws SQLException 
	 * @MethodName: 保存订单信息
	 * @Return:
	 **/
	@Override
	public int saveOrder(Order order) throws SQLException {
		String sql = "INSERT INTO theorder(`orderid`,`createtime`,`price`,`status`,`userid`) VALUES(?,?,?,?,?)";
		// 1、导入驱动,加载具体的驱动类
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);

		pstmt = conn.prepareStatement(sql);

		pstmt.setObject(1,order.getOrderId());
		pstmt.setObject(2, order.getCreateTime());
		pstmt.setObject(3, order.getPrice());
		pstmt.setObject(4, order.getStatus());
		pstmt.setObject(5, order.getUserIdentity());
		int status = pstmt.executeUpdate();

		pstmt.close();
		conn.close();

		return status;
	}

	@Override
	public List<Order> queryUserOrders(String useridentity) throws SQLException {
		String sql = "select * from theorder where userid =? ";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, useridentity);
		rs=pstmt.executeQuery();
		 List<Order> list = new ArrayList<Order>();
		while (rs.next()) {
			String orderid = rs.getString("theorder.orderid");
			Date createtime = rs.getDate("theorder.createtime");			
			double price = rs.getDouble("theorder.price");
			Order order = new Order(orderid,createtime,price,useridentity);
			list.add(order);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
	}

	/*    *//**
			 * @MethodName: 查询所有订单
			 * @param: []
			 * @Return: java.util.List<com.learn.pojo.Order>
			 **/
	/*
	 * @Override public List<Order> queryOrders() { String sql =
	 * "SELECT `order_id` AS `orderId`,`create_time`AS `createTime`" +
	 * ",`price` AS `price`,`status` AS `status`,`user_id`  AS `userId` FROM t_order "
	 * ; return queryForList(Order.class, sql); }
	 * 
	 *//**
		 * @MethodName: 修改订单信息
		 * @param: [orderId, Status]
		 * @Return: int
		 **/
	/*
	 * @Override public int changeOrderStatus(String orderId, int status) { String
	 * sql ="UPDATE t_order SET `status`  = ? WHERE `order_id` = ?"; return
	 * update(sql, status,orderId); }
	 * 
	 *//**
		 * @MethodName: 查询指定用户的订单方法
		 * @param: [user_id]
		 * @Return: java.util.List<com.learn.pojo.Order>
		 **//*
			 * @Override public List<Order> queryUserOrders(Integer user_id) { String sql =
			 * "SELECT `order_id` AS `orderId`,`create_time`AS `createTime`" +
			 * ",`price` AS `price`,`status` AS `status`,`user_id`  AS `userId` FROM t_order WHERE `user_id` = ? "
			 * ; return queryForList(Order.class, sql,user_id); }
			 */
}
