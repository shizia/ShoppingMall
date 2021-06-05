package com.cn.dao;

import java.sql.SQLException;
import com.cn.subject.Admin;

public interface AdminDao {

		int addAdmin(Admin admin) throws SQLException, Exception;
		
		int deleteAdmin(String identity) throws SQLException;
		
		int updateAdmin(Admin admin) throws SQLException;

		Admin getAdminById(String identity) throws SQLException;
		
		//昵称可以重复
		//List<User> getUserByName(String name) throws SQLException;

		//List<User> getAllUser() throws SQLException;
		

}
