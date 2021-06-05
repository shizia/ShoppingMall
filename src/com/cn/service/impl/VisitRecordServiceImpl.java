package com.cn.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.cn.dao.VisitRecordDao;
import com.cn.dao.impl.VisitRecordDaoImpl;
import com.cn.service.VisitRecordService;
import com.cn.subject.ProductVisit;
import com.cn.subject.VisitRecord;

public class VisitRecordServiceImpl implements VisitRecordService{
	private VisitRecordDao visitrecordDao = new VisitRecordDaoImpl();

	@Override
	public int addVisitRecord(VisitRecord visitrecord) {
		int recordNumber = 0;
		try {
			recordNumber =visitrecordDao.addVisitRecord(visitrecord);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recordNumber;
	}

	@Override
	public List<VisitRecord> getVisitRecordByUserid(String userid) {
		List<VisitRecord> list = null;
		try {
			list = visitrecordDao.getVisitRecordByUserid(userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int update(VisitRecord visitrecord,String exittime) {
		int recordNumber = 0;
		try {
			recordNumber =visitrecordDao.updateVisitRecord(visitrecord, exittime);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recordNumber;
	}

	@Override
	public List<ProductVisit> getVisitRecordByProduct(String productarea, String producttype) throws SQLException {
		List<ProductVisit> list = null;
		try {
			list = visitrecordDao.getVisitRecordByProduct(productarea, producttype);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}


}
