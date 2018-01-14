<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'pay.jsp' starting page</title>
    
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
  			<td id="tid" align="center">
				<table height="100%" width="100%">
					<tr height="10%">
						<td colspan="3" align="left">
	   						<font size="20" >订单详情</font>
	   					</td>
					</tr>
					<tr height="">
						<td  align="center" valign="top">
							<table border="1" width="70%" >
	   						<tr align="center" bgcolor="gray">
		   						<td>isbn</td>
		   						<td>图片</td>
		   						<td>书名</td>
		   						<td>单价</td>
		   						<td>数量</td>
		   						<td>总金额</td>
		   						<td>优惠后总金额</td>
	   						</tr>
	   						<c:forEach var="order" items="${order}">
	   						<tr align="center">
		   						<td>${order[0]}</td>
		   						<td><img src="<%=path %>/images/${order[1]}" ></td>
		   						<td>${order[2]}</td>
		   						<td>${order[3]}</td>
		   						<td>${order[4]}</td>
		   						<td>${order[5]}</td>
		   						<td><fmt:formatNumber value="${order[6]}" pattern="#.##" /></td>
	   						</tr>
	   						</c:forEach>
	   						</table>
						</td>
					</tr>
					<tr height="20%">
						<td align="center">
							<input type="button" value="返回上一页" style="font-size: 20px" onclick="javascript:history.back(-1);">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value="返回首页" style="font-size: 20px" onclick="window.location.href='<%=path %>/index.jsp'">
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
