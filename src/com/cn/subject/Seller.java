package com.cn.subject;

public class Seller {
	private String identity;	   //身份证号，唯一	
	private String name;	    
	private String password;
	private String productarea;
	private String producttype;
	
	public Seller(String identity, String name, String password,String productarea,String producttype) {
		this.setIdentity(identity);
		this.setName(name);
		this.setPassword(password);
		this.setProductarea(productarea);
		this.setProducttype(producttype);
	}
	
	//登陆用的构造函数
	public Seller(String identity2, String password2) {
		this.setIdentity(identity2);
		this.setPassword(password2);
	}
	//也是登陆用
	public Seller() {
		// TODO Auto-generated constructor stub
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
