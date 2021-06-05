package com.cn.subject;
import java.text.SimpleDateFormat;
import java.util.Date;



public class VisitRecord {
	private String useridentity;
	private String username;
	private String productarea;
	private String producttype;
	private String productid;
	private String productname;
	private String enterdatetime;
	private String spendtime;
	private int ifonline;
	private double productprice;
	
	public VisitRecord(String useridentity, String username,String productarea, String producttype, String productid,String productname,String enterdatetime,String spendtime,int ifonline,double productprice) {
		this.useridentity = useridentity;
		this.username = username;
		this.productarea = productarea;
		this.producttype = producttype;
		this.productid = productid;
		this.setProductname(productname);
		this.enterdatetime=enterdatetime;
		this.spendtime=spendtime;
		this.setIfonline(ifonline);
		this.productprice=productprice;
	}
	//可能还没有exittime，刚进来
	public VisitRecord(String useridentity,String username, String productarea, String producttype, String productid,String productname,String enterdatetime,int ifonline,double productprice) {
		this.useridentity = useridentity;
		this.username = username;
		this.productarea = productarea;
		this.producttype = producttype;
		this.productid = productid;
		this.setProductname(productname);
		this.enterdatetime=enterdatetime;
		this.setIfonline(ifonline);
		this.productprice=productprice;
	}
	
	//访问的可能是页面，没有productid
	public VisitRecord(String useridentity, String productarea, String producttype, String enterdatetime,String spendtime) {
		this.useridentity = useridentity;
		this.productarea = productarea;
		this.producttype = producttype;
		this.enterdatetime=enterdatetime;
		this.spendtime=spendtime;
	}

	public VisitRecord() {
		// TODO Auto-generated constructor stub
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

	public String getEnterdatetime() {
		return enterdatetime;
	}

	public void setEnterdatetime(String enterdatetime) {
		this.enterdatetime = enterdatetime;
	}

	public String getExitdatetime() {
		return spendtime;
	}

	public void setExitdatetime(String spendtime) {
		this.spendtime = spendtime;
	}
	public int getIfonline() {
		return ifonline;
	}
	public void setIfonline(int ifonline) {
		this.ifonline = ifonline;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getProductprice() {
		return productprice;
	}
	public void setProductprice(double productprice) {
		this.productprice = productprice;
	}

	
	

}
