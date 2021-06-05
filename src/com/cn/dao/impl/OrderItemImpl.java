package com.cn.dao.impl;

import com.cn.dao.OrderItemDao;
import com.cn.subject.Book;
import com.cn.subject.OrderItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：OrderItem订单项实现类
 *
 * @author
 * @create
 */
public class OrderItemImpl implements OrderItemDao {
	private String url = "jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC";
	private String dbuser = "root";
	private String dbpwd = "L0S4Q701047root";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
    //添加订单项
    @Override
    public int saveOrderItem(OrderItem orderItem) throws Exception {
        String sql ="INSERT INTO orderitem(`name`,`count`,`price`,`totalprice`,`orderid`) VALUES(?,?,?,?,?)";
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

     		pstmt.setObject(1,orderItem.getName());
     		pstmt.setObject(2,orderItem.getCount());
     		pstmt.setObject(3,orderItem.getPrice());
     		pstmt.setObject(4,orderItem.getTotalPrice());
     		pstmt.setObject(5, orderItem.getOrderId());
     		int status = pstmt.executeUpdate();

     		pstmt.close();
     		conn.close();

     		return status;
     	
    }

	/*
	 * //查询订单详情数据
	 */ 
	 @Override 
	 public List<OrderItem> queryOrderItemsByOrderId(String orderId) throws SQLException {
	  String sql ="SELECT * FROM orderitem WHERE orderid = ?"; 
	  
	  try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, orderId);
		rs = pstmt.executeQuery();
	  
		List<OrderItem> list = new ArrayList<OrderItem>();
		while (rs.next()) {
			String name = rs.getString("orderitem.name");
			int count = rs.getInt("orderitem.count");
			double price = rs.getDouble("orderitem.price");
			double totalprice = rs.getDouble("orderitem.totalprice");

			OrderItem orderitem = new OrderItem(name, count,price,totalprice);
			list.add(orderitem);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return list; 
	  }
}
	 
