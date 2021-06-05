package com.cn.subject;

public class ProductVisit {

	private String productname;	   
	private String productid;	    
	private int count;     //浏览次数
	
	public ProductVisit(String productname, String productid, int count) {
		this.productname = productname;
		this.productid = productid;
		this.count = count;
	}
	
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	

	
}
