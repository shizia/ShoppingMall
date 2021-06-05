package com.cn.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.subject.Book;
import com.cn.subject.BuyRecord;
import com.cn.subject.VisitRecord;
import com.cn.service.BookService;
import com.cn.service.BuyRecordService;
import com.cn.service.VisitRecordService;
import com.cn.service.impl.BookServiceImpl;
import com.cn.service.impl.BuyRecordServiceImpl;
import com.cn.service.impl.VisitRecordServiceImpl;


public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BookService bookService = new BookServiceImpl();
	VisitRecordService visitrecordService = new VisitRecordServiceImpl();
	BuyRecordService buyrecordService = new BuyRecordServiceImpl();
	//初始展示全部书
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * List<Book> bookList=null;
		 * 
		 * doPost(request, response); bookList = bookService.getAllBook();
		 * request.setAttribute("action", "showall"); if(bookList != null) {
		 * request.setAttribute("bookList", bookList); }else {
		 * System.out.println("没有任何图书信息"); }
		 * request.getRequestDispatcher("pages/user/book.jsp").forward(request,
		 * response);
		 */
		request.setAttribute("productarea", "书籍");
		doPost(request,response);
		
	
}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//记录时间(秒)
		String spendtime=request.getParameter("visitsecond");
		System.out.println("visitsecond:");
		if(spendtime!=null) {
		int spendsecond=Integer.parseInt(spendtime);
		int hours = (spendsecond/3600)%24;
		int minutes = (spendsecond / 60) % 60;
		int second = spendsecond % 60;
		String formattime=Integer.toString(hours)+":"+Integer.toString(minutes)+":"+Integer.toString(second);
		String userid=(String) request.getSession().getAttribute("useridentity");
		VisitRecord lastvisit=(VisitRecord)request.getSession().getAttribute("visitrecord");
		visitrecordService.update(lastvisit,formattime);
		}
		
		
		String action = request.getParameter("action");
		
		System.out.println("here:"+action);
		request.setAttribute("productarea", "书籍");
		List<Book> bookList=null;
		List<BuyRecord> buyrecordlist=null;
		
		//按名字找书
		if("getBookByName".equals(action)) {
			String bookname=request.getParameter("getbybookname");
			System.out.println(bookname);
			bookList=bookService.getBookByName(bookname);
			if(bookList != null) {
				request.setAttribute("productList", bookList);
			}else{
				request.setAttribute("getByNameError", "没有相关书籍信息！");
				System.out.println("没有任何图书信息");
			}
			System.out.println("productList"+bookList);
			request.getRequestDispatcher("pages/seller/mysellbook.jsp").forward(request, response);
		}
		//按名字找书
		else		if("getBookByName_user".equals(action)) {
					String bookname=request.getParameter("getbybookname");
					if(bookname!="") {
					System.out.println(bookname);
					bookList=bookService.getBookByName(bookname);
					if(bookList != null) {
						request.setAttribute("bookList", bookList);
					}else{
						request.setAttribute("getByNameError", "没有相关书籍信息！");
						System.out.println("没有任何图书信息");
					}
					System.out.println("productList"+bookList);
					request.setAttribute("searchname", bookname);
					request.getRequestDispatcher("pages/user/book.jsp").forward(request, response);
					}
					else {
						request.getRequestDispatcher("/BookServlet?action=showall").forward(request, response);
					}
				}
		
		else if("showallsell".equals(action)) {

			try {
				buyrecordlist=buyrecordService.getBuyRecordByProductarea("书籍");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("sellbooklist", buyrecordlist);
			System.out.println("sellbook"+ buyrecordlist);
			request.getRequestDispatcher("pages/admin/book_sell.jsp").forward(request, response);
		}
		else if("showtypesell".equals(action)) {

			String typesell=request.getParameter("typesell");
			System.out.println("typesell"+typesell);
			try {
				buyrecordlist=buyrecordService.getBuyRecordByProducttype("书籍", typesell);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("sellbooklist", buyrecordlist);
			System.out.println("sellbook"+ buyrecordlist);
			request.getRequestDispatcher("pages/admin/book_sell.jsp").forward(request, response);
		}
		
		else {
			//展示全部
		if("showall".equals(action)) {
		bookList = bookService.getAllBook();	
		request.setAttribute("action", "showall");
		request.setAttribute("producttype", "全部");
		}
		//文学
		else if("showliterature".equals(action)) {
			bookList = bookService.getBookByType("文学");	
			request.setAttribute("action", "showliterature");
			request.setAttribute("producttype", "文学");
			}
		//哲学
		else if("showphilosophy".equals(action)) {
			bookList = bookService.getBookByType("哲学");
			request.setAttribute("action", "showphilosophy");
			request.setAttribute("producttype", "哲学");
			}
		//历史
		else if("showhistory".equals(action)) {
			bookList = bookService.getBookByType("历史");	
			request.setAttribute("action", "showhistory");
			request.setAttribute("producttype", "历史");
			}
		//社科
		else if("showsociety".equals(action)) {
			bookList = bookService.getBookByType("社会科学");	
			request.setAttribute("action", "showsociety");
			request.setAttribute("producttype", "社会科学");
			}
		//计算机
		else if("showcomputer".equals(action)) {
			bookList = bookService.getBookByType("计算机科学");	
			request.setAttribute("action", "showeducation");
			request.setAttribute("producttype", "计算机科学");
		}
		
		
		System.out.println(bookList);
		
		if(bookList != null) {
			request.setAttribute("bookList", bookList);
		}else {
			System.out.println("没有任何图书信息");
		}
		request.getRequestDispatcher("pages/user/book.jsp").forward(request, response);
		}
		
	}


}
