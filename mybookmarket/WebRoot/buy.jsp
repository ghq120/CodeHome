<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'buy.jsp' starting page</title>
    
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
				$("#spanname").html("收货人不能为空");
			}else {
				$("#spanname").html(" ");
			}
		}
		function judgeadd(add){
			if ($.trim(add).length <= 0) {
				$("#spanadd").html("地址不能为空");
			}else {
				$("#spanadd").html(" ");
			}		
		}
		function judgetel(tel){
			if ($.trim(tel).length <= 0) {
				$("#spantel").html("手机号不能为空");
			}else {
				$("#spantel").html(" ");
			}
			var re = /^1\d{10}$/;
			if (!re.test(tel)) {
				$("#spantel").html("手机号格式不正确");
			}else {
				$("#spantel").html(" ");
			}
		}
		function judgeemail(email){
			if ($.trim(email).length <= 0) {
				$("#spanemail").html("E-mail不能为空");
			}else {
				$("#spanemail").html(" ");
			}
			var re = /^\w+@\w+.\w+$/;
			if (!re.test(email)) {
				$("#spanemail").html("E-mail格式不正确");
			}else {
				$("#spanemail").html(" ");
			}
		}
		function judgepostcode(postcode){
			if ($.trim(postcode).length <= 0) {
				$("#spanpostcode").html("邮编不能为空");
			}else {
				$("#spanpostcode").html(" ");
			}
			var re = /^\d{6}$/;
			if (!re.test(postcode)) {
				$("#spanpostcode").html("邮编格式不正确");
			}else {
				$("#spanpostcode").html(" ");
			}
		}
		function buy(){
			var name = $('input[name="custrealname"]').val();
			var add = $('input[name="custadd"]').val();
			var tel = $('input[name="custtel"]').val();
			var email = $('input[name="custemail"]').val();
			var postcode = $('input[name="custepostcode"]').val();
			
			if ($.trim(name).length <= 0) {
				alert("收货人姓名不能为空");
				return;
			}
			if ($.trim(add).length <= 0) {
				alert("地址不能为空");
				return;
			}
			if ($.trim(tel).length <= 0) {
				alert("手机不能为空");
				return;
			}
			if ($.trim(email).length <= 0) {
				alert("E-mail不能为空");
				return;
			}
			if ($.trim(postcode).length <= 0) {
				alert("邮编不能为空");
				return;
			}
			
			var spanname = $("#spanname").val();
			var spanadd = $("#spanadd").val();
			var spantel = $("#spantel").val();
			var spanemail = $("#spanemail").val();
			var spanpostcode = $("#spanpostcode").val();
			
			if (spanname == "" && spanadd == "" && spantel == "" && spanemail == "" && spanpostcode == "") {
				$.ajax({
					type:"post",
					url:"<%=path %>/OrderServlet?whichrequest=proorder",
					data:$("form").serializeArray(),
					success:function(resText){
						if (resText == "支付失败") {
							alert(resText);
							return;
						};
						window.location.href="<%=path%>/ordersuc.jsp";
					},
					error:function(){
						window.location.href="<%=path%>/building.jsp";
					}
				})
			}
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
    		<!-- 订单信息展示 -->
    		<td>
    			<form>
    			<table align="center" border="1" width="50%">
    				<tr>
    					<td>客户名:</td>
    					<td><input type="text" name="custname"  readonly="readonly" value="${cust.custName }"></td>
    				</tr>
    				<tr>
    					<td>收货人姓名:</td>
    					<td><input type="text" name="custrealname"  value="${cust.custRealName }" onblur="judgename(this.value)"><span id="spanname"></span> </td>
    				</tr>
    				<tr>
    					<td>地址:</td>
    					<td><input type="text" name="custadd"  value="${cust.custCity }${cust.custAdd }" onblur="judgeadd(this.value)"><span id="spanadd"></span></td>
    				</tr>
    				<tr>
    					<td>手机:</td>
    					<td><input type="text" name="custtel"  value="${cust.custTel }" onblur="judgetel(this.value)"><span id="spantel"></span></td>
    				</tr>
    				<tr>
    					<td>E-mail:</td>
    					<td><input type="text" name="custemail"  value="${cust.custEmail }" onblur="judgeemail(this.value)"><span id="spanemail"></span></td>
    				</tr>
    				<tr>
    					<td>邮编:</td>
    					<td><input type="text" name="custepostcode"  value="${cust.custPostcode }" onblur="judgepostcode(this.value)"><span id="spanpostcode"></span></td>
    				</tr>
    				<tr>
    					<td>付款方式:</td>
    					<td>
    						<select name="paymethod">
    							<option>银行卡</option>
    							<option>支付宝</option>
    							<option>微信</option>
    							<option>货到付款</option>
    						</select>
    					</td>
    				</tr>
    				<tr>
    					<td>配送方式:</td>
    					<td>
    						<select name="sendmethod">
    							<option>顺丰</option>
    							<option>韵达</option>
    							<option>圆通</option>
    							<option>EMS</option>
    						</select>
    					</td>
    				</tr>
    				<tr>
    					<td>备注</td>
    					<td>
    						<textarea name="bookdesc"></textarea>
    					</td>
    				</tr>
    				<tr>
    					<td colspan="2" align="center">
    						<input type="button" value="支付" onclick="buy()">
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
