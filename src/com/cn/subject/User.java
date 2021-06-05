package com.cn.subject;

public class User {

	private String identity;	   //身份证号，唯一	
	private String name;	    
	private String password;
	private String email;
	
	public User(String identity, String name, String password,String email) {
		this.identity = identity;
		this.name = name;
		this.password = password;
		this.email=email;
	}
	
	//登陆用的构造函数
	public User(String identity2, String password2) {
		this.identity = identity2;
		this.password = password2;
	}
	//也是登陆用
	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
