<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
  <script type="text/javascript">
  function judgename(name){
		if (name.length <= 0) {
			$("#namespan").html("用户名不能为空");
			return;
		}else {
			$("#namespan").html("");
			$.ajax({
				type:"post",
				url:"<%=path %>/UserServlet",
				data:"username="+name,
				success:function(resText){
					$("#namespan").html(resText);
				},
				error:function(){
					window.location.href = "<%=path %>/building.jsp";
				}
			});
		} 
		
  }
  </script>
  
  
  <body>
    <form action="<%=path %>/UserServlet" method="post">
    	用户名：<input type="text" name="username" onblur="judgename(this.value)"><span id="namespan"></span><br>
    	密码：<input type="password" name="userpass"><br>
    	<input type="submit" value="注册">
    </form>
  </body>
</html>
