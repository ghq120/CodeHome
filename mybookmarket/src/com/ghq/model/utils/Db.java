package com.ghq.model.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Db {
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static String user = "scott";
	private static String password = "itcast";
	
	private static Connection conn;
	private static PreparedStatement ps;
	private static ResultSet rs;
	
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn(){
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static PreparedStatement getPs(String sql , Object...args){
		try {
			getConn();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i+1, args[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ps;
	}
	
	//��ѯ�ķ���
	public static ResultSet executeQuery(String sql , Object...args){
		getPs(sql, args);
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	//��ɾ�ĵķ���
	public static int exeUpdate(String sql , Object...args){
		getPs(sql, args);
		int row = 0;
		try {
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	//������ķ���
	public static int[] exeUpdateBatch(String sql , List<List> args){
		getConn();
		int[] rows = null;
		
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < args.size(); i++) {
				for (int j = 0; j < args.get(i).size(); j++) {
					ps.setObject(j+1, args.get(i).get(j));
				}
				ps.addBatch();
			}
			rows = ps.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return rows;
		
	}
	
	//�ر���Դ
	public static void closeAll(){
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
