<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		td.rightborder{
			border-right: 1px black solid;
			text-align: center;
			width: 12.8%;
		}
	</style>
	<script type="text/javascript" src="<%=path %>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
	function tip(){
		alert("必须先退出登录再注册用户");
		var flag = confirm("是否退出当前用户");
		if (flag) {
			window.location.href="<%=path %>/CustomerServlet?whichrequest=logout";
			<%-- window.location.href="<%=path %>/register.jsp"; --%>
		}
		
	}
	</script>
  </head>
  
  <body>
    <table align="center" width="80%" height="100%" >
    	<tr>
    		<td class="rightborder"><a href="">首页</a></td>
   			<td class="rightborder"><a href="<%=path %>/BookTypeServlet?whichrequest=getalltype">图书分类</a></td>
   			<td class="rightborder">
   				<c:choose>
   					<c:when test="${cust == null }">
   						<a href="custlogin.jsp">购物车</a>
   					</c:when>
   					<c:otherwise>
   						<a href="<%=path %>/cart.jsp">购物车</a>
   					</c:otherwise>
   				</c:choose>
   			</td>
   			<td class="rightborder">
   				<c:choose>
   					<c:when test="${cust == null }">
   						<a href="custlogin.jsp">订单查询</a>
   					</c:when>
   					<c:otherwise>
   						<a href="<%=path %>/OrderServlet?whichrequest=showorder">订单查询</a>
   					</c:otherwise>
   				</c:choose>
   			</td>
   			<td class="rightborder"><a href="<%=path %>/OrderServlet?whichrequest=salesrank">销量排行</a></td>
   			<td class="rightborder">
   				<c:choose>
   					<c:when test="${cust == null }">
   						<a href="register.jsp">用户注册</a>
   					</c:when>
   					<c:otherwise>
   						<a onclick="tip()">用户注册</a>
   						<%-- <a href="<%=path %>/CustomerServlet?whichrequest=logout">用户注册</a> --%>
   					</c:otherwise>
   				</c:choose>
   			</td>
   			<td align="center">
   				<c:choose>
   					<c:when test="${cust == null }">
   						<a href="custlogin.jsp">用户登录</a>
   					</c:when>
   					<c:otherwise>
   						欢迎，${cust.custName }&nbsp;
   						<a href="<%=path %>/CustomerServlet?whichrequest=logout">注销</a>
   					</c:otherwise>
   				</c:choose>
   			</td>
    	</tr>
    </table>
  </body>
</html>
