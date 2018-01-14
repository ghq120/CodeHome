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
				<!-- 订单信息的展示 -->
				<table height="100%" width="100%">
					<tr height="10%">
						<td colspan="3" align="left">
    						<font size="20" >下单成功</font>
    					</td>
					</tr>
					<tr>
						<td align="center">
							<table border="1" width="70%" >
			   					<tr>
			   						<td>订单号</td>
			   						<td>${order.orderid}</td>
			   					</tr>	  
			   					<tr>
			   						<td>总数量</td>
			   						<td>${order.ordernum}</td>
			   					</tr>	  				
			   					<tr>
			   						<td>总金额</td>
			   						<td>${order.orderamount}</td>
			   					</tr>
			   							  				
			   					<tr>
			   						<td>折扣</td>
			   						<td>
			   						<c:choose>
			   							<c:when test="${order.rate == 1.0 }">
			   								无折扣
			   							</c:when>
			   							<c:otherwise>
			   								<fmt:parseNumber value="${order.rate *10}" pattern="#" />折
			   							</c:otherwise>
			   						</c:choose>
			   						</td>
			   					</tr>	 	  				
			   					<tr>
			   						<td>优惠后的金额</td>
			   						<td><fmt:formatNumber value="${order.orderrateamount}" pattern="#.##" /></td>
			   					</tr>		 	  				
			   					<tr>
			   						<td>订单时间</td>
			   						<td><fmt:formatDate value="${order.orderdate}" pattern="yyyy年MM月dd日 HH:mm:ss E"/></td>
			   					</tr>		 	  				
			   					<tr>
			   						<td>订单状态</td>
			   						<td>
			   							<c:choose>
			   								<c:when test="${order.orderstate==0}">未付款</c:when>
			   								<c:when test="${order.orderstate==1}">已付款</c:when>
			   								<c:otherwise>已发货</c:otherwise>
			   							</c:choose>
		
			   						</td>
			   					</tr>	  	     				
		   					</table>
						</td>
					</tr>
					<tr height="40%">
						<td valign="top" align="center">
							<table border="1" width="70%" >
	   						<tr>
		   						<td>isbn</td>
		   						<td>书名</td>
		   						<td>单价</td>
		   						<td>数量</td>
		   						<td>优惠后的单价</td>
		   						<td>金额</td>
		   						<td>优惠后的金额</td>
	   						</tr>
	   						<c:forEach var="book" items="${cart.keySet()}">
	   						<tr>
		   						<td>${book.isbn }</td>
		   						<td>${book.bookName}</td>
		   						<td>${book.bookPrice}</td>
		   						<td>${cart.get(book)}</td>
		   						<td><fmt:formatNumber value="${book.bookPrice*order.rate}" pattern="#.##" /></td>
		   						<td><fmt:formatNumber value="${book.bookPrice*cart.get(book)}" pattern="#.##" /></td>
		   						<td><fmt:formatNumber value="${book.bookPrice*cart.get(book)*order.rate}" pattern="#.##" /></td>
	   						</tr>
	   						</c:forEach>
	   						</table>
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="button" value="返回首页" style="font-size: 20px" onclick="window.location.href='<%=path %>/index.jsp'">
						</td>
					</tr>
				</table>
   	        	<!-- 订单项的展示 -->
	   				<c:remove var="cart" scope="session"/>
	   				<c:remove var="sum" scope="session"/>
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
