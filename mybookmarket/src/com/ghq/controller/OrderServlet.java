package com.ghq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ghq.model.dao.IAdviceDao;
import com.ghq.model.dao.IBookDao;
import com.ghq.model.dao.IOrderDao;
import com.ghq.model.dao.IRateDao;
import com.ghq.model.dao.IVoteDao;
import com.ghq.model.daoimpl.AdviceDaoImpl;
import com.ghq.model.daoimpl.BookDaoImpl;
import com.ghq.model.daoimpl.OrderDaoImpl;
import com.ghq.model.daoimpl.RateDaoImpl;
import com.ghq.model.daoimpl.VoteDaoImpl;
import com.ghq.model.entity.Advice;
import com.ghq.model.entity.Book;
import com.ghq.model.entity.Customer;
import com.ghq.model.entity.Order;
import com.ghq.model.entity.Vote;

public class OrderServlet extends HttpServlet {
	private IBookDao ibd = new BookDaoImpl();
	private IAdviceDao iad = new AdviceDaoImpl();
	private IVoteDao ivd = new VoteDaoImpl();
	private IRateDao ird = new RateDaoImpl();
	private IOrderDao iod = new OrderDaoImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("whichrequest");
		if ("buy".equals(method)) {
			buy(request, response);
		} else if("remove".equals(method)){
			remove(request, response);
		} else if("removeall".equals(method)){
			removeall(request, response);
		} else if("totalprice".equals(method)){
			totalprice(request, response);
		} else if("proorder".equals(method)){
			proorder(request, response);
		} else if("showorder".equals(method)){
			showorder(request, response);
		} else if("orderitems".equals(method)){
			orderitems(request, response);
		} else if("salesrank".equals(method)){
			salesrank(request, response);
		}
	}
	//销售排行的方法
	private void salesrank(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		List<List> salesrank = iod.getsalesrank();
		request.setAttribute("salesrank", salesrank);
		request.getRequestDispatcher("/salesrank.jsp").forward(request, response);
	}
	
	private void orderitems(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		String oid = request.getParameter("orderid");
		List<List> getorderwithid = iod.getorderwithid(oid);
		request.setAttribute("order", getorderwithid);
		
		request.getRequestDispatcher("/showOrderdetail.jsp").forward(request, response);
		
	}

	private void showorder(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		HttpSession session = request.getSession();
		Customer cust = (Customer)session.getAttribute("cust");
		List<Order> olist = iod.getAllOrder(cust.getCustName());
		request.setAttribute("order", olist);
		request.getRequestDispatcher("/showOrder.jsp").forward(request, response);
		
	}

	private void proorder(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		HttpSession session = request.getSession();
		//获得购物车和用户
		Map<Book,Integer> m = (HashMap<Book, Integer>)session.getAttribute("cart");
		Customer cust = (Customer)session.getAttribute("cust");
		//获得用户的等级
		double rate = ird.getRate(cust.getCustName()).getRate();
		//订单编号
		String orderid = UUID.randomUUID().toString();
		//获取所有键的集合，book对象
		Set<Book> bookset = m.keySet();
		double mul = 0;		//总金额
		double ratemul = 0; //优惠后总金额
		int nums = 0;		//总数量
		for (Book b : bookset) {
			int num = m.get(b);
			double price = b.getBookPrice();
			mul += num * price;
			nums += num;
			ratemul += num * price * rate;
		}
		String bookdesc = request.getParameter("bookdesc");
		String custname = request.getParameter("custname");
		String custrealname = request.getParameter("custrealname");
		String custadd = request.getParameter("custadd");
		String custtel = request.getParameter("custtel");
		String custemail = request.getParameter("custemail");
		String custepostcode = request.getParameter("custepostcode");
		String paymethod = request.getParameter("paymethod");
		String sendmethod = request.getParameter("sendmethod");
		
		Order order = new Order(orderid, nums, mul, new Date(), 1, bookdesc, custname , custrealname, custadd, 
				  	  custtel, custemail, custepostcode, rate, ratemul, paymethod, sendmethod);
		
		boolean flag = iod.proOrder(order, m);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if (flag) {
			session.setAttribute("order", order);
			//request.getRequestDispatcher("/ordersuc.jsp").forward(request, response);
			//response.sendRedirect(request.getContextPath()+"/ordersuc.jsp");
			out.print("");
		} else {
			out.print("支付失败");
		}
		
	}

	private void totalprice(HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException{
		HttpSession session = request.getSession();
		String numStr = request.getParameter("num");
		numStr = numStr.trim();
		int num = Integer.parseInt(numStr);
		String isbn = request.getParameter("isbn");
		Book book = ibd.getSinglebook(isbn);
		Map<Book,Integer> m = (HashMap<Book, Integer>)session.getAttribute("cart");
		//从map中获取该本书的数量并修改成输入的数量
		int oldnum = m.get(book);
		oldnum = num;
		m.put(book, oldnum);
		//计算总价钱
		double sum = 0;
		Set<Book> bookset = m.keySet();
		for (Book b : bookset) {
			double price = b.getBookPrice();
			int number = m.get(b);
			sum += price*number;
			session.setAttribute("sum", sum);
		}
		
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
		
	}
	//清空购物车
	private void removeall(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		HttpSession session = request.getSession();
		Map<Book,Integer> m = (HashMap<Book, Integer>)session.getAttribute("cart");
		m.clear();
		session.removeAttribute("sum");
		
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}
	//删除一条商品
	private void remove(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		HttpSession session = request.getSession();
		Map<Book,Integer> m = (HashMap<Book, Integer>)session.getAttribute("cart");
		String isbn = request.getParameter("isbn");
		Book b = ibd.getSinglebook(isbn);
		m.remove(b);
		
		if (m.isEmpty()) {
			session.removeAttribute("sum");
		}
		
		double sum = 0;
		Set<Book> bookset = m.keySet();
		for (Book book : bookset) {
			double price = book.getBookPrice();
			int number = m.get(book);
			sum += price*number;
			session.setAttribute("sum", sum);
		}
		
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
		
	}

	private void buy(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//判断用户是否登录，没登录重定向到登录界面
		Customer cust = (Customer)session.getAttribute("cust");
		if (cust == null) {
			request.setAttribute("tip", "您还没有登录，请登录");
			request.getRequestDispatcher("/custlogin.jsp").forward(request, response);
			//response.sendRedirect(request.getContextPath()+"/custlogin.jsp");
			return;
		}
		String isbn = request.getParameter("isbn");
		Book book = ibd.getSinglebook(isbn);
		Map<Book,Integer> m  = (HashMap<Book, Integer>)session.getAttribute("cart");
		//第一次购买，创建购物车
		if (m == null) {
			m = new HashMap<Book, Integer>();
			session.setAttribute("cart", m);
		} 
		//如果已经存在相同产品，数量＋1
		if (m.containsKey(book)) {
			int size = m.get(book);
			size++;
			m.put(book, size);
		}else {
			m.put(book, 1);
		}
		//商品总价
		double sum = 0;
		Set<Book> bookset = m.keySet();
		for (Book b : bookset) {
			double price = b.getBookPrice();
			int num = m.get(b);
			sum += price*num;
			session.setAttribute("sum", sum);
		}
		List<Advice> alist = iad.getAlladvice();
		List<Vote> vlist = ivd.getAllvote();
		
		request.setAttribute("advicelist", alist);
		request.setAttribute("votelist", vlist);
		
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}

}
