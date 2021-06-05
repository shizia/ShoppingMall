package com.cn.dao.impl;

import com.cn.dao.AdminDao;
import com.cn.subject.Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminDaoImpl implements AdminDao {
	
	private String url="jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC";
	private String dbuser="root";
	private String dbpwd="L0S4Q701047root";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//增
	public int addAdmin(Admin admin) throws SQLException {
		String sql = "insert into administrator (identity,name,password,email) VALUES (?,?,?,?)";
		
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
		//pstmt.setObject(1, user.getIdentity());

		// 执行添加语句
		int status = pstmt.executeUpdate();
		// 关流
		pstmt.close();
		conn.close();
		
		return status;
	}
	
	//删
	public int deleteAdmin(Admin admin) throws SQLException, Exception {
		String sql = "delete from administrator where identity=?";
		
		//1、导入驱动,加载具体的驱动类 
		Class.forName("com.mysql.cj.jdbc.Driver");
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		//pstmt.setObject(1, user.getIdentity());
		// 执行删除语句
		int status = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return status;
	}
	
	//改
	public int updateAdmin(Admin admin) throws SQLException {
		String sql = "update administrator set name=?,password=?,email=? where identity=?";
		
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

		//pstmt.setObject(1, user.getName());

		
		int status = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return status;
	}

	//查(根据身份证号)
	public Admin getAdminById(String identity) throws SQLException {
		String sql = "select * from administrator where identity=?";
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
		
		Admin admin = null;
		  
		 while (rs.next()) { 
		String name = rs.getString("name"); 
		 String password =rs.getString("password"); 
		 admin = new Admin(identity, name, password);
		 }
		rs.close();
		pstmt.close();
		conn.close();
		
		return admin;
		
	}

	@Override
	public int deleteAdmin(String identity) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}