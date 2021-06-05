package com.cn.dao;

import java.sql.SQLException;
import java.util.List;

import com.cn.subject.IPLoginOut;
import com.cn.subject.Seller;
import com.cn.subject.SellerAction;
import com.cn.subject.User;


public interface SellerDao {
	int addSeller(Seller seller) throws SQLException, Exception;
	
	int deleteSeller(String identity) throws SQLException;
	
	int updateSeller(Seller seller) throws SQLException;

	Seller getSellerById(String identity) throws SQLException;

	List<Seller> getSellerByName(String name) throws SQLException;
	
	List<Seller> getAllSeller() throws SQLException;
	
	List<IPLoginOut> getSellerIP(String identity) throws SQLException;
	
	List<SellerAction> getSellerAction(String identity) throws SQLException;
	
	List<IPLoginOut> getSellerIPByTime(String starttime,String endtime) throws SQLException;
	
	List<SellerAction> getSellerActionByTime(String starttime,String endtime) throws SQLException;
	
	int insertIpTime(Seller seller,String ip,String time) throws SQLException;
	
	int updateIpTime(String identity,String time) throws SQLException;

}
