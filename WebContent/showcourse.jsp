<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>游客后台</title>
<meta http-equiv="X-UA-Compatible" content="edge" />
<link rel="shortcut icon" href="./images/logo2.jpg" />

<!--
    JQuery EasyUI 1.5.x of Insdep Theme 1.0.0
    演示地址：https://www.insdep.com/example/
    下载地址：https://www.insdep.com
    问答地址：https://bbs.insdep.com

    项目地址：http://git.oschina.net/weavors/JQuery-EasyUI-1.5.x-Of-Insdep-Theme

    QQ交流群：184075694 （优先发布更新主题及内测包）
-->
<!--
    注意样式表优先级
    主题样式必须在easyui组件样式后。
-->

<link href="./themes/insdep/easyui.css" rel="stylesheet" type="text/css">


<!--
    ../themes/insdep/easyui_animation.css
    Insdep对easyui的额外增加的动画效果样式，根据需求引入或不引入，此样式不会对easyui产生影响
-->
<link href="./themes/insdep/easyui_animation.css" rel="stylesheet" type="text/css">

<!--
    ../themes/insdep/easyui_plus.css
    Insdep对easyui的额外增强样式,内涵所有 insdep_xxx.css 的所有组件样式
    根据需求可单独引入insdep_xxx.css或不引入，此样式不会对easyui产生影响
-->
<link href="./themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">

<!--
    ../themes/insdep/insdep_theme_default.css
    Insdep官方默认主题样式,更新需要自行引入，此样式不会对easyui产生影响
-->
<link href="./themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">

<!--
    ../themes/insdep/icon.css
    美化过的easyui官方icons样式，根据需要自行引入
-->
<link href="./themes/insdep/icon.css" rel="stylesheet" type="text/css">

<!--
    2017/03/22 新增
    ../plugin/font-awesome-4.7.0/css/font-awesome.min.css
    第三方图标库样式，用于显示左侧菜单栏图标，根据需要自行引入
-->
<link href="./plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="./themes/insdep/jquery.insdep-extend.min.js"></script>

</head>
<body>
	<table id="dg" class="easyui-datagrid" title="课程列表" style="width:100%;height:auto"
							data-options="rownumbers:true,singleSelect:true,pagination:true,url:'./SystemServlet?method=getElectiveJson',method:'get'">
						<thead>
							<tr>
								<th data-options="field:'course_id',width:80">课程号</th>
								<th data-options="field:'course_name',width:100">课程名称</th>
								<th data-options="field:'class_time',width:100,formatter:function(value,row){
							if (value == 1) {
								return '星期一';
							} else if (value == 2) {
								return '星期二';
							} else if (value == 3) {
								return '星期三';
							} else if (value == 4) {
								return '星期四';
							} else if (value == 5) {
								return '星期五';
							} else if (value == 6) {
								return '星期六上';
							} else if (value == 7) {
								return '星期六下';
							} else if (value == 8) {
								return '星期六晚';
							} else if (value == 9) {
								return '星期天上';
							} else if (value == 10) {
								return '星期天下';
							} else if (value == 11) {
								return '星期天晚';
							} 
						}">上课时间</th>
								<th data-options="field:'class_place',width:100">上课教室</th>
								<th data-options="field:'course_teac',width:100">任课老师</th>
								<th data-options="field:'course_total',width:100">课程容量</th>
								<th data-options="field:'total',width:100">当前段可选</th>
								<th data-options="field:'nexttotal',width:100">下阶段可选</th>
							</tr>
						</thead>
					</table>
					
		<script type="text/javascript">
		$(function(){
			$.post("./SystemServlet?method=isOpen",function(data){
				 var data = eval('(' + data + ')');  // change the JSON string to javascript object      
	               if (data.success){  
	                   if (data.message == false) {
	                   	$.messager.alert('警告', '当前无选课计划');
	                   }  
	               }  
			  });
		});
		</script>
</body>
</html>