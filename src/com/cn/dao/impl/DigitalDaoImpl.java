package com.cn.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cn.dao.DigitalDao;
import com.cn.subject.Digital;
import com.cn.subject.Seller;

public class DigitalDaoImpl implements DigitalDao{
	private String url="jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC";
	private String dbuser="root";
	private String dbpwd="L0S4Q701047root";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//增(刚增加时默认soldnum=0)
	public int addDigital(Digital digital,Seller seller,String time) throws SQLException {
		String sql = "insert into digital (digitalid,digitalname,digitaltype,digitaldescribe,digitalprice,digitalpic,digitaltotalnum,digitalsoldnum) VALUES (?,?,?,?,?,?,?,?)";
		String sql2="insert into selleraction(selleridentity,sellername,selleraction,productarea,producttype,productid,productname,time) VALUES (?,?,?,?,?,?,?,?)";
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
		pstmt.setObject(1, digital.getDigitalid());
		pstmt.setObject(2,digital.getDigitalname());
		pstmt.setObject(3,digital.getDigitaltype());
		pstmt.setObject(4,digital.getDigitaldescribe());
		pstmt.setObject(5,digital.getDigitalprice());
		pstmt.setObject(6,digital.getDigitalpic());
		pstmt.setObject(7,digital.getDigitaltotalnum());
		pstmt.setObject(8,digital.getDigitalsoldnum());
		// 执行添加语句
		int status = pstmt.executeUpdate();
		
		pstmt = conn.prepareStatement(sql2);
		pstmt.setObject(1,seller.getIdentity());
		pstmt.setObject(2,seller.getName());
		pstmt.setObject(3,"上架");
		pstmt.setObject(4,"数码");
		pstmt.setObject(5,digital.getDigitaltype());
		pstmt.setObject(6,digital.getDigitalid());
		pstmt.setObject(7,digital.getDigitalname());
		pstmt.setObject(8,time);
		status = pstmt.executeUpdate();

		
		
		// 关流
		pstmt.close();
		conn.close();
		
		return status;
	}
	
	//删,根据id
	public int deleteDigital(Digital digital,Seller seller,String time) throws SQLException, Exception {
		String sql = "delete from digital where digitalid=?";
		String sql2="insert into selleraction(selleridentity,sellername,selleraction,productarea,producttype,productid,productname,time) VALUES (?,?,?,?,?,?,?,?)";
		
		//1、导入驱动,加载具体的驱动类 
		Class.forName("com.mysql.cj.jdbc.Driver");
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, digital.getDigitalid());
		// 执行删除语句
		int status = pstmt.executeUpdate();
		
		pstmt = conn.prepareStatement(sql2);
		pstmt.setObject(1,seller.getIdentity());
		pstmt.setObject(2,seller.getName());
		pstmt.setObject(3,"下架");
		pstmt.setObject(4,"数码");
		pstmt.setObject(5,digital.getDigitaltype());
		pstmt.setObject(6,digital.getDigitalid());
		pstmt.setObject(7,digital.getDigitalname());
		pstmt.setObject(8,time);
		status = pstmt.executeUpdate();
		
		
		pstmt.close();
		conn.close();
		
