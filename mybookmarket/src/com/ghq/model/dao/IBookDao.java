package com.ghq.model.dao;

import java.util.List;
import java.util.Map;

import com.ghq.model.entity.Book;
import com.ghq.model.entity.BookType;

public interface IBookDao {
	//ģ����ѯ���е��Ƽ�ͼ��
	List<Book> getAllcommendbook(String bookname);
	//��������Ƽ�ͼ��ĸ���
	int commendbooknum();
	//��ҳ��ȡ�Ƽ���ͼ��
	List<Book> getAllCommendWithPage(int curPage,int perPageSize);
	//����isbn������
	Book getSinglebook(String isbn);
	//�������ͼ�������
	List<String> getAlltype();
	//�������ͻ��ͼ��
	List<Book> getTypebook(String type);
	//�������ͼ��
	List<Book> getAllBooks(Map<String, Object> m);
	//��ҳ��ȡ����ͼ��
	List<Book> getAllBookWithPage(Map<String, Object> m);
	//��̨����ͼ�������
	List<BookType> alltype();
	//����ͼ��
	boolean addbook (Book b);
	//�޸�ͼ��
	boolean updatebook (Book b);
	//ɾ��ͼ��
	boolean delbook (String[] isbns);
	
	//��������
	boolean addType(BookType bt);
	
}
