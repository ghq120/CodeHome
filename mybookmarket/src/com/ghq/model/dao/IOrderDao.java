package com.ghq.model.dao;

import java.util.List;
import java.util.Map;

import com.ghq.model.entity.Book;
import com.ghq.model.entity.Order;

public interface IOrderDao {
	//生产订单  cust就是收货人，cart是购物车
	boolean proOrder(Order order, Map<Book,Integer> cart);
	//获得所有订单信息
	List<Order> getAllOrder(String name);
	//根据编号获取订单详细信息
	List<List> getorderwithid (String orderid);
	//获取销量排行
	List<List> getsalesrank();
	
}
