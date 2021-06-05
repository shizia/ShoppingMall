package com.cn.dao;

import java.sql.SQLException;
import java.util.List;

import com.cn.subject.BuyRecord;

public interface BuyRecordDao {
	int addBuyRecord(BuyRecord buyrecord) throws SQLException, Exception;
	
	List<BuyRecord> getBuyRecordBySellerid(String sellerid) throws SQLException;
	
	List<BuyRecord> getBuyRecordByProducttype(String productarea,String producttype) throws SQLException;
	
	List<BuyRecord> getBuyRecordByProductarea(String productarea) throws SQLException;

	List<BuyRecord> getBuyRecordByTimeSellid(String starttime, String endtime, String selleridentity) throws SQLException;

}
