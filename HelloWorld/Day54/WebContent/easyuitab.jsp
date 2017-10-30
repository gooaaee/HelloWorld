<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="/Day52/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/Day52/easyui/themes/icon.css">
	<script type="text/javascript" src="/Day52/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="/Day52/easyui/jquery.easyui.min.js"></script>
</head>
<body>

<!--
	<div class="easyui-tabs" style="width:700px;height:250px">
		<div title="About" style="padding:10px">
		</div>
		<div title="My Documents" style="padding:10px">
		</div>
		<div title="Help" data-options="iconCls:'icon-help',closable:true" style="padding:10px">
		</div>
	</div>
-->


	<button name="btn01" onclick="func01()">选择tab2</button>
	<button name="btn02" onclick="func02()">删除tab2</button>
	<div id="tt" class="easyui-tabs" style="width:700px;height:250px;">  
		<div title="Tab1" style="padding:20px;">   
	        tab1...    
		</div>
	</div>
	
</body>
<script type="text/javascript">
function func01(){
	var obj = $('#tt').tabs('getTab','Tab2'); 
	if(null == obj){
		$('#tt').tabs('add',{
			title:'Tab2',
			content:'Tab2...',
		}); 
	}else{
		$('#tt').tabs('select','Tab2'); 
	}
} 
function func02(){
	var obj = $('#tt').tabs('getTab','Tab2'); 
	if(null != obj){
		$('#tt').tabs('close','Tab2'); 
	}
} 
$(function(){
	$('#tt').tabs({    
	    border:true,    
	    onSelect:function(title){    
	        //alert(title+' is selected');   
	    }    
	});
});
</script>
</html>