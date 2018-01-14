<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    		<!-- 订单信息展示 -->
    		<td>
    			<table width="100%" height="100%">
    				<tr height="10%">
    					<td colspan="3" align="left">
    						<font size="20" >订单列表</font>
    					</td>
    				</tr>
    				<tr>
    					<td valign="top">
    						<table border="1" align="center" width="80%" >
			    				<tr bgcolor="gray" align="center" height="30px">
			    					<td>编号</td>
			    					<td>订单号</td>
			    					<td>商品总数量</td>
			    					<td>商品总金额</td>
			    					<td>订单时间</td>
			    					<td>订单状态</td>
			    					<td>备注</td>
			    					<td>折扣</td>
			    					<td>折后总价</td>
			    					<td>付款方式</td>
			    					<td>配送方式</td>
			    				</tr>
			    				<c:choose>
			    					<c:when test="${ empty order }">
			    						<tr>
			    							<td colspan="11" align="center">
			    								<font size="6">当前没有订单。。。</font>
			    								<a href="<%=path %>/index.jsp"><font size="6">去购物</font></a>
			    							</td>
			    						</tr>
			    					</c:when>
			    					<c:otherwise>
			    						<c:forEach var="ord" items="${order }" varStatus="vs">
					    					<tr align="center">
					    						<td>${vs.count }</td>
					    						<td><a href="<%=path %>/OrderServlet?whichrequest=orderitems&orderid=${ord.orderid}">${ord.orderid }</a></td>
					    						<td>${ord.ordernum }</td>
					    						<td>${ord.orderamount }</td>
					    						<td>${ord.orderdate }</td>
					    						<td>
					    							<c:if test="${ord.orderstate == 0 }">
					    								未付款
					    							</c:if>
					    							<c:if test="${ord.orderstate == 1 }">
					    								已付款
					    							</c:if>
					    							<c:if test="${ord.orderstate == 2 }">
					    								已发货
					    							</c:if>
					    						</td>
					    						<td>
					    							<c:choose>
					    								<c:when test="${ord.orderDesc == null }">
					    									无
					    								</c:when>
					    								<c:otherwise>${ord.orderDesc }</c:otherwise>
					    							</c:choose>
					    						</td>
					    						<td>
					    							<c:choose>
							   							<c:when test="${ord.rate == 1.0 }">
							   								无
							   							</c:when>
							   							<c:otherwise>
							   								<fmt:parseNumber value="${ord.rate *10}" pattern="#" />折
							   							</c:otherwise>
						   							</c:choose>
					    						</td>
					    						<td><fmt:formatNumber value="${ord.orderrateamount }" pattern="#.##" /></td>
					    						<td>${ord.paymet }</td>
					    						<td>${ord.sendmet }</td>
					    					</tr>
					    				</c:forEach>
			    					</c:otherwise>
			    				</c:choose>
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
