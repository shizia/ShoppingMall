package com.cn.service;

import java.sql.SQLException;
import java.util.List;

import com.cn.subject.ProductVisit;
import com.cn.subject.VisitRecord;

public interface VisitRecordService {
	
	int addVisitRecord(VisitRecord visitrecord);
	
	
	List<VisitRecord> getVisitRecordByUserid(String userid);

	int update(VisitRecord visitrecord, String exittime);
	
	List<ProductVisit> getVisitRecordByProduct(String productarea,String producttype) throws SQLException;

}
