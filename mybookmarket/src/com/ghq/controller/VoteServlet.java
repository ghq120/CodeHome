package com.ghq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ghq.model.dao.IVoteDao;
import com.ghq.model.daoimpl.VoteDaoImpl;
import com.ghq.model.entity.Vote;

public class VoteServlet extends HttpServlet {
	private IVoteDao ivd = new VoteDaoImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("whichrequest");
		
		if ("dovote".equals(method)) {
			dovote(request,response);
		}else if ("showvote".equals(method)) {
			showvote(request,response);
		} 
	}

	private void showvote(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException  {
		List<Vote> allvote = ivd.getAllvote();
		request.setAttribute("allvote", allvote);
		request.getRequestDispatcher("/showvote.jsp").forward(request, response);
	}

	private void dovote(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String voteid = request.getParameter("selectvote");
		int id = Integer.parseInt(voteid);
		boolean flag = ivd.updatevotenum(id);
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		if (flag) {
			out.print("投票成功");
		}else {
			out.print("投票失败");
		}
		if (out != null) {
			out.close();
		}
		
	}

}
