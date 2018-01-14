package com.ghq.model.utils;

public class Page {
	private String currentPage;
	private int pageSize;
	private int totalrecord;
	
	public Page(String currentPage, int pageSize, int totalrecord) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalrecord = totalrecord;
	}
	public Page() {
		
	}
	
	public int gettotalpage(){
		int totalpage = 0;
		
		int m = totalrecord/pageSize;
		int n = totalrecord%pageSize;
		
		totalpage = (n == 0)? m : m+1;
		
		return totalpage;
	}
	
	public int getcurpage(){
		int curpage = 0;
		int totalpage = gettotalpage();
		
		if (currentPage == null) {
			curpage = 1;
		} else{
			curpage = Integer.parseInt(currentPage);
		}
		if (curpage < 1) {
			curpage = 1;
		}
		if (curpage > totalpage) {
			curpage = totalpage;
		}
		
		return curpage;
	}
}
