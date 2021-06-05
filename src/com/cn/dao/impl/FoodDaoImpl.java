package com.cn.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cn.dao.FoodDao;
import com.cn.subject.Food;
import com.cn.subject.Seller;

public class FoodDaoImpl implements FoodDao{
	private String url="jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC";
	private String dbuser="root";
	private String dbpwd="L0S4Q701047root";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//增(刚增加时默认soldnum=0)
	public int addFood(Food food,Seller seller,String time) throws SQLException {
		String sql = "insert into food (foodid,foodname,foodtype,fooddescribe,foodprice,foodpic,foodtotalnum,foodsoldnum) VALUES (?,?,?,?,?,?,?,?)";
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
		pstmt.setObject(1, food.getFoodid());
		pstmt.setObject(2,food.getFoodname());
		pstmt.setObject(3,food.getFoodtype());
		pstmt.setObject(4,food.getFooddescribe());
		pstmt.setObject(5,food.getFoodprice());
		pstmt.setObject(6,food.getFoodpic());
		pstmt.setObject(7,food.getFoodtotalnum());
		pstmt.setObject(8,food.getFoodsoldnum());
		// 执行添加语句
		int status = pstmt.executeUpdate();
		
		pstmt = conn.prepareStatement(sql2);
		pstmt.setObject(1,seller.getIdentity());
		pstmt.setObject(2,seller.getName());
		pstmt.setObject(3,"上架");
		pstmt.setObject(4,"食品");
		pstmt.setObject(5,food.getFoodtype());
		pstmt.setObject(6,food.getFoodid());
		pstmt.setObject(7,food.getFoodname());
		pstmt.setObject(8,time);
		status = pstmt.executeUpdate();
		
		// 关流
		pstmt.close();
		conn.close();
		
		return status;
	}
	
	//删,根据id
	public int deleteFood(Food food, Seller seller, String time) throws SQLException, Exception {
		String sql = "delete from food where foodid=?";
		String sql2="insert into selleraction(selleridentity,sellername,selleraction,productarea,producttype,productid,productname,time) VALUES (?,?,?,?,?,?,?,?)";
		
		//1、导入驱动,加载具体的驱动类 
		Class.forName("com.mysql.cj.jdbc.Driver");
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, food.getFoodid());
		// 执行删除语句
		int status = pstmt.executeUpdate();
		
