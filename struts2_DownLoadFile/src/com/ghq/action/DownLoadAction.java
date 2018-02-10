package com.ghq.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DownLoadAction extends ActionSupport {
	private String filename;
	private String finalName;
	
	public String getFilename() {
		//解决被下载文件的名字是中文时乱码的情况
		try {
			filename=new String(filename.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getFinalName() {
		return finalName;
	}
	
	public InputStream getTargetFile() throws Exception{
		//获取ServletContext的对象
		ServletContext application = ServletActionContext.getServletContext();
		InputStream fin = application.getResourceAsStream("/images/"+filename);
		return fin;
	}
	
	
	
}
