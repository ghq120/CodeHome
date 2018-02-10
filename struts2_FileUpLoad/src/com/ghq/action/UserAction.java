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
	//用于封装上传的对象，userphoto必须和文件域的名字相同  ，不管上传的文件类型是什么，userphoto扩展名都是tmp
	private File userphoto;
	//封装被上传的文件的名字，userphoto必须和文件域的名字相同，FileName区分大小写
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
		//获取目标文件所在目录的绝对路径
		String filePath = ServletActionContext.getServletContext().getRealPath("/images");
		//目标文件的名字 UUID和扩展名组成
		String fileName = UUID.randomUUID().toString()+
						  userphotoFileName.substring(userphotoFileName.lastIndexOf("."));
		//定义目标文件的对象，即要在服务器上出现的文件，要考虑到目标文件不能重复。
		File targetFile = new File(filePath,fileName);
		
		//获取上传文件的输入流
		FileInputStream fin = new FileInputStream(userphoto);
		//获取目标文件的输出流
		FileOutputStream fout = new FileOutputStream(targetFile);
		
		byte[] bs = new byte[1024];
		int count = 0;
		//上传文件
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
