package com.ghq.model.dao;

import com.ghq.model.entity.Customer;

public interface ICustomerDao {
	//�ж��û��Ƿ����
	boolean checkCustomer(Customer c);
	//�����û�����������û�
	Customer getCustomerbyname (String name);
	//�ж��û����Ƿ��ظ�
	boolean queryname(String name);
	//�����û�
	boolean regCustomer(Customer c);

}
