package com.cn.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cn.dao.SellerDao;
import com.cn.subject.IPLoginOut;
import com.cn.subject.Seller;
import com.cn.subject.SellerAction;

public class SellerDaoImpl implements SellerDao{
	
	private String url="jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC";
	private String dbuser="root";
	private String dbpwd="L0S4Q701047root";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private PreparedStatement pstmt2 = null;
	private ResultSet rs = null;

	@Override
	public int addSeller(Seller seller) throws SQLException, Exception {
		String sql = "insert into seller (identity,name,password,productarea,producttype) VALUES (?,?,?,?,?)";
		
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
		pstmt.setObject(1, seller.getIdentity());
		pstmt.setObject(2, seller.getName());
		pstmt.setObject(3, seller.getPassword());
		pstmt.setObject(4, seller.getProductarea());
		pstmt.setObject(5, seller.getProducttype());
		// 执行添加语句
		int status = pstmt.executeUpdate();
		// 关流
		pstmt.close();
		conn.close();
		
		return status;
	}

	@Override
	public int deleteSeller(String identity) throws SQLException {
		String sql = "delete from seller where identity=?";
		
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
		pstmt.setObject(1, identity);
		// 执行删除语句
		int status = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return status;
	}

	@Override
	public int updateSeller(Seller seller) throws SQLException {
		String sql = "update seller set name=?,password=?,productarea=?,producttype=? where identity=?";
		
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

		pstmt.setObject(1, seller.getName());
		pstmt.setObject(2, seller.getPassword());
		pstmt.setObject(3, seller.getProductarea());
		pstmt.setObject(4, seller.getProducttype());
		pstmt.setObject(5, seller.getIdentity());
		
		int status = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return status;
	}

