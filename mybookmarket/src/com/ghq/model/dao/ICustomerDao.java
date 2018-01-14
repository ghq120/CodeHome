package com.ghq.model.dao;

import com.ghq.model.entity.Customer;

public interface ICustomerDao {
	//判断用户是否存在
	boolean checkCustomer(Customer c);
	//根据用户名获得整个用户
	Customer getCustomerbyname (String name);
	//判断用户名是否重复
	boolean queryname(String name);
	//新增用户
	boolean regCustomer(Customer c);

}
