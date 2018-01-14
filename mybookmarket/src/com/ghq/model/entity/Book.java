package com.ghq.model.entity;

import java.util.Date;

public class Book {
	//isbn
	private String isbn;
	//����
	private String bookName;
	//����
	private String bookType;
	//����
	private double bookPrice;
	//����
	private String writer;
	//������
	private String publisher;
	//����
	private String  cover;
	//�Ƿ��Ƽ� 0 �Ƽ� 1 ���Ƽ�
	private String isCommend;
	//����ʱ��
	private Date pubTime;
	//���
	private String  introduce;
	//¼��ʱ��
	private Date inTime;
	public Book(String isbn, String bookName, String bookType,
			double bookPrice, String writer, String publisher, String cover,
			String isCommend, Date pubTime, String introduce, Date inTime) {
		super();
		this.isbn = isbn;
		this.bookName = bookName;
		this.bookType = bookType;
		this.bookPrice = bookPrice;
		this.writer = writer;
		this.publisher = publisher;
		this.cover = cover;
		this.isCommend = isCommend;
		this.pubTime = pubTime;
		this.introduce = introduce;
		this.inTime = inTime;
	}
	
	
	public Book() {
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getIsCommend() {
		return isCommend;
	}
	public void setIsCommend(String isCommend) {
		this.isCommend = isCommend;
	}
	public Date getPubTime() {
		return pubTime;
	}
	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}
	
	
}
