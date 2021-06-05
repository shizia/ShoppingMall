package com.cn.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.service.BookService;
import com.cn.service.BuyRecordService;
import com.cn.service.DigitalService;
import com.cn.service.FoodService;
import com.cn.service.impl.BookServiceImpl;
import com.cn.service.impl.BuyRecordServiceImpl;
import com.cn.service.impl.DigitalServiceImpl;
import com.cn.service.impl.FoodServiceImpl;
import com.cn.subject.Book;
import com.cn.subject.BuyRecord;
import com.cn.subject.Digital;
import com.cn.subject.Food;

public class SellServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
		
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取操作
		String action = request.getParameter("action");
		
		System.out.println("here:"+action);
		
		String productarea=(String) request.getSession().getAttribute("productarea");
		String producttype=(String) request.getSession().getAttribute("producttype");
		String sellerid=(String) request.getSession().getAttribute("useridentity");
		
		if("showmysell".equals(action)) {
			//如果卖的是书
			if(productarea.equals("书籍")) {
				BookService bookService = new BookServiceImpl();
				List<Book> bookList=null;
				bookList = bookService.getBookByType(producttype);
				if(bookList != null) {
					request.setAttribute("productList", bookList);
				}else {
					System.out.println("没有任何图书信息");
				}
				request.getRequestDispatcher("pages/seller/mysellbook.jsp").forward(request, response);
			}
			
			//如果卖的是数码
			if(productarea.equals("数码")) {
				DigitalService digitalService = new DigitalServiceImpl();
				List<Digital> digitalList=null;
				digitalList = digitalService.getDigitalByType(producttype);
				if(digitalList != null) {
					request.setAttribute("productList", digitalList);
				}else {
					System.out.println("没有任何数码产品信息");
				}
				request.getRequestDispatcher("pages/seller/myselldigital.jsp").forward(request, response);
			}
			
			//如果卖的是食品
			if(productarea.equals("食品")) {
				FoodService foodService = new FoodServiceImpl();
				List<Food> foodList=null;
				foodList = foodService.getFoodByType(producttype);
				if(foodList != null) {
					request.setAttribute("productList", foodList);
				}else {
					System.out.println("没有任何食品信息");
				}
				request.getRequestDispatcher("pages/seller/mysellfood.jsp").forward(request, response);
			}
				
			}
		
		if("showhavesellByTime".equals(action)) {
			String starttime=request.getParameter("time1");
			String endtime=request.getParameter("time2");
			if(starttime==""||endtime=="") {
				response.getWriter().write("<script > alert(\"请输入完整日期\");location.href='SellServlet?action=showhavesell';</script>");
			}
			else {
			BuyRecordService buyrecordService = new BuyRecordServiceImpl();
			List<BuyRecord>buyrecordList=null;
			try {
				buyrecordList = buyrecordService.getBuyRecordByTimeSellid(starttime, endtime, sellerid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(buyrecordList != null) {
				request.setAttribute("buyrecordList", buyrecordList);
			}else {
				System.out.println("没有任何出售信息");
			}
			request.getRequestDispatcher("pages/seller/havesell.jsp").forward(request, response);
			}
		}
		
		//管理员看某个销售员卖了什么
		if("sellBySellerid".equals(action)) {
			BuyRecordService buyrecordService = new BuyRecordServiceImpl();
			List<BuyRecord>sellersellList=null;
			String selleridentity=request.getParameter("selleridentity");
			sellersellList = buyrecordService.getBuyRecordBySellerid(selleridentity);
			if(sellersellList != null) {
				request.setAttribute("sellersellList", sellersellList);
				request.getSession().setAttribute("thissellersell", selleridentity);
			}else {
				System.out.println("没有任何出售信息");
			}
			request.getRequestDispatcher("pages/admin/sellersell.jsp").forward(request, response);
		}
		
		//已经卖出的记录
		if("showhavesell".equals(action)) {
			
				BuyRecordService buyrecordService = new BuyRecordServiceImpl();
				List<BuyRecord>buyrecordList=null;
				buyrecordList = buyrecordService.getBuyRecordBySellerid(sellerid);
				if(buyrecordList != null) {
					request.setAttribute("buyrecordList", buyrecordList);
				}else {
					System.out.println("没有任何出售信息");
				}
				//double totalsell=0;
				//request.setAttribute("totalsell", totalsell);
				request.getRequestDispatcher("pages/seller/havesell.jsp").forward(request, response);
			}
			
			//如果
				
			}
		
}
