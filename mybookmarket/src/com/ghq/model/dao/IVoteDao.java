package com.ghq.model.dao;

import java.util.List;

import com.ghq.model.entity.Vote;

public interface IVoteDao {
	//获取所有的投票
	List<Vote> getAllvote();
	//判断是否投票成功
	boolean updatevotenum (int id);
}
