package com.cn.service.impl;

import com.cn.dao.BookDao;
import com.cn.dao.BuyRecordDao;
import com.cn.dao.DigitalDao;
import com.cn.dao.FoodDao;
import com.cn.dao.OrderDao;
import com.cn.dao.OrderItemDao;
import com.cn.dao.impl.BookDaoImpl;
import com.cn.dao.impl.BuyRecordDaoImpl;
import com.cn.dao.impl.DigitalDaoImpl;
import com.cn.dao.impl.FoodDaoImpl;
import com.cn.dao.impl.OrderDaoImpl;
import com.cn.dao.impl.OrderItemImpl;
import com.cn.subject.*;
import com.cn.service.OrderService;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 类描述：订单模块的 Service接口实现类
 *
 * @author
 * @create
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemImpl();
    private BuyRecordDao buyrecordDao=new BuyRecordDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    private DigitalDao digitalDao = new DigitalDaoImpl();
    private FoodDao foodDao = new FoodDaoImpl();

    @Override
    public String createOrder(Cart cart, String userIdentity) {
        /*订单表order操作 */
        // 订单号===唯一性
        String orderId = System.currentTimeMillis() + "" + userIdentity;
        //创建一个订单对象
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userIdentity);
        // 保存订单
        try {
			try {
				orderDao.saveOrder(order);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

        // 遍历购物车中每一个商品项转换成为订单项、购买记录项保存到数据库
        for (Entry<String, CartItem> entry : cart.getItems().entrySet()) 
        {
            // 获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();

            // 转换为每一个订单项
            OrderItem orderItem = null;
			try {
				orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(),
				        cartItem.getTotalPrice(), orderId);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//转化为每个buyrecord项
			BuyRecord buyrecord=null;
			Date day = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String dateNowStr = sdf.format(day);
			buyrecord=new BuyRecord(userIdentity,cartItem.getProductarea(),cartItem.getProducttype(),cartItem.getId(),cartItem.getName(), cartItem.getPrice(), cartItem.getCount(),dateNowStr);
			try {
				buyrecordDao.addBuyRecord(buyrecord);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

            // 保存订单项到数据库
            try {
				orderItemDao.saveOrderItem(orderItem);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

            // 更新库存和销量
            
            //如果是书
            if(cartItem.getId().substring(0, 4).equals("book")) {
            //获取订单的ID，返回Book对象
            Book book=null;
			try {
				book = bookDao.getBookById(cartItem.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //设置book都对象的销量，计算方式为：获取当前book对象的销量 + 购物车中该商品的数量
            book.setBooksoldnum(book.getBooksoldnum() + cartItem.getCount());
       
            try {
				bookDao.updateBook_norecord(book);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            }
            
            //如果是数码产品
            else if(cartItem.getId().substring(0, 7).equals("digital")) {
            //获取订单的ID，
            Digital digital=null;
			try {
				digital = digitalDao.getDigitalById(cartItem.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //设置销量，计算方式为：获取当前销量 + 购物车中该商品的数量
			digital.setDigitalsoldnum(digital.getDigitalsoldnum() + cartItem.getCount());
       
            try {
            	digitalDao.updateDigital_norecord(digital);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            }
            
            
          //如果是食品
            else if(cartItem.getId().substring(0, 4).equals("food")) {
            //获取订单的ID，
            Food food=null;
			try {
				food =foodDao.getFoodById(cartItem.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //设置销量，计算方式为：获取当前销量 + 购物车中该商品的数量
			food.setFoodsoldnum(food.getFoodsoldnum() + cartItem.getCount());
       
            try {
                    foodDao.updateFood_norecord(food);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            }
            
            
        }
        //清空购物车
        cart.clear();

        return orderId;
    }

    /**
     * @MethodName: 获取所有订单数据
     * @param: []
     * @Return: java.util.List<com.learn.pojo.Order>
    **/
	/* @Override */
	/*
	 * public List<Order> showAllOrders() { return orderDao.queryOrders(); }
	 */

    /**
     * @MethodName: 修改订单状态
     * @param: [orderId]
     * @Return: int
    **/
	/*
	 * @Override public int sendOrder(String orderId) { //传入订单编号和发货状态，数据库中发货状态为1
	 * return orderDao.changeOrderStatus(orderId,1); }
	 */


/**
 * @MethodName: 查找指定用户的所有订单
 * @param: [id]
 * @Return: java.util.List<com.learn.pojo.Order>
**/
/*
 * @Override public List<Order> showUserOrders(Integer id) { return
 * orderDao.queryUserOrders(id); }
 */

    /**
     * @MethodName: 用户确认签收订单
     * @param: [orderId]
     * @Return: void
    **/
	/*
	 * @Override public int receiverOrder(String orderId) { return
	 * orderDao.changeOrderStatus(orderId,2); }
	 */


    /**
     * @MethodName: 查询订单详情
     * @param: [orderId]
     * @Return: java.util.List<com.learn.pojo.OrderItem>
    **/
    
    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
    	List<OrderItem> list = null;
        try {
			list=orderItemDao.queryOrderItemsByOrderId(orderId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
    }

	@Override
	public List<Order> queryUserOrders(String useridentity) throws SQLException {
		List<Order> list = null;
        try {
			list=orderDao.queryUserOrders(useridentity);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<OrderItem> showOrderDetatil(String orderid) throws SQLException {
		List<OrderItem>list = null;
        try {
			list=orderItemDao.queryOrderItemsByOrderId(orderid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



}
