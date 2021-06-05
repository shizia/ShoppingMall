package com.cn.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.cn.service.BookService;
import com.cn.service.DigitalService;
import com.cn.service.FoodService;
import com.cn.service.impl.BookServiceImpl;
import com.cn.service.impl.DigitalServiceImpl;
import com.cn.service.impl.FoodServiceImpl;
import com.cn.subject.Book;
import com.cn.subject.Cart;
import com.cn.subject.CartItem;
import com.cn.subject.Digital;
import com.cn.subject.Food;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class CartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookServiceImpl();
	private DigitalService digitalService = new DigitalServiceImpl();
	private FoodService foodService = new FoodServiceImpl();
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doPost(request,response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取操作
		
		String username=(String) request.getSession().getAttribute("username");
		if (username==null){
			response.getWriter().write("<script > alert(\"登录后方可查看购物车!\");location='pages/login.jsp';</script>");
			}
		
		else {
		String action = request.getParameter("action");
		System.out.println("here:"+action);
		
		if("add".equals(action)) {
			addItem(request,response);
		}
		else if ("updateCount".equals(action)) {
			updateCount(request,response);
		}
		else if("delete".equals(action)) {
			deleteItem(request,response);
		}
		else if("clearAll".equals(action)) {
			clearItem(request,response);
		}
		}
	}
    
    
    //添加
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取商品编号
        String itemid =request.getParameter("itemid");
        System.out.println("aaa:"+itemid);

        //把商品信息转换成为carItem商品项
        CartItem cartItem=null;
        //如果是书
        if(itemid.substring(0, 4).equals("book")) 
        {
        	Book book = bookService.getBookById(itemid);
        	cartItem = new CartItem(book.getBookid(),book.getBooktotalnum()-book.getBooksoldnum(),"书籍",book.getBooktype(),book.getBookname(), 1, book.getBookprice(), book.getBookprice());
        }
        //如果是数码产品
        if(itemid.substring(0, 7).equals("digital")) 
        {
        	Digital digital = digitalService.getDigitalById(itemid);
        	cartItem = new CartItem( digital.getDigitalid(), digital.getDigitaltotalnum()- digital.getDigitalsoldnum(),"数码", digital.getDigitaltype(), digital.getDigitalname(), 1,  digital.getDigitalprice(),  digital.getDigitalprice());
        }
      //如果是食品
        if(itemid.substring(0, 4).equals("food")) 
        {
        	Food food = foodService.getFoodById(itemid);
        	cartItem = new CartItem(food.getFoodid(), food.getFoodtotalnum()- food.getFoodsoldnum(),"食品",food.getFoodtype(), food.getFoodname(), 1,  food.getFoodprice(), food.getFoodprice());
        }
        //如果是xxx
        //…………………………………………
          
        //调用cart.addItem，添加商品项
        //使用session来进行控制cart唯一
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        //添加商品项
        cart.addItem(cartItem);
        request.getSession().setAttribute("addName", cartItem.getName());
        System.out.println("ohhhhh:"+cartItem.getName());

        //重定向回原来商品所在的地址页面
        System.out.println(request.getHeader("Referer"));
		response.sendRedirect(request.getHeader("Referer"));

        //最后一个添加的商品名称
        request.getSession().setAttribute("lastName", cartItem.getName());
    }
    
    //删除
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取商品编号
        String itemid =request.getParameter("itemid");
        //获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            // 删除购物车商品项
            cart.deleteItem(itemid);
            // 重定向回原来购物车展示页面
            response.sendRedirect(request.getHeader("Referer"));        }
    }
   //清空购物车
    protected void clearItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            //清空购物车
            cart.clear();
            // 重定向回原来购物车展示页面
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
  //修改商品数量
    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数、商品编号、商品数量
    	String itemid =request.getParameter("itemid");
        int count = Integer.parseInt(request.getParameter("count"));
        System.out.println("修改的商品id:"+itemid);
        System.out.println("修改后的数量:"+count);

        //获取cart购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //判断cart对象是否为空
        System.out.println(cart);
        if (cart != null) {
            //修改商品数量。
            cart.updateCount(itemid, count);
            // 重定向回原来购物车展示页面
            response.sendRedirect(request.getHeader("Referer"));
        }
    }



    /**
     * @MethodName: Ajax的方式加入购物车
     **/
	/*
	 * protected void ajaxAddItem(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { //获取请求的参数 商品编号 String bookid
	 * =request.getParameter("bookid"); Book book = bookService.getBookById(bookid);
	 * 
	 * //把图书信息转换成为carItem商品项。 CartItem cartItem = new CartItem(book.getBookid(),
	 * book.getBookname(), 1, book.getBookprice(), book.getBookprice());
	 * 
	 * //调用cart.addItem，添加商品项 //为了只存在一个cart对象，我们使用session来进行控制。 Cart cart = (Cart)
	 * request.getSession().getAttribute("cart"); if (cart == null) { cart = new
	 * Cart(); request.getSession().setAttribute("cart", cart); } //添加商品项
	 * cart.addItem(cartItem); //最后一个添加的商品名称
	 * request.getSession().setAttribute("lastName", cartItem.getName());
	 * 
	 * //去购物车总的商品数量和最后一个添加的商品名称 HashMap<String, Object> resultMap = new
	 * HashMap<>(); //购物车总的商品数量 resultMap.put("totalCount",cart.getTotalCount());
	 * //最后一个添加的商品名称 resultMap.put("lastName",cartItem.getName());
	 * 
	 * Gson gson = new Gson(); //转换为Json字符串 String json = gson.toJson(resultMap);
	 * 
	 * response.getWriter().write(json); }
	 */
}
