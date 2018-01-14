package com.ghq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ghq.model.dao.IBookDao;
import com.ghq.model.daoimpl.BookDaoImpl;
import com.ghq.model.entity.Book;
import com.ghq.model.entity.BookType;
import com.google.gson.Gson;

public class BookTypeServlet extends HttpServlet {
	private IBookDao ibd = new BookDaoImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String whichreq = request.getParameter("whichrequest");
		if ("getalltype".equals(whichreq)) {
			getalltype(request,response);
		} else if ("gettypebook".equals(whichreq)) {
			gettypebook(request,response);
		} else if ("alltype".equals(whichreq)) {
			alltype(request,response);
		} else if ("addType".equals(whichreq)) {
			addType(request,response);
		}
		
	}

	private void addType(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		String typename = request.getParameter("typeName");
		String typedesc = request.getParameter("typeDesc");
		
		BookType bt = new BookType(typename, typedesc);
		boolean flag = ibd.addType(bt);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (flag) {
			out.print("1");
		}else {
			out.print("0");
		}
		
	}

	private void alltype(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<BookType> alltype = ibd.alltype();
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		String typeName = alltype.get(0).getTypeName();
		session.setAttribute("type", typeName);
		String json = new Gson().toJson(alltype);
		out.print(json);
		out.close();
	}

	private void gettypebook(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		String type = request.getParameter("type");
		List<Book> typebook = ibd.getTypebook(type);
		session.setAttribute("typebook", typebook);
		
		request.getRequestDispatcher("/booktype.jsp").forward(request, response);
		
		/*response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		String json = new Gson().toJson(typebook);
		out.print(json);
		
		if (out != null) {
			out.close();
		}*/
		
	}

	private void getalltype(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<String> alltype = ibd.getAlltype();
		HttpSession session = request.getSession();
		session.setAttribute("btype", alltype);
		
		request.getRequestDispatcher("/booktype.jsp").forward(request, response);
	}

}
