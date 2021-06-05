package com.cn.subject;
import java.text.SimpleDateFormat;
import java.util.Date;



public class BuyRecord {
	private String useridentity;
	private String productarea;
	private String producttype;
	private String productid;
	private String productname;
	private double productprice;
	private int productnum;
	private String date;
	private String selleridentity;
	
	public BuyRecord(String useridentity, String productarea, String producttype, String productid,String productname, double productprice,
			int productnum, String date,String selleridentity) {
		this.useridentity = useridentity;
		this.productarea = productarea;
		this.producttype = producttype;
		this.productid = productid;
		this.setProductname(productname);
		this.productprice = productprice;
		this.productnum = productnum;
		this.setSelleridentity(selleridentity);
		this.setDate(date);
	}
	public BuyRecord(String useridentity, String productarea, String producttype, String productid,String productname, double productprice,
			int productnum, String date) {
		this.useridentity = useridentity;
		this.productarea = productarea;
		this.producttype = producttype;
		this.productid = productid;
		this.setProductname(productname);
		this.productprice = productprice;
		this.productnum = productnum;
		this.setDate(date);
	}
	
	public String getUseridentity() {
		return useridentity;
	}
	public void setUseridentity(String useridentity) {
		this.useridentity = useridentity;
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
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public double getProductprice() {
		return productprice;
	}
	public void setProductprice(double productprice) {
		this.productprice = productprice;
	}
	public int getProductnum() {
		return productnum;
	}
	public void setProductnum(int productnum) {
		this.productnum = productnum;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getSelleridentity() {
		return selleridentity;
	}

	public void setSelleridentity(String selleridentity) {
		this.selleridentity = selleridentity;
	}

	

}