		pstmt = conn.prepareStatement(sql2);
		pstmt.setObject(1,seller.getIdentity());
		pstmt.setObject(2,seller.getName());
		pstmt.setObject(3,"下架");
		pstmt.setObject(4,"食品");
		pstmt.setObject(5,food.getFoodtype());
		pstmt.setObject(6,food.getFoodid());
		pstmt.setObject(7,food.getFoodname());
		pstmt.setObject(8,time);
		status = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return status;
	}
	

	/*
	 * public int deleteFood(String foodid) throws SQLException, Exception { String
	 * sql = "delete from food where foodid=?";
	 * 
	 * //1、导入驱动,加载具体的驱动类 Class.forName("com.mysql.cj.jdbc.Driver"); //2、与数据库建立连接
	 * conn = DriverManager.getConnection(url, dbuser, dbpwd);
	 * 
	 * pstmt = conn.prepareStatement(sql); pstmt.setObject(1, foodid); // 执行删除语句 int
	 * status = pstmt.executeUpdate();
	 * 
	 * pstmt.close(); conn.close();
	 * 
	 * return status; }
	 */
	
	
	//改
	public int updateFood(Food food,Seller seller,String time) throws SQLException {
		String sql = "update food set foodname=?,foodtype=?,fooddescribe=?,foodprice=?,foodpic=?,foodtotalnum=?,foodsoldnum=? where foodid=?";
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

		pstmt.setObject(1, food.getFoodname());
		pstmt.setObject(2,food.getFoodtype());
		pstmt.setObject(3,food.getFooddescribe());
		pstmt.setObject(4,food.getFoodprice());
		pstmt.setObject(5,food.getFoodpic());
		pstmt.setObject(6,food.getFoodtotalnum());
		pstmt.setObject(7,food.getFoodsoldnum());
		pstmt.setObject(8,food.getFoodid());		
		int status = pstmt.executeUpdate();
		
		pstmt = conn.prepareStatement(sql2);
		pstmt.setObject(1,seller.getIdentity());
		pstmt.setObject(2,seller.getName());
		pstmt.setObject(3,"修改");
		pstmt.setObject(4,"食品");
		pstmt.setObject(5,food.getFoodtype());
		pstmt.setObject(6,food.getFoodid());
		pstmt.setObject(7,food.getFoodname());
		pstmt.setObject(8,time);
		status = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return status;
	}

	//查全部
	public List<Food> getAllFood() throws SQLException {
		String sql = "select * from food";
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
		List<Food> list = new ArrayList<Food>();
		while (rs.next()) {
			String foodid = rs.getString("food.foodid");
			String foodname = rs.getString("food.foodname");
			String foodtype = rs.getString("food.foodtype");
			String fooddescribe = rs.getString("food.fooddescribe");
			double foodprice = rs.getDouble("food.foodprice");
			String foodpic = rs.getString("food.foodpic");
			int foodtotalnum=rs.getInt("food.foodtotalnum");
			int foodsoldnum=rs.getInt("food.foodsoldnum");
			
			Food food = new Food(foodid,foodname, foodtype,fooddescribe,foodprice,foodpic,foodtotalnum,foodsoldnum);
			list.add(food);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
		
		}
	
	//按类型
	public List<Food> getFoodByType(String foodtype) throws SQLException {
		String sql = "select * from food where foodtype=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, foodtype);
		rs = pstmt.executeQuery();

		List<Food> list = new ArrayList<Food>();
		while (rs.next()) {
			String foodid = rs.getString("food.foodid");
			String foodname = rs.getString("food.foodname");
			String fooddescribe = rs.getString("food.fooddescribe");
			double foodprice = rs.getDouble("food.foodprice");
			String foodpic = rs.getString("food.foodpic");
			int foodtotalnum=rs.getInt("food.foodtotalnum");
			int foodsoldnum=rs.getInt("food.foodsoldnum");
			
			Food food = new Food(foodid,foodname, foodtype,fooddescribe,foodprice,foodpic,foodtotalnum,foodsoldnum);
			list.add(food);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
		
		}
	
	//查(根据编号)
	public Food getFoodById(String foodid) throws SQLException {
		String sql = "select * from food where foodid=?";
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
		pstmt.setObject(1, foodid);
		rs = pstmt.executeQuery();
		Food food = null;

		while (rs.next()) {
			String foodname = rs.getString("food.foodname");
			String foodtype = rs.getString("food.foodtype");
			String fooddescribe = rs.getString("food.fooddescribe");
			double foodprice = rs.getDouble("food.foodprice");
			String foodpic = rs.getString("food.foodpic");
			int foodtotalnum=rs.getInt("food.foodtotalnum");
			int foodsoldnum=rs.getInt("food.foodsoldnum");
			
			food = new Food(foodid,foodname,foodtype,fooddescribe,foodprice,foodpic,foodtotalnum,foodsoldnum);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return food;
		
	}
	
	//查（根据名字）
	public List<Food> getFoodByName(String searchname) throws SQLException {
		String sql = "SELECT * FROM food WHERE foodname like ? ";
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
		List<Food> list = new ArrayList<Food>();
		while (rs.next()) {
			String foodid = rs.getString("food.foodid");
			String foodname = rs.getString("food.foodname");
			String foodtype = rs.getString("food.foodtype");
			String fooddescribe = rs.getString("food.fooddescribe");
			double foodprice = rs.getDouble("food.foodprice");
			String foodpic = rs.getString("food.foodpic");
			int foodtotalnum=rs.getInt("food.foodtotalnum");
			int foodsoldnum=rs.getInt("food.foodsoldnum");
			
			Food food = new Food(foodid,foodname,foodtype,fooddescribe,foodprice,foodpic,foodtotalnum,foodsoldnum);
			list.add(food);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
		
		}

	@Override
	public int updateFood_norecord(Food food) throws SQLException {
		String sql = "update food set foodname=?,foodtype=?,fooddescribe=?,foodprice=?,foodpic=?,foodtotalnum=?,foodsoldnum=? where foodid=?";
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

		pstmt.setObject(1, food.getFoodname());
		pstmt.setObject(2,food.getFoodtype());
		pstmt.setObject(3,food.getFooddescribe());
		pstmt.setObject(4,food.getFoodprice());
		pstmt.setObject(5,food.getFoodpic());
		pstmt.setObject(6,food.getFoodtotalnum());
		pstmt.setObject(7,food.getFoodsoldnum());
		pstmt.setObject(8,food.getFoodid());		
		int status = pstmt.executeUpdate();

		
		pstmt.close();
		conn.close();
		
		return status;
	}


	
	
}
