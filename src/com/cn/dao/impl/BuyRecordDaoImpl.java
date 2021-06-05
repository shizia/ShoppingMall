package com.cn.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cn.dao.BuyRecordDao;
import com.cn.subject.BuyRecord;

public class BuyRecordDaoImpl implements BuyRecordDao {
	
	private String url="jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC";
	private String dbuser="root";
	private String dbpwd="L0S4Q701047root";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private PreparedStatement pstmt1 = null;
	private ResultSet rs = null;

	@Override
	public int addBuyRecord(BuyRecord buyrecord) throws SQLException, Exception {
		String sql1="select * from seller where productarea=? and producttype=?";
		String sql = "insert into buyrecord (useridentity,productarea,producttype,productid,productname,productprice,productnum,date,selleridentity) VALUES (?,?,?,?,?,?,?,?,?)";
		
		//1、导入驱动,加载具体的驱动类 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt1 = conn.prepareStatement(sql1);
		pstmt1.setObject(1, buyrecord.getProductarea());
		pstmt1.setObject(2, buyrecord.getProducttype());
		rs=pstmt1.executeQuery();
		String selleridentity=null;
		while (rs.next()) {
			selleridentity = rs.getString("seller.identity");
		}
		
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, buyrecord.getUseridentity());
		pstmt.setObject(2, buyrecord.getProductarea());
		pstmt.setObject(3, buyrecord.getProducttype());
		pstmt.setObject(4, buyrecord.getProductid());
		pstmt.setObject(5, buyrecord.getProductname());
		pstmt.setObject(6, buyrecord.getProductprice());
		pstmt.setObject(7, buyrecord.getProductnum());
		pstmt.setObject(8, buyrecord.getDate());
		pstmt.setObject(9,selleridentity);

		// 执行添加语句
		int status = pstmt.executeUpdate();
		// 关流
		pstmt.close();
		conn.close();
		
		return status;	
	}
	

	@Override
	public List<BuyRecord> getBuyRecordBySellerid(String sellerid) throws SQLException {
		String sql = "select * from buyrecord where selleridentity =? ";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, sellerid);
		rs=pstmt.executeQuery();
		List<BuyRecord> list = new ArrayList<BuyRecord>();
		while (rs.next()) {
			String useridentity = rs.getString("buyrecord.useridentity");
			String productarea = rs.getString("buyrecord.productarea");
			String producttype = rs.getString("buyrecord.producttype");
			String productid = rs.getString("buyrecord.productid");
			String productname = rs.getString("buyrecord.productname");
			double productprice = rs.getDouble("buyrecord.productprice");
			int productnum = rs.getInt("buyrecord.productnum");
			String date = rs.getString("buyrecord.date");
			BuyRecord buyrecord = new BuyRecord(useridentity, productarea, producttype,productid,productname,productprice,productnum, date,sellerid);
			list.add(buyrecord);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
	}


	@Override
	public List<BuyRecord> getBuyRecordByProducttype(String productarea, String producttype) throws SQLException {
		String sql = "select * from buyrecord where productarea=? and producttype=?";
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
		List<BuyRecord> list = new ArrayList<BuyRecord>();
		while (rs.next()) {
			String useridentity = rs.getString("buyrecord.useridentity");
			String productid = rs.getString("buyrecord.productid");
			String productname = rs.getString("buyrecord.productname");
			double productprice = rs.getDouble("buyrecord.productprice");
			int productnum = rs.getInt("buyrecord.productnum");
			String date = rs.getString("buyrecord.date");
			String selleridentity=rs.getString("buyrecord.selleridentity");
			BuyRecord buyrecord = new BuyRecord(useridentity, productarea, producttype,productid,productname,productprice,productnum, date,selleridentity);
			list.add(buyrecord);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
	}


	@Override
	public List<BuyRecord> getBuyRecordByProductarea(String productarea) throws SQLException {
		String sql = "select * from buyrecord where productarea=?";
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
		rs=pstmt.executeQuery();
		List<BuyRecord> list = new ArrayList<BuyRecord>();
		while (rs.next()) {
			String useridentity = rs.getString("buyrecord.useridentity");
			String producttype = rs.getString("buyrecord.producttype");
			String productid = rs.getString("buyrecord.productid");
			String productname = rs.getString("buyrecord.productname");
			double productprice = rs.getDouble("buyrecord.productprice");
			int productnum = rs.getInt("buyrecord.productnum");
			String date = rs.getString("buyrecord.date");
			String selleridentity=rs.getString("buyrecord.selleridentity");
			BuyRecord buyrecord = new BuyRecord(useridentity, productarea, producttype,productid,productname,productprice,productnum, date,selleridentity);
			list.add(buyrecord);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
	}


	@Override
	public List<BuyRecord> getBuyRecordByTimeSellid(String starttime, String endtime,String selleridentity) throws SQLException {
		String sql = "select * from buyrecord where date>=? and date<=? and selleridentity=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, starttime);
		pstmt.setObject(2, endtime);
		pstmt.setObject(3, selleridentity);
		rs=pstmt.executeQuery();
		List<BuyRecord> list = new ArrayList<BuyRecord>();
		while (rs.next()) {
			String useridentity = rs.getString("buyrecord.useridentity");
			String productarea = rs.getString("buyrecord.productarea");
			String producttype = rs.getString("buyrecord.producttype");
			String productid = rs.getString("buyrecord.productid");
			String productname = rs.getString("buyrecord.productname");
			double productprice = rs.getDouble("buyrecord.productprice");
			int productnum = rs.getInt("buyrecord.productnum");
			String date = rs.getString("buyrecord.date");
			BuyRecord buyrecord = new BuyRecord(useridentity, productarea, producttype,productid,productname,productprice,productnum, date,selleridentity);
			list.add(buyrecord);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
	}


}
