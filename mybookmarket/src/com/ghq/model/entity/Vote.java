package com.ghq.model.entity;

public class Vote {
	//��־����
	private int id;
	//���
	private String typeName;
	//Ʊ��
	private int voteNum;
	
	public Vote(int id, String typeName, int voteNum) {
		this.id = id;
		this.typeName = typeName;
		this.voteNum = voteNum;
	}
	
	public Vote(String typeName, int voteNum) {
		this.typeName = typeName;
		this.voteNum = voteNum;
	}
	public Vote() {
	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getVoteNum() {
		return voteNum;
	}
	public void setVoteNum(int voteNum) {
		this.voteNum = voteNum;
	}
}
