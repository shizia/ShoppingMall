package com.cn.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.subject.Book;
import com.cn.subject.Digital;
import com.cn.subject.Food;
import com.cn.subject.ProductVisit;
import com.cn.subject.VisitRecord;
import com.cn.service.BookService;
import com.cn.service.DigitalService;
import com.cn.service.FoodService;
import com.cn.service.VisitRecordService;
import com.cn.service.impl.BookServiceImpl;
import com.cn.service.impl.DigitalServiceImpl;
import com.cn.service.impl.FoodServiceImpl;
import com.cn.service.impl.VisitRecordServiceImpl;
import com.cn.util.*;


public class VisitRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BookService bookService = new BookServiceImpl();
	FoodService foodService = new FoodServiceImpl();
	DigitalService digitalService = new DigitalServiceImpl();
	VisitRecordService visitrecordService = new VisitRecordServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
        
		String useridentity = (String) request.getSession().getAttribute("useridentity");
		String username = (String) request.getSession().getAttribute("username");
		String action = request.getParameter("action");
		/*
		 * if (request.getSession().getAttribute("lasttime")==null) {
		 * request.getSession().setAttribute("lasttime", 1L); }
		 */
		
		if(action.equals("myvisit")) {
			List<VisitRecord> visitList=null;
			visitList=visitrecordService.getVisitRecordByUserid(useridentity);
			if(visitList != null) {
				request.setAttribute("visitList", visitList);
			}else{
				request.setAttribute("getByNameError", "没有浏览信息！");
				System.out.println("没有浏览信息");
			}
			request.getRequestDispatcher("pages/user/visitrecord.jsp").forward(request, response);
		}
		else if (action.equals("productvisit")) {
			String area=request.getParameter("sellerarea");
			String type=request.getParameter("sellertype");
			List<ProductVisit> visitList=null;
			try {
				visitList=visitrecordService.getVisitRecordByProduct(area, type);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("visitList",visitList);	
			System.out.println("heyheyhey"+visitList);
			request.getRequestDispatcher("pages/seller/visitrecord.jsp").forward(request, response);
		}
		
		else if (action.equals("visitproduct_record")) {
			/*
			 * //把上一条浏览的更新掉
			 * if((VisitRecord)request.getSession().getAttribute("visitrecord")!=null) {
			 * VisitRecord oldvisit=new VisitRecord();
			 * oldvisit=(VisitRecord)request.getSession().getAttribute("visitrecord");
			 * System.out.println("whywhywhynull"+oldvisit);
			 * visitrecordService.update(oldvisit, dateNowStr); }
			 */
		
		
		VisitRecord nowvisit;
		
		//接收商品ID
        String id = request.getParameter("productid");
        //接受商品区域
        String productarea=request.getParameter("productarea");
        //接收商品
        if(productarea.equals("book")) {
        Book book=new Book();
        book=bookService.getBookById(id);
        nowvisit=new VisitRecord(useridentity,username,"书籍",book.getBooktype(),book.getBookid(),book.getBookname(),dateNowStr,1,book.getBookprice());
        visitrecordService.addVisitRecord(nowvisit);
        request.setAttribute("visitrecord",  nowvisit);
        request.setAttribute("visitproduct", book);
      //转发跳到商品详情页
        request.getRequestDispatcher("pages/user/bookdetail.jsp").forward(request, response);
        }
        else if(productarea.equals("food")) {
            Food food=new Food();
            food=foodService.getFoodById(id);
            nowvisit=new VisitRecord(useridentity,username,"食品",food.getFoodtype(),food.getFoodid(),food.getFoodname(),dateNowStr,1,food.getFoodprice());
            visitrecordService.addVisitRecord(nowvisit);
            request.setAttribute("visitrecord",  nowvisit);
            request.setAttribute("visitproduct", food);
          //转发跳到商品详情页
            request.getRequestDispatcher("pages/user/fooddetail.jsp").forward(request, response);
            }
        else if(productarea.equals("digital")) {
            Digital digital=new Digital();
            digital=digitalService.getDigitalById(id);
            nowvisit=new VisitRecord(useridentity,username,"数码",digital.getDigitaltype(),digital.getDigitalid(),digital.getDigitalname(),dateNowStr,1,digital.getDigitalprice());
            visitrecordService.addVisitRecord(nowvisit);
            request.setAttribute("visitrecord",  nowvisit); 
            request.setAttribute("visitproduct", digital);
          //转发跳到商品详情页
            request.getRequestDispatcher("pages/user/digitaldetail.jsp").forward(request, response);
            }
        
			/*
			 * //获取Cookie信息 Cookie[] cookies = request.getCookies();
			 * //从Cookie数组中查找指定名称的cookie,这边要找的是history Cookie cookie =
			 * CookieUtils.findCookie(cookies, "history");
			 * 
			 * if(cookie == null) { //如果cookie中没有东西则把id加入history这个cookie中 Cookie c = new
			 * Cookie("history",id); c.setPath("/ShoppingMall"); c.setMaxAge(60*60*24*7);
			 * //7天 response.addCookie(c); } else { //获得history这个cookie的所有值 String value =
			 * cookie.getValue(); //将value(1-2-3-4)这个字符串以"-"分割开成[1,2,3,4] String[] ids =
			 * value.split("-"); LinkedList<String> list = new
			 * LinkedList<String>(Arrays.asList(ids));
			 * 
			 * //如果商品在浏览记录里 if(list.contains(id)) { //除去原纪录，并添加到第一个。 //list.remove(id);
			 * list.addFirst(id); } else { //如果商品不在浏览记录里 if(list.size() >= 100) {
			 * //记录大于等于100个时，删掉最后一个记录，再添加一个新的记录 list.removeLast(); list.addFirst(id); } else
			 * { list.addFirst(id); } } //将list元素取出来，用"-"连接保存到Cookie中写回浏览器 StringBuffer sb =
			 * new StringBuffer(); for (String string : list) {
			 * sb.append(string).append("-"); } //将最后一个"-"去掉:(1-2-3-)-->(1-2-3) String
			 * cookieValue = sb.toString().substring(0,sb.length()-1);
			 * System.out.println(cookieValue); //写回浏览器 Cookie c = new
			 * Cookie("history",cookieValue); c.setPath("/ShoppingMall");
			 * c.setMaxAge(60*60*24*7); response.addCookie(c); }
			 */
        
		}
		
		else if (action.equals("visitproduct")) {
			//接收商品ID
	        String id = request.getParameter("productid");
	        //接受商品区域
	        String productarea=request.getParameter("productarea");
	        //接收商品
	        if(productarea.equals("book")) {
	        Book book=new Book();
	        book=bookService.getBookById(id);
	        request.setAttribute("visitproduct", book);
	      //转发跳到商品详情页
	        request.getRequestDispatcher("pages/user/bookdetail.jsp").forward(request, response);
	        }
	        else if(productarea.equals("food")) {
	            Food food=new Food();
	            food=foodService.getFoodById(id);
	            request.setAttribute("visitproduct", food);
	          //转发跳到商品详情页
	        request.getRequestDispatcher("pages/user/fooddetail.jsp").forward(request, response);
	            }
	        else if(productarea.equals("digital")) {
	            Digital digital=new Digital();
	            digital=digitalService.getDigitalById(id);
	            request.setAttribute("visitproduct", digital);
	          //转发跳到商品详情页
	       request.getRequestDispatcher("pages/user/digitaldetail.jsp").forward(request, response);
	            }
		}
		
}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request,response);
	}

}
