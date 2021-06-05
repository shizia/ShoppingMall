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

public class UpdateProductServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		Seller seller=(Seller) request.getSession().getAttribute("user");
		//
		Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
		//
		System.out.println("here:"+action);
		
		String productarea=(String) request.getSession().getAttribute("productarea");
		String producttype=(String) request.getSession().getAttribute("producttype");
		
		if("updateproduct".equals(action)) {
			
			//如果是书
			if(productarea.equals("书籍")) {
				String bookid = request.getParameter("productid");
				BookService bookService = new BookServiceImpl();
				Book utbook=null; 
				utbook=bookService.getBookById(bookid);
				request.setAttribute("updatebook", utbook);
				request.getRequestDispatcher("pages/seller/updatebook.jsp").forward(request, response);
				
			}
			
			//如果是食品
			else if(productarea.equals("食品")) {
				String id = request.getParameter("productid");
				FoodService foodService = new FoodServiceImpl();
				Food utfood=null; 
				utfood=foodService.getFoodById(id);
				request.setAttribute("updatefood", utfood);
				request.getRequestDispatcher("pages/seller/updatefood.jsp").forward(request, response);
				}
			
			//如果是数码
			else if(productarea.equals("数码")) {
				String id = request.getParameter("productid");
				DigitalService digitalService = new DigitalServiceImpl();
				Digital utdigital=null; 
				utdigital=digitalService.getDigitalById(id);
				request.setAttribute("updatedigital", utdigital);
				request.getRequestDispatcher("pages/seller/updatedigital.jsp").forward(request, response);
				}
			
		}
		
		if("deleteproduct".equals(action)) {
			
			//如果是书
			if(productarea.equals("书籍")) {
				String bookid = request.getParameter("productid");
				BookService bookService = new BookServiceImpl();
				Book book=bookService.getBookById(bookid);
				bookService.delete(book,seller,dateNowStr);
				//request.getRequestDispatcher("pages/seller/mysellbook.jsp").forward(request, response);
				response.getWriter().write("<script > alert(\"下架商品成功!\");location.href='SellServlet?action=showmysell';</script>");
				
			}
			
			//如果是其他
			else if(productarea.equals("食品")) {
				String id = request.getParameter("productid");
				FoodService foodService = new FoodServiceImpl();
				Food food=foodService.getFoodById(id);
				foodService.delete(food, seller, dateNowStr);
				//request.getRequestDispatcher("pages/seller/mysellfood.jsp").forward(request, response);
				response.getWriter().write("<script > alert(\"下架商品成功!\");location.href='SellServlet?action=showmysell';</script>");
			}
			
			//如果是其他
			else if(productarea.equals("数码")) {
				String id = request.getParameter("productid");
				DigitalService digitalService = new DigitalServiceImpl();
				Digital digital=digitalService.getDigitalById(id);
				digitalService.delete(digital, seller, dateNowStr);
				//request.getRequestDispatcher("pages/seller/myselldigital.jsp").forward(request, response);
				response.getWriter().write("<script > alert(\"下架商品成功!\");location.href='SellServlet?action=showmysell';</script>");
			}
			
			
		}
		
		
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
		Book book=null; 
		book=bookService.getBookById(request.getParameter("productid"));
		System.out.println("ohohohohohoh"+request.getParameter("productid"));
		System.out.println("aaaaaaa"+book);
		
		String bookname=request.getParameter("name");
		System.out.println("aaaaaaa"+bookname);
		if(bookname!="") {
			book.setBookname(bookname);
			System.out.println("nnnnnn"+book.getBookname());
		}
		
		String bookauthor=request.getParameter("author");
		if(bookauthor!="") {
			book.setBookauthor(bookauthor);
		}
		
		String booktype=request.getParameter("type");
		if(booktype!="") {
			book.setBooktype(booktype);
		}
		
		String bookdescribe=request.getParameter("describe");
		if(bookdescribe!="") {
			book.setBookdescribe(bookdescribe);
		}
		
		 String bookleftnum=request.getParameter("leftnum");
		 if(bookleftnum!="") {
			 Integer leftnum=Integer.parseInt(bookleftnum);
			 book.setBooktotalnum(book.getBooksoldnum()+leftnum); 
		}
		 
		 String bookprice=request.getParameter("price");
		 if(bookprice!="") {
			 double price=Double.parseDouble(bookprice);
			 book.setBookprice(price); 
		}
		bookService.update(book,seller,dateNowStr);
		response.getWriter().write("<script > alert(\"修改成功!\");location.href='SellServlet?action=showmysell';</script>");
		
		/*
		 * request.getRequestDispatcher("pages/seller/mysellbook.jsp").forward(request,
		 * response);
		 */
		}	
		
		else if(productarea.equals("数码")) {
			
			DigitalService digitalService = new DigitalServiceImpl();
			Digital digital=null; 
			digital=digitalService.getDigitalById(request.getParameter("productid"));
			System.out.println("ohohohohohoh"+request.getParameter("productid"));
			
			String name=request.getParameter("name");
			System.out.println("aaaaaaa"+name);
			if(name!="") {
				digital.setDigitalname(name);
				System.out.println("nnnnnn"+digital.getDigitalname());
			}
			
			String type=request.getParameter("type");
			if(type!="") {
				digital.setDigitaltype(type);
			}
			
			String describe=request.getParameter("describe");
			if(describe!="") {
				digital.setDigitaldescribe(describe);
			}
			
			 String leftnum=request.getParameter("leftnum");
			 if(leftnum!="") {
				 Integer leftnum2=Integer.parseInt(leftnum);
				 digital.setDigitaltotalnum(digital.getDigitalsoldnum()+leftnum2); 
			}
			 
			 String price=request.getParameter("price");
			 if(price!="") {
				 double price2=Double.parseDouble(price);
				 digital.setDigitalprice(price2); 
			}
			 digitalService.update(digital,seller,dateNowStr);
			response.getWriter().write("<script > alert(\"修改成功!\");location.href='SellServlet?action=showmysell';</script>");

			}
		
		else if(productarea.equals("食品")) {
			
			FoodService foodService = new FoodServiceImpl();
			Food food=null; 
			food=foodService.getFoodById(request.getParameter("productid"));
			System.out.println("ohohohohohoh"+request.getParameter("productid"));
			
			String name=request.getParameter("name");
			System.out.println("aaaaaaa"+name);
			if(name!="") {
				food.setFoodname(name);
				System.out.println("nnnnnn"+food.getFoodname());
			}
			
			String type=request.getParameter("type");
			if(type!="") {
				food.setFoodtype(type);
			}
			
			String describe=request.getParameter("describe");
			if(describe!="") {
				food.setFooddescribe(describe);
			}
			
			 String leftnum=request.getParameter("leftnum");
			 if(leftnum!="") {
				 Integer leftnum2=Integer.parseInt(leftnum);
				 food.setFoodtotalnum(food.getFoodsoldnum()+leftnum2); 
			}
			 
			 String price=request.getParameter("price");
			 if(price!="") {
				 double price2=Double.parseDouble(price);
				 food.setFoodprice(price2); 
			}
			 foodService.update(food,seller,dateNowStr);
			response.getWriter().write("<script > alert(\"修改成功!\");location.href='SellServlet?action=showmysell';</script>");

			}
		
		
		
	}
}
