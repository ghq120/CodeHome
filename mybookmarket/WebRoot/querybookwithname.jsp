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
	<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
	function checknum(){
		var curpage = $("input[name='curpage']").val();
		if (curpage == '') {
			alert("页码输入不能为空");
		} else{
			$("form").submit();
		}
	}
	</script>
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
    			<form action="<%=path %>/BookServlet?whichrequest=querybookwithname" method="post">
	    			请输入书名：<input type="text" name="bookname">
	    			<input type="submit" value="搜索">
    			</form>
    			<table width="100%" height="90%">
    				<tr>
    					<td>
    						<c:forEach var="b" items="${commbook }">
    					<table width="100%" height="30%" >
	    					<tr>
	    						<td width="20%" height="40%" align="center">
	    							<img src="<%=path %>/images/${b.cover}">
	    						</td>
	    						<td width="30%">
	    							<a href="<%=path %>/BookServlet?whichrequest=singlebook&bisbn=${b.isbn}">
	    							${b.bookName }<br></a>
	    							作者：${b.writer }<br>
	    							出版社：${b.publisher }<br>
	    							价钱：${b.bookPrice }￥<br>
	    						</td>
	    						<td>
	    							<a href="">购买</a>
	    						</td>
	    					</tr>
    					</table>
    						</c:forEach>
    					</td>
    				</tr>
    				<tr>
    					<td height="10%" align="center">
    					当前页：${curpage }/${totalpage }&nbsp;&nbsp;
		    			<a href="<%=path %>/BookServlet?whichrequest=querybookwithname&curpage=1&bookname=${bn}">首页</a>
		    			<a href="<%=path %>/BookServlet?whichrequest=querybookwithname&curpage=${curpage-1 }&bookname=${bn}">上一页</a>
		    			<a href="<%=path %>/BookServlet?whichrequest=querybookwithname&curpage=${curpage+1 }&bookname=${bn}">下一页</a>
		    			<a href="<%=path %>/BookServlet?whichrequest=querybookwithname&curpage=${totalpage }&bookname=${bn}">尾页</a>&nbsp;&nbsp;
		    			
		    			<form action="<%=path %>/BookServlet?whichrequest=querybookwithname&bookname=${bn}" method="post">
		    				转到<input type="text" size="1" name="curpage" 
		    					onkeyup="this.value=this.value.replace(/\D/g,'')" 
		    					onafterpaste="this.value=this.value.repalce(/\D/g,'')">页
		    				<input type="button" value="GO" onclick="checknum()">&nbsp;总记录数：${totalrecord }
		    			</form>
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
