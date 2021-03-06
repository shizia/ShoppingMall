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
				request.setAttribute("getByNameError", "?????????????????????");
				System.out.println("??????????????????");
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
			 * //??????????????????????????????
			 * if((VisitRecord)request.getSession().getAttribute("visitrecord")!=null) {
			 * VisitRecord oldvisit=new VisitRecord();
			 * oldvisit=(VisitRecord)request.getSession().getAttribute("visitrecord");
			 * System.out.println("whywhywhynull"+oldvisit);
			 * visitrecordService.update(oldvisit, dateNowStr); }
			 */
		
		
		VisitRecord nowvisit;
		
		//????????????ID
        String id = request.getParameter("productid");
        //??????????????????
        String productarea=request.getParameter("productarea");
        //????????????
        if(productarea.equals("book")) {
        Book book=new Book();
        book=bookService.getBookById(id);
        nowvisit=new VisitRecord(useridentity,username,"??????",book.getBooktype(),book.getBookid(),book.getBookname(),dateNowStr,1,book.getBookprice());
        visitrecordService.addVisitRecord(nowvisit);
        request.setAttribute("visitrecord",  nowvisit);
        request.setAttribute("visitproduct", book);
      //???????????????????????????
        request.getRequestDispatcher("pages/user/bookdetail.jsp").forward(request, response);
        }
        else if(productarea.equals("food")) {
            Food food=new Food();
            food=foodService.getFoodById(id);
            nowvisit=new VisitRecord(useridentity,username,"??????",food.getFoodtype(),food.getFoodid(),food.getFoodname(),dateNowStr,1,food.getFoodprice());
            visitrecordService.addVisitRecord(nowvisit);
            request.setAttribute("visitrecord",  nowvisit);
            request.setAttribute("visitproduct", food);
          //???????????????????????????
            request.getRequestDispatcher("pages/user/fooddetail.jsp").forward(request, response);
            }
        else if(productarea.equals("digital")) {
            Digital digital=new Digital();
            digital=digitalService.getDigitalById(id);
            nowvisit=new VisitRecord(useridentity,username,"??????",digital.getDigitaltype(),digital.getDigitalid(),digital.getDigitalname(),dateNowStr,1,digital.getDigitalprice());
            visitrecordService.addVisitRecord(nowvisit);
            request.setAttribute("visitrecord",  nowvisit); 
            request.setAttribute("visitproduct", digital);
          //???????????????????????????
            request.getRequestDispatcher("pages/user/digitaldetail.jsp").forward(request, response);
            }
        
			/*
			 * //??????Cookie?????? Cookie[] cookies = request.getCookies();
			 * //???Cookie??????????????????????????????cookie,??????????????????history Cookie cookie =
			 * CookieUtils.findCookie(cookies, "history");
			 * 
			 * if(cookie == null) { //??????cookie?????????????????????id??????history??????cookie??? Cookie c = new
			 * Cookie("history",id); c.setPath("/ShoppingMall"); c.setMaxAge(60*60*24*7);
			 * //7??? response.addCookie(c); } else { //??????history??????cookie???????????? String value =
			 * cookie.getValue(); //???value(1-2-3-4)??????????????????"-"????????????[1,2,3,4] String[] ids =
			 * value.split("-"); LinkedList<String> list = new
			 * LinkedList<String>(Arrays.asList(ids));
			 * 
			 * //?????????????????????????????? if(list.contains(id)) { //?????????????????????????????????????????? //list.remove(id);
			 * list.addFirst(id); } else { //????????????????????????????????? if(list.size() >= 100) {
			 * //??????????????????100??????????????????????????????????????????????????????????????? list.removeLast(); list.addFirst(id); } else
			 * { list.addFirst(id); } } //???list?????????????????????"-"???????????????Cookie?????????????????? StringBuffer sb =
			 * new StringBuffer(); for (String string : list) {
			 * sb.append(string).append("-"); } //???????????????"-"??????:(1-2-3-)-->(1-2-3) String
			 * cookieValue = sb.toString().substring(0,sb.length()-1);
			 * System.out.println(cookieValue); //??????????????? Cookie c = new
			 * Cookie("history",cookieValue); c.setPath("/ShoppingMall");
			 * c.setMaxAge(60*60*24*7); response.addCookie(c); }
			 */
        
		}
		
		else if (action.equals("visitproduct")) {
			//????????????ID
	        String id = request.getParameter("productid");
	        //??????????????????
	        String productarea=request.getParameter("productarea");
	        //????????????
	        if(productarea.equals("book")) {
	        Book book=new Book();
	        book=bookService.getBookById(id);
	        request.setAttribute("visitproduct", book);
	      //???????????????????????????
	        request.getRequestDispatcher("pages/user/bookdetail.jsp").forward(request, response);
	        }
	        else if(productarea.equals("food")) {
	            Food food=new Food();
	            food=foodService.getFoodById(id);
	            request.setAttribute("visitproduct", food);
	          //???????????????????????????
	        request.getRequestDispatcher("pages/user/fooddetail.jsp").forward(request, response);
	            }
	        else if(productarea.equals("digital")) {
	            Digital digital=new Digital();
	            digital=digitalService.getDigitalById(id);
	            request.setAttribute("visitproduct", digital);
	          //???????????????????????????
	       request.getRequestDispatcher("pages/user/digitaldetail.jsp").forward(request, response);
	            }
		}
		
}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request,response);
	}

}
