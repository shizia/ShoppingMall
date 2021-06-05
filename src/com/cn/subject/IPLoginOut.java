package com.cn.subject;

public class IPLoginOut {
	private String identity;
	private String name;
	private String ip;
	private String logintime;
	private String logouttime;
	private int ifonline;


	public IPLoginOut() {
		// TODO Auto-generated constructor stub
	}


	public IPLoginOut(String identity, String name, String ip, String logintime, String logouttime, int ifonline) {
		this.identity = identity;
		this.name = name;
		this.ip = ip;
		this.logintime = logintime;
		this.logouttime = logouttime;
		this.ifonline = ifonline;
	}


	public IPLoginOut(String identity, String name, String ip, String logintime, int ifonline) {
		this.identity = identity;
		this.name = name;
		this.ip = ip;
		this.logintime = logintime;
		this.ifonline = ifonline;
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


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getLogintime() {
		return logintime;
	}


	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}


	public String getLogouttime() {
		return logouttime;
	}


	public void setLogouttime(String logouttime) {
		this.logouttime = logouttime;
	}


	public int getIfonline() {
		return ifonline;
	}


	public void setIfonline(int ifonline) {
		this.ifonline = ifonline;
	}
	
}
