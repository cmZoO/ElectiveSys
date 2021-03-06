<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理员后台</title>
<meta http-equiv="X-UA-Compatible" content="edge" />
<link rel="shortcut icon" href="../images/logo2.jpg" />

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

<link href="../themes/insdep/easyui.css" rel="stylesheet" type="text/css">


<!--
    ../themes/insdep/easyui_animation.css
    Insdep对easyui的额外增加的动画效果样式，根据需求引入或不引入，此样式不会对easyui产生影响
-->
<link href="../themes/insdep/easyui_animation.css" rel="stylesheet" type="text/css">

<!--
    ../themes/insdep/easyui_plus.css
    Insdep对easyui的额外增强样式,内涵所有 insdep_xxx.css 的所有组件样式
    根据需求可单独引入insdep_xxx.css或不引入，此样式不会对easyui产生影响
-->
<link href="../themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">

<!--
    ../themes/insdep/insdep_theme_default.css
    Insdep官方默认主题样式,更新需要自行引入，此样式不会对easyui产生影响
-->
<link href="../themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">

<!--
    ../themes/insdep/icon.css
    美化过的easyui官方icons样式，根据需要自行引入
-->
<link href="../themes/insdep/icon.css" rel="stylesheet" type="text/css">

<!--
    2017/03/22 新增
    ../plugin/font-awesome-4.7.0/css/font-awesome.min.css
    第三方图标库样式，用于显示左侧菜单栏图标，根据需要自行引入
-->
<link href="../plugin/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../themes/insdep/jquery.insdep-extend.min.js"></script>


</head>
<body>
	<table id="dg" class="easyui-datagrid" title="选课时间列表"
			style="width: 100%; height: auto"
			data-options="
				singleSelect: true,
				toolbar: '#tb',
				onClickRow: onClickRow,
				fitColumns: true,
				url:'../SystemServlet?method=getElectiveTimes'
			">
			<thead>
				<tr>
					<th width="80"
						data-options="field:'register_date',align:'center',
						editor:{
							type:'datebox',
							options:{
								required:true
							}
						}">可选课年级</th>
					<th width="120"
						data-options="field:'start_time',align:'center',editor:{type:'datetimebox',options:{required:true,showSeconds:false}}">
						选课开始时间
					</th>
					<th width="120"
						data-options="field:'end_time',align:'center',editor:{type:'datetimebox',options:{required:true,showSeconds:false}}">选课结束时间
					</th>
					<th width="120"
						data-options="field:'max',align:'center',editor:{type:'textbox',options:{required:true}}">
						可选课程个数
					</th>
				</tr>
			</thead>
		</table>
		<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">增加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">移除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">撤销</a>
		</div>
		<div style="padding: 5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" id="on-submit" style="width: 80px; float:right">插入计划</a>
		</div>
		<form id="ff" method="post" action="./adminServlet?method=timeChange">
			<input type="hidden" name="times" id="times" />
		</form>
	<script>
	$("#on-submit").click(function() {
		accept();
		
		var jsonstr="[]";
		var jsonarray = eval('('+jsonstr+')');
		
		for (var i = 0; i < $('#dg').datagrid('getRows').length; i++) {
			  $('#dg').datagrid('selectRow',i);// 关键在这里
			  
			var register_date = $('#dg').datagrid('getSelected').register_date;
			
			var start_time = $('#dg').datagrid('getSelected').start_time;
			
			var end_time = $('#dg').datagrid('getSelected').end_time;
			
			var max = $('#dg').datagrid('getSelected').max;
			
			var arr  =
		     {
		         "register_date" : register_date,
		         "start_time" : start_time,
		         "end_time" : end_time,
		         "max" :max
		     }
			jsonarray.push(arr);
		}
		
		$("#times").val(JSON.stringify(jsonarray));
		
		$("#ff").submit();
	});
	
	$('#ff').form({      
        success:function(data){     // 提交成功后的回调函数。  
            //alert(data);  
            var data = eval('(' + data + ')');  // change the JSON string to javascript object      
            if (data.success){  
                alert(data.message);  
            }  
        }  
    }); 
	
	var editIndex = undefined;
	function endEditing(){
		if (editIndex == undefined){return true}
		if ($('#dg').datagrid('validateRow', editIndex)){
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickRow(index){
		if (editIndex != index){
			if (endEditing()){
				$('#dg').datagrid('selectRow', index)
						.datagrid('beginEdit', index);
				editIndex = index;
			} else {
				$('#dg').datagrid('selectRow', editIndex);
			}
		}
	}
	function append(){
		if (endEditing()){
			$('#dg').datagrid('appendRow',{register_date:''});
			editIndex = $('#dg').datagrid('getRows').length-1;
			$('#dg').datagrid('selectRow', editIndex)
					.datagrid('beginEdit', editIndex);
		}
	}
	function removeit(){
		if (editIndex == undefined){return}
		$('#dg').datagrid('cancelEdit', editIndex)
				.datagrid('deleteRow', editIndex);
		editIndex = undefined;
	}
	function accept(){
		if (endEditing()){
			$('#dg').datagrid('acceptChanges');
		}
	}
	function reject(){
		$('#dg').datagrid('rejectChanges');
		editIndex = undefined;
	}
    	$(function(){
    		$.post("../SystemServlet?method=isLoad",function(data){
				 var data = eval('(' + data + ')');  // change the JSON string to javascript object      
	                if (data.success){  
	                    if (data.message == false) {
	                    	alert("当前未挂载选课计划,请前往计划列表中挂载");
	                    	window.location = "./planList.jsp";
	                    }  
	                }  
			  });
		});
    </script>
</body>
</html>