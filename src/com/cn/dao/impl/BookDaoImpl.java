package com.cn.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cn.dao.BookDao;
import com.cn.subject.Book;
import com.cn.subject.Seller;

public class BookDaoImpl implements BookDao{
	private String url="jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC";
	private String dbuser="root";
	private String dbpwd="L0S4Q701047root";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//增(刚增加时默认soldnum=0)
	public int addBook(Book book,Seller seller,String time) throws SQLException {
		String sql = "insert into book (bookid,bookname,booktype,bookauthor,bookdescribe,bookprice,bookpic,booktotalnum,booksoldnum) VALUES (?,?,?,?,?,?,?,?,?)";
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
		pstmt.setObject(1, book.getBookid());
		pstmt.setObject(2,book.getBookname());
		pstmt.setObject(3,book.getBooktype());
		pstmt.setObject(4,book.getBookauthor());
		pstmt.setObject(5,book.getBookdescribe());
		pstmt.setObject(6,book.getBookprice());
		pstmt.setObject(7,book.getBookpic());
		pstmt.setObject(8,book.getBooktotalnum());
		pstmt.setObject(9,book.getBooksoldnum());
		int status = pstmt.executeUpdate();
		
		pstmt = conn.prepareStatement(sql2);
		pstmt.setObject(1,seller.getIdentity());
		pstmt.setObject(2,seller.getName());
		pstmt.setObject(3,"上架");
		pstmt.setObject(4,"书籍");
		pstmt.setObject(5,book.getBooktype());
		pstmt.setObject(6,book.getBookid());
		pstmt.setObject(7,book.getBookname());
		pstmt.setObject(8,time);
		
		// 执行添加语句
		status = pstmt.executeUpdate();
		// 关流
		pstmt.close();
		conn.close();
		
		return status;
	}
	
	//删
	public int deleteBook(Book book,Seller seller,String time) throws SQLException, Exception {
		String sql = "delete from book where bookid=?";
		String sql2="insert into selleraction(selleridentity,sellername,selleraction,productarea,producttype,productid,productname,time) VALUES (?,?,?,?,?,?,?,?)";
		
		//1、导入驱动,加载具体的驱动类 
		Class.forName("com.mysql.cj.jdbc.Driver");
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, book.getBookid());
		// 执行删除语句
		int status = pstmt.executeUpdate();
		
		pstmt = conn.prepareStatement(sql2);
		pstmt.setObject(1,seller.getIdentity());
		pstmt.setObject(2,seller.getName());
		pstmt.setObject(3,"下架");
		pstmt.setObject(4,"书籍");
		pstmt.setObject(5,book.getBooktype());
		pstmt.setObject(6,book.getBookid());
		pstmt.setObject(7,book.getBookname());
		pstmt.setObject(8,time);
		
