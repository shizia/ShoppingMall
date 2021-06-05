package com.cn.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.cn.dao.BuyRecordDao;
import com.cn.dao.impl.BuyRecordDaoImpl;
import com.cn.service.BuyRecordService;
import com.cn.subject.BuyRecord;

public class BuyRecordServiceImpl implements BuyRecordService{
	private BuyRecordDao buyrecordDao = new BuyRecordDaoImpl();

	@Override
	public int add(BuyRecord buyrecord) {
		int recordNumber = 0;
		try {
			recordNumber =buyrecordDao.addBuyRecord(buyrecord);
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
	public List<BuyRecord> getBuyRecordBySellerid(String sellerid) {
		List<BuyRecord> list = null;
		try {
			list = buyrecordDao.getBuyRecordBySellerid(sellerid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BuyRecord> getBuyRecordByTimeSellid(String starttime, String endtime, String selleridentity)
			throws SQLException {
		List<BuyRecord> list = null;
		try {
			list = buyrecordDao.getBuyRecordByTimeSellid(starttime, endtime, selleridentity);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BuyRecord> getBuyRecordByProducttype(String productarea, String producttype) throws SQLException {
		List<BuyRecord> list = null;
		try {
			list = buyrecordDao.getBuyRecordByProducttype(productarea, producttype);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BuyRecord> getBuyRecordByProductarea(String productarea) throws SQLException {
		List<BuyRecord> list = null;
		try {
			list = buyrecordDao.getBuyRecordByProductarea(productarea);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}


}
