package com.cn.service;

import java.sql.SQLException;
import java.util.List;

import com.cn.subject.Food;
import com.cn.subject.Seller;

public interface FoodService {

	int add(Food food,Seller seller,String time);

	int delete(Food food,Seller seller,String time);
	
	int update(Food food,Seller seller,String time);

	List<Food> getAllFood();
	
	List<Food> getFoodByType(String foodtype);
	
	Food getFoodById(String foodId);

	List<Food> getFoodByName(String name);


}
