package com.cn.subject;

public class Product {
	private String area;
	private String type;
	private String id;
	private String name;
	private double price;
	private String pic;	
	
	public Product(String area, String type, String id, String name, double price, String pic) {
		this.area = area;
		this.type = type;
		this.id = id;
		this.name = name;
		this.price = price;
		this.pic = pic;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	

}
