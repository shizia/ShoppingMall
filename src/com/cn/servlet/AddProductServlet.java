package com.cn.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.service.BookService;
import com.cn.service.DigitalService;
import com.cn.service.FoodService;
import com.cn.service.impl.BookServiceImpl;
import com.cn.service.impl.DigitalServiceImpl;
import com.cn.service.impl.FoodServiceImpl;
import com.cn.subject.Book;
import com.cn.subject.Digital;
import com.cn.subject.Food;
import com.cn.subject.Seller;

public class AddProductServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
		}
		
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String productarea=(String) request.getSession().getAttribute("productarea");
		String producttype=(String) request.getSession().getAttribute("producttype");
		Seller seller=(Seller) request.getSession().getAttribute("user");
		//
		Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
		//
		
		if(productarea.equals("书籍")) {
		
		BookService bookService = new BookServiceImpl();
		double price = 0;
		Integer totalnum = null;
		String bookid=request.getParameter("id");
		String bookname=request.getParameter("name");
		String bookauthor=request.getParameter("author");
		String booktype=request.getParameter("type");
		String bookdescribe=request.getParameter("describe");
		String bookpic=request.getParameter("pic");
		String booktotalnum=request.getParameter("totalnum");
		String bookprice=request.getParameter("price");
		if(bookid!=""&&bookname!=""&&bookauthor!=""&&booktype!=""&&bookdescribe!=""&&bookpic!=""&&booktotalnum!=""&&bookprice!="") {
			price=Double.parseDouble(bookprice);
			totalnum=Integer.parseInt(booktotalnum);
			Book book=new Book(bookid,bookname,booktype,bookauthor,bookdescribe,price,bookpic,totalnum,0);
	        bookService.add(book,seller,dateNowStr);
			response.getWriter().write("<script > alert(\"上架商品成功!\");location.href='SellServlet?action=showmysell';</script>");
		}
		else {
			response.getWriter().write("<script > alert(\"请填写完整商品信息!\");location.href='SellServlet?action=showmysell';</script>");
		}
		}
		else if(productarea.equals("数码")) {
			
			DigitalService digitalService = new DigitalServiceImpl();
			double price = 0;
			Integer totalnum = null;
			String id=request.getParameter("id");
			String name=request.getParameter("name");
			String type=request.getParameter("type");
			String describe=request.getParameter("describe");
			String pic=request.getParameter("pic");
			String dtotalnum=request.getParameter("totalnum");
			String dprice=request.getParameter("price");
			if(id!=""&&name!=""&&type!=""&&describe!=""&&pic!=""&&dtotalnum!=""&&dprice!="") {
				 price=Double.parseDouble(dprice);
				 totalnum=Integer.parseInt(dtotalnum);
				 Digital digital=new Digital(id,name,type,describe,price,pic,totalnum,0);
				 digitalService.add(digital,seller,dateNowStr);
				response.getWriter().write("<script > alert(\"上架商品成功!\");location.href='SellServlet?action=showmysell';</script>");
			}
			else {
				response.getWriter().write("<script > alert(\"请填写完整商品信息!\");location.href='SellServlet?action=showmysell';</script>");
			}
		}
		else if(productarea.equals("食品")) {
			
			FoodService foodService = new FoodServiceImpl();
			double price = 0;
			Integer totalnum = null;
			String id=request.getParameter("id");
			String name=request.getParameter("name");
			String type=request.getParameter("type");
			String describe=request.getParameter("describe");
			String pic=request.getParameter("pic");
			String dtotalnum=request.getParameter("totalnum");
			String dprice=request.getParameter("price");
			if(id!=""&&name!=""&&type!=""&&describe!=""&&pic!=""&&dtotalnum!=""&&dprice!="") {
				 price=Double.parseDouble(dprice);
				 totalnum=Integer.parseInt(dtotalnum);
				 Food food=new Food(id,name,type,describe,price,pic,totalnum,0);
				 foodService.add(food,seller,dateNowStr);
				response.getWriter().write("<script > alert(\"上架商品成功!\");location.href='SellServlet?action=showmysell';</script>");
			}
			else {
				response.getWriter().write("<script > alert(\"请填写完整商品信息!\");location.href='SellServlet?action=showmysell';</script>");
			}
		}
	
		}				
		
	
}
