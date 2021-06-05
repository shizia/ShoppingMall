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
import com.cn.service.SellerService;
import com.cn.service.impl.BookServiceImpl;
import com.cn.service.impl.BuyRecordServiceImpl;
import com.cn.service.impl.SellerServiceImpl;
import com.cn.subject.Book;
import com.cn.subject.BuyRecord;
import com.cn.subject.IPLoginOut;
import com.cn.subject.Seller;
import com.cn.subject.SellerAction;

public class UpdateSellerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		SellerService sellerservice=new SellerServiceImpl();
		
		System.out.println("hereherehere:"+action);

		if("showallseller".equals(action)) {
			List<Seller> sellerList=null;
			try {
				sellerList = sellerservice.getAllSeller();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("action", "showallaeller");
			System.out.println(sellerList);
			
			if(sellerList != null) {
				request.setAttribute("sellerList", sellerList);
			}else {
				System.out.println("没有任何销售员信息");
			}
			request.getRequestDispatcher("pages/admin/sellerinfo.jsp").forward(request, response);
			}
		if("showallseller_enterip".equals(action)) {
			List<Seller> sellerList=null;
			try {
				sellerList = sellerservice.getAllSeller();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("action", "showallaeller");
			System.out.println(sellerList);
			
			if(sellerList != null) {
				request.setAttribute("sellerList", sellerList);
			}else {
				System.out.println("没有任何销售员信息");
			}
			request.getRequestDispatcher("pages/admin/sellerinfo_enterip.jsp").forward(request, response);
			}
		if("showallseller_action".equals(action)) {
			List<Seller> sellerList=null;
			try {
				sellerList = sellerservice.getAllSeller();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("action", "showallaeller");
			System.out.println(sellerList);
			
			if(sellerList != null) {
				request.setAttribute("sellerList", sellerList);
			}else {
				System.out.println("没有任何销售员信息");
			}
			request.getRequestDispatcher("pages/admin/sellerinfo_action.jsp").forward(request, response);
			}
		if("showallseller_sell".equals(action)) {
			List<Seller> sellerList=null;
			try {
				sellerList = sellerservice.getAllSeller();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("action", "showallaeller");
			System.out.println(sellerList);
			
			if(sellerList != null) {
				request.setAttribute("sellerList", sellerList);
			}else {
				System.out.println("没有任何销售员信息");
			}
			request.getRequestDispatcher("pages/admin/sellerinfo_sell.jsp").forward(request, response);
			}
	
		if("updateseller".equals(action)) {
			String sellerid = request.getParameter("sellerid");
			SellerService sellerService = new SellerServiceImpl();
			Seller seller=null;
			seller=sellerService.getSellerById(sellerid);
			request.setAttribute("updateseller", seller);
			request.getRequestDispatcher("pages/admin/updateseller.jsp").forward(request, response);
			
		}
		
		if("deleteseller".equals(action)) {
			String sellerid = request.getParameter("sellerid");
			SellerService sellerService = new SellerServiceImpl();
			sellerService.delete(sellerid);
			request.getRequestDispatcher("UpdateSellerServlet?action=showallseller").forward(request, response);
			
		}
		if("showsellerip".equals(action)) {
			String sellerid = request.getParameter("sellerid");
			SellerService sellerService = new SellerServiceImpl();
			List<IPLoginOut> selleripList=null;
			try {
				selleripList=sellerService.getSellerIP(sellerid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("selleripList", selleripList);
			request.getRequestDispatcher("pages/admin/showsellerip.jsp").forward(request, response);
			
		}
		if("showselleraction".equals(action)) {
			String sellerid = request.getParameter("sellerid");
			SellerService sellerService = new SellerServiceImpl();
			List<SellerAction> selleractionList=null;
			try {
				selleractionList=sellerService.getSellerAction(sellerid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("action!!"+selleractionList);
			request.setAttribute("selleractionList", selleractionList);
			request.getRequestDispatcher("pages/admin/showselleraction.jsp").forward(request, response);
			
		}
		
		
		}
		
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		SellerService sellerservice=new SellerServiceImpl();
		BuyRecordService buyrecordService=new BuyRecordServiceImpl();
		
		if("getSellerByName".equals(action)) {
			String name=request.getParameter("getname");
			List<Seller> sellerList=null;
			try {
				sellerList=sellerservice.getSellerByName(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("sellerList", sellerList);
			System.out.println("xiaowen:"+sellerList);
			request.getRequestDispatcher("pages/admin/sellerinfo.jsp").forward(request, response);
		}
		
		else if("getSellerByName_ip".equals(action)) {
			String name=request.getParameter("getname");
			List<Seller> sellerList=null;
			try {
				sellerList=sellerservice.getSellerByName(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("sellerList", sellerList);
			request.getRequestDispatcher("pages/admin/sellerinfo_enterip.jsp").forward(request, response);
		}
		else if("getSellerByName_action".equals(action)) {
			String name=request.getParameter("getname");
			List<Seller> sellerList=null;
			try {
				sellerList=sellerservice.getSellerByName(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("sellerList", sellerList);
			request.getRequestDispatcher("pages/admin/sellerinfo_action.jsp").forward(request, response);
		}
		else if("getSellerByName_sell".equals(action)) {
			String name=request.getParameter("getname");
			List<Seller> sellerList=null;
			try {
				sellerList=sellerservice.getSellerByName(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("sellerList", sellerList);
			request.getRequestDispatcher("pages/admin/sellerinfo_sell.jsp").forward(request, response);
		}
		else if("getLoginByTime".equals(action)) {
			String starttime=request.getParameter("logintime1");
			String endtime=request.getParameter("logintime2");
			if(starttime==""||endtime=="") {
				response.getWriter().write("<script > alert(\"请输入完整日期\");location.href='UpdateSellerServlet?action=showallseller_enterip';</script>");
			}
			else {
			List<IPLoginOut> selleripList=null;
			try {
				selleripList=sellerservice.getSellerIPByTime(starttime, endtime);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("selleripList", selleripList);
			request.getRequestDispatcher("pages/admin/showsellerip.jsp").forward(request, response);
			}
		}
		else if("getActionByTime".equals(action)) {
			String starttime=request.getParameter("logintime1");
			String endtime=request.getParameter("logintime2");
			if(starttime==""||endtime=="") {
				response.getWriter().write("<script > alert(\"请输入完整日期\");location.href='UpdateSellerServlet?action=showallseller_action';</script>");
			}
			else {
			List<SellerAction> selleractionList=null;
			try {
				selleractionList=sellerservice.getSellerActionByTime(starttime, endtime);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("selleractionList", selleractionList);
			request.getRequestDispatcher("pages/admin/showselleraction.jsp").forward(request, response);
			}
		}
		else if("getSellByTime".equals(action)) {
			String starttime=request.getParameter("logintime1");
			String endtime=request.getParameter("logintime2");
			String selleridentity=request.getParameter("selleridentity");
			System.out.println("abaaaaaaaaa"+starttime);
			System.out.println("abaaaaaaaaa"+selleridentity);
			if(starttime==""||endtime=="") {
				response.getWriter().write("<script > alert(\"请输入完整日期\");location.href='UpdateSellerServlet?action=showallseller_sell';</script>");
			}
			else {
			List<BuyRecord> sellersellList=null;
			try {
				sellersellList=buyrecordService.getBuyRecordByTimeSellid(starttime, endtime, selleridentity);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("sellersellList", sellersellList);
			request.getRequestDispatcher("pages/admin/sellersell.jsp").forward(request, response);
			}
		}
		
		else {
		SellerService sellerService = new SellerServiceImpl();
		Seller seller=null; 
		seller=sellerService.getSellerById(request.getParameter("selleridentity"));
		System.out.println("dddddddddddd"+request.getParameter("selleridentity"));
		
		String name=request.getParameter("name");
		if(name!="") {
			seller.setName(name);
		}
		System.out.println("dddddddddddd"+name);
		
		String password=request.getParameter("password");
		if(password!="") {
			seller.setPassword(password);
		}
		System.out.println("dddddddddddd"+password);
		
		String area=request.getParameter("productarea");
		if(area!="") {
			seller.setProductarea(area);
		}
		
		String type=request.getParameter("producttype");
		if(type!="") {
			seller.setProducttype(type);
		}
		int i=sellerService.update(seller);
		System.out.println(i);
		response.getWriter().write("<script > alert(\"修改成功!\");location.href='UpdateSellerServlet?action=showallseller';</script>");
		
		/*
		 * request.getRequestDispatcher("pages/seller/mysellbook.jsp").forward(request,
		 * response);
		 */
		}		}		
		
	}

