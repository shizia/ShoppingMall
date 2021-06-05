package com.cn.service;

import java.sql.SQLException;

import com.cn.subject.Admin;

public interface AdminService {
	
	//注册
	//public int regist(User user) throws SQLException, Exception;
	//登录
	public Admin login(Admin admin) throws SQLException;
	
		
	//public List<Admin> getAllAdmins();
	
	//public List<Seller> getAllSellers();
	
	//public void updateAdmin(Admin admin);
	
	//public void updateSeller(Seller seller);

	//public Seller getSellerByIdentity(String identity);
	
	//public void deleteSeller(String identity);
	
	//public List<Seller> getSellerByStation(String station);
}
