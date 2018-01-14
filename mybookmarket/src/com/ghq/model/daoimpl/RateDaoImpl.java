package com.ghq.model.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ghq.model.dao.IRateDao;
import com.ghq.model.entity.Rate;
import com.ghq.model.utils.Db;

public class RateDaoImpl implements IRateDao {

	@Override
	public Rate getRate(String name) {
		String sql="select * from rate_tb where id=(select c_grade from customer_tb where c_name=?)";
		ResultSet rs=Db.executeQuery(sql, name);
		Rate rate=null;
		try {
			if(rs.next()){
				rate=new Rate(rs.getInt(1),rs.getDouble(2),rs.getDouble(3));
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return rate;
	}

}
