package com.cn.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.subject.Admin;
import com.cn.subject.Cart;
import com.cn.subject.Seller;
import com.cn.subject.User;
import com.cn.subject.Product;
import com.cn.service.AdminService;
import com.cn.service.SellerService;
import com.cn.service.UserService;
import com.cn.service.impl.AdminServiceImpl;
import com.cn.service.impl.SellerServiceImpl;
import com.cn.service.impl.UserServiceImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;


public class RecommendServlet extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();
	SellerService sellerService=new SellerServiceImpl();
	AdminService adminService=new AdminServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //根据该用户的数据进行推荐
		String useridentity=(String) request.getSession().getAttribute("useridentity");
        List<Product> recommendList = null;
		try {
			recommendList = userService.getRecommendByUserid(useridentity);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.getSession().setAttribute("recommendList", recommendList);
        System.out.println("recommend"+recommendList);
        
        request.getRequestDispatcher("pages/user/index.jsp").forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	
}
