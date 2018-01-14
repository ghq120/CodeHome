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
	function sumprice(num,bisbn){
		if (num == "") {
			alert("请输入数量");
			return;
		}
		window.location.href="<%=path %>/OrderServlet?whichrequest=totalprice&num="+num+"&isbn="+bisbn;
	}
	function tip(){
		alert("购物车为空，快去购买商品！！！");
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
    		<!-- 购物车展示 -->
    		<td>
    		<table width="100%" height="100%" border="1">
    			<tr height="10%">
    				<td colspan="3" align="left">
    					<font size="20" >购物车</font>
    				</td>
    			</tr>
    			<tr>
    				<td colspan="3">
    					<table width="100%" border="1">
		    				<tr bgcolor="gray" height="30px" align="center">
		    					<td>编号</td>
		    					<td>isbn</td>
		    					<td>图片</td>
		    					<td>书名</td>
		    					<td>单价</td>
		    					<td>数量</td>
		 						<td>总价</td>
		 						<td>操作</td>
		    				</tr>
		    				<c:if test="${ empty cart.keySet() }">
	    						<tr>
	    							<td colspan="8" align="center">
	    								<font size="6">购物车为空，快去...</font>
	    								<a href="<%=path %>/index.jsp"><font size="6">添加商品</font></a>
	    							</td>
	    						</tr>
    						</c:if>
		    				<c:forEach var="book" items="${cart.keySet() }" varStatus="vs">
		    					<tr align="center">
		    						<td>${vs.count }</td>
		    						<td>${book.isbn }</td>
		    						<td><img height="80px" width="80px" src="<%=path %>/images/${book.cover}"></td>
		    						<td>${book.bookName }</td>
		    						<td>${book.bookPrice }</td>
		    						<%-- <td><input size="1" type="text" name="num" value="${cart.get(book) }" onblur="sumprice(this.value,'${book.isbn }')"></td> --%>
		    						<td><input type="text" size="1" name="num" onkeyup="this.value=this.value.replace(/\D/g,'')" 
				    					onafterpaste="this.value=this.value.repalce(/\D/g,'')" value="${cart.get(book) }" onblur="sumprice(this.value,'${book.isbn }')"></td>
		    						<td>${cart.get(book)*book.bookPrice }</td>
		    						<td><a href="<%=path %>/OrderServlet?whichrequest=remove&isbn=${book.isbn}">删除</a></td>
		    					</tr>
		    				</c:forEach>
    					</table>
    				</td>
    			</tr>
    			<tr>
    				<td align="right" colspan="3" height="8%">
    					<font size="5px" color="red">总金额：${sum }</font>
    				</td>
    			</tr>
    			<tr height="10%" align="center">
    				<td>
    					<input type="button" value="继续购物" style="font-size: 20px" onclick="window.location.href='<%=path %>/index.jsp'">
    				</td>
    				<td>
    					<c:choose>
    						<c:when test="${empty cart }">
    							<input type="button" value="清空购物车" style="font-size: 20px" onclick="tip()">
    						</c:when>
    						<c:otherwise>
    							<input type="button" value="清空购物车" style="font-size: 20px" onclick="window.location.href='<%=path %>/OrderServlet?whichrequest=removeall'">
    						</c:otherwise>
    					</c:choose>
    					
    				</td>
    				<td>
    					<c:choose>
    						<c:when test="${empty cart }">
    							<input type="button" value="结算" style="font-size: 20px" onclick="tip()">
    						</c:when>
    						<c:otherwise>
    							<input type="button" value="结算" style="font-size: 20px" onclick="window.location.href='<%=path %>/buy.jsp'">
    						</c:otherwise>
    					</c:choose>
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
