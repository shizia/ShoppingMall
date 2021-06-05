package com.cn.dao.impl;

import com.cn.dao.UserDao;
import com.cn.subject.Book;
import com.cn.subject.Product;
import com.cn.subject.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements UserDao {
	
	private String url="jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC";
	private String dbuser="root";
	private String dbpwd="L0S4Q701047root";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private PreparedStatement pstmt2 = null;
	private ResultSet rs = null;
	
	//增
	public int addUser(User user) throws SQLException {
		String sql = "insert into user (identity,name,password,email) VALUES (?,?,?,?)";
		
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
		pstmt.setObject(1, user.getIdentity());
		pstmt.setObject(2, user.getName());
		pstmt.setObject(3, user.getPassword());
		pstmt.setObject(4, user.getEmail());
		// 执行添加语句
		int status = pstmt.executeUpdate();
		// 关流
		pstmt.close();
		conn.close();
		
		return status;
	}
	
	//删
	public int deleteUser(User user) throws SQLException, Exception {
		String sql = "delete from user where identity=?";
		
		//1、导入驱动,加载具体的驱动类 
		Class.forName("com.mysql.cj.jdbc.Driver");
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, user.getIdentity());
		// 执行删除语句
		int status = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return status;
	}
	
	//改
	public int updateUser(User user) throws SQLException {
		String sql = "update user set name=?,password=?,email=? where identity=?";
		
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

		pstmt.setObject(1, user.getName());
		pstmt.setObject(2, user.getPassword());
		pstmt.setObject(3, user.getEmail());
		pstmt.setObject(4, user.getIdentity());
		
		int status = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return status;
	}

	//查(根据身份证号)
	public User getUserById(String identity) throws SQLException {
		String sql = "select * from user where identity=?";
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
		User user = null;

		while (rs.next()) {
			String userName = rs.getString("name");
			String password = rs.getString("password");
			String email=rs.getString("email");
			user = new User(identity, userName, password,email);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return user;
		
	}

	@Override
	public int deleteUser(String identity) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertIpTime(User user,String ip, String time) throws SQLException {
		String sql = "insert into useriptime (identity,name,ip,logintime,ifonline) VALUES (?,?,?,?,?)";
		
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

		pstmt.setObject(1, user.getIdentity());
		pstmt.setObject(2, user.getName());
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
		String sql = "update useriptime set logouttime=? where identity=? and ifonline=?";
		String sql2 = "update useriptime set ifonline=? where identity=? and logouttime=?";
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
	public List<Product> getRecommendByUserid(String useridentity) throws SQLException {
		//15天内购买记录
		String sql_buyrecord = "SELECT * FROM buyrecord where useridentity=? and date>DATE_SUB(NOW(),INTERVAL 15 DAY)";
		//近5天最多的浏览记录的商品区域类型
		String sql_visit_area_type = "SELECT productarea,producttype,count(*) FROM visitrecord where useridentity=? and enterdatetime>DATE_SUB(NOW(),INTERVAL 5 DAY) group by productarea,producttype order by count(*) desc limit 1";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		//该用户15天内购买记录商品id,存入buylist
		pstmt = conn.prepareStatement(sql_buyrecord);
		pstmt.setObject(1, useridentity);
		rs=pstmt.executeQuery();
		List<String> buylist = new ArrayList<String>();
		while (rs.next()) {
			String buy_productid= rs.getString("buyrecord.productid");
			buylist.add(buy_productid);
		}

		
		//5天内最多浏览的区域，记录为_visitmost
		pstmt = conn.prepareStatement(sql_visit_area_type);
		pstmt.setObject(1, useridentity);
		rs=pstmt.executeQuery();
		String productarea_visitmost=null;
		String producttype_visitmost=null;
		while (rs.next()) {
			productarea_visitmost= rs.getString("visitrecord.productarea");
			producttype_visitmost=rs.getString("visitrecord.producttype");
		}
		System.out.println("productarea_visitmost"+productarea_visitmost);
		System.out.println("producttype_visitmost"+producttype_visitmost);
		
		List<Product> recommendList=new ArrayList<Product>();
		
		if (productarea_visitmost!=null) {
		String sql_recommend = null;
		if(productarea_visitmost.equals("书籍"))
		{
			sql_recommend="SELECT * FROM book where booktype=?";
			pstmt = conn.prepareStatement(sql_recommend);
			pstmt.setObject(1, producttype_visitmost);
			rs=pstmt.executeQuery();
			//最多推荐8个
			int num=0;
			while (rs.next()&&num<8) {
				String recommend_id=rs.getString("book.bookid");
				System.out.println("recommend_id"+recommend_id);
				if (!buylist.contains(recommend_id)) {
					String recommend_area="书籍";
					String recommend_type=rs.getString("book.booktype");
					String recommend_name=rs.getString("book.bookname");
					Double recommend_price=rs.getDouble("book.bookprice");
					String recommend_pic=rs.getString("book.bookpic");
					Product product=new Product( recommend_area,  recommend_type,  recommend_id,  recommend_name,  recommend_price,  recommend_pic);
					recommendList.add(product);
					System.out.println("recommendlist"+recommendList);
					num=num+1;
				}
			}
		
		}
		else if(productarea_visitmost.equals("数码"))
		{
			sql_recommend="SELECT * FROM digital where digitaltype=?";
			pstmt = conn.prepareStatement(sql_recommend);
			pstmt.setObject(1, producttype_visitmost);
			rs=pstmt.executeQuery();
			//最多推荐8个
			int num=0;
			while (rs.next()&&num<8) {
				String recommend_id=rs.getString("digital.digitalid");
				System.out.println("recommend_id"+recommend_id);
				if (!buylist.contains(recommend_id)) {
					String recommend_area="数码";
					String recommend_type=rs.getString("digital.digitaltype");
					String recommend_name=rs.getString("digital.digitalname");
					Double recommend_price=rs.getDouble("digital.digitalprice");
					String recommend_pic=rs.getString("digital.digitalpic");
					Product product=new Product( recommend_area,  recommend_type,  recommend_id,  recommend_name,  recommend_price,  recommend_pic);
					recommendList.add(product);
					num=num+1;
				}
			}
		
		}
		else if(productarea_visitmost.equals("食品"))
		{
			sql_recommend="SELECT * FROM food where foodtype=?";
			pstmt = conn.prepareStatement(sql_recommend);
			pstmt.setObject(1, producttype_visitmost);
			rs=pstmt.executeQuery();
			//最多推荐8个
			int num=0;
			while (rs.next()&&num<8) {
			String recommend_id=rs.getString("food.foodid");
				System.out.println("recommend_id"+recommend_id);
				if (!buylist.contains(recommend_id)) {
					String recommend_area="食品";
					String recommend_type=rs.getString("food.foodtype");
					String recommend_name=rs.getString("food.foodname");
					Double recommend_price=rs.getDouble("food.foodprice");
					String recommend_pic=rs.getString("food.foodpic");
					Product product=new Product( recommend_area,  recommend_type,  recommend_id,  recommend_name,  recommend_price,  recommend_pic);
					recommendList.add(product);
					num=num+1;
				}
			}
		}
		}
		rs.close();
		pstmt.close();
		conn.close();
		System.out.println("recommend_List"+recommendList);
		return recommendList;
	}

}