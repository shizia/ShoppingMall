package com.cn.subject;

import java.math.BigDecimal;

/**
 * 类描述：订单项的javaBean
 *
 * @author
 * @create
 */
public class OrderItem {
    private Integer id; //主键编号
    private String name; //商品名称
    private Integer count; //数量
    private double price; //单价
    private double totalPrice; //总价
    private String orderId; //订单号


    public OrderItem() {
    }

    public OrderItem(Integer id, String name, Integer count, double price,double totalPrice, String orderId) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
    }

    public OrderItem(String name, Integer count, double price,double totalPrice) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
