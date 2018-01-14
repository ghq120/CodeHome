package com.ghq.model.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ghq.model.dao.IBookDao;
import com.ghq.model.entity.Book;
import com.ghq.model.entity.BookType;
import com.ghq.model.utils.ChangeDate;
import com.ghq.model.utils.Db;

public class BookDaoImpl implements IBookDao {

	@Override
	public List<Book> getAllcommendbook(String bookname) {
		String sql = "SELECT * FROM book_tb WHERE bookname LIKE ? ";
		ResultSet rs = Db.executeQuery(sql, "%"+bookname+"%");
		Book book = null;
		List<Book> blist = new ArrayList<Book>();
		try {
			while (rs.next()) {
				book = new Book(rs.getString("isbn"),rs.getString("bookname"),rs.getString("booktype"),rs.getDouble("bookprice"),
								rs.getString("writer"),rs.getString("publisher"),rs.getString("cover"),rs.getString("iscommend"),
								rs.getDate("pubtime"),rs.getString("introduce"),rs.getDate("intime"));
				blist.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closeAll();
		}
		return blist;
	}

	@Override
	public int commendbooknum() {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM book_tb WHERE iscommend = ?";
		ResultSet rs = Db.executeQuery(sql, 0);
		try {
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closeAll();
		}
		
		return count;
	}

	@Override
	public List<Book> getAllCommendWithPage(int curPage, int perPageSize) {
		String sql = "SELECT temp.* FROM " +
					 "(SELECT book_tb.*,ROWNUM rn FROM book_tb WHERE ROWNUM <=? and iscommend=?) temp " +
					 "WHERE temp.rn>?";
		ResultSet rs = Db.executeQuery(sql, curPage*perPageSize, 0, (curPage-1)*perPageSize);
		Book book = null;
		List<Book> blist = new ArrayList<Book>();
		try {
			while (rs.next()) {
				book = new Book(rs.getString("isbn"),rs.getString("bookname"),rs.getString("booktype"),rs.getDouble("bookprice"),
								rs.getString("writer"),rs.getString("publisher"),rs.getString("cover"),rs.getString("iscommend"),
								rs.getDate("pubtime"),rs.getString("introduce"),rs.getDate("intime"));
				blist.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closeAll();
		}
		return blist;
	}

	@Override
	public Book getSinglebook(String isbn) {
		String sql ="select * from book_tb where isbn = ?";
		ResultSet rs = Db.executeQuery(sql, isbn);
		Book book = null;
		try {
			if (rs.next()) {
				book = new Book(rs.getString("isbn"),rs.getString("bookname"),rs.getString("booktype"),rs.getDouble("bookprice"),
								rs.getString("writer"),rs.getString("publisher"),rs.getString("cover"),rs.getString("iscommend"),
								rs.getDate("pubtime"),rs.getString("introduce"),rs.getDate("intime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closeAll();
		}
		return book;
	}

	@Override
	public List<String> getAlltype() {
		String sql = "SELECT DISTINCT booktype FROM book_tb";
		ResultSet rs = Db.executeQuery(sql);
		List<String> blist = new ArrayList<String>();
		try {
			while (rs.next()) {
				blist.add(rs.getString("booktype"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closeAll();
		}
		return blist;
	}

	@Override
	public List<Book> getTypebook(String type) {
		String sql ="SELECT * FROM book_tb WHERE booktype = ? ";
		ResultSet rs = Db.executeQuery(sql, type);
		Book book = null;
		List<Book> blist = new ArrayList<Book>();
		try {
			while (rs.next()) {
				book = new Book(rs.getString("isbn"),rs.getString("bookname"),rs.getString("booktype"),rs.getDouble("bookprice"),
								rs.getString("writer"),rs.getString("publisher"),rs.getString("cover"),rs.getString("iscommend"),
								rs.getDate("pubtime"),rs.getString("introduce"),rs.getDate("intime"));
				blist.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closeAll();
		}
		return blist;
	}
	
	@Override
	public List<Book> getAllBooks(Map<String, Object> m) {
		String sql ="SELECT * FROM book_tb where 1=1";
		
		String bookName = (String)m.get("bookName");
		String bookType = (String)m.get("bookType");
		
		if (bookName != null && bookName.length() > 0) {
			sql += " AND bookname like '%"+bookName+"%'";
		}
		if (bookType != null && bookType.length() > 0) {
			sql += " AND booktype = '"+bookType+"'";
		}
		ResultSet rs = Db.executeQuery(sql);
		Book book = null;
		List<Book> blist = new ArrayList<Book>();
		try {
			while (rs.next()) {
				book = new Book(rs.getString("isbn"),rs.getString("bookname"),rs.getString("booktype"),rs.getDouble("bookprice"),
								rs.getString("writer"),rs.getString("publisher"),rs.getString("cover"),rs.getString("iscommend"),
								rs.getDate("pubtime"),rs.getString("introduce"),rs.getDate("intime"));
				blist.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closeAll();
		}
		return blist;
	}
	
	@Override
	public List<Book> getAllBookWithPage(Map<String, Object> m) {
		String sql1 = "SELECT temp.* FROM (SELECT book_tb.*,ROWNUM rn FROM book_tb WHERE 1=1 ";
		String sql2 = " AND ROWNUM <=?) temp WHERE temp.rn>?";
		
		String bookName = (String)m.get("bookName");
		String bookType = (String)m.get("bookType");
		int curPage = (Integer)m.get("page");
		int perPageSize = (Integer)m.get("rows");
		
		if (bookName != null && bookName.length() > 0) {
			sql1 += " AND bookname like '%"+bookName+"%'";
		}
		if (bookType != null && bookType.length() > 0) {
			sql1 += " AND booktype = '"+bookType+"'";
		}
		
		sql1 += sql2;
		
		ResultSet rs = Db.executeQuery(sql1, curPage*perPageSize, (curPage-1)*perPageSize);
		Book book = null;
		List<Book> blist = new ArrayList<Book>();
		try {
			while (rs.next()) {
				book = new Book(rs.getString("isbn"),rs.getString("bookname"),rs.getString("booktype"),rs.getDouble("bookprice"),
								rs.getString("writer"),rs.getString("publisher"),rs.getString("cover"),rs.getString("iscommend"),
								rs.getDate("pubtime"),rs.getString("introduce"),rs.getDate("intime"));
				blist.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closeAll();
		}
		return blist;
	}

	@Override
	public List<BookType> alltype() {
		String sql = "select * from book_type";
		ResultSet rs = Db.executeQuery(sql);
		List<BookType> blist = new ArrayList<BookType>();
		BookType bt = null;
		try {
			while (rs.next()) {
				bt = new BookType(rs.getString(1), rs.getString(2),rs.getInt(3));
				blist.add(bt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.closeAll();
		}
		
		return blist;
	}

	@Override
	public boolean addbook(Book b) {
		Date pubTimeDate = b.getPubTime();
		java.sql.Date pubTime = ChangeDate.utilDateToSqlDate(pubTimeDate);
		Date inTimeDate = b.getInTime();
		java.sql.Date inTime = ChangeDate.utilDateToSqlDate(inTimeDate);
		String isCommendStr = b.getIsCommend();
		int isCommend = Integer.parseInt(isCommendStr);
		
		String sql = "insert into book_tb (isbn,bookname,booktype,bookprice,writer,publisher,cover,iscommend,pubtime,introduce,intime)" +
					 " values(?,?,?,?,?,?,?,?,?,?,?)";
		int rows = Db.exeUpdate(sql, b.getIsbn(),b.getBookName(),b.getBookType(),b.getBookPrice(),b.getWriter(),
					b.getPublisher(),b.getCover(),isCommend,pubTime,b.getIntroduce(),inTime);
		Db.closeAll();
		if (rows > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updatebook(Book b) {
		Date pubTimeDate = b.getPubTime();
		java.sql.Date pubTime = ChangeDate.utilDateToSqlDate(pubTimeDate);
		Date inTimeDate = b.getInTime();
		java.sql.Date inTime = ChangeDate.utilDateToSqlDate(inTimeDate);
		String isCommendStr = b.getIsCommend();
		
		int isCommend = Integer.parseInt("0".equals(isCommendStr)||"ÊÇ".equals(isCommendStr)? "0" : "1");
		int rows = 0;
		
		if (b.getCover() == "") {
			String sql = "update book_tb set bookname = ?,booktype = ?,bookprice = ?,writer = ?,publisher = ?,iscommend = ?,pubtime = ?,introduce = ?,intime = ? " +
					     "where isbn = ?";
			rows = Db.exeUpdate(sql, b.getBookName(),b.getBookType(),b.getBookPrice(),b.getWriter(),
					b.getPublisher(),isCommend,pubTime,b.getIntroduce(),inTime,b.getIsbn());
		}else {
			String sql = "update book_tb set bookname = ?,booktype = ?,bookprice = ?,writer = ?,publisher = ?,cover = ?,iscommend = ?,pubtime = ?,introduce = ?,intime = ? " +
					     "where isbn = ?";
			rows = Db.exeUpdate(sql, b.getBookName(),b.getBookType(),b.getBookPrice(),b.getWriter(),
					b.getPublisher(),b.getCover(),isCommend,pubTime,b.getIntroduce(),inTime,b.getIsbn());
		}
		
		
		Db.closeAll();
		if (rows > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delbook(String[] isbns) {
		String sql = "delete from book_tb where isbn = ?";
		List<List> rows = new ArrayList<List>();
		for (String isbn : isbns) {
			List row = new ArrayList();
			row.add(isbn);
			rows.add(row);
		}
		int[] counts = Db.exeUpdateBatch(sql, rows);
		Db.closeAll();
		for(int count : counts){
			if (count == PreparedStatement.EXECUTE_FAILED) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addType(BookType bt) {
		String sql = "INSERT INTO book_type (typename,type_desc,ID) VALUES (?,?,booktype_tb_seq.nextval)";
		int row = Db.exeUpdate(sql, bt.getTypeName(),bt.getTypeDesc());
		Db.closeAll();
		if (row > 0) {
			return true;
		}
		return false;
	}

}
