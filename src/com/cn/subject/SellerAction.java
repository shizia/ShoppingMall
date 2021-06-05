package com.cn.subject;
import java.text.SimpleDateFormat;
import java.util.Date;



public class SellerAction {
	private String selleridentity;
	private String sellername;
	private String selleraction;
	private String productarea;
	private String producttype;
	private String productid;
	private String productname;
	private String datetime;
	
	public SellerAction(String selleridentity, String sellername, String selleraction, String productarea,
			String producttype, String productid, String productname, String datetime) {
		this.selleridentity = selleridentity;
		this.sellername = sellername;
		this.selleraction = selleraction;
		this.productarea = productarea;
		this.producttype = producttype;
		this.productid = productid;
		this.productname = productname;
		this.datetime = datetime;
	}

	public String getSelleridentity() {
		return selleridentity;
	}

	public void setSelleridentity(String selleridentity) {
		this.selleridentity = selleridentity;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public String getSelleraction() {
		return selleraction;
	}

	public void setSelleraction(String selleraction) {
		this.selleraction = selleraction;
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

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	

}
