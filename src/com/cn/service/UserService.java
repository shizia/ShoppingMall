package com.cn.service;

import java.sql.SQLException;

import com.cn.subject.Product;
import com.cn.subject.User;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface UserService {
	
	//注册
	public int regist(User user) throws SQLException, Exception;
	//登录
	public User login(User user) throws SQLException;
	
	public int insertIpTime(User user,String ip,String dateNowStr) throws SQLException;
	
	int updateIpTime(String identity,String time) throws SQLException;
	
	List<Product> getRecommendByUserid(String useridentity) throws SQLException;
	
		
	//public List<Admin> getAllAdmins();
	
	//public List<Seller> getAllSellers();
	
	//public void updateAdmin(Admin admin);
	
	//public void updateSeller(Seller seller);

	//public Seller getSellerByIdentity(String identity);
	
	//public void deleteSeller(String identity);
	
	//public List<Seller> getSellerByStation(String station);
}
