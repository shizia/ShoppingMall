package com.cn.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.cn.dao.BookDao;
import com.cn.dao.impl.BookDaoImpl;
import com.cn.service.BookService;
import com.cn.subject.Book;
import com.cn.subject.Seller;

public class BookServiceImpl implements BookService{
	private BookDao bookDao = new BookDaoImpl();

	@Override
	public int add(Book book,Seller seller,String time) {
		int recordNumber = 0;
		try {
			recordNumber =bookDao.addBook(book,seller,time);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recordNumber;
	}



	@Override
	public List<Book> getAllBook() {
		List<Book> list = null;
		try {
			list = bookDao.getAllBook();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Book> getBookByType(String booktype) {
		List<Book> list = null;
		try {
			list = bookDao.getBookByType(booktype);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public Book getBookById(String bookId) {
		Book book=null;
		try {
			book=bookDao.getBookById(bookId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
		
	}

	@Override
	public List<Book> getBookByName(String name) {
		List<Book> list = null;
		try {
			list = bookDao.getBookByName(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int delete(Book book, Seller seller, String time) {
		int recordNumber = 0;
		try {
				recordNumber =bookDao.deleteBook(book,seller,time);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return recordNumber;
	}



	@Override
	public int update(Book book, Seller seller, String time) {
		int recordNumber = 0;
		if(book!=null) {
			try {
				recordNumber =bookDao.updateBook(book,seller,time);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return recordNumber;
	}

}
