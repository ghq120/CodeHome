package com.ghq.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	private String username;
	private String userpass;
	//���ڷ�װ�ϴ��Ķ���userphoto������ļ����������ͬ  �������ϴ����ļ�������ʲô��userphoto��չ������tmp
	private File userphoto;
	//��װ���ϴ����ļ������֣�userphoto������ļ����������ͬ��FileName���ִ�Сд
	private String userphotoFileName;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public File getUserphoto() {
		return userphoto;
	}

	public void setUserphoto(File userphoto) {
		this.userphoto = userphoto;
	}

	public String getUserphotoFileName() {
		return userphotoFileName;
	}

	public void setUserphotoFileName(String userphotoFileName) {
		this.userphotoFileName = userphotoFileName;
	}
	
	public String regUI() throws Exception{
		return "regUI";
	}
	
	public String reg() throws Exception {
		//��ȡĿ���ļ�����Ŀ¼�ľ���·��
		String filePath = ServletActionContext.getServletContext().getRealPath("/images");
		//Ŀ���ļ������� UUID����չ�����
		String fileName = UUID.randomUUID().toString()+
						  userphotoFileName.substring(userphotoFileName.lastIndexOf("."));
		//����Ŀ���ļ��Ķ��󣬼�Ҫ�ڷ������ϳ��ֵ��ļ���Ҫ���ǵ�Ŀ���ļ������ظ���
		File targetFile = new File(filePath,fileName);
		
		//��ȡ�ϴ��ļ���������
		FileInputStream fin = new FileInputStream(userphoto);
		//��ȡĿ���ļ��������
		FileOutputStream fout = new FileOutputStream(targetFile);
		
		byte[] bs = new byte[1024];
		int count = 0;
		//�ϴ��ļ�
		while ((count = fin.read(bs))!= -1) {
			fout.write(bs,0,count);
		}
		
		if (fout != null) {
			fout.close();
		}
		if (fin != null) {
			fin.close();
		}
		
		return "regSuc";
	}
	
}
