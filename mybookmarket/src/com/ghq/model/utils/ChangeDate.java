package com.ghq.model.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeDate {
	//将java.uitl包下的Date对象转为java.sql包下的date
	public static java.sql.Date utilDateToSqlDate(Date date){
		//获取了毫秒值
		long millisTime=date.getTime();
		//根据毫秒值来获取java.sql包下的Date对象
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
