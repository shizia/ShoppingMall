package com.cn.dao;

import java.sql.SQLException;
import java.util.List;

import com.cn.subject.BuyRecord;
import com.cn.subject.ProductVisit;
import com.cn.subject.VisitRecord;

public interface VisitRecordDao {
	int addVisitRecord(VisitRecord visitrecord) throws SQLException, Exception;
	
	List<VisitRecord> getVisitRecordByUserid(String userid) throws SQLException;

	int updateVisitRecord(VisitRecord visitrecord, String time) throws SQLException, Exception;
	
	//VisitRecord getVisitRecordById(VisitRecord visitrecord)  throws SQLException;

	VisitRecord getVisitRecordById(String productid) throws SQLException;
	
	List<ProductVisit> getVisitRecordByProduct(String productarea,String producttype) throws SQLException;

}
