package com.ghq.model.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ghq.model.dao.IOrderDao;
import com.ghq.model.entity.Book;
import com.ghq.model.entity.Order;
import com.ghq.model.utils.ChangeDate;
import com.ghq.model.utils.Db;

public class OrderDaoImpl implements IOrderDao {

	@Override
	public boolean proOrder(Order order, Map<Book, Integer> cart) {
		Set<Book> books = cart.keySet();
		int[] rows = null;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		//将util的Date转换为sql的Date
		java.sql.Date orderDate = ChangeDate.utilDateToSqlDate(order.getOrderdate());
		//向订单表中写入数据
		String sql1 = "INSERT INTO order_tb (orderid,ordernum,orderamount,orderdate,orderstate,orderdes,recname," +
					  "rectruename,recadd,rectel,recemail,recpostcode,rate,orderrateamount,pay_met,send_met)" +
					  "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		//向订单明细表中写入数据
		String sql2 = "INSERT INTO orderitem_tb (id,orderid,isbn,price,itemnum,itemmoney,itemratemoney,rateprice)" +
					  "values (ORDERITEM_TB_SEQ.nextval,?,?,?,?,?,?,?)";
		//修改顾客的消费金额
		String sql3 = "UPDATE customer_tb set c_amount = c_amount+? where c_name = ? ";
		//修改顾客等级
		String sql4 = "UPDATE customer_tb set c_grade = (select max(id) from rate_tb where c_amount > amount) where c_name = ?";
		
		Connection conn = Db.getConn();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql1);
			ps.setObject(1, order.getOrderid());
			ps.setObject(2, order.getOrdernum());
			ps.setObject(3, order.getOrderamount());
			ps.setObject(4, orderDate);
			ps.setObject(5, order.getOrderstate());
			ps.setObject(6, order.getOrderDesc());
			ps.setObject(7, order.getRecname());
			ps.setObject(8, order.getRecrealname());
			ps.setObject(9, order.getRecadd());
			ps.setObject(10, order.getRectel());
			ps.setObject(11, order.getRecemail());
			ps.setObject(12, order.getRecpostcode());
			ps.setObject(13, order.getRate());
			ps.setObject(14, order.getOrderrateamount());
			ps.setObject(15, order.getPaymet());
			ps.setObject(16, order.getSendmet());
			
			count1 = ps.executeUpdate();
			
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			Iterator<Book> bookIt = books.iterator();
			
			while (bookIt.hasNext()) {
				Book book = bookIt.next();
				
				ps2.setObject(1, order.getOrderid());
				ps2.setObject(2, book.getIsbn());
				ps2.setObject(3, book.getBookPrice());
				ps2.setObject(4, cart.get(book));
				ps2.setObject(5, book.getBookPrice()*cart.get(book));
				ps2.setObject(6, order.getRate()*book.getBookPrice()*cart.get(book));
				ps2.setObject(7, order.getRate()*book.getBookPrice());
				
				ps2.addBatch();
			}
			rows = ps2.executeBatch();
			
			PreparedStatement ps3 = conn.prepareStatement(sql3);
			ps3.setObject(1, order.getOrderamount());
			ps3.setObject(2, order.getRecname());
			count2 = ps3.executeUpdate();
			
			PreparedStatement ps4 = conn.prepareStatement(sql4);
			ps4.setObject(1, order.getRecname());
			count3 = ps4.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		for (int i = 0; i < rows.length; i++) {
			if (rows[i] == PreparedStatement.EXECUTE_FAILED) {
				return false;
			}
		}
		if (count1 <= 0) {
			return false;
		}
		if (count2 <= 0) {
			return false;
		}
		if (count3 <= 0) {
			return false;
		}
		
		return true;
	}

	@Override
	public List<Order> getAllOrder(String name) {
		String sql = "select * from order_tb where recname = ? ";
		ResultSet rs = Db.executeQuery(sql, name);
		List<Order> olist = new ArrayList<Order>();
		Order order = null;
		try {
			while (rs.next()) {
				order = new Order(rs.getString("orderid"), rs.getInt("ordernum"), rs.getDouble("orderamount"), 
						          rs.getDate("orderdate"), rs.getInt("orderstate"), rs.getString("orderdes"), 
						          rs.getDouble("rate"), rs.getDouble("orderrateamount"), rs.getString("pay_met"), 
						          rs.getString("send_met"));
				olist.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			Db.closeAll();
		}
		return olist;
	}

	@Override
	public List<List> getorderwithid(String orderid) {
		String sql = "SELECT bt.isbn,bt.cover,bt.bookname,bt.bookprice,oi.itemnum,oi.itemmoney,oi.itemratemoney " +
				     "FROM Orderitem_Tb oi, book_tb bt WHERE oi.isbn = bt.isbn AND oi.orderid = ? ";
		List<List> llist = new ArrayList<List>();
		ResultSet rs = Db.executeQuery(sql, orderid);
		try {
			while (rs.next()) {
				List l = new ArrayList();
				l.add(rs.getString(1));
				l.add(rs.getString(2));
				l.add(rs.getString(3));
				l.add(rs.getDouble(4));
				l.add(rs.getInt(5));
				l.add(rs.getDouble(6));
				l.add(rs.getDouble(7));
				
				llist.add(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			Db.closeAll();
		}
		return llist;
	}

	@Override
	public List<List> getsalesrank() {
		String sql = "SELECT bt.isbn, bt.cover, bt.bookname, bt.writer, bt.publisher, bt.bookprice, temp.sum " +
				     "FROM book_tb bt, (SELECT isbn,SUM(itemnum) SUM  FROM orderitem_tb GROUP BY isbn)temp " +
				     "WHERE bt.isbn = temp.isbn ORDER BY temp.sum DESC";
		ResultSet rs = Db.executeQuery(sql);
		List<List> llist = new ArrayList<List>();
		try {
			while (rs.next()) {
				List l = new ArrayList();
				l.add(rs.getString(1));
				l.add(rs.getString(2));
				l.add(rs.getString(3));
				l.add(rs.getString(4));
				l.add(rs.getString(5));
				l.add(rs.getDouble(6));
				l.add(rs.getInt(7));
				
				llist.add(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			Db.closeAll();
		}
		return llist;
	}
}
