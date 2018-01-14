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
    
    <title>My JSP 'left.jsp' starting page</title>
    
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
	$(function(){
		$("#vote").click(function(){
			
			$.ajax({
				type:"post",
				url:"<%=path %>/VoteServlet?whichrequest=dovote",
				data:$("form").serializeArray(),
				success:function(resText){
					alert(resText);
				},
				error:function(){
					window.location.href="<%=path %>/building.jsp";
				}
			});
		});
	});
	</script>
  </head>
  
  <body>
  <table align="left" width="100%" height="100%">
  <!-- 公告信息 -->
  	<tr>
  		<td height="50%">
  			<table width="100%" height="100%">
		    	<tr>
		    		<td align="center">网上公告</td>
		    	</tr>
		    	<c:forEach var="a" items="${advicelist }">
		    		<tr>
		    			<td align="center">
		    				${a.content }
		    			</td>
		    		</tr>
		    	</c:forEach>
	    	</table>
  		</td>
  	</tr>
  <!-- 投票信息 -->
  	<tr>
  		<td>
  			<form>
  			<table width="100%" height="100%">
  				<tr>
  					<td align="center" height="25%">网上调查</td>
  				</tr>
  				<tr>
  					<td align="center" height="50%">请选择您喜欢的图书类型:<br><br>
  						<c:forEach var="v" items="${votelist }" varStatus="vs">
  						<c:choose>
  							<c:when test="${vs.count ==1 }">
  								<input type="radio" name="selectvote" checked="checked" value="${v.id }">${v.typeName }<br>
  							</c:when>
  							<c:otherwise>
  								<input type="radio" name="selectvote" value="${v.id }">${v.typeName }<br>
  							</c:otherwise>
  						</c:choose>
  						</c:forEach><br>
  						<input type="button" value="投票" id="vote">&nbsp;
  						<input type="button" value="查看投票" onclick="window.location.href='<%=path %>/VoteServlet?whichrequest=showvote'">
  					</td>
  				</tr>
  			</table>
  			</form>
  		</td>
  	</tr>
  </table>
  </body>
</html>
