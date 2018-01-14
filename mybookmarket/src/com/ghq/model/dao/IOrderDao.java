package com.ghq.model.dao;

import java.util.List;
import java.util.Map;

import com.ghq.model.entity.Book;
import com.ghq.model.entity.Order;

public interface IOrderDao {
	//��������  cust�����ջ��ˣ�cart�ǹ��ﳵ
	boolean proOrder(Order order, Map<Book,Integer> cart);
	//������ж�����Ϣ
	List<Order> getAllOrder(String name);
	//���ݱ�Ż�ȡ������ϸ��Ϣ
	List<List> getorderwithid (String orderid);
	//��ȡ��������
	List<List> getsalesrank();
	
}
