package com.ghq.model.entity;

import java.util.Date;
//订单类
public class Order {
	//订单号
	private String orderid;
	//订单中的总数量
	private int ordernum;
	//总金额
	private double orderamount;
	//订单时间
	private Date orderdate;
	//订单状态
	private int orderstate;
	//描述
	private String orderDesc;
	//登录人的用户名
	private String recname;
	//收货人的名字
	private String recrealname;
	//地址
	private String recadd;
	//手机
	private String rectel;
	//email
	private String recemail;
	//邮编
	private String recpostcode;
	//折扣
	private double rate;
	//优惠后的金额
	private double orderrateamount;
	//付款方式
	private String paymet;
	//配送方式
	private String sendmet;
	
	public Order(String orderid, int ordernum, double orderamount,
			Date orderdate, int orderstate, String orderDesc, String recname,
			String recrealname, String recadd, String rectel, String recemail,
			String recpostcode, double rate, double orderrateamount,
			String paymet, String sendmet) {
		super();
		this.orderid = orderid;
		this.ordernum = ordernum;
		this.orderamount = orderamount;
		this.orderdate = orderdate;
		this.orderstate = orderstate;
		this.orderDesc = orderDesc;
		this.recname = recname;
		this.recrealname = recrealname;
		this.recadd = recadd;
		this.rectel = rectel;
		this.recemail = recemail;
		this.recpostcode = recpostcode;
		this.rate = rate;
		this.orderrateamount = orderrateamount;
		this.paymet = paymet;
		this.sendmet = sendmet;
	}
	public Order(String orderid, int ordernum, double orderamount,
			Date orderdate, int orderstate, String orderDesc, double rate, 
			double orderrateamount,String paymet, String sendmet) {
		super();
		this.orderid = orderid;
		this.ordernum = ordernum;
		this.orderamount = orderamount;
		this.orderdate = orderdate;
		this.orderstate = orderstate;
		this.orderDesc = orderDesc;
		this.rate = rate;
		this.orderrateamount = orderrateamount;
		this.paymet = paymet;
		this.sendmet = sendmet;
	}
	
	public Order() {
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public double getOrderamount() {
		return orderamount;
	}
	public void setOrderamount(double orderamount) {
		this.orderamount = orderamount;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	public int getOrderstate() {
		return orderstate;
	}
	public void setOrderstate(int orderstate) {
		this.orderstate = orderstate;
	}
	public String getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
	public String getRecname() {
		return recname;
	}
	public void setRecname(String recname) {
		this.recname = recname;
	}
	public String getRecrealname() {
		return recrealname;
	}
	public void setRecrealname(String recrealname) {
		this.recrealname = recrealname;
	}
	public String getRecadd() {
		return recadd;
	}
	public void setRecadd(String recadd) {
		this.recadd = recadd;
	}
	public String getRectel() {
		return rectel;
	}
	public void setRectel(String rectel) {
		this.rectel = rectel;
	}
	public String getRecemail() {
		return recemail;
	}
	public void setRecemail(String recemail) {
		this.recemail = recemail;
	}
	public String getRecpostcode() {
		return recpostcode;
	}
	public void setRecpostcode(String recpostcode) {
		this.recpostcode = recpostcode;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getOrderrateamount() {
		return orderrateamount;
	}
	public void setOrderrateamount(double orderrateamount) {
		this.orderrateamount = orderrateamount;
	}
	public String getPaymet() {
		return paymet;
	}
	public void setPaymet(String paymet) {
		this.paymet = paymet;
	}
	public String getSendmet() {
		return sendmet;
	}
	public void setSendmet(String sendmet) {
		this.sendmet = sendmet;
	}
	
}
