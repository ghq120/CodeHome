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
  <script type="text/javascript">
  var xmlHttpReq;
  function getXMLHttpReq(){
	  if (window.XMLHttpRequest) {
		  xmlHttpReq = new XMLHttpRequest();
		} else {
			xmlHttpReq = new ActiveXObject("Micorsoft.XMLHTTP");
		}
	  
  }
  
  function judgename(name){
	  var nameSpan = document.getElementById("namespan");
	  
	  if( name.length <=0 ){
		  nameSpan.innerHTML = "用户名不能为空";
		  return;
	  }else {
		  getXMLHttpReq();
		  xmlHttpReq.onreadystatechange = function(){
			  if (xmlHttpReq.status == 200){
				  if (xmlHttpReq.readyState == 4 ) {
						nameSpan.innerHTML = xmlHttpReq.responseText;
					}
			  } else {
					window.location.href = "<%=path %>/building.jsp";
				}
		  };
	  }
	  
	  var url = "<%=path%>/UserServlet";
	  xmlHttpReq.open("post",url,true);
	  xmlHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	  xmlHttpReq.send("username="+name);
	  
	  <%-- var url = "<%=path %>/UserServlet?username="+name;
	  xmlHttpReq.open("get",url,true);
	  xmlHttpReq.send(); --%>
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
