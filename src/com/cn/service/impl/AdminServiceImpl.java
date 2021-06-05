package com.cn.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.cn.dao.AdminDao;
import com.cn.dao.impl.AdminDaoImpl;
import com.cn.service.AdminService;
import com.cn.subject.Admin;

public class AdminServiceImpl implements AdminService{
	AdminDao adminDao = new AdminDaoImpl();

	//注册
	/*
	 * public int regist(Admin user) throws SQLException, Exception {
	 * if(userDao.getUserById(user.getIdentity())!=null) {
	 * System.out.println("用户已注册"); return 1; } else {
	 * if(user.getIdentity().length()!=18) { System.out.println("身份证位数有误，请重新输入");
	 * return 2;
	 * 
	 * } else { user = new
	 * User(user.getIdentity(),user.getName(),user.getPassword(),user.getEmail());
	 * userDao.addUser(user); System.out.println("注册成功"); return 3;
	 * 
	 * } }
	 * 
	 * }
	 */

	//登录
	public Admin login(Admin user) throws SQLException {
		Admin user1 = new Admin();
		//user1是数据库里查出来的
		user1 = adminDao.getAdminById(user.getIdentity());
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


}
