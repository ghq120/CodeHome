package com.ghq.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ghq.model.dao.ICustomerDao;
import com.ghq.model.daoimpl.CustomerDaoImpl;
import com.ghq.model.entity.Customer;

public class CustomerServlet extends HttpServlet {
	private ICustomerDao icd = new CustomerDaoImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String whichreq = request.getParameter("whichrequest");
		if (whichreq.equals("customerlogin")) {
			customerlogin(request,response);
		} else if (whichreq.equals("logout")) {
			logout(request,response);
		} else if (whichreq.equals("queryname")) {
			queryname(request,response);
		} else if (whichreq.equals("register")) {
			register(request,response);
		}
	}
	
	private void register(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String name = request.getParameter("cname");
		String realname = request.getParameter("realname");
		String pass = request.getParameter("cpass");
		String phone = request.getParameter("phonenum");
		String email = request.getParameter("email");
		
		Customer c = new Customer(name, realname, pass, phone, email);
		boolean flag = icd.regCustomer(c);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if (flag) {
			out.print("");
			session.setAttribute("cust", c);
		} else{
			out.print("注册失败");
		}
		
		out.close();
	
		
	}

	private void queryname(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("name");
		boolean flag = icd.queryname(name);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if (flag) {
			out.print("");
		}else {
			out.print("用户名重复,请重新输入");
		}
		
		out.close();
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		HttpSession session = request.getSession();
		session.setAttribute("cust", null);
		session.setAttribute("cart", null);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	private void customerlogin(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		String cname = request.getParameter("custname");
		String cpass = request.getParameter("custpass");
		Customer c = new Customer(cname,cpass);
		
		HttpSession session = request.getSession();
		//String checkcode = (String)session.getAttribute("checkcode");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		boolean flag = icd.checkCustomer(c);
		
		if (flag) {
			Customer cust = icd.getCustomerbyname(cname);
			session.setAttribute("cust", cust);
			out.print(" ");
		}else {
			out.print("用户名或密码不正确");
		}
		
		out.close();
		
		
		
	}
	
	
}

	
