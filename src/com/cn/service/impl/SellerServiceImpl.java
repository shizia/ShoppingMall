package com.cn.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.cn.dao.SellerDao;
import com.cn.dao.impl.SellerDaoImpl;
import com.cn.service.SellerService;
import com.cn.subject.Book;
import com.cn.subject.IPLoginOut;
import com.cn.subject.Seller;
import com.cn.subject.SellerAction;


public class SellerServiceImpl implements SellerService{
	SellerDao sellerDao = new SellerDaoImpl();
	//登录
	public Seller login(Seller seller) throws SQLException {
			Seller seller1 = new Seller();
			//seller1是数据库里查出来的
			seller1 = sellerDao.getSellerById(seller.getIdentity());
			System.out.println("seller1:"+seller);
			if(seller1!=null && seller1.getPassword().equals(seller.getPassword()) ) {
				System.out.println("登陆成功！");
				return seller1;
			}
			else {
				System.out.println("账户或密码错误");
				return null;	
			}
		}
	@Override
	public List<Seller> getAllSeller() throws SQLException {
		List<Seller> list = null;
		list = sellerDao.getAllSeller();
		return list;
	}
	
	@Override
	public Seller getSellerById(String sellerid) {
		Seller seller=null;
		try {
			seller=sellerDao.getSellerById(sellerid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seller;
	}
	@Override
	public int update(Seller seller) {
		int recordNumber = 0;
		if(seller!=null) {
			try {
				recordNumber =sellerDao.updateSeller(seller);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return recordNumber;
	}
	@Override
	public int delete(String identity) {
		int recordNumber = 0;
		if(identity!=null) {
			try {
				recordNumber =sellerDao.deleteSeller(identity);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return recordNumber;
	}
	@Override
	public int add(Seller seller) throws Exception {
		int recordNumber = 0;
		if(seller!=null) {
			try {
				recordNumber =sellerDao.addSeller(seller);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return recordNumber;
	}
	@Override
	public int insertIpTime(Seller seller, String ip, String dateNowStr) throws SQLException {
		int recordNumber = 0;
		if(seller!=null) {
			try {
				recordNumber =sellerDao.insertIpTime(seller, ip, dateNowStr);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return recordNumber;
	}
	@Override
	public int updateIpTime(String identity, String time) throws SQLException {
		int recordNumber = 0;
		if(identity!="") {
			try {
				recordNumber =sellerDao.updateIpTime(identity, time);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return recordNumber;
	}
	@Override
	public List<IPLoginOut> getSellerIP(String identity) throws SQLException {
		List<IPLoginOut> list = null;
		list = sellerDao.getSellerIP(identity);
		return list;
	}
	@Override
	public List<Seller> getSellerByName(String name) throws SQLException {
		List<Seller> list = null;
		list = sellerDao.getSellerByName(name);
		return list;
	}
	@Override
	public List<IPLoginOut> getSellerIPByTime(String starttime, String endtime) throws SQLException {
		List<IPLoginOut> list = null;
		list = sellerDao.getSellerIPByTime(starttime, endtime);
		return list;
	}
	@Override
	public List<SellerAction> getSellerAction(String identity) throws SQLException {
		List<SellerAction> list = null;
		list = sellerDao.getSellerAction(identity);
		return list;
	}
	@Override
	public List<SellerAction> getSellerActionByTime(String starttime, String endtime) throws SQLException {
		List<SellerAction> list = null;
		list = sellerDao.getSellerActionByTime(starttime, endtime);
		return list;
	}

	
}
