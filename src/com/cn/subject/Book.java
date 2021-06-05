package com.cn.subject;

public class Book {
	private String bookid;
	private String bookname;
	private String bookauthor;
	private String booktype;
	private String bookdescribe;
	private double bookprice;
	private String bookpic;
	private int booktotalnum;
	private int booksoldnum;
	
	public Book(String bookid,String bookname,String booktype,String bookauthor,String bookdescribe,double bookprice,String bookpic,int booktotalnum,int booksoldnum) {
		this.bookid=bookid;
		this.bookname=bookname;
		this.booktype=booktype;
		this.bookauthor=bookauthor;
		this.bookdescribe=bookdescribe;
		this.bookprice=bookprice;
		this.bookpic=bookpic;
		this.booktotalnum=booktotalnum;
		this.booksoldnum=booksoldnum;
	}

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public String getBookid() {
		return bookid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getBookauthor() {
		return bookauthor;
	}

	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}

	public String getBookdescribe() {
		return bookdescribe;
	}

	public void setBookdescribe(String bookdescribe) {
		this.bookdescribe = bookdescribe;
	}

	public double getBookprice() {
		return bookprice;
	}

	public void setBookprice(double bookprice) {
		this.bookprice = bookprice;
	}

	public String getBookpic() {
		return bookpic;
	}

	public void setBookpic(String bookpic) {
		this.bookpic = bookpic;
	}

	public int getBooktotalnum() {
		return booktotalnum;
	}

	public void setBooktotalnum(int booktotalnum) {
		this.booktotalnum = booktotalnum;
	}

	public int getBooksoldnum() {
		return booksoldnum;
	}

	public void setBooksoldnum(int booksoldnum) {
		this.booksoldnum = booksoldnum;
	}

	public String getBooktype() {
		return booktype;
	}

	public void setBooktype(String booktype) {
		this.booktype = booktype;
	}
	
	
	
}
