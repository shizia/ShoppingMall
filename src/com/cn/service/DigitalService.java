package com.cn.service;

import java.sql.SQLException;
import java.util.List;

import com.cn.subject.Digital;
import com.cn.subject.Seller;

public interface DigitalService {

	int add(Digital digital,Seller seller,String time);

	int delete(Digital digital,Seller seller,String time);
	
	int update(Digital digital,Seller seller,String time);

	List<Digital> getAllDigital();
	
	List<Digital> getDigitalByType(String digitaltype);
	
	Digital getDigitalById(String digitalId);

	List<Digital> getDigitalByName(String name);


}
