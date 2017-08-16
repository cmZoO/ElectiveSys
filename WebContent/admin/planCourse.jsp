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
	<table id="dg" class="easyui-datagrid" title="选课课程列表"
			style="width: 100%; height: auto"
			data-options="
				singleSelect: true,
				toolbar: '#tb',
				onClickRow: onClickRow,
				fitColumns: true,
				url:'../SystemServlet?method=getCourse'
			">
			<thead>
				<tr>
					<th width="120"
						data-options="field:'course_id',align:'center'">
						选修课程ID
					</th>
					<th width="120"
						data-options="field:'course_name',align:'center',editor:{type:'textbox',options:{required:true}}">
						选修课程名称
					</th>
					<th width="120"
						data-options="field:'class_time',align:'center',
						formatter:function(value,row){
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
						},
						editor:{
							type:'combobox',
							options:{
								valueField:'class_time',
								textField:'timename',
								method:'get',
								url:'../json/products.json',
								required:true
							}
						}">
						选修上课时间
					</th>
					<th width="120"
						data-options="field:'class_place',align:'center',editor:{type:'textbox',options:{required:false}}">
						选修上课地点
					</th>
					<th width="120"
						data-options="field:'total',align:'center',editor:{type:'textbox',options:{required:true}}">
						选修总人数
					</th>
					<th width="120"
						data-options="field:'course_teac',align:'center'">
						任课老师
					</th>
				</tr>
			</thead>
		</table>
		<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">增加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">移除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">撤销</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-refresh',plain:true" onclick="refresh()">刷新</a>
		</div>
		<div style="padding: 5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" id="teacherOption" style="width: 80px; float:right">老师修改</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" id="on-submit" style="width: 80px; float:right">提交更改</a>
		</div>
		<form id="ff" method="post" action="./adminServlet?method=courseChange">
			<input type="hidden" name="courses" id="courses" />
		</form>
		
		<div id="userWindow" class="easyui-window" title="老师修改" data-options="modal:true,collapsible:false,closed:true,maximizable:false,minimizable:false,resizable:false" style="width:550px;">
				<input class="easyui-textbox theme-textbox-radius " id="teachers" name="teachers" style="width: 400px"
					data-options="required:true, label:'已选教师:'" />
			<form id="teacherUp" action="./adminServlet?method=CourseTeacherChange" method="post">
			<input type="hidden" id="teachersid" name="teachersid"/>
			<input type="hidden" id="selectCourse" name="selectCourse"/>
			</form>
			<table id="teacherdg"  class="easyui-datagrid" title="老师列表" style="width:100%"
			data-options="rownumbers:true,singleSelect:true,pagination:true,url:'./adminServlet?method=getTeacherJson',method:'get'">
			<thead>
				<tr>
					<th data-options="field:'teac_id',width:80">教工编号</th>
					<th data-options="field:'teac_name',width:100">教师名字</th>
				</tr>
			</thead>
		</table>
		<div style="padding: 5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" id="teacherUpButton" style="width: 80px; float:right">提交</a>
		</div>
		</div>
		<script>
		$("#teacherUpButton").click(function() {
			$("#selectCourse").val($('#dg').datagrid('getSelected').course_id);
			$("#teacherUp").submit();
		});
		$("#teacherOption").click(function() {
			if ( $('#dg').datagrid('getSelected') == null) {
				alert("没有选中课程");
				return;
			}
			$("#teachersid").val("");
			$("#selectCourse").val("");
			$("#teachers").textbox('setValue',"");
			
			 $('#userWindow').window('open');
		});
	$("#on-submit").click(function() {
		accept();
		
		var jsonstr="[]";
		var jsonarray = eval('('+jsonstr+')');
		
		for (var i = 0; i < $('#dg').datagrid('getRows').length; i++) {
		    $('#dg').datagrid('selectRow',i);// 关键在这里
		    
			var course_id = $('#dg').datagrid('getSelected').course_id;
		    if (course_id == null) {
		    	course_id = "";
		    }
			var course_name = $('#dg').datagrid('getSelected').course_name;
			var class_time = $('#dg').datagrid('getSelected').class_time;
			var class_place = $('#dg').datagrid('getSelected').class_place;
			if (class_place == null) {
				class_place = "";
		    }
			var total = $('#dg').datagrid('getSelected').total;
			var arr  =
		     {
		         "course_id" : course_id,
		         "course_name" : course_name,
		         "class_time" : class_time,
		         "class_place" : class_place,
		         "total" : total
		     }
			jsonarray.push(arr);
		}
		
		$("#courses").val(JSON.stringify(jsonarray));
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
	
	$('#teacherUp').form({      
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
	function refresh() {
	      $('#dg').datagrid('reload');  
	}
	
	$("#teacherdg").datagrid({  
        onClickRow: function (index, row) {  //easyui封装好的时间（被单机行的索引，被单击行的值）
        	if ($("#teachers").val().length < 1) {
	        	$("#teachers").textbox('setValue',$('#teacherdg').datagrid('getSelected').teac_name);
        	} else {
        		$("#teachers").textbox('setValue',$("#teachers").val() + "," +$('#teacherdg').datagrid('getSelected').teac_name);
        	}
        	if ($("#teachersid").val().length < 1) {
        		$("#teachersid").val($('#teacherdg').datagrid('getSelected').teac_id);
        	} else {
        		$("#teachersid").val($("#teachersid").val() + "," + $('#teacherdg').datagrid('getSelected').teac_id);
        	}
        }
    });
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