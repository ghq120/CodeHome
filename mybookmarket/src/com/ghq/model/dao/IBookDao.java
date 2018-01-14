package com.ghq.model.dao;

import java.util.List;
import java.util.Map;

import com.ghq.model.entity.Book;
import com.ghq.model.entity.BookType;

public interface IBookDao {
	//模糊查询所有的推荐图书
	List<Book> getAllcommendbook(String bookname);
	//获得所有推荐图书的个数
	int commendbooknum();
	//分页获取推荐的图书
	List<Book> getAllCommendWithPage(int curPage,int perPageSize);
	//根据isbn查找书
	Book getSinglebook(String isbn);
	//获得所有图书的类型
	List<String> getAlltype();
	//根据类型获得图书
	List<Book> getTypebook(String type);
	//获得所有图书
	List<Book> getAllBooks(Map<String, Object> m);
	//分页获取所有图书
	List<Book> getAllBookWithPage(Map<String, Object> m);
	//后台所有图书的类型
	List<BookType> alltype();
	//新增图书
	boolean addbook (Book b);
	//修改图书
	boolean updatebook (Book b);
	//删除图书
	boolean delbook (String[] isbns);
	
	//增加类型
	boolean addType(BookType bt);
	
}
