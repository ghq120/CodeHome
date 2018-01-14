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
	<%-- $(function(){
		$("#search").on("click",function(){
			var typeValue = $("#type").val();
			$.ajax({
				type:"post",
				url:"<%=path %>/BookTypeServlet",
				data:{"whichrequest":"gettypebook","typev":typeValue},
				dataType:"json",
				success:function(resText){
					$("tr").remove("#delid");
					//让表格显示
					$("#tid").css("display","block");
					//json对象组成的数组长度 <=0;
					if (resText.length <= 0 ) {
						alert("没有查找到书籍");
						return;
					}
					for(var i = 0; i < resText.length; i++){
						var row = $("<tr></tr>");
						row.attr("id","delid");
						//var cell1 = $("<td></td>");
						//cell1.html(<img src="<%=path %>/images/resText[i].cover">);
						var cell2 = $("<td></td>");
						cell2.html(resText[i].bookName);
						var cell3 = $("<td></td>");
						cell3.html(resText[i].writer);
						var cell4 = $("<td></td>");
						cell4.html(resText[i].publisher);
						var cell5 = $("<td></td>");
						cell5.html(resText[i].bookPrice);
						
						//row.append(cell1);
						row.append(cell2);
						row.append(cell3);
						row.append(cell4);
						row.append(cell5);
						$("#tid").append(row);
					}
				},
				error:function(){
					window.location.href="<%=path %>/building.jsp";
				}
			});
		});
	});
		 --%>
	</script>
  </head>
  
  <body>
    
    <table width="100%" height="100%" >
    	<tr>
    		<!-- 顶部信息显示 -->
    		<td colspan="2" height="10%" bgcolor="#E0FFFF">
    			<jsp:include page="top.jsp"></jsp:include>
    		</td>
    	</tr>
    	<tr bgcolor="#FAFAD2">
    		<!-- 左边信息显示 -->
    		<td width="25%" align="center">
    			<font size="6">图书类型</font><br>
	    			<form action="<%=path %>/BookTypeServlet?whichrequest=gettypebook" method="post">
	    			<select name="type" id="type">
						<option value="">请选择</option>
						<c:forEach var="bt"  items="${btype }" >
							<option value="${bt }">${bt }</option>
						</c:forEach>
					</select>
					<input type="submit" value="查询" id="search">
					</form>
    		</td>
    		<!-- 图书信息展示 -->
    		<td>
    		<table height="10%" width="100%" >
    		</table>
    			<c:forEach var="tb" items="${typebook }">
				<table width="100%" height="30%" >
   					<tr>
   						<td width="20%" height="40%" align="center">
   							<img src="<%=path %>/images/${tb.cover}">
   						</td>
   						<td width="30%">
   							<a href="<%=path %>/BookServlet?whichrequest=singlebook&bisbn=${tb.isbn}">
   							${tb.bookName }<br></a>
   							作者：${tb.writer }<br>
   							出版社：${tb.publisher }<br>
   							价钱：${tb.bookPrice }￥<br>
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
    		<td colspan="2" height="10%" bgcolor="#E0FFFF"> 
    			<jsp:include page="buttom.jsp"></jsp:include>
    		</td>
    	</tr>
    </table>
  </body>
</html>
