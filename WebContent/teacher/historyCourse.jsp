<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>教师后台</title>
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
			style="width: 100%; height: auto" sortName="plan_name" multiSort="true"
			data-options="
				singleSelect: true,
				fitColumns: true,
				url:'teacherServlet?method=getAllCourse'
				
			">
			<thead>
				<tr>
					<th width="120" sortable="true"
						data-options="field:'plan_name',align:'center'">
						课程计划
					</th>
					<th width="120"
						data-options="field:'course_id',align:'center'">
						选修课程ID
					</th>
					<th width="120"
						data-options="field:'course_name',align:'center',editor:{type:'textbox',options:{required:true}}">
						选修课程名称
					</th>
					<th width="120" sortable="true"
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
								return '星期六';
							} else if (value == 7) {
								return '星期天';
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
		
		<div id="studentListWD" class="easyui-window" title="选课学生信息" data-options="modal:true,collapsible:false,closed:true,maximizable:false,minimizable:false,resizable:false" style="width:850px;height:400px">
			<table id="studentList" class="easyui-datagrid" 
			style="width: 100%; height: auto"
			data-options="
				singleSelect: true,
				toolbar: '#tb',
				fitColumns: true,
				url:'teacherServlet?method=getMyStudent'
			">
			<thead>
				<tr>
					<th width="120"
						data-options="field:'stu_id',align:'center'">
						学生id
					</th>
					<th width="120"
						data-options="field:'stu_name',align:'center'">
						学生姓名
					</th>
					<th width="120"
						data-options="field:'register_date',align:'center'">
						入学时间
					</th>
				</tr>
			</thead>
		</table>
		<div id="tb" style="height:auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-print',plain:true" onclick="exportexcl()">导出为excel</a>
		</div>
		</div>
		<div id="dialog2"></div> 
		<script type="text/javascript">
		$(function(){
			$("#dg").datagrid({  
		        onClickRow: function (index, row) {  //easyui封装好的时间（被单机行的索引，被单击行的值）
		        	$('#studentList').datagrid('load',{
		        		course_id: row.course_id
		        	});
		        	$('#studentListWD').window('open');
		        }
		    });
		});
		
		function exportexcl() {
			var grid = $("#studentList");
			var tableString = '<table cellspacing="0" class="pb">';    
            var frozenColumns = grid.datagrid("options").frozenColumns;  // 得到frozenColumns对象    
	        var columns = grid.datagrid("options").columns;    // 得到columns对象    
	            var nameList = new Array();    
	    
	    // 载入title    
	    if (typeof columns != 'undefined' && columns != '') {    
	        $(columns).each(function (index) {    
	            tableString += '\n<tr>';    
	            if (typeof frozenColumns != 'undefined' && typeof frozenColumns[index] != 'undefined') {    
	                for (var i = 0; i < frozenColumns[index].length; ++i) {    
	                    if (!frozenColumns[index][i].hidden) {    
	                        tableString += '\n<th width="' + frozenColumns[index][i].width + '"';    
	                        if (typeof frozenColumns[index][i].rowspan != 'undefined' && frozenColumns[index][i].rowspan > 1) {    
	                            tableString += ' rowspan="' + frozenColumns[index][i].rowspan + '"';    
	                        }    
	                        if (typeof frozenColumns[index][i].colspan != 'undefined' && frozenColumns[index][i].colspan > 1) {    
	                            tableString += ' colspan="' + frozenColumns[index][i].colspan + '"';    
	                        }    
	                        if (typeof frozenColumns[index][i].field != 'undefined' && frozenColumns[index][i].field != '') {    
	                            nameList.push(frozenColumns[index][i]);    
	                        }    
	                        tableString += '>' + frozenColumns[0][i].title + '</th>';    
	                    }    
	                }    
	            }    
	            for (var i = 0; i < columns[index].length; ++i) {    
	                if (!columns[index][i].hidden) {    
	                    tableString += '\n<th width="' + columns[index][i].width + '"';    
	                    if (typeof columns[index][i].rowspan != 'undefined' && columns[index][i].rowspan > 1) {    
	                        tableString += ' rowspan="' + columns[index][i].rowspan + '"';    
	                    }    
	                    if (typeof columns[index][i].colspan != 'undefined' && columns[index][i].colspan > 1) {    
	                        tableString += ' colspan="' + columns[index][i].colspan + '"';    
	                    }    
	                    if (typeof columns[index][i].field != 'undefined' && columns[index][i].field != '') {    
	                        nameList.push(columns[index][i]);    
	                    }    
	                    tableString += '>' + columns[index][i].title + '</th>';    
	                }    
	            }    
	            tableString += '\n</tr>';    
	        });    
	    }    
	    // 载入内容    
	    var rows = grid.datagrid("getRows"); // 这段代码是获取当前页的所有行    
	    for (var i = 0; i < rows.length; ++i) {    
	        tableString += '\n<tr>';    
	        for (var j = 0; j < nameList.length; ++j) {    
	            var e = nameList[j].field.lastIndexOf('_0');    
	    
	            tableString += '\n<td';    
	            if (nameList[j].align != 'undefined' && nameList[j].align != '') {    
	                tableString += ' style="text-align:' + nameList[j].align + ';"';    
	            }    
	            tableString += '>';    
	            if (e + 2 == nameList[j].field.length) {    
	                tableString += rows[i][nameList[j].field.substring(0, e)];    
	            }    
	            else    
	                tableString += rows[i][nameList[j].field];    
	            tableString += '</td>';    
	        }    
	        tableString += '\n</tr>';    
	    }    
	    tableString += '\n</table>';   
	    $('#hlf').val(tableString);  
	    exportString=tableString;  
	    var url="../export.jsp";  
	    var param2={  
	            doSize:false,  
	            shadow:false,  
	            content:'<iframe  scrolling="no" frameborder="0"  src="'+url+'" style="width:100%;height:95%;"></iframe>',  
	            title:'Export',  
	            width:300,      
	            height:170,  
	            modal:true        
	    };  
	    mpgdialog(param2);  
		}
		
		function mpgdialog(param){    
	        $('#dialog2').dialog(param);  
	        $('#dialog2').window('center');  
	    } 
		</script>
</body>
</html>