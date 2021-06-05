package com.cn.subject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 类描述：订单的javaBean
 *
 * @author
 * @create
 */
public class Order {
    private String orderId;  //订单号（唯一）
    private Date createTime; //下单时间
    private double price; //金额
    private Integer status=0; //订单状态 0未发货，1已发货，2已签收
    private String userIdentity;   //用户编号

    public Order() {
    }

    public Order(String orderId, Date createTime, double price, Integer status, String userIdentity) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userIdentity = userIdentity;
    }
    public Order(String orderId, Date createTime, double price, String userIdentity) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.userIdentity = userIdentity;
    }

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserIdentity() {
		return userIdentity;
	}

	public void setUserIdentity(String userIdentity) {
		this.userIdentity = userIdentity;
	}
    
    
    
    }
