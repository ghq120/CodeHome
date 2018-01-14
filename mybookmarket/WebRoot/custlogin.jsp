<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'custlogin.jsp' starting page</title>
    
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
	<%-- function changeCode(img){
		//$(img).removeAttr("src");
		$(img).attr("src","<%=path%>/PicCodeServlet?t"+Math.random());
	} --%>
	function changeCode(){
		//$(img).removeAttr("src");
		$("#img").attr("src","<%=path%>/PicCodeServlet?whichrequest=getCode&t"+Math.random());
	}
	function judgecode(){
		var chcode = $("input[name='checkcode']").val();
		$.ajax({
			type:"post",
			url:"<%=path%>/PicCodeServlet",
			data:"whichrequest=checkcode&code="+chcode,
			success:function(resText){
				if (resText == "") {
					$("#spanid").html(resText);
				}else {
					$("#spanid").html(resText);
					changeCode();
				}
			},
			error:function(){
				window.location.href="<%=path%>/building.jsp";
			}
		});
	}
	function judgename(name){
		if ($.trim(name).length <= 0) {
			$("#spanname").html("用户名不能为空");
		}else {
			$("#spanname").html(" ");
		}
	}
	function judgepass(pass){
		if ($.trim(pass).length <= 0) {
			$("#spanpass").html("密码不能为空");
		}else {
			$("#spanpass").html(" ");
		}
	}
	
	function login(){
		var name = $('input[name="custname"]').val();
		var pass = $('input[name="custpass"]').val();
		var code = $('input[name="checkcode"]').val();
		var span = $("#spanid").html();
		
		if ($.trim(name).length <= 0) {
			alert("用户名不能为空");
			return;
		}
		if ($.trim(pass).length <= 0) {
			alert("密码不能为空");
			return;
		}
		if ($.trim(code).length <= 0) {
			alert("验证码不能为空");
			return;
		}
		if (span == "验证码不正确") {
			alert("验证码不正确");
			return;
		}
		
		$.ajax({
			type:"post",
			url:"<%=path%>/CustomerServlet?whichrequest=customerlogin",
			data:$("form").serializeArray(),
			success:function(resText){
				if (resText == "用户名或密码不正确") {
					alert(resText);
					return;
				};
				window.location.href="<%=path%>/index.jsp";
			},
			error:function(){
				window.location.href="<%=path%>/building.jsp";
			}
		});
	}
	$(function(){
		$('input[name="custname"]').focus();
	})
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
		   		<form>
			   		<table align="center" > 
			   			<tr>
			   				<td colspan="2">${tip}</td>
			   			</tr>
			   			<tr>
			   				<td>用户名:</td>
			   				<td><input type="text" name="custname" onblur="judgename(this.value)"><span id="spanname"></span></td>
			   			</tr>
			   			<tr>
			   				<td>密码:</td>
			   				<td><input type="password" name="custpass" onblur="judgepass(this.value)"><span id="spanpass"></span></td>
			   			</tr>
			   			<tr>
			   				<td>验证码:</td>
			   				<td><input type="text" name="checkcode" onblur="judgecode(this.value)">
			   				<img src="<%=path%>/PicCodeServlet?whichrequest=getCode" onclick="changeCode()" id="img"/><span id="spanid"></span></td>
			   			</tr>
			   			<tr>
			   				<td colspan="2" align="center">
			   				<input type="button" value="登录" id="loginbuttonId" onclick="login()">&nbsp;&nbsp;
			   				<input type="reset" value="重置" />
			   				</td>
			   			</tr>
			   		</table>
		        </form>
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
