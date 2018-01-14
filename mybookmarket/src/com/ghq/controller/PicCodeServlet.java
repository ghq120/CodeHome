package com.ghq.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PicCodeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String metod = request.getParameter("whichrequest");
		
		if ("getCode".equals(metod)) {
			getCode(request,response);
		}else if("checkcode".equals(metod)){
			checkcode(request,response);
		}
		
	}

	private void checkcode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		
		HttpSession session = request.getSession();
		String code = request.getParameter("code");
		String checkcode = (String)session.getAttribute("checkcode");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (code.equalsIgnoreCase(checkcode)) {
			
		}else{
			out.print("验证码不正确");
		}
		out.close();
	}

	private void getCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		Random ran = new Random();
		
		//获得系统字体
		//GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		//String[] fontnames = environment.getAvailableFontFamilyNames();
		
		int w = 100; //图片宽度
		int h = 30;	 //图片高度
		//创建图片， bi指向了一个可以访问缓冲区的图片
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		//获得画笔
		Graphics g = bi.getGraphics();
		//创建颜色对象 参数范围是[0-255];
		Color c = new Color(200+ran.nextInt(50), 200+ran.nextInt(50), 200+ran.nextInt(50));
		//用于存放字体对象
		Font f = null;
		//设置画笔颜色
		g.setColor(c);
		//填充矩形
		g.fillRect(0, 0, w, h);
		//存放验证码的StringBuffer
		StringBuffer stringbuff = new StringBuffer();
		//在图片上写验证码
		for (int i = 0; i < 4; i++) {
			c = new Color(100+ran.nextInt(50), 100+ran.nextInt(50), 100+ran.nextInt(50));
			f = new Font("隶书", Font.BOLD, 18);
			g.setFont(f);
			g.setColor(c);
			
			//产生大小写字母的Unicode码和数字随机数  a为97，A为65
			int lowUni = 97+ran.nextInt(26);
			int upUni = 65+ran.nextInt(26);
			int num = ran.nextInt(10);
			
			String lowUnistr = String.valueOf((char)lowUni);
			String upUnistr = String.valueOf((char)upUni);
			String nstr = String.valueOf(num);
			
			String checkcode1 = ran.nextInt(2) < 0.5 ? (ran.nextInt(2)<0.5 ? lowUnistr : upUnistr) : nstr;
			stringbuff.append(checkcode1);
			
			//在图片上写验证码
			g.drawString(checkcode1, (i+1)*20, 20);
		}
		HttpSession session = request.getSession();
		session.setAttribute("checkcode", stringbuff.toString());
		
		//画干扰线
		for (int i = 0; i < 30; i++) {
			c = new Color(150+ran.nextInt(50), 150+ran.nextInt(50), 150+ran.nextInt(50));
			g.setColor(c);
			int x1 = ran.nextInt(90);
			int y1 = ran.nextInt(20);
			int x2 = ran.nextInt(20)+x1;
			int y2 = ran.nextInt(20)+y1;
			
			g.drawLine(x1, y1, x2, y2);
		}
		
		ImageIO.write(bi, "jpg", response.getOutputStream());
	}

}
