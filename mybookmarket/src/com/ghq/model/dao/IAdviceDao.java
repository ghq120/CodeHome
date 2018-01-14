package com.ghq.model.dao;

import java.util.List;

import com.ghq.model.entity.Advice;

public interface IAdviceDao {
	//获得所有的公告
	List<Advice> getAlladvice();
}
