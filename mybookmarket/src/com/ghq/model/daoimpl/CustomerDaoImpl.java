package com.ghq.model.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ghq.model.dao.ICustomerDao;
import com.ghq.model.entity.Customer;
import com.ghq.model.utils.Db;

public class CustomerDaoImpl implements ICustomerDao {

	@Override
	public boolean checkCustomer(Customer c) {
		String sql = "SELECT * FROM customer_tb WHERE c_name = ? AND c_password = ? ";
		ResultSet rs = Db.executeQuery(sql, c.getCustName(), c.getCustPassword());
		try {
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			Db.closeAll();
		}
		return false;
	}

	@Override
	public Customer getCustomerbyname(String name) {
		String sql = "SELECT * FROM customer_tb WHERE c_name = ? ";
		ResultSet rs = Db.executeQuery(sql, name);
		Customer cust=null;
		try {
			if (rs.next()) {
				cust=new Customer(  rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
									rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),
									rs.getInt(12),rs.getDouble(13),rs.getInt(14));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			Db.closeAll();
		}
		return cust;
	}

	@Override
	public boolean queryname(String name) {
		String sql = "SELECT * FROM customer_tb WHERE c_name = ? ";
		ResultSet rs = Db.executeQuery(sql, name);
		try {
			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			Db.closeAll();
		}
		return true;
	}

	@Override
	public boolean regCustomer(Customer c) {
		String sql = "INSERT INTO customer_tb (id,c_name,c_realname,c_password,c_tel,c_email,c_grade,c_amount,c_freeze) VALUES (cust_tb_seq.nextval,?,?,?,?,?,1,0,1)";
		int row = Db.exeUpdate(sql, c.getCustName(),c.getCustRealName(),c.getCustPassword(),c.getCustTel(),c.getCustEmail());
		if (row > 0) {
			return true;
		} 
		Db.closeAll();
		
		return false;
	}

}
