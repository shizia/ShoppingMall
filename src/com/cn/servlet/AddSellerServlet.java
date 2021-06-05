package com.cn.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.service.SellerService;
import com.cn.service.DigitalService;
import com.cn.service.FoodService;
import com.cn.service.impl.SellerServiceImpl;
import com.cn.service.impl.DigitalServiceImpl;
import com.cn.service.impl.FoodServiceImpl;
import com.cn.subject.Seller;
import com.cn.subject.Digital;
import com.cn.subject.Food;

public class AddSellerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
		}
		
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SellerService sellerService = new SellerServiceImpl();
		String sellerid=request.getParameter("id");
		String sellername=request.getParameter("name");
		String sellerpwd=request.getParameter("pwd");
		String sellerarea=request.getParameter("area");
		String sellertype=request.getParameter("type");
		if(sellerid!=""&&sellername!=""&&sellerpwd!=""&&sellertype!=""&&sellerarea!="") {
			 Seller seller=new Seller(sellerid,sellername,sellerpwd,sellerarea,sellertype);
			try {
				sellerService.add(seller);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().write("<script > alert(\"添加销售员成功!\");location.href='UpdateSellerServlet?action=showallseller';</script>");
		}
		else {
			response.getWriter().write("<script > alert(\"请填写完整销售员信息!\");location.href='pages/admin/addseller.jsp';</script>");
		}
	}
}
