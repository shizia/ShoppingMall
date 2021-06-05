package com.cn.subject;

public class Digital {
	private String digitalid;
	private String digitalname;
	private String digitaltype;
	private String digitaldescribe;
	private double digitalprice;
	private String digitalpic;
	private int digitaltotalnum;
	private int digitalsoldnum;
	
	public Digital(String digitalid,String digitalname,String digitaltype,String digitaldescribe,double digitalprice,String digitalpic,int digitaltotalnum,int digitalsoldnum) {
		this.digitalid=digitalid;
		this.digitalname=digitalname;
		this.digitaltype=digitaltype;
		this.digitaldescribe=digitaldescribe;
		this.digitalprice=digitalprice;
		this.digitalpic=digitalpic;
		this.digitaltotalnum=digitaltotalnum;
		this.digitalsoldnum=digitalsoldnum;
	}

	public Digital() {
		// TODO Auto-generated constructor stub
	}

	public String getDigitalid() {
		return digitalid;
	}

	public void setDigitalid(String digitalid) {
		this.digitalid = digitalid;
	}

	public String getDigitalname() {
		return digitalname;
	}

	public void setDigitalname(String digitalname) {
		this.digitalname = digitalname;
	}

	public String getDigitaltype() {
		return digitaltype;
	}

	public void setDigitaltype(String digitaltype) {
		this.digitaltype = digitaltype;
	}

	public String getDigitaldescribe() {
		return digitaldescribe;
	}

	public void setDigitaldescribe(String digitaldescribe) {
		this.digitaldescribe = digitaldescribe;
	}

	public double getDigitalprice() {
		return digitalprice;
	}

	public void setDigitalprice(double digitalprice) {
		this.digitalprice = digitalprice;
	}

	public String getDigitalpic() {
		return digitalpic;
	}

	public void setDigitalpic(String digitalpic) {
		this.digitalpic = digitalpic;
	}

	public int getDigitaltotalnum() {
		return digitaltotalnum;
	}

	public void setDigitaltotalnum(int digitaltotalnum) {
		this.digitaltotalnum = digitaltotalnum;
	}

	public int getDigitalsoldnum() {
		return digitalsoldnum;
	}

	public void setDigitalsoldnum(int digitalsoldnum) {
		this.digitalsoldnum = digitalsoldnum;
	}

}
