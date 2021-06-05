package com.cn.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.cn.subject.Product;
import com.cn.subject.User;

public interface UserDao {

		int addUser(User user) throws SQLException, Exception;
		
		int deleteUser(String identity) throws SQLException;
		
		int insertIpTime(User user,String ip,String time) throws SQLException;
		
		int updateIpTime(String identity,String time) throws SQLException;
		
		int updateUser(User user) throws SQLException;

		User getUserById(String identity) throws SQLException;
		
		List<Product> getRecommendByUserid(String useridentity) throws SQLException;
		
		//昵称可以重复
		//List<User> getUserByName(String name) throws SQLException;

		//List<User> getAllUser() throws SQLException;
		

}
