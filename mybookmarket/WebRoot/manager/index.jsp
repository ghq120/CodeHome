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
	<link rel="stylesheet" type="text/css" href="<%=path %>/jqueryui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/jqueryui/themes/icon.css">
	<script type="text/javascript" src="<%=path %>/jqueryui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/jqueryui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path %>/jqueryui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
	function addtab(pagename,title){
		//如果选项卡存在，选择选项卡
		var flag = $("#mytab").tabs('exists',title);
		if (flag) {
			//选中选项卡
			$("#mytab").tabs('select',title);
		} else {
			//否则创建选项卡
			var tabContent = "<iframe width='100%' height='100%' src= '"+pagename+"'></iframe>";
			//创建选项卡
			$("#mytab").tabs('add',{
				title: title,
				content: tabContent,
				closable: true
			})
		}
	}
	</script>

  </head>
  
  <body class="easyui-layout">  
    <div data-options="region:'north',title:'图书后台管理系统',split:true" style="height:100px;"></div>  
    <div data-options="region:'west',title:'后台管理区',split:true" style="width:200px;">
    	<div id="aa" class="easyui-accordion" style="width:300px;height:300px;">  
			    <div title="公司产品" data-options="iconCls:'icon-save'" style="overflow:auto;padding:20px;">  
			        <div>
			        	<a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true"
			        	onclick="addtab('<%=path %>/manager/bookTypelist.jsp','类型管理')">类型管理</a>  
			        </div>
			        <div>
			        	<a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true"
			        	onclick="addtab('<%=path %>/manager/booklist.jsp','图书管理')">图书管理</a>  
			        </div>
			        <div>
			        	<a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">公告管理</a>  
			        </div>
			    </div>  
			    <div title="公司顾客" data-options="iconCls:'icon-reload'" style="padding:10px;">  
							<div>
			        	<a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-man',plain:true">顾客管理</a>  
			        </div>
			        <div>
			        	<a id="btn" class="easyui-linkbutton" data-options="iconCls:'icon-large-chart',plain:true">订单管理</a>  
			        </div>
			    </div>  
			</div>  
    </div>  
    <div data-options="region:'center',title:'数据显示区'" style="padding:5px;background:#eee;">
    	<div id="mytab" class="easyui-tabs" fit= "true"></div>
    </div>  
	</body>  
</html>
