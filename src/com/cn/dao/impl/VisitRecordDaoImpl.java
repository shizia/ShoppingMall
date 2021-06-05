package com.cn.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cn.dao.VisitRecordDao;
import com.cn.subject.Book;
import com.cn.subject.ProductVisit;
import com.cn.subject.VisitRecord;

public class VisitRecordDaoImpl implements VisitRecordDao {
	
	private String url="jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC";
	private String dbuser="root";
	private String dbpwd="L0S4Q701047root";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private PreparedStatement pstmt2 = null;
	private ResultSet rs = null;

	@Override
	public int addVisitRecord(VisitRecord visitrecord) throws SQLException, Exception {
		String sql = "insert into visitrecord (useridentity,username,productarea,producttype,productid,productname,enterdatetime,ifonline,productprice) VALUES (?,?,?,?,?,?,?,?,?)";
		
		
		//1、导入驱动,加载具体的驱动类 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);

		pstmt.setObject(1,visitrecord.getUseridentity());
		pstmt.setObject(2,visitrecord.getProductname());
		pstmt.setObject(3,visitrecord.getProductarea());
		pstmt.setObject(4,visitrecord.getProducttype());
		pstmt.setObject(5,visitrecord.getProductid());
		pstmt.setObject(6,visitrecord.getProductname());
		pstmt.setObject(7,visitrecord.getEnterdatetime());
		pstmt.setObject(8,1);
		pstmt.setObject(9,visitrecord.getProductprice());
		
		int status = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return status;
	}
	

	@Override
	public List<VisitRecord> getVisitRecordByUserid(String userid) throws SQLException {
		String sql = "select * from visitrecord where useridentity =? ";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, userid);
		rs=pstmt.executeQuery();
		List<VisitRecord> list = new ArrayList<VisitRecord>();
		while (rs.next()) {
			String useridentity = rs.getString("visitrecord.useridentity");
			String username = rs.getString("visitrecord.username");
			String productarea = rs.getString("visitrecord.productarea");
			String producttype = rs.getString("visitrecord.producttype");
			String productid = rs.getString("visitrecord.productid");
			String productname = rs.getString("visitrecord.productname");
			String enterdatetime= rs.getString("visitrecord.enterdatetime");
			String spendtime= rs.getString("visitrecord.spendtime");
			int ifonline=rs.getInt("visitrecord.ifonline");
			double productprice=rs.getInt("visitrecord.productprice");
			VisitRecord visitrecord = new VisitRecord(useridentity,username, productarea, producttype,productid,productname,enterdatetime,spendtime,ifonline,productprice);
			list.add(visitrecord);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
	}


	@Override
	public int updateVisitRecord(VisitRecord visitrecord, String time) throws SQLException, Exception {
		String sql = "update visitrecord set spendtime=?,ifonline=? where useridentity=? and enterdatetime=?";
		//String sql2 = "update visitrecord set ifonline=? where useridentity=? and spendtime=?";
		//1、导入驱动,加载具体的驱动类 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);

		pstmt.setObject(1, time);
		pstmt.setObject(2, 0);
		pstmt.setObject(3, visitrecord.getUseridentity());
		pstmt.setObject(4,visitrecord.getEnterdatetime());
		
		int status = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return status;
	}


	@Override
	public VisitRecord getVisitRecordById(String productid) throws SQLException {
		String sql = "select * from visitrecord where productid=?";
		//1、导入驱动,加载具体的驱动类 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, productid);
		rs = pstmt.executeQuery();
		VisitRecord  visitrecord = null;

		while (rs.next()) {
			String useridentity = rs.getString("visitrecord.useridentity");
			String username = rs.getString("visitrecord.username");
			String productarea = rs.getString("visitrecord.productarea");
			String producttype = rs.getString("visitrecord.producttype");
			String productname = rs.getString("visitrecord.productname");
			String enterdatetime= rs.getString("visitrecord.enterdatetime");
			String spendtime= rs.getString("visitrecord.spendtime");
			int ifonline= rs.getInt("visitrecord.ifonline");
			double productprice=rs.getInt("visitrecord.productprice");
			visitrecord = new VisitRecord(useridentity,username, productarea, producttype,productid,productname,enterdatetime,spendtime,ifonline,productprice);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return visitrecord;
	}


	@Override
	public List<ProductVisit> getVisitRecordByProduct(String productarea, String producttype)
			throws SQLException {
		String sql = "select productid,productname,count(*) from visitrecord where productarea=? and producttype=? group by productid,productname";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, productarea);
		pstmt.setObject(2, producttype);
		rs=pstmt.executeQuery();
		List<ProductVisit> list = new ArrayList<ProductVisit>();
		while (rs.next()) {
			String productid = rs.getString("productid");
			String productname = rs.getString("productname");
			int count=rs.getInt("count(*)");
			ProductVisit visit = new ProductVisit(productname,productid,count);
			list.add(visit);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
	}

}
