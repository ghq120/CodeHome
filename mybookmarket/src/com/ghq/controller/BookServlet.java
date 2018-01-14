package com.ghq.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ghq.model.dao.IAdviceDao;
import com.ghq.model.dao.IBookDao;
import com.ghq.model.dao.IVoteDao;
import com.ghq.model.daoimpl.AdviceDaoImpl;
import com.ghq.model.daoimpl.BookDaoImpl;
import com.ghq.model.daoimpl.VoteDaoImpl;
import com.ghq.model.entity.Advice;
import com.ghq.model.entity.Book;
import com.ghq.model.entity.Vote;
import com.ghq.model.utils.ChangeDate;
import com.ghq.model.utils.Page;
import com.google.gson.Gson;

public class BookServlet extends HttpServlet {
	private IBookDao ibd = new BookDaoImpl();
	private IAdviceDao iad = new AdviceDaoImpl();
	private IVoteDao ivd = new VoteDaoImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String whichreq = request.getParameter("whichrequest");
		if (whichreq.equals("pagebook")) {
			pagebook(request,response);
		} else if (whichreq.equals("singlebook")) {
			singlebook(request,response);
		}else if (whichreq.equals("querybookwithname")) {
			querybookwithname(request,response);
		}else if (whichreq.equals("getallbookswithpage")) {
			getallbookswithpage(request,response);
		}else if (whichreq.equals("addbook")) {
			addbook(request,response);
		}else if (whichreq.equals("querybookwithisbn")) {
			querybookwithisbn(request,response);
		}else if (whichreq.equals("updatebook")) {
			updatebook(request,response);
		}else if (whichreq.equals("delbook")) {
			delbook(request,response);
		}
		
		
	}
	
	private void delbook(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		String[] isbns = request.getParameterValues("isbn");
		boolean flag = ibd.delbook(isbns);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if (flag) {
			out.print("1");
		}else{
			out.print("0");
		}
		
		out.close();
		
	}

	private void updatebook(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
		Map<String,String> m = new HashMap<String,String>();
		String cover = null;
		Book book = null;
		
		try {
			List<FileItem> fileItems = fileUpload.parseRequest(request);
			for (FileItem fi : fileItems) {
				if (fi.isFormField()) {
					String fieldName = fi.getFieldName();
					String fieldValue = fi.getString();
					m.put(fieldName, fieldValue);
				}else{
					if (fi.getName() != "") {
						cover = UUID.randomUUID().toString()+fi.getName();
						File targetfile = new File(this.getServletContext().getRealPath("/images"),cover);
						try {
							fi.write(targetfile);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		
		String isbn = m.get("isbn");
		isbn = new String(isbn.getBytes("iso-8859-1"),"utf-8");
		
		String bookName = m.get("bookName");
		bookName = new String(bookName.getBytes("iso-8859-1"),"utf-8");
		
		String bookType = m.get("bookType");
		bookType = new String(bookType.getBytes("iso-8859-1"),"utf-8");
		
		double bookPrice = Double.parseDouble(m.get("bookPrice"));
		String writer = m.get("writer");
		writer = new String(writer.getBytes("iso-8859-1"),"utf-8");
		
		String publisher = m.get("publisher");
		publisher = new String(publisher.getBytes("iso-8859-1"),"utf-8");
		
		String isCommend = m.get("isCommend");
		String pubTimeStr = m.get("pubTime");
		Date pubTime = ChangeDate.strDate(pubTimeStr);
		
		String introduce = m.get("introduce");
		introduce = new String(introduce.getBytes("iso-8859-1"),"utf-8");
		
		book = new Book(isbn, bookName, bookType, bookPrice, writer, publisher, cover, isCommend, pubTime, introduce, new Date());
		
		boolean flag = ibd.updatebook(book);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if (flag) {
			out.print("1");
		}else{
			out.print("0");
		}
		
		out.close();
	}

	private void querybookwithisbn(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		
		String isbn = request.getParameter("isbn");
		Book b = ibd.getSinglebook(isbn);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String book = new Gson().toJson(b);
		out.print(book);
		out.close();
	}

	private void addbook(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException  {
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
		Map<String,String> m = new HashMap<String,String>();
		String cover = null;
		
		try {
			List<FileItem> fileItems = fileUpload.parseRequest(request);
			for (FileItem fi : fileItems) {
				if (fi.isFormField()) {
					String fieldName = fi.getFieldName();
					String fieldValue = fi.getString();
					m.put(fieldName, fieldValue);
				}else{
					cover = UUID.randomUUID().toString()+fi.getName();
					File targetfile = new File(this.getServletContext().getRealPath("/images"),cover);
					try {
						fi.write(targetfile);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		
		String isbn = m.get("isbn");
		isbn = new String(isbn.getBytes("iso-8859-1"),"utf-8");
		
		String bookName = m.get("bookName");
		bookName = new String(bookName.getBytes("iso-8859-1"),"utf-8");
		
		String bookType = m.get("bookType");
		bookType = new String(bookType.getBytes("iso-8859-1"),"utf-8");
		
		double bookPrice = Double.parseDouble(m.get("bookPrice"));
		String writer = m.get("writer");
		writer = new String(writer.getBytes("iso-8859-1"),"utf-8");
		
		String publisher = m.get("publisher");
		publisher = new String(publisher.getBytes("iso-8859-1"),"utf-8");
		
		String isCommend = m.get("isCommend");
		String pubTimeStr = m.get("pubTime");
		Date pubTime = ChangeDate.strDate(pubTimeStr);
		
		String introduce = m.get("introduce");
		introduce = new String(introduce.getBytes("iso-8859-1"),"utf-8");
		
		Book book = new Book(isbn, bookName, bookType, bookPrice, writer, publisher, cover, isCommend, pubTime, introduce, new Date());
		
		boolean flag = ibd.addbook(book);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if (flag) {
			out.print("1");
		}else{
			out.print("0");
		}
		
		out.close();
		
	}

	private void getallbookswithpage(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException  {
		
		String pagestr = request.getParameter("page");
		int page = Integer.parseInt(pagestr);
		String rowsstr = request.getParameter("rows");
		int rows = Integer.parseInt(rowsstr);
		String bookName = request.getParameter("bookname");
		String bookType = request.getParameter("booktype");
		
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("page", page);
		m.put("rows", rows);
		m.put("bookName", bookName);
		m.put("bookType", bookType);
		//分页获得所有图书
		List<Book> allBooks = ibd.getAllBookWithPage(m);
		
		List<Book> books= ibd.getAllBooks(m);
		int total = books.size();
		
		Map<String,Object> m2 = new HashMap<String,Object>();
		m2.put("rows", allBooks);
		m2.put("total", total);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		for (Book book : allBooks) {
			if (book.getIsCommend().equals("0")) {
				book.setIsCommend("是");
			}else {
				book.setIsCommend("否");
			}
		}
		String allbooks = new Gson().toJson(m2);
		out.print(allbooks);
		
		out.close();
	}
	
	private void querybookwithname(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("bookname");
		request.setAttribute("bn", name);
		
		List<Book> allcommendbook = ibd.getAllcommendbook(name);
		
		int commendnum = allcommendbook.size();
		
		
		String curp = request.getParameter("curpage");
		int pageSize = 3;
		Page p = new Page(curp, pageSize, commendnum);
		int curpage = p.getcurpage();
		int totalpage = p.gettotalpage();
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalrecord", commendnum);
		request.setAttribute("totalpage", totalpage);
		
		//List<Book> allCommendWithPage = ibd.getAllCommendWithPage(curpage, pageSize);
		List<Advice> alist = iad.getAlladvice();
		List<Vote> vlist = ivd.getAllvote();
		
		request.setAttribute("commbook", allcommendbook);
		request.setAttribute("advicelist", alist);
		request.setAttribute("votelist", vlist);
		
		
		request.getRequestDispatcher("/querybookwithname.jsp").forward(request, response);
		
	}

	public void singlebook(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String isbn = request.getParameter("bisbn");
		Book singlebook = ibd.getSinglebook(isbn);
		request.setAttribute("sbook", singlebook);
		
		List<Advice> alist = iad.getAlladvice();
		List<Vote> vlist = ivd.getAllvote();
		
		request.setAttribute("advicelist", alist);
		request.setAttribute("votelist", vlist);
		
		request.getRequestDispatcher("/singlebook.jsp").forward(request, response);
		
	}

	public void pagebook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String curp = request.getParameter("curpage");
		int commendnum = ibd.commendbooknum();
		int pageSize = 3;
		Page p = new Page(curp, pageSize, commendnum);
		int curpage = p.getcurpage();
		int totalpage = p.gettotalpage();
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalrecord", commendnum);
		request.setAttribute("totalpage", totalpage);
		
		List<Book> blist = ibd.getAllCommendWithPage(curpage, pageSize);
		List<Advice> alist = iad.getAlladvice();
		List<Vote> vlist = ivd.getAllvote();
		
		request.setAttribute("booklist", blist);
		session.setAttribute("advicelist", alist);
		session.setAttribute("votelist", vlist);
		
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}

}
