package com.cn.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.subject.BuyRecord;
import com.cn.subject.Digital;
import com.cn.service.BuyRecordService;
import com.cn.service.DigitalService;
import com.cn.service.impl.BuyRecordServiceImpl;
import com.cn.service.impl.DigitalServiceImpl;


public class DigitalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DigitalService digitalService = new DigitalServiceImpl();
	
	//初始展示全部
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * List<Digital> digitalList=null;
		 * 
		 * doPost(request, response); digitalList = digitalService.getAllDigital();
		 * request.setAttribute("action", "showall"); if(digitalList != null) {
		 * request.setAttribute("digitalList", digitalList); }else {
		 * System.out.println("没有任何图书信息"); }
		 * request.getRequestDispatcher("pages/user/digital.jsp").forward(request,
		 * response);
		 */
		request.setAttribute("productarea", "数码");
		doPost(request,response);
		
	
}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		request.setAttribute("productarea", "数码");
		
		System.out.println("here:"+action);
		
		List<Digital> digitalList=null;
		List<BuyRecord> buyrecordlist=null;
		BuyRecordService buyrecordService = new BuyRecordServiceImpl();
		
		//按名字找
		if("getDigitalByName".equals(action)) {
			String digitalname=request.getParameter("getbydigitalname");
			System.out.println(digitalname);
			digitalList=digitalService.getDigitalByName(digitalname);
			if(digitalList != null) {
				request.setAttribute("productList", digitalList);
			}else{
				request.setAttribute("getByNameError", "没有相关数码产品信息！");
				System.out.println("没有任何数码产品信息");
			}
			System.out.println("productList"+digitalList);
			request.getRequestDispatcher("pages/seller/myselldigital.jsp").forward(request, response);
		}
		else if("getDigitalByName_user".equals(action)) {
			String digitalname=request.getParameter("getbydigitalname");
			if(digitalname!="") {
			System.out.println(digitalname);
			digitalList=digitalService.getDigitalByName(digitalname);
			if(digitalList != null) {
				request.setAttribute("digitalList", digitalList);
			}else{
				request.setAttribute("getByNameError", "没有相关数码产品信息！");
				System.out.println("没有任何数码产品信息");
			}
			System.out.println("productList"+digitalList);
			request.setAttribute("searchname", digitalname);
			request.getRequestDispatcher("pages/user/digital.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/DigitalServlet?action=showall").forward(request, response);
			}
		}
		
		else if("showallsell".equals(action)) {

			try {
				buyrecordlist=buyrecordService.getBuyRecordByProductarea("数码");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("selldigitallist", buyrecordlist);
			System.out.println("selldigital"+ buyrecordlist);
			request.getRequestDispatcher("pages/admin/digital_sell.jsp").forward(request, response);
		}
		else if("showtypesell".equals(action)) {

			String typesell=request.getParameter("typesell");
			System.out.println("typesell"+typesell);
			try {
				buyrecordlist=buyrecordService.getBuyRecordByProducttype("数码", typesell);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("selldigitallist", buyrecordlist);
			System.out.println("selldigital"+ buyrecordlist);
			request.getRequestDispatcher("pages/admin/digital_sell.jsp").forward(request, response);
		}
		
		else {
			//展示全部
		if("showall".equals(action)) {
		digitalList = digitalService.getAllDigital();	
		request.setAttribute("action", "showall");
		request.setAttribute("producttype", "全部");
		}
		//相机
		else if("showcamera".equals(action)) {
			digitalList = digitalService.getDigitalByType("相机");	
			request.setAttribute("action", "showcamera");
			request.setAttribute("producttype", "相机");
			}
		//手机
		else if("showphone".equals(action)) {
			digitalList = digitalService.getDigitalByType("手机");	
			request.setAttribute("action", "showphone");
			request.setAttribute("producttype", "手机");
			}
		//电脑
		else if("showcomputer".equals(action)) {
			digitalList = digitalService.getDigitalByType("电脑");	
			request.setAttribute("action", "showcomputer");
			request.setAttribute("producttype", "电脑");
			}
		
		
		System.out.println(digitalList);
		
		if(digitalList != null) {
			request.setAttribute("digitalList", digitalList);
		}else {
			System.out.println("没有任何数码产品信息");
		}
		request.getRequestDispatcher("pages/user/digital.jsp").forward(request, response);
		}
		
	}


}
