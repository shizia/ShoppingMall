package com.cn.subject;

public class Admin {

	private String identity;	   //身份证号，唯一	
	private String name;	    
	private String password;

	
	public Admin(String identity, String name, String password) {
		this.identity = identity;
		this.name = name;
		this.password = password;

	}
	
	//登陆用的构造函数
	public Admin(String identity2, String password2) {
		this.identity = identity2;
		this.password = password2;
	}
	//也是登陆用
	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public String getIdentity() {
		return identity;
	}
	public void setId(String identity) {
		this.identity = identity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	
	
}
