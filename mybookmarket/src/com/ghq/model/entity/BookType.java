package com.ghq.model.entity;
//图书类型实体类
public class BookType {
	//类型名
	private String typeName;
	//类型描述
	private String typeDesc;
	//标志属性
	private int id;
	
	public BookType(String typeName, String typeDesc, int id) {
		this.typeName = typeName;
		this.typeDesc = typeDesc;
		this.id = id;
	}
	public BookType(String typeName, String typeDesc) {
		this.typeName = typeName;
		this.typeDesc = typeDesc;
	}
	public BookType() {
	}
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