	@Override
	public Seller getSellerById(String identity) throws SQLException {
		String sql = "select * from seller where identity=?";
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
		pstmt.setObject(1, identity);
		rs = pstmt.executeQuery();
		Seller seller = null;

		while (rs.next()) {
			String sellerName = rs.getString("name");
			String password = rs.getString("password");
			String productarea=rs.getString("productarea");
			String producttype=rs.getString("producttype");
			seller = new Seller(identity, sellerName, password,productarea,producttype);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return seller;
	}

	@Override
	public List<Seller> getAllSeller() throws SQLException {
		String sql = "select * from seller";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		
		rs=pstmt.executeQuery();
		
		List<Seller> list = new ArrayList<Seller>();
		while (rs.next()) {
			String sellerid = rs.getString("seller.identity");
			String sellerName = rs.getString("name");
			String password = rs.getString("password");
			String productarea=rs.getString("productarea");
			String producttype=rs.getString("producttype");
			Seller seller = new Seller(sellerid, sellerName, password,productarea,producttype);
			list.add(seller);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
	}

	@Override
	public int insertIpTime(Seller seller, String ip, String time) throws SQLException {
String sql = "insert into selleriptime (identity,name,ip,logintime,ifonline) VALUES (?,?,?,?,?)";
		
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

		pstmt.setObject(1, seller.getIdentity());
		pstmt.setObject(2, seller.getName());
		pstmt.setObject(3, ip);
		pstmt.setObject(4, time);
		pstmt.setObject(5,1);
		
		int status = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return status;
	}

	@Override
	public int updateIpTime(String identity, String time) throws SQLException {
		String sql = "update selleriptime set logouttime=? where identity=? and ifonline=?";
		String sql2 = "update selleriptime set ifonline=? where identity=? and logouttime=?";
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
		pstmt.setObject(2, identity);
		pstmt.setObject(3, 1);
		int status = pstmt.executeUpdate();
		
		pstmt2 = conn.prepareStatement(sql2);

		pstmt2.setObject(1,0);
		pstmt2.setObject(2, identity);
		pstmt2.setObject(3, time);
		status = pstmt2.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return status;
	}

	@Override
	public List<IPLoginOut> getSellerIP(String identity) throws SQLException {
		String sql = "select * from selleriptime where identity=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1,identity );
		rs=pstmt.executeQuery();
		
		List<IPLoginOut> list = new ArrayList<IPLoginOut>();
		while (rs.next()) {
			String name = rs.getString("selleriptime.name");
			String ip = rs.getString("selleriptime.ip");
			String logintime = rs.getString("selleriptime.logintime");
			String logouttime = rs.getString("selleriptime.logouttime");
			int ifonline = rs.getInt("selleriptime.ifonline");
			IPLoginOut iploginout = new IPLoginOut(identity, name,ip, logintime,logouttime,ifonline);
			list.add(iploginout);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
	}

	@Override
	public List<Seller> getSellerByName(String name) throws SQLException {
		String sql = "select * from seller where name=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1,name);
		rs=pstmt.executeQuery();
		
		List<Seller> list = new ArrayList<Seller>();
		while (rs.next()) {
			String sellerid = rs.getString("seller.identity");
			String password = rs.getString("password");
			String productarea=rs.getString("productarea");
			String producttype=rs.getString("producttype");
			Seller seller = new Seller(sellerid,name, password,productarea,producttype);
			list.add(seller);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
	}

	@Override
	public List<IPLoginOut> getSellerIPByTime(String starttime, String endtime) throws SQLException {
		String sql = "select * from selleriptime where logintime>=? and logintime<=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1,starttime);
		pstmt.setObject(2,endtime);
		rs=pstmt.executeQuery();
		
		List<IPLoginOut> list = new ArrayList<IPLoginOut>();
		while (rs.next()) {
			String identity = rs.getString("selleriptime.identity");
			String name = rs.getString("selleriptime.name");
			String ip = rs.getString("selleriptime.ip");
			String logintime = rs.getString("selleriptime.logintime");
			String logouttime = rs.getString("selleriptime.logouttime");
			int ifonline = rs.getInt("selleriptime.ifonline");
			IPLoginOut iploginout = new IPLoginOut(identity, name,ip, logintime,logouttime,ifonline);
			list.add(iploginout);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
	}

	@Override
	public List<SellerAction> getSellerAction(String identity) throws SQLException {
		String sql = "select * from selleraction where selleridentity=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1,identity);
		rs=pstmt.executeQuery();
		
		List<SellerAction> list = new ArrayList<SellerAction>();
		while (rs.next()) {
			String sellername = rs.getString("selleraction.sellername");
			String selleraction = rs.getString("selleraction.selleraction");
			String productarea = rs.getString("selleraction.productarea");
			String producttype = rs.getString("selleraction.producttype");
			String productid = rs.getString("selleraction.productid");
			String productname = rs.getString("selleraction.productname");
			String time = rs.getString("selleraction.time");
			
			SellerAction selleraction1 = new SellerAction(identity,sellername, selleraction,productarea,producttype, productid, productname,time);
			list.add(selleraction1);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
	}

	@Override
	public List<SellerAction> getSellerActionByTime(String starttime, String endtime) throws SQLException {
		String sql = "select * from selleraction where time>=? and time<=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1,starttime);
		pstmt.setObject(2,endtime);
		rs=pstmt.executeQuery();
		
		List<SellerAction> list = new ArrayList<SellerAction>();
		while (rs.next()) {
			String identity = rs.getString("selleraction.selleridentity");
			String sellername = rs.getString("selleraction.sellername");
			String selleraction = rs.getString("selleraction.selleraction");
			String productarea = rs.getString("selleraction.productarea");
			String producttype = rs.getString("selleraction.producttype");
			String productid = rs.getString("selleraction.productid");
			String productname = rs.getString("selleraction.productname");
			String time = rs.getString("selleraction.time");		
			SellerAction selleraction1 = new SellerAction(identity,sellername, selleraction,productarea,producttype, productid, productname,time);
			list.add(selleraction1);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
	}


	

}
