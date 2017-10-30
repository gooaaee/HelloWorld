<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
	.myClass01{
		border: 0px solid red;
		display: block; /*让当前元素转换为区块元素  */
		margin: 10px auto;
		width: 80%;
		background-color: #E4EEFF;
	}
	.myClass02{
		border: 0px solid red;
		display: block; /*让当前元素转换为区块元素  */
		margin: 10px auto;
		width: 80%;
		background-color: blue;
		color:white;
	}

</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>

<link rel="stylesheet" type="text/css" href="/Day54/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/Day54/easyui/themes/icon.css">
<script type="text/javascript" src="/Day54/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/Day54/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/Day54/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>

<body class="easyui-layout">   
	<!-- 北start -->
    <div data-options="region:'north',title:'',split:false" style="height:30px;background-image: url('/Day53/admin/images/layout-browser-hd-bg.gif');">
    	<span style="color:white;float:left;margin-left: 30px;margin-top: 4px">欢迎来到天天天才的小屋</span>
    	<span style="color:white;float:right;margin-right: 30px;margin-top: 4px">欢迎admin<a style="color:white;text-decoration:none;" href="#">退出</a></span>
    </div>   
	<!-- 北end -->
	<!-- 南start -->
    <div data-options="region:'south',title:'' " style="height:30px;background-color: #E4EFFF">
    	<div style="text-align:center;margin-top: 5px;font-weight: bold;">@Copyright郭攀杰版权所有</div>
    </div>
	<!-- 南end -->
	<!-- 西start -->
    <div data-options="region:'west',title:'',split:false" style="width:200px;">
	    <div id="aa" class="easyui-accordion" style="width:200px;height:auto;" data-options="fit:true">
			<div title="分类管理" data-options="" style="overflow: auto; padding: 10px;">
				<a href="/Day54/admin/category/category.html" class="myClass01">分类管理</a>
			</div>
			<div title="商品管理" data-options="" style="padding: 10px;">
				<a href="#">商品管理</a>
			</div>
			<div title="用户管理" data-options="">
				<a href="/Day54/index.jsp" class="myClass01">普通会员</a><br/>
				<a href="/Day54/admin/index.jsp" class="myClass01">白银会员</a><br/>
				<a href="/Day54/easyuiform.jsp" class="myClass01">黄金会员</a><br/>
				<a href="/Day54/easyuitest.jsp" class="myClass01">超凡大师会员</a><br/>
			</div>
		</div>
    </div>
	<!-- 西end -->
	<!-- 中间start -->
    <div data-options="region:'center',title:'' " style="padding:5px;">
	    <div id="tt" class="easyui-tabs"  data-options="fit:true" style="width:500px;height:250px;">   
			<div title="首页" data-options="" style="overflow:auto;padding:20px;">   
				这他妈的是首页...
			</div>  
			<!-- 
				<div title="普通会员" data-options="" style="overflow:auto;padding:20px;">
					<iframe src="/Day53/admin/index.jsp" style="width:100%;height:100%;border:0px;"></iframe>
				</div> 
			--> 
		</div>  
    </div>   
	<!-- 中间end -->
</body>
<script type="text/javascript">
	$(function(){
		$(".myClass01").mouseover(function(){$(this).toggleClass("myClass02");});
		$(".myClass01").mouseout(function(){$(this).toggleClass("myClass02");});
 		$(".myClass01").click(function(){
			var title = $(this).html();
			var url = this.href;
			var tab = $('#tt').tabs('getTab',title);
			console.log(title);
			console.log(url);
			//<iframe src="+ url + " style='width:100%;height:100%;border:0px;'></iframe>
			if(null == tab){//点击西部的链接,中部不存在对应的tab面板
				$('#tt').tabs('add',{
				    title:title,
				    content:"<iframe src="+ url + " style='width:100%;height:100%;border:0px;'></iframe>", 
				    closable:true
				});
			}else{//点击西部的链接,中部存在对应的tab面板
				$('#tt').tabs('select',title);
			}
			//阻止链接跳转
			return false;
		});
	});
</script>
</html>