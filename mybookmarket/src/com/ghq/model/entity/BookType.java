package com.ghq.model.entity;
//ͼ������ʵ����
public class BookType {
	//������
	private String typeName;
	//��������
	private String typeDesc;
	//��־����
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
