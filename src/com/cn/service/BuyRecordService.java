package com.cn.service;

import java.sql.SQLException;
import java.util.List;
import com.cn.subject.BuyRecord;

public interface BuyRecordService {
	
	int add(BuyRecord buyrecord);
	
	List<BuyRecord> getBuyRecordBySellerid(String sellerid);
	
	List<BuyRecord> getBuyRecordByTimeSellid(String starttime, String endtime, String selleridentity) throws SQLException;
	
	List<BuyRecord> getBuyRecordByProducttype(String productarea,String producttype) throws SQLException;
	
	List<BuyRecord> getBuyRecordByProductarea(String productarea) throws SQLException;

}
