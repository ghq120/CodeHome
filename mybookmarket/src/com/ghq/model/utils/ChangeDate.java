package com.ghq.model.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeDate {
	//��java.uitl���µ�Date����תΪjava.sql���µ�date
	public static java.sql.Date utilDateToSqlDate(Date date){
		//��ȡ�˺���ֵ
		long millisTime=date.getTime();
		//���ݺ���ֵ����ȡjava.sql���µ�Date����
		java.sql.Date pubTime=new java.sql.Date(millisTime);
		return pubTime;
	}
	
	public static Date strDate(String time){
		Date date = null;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = df.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
