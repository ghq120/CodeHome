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
	function judgename(name){
		if ($.trim(name).length <= 0) {
			$("#spanname").html("用户名不能为空");
			return;
		}else {
			$("#spanname").html("");
		}
		
		var re = /^[a-zA-Z]+[0-9a-zA-Z]{2,7}$/;
		if(!re.test(name)) {
			$("#spanname").html("用户必须英文开头，长度3-8位");
			return;
		}else if($.trim(name).length > 8){
			$("#spanname").html("用户名长度超过8位");
			return;
		}else {
			$("#spanname").html(" ");
		}
		
		$.ajax({
			type:"post",
			url:"<%=path %>/CustomerServlet",
			data:"whichrequest=queryname&name="+name,
			success:function(resText){
				$("#spanname").html(resText);
			},
			error:function(){
				window.location.href="<%=path%>/building.jsp";
			}
		})
	}
	
	function judgerealname(realname){
		if ($.trim(realname).length <= 0) {
			$("#spanrealname").html("真实姓名不能为空");
			return;
		}else if($.trim(realname).length >= 6){
			$("#spanrealname").html("请正确输入姓名");
			return;
		}else{
			$("#spanrealname").html("");
		}
		
		var re = /^[\u0391-\uFFE5]+$/;
		if (!re.test(realname)) {
			$("#spanrealname").html("请输入中文");
			return;
		} else{
			$("#spanrealname").html("");
		}
		
	}
	function judgepass(pass){
		if ($.trim(pass).length <= 0) {
			$("#spanpass").html("密码不能为空");
			return;
		}else {
			$("#spanpass").html("");
		}
		
		var re = /^[a-zA-Z]+[0-9a-zA-Z]{5,7}$/;
		if (!re.test(pass)) {
			$("#spanpass").html("密码必须以字母开头,长度为6-8位");
			return;
		}else {
			$("#spanpass").html(" ");
		}
		
	}
	function judgeconfpass(confpass){
		if ($.trim(confpass).length <= 0) {
			$("#spanconfpass").html("确认密码不能为空");
			return;
		}else {
			$("#spanconfpass").html("");
		}
		var pass = $('input[name="cpass"]').val();
		if (!(pass == confpass)) {
			$("#spanconfpass").html("两次密码不一致");
		}
		
	}
	function judgephone(phone){
		if ($.trim(phone).length <= 0) {
			$("#spanphone").html("手机号不能为空");
			return;
		}else {
			$("#spanphone").html("");
		}
		
		var re = /^1\d{10}$/;
		if (!re.test(phone)) {
			$("#spanphone").html("手机号格式不正确");
			return;
		}else {
			$("#spanphone").html(" ");
		}
		
		
	}
	function judgeemail(email){
		if ($.trim(email).length <= 0) {
			$("#spanemail").html("邮箱不能为空");
			return;
		}else {
			$("#spanemail").html("");
		}
		
		var re = /^\w+@\w+.com+$/;
		if (!re.test(email)) {
			$("#spanemail").html("E-mail格式不正确");
			return;
		}else {
			$("#spanemail").html(" ");
		}
		
		
	}
	function register(){
		var name = $('input[name="cname"]').val();
		var realname = $('input[name="realname"]').val();
		var pass = $('input[name="cpass"]').val();
		var confirmpass = $('input[name="confirmpass"]').val();
		var phonenum = $('input[name="phonenum"]').val();
		var email = $('input[name="email"]').val();
		
		if ($.trim(name).length <= 0) {
			alert("用户名不能为空");
			return;
		}
		if ($.trim(realname).length <= 0) {
			alert("真实姓名不能为空");
			return;
		}
		if ($.trim(pass).length <= 0) {
			alert("密码不能为空");
			return;
		}
		if ($.trim(confirmpass).length <= 0) {
			alert("确认密码不能为空");
			return;
		}
		if ($.trim(phonenum).length <= 0) {
			alert("手机号不能为空");
			return;
		}
		if ($.trim(email).length <= 0) {
			alert("邮箱不能为空");
			return;
		}
		
		var spanname = $("#spanname").val();
		var spanrealname = $("#spanrealname").val();
		var spanpass = $("#spanpass").val();
		var spanconfpass = $("#spanconfpass").val();
		var spanphone = $("#spanphone").val();
		var spanemail = $("#spanemail").val();
		
		if (spanname == "" && spanrealname == "" && spanpass == "" && spanconfpass == "" && spanphone == "" && spanemail == "") {
			$.ajax({
				type:"post",
				url:"<%=path %>/CustomerServlet?whichrequest=register",
				data:$("form").serializeArray(),
				success:function(resText){
					if (resText == "注册失败") {
						alert(resText);
						return;
					}
					window.location.href="<%=path%>/index.jsp";
					
				},
				error:function(){
					window.location.href="<%=path%>/building.jsp";
				}
			})
		}
		
	}
	$(function(){
		$('input[name="cname"]').focus();
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
    			<form >
    				<table align="center" >
    					<tr>
    						<td>用户名：</td>
    						<td><input type="text" name="cname" onblur="judgename(this.value)" placeholder="用户名长度为3~8">
    						<span id="spanname"></span></td>
    					</tr>
    					<tr>
    						<td>真实姓名：</td>
    						<td><input type="text" name="realname" onblur="judgerealname(this.value)" placeholder="请输入真实姓名">
    						<!-- <input onkeyup="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" 
    							   onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))" 
    							   name="realname" onblur="judgerealname(this.value)" placeholder="请输入真实姓名"> -->
    						<span id="spanrealname"></span></td>
    					</tr>
    					<tr>
    						<td>密码：</td>
    						<td><input type="password" name="cpass" onblur="judgepass(this.value)" placeholder="密码必须是字母开头，数字与字母组合">
    						<span id="spanpass"></span></td>
    					</tr>
    					<tr>
    						<td>确认密码：</td>
    						<td><input type="password" name="confirmpass" onblur="judgeconfpass(this.value)" placeholder="必须和密码一致">
    						<span id="spanconfpass"></span></td>
    					</tr>
    					<tr>
    						<td>手机号：</td>
    						<td><input type="text" name="phonenum" onblur="judgephone(this.value)" placeholder="手机号长度为11位">
    						<span id="spanphone"></span></td>
    					</tr>
    					<tr>
    						<td>邮箱：</td>
    						<td><input type="text" name="email" onblur="judgeemail(this.value)" placeholder="xxx@xx.com">
    						<span id="spanemail"></span></td>
    					</tr>
    					<tr>
    						<td colspan="2" align="center">
    							<input type="button" value="注册" onclick="register()">&nbsp;&nbsp;&nbsp;
    							<input type="reset" value="重置">
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
