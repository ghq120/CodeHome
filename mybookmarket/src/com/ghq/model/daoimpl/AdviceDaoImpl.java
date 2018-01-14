package com.ghq.model.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ghq.model.dao.IAdviceDao;
import com.ghq.model.entity.Advice;
import com.ghq.model.utils.Db;

public class AdviceDaoImpl implements IAdviceDao {

	@Override
	public List<Advice> getAlladvice() {
		String sql = "select * from advice_tb";
		ResultSet rs = Db.executeQuery(sql);
		List<Advice> alist = new ArrayList<Advice>();
		Advice ad = null;
		try {
			while (rs.next()) {
				ad = new Advice(rs.getInt("id"),rs.getString("content"),rs.getDate("intime"));
				alist.add(ad);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closeAll();
		}
		return alist;
	}

}
