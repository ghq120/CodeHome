package com.ghq.model.entity;

import java.util.Date;

public class Advice {
	//标志属性
	private int id;
	//内容
	private String content;
	//录入时间
	private Date inTime;
	
	public Advice(int id, String content, Date inTime) {
		super();
		this.id = id;
		this.content = content;
		this.inTime = inTime;
	}
	public Advice(String content, Date inTime) {

		this.content = content;
		this.inTime = inTime;
	}
	public Advice() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
}
