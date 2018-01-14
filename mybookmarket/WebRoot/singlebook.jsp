<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    
    <table width="100%" height="100%">
    	<tr>
    		<!-- 顶部信息显示 -->
    		<td colspan="2" height="10%" bgcolor="#E0FFFF">
    			<jsp:include page="top.jsp"></jsp:include>
    		</td>
    	</tr>
    	<tr bgcolor="#FAFAD2">
    		<!-- 左边信息显示 -->
    		<td width="25%">
    			<jsp:include page="left.jsp"></jsp:include>
    		</td>
    		<!-- 图书信息展示 -->
    		<td>
    			<table width="100%" height="100%">
    				<tr>
    					<td>
    					<table width="100%" height="30%">
	    					<tr>
	    						<td width="50%"  align="center">
	    							<img src="<%=path %>/images/${sbook.cover}" width="60%" height="80%">
	    						</td>
	    						<td width="30%">
	    							<div>书名：${sbook.bookName }</div>
	    							<div>isbn：${sbook.isbn }</div>
	    							<div>作者：${sbook.writer }</div>
	    							<div>出版社：${sbook.publisher }</div>
	    							<div>出版时间：${sbook.pubTime }</div>
	    							<div>价钱：￥${sbook.bookPrice }</div>
	    							<div>简介：<br>&nbsp;&nbsp;${sbook.introduce }</div>
	    						</td>
	    						<td align="center">
	    							<a href="<%=path %>/OrderServlet?whichrequest=buy&isbn=${sbook.isbn}">购买</a>
	    						</td>
	    					</tr>
	    					<tr height="100">
	    						<td colspan="2" align="center"><input type="button" value="返回上一页" style="font-size: 20px" onclick="javascript:history.back(-1);"></td>
	    					</tr>
    					</table>
    					</td>
    				</tr>
    			</table>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="2" height="10%" bgcolor="#E0FFFF"> 
    			<jsp:include page="buttom.jsp"></jsp:include>
    		</td>
    	</tr>
    </table>
  </body>
</html>
