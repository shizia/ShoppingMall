package com.cn.service;

import java.sql.SQLException;
import java.util.List;

import com.cn.subject.IPLoginOut;
import com.cn.subject.Seller;
import com.cn.subject.SellerAction;
import com.cn.subject.User;

public interface SellerService {
	//登录
	public Seller login(Seller seller) throws SQLException;
	
	//获取所有
	List<Seller> getAllSeller() throws SQLException;
	
	List<IPLoginOut> getSellerIP(String identity) throws SQLException;
	
	List<IPLoginOut> getSellerIPByTime(String starttime,String endtime) throws SQLException;
	
	List<SellerAction> getSellerAction(String identity) throws SQLException;
	
	List<SellerAction> getSellerActionByTime(String starttime,String endtime) throws SQLException;
	
	Seller getSellerById(String sellerid);
	
	List<Seller> getSellerByName(String name) throws SQLException;
	
	int update(Seller seller);
	
	int add(Seller seller) throws Exception;
	
	int delete(String identity);
	
	public int insertIpTime(Seller seller,String ip,String dateNowStr) throws SQLException;
	
	int updateIpTime(String identity,String time) throws SQLException;
}
