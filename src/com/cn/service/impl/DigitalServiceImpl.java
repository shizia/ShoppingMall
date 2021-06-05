package com.cn.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.cn.dao.DigitalDao;
import com.cn.dao.impl.DigitalDaoImpl;
import com.cn.service.DigitalService;
import com.cn.subject.Digital;
import com.cn.subject.Seller;

public class DigitalServiceImpl implements DigitalService{
	private DigitalDao digitalDao = new DigitalDaoImpl();

	@Override
	public int add(Digital digital,Seller seller,String time) {
		int recordNumber = 0;
		try {
			recordNumber =digitalDao.addDigital(digital,seller,time);
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
	public int update(Digital digital, Seller seller, String time) {
		int recordNumber = 0;
		if(digital!=null) {
			try {
				recordNumber =digitalDao.updateDigital(digital,seller,time);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return recordNumber;
	}

	@Override
	public List<Digital> getAllDigital() {
		List<Digital> list = null;
		try {
			list = digitalDao.getAllDigital();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Digital> getDigitalByType(String digitaltype) {
		List<Digital> list = null;
		try {
			list = digitalDao.getDigitalByType(digitaltype);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public Digital getDigitalById(String digitalId) {
		Digital digital=null;
		try {
			digital=digitalDao.getDigitalById(digitalId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return digital;
		
	}

	@Override
	public List<Digital> getDigitalByName(String name) {
		List<Digital> list = null;
		try {
			list = digitalDao.getDigitalByName(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public int delete(Digital digital, Seller seller, String time) {
		int recordNumber = 0;
		try {
				recordNumber =digitalDao.deleteDigital(digital,seller,time);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return recordNumber;
	}

}
