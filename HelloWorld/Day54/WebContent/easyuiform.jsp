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
<script type="text/javascript" src="/Day53/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>

<body>
	<form id="ff" method="post">   
	    <div>   
	        <label for="name">Name:</label>   
	        <input class="easyui-validatebox" type="text" name="name" data-options="required:true" />   
	    </div>   
	    <div>   
	        <label for="email">Email:</label>   
	        <input class="easyui-validatebox" type="text" name="email" data-options="validType:'email'" />   
	    </div>   
	</form>  
	<button name="btn1" onclick="func01()">提交方式1</button> 
	<button name="btn1" onclick="func02()">提交方式2</button> 
	<button name="btn1" onclick="func03()">载入</button> 
	<button name="btn1" onclick="func04()">清除</button> 
</body>

<script type="text/javascript">
	$('#ff').form({    
	    url:'myform.jsp',    
	    onSubmit: function(){    
	        // do some check    
	        // return false to prevent submit;    
	    },    
	    success:function(data){    
	        alert(data);    
	    }    
	}); 
	//Ajax提交方式1
	function func01(){
		$('#ff').submit(); 
	}
	//Ajax提交方式2
	function func02(){
		$('#ff').form('submit', {    
		    url:'myform.jsp',    
		    onSubmit: function(){    
		        // do some check    
		        // return false to prevent submit;    
		    },    
		    success:function(data){    
		        alert(data);    
		    }    
		}); 
	}
	//载入
	function func03(){
		$('#ff').form('load',{
			name:'xiaohei',
			email:'xiaohei@mygf.com',
		});
	}
	//清除
	function func04(){
		$('#ff').form('clear');
	}
</script>
</html>