package com.cn.dao;

import java.sql.SQLException;
import java.util.List;

import com.cn.subject.*;

public interface BookDao {

	int addBook(Book book,Seller seller,String time) throws SQLException, Exception;
	
	//int deleteBook(String bookid,Seller seller,String time) throws SQLException, Exception;
	
	int deleteBook(Book book,Seller seller,String time) throws SQLException, Exception;
	
	int updateBook(Book book,Seller seller,String time) throws SQLException;
	
	//非销售人员的动作，为卖出后的数量更新
	int updateBook_norecord(Book book) throws SQLException;
	
	//查找所有书
	List<Book> getAllBook() throws SQLException;
	
	//通过类型找
	List<Book> getBookByType(String booktype) throws SQLException;
	
	//通过名字查找书
	List<Book> getBookByName(String bookname) throws SQLException;
	
	//根据id找书
	Book getBookById(String bookid)  throws SQLException;
}
