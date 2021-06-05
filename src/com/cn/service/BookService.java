package com.cn.service;

import java.sql.SQLException;
import java.util.List;

import com.cn.subject.Book;
import com.cn.subject.Seller;

public interface BookService {

	int add(Book book,Seller seller,String time);

	int delete(Book book,Seller seller,String time);
	
	int update(Book book,Seller seller,String time);

	List<Book> getAllBook();
	
	List<Book> getBookByType(String booktype);
	
	Book getBookById(String bookId);

	List<Book> getBookByName(String name);


}
