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
			out.print("��֤�벻��ȷ");
		}
		out.close();
	}

	private void getCode(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		Random ran = new Random();
		
		//���ϵͳ����
		//GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		//String[] fontnames = environment.getAvailableFontFamilyNames();
		
		int w = 100; //ͼƬ���
		int h = 30;	 //ͼƬ�߶�
		//����ͼƬ�� biָ����һ�����Է��ʻ�������ͼƬ
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		//��û���
		Graphics g = bi.getGraphics();
		//������ɫ���� ������Χ��[0-255];
		Color c = new Color(200+ran.nextInt(50), 200+ran.nextInt(50), 200+ran.nextInt(50));
		//���ڴ���������
		Font f = null;
		//���û�����ɫ
		g.setColor(c);
		//������
		g.fillRect(0, 0, w, h);
		//�����֤���StringBuffer
		StringBuffer stringbuff = new StringBuffer();
		//��ͼƬ��д��֤��
		for (int i = 0; i < 4; i++) {
			c = new Color(100+ran.nextInt(50), 100+ran.nextInt(50), 100+ran.nextInt(50));
			f = new Font("����", Font.BOLD, 18);
			g.setFont(f);
			g.setColor(c);
			
			//������Сд��ĸ��Unicode������������  aΪ97��AΪ65
			int lowUni = 97+ran.nextInt(26);
			int upUni = 65+ran.nextInt(26);
			int num = ran.nextInt(10);
			
			String lowUnistr = String.valueOf((char)lowUni);
			String upUnistr = String.valueOf((char)upUni);
			String nstr = String.valueOf(num);
			
			String checkcode1 = ran.nextInt(2) < 0.5 ? (ran.nextInt(2)<0.5 ? lowUnistr : upUnistr) : nstr;
			stringbuff.append(checkcode1);
			
			//��ͼƬ��д��֤��
			g.drawString(checkcode1, (i+1)*20, 20);
		}
		HttpSession session = request.getSession();
		session.setAttribute("checkcode", stringbuff.toString());
		
		//��������
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
