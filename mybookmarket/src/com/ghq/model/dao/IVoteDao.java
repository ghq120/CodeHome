package com.ghq.model.dao;

import java.util.List;

import com.ghq.model.entity.Vote;

public interface IVoteDao {
	//��ȡ���е�ͶƱ
	List<Vote> getAllvote();
	//�ж��Ƿ�ͶƱ�ɹ�
	boolean updatevotenum (int id);
}
