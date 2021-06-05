package com.cn.dao;

import java.sql.SQLException;
import java.util.List;

import com.cn.subject.Digital;
import com.cn.subject.Seller;

public interface DigitalDao {

	int addDigital(Digital digital,Seller seller,String time) throws SQLException, Exception;
	
	//int deleteDigital(String digitalid,Seller seller,String time) throws SQLException, Exception;
	
	int deleteDigital(Digital digital,Seller seller,String time) throws SQLException, Exception;
	
	int updateDigital(Digital digital,Seller seller,String time) throws SQLException;
	
	int updateDigital_norecord(Digital digital) throws SQLException;
	
	//查找所有
	List<Digital> getAllDigital() throws SQLException;
	
	//通过类型找
	List<Digital> getDigitalByType(String digitaltype) throws SQLException;
	
	//通过名字查找
	List<Digital> getDigitalByName(String digitalname) throws SQLException;
	
	//根据id找
	Digital getDigitalById(String digitalid)  throws SQLException;
}