		return status;
	}
	
	/*
	 * public int deleteDigital(String digitalid) throws SQLException, Exception {
	 * String sql = "delete from digital where digitalid=?";
	 * 
	 * //1、导入驱动,加载具体的驱动类 Class.forName("com.mysql.cj.jdbc.Driver"); //2、与数据库建立连接
	 * conn = DriverManager.getConnection(url, dbuser, dbpwd);
	 * 
	 * pstmt = conn.prepareStatement(sql); pstmt.setObject(1, digitalid); // 执行删除语句
	 * int status = pstmt.executeUpdate();
	 * 
	 * pstmt.close(); conn.close();
	 * 
	 * return status; }
	 */
	
	
	//改
	public int updateDigital(Digital digital,Seller seller,String time) throws SQLException {
		String sql = "update digital set digitalname=?,digitaltype=?,digitaldescribe=?,digitalprice=?,digitalpic=?,digitaltotalnum=?,digitalsoldnum=? where digitalid=?";
		String sql2="insert into selleraction(selleridentity,sellername,selleraction,productarea,producttype,productid,productname,time) VALUES (?,?,?,?,?,?,?,?)";
		
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

		pstmt.setObject(1, digital.getDigitalname());
		pstmt.setObject(2,digital.getDigitaltype());
		pstmt.setObject(3,digital.getDigitaldescribe());
		pstmt.setObject(4,digital.getDigitalprice());
		pstmt.setObject(5,digital.getDigitalpic());
		pstmt.setObject(6,digital.getDigitaltotalnum());
		pstmt.setObject(7,digital.getDigitalsoldnum());
		pstmt.setObject(8,digital.getDigitalid());
		int status = pstmt.executeUpdate();
		
		pstmt = conn.prepareStatement(sql2);
		pstmt.setObject(1,seller.getIdentity());
		pstmt.setObject(2,seller.getName());
		pstmt.setObject(3,"修改");
		pstmt.setObject(4,"数码");
		pstmt.setObject(5,digital.getDigitaltype());
		pstmt.setObject(6,digital.getDigitalid());
		pstmt.setObject(7,digital.getDigitalname());
		pstmt.setObject(8,time);
		status = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return status;
	}

	//查全部
	public List<Digital> getAllDigital() throws SQLException {
		String sql = "select * from digital";
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
		List<Digital> list = new ArrayList<Digital>();
		while (rs.next()) {
			String digitalid = rs.getString("digital.digitalid");
			String digitalname = rs.getString("digital.digitalname");
			String digitaltype = rs.getString("digital.digitaltype");
			String digitaldescribe = rs.getString("digital.digitaldescribe");
			double digitalprice = rs.getDouble("digital.digitalprice");
			String digitalpic = rs.getString("digital.digitalpic");
			int digitaltotalnum=rs.getInt("digital.digitaltotalnum");
			int digitalsoldnum=rs.getInt("digital.digitalsoldnum");
			
			Digital digital = new Digital(digitalid,digitalname, digitaltype,digitaldescribe,digitalprice,digitalpic,digitaltotalnum,digitalsoldnum);
			list.add(digital);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
		
		}
	
	//按类型
	public List<Digital> getDigitalByType(String digitaltype) throws SQLException {
		String sql = "select * from digital where digitaltype=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, digitaltype);
		rs = pstmt.executeQuery();

		List<Digital> list = new ArrayList<Digital>();
		while (rs.next()) {
			String digitalid = rs.getString("digital.digitalid");
			String digitalname = rs.getString("digital.digitalname");
			String digitaldescribe = rs.getString("digital.digitaldescribe");
			double digitalprice = rs.getDouble("digital.digitalprice");
			String digitalpic = rs.getString("digital.digitalpic");
			int digitaltotalnum=rs.getInt("digital.digitaltotalnum");
			int digitalsoldnum=rs.getInt("digital.digitalsoldnum");
			
			Digital digital = new Digital(digitalid,digitalname, digitaltype,digitaldescribe,digitalprice,digitalpic,digitaltotalnum,digitalsoldnum);
			list.add(digital);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
		
		}
	
	//查(根据编号)
	public Digital getDigitalById(String digitalid) throws SQLException {
		String sql = "select * from digital where digitalid=?";
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
		pstmt.setObject(1, digitalid);
		rs = pstmt.executeQuery();
		Digital digital = null;

		while (rs.next()) {
			String digitalname = rs.getString("digital.digitalname");
			String digitaltype = rs.getString("digital.digitaltype");
			String digitaldescribe = rs.getString("digital.digitaldescribe");
			double digitalprice = rs.getDouble("digital.digitalprice");
			String digitalpic = rs.getString("digital.digitalpic");
			int digitaltotalnum=rs.getInt("digital.digitaltotalnum");
			int digitalsoldnum=rs.getInt("digital.digitalsoldnum");
			
			digital = new Digital(digitalid,digitalname,digitaltype,digitaldescribe,digitalprice,digitalpic,digitaltotalnum,digitalsoldnum);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return digital;
		
	}
	
	//查（根据名字）
	public List<Digital> getDigitalByName(String searchname) throws SQLException {
		String sql = "SELECT * FROM digital WHERE digitalname like ? ";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, "%"+searchname+"%");
		rs=pstmt.executeQuery();
		List<Digital> list = new ArrayList<Digital>();
		while (rs.next()) {
			String digitalid = rs.getString("digital.digitalid");
			String digitalname = rs.getString("digital.digitalname");
			String digitaltype = rs.getString("digital.digitaltype");
			String digitaldescribe = rs.getString("digital.digitaldescribe");
			double digitalprice = rs.getDouble("digital.digitalprice");
			String digitalpic = rs.getString("digital.digitalpic");
			int digitaltotalnum=rs.getInt("digital.digitaltotalnum");
			int digitalsoldnum=rs.getInt("digital.digitalsoldnum");
			
			Digital digital = new Digital(digitalid,digitalname,digitaltype,digitaldescribe,digitalprice,digitalpic,digitaltotalnum,digitalsoldnum);
			list.add(digital);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
		
		}

	@Override
	public int updateDigital_norecord(Digital digital) throws SQLException {
		String sql = "update digital set digitalname=?,digitaltype=?,digitaldescribe=?,digitalprice=?,digitalpic=?,digitaltotalnum=?,digitalsoldnum=? where digitalid=?";
	
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

		pstmt.setObject(1, digital.getDigitalname());
		pstmt.setObject(2,digital.getDigitaltype());
		pstmt.setObject(3,digital.getDigitaldescribe());
		pstmt.setObject(4,digital.getDigitalprice());
		pstmt.setObject(5,digital.getDigitalpic());
		pstmt.setObject(6,digital.getDigitaltotalnum());
		pstmt.setObject(7,digital.getDigitalsoldnum());
		pstmt.setObject(8,digital.getDigitalid());
		int status = pstmt.executeUpdate();

		
		pstmt.close();
		conn.close();
		
		return status;
	}


	
	
}
