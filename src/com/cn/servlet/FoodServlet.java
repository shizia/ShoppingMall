package com.cn.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.subject.BuyRecord;
import com.cn.subject.Food;
import com.cn.service.BuyRecordService;
import com.cn.service.FoodService;
import com.cn.service.impl.BuyRecordServiceImpl;
import com.cn.service.impl.FoodServiceImpl;


public class FoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FoodService foodService = new FoodServiceImpl();
	
	//初始展示全部
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * List<Food> foodList=null;
		 * 
		 * doPost(request, response); foodList = foodService.getAllFood();
		 * request.setAttribute("action", "showall"); if(foodList != null) {
		 * request.setAttribute("foodList", foodList); }else {
		 * System.out.println("没有任何图书信息"); }
		 * request.getRequestDispatcher("pages/user/food.jsp").forward(request,
		 * response);
		 */
		request.setAttribute("productarea", "食品");
		doPost(request,response);
		
	
}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		request.setAttribute("productarea", "食品");
		
		System.out.println("here:"+action);
		
		List<Food> foodList=null;
		List<BuyRecord> buyrecordlist=null;
		BuyRecordService buyrecordService = new BuyRecordServiceImpl();
		
		//按名字找
		if("getFoodByName".equals(action)) {
			String foodname=request.getParameter("getbyfoodname");
			System.out.println(foodname);
			foodList=foodService.getFoodByName(foodname);
			if(foodList != null) {
				request.setAttribute("productList", foodList);
			}else{
				request.setAttribute("getByNameError", "没有相关数码产品信息！");
				System.out.println("没有任何数码产品信息");
			}
			System.out.println("productList"+foodList);
			request.getRequestDispatcher("pages/seller/mysellfood.jsp").forward(request, response);
		}
		else if("getFoodByName_user".equals(action)) {
			String foodname=request.getParameter("getbyfoodname");
			if(foodname!="") {
			System.out.println(foodname);
			foodList=foodService.getFoodByName(foodname);
			if(foodList != null) {
				request.setAttribute("foodList", foodList);
			}else{
				request.setAttribute("getByNameError", "没有相关食品信息！");
				System.out.println("没有任何数食品信息");
			}
			System.out.println("productList"+foodList);
			request.setAttribute("searchname", foodname);
			request.getRequestDispatcher("pages/user/food.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/FoodServlet?action=showall").forward(request, response);
			}
		}
		
		else if("showallsell".equals(action)) {

			try {
				buyrecordlist=buyrecordService.getBuyRecordByProductarea("食品");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("sellfoodlist", buyrecordlist);
			System.out.println("sellfood"+ buyrecordlist);
			request.getRequestDispatcher("pages/admin/food_sell.jsp").forward(request, response);
		}
		else if("showtypesell".equals(action)) {

			String typesell=request.getParameter("typesell");
			System.out.println("typesell"+typesell);
			try {
				buyrecordlist=buyrecordService.getBuyRecordByProducttype("食品", typesell);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("sellfoodlist", buyrecordlist);
			System.out.println("sellfood"+ buyrecordlist);
			request.getRequestDispatcher("pages/admin/food_sell.jsp").forward(request, response);
		}
		
		else {
			//展示全部
		if("showall".equals(action)) {
		foodList = foodService.getAllFood();	
		request.setAttribute("action", "showall");
		request.setAttribute("producttype", "全部");
		}
		else if("showsnack".equals(action)) {
			foodList = foodService.getFoodByType("零食");	
			request.setAttribute("action", "showsnack");
			request.setAttribute("producttype", "零食");
			}
		else if("showfruit".equals(action)) {
			foodList = foodService.getFoodByType("水果");	
			request.setAttribute("action", "showfruit");
			request.setAttribute("producttype", "水果");
			}
		else if("showmilk".equals(action)) {
			foodList = foodService.getFoodByType("乳品");	
			request.setAttribute("action", "showmilk");
			request.setAttribute("producttype", "乳品");
			}
		else if("showdrinks".equals(action)) {
			foodList = foodService.getFoodByType("饮料");	
			request.setAttribute("action", "showdrinks");
			request.setAttribute("producttype", "饮料");
			}
		else if("showcake".equals(action)) {
			foodList = foodService.getFoodByType("糕点");	
			request.setAttribute("action", "showcake");
			request.setAttribute("producttype", "糕点");
			}

		
		
		System.out.println(foodList);
		
		if(foodList != null) {
			request.setAttribute("foodList", foodList);
		}else {
			System.out.println("没有任何数码产品信息");
		}
		request.getRequestDispatcher("pages/user/food.jsp").forward(request, response);
		}
		
	}


}
