<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Day53/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/Day53/easyui/themes/icon.css">
	<script type="text/javascript" src="/Day53/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="/Day53/easyui/jquery.easyui.min.js"></script>
</head>
<body>
<div id="dd">Dialog Content</div> 
<button name="btn" value="刷新" onclick="func()">刷新</button>
<button name="btn01" value="open" onclick="func01()">打开</button>
<button name="btn02" value="close" onclick="func02()">关闭</button>
</body>
<script type="text/javascript">
$(function(){
	$('#dd').dialog({    
	    title: 'My Dialog',    
	    width: 400,    
	    height: 200,    
	    closed: false,    
	    cache: false,    
	    href: '/Day53/myeasyui.jsp',    
	    modal: false
	});
//页面加载
});
function func(){
	$('#dd').dialog('refresh', '/Day53/myeasyui.jsp');  
}
function func01(){
	$('#dd').dialog('open', '/Day53/myeasyui.jsp');  
}
function func02(){
	$('#dd').dialog('close', '/Day53/myeasyui.jsp');  
}
</script>
</html>