		// 执行添加语句
		status = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return status;
	}
	

	/*
	 * public int deleteBook(String bookid) throws SQLException, Exception { String
	 * sql = "delete from book where bookid=?";
	 * 
	 * //1、导入驱动,加载具体的驱动类 Class.forName("com.mysql.cj.jdbc.Driver"); //2、与数据库建立连接
	 * conn = DriverManager.getConnection(url, dbuser, dbpwd);
	 * 
	 * pstmt = conn.prepareStatement(sql); pstmt.setObject(1, bookid); // 执行删除语句 int
	 * status = pstmt.executeUpdate();
	 * 
	 * pstmt.close(); conn.close();
	 * 
	 * return status; }
	 */
	
	
	//改
	public int updateBook(Book book,Seller seller,String time) throws SQLException {
		String sql = "update book set bookname=?,bookauthor=?,booktype=?,bookdescribe=?,bookprice=?,bookpic=?,booktotalnum=?,booksoldnum=? where bookid=?";
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

		pstmt.setObject(1, book.getBookname());
		pstmt.setObject(2,book.getBookauthor());
		pstmt.setObject(3,book.getBooktype());
		pstmt.setObject(4,book.getBookdescribe());
		pstmt.setObject(5,book.getBookprice());
		pstmt.setObject(6,book.getBookpic());
		pstmt.setObject(7,book.getBooktotalnum());
		pstmt.setObject(8,book.getBooksoldnum());
		pstmt.setObject(9,book.getBookid());	
		int status = pstmt.executeUpdate();
		
		pstmt = conn.prepareStatement(sql2);
		pstmt.setObject(1,seller.getIdentity());
		pstmt.setObject(2,seller.getName());
		pstmt.setObject(3,"修改");
		pstmt.setObject(4,"书籍");
		pstmt.setObject(5,book.getBooktype());
		pstmt.setObject(6,book.getBookid());
		pstmt.setObject(7,book.getBookname());
		pstmt.setObject(8,time);
		status = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return status;
	}

	//查全部
	public List<Book> getAllBook() throws SQLException {
		String sql = "select * from book";
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
		List<Book> list = new ArrayList<Book>();
		while (rs.next()) {
			String bookid = rs.getString("book.bookid");
			String bookname = rs.getString("book.bookname");
			String booktype = rs.getString("book.booktype");
			String bookauthor = rs.getString("book.bookauthor");
			String bookdescribe = rs.getString("book.bookdescribe");
			double bookprice = rs.getDouble("book.bookprice");
			String bookpic = rs.getString("book.bookpic");
			int booktotalnum=rs.getInt("book.booktotalnum");
			int booksoldnum=rs.getInt("book.booksoldnum");
			
			Book book = new Book(bookid,bookname, booktype,bookauthor,bookdescribe,bookprice,bookpic,booktotalnum,booksoldnum);
			list.add(book);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
		
		}
	
	//按类型
	public List<Book> getBookByType(String booktype) throws SQLException {
		String sql = "select * from book where booktype=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2、与数据库建立连接
		conn = DriverManager.getConnection(url, dbuser, dbpwd);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, booktype);
		rs = pstmt.executeQuery();

		List<Book> list = new ArrayList<Book>();
		while (rs.next()) {
			String bookid = rs.getString("book.bookid");
			String bookname = rs.getString("book.bookname");
			String bookauthor = rs.getString("book.bookauthor");
			String bookdescribe = rs.getString("book.bookdescribe");
			double bookprice = rs.getDouble("book.bookprice");
			String bookpic = rs.getString("book.bookpic");
			int booktotalnum=rs.getInt("book.booktotalnum");
			int booksoldnum=rs.getInt("book.booksoldnum");
			
			Book book = new Book(bookid,bookname, booktype,bookauthor,bookdescribe,bookprice,bookpic,booktotalnum,booksoldnum);
			list.add(book);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
		
		}
	
	//查(根据编号)
	public Book getBookById(String bookid) throws SQLException {
		String sql = "select * from book where bookid=?";
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
		pstmt.setObject(1, bookid);
		rs = pstmt.executeQuery();
		Book book = null;

		while (rs.next()) {
			String bookname = rs.getString("book.bookname");
			String bookauthor = rs.getString("book.bookauthor");
			String booktype = rs.getString("book.booktype");
			String bookdescribe = rs.getString("book.bookdescribe");
			double bookprice = rs.getDouble("book.bookprice");
			String bookpic = rs.getString("book.bookpic");
			int booktotalnum=rs.getInt("book.booktotalnum");
			int booksoldnum=rs.getInt("book.booksoldnum");
			
			book = new Book(bookid,bookname,booktype,bookauthor,bookdescribe,bookprice,bookpic,booktotalnum,booksoldnum);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return book;
		
	}
	
	//查（根据名字）
	public List<Book> getBookByName(String searchname) throws SQLException {
		String sql = "SELECT * FROM book WHERE bookname like  ?";
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
		List<Book> list = new ArrayList<Book>();
		while (rs.next()) {
			String bookid = rs.getString("book.bookid");
			String bookname = rs.getString("book.bookname");
			String booktype = rs.getString("book.booktype");
			String bookauthor = rs.getString("book.bookauthor");
			String bookdescribe = rs.getString("book.bookdescribe");
			double bookprice = rs.getDouble("book.bookprice");
			String bookpic = rs.getString("book.bookpic");
			int booktotalnum=rs.getInt("book.booktotalnum");
			int booksoldnum=rs.getInt("book.booksoldnum");
			
			Book book = new Book(bookid,bookname,booktype,bookauthor,bookdescribe,bookprice,bookpic,booktotalnum,booksoldnum);
			list.add(book);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list;
		
		}

	@Override
	public int updateBook_norecord(Book book) throws SQLException {
		String sql = "update book set bookname=?,bookauthor=?,booktype=?,bookdescribe=?,bookprice=?,bookpic=?,booktotalnum=?,booksoldnum=? where bookid=?";
		
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

		pstmt.setObject(1, book.getBookname());
		pstmt.setObject(2,book.getBookauthor());
		pstmt.setObject(3,book.getBooktype());
		pstmt.setObject(4,book.getBookdescribe());
		pstmt.setObject(5,book.getBookprice());
		pstmt.setObject(6,book.getBookpic());
		pstmt.setObject(7,book.getBooktotalnum());
		pstmt.setObject(8,book.getBooksoldnum());
		pstmt.setObject(9,book.getBookid());	
		int status = pstmt.executeUpdate();
		
		
		pstmt.close();
		conn.close();
		
		return status;
	}

	
	
}
