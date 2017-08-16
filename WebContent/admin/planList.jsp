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
	<div style="margin:20px 0;"></div>
	<table id="dg" title="计划列表" class="easyui-datagrid" style="width:100%;height:500px"
			data-options="rownumbers:true,singleSelect:true,pagination:true,url:'./adminServlet?method=getPlanJson',method:'get',pageList: [5, 10, 20, 50, 100]">
		<thead>
			<tr>
				<th data-options="field:'plan_id',width:80">计划ID</th>
				<th data-options="field:'plan_name',width:100">计划名称</th>
				<th data-options="field:'delay_drop_time',width:100">退选间隔</th>
				<th data-options="field:'timeNum',width:100">选课时间个数</th>
				<th data-options="field:'coursesNum',width:100">选修个数</th>
				<th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">操作</th>
			</tr>
		</thead>
	</table>
	
	<div id="userWindow" class="easyui-window" title="修改资料" data-options="modal:true,collapsible:false,closed:true,maximizable:false,minimizable:false,resizable:false" style="width:550px;height:400px;padding:10px;">
		<div style="width: 400px; padding: 30px 60px;">
			<form id="ff" method="post" action="./adminServlet?method=planChange">
				<div style="margin-bottom: 20px">
					<input class="easyui-textbox theme-textbox-radius  validatebox-readonly" readonly="readonly" id="plan_id" name="plan_id" style="width: 100%"
						data-options="required:true, label:'计划ID:'" />
				</div>
				<div style="margin-bottom: 20px">
					<input class="easyui-textbox theme-textbox-radius " id="plan_name" name="plan_name" style="width: 100%"
						data-options="required:true, label:'计划名称:'" />
				</div>
				<div style="margin-bottom: 20px">
					<select class="easyui-combobox theme-textbox-radius" name="delay_time" id="delay_time" style="width:100%;panelHeight:300px" data-options="required:true, label:'退选间隔'">
						<option value="30">30分钟</option>
		                <option value="60" selected="selected">60分钟</option>
		                <option value="120">120分钟</option>
					</select>
				</div>
			</form>
			
			<div style="padding: 5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton" id="on-delete" style="width: 80px; float:left">删除计划</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" id="on-submit" style="width: 80px; float:right">修改计划</a>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		function formatOper(val,row,index){  
		    return '<a href="#" style="color:blue;" onclick="loadPlan('+index+')">挂载</a>';  
		} 
		
		function loadPlan(index) {
			$('#dg').datagrid('selectRow',index);// 关键在这里
			txt=$('#dg').datagrid('getSelected').plan_id;
			$.post("./adminServlet?method=planLoad",{plan_id:txt},function(data){
				 var data = eval('(' + data + ')');  // change the JSON string to javascript object      
	                if (data.success){  
	                    alert(data.message);  
	                }  
			  });
		}
		
		$(function(){
			$("#on-submit").click(function() {
				if ($("#plan_id").val().length < 1) {
					alert("计划ID非法");
					return;
				}
				
				if ($("#plan_name").val().length < 1) {
					alert("计划名称非法");
					return;
				}
				
				if ($("#delay_time").val().length < 1) {
					alert("退选间隔");
					return;
				}
				$("#ff").submit();
			});
			
			var pager = $('#dg').datagrid().datagrid('getPager');	// get the pager of datagrid
			pager.pagination({
				buttons:[{
					iconCls:'icon-edit',
					handler:function(){
						$("#plan_id").textbox('setValue',$('#dg').datagrid('getSelected').plan_id);//赋值
						$("#plan_name").textbox('setValue',$('#dg').datagrid('getSelected').plan_name);//赋值
						$("#delay_time").textbox('setValue',$('#dg').datagrid('getSelected').delay_drop_time);//赋值
						
						$('#userWindow').window('open');
					}
				}]
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
		})
	</script>
</body>
</html>