package com.cn.dao;

import java.sql.SQLException;
import java.util.List;

import com.cn.subject.Food;
import com.cn.subject.Seller;

public interface FoodDao {

	int addFood(Food food,Seller seller,String time) throws SQLException, Exception;
	
	//int deleteFood(String foodid) throws SQLException, Exception;
	
	int deleteFood(Food food,Seller seller,String time) throws SQLException, Exception;
	
	int updateFood(Food food,Seller seller,String time) throws SQLException;
	int updateFood_norecord(Food food) throws SQLException;
	
	//查找所有
	List<Food> getAllFood() throws SQLException;
	
	//通过类型找
	List<Food> getFoodByType(String foodtype) throws SQLException;
	
	//通过名字查找
	List<Food> getFoodByName(String foodname) throws SQLException;
	
	//根据id找
	Food getFoodById(String foodid)  throws SQLException;
}
