<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'booklist.jsp' starting page</title>
    
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
		$(function(){
			var myurl = "<%=path%>/BookServlet?whichrequest=delbook";
			//为删除按钮添加处理函数
			$("#delid").click(function(){
				//获得用户选择的所有行
				var rows =  $("#booklist").datagrid('getSelections');
				//如果没有选择行，则提示选择
				if (rows.length <= 0) {
					alert("请选择您要删除的记录");
				}else{
					//获取要删除行的标志，拼接在URL中
					for(i in rows){
						var isbn = rows[i].isbn;
						myurl += "&isbn="+isbn;
					}
					//判断是否要删除行
					if (window.confirm("确定要删除这些书籍吗？")) {
						$.ajax({
							type:"post",
							url:myurl,
							data:{},
							success:function(res){
								if (res == "1") {
									alert("删除成功");
									//刷新表格的数据， load始终展示的是第一页，reload展示当前页
									$('#booklist').datagrid('reload');
								}else{
									alert("删除失败");
								}
							},
							error:function(){
								window.location.href="<%=path%>/building.jsp";
							}
						})
					}
				}
			});
			
			//为修改按钮添加处理函数
			$("#updateid").click(function(){
				//判断用户选择的记录条数是否合法
				var rows =  $("#booklist").datagrid('getSelections');
				if (rows.length <= 0) {
					alert("请选择您要修改的记录");
				}else if (rows.length > 1) {
					alert("只能选择一行");
					//清除所选择的所有行
					$("#booklist").datagrid('unselectAll');
				} else{
					//获取所选择行的标志
					var isbn = rows[0].isbn;
					//显示一个对话框
					$('#updatediv').dialog({
					    title: '修改图书',   
					    width: 400,
					    closable: false,   //是否显示关闭按钮。
					    closed: false,     //是否在初始化组件时关闭（隐藏）窗口
					    modal: true,
					    buttons:[{
					        text:'保存',
					        handler:function(){
					        	//提交表单
					        	$('#updateform').form('submit',{   
					        	    url: '<%=path %>/BookServlet?whichrequest=updatebook',   
					        	    onSubmit: function(){ 
					        	    		//返回值为true,表单字段合法，否则不合法
					        	        return $(this).form('validate');  
					        	    },   
					        	    success:function(res){   
					        	        if (res == "1") {
					        	        	  alert("修改图书成功");
						        	        //刷新表格的数据
						        	        $('#booklist').datagrid('load');
							        	      //清空表单中的数据
									            $('#updateform').form('clear');
							        	      //关闭对话框
										        	$('#updatediv').dialog({
										        		closed: true
										        	});
					        	        } else{
					        	        	alert("修改图书失败");
					        	        }  
					        	    }   
					        	}); 
					        }
					      },{
					        text:'关闭',
					        handler:function(){
					        	//清空表单中的数据
				            $('#updateform').form('clear');
					        	//关闭对话框
					        	$('#updatediv').dialog({
					        		closed: true
					        	});
					        }
					      }]
					});
				}
						//表单中加载了该行的数据
					$('#updateform').form('load','<%=path%>/BookServlet?whichrequest=querybookwithisbn&isbn='+isbn);
					/* $('#updateform').form('load',{
						
						isbn: rows[0].isbn,
						bookName: rows[0].bookName,
						bookType: rows[0].bookType,
						bookPrice: rows[0].bookPrice,
						writer: rows[0].writer,
						publisher: rows[0].publisher,
						cover: rows[0].cover,
						isCommend: rows[0].isCommend,
						pubTime: rows[0].pubTime,
						introduce: rows[0].introduce
							
					}); */
				
			});
			
			//为新增按钮添加单击事件处理函数
			$("#addid").click(function(){
				$('#adddiv').dialog({
				    title: '新增类型',   
				    width: 400,
				    closable: false,   //是否显示关闭按钮。
				    closed: false,     //是否在初始化组件时关闭（隐藏）窗口
				    modal: true,
				    buttons:[{
				        text:'保存',
				        handler:function(){
				        	//提交表单
				        	$('#addform').form('submit',{   
				        	    url: '<%=path %>/BookTypeServlet?whichrequest=addType',   
				        	    onSubmit: function(){   
				        	        return $(this).form('validate');  
				        	    },   
				        	    success:function(res){   
				        	        if (res == "1") {
				        	        	  alert("添加类型成功");
				        	        //清空表单中的数据
				        	        $('#addform').form('clear');
				        	        //刷新表格的数据
				        	        $('#typelist').datagrid('load');
				        	        } else{
				        	        	alert("添加类型失败");
				        	        }  
				        	    }   
				        	}); 
				        }
				      },{
			          text:'重置',
			          handler:function(){
			        	  //清空表单中的数据
                  $('#addform').form('clear');     
			          }
			        },{
				        text:'关闭',
				        handler:function(){
				        	//清空表单中的数据
			            $('#addform').form('clear');
				        	//关闭对话框
				        	$('#adddiv').dialog({
				        		closed: true
				        	});
				        }
				      }]
				});   

			});
			
			
			
			//创建数据表格
			$('#typelist').datagrid({   
			    url:'<%=path%>/BookTypeServlet?whichrequest=alltype',   
			    columns:[[   
			        {field:'id',title:'id',width:100,align:'center',checkbox:true},   
			        {field:'typeName',title:'类型名',width:100,align:'center'},   
			        {field:'typeDesc',title:'类型描述',width:100,align:'center'}
			        
			    ]],
			    fitColumns:true,	//自动使列适应表格宽度以防止出现水平滚动
			    title:'类型列表',		//表格的标题
			    striped:true, 		//交替显示行背景
			    rownumbers:true,	//显示行数
			    fit: true,			//铺满整个页面
			    pagination:true,	//在数据表格底部显示分页工具栏
			    toolbar:'#tb'
			});
			
		})
	</script>
	
  </head>
  
  <body>
    <!-- 数据表格 -->
      <table id="typelist"></table>
    <!-- 数据表格的工具栏 -->
    <div id="tb">
      <!-- 连接按钮 -->
    	<div>
    		<a class="easyui-linkbutton" data-options="iconCls:'icon-add'" id="addid">新增</a>&nbsp;&nbsp;
    		<a class="easyui-linkbutton" data-options="iconCls:'icon-edit'" id="updateid">修改</a>&nbsp;&nbsp;
    		<a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" id="delid">删除</a>
    	</div>
    </div>
      <!-- 新增对话框 --> 
    	<div id="adddiv" >
    		<form id="addform" method="post" >
    			<table align="center">
		    		<tr>
		    		  <td><label for="typeName">类型名:</label></td>
              <td><input class="easyui-validatebox" type="text" name="typeName" data-options="required:true" /></td>
            </tr>
            <tr>
              <td><label for="typeDesc">类型描述:</label></td> 
              <td><input class="easyui-validatebox" type="text" name="typeDesc" data-options="required:true" /></td>
            </tr>
    			</table>
    		</form>
    	</div>
    	<!-- 修改对话框 --> 
    	<div id="updatediv" >
    		<form id="updateform" method="post" >
    			<table align="center">
		    		<tr>
		    		  <td><label for="typeName">类型名:</label></td>
              <td><input class="easyui-validatebox" type="text" name="typeName" data-options="required:true" /></td>
            </tr>
            <tr>
              <td><label for="typeDesc">类型描述:</label></td> 
              <td><input class="easyui-validatebox" type="text" name="typeDesc" data-options="required:true" /></td>
            </tr>
    			</table>
    		</form>
    	</div>
  </body>
</html>
