package com.ghq.model.entity;
//折扣实体类
public class Rate {
	//等级
	private int id;
	//等级的上限
	private double amount;
	//折扣
	private double rate;
	public Rate(int id, double amount, double rate) {
		super();
		this.id = id;
		this.amount = amount;
		this.rate = rate;
	}
	public Rate() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
}
