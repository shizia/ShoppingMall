package com.cn.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.cn.dao.FoodDao;
import com.cn.dao.impl.FoodDaoImpl;
import com.cn.service.FoodService;
import com.cn.subject.Food;
import com.cn.subject.Seller;

public class FoodServiceImpl implements FoodService{
	private FoodDao foodDao = new FoodDaoImpl();

	@Override
	public int add(Food food,Seller seller,String time) {
		int recordNumber = 0;
		try {
			recordNumber =foodDao.addFood(food,seller,time);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recordNumber;
	}



	@Override
	public int update(Food food, Seller seller, String time) {
		int recordNumber = 0;
		if(food!=null) {
			try {
				recordNumber =foodDao.updateFood(food,seller,time);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return recordNumber;
	}

	@Override
	public List<Food> getAllFood() {
		List<Food> list = null;
		try {
			list = foodDao.getAllFood();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Food> getFoodByType(String foodtype) {
		List<Food> list = null;
		try {
			list = foodDao.getFoodByType(foodtype);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public Food getFoodById(String foodId) {
		Food food=null;
		try {
			food=foodDao.getFoodById(foodId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return food;
		
	}

	@Override
	public List<Food> getFoodByName(String name) {
		List<Food> list = null;
		try {
			list = foodDao.getFoodByName(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public int delete(Food food, Seller seller, String time) {
		int recordNumber = 0;
		try {
				recordNumber =foodDao.deleteFood(food,seller,time);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return recordNumber;
	}

}
