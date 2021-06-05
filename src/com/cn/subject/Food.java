package com.cn.subject;

public class Food {
	private String foodid;
	private String foodname;
	private String foodtype;
	private String fooddescribe;
	private double foodprice;
	private String foodpic;
	private int foodtotalnum;
	private int foodsoldnum;
	
	public Food(String foodid,String foodname,String foodtype,String fooddescribe,double foodprice,String foodpic,int foodtotalnum,int foodsoldnum) {
		this.foodid=foodid;
		this.foodname=foodname;
		this.foodtype=foodtype;
		this.fooddescribe=fooddescribe;
		this.foodprice=foodprice;
		this.foodpic=foodpic;
		this.foodtotalnum=foodtotalnum;
		this.foodsoldnum=foodsoldnum;
	}

	public Food() {
		// TODO Auto-generated constructor stub
	}


	public String getFoodid() {
		return foodid;
	}

	public void setFoodid(String foodid) {
		this.foodid = foodid;
	}

	public String getFoodname() {
		return foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}

	public String getFoodtype() {
		return foodtype;
	}

	public void setFoodtype(String foodtype) {
		this.foodtype = foodtype;
	}

	public String getFooddescribe() {
		return fooddescribe;
	}

	public void setFooddescribe(String fooddescribe) {
		this.fooddescribe = fooddescribe;
	}

	public double getFoodprice() {
		return foodprice;
	}

	public void setFoodprice(double foodprice) {
		this.foodprice = foodprice;
	}

	public String getFoodpic() {
		return foodpic;
	}

	public void setFoodpic(String foodpic) {
		this.foodpic = foodpic;
	}

	public int getFoodtotalnum() {
		return foodtotalnum;
	}

	public void setFoodtotalnum(int foodtotalnum) {
		this.foodtotalnum = foodtotalnum;
	}

	public int getFoodsoldnum() {
		return foodsoldnum;
	}

	public void setFoodsoldnum(int foodsoldnum) {
		this.foodsoldnum = foodsoldnum;
	}

}
