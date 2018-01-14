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
    		<td>
    			<table height="100%" width="100%">
    				<tr height="10%">
	    				<td colspan="3" align="left">
	    					<font size="20" >销量排行榜</font>
	    				</td>
    				</tr>
    				<tr>
    					<td>
		    				<table width="100%" >
		    					<c:forEach var="sr" items="${salesrank }" varStatus="vs">
			    					<tr align="center">
			    						<td align="center"><font size="12" style="font-family: Arial Black; color: red;">${vs.count }</font></td>
			    						<td width="20%" height="40%" align="center">
			    							<img src="<%=path %>/images/${sr[1]}">
			    						</td>
			    						<td width="30%">
			    							<a href="<%=path %>/BookServlet?whichrequest=singlebook&bisbn=${sr[0]}">
			    							${sr[2] }<br></a>
			    							作者：${sr[3] }<br>
			    							出版社：${sr[4]}<br>
			    							价钱：${sr[5] }￥<br>
			    						</td>
			    						<td>${sr[6] }本</td>
			    						<td>
			    							<a href="<%=path %>/OrderServlet?whichrequest=buy&isbn=${b.isbn}">购买</a>
			    						</td>
			    					</tr>
			    				</c:forEach>
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
