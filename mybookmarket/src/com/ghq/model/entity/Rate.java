package com.ghq.model.entity;
//�ۿ�ʵ����
public class Rate {
	//�ȼ�
	private int id;
	//�ȼ�������
	private double amount;
	//�ۿ�
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
