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


public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();
	SellerService sellerService=new SellerServiceImpl();
	AdminService adminService=new AdminServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取操作
		String action = request.getParameter("action");
		//判断操作
		if("login".equals(action)) {
			try {
				login(request, response);
				
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("regist".equals(action)){
			try {
				regist(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("logout".equals(action)) {
			logout(request, response);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	//登录
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		//获取登录信息表中的信息
		String identity = request.getParameter("useridentity");
		String password = request.getParameter("userpassword");
		String status = request.getParameter("status");
		
		System.out.println(identity);
		System.out.println(password);
		System.out.println(status);
		
		
		if(status==null) {
			request.setAttribute("loginError", "请选择您的身份！");
            request.getRequestDispatcher("pages/login.jsp").forward(request, response);
		}
		
		//如果是普通用户
		if(status.equals("user")){
		User loginUser = userService.login(new User(identity,password));	
		System.out.println("loginUser:"+loginUser);	
		if(loginUser == null){
            //登录失败
            System.out.println("账号或密码错误，登录失败！");
            request.setAttribute("loginError", "账号或密码错误，登录失败！");
            request.getRequestDispatcher("pages/login.jsp").forward(request, response);
        }
		else{
			//登录成功,创建一个购物车
            //保存用户登录后的信息到Session域中
			Cart cart=new Cart();
			request.getSession().setAttribute("user", loginUser);
            request.getSession().setAttribute("username", loginUser.getName());
            request.getSession().setAttribute("useridentity", loginUser.getIdentity());
            request.getSession().setAttribute("useremail", loginUser.getEmail());
            request.getSession().setAttribute("cart", cart);
            request.getSession().setAttribute("status", "用户");
            
            System.out.println("验证通过，登录成功！");
            
            //记录登陆时间和ip
            InetAddress addr = InetAddress.getLocalHost();   
            String ip=addr.getHostAddress();//获得本机IP  
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateNowStr = sdf.format(d);
            System.out.println("IP: "+ip+" Time: "+dateNowStr);
            userService.insertIpTime(loginUser, ip, dateNowStr);
            
            //根据该用户的数据进行推荐
            List<Product> recommendList=userService.getRecommendByUserid(loginUser.getIdentity());
            request.getSession().setAttribute("recommendList", recommendList);
            System.out.println("recommend"+recommendList);
            
            request.getRequestDispatcher("pages/user/index.jsp").forward(request, response);
        }
		}
		
		//如果是销售员
		else if(status.equals("seller")) {
			Seller loginSeller = sellerService.login(new Seller(identity,password));
			
			System.out.println("loginSeller:"+loginSeller);
			
			if(loginSeller == null){
	            //登录失败
	            System.out.println("账号或密码错误，登录失败！");
	            request.setAttribute("loginError", "账号或密码错误，登录失败！");
	            request.getRequestDispatcher("pages/login.jsp").forward(request, response);
	        }
			else{
	            //保存用户登录后的信息到Session域中
				request.getSession().setAttribute("user", loginSeller);
	            request.getSession().setAttribute("username", loginSeller.getName());
	            request.getSession().setAttribute("useridentity", loginSeller.getIdentity());
	            request.getSession().setAttribute("productarea", loginSeller.getProductarea());
	            request.getSession().setAttribute("producttype", loginSeller.getProducttype());
	            request.getSession().setAttribute("status", "销售员");
	            
	          //记录登陆时间和ip
	            InetAddress addr = InetAddress.getLocalHost();   
	            String ip=addr.getHostAddress();//获得本机IP  
	            Date d = new Date();
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            String dateNowStr = sdf.format(d);
	            System.out.println("IP: "+ip+" Time: "+dateNowStr);
	            sellerService.insertIpTime(loginSeller, ip, dateNowStr);
	            
	            System.out.println("验证通过，登录成功！");
	            response.sendRedirect("pages/seller/index.jsp");
	        }
			
		}
		
		//如果是管理员
		else if(status.equals("admin")) {
			Admin loginAdmin = adminService.login(new Admin(identity,password));
			
			System.out.println("loginAdmin:"+loginAdmin);
			
			if(loginAdmin == null){
	            //登录失败
	            System.out.println("账号或密码错误，登录失败！");
	            request.setAttribute("loginError", "账号或密码错误，登录失败！");
	            request.getRequestDispatcher("pages/login.jsp").forward(request, response);
	        }
			else{
	            //保存用户登录后的信息到Session域中
				request.getSession().setAttribute("user", loginAdmin);
	            request.getSession().setAttribute("username",loginAdmin.getName());
	            request.getSession().setAttribute("status", "管理员");
	            
	            System.out.println("验证通过，登录成功！");
	            response.sendRedirect("pages/admin/index.jsp");
	        }
			
		}
		
		}
	
	
	
	//注册
	protected void regist(HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
		
		//获取注册信息表中的信息
		String identity = request.getParameter("identity");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repwd");
		String email = request.getParameter("email");
		String status = request.getParameter("status");
		
		if(!password.equals(repassword)) {
			request.setAttribute("registError", "两次密码输入不一致！");
			request.getRequestDispatcher("pages/register.jsp").forward(request, response);
		}
		
		else {
			//
		//if(status.equals("user")) {
		
		User user = new User(identity,name, password,email);
		
		int code=userService.regist(user);
		if(code==1) {
			request.setAttribute("registError", "该账号已存在！");
			request.getRequestDispatcher("pages/register.jsp").forward(request, response);
		}
		else if(code==2) {
			request.setAttribute("registError", "身份证位数有误！");
			request.getRequestDispatcher("pages/register.jsp").forward(request, response);
		}
		else if(code==3) {
			request.setAttribute("registError", "密码位数有误！");
			request.getRequestDispatcher("pages/register.jsp").forward(request, response);
		}
		else if(code==4) {
			response.getWriter().write("<script > alert(\"注册成功!\");location.href='pages/index.jsp';</script>");
		}
		//}
		
		//if(status.equals("seller")) {}
		//if(status.equals("admin")) {}
		
		}
	}		
	
	
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String identity = (String) request.getSession().getAttribute("useridentity");
		String status = (String) request.getSession().getAttribute("status");
		//如果是普通用户
				if(status.equals("用户")){
		            
		            //记录退出时间和ip
		            InetAddress addr = InetAddress.getLocalHost();   
		            String ip=addr.getHostAddress();//获得本机IP  
		            Date d = new Date();
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		            String dateNowStr = sdf.format(d);
		            System.out.println("logout   IP: "+ip+" Time: "+dateNowStr);
		            try {
						userService.updateIpTime(identity, dateNowStr);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            
				}
					if(status.equals("销售员")){
		            
		            //记录退出时间和ip
		            InetAddress addr = InetAddress.getLocalHost();   
		            String ip=addr.getHostAddress();//获得本机IP  
		            Date d = new Date();
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		            String dateNowStr = sdf.format(d);
		            System.out.println("logout   IP: "+ip+" Time: "+dateNowStr);
		            try {
						sellerService.updateIpTime(identity, dateNowStr);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            
				}
		
		
		//销毁session
        request.getSession().invalidate();
		/*
		 * response.sendRedirect(request.getContextPath());
		 * response.sendRedirect("pages/index.jsp");
		 */
        request.getRequestDispatcher("pages/index.jsp").forward(request, response);
	}
	

	
}
