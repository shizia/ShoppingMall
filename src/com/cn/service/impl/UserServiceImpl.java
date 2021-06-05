package com.cn.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.cn.dao.UserDao;
import com.cn.dao.impl.UserDaoImpl;
import com.cn.service.UserService;
import com.cn.subject.Book;
import com.cn.subject.Product;
import com.cn.subject.User;

public class UserServiceImpl implements UserService{
	UserDao userDao = new UserDaoImpl();

	//注册
	public int regist(User user) throws SQLException, Exception {
		if(userDao.getUserById(user.getIdentity())!=null) {
			System.out.println("用户已注册");
			return 1;
		}
		else {
			if(user.getIdentity().length()!=18) {
				System.out.println("身份证位数有误，请重新输入");
				return 2;

			}
			else 
				if(user.getPassword().length()<6||user.getPassword().length()>16) {
					System.out.println("密码位数有误，请重新输入");
					return 3;

				}
			else {
			user = new User(user.getIdentity(),user.getName(),user.getPassword(),user.getEmail());
			userDao.addUser(user);
			System.out.println("注册成功");
			return 4;

			}
		}
		
	}

	//登录
	public User login(User user) throws SQLException {
		User user1 = new User();
		//user1是数据库里查出来的
		user1 = userDao.getUserById(user.getIdentity());
		System.out.println("user1:"+user1);
		if(user1!=null && user1.getPassword().equals(user.getPassword()) ) {
			System.out.println("登陆成功！");
			return user1;
		}
		else {
			System.out.println("账户或密码错误");
			return null;
			
		}

	}

	@Override
	public int insertIpTime(User user,String ip, String time) throws SQLException {
		int recordNumber = 0;
		if(user!=null) {
			try {
				recordNumber =userDao.insertIpTime(user, ip, time);
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
				recordNumber =userDao.updateIpTime(identity, time);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return recordNumber;
	}

	@Override
	public List<Product> getRecommendByUserid(String useridentity) throws SQLException {
		List<Product> list = null;
		try {
			list = userDao.getRecommendByUserid(useridentity);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}


}
