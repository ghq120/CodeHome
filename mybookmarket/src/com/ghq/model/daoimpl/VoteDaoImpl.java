package com.ghq.model.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ghq.model.dao.IVoteDao;
import com.ghq.model.entity.Vote;
import com.ghq.model.utils.Db;

public class VoteDaoImpl implements IVoteDao {

	@Override
	public List<Vote> getAllvote() {
		String sql = "select * from vote_tb";
		ResultSet rs = Db.executeQuery(sql);
		List<Vote> vlist = new ArrayList<Vote>();
		Vote vo = null;
		try {
			while (rs.next()) {
				vo = new Vote(rs.getInt("id"),rs.getString("typename"),rs.getInt("votenum"));
				vlist.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closeAll();
		}
		return vlist;
	}

	@Override
	public boolean updatevotenum(int id) {
		String sql = "UPDATE vote_tb SET votenum = votenum + 1 WHERE ID = ? ";
		int row = Db.exeUpdate(sql, id);
		Db.closeAll();
		if (row > 0) {
			return true;
		}
		return false;
	}

}
