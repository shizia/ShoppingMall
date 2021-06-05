package com.cn.subject;



public class CartItem {
    private String id;  //购物车商品ID
    private int remain;//该商品的剩余量
    private String productarea;
    private String producttype;
    private String name; //购物车商品名称
    private Integer count; //购物车商品数量
    private double price;  //购物车商品单价
    private double totalPrice;  //购物车商品总价

    public CartItem() {
    }

    public CartItem(String id,int remain,String productarea,String producttype, String name, Integer count, double price, double totalPrice) {
        this.id = id;
        this.remain=remain;
        this.productarea=productarea;
        this.producttype=producttype;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }

	public int getRemain() {
		return remain;
	}

	public void setRemain(int remain) {
		this.remain = remain;
	}

	public String getProductarea() {
		return productarea;
	}

	public void setProductarea(String productarea) {
		this.productarea = productarea;
	}

	public String getProducttype() {
		return producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}
}
