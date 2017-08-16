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

<script type="text/javascript" src="http://www.gongjuji.net/Content/files/jquery.md5.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#ff').form({      
            success:function(data){     // 提交成功后的回调函数。  
                //alert(data);  
                var data = eval('(' + data + ')');  // change the JSON string to javascript object      
                if (data.success){  
                    alert(data.message);  
                }  
            }  
        }); 
		
		$("#on-submit").click(function() {
			if ($("#id").val().length < 1) {
				alert("学号非法");
				return;
			}
			if ($("#name").val().length < 1) {
				alert("姓名非法");
				return;
			}
			if ($("#date").val().length < 1) {
				alert("日期非法");
				return;
			}
					
			if(!$("#password-panel").is(":hidden")){
				if ($("#ipassword1").val().length < 8 || $("#ipassword1").val().length > 16) {
					alert("密码长度非法");
					return;
				}
				if ($("#ipassword1").val() != $("#ipassword2").val()) {
					alert("两次密码不一致");
					return;
				}
				$("#password").val($.md5($("#ipassword1").val()));
			}
			
			$("#ff").submit();
		});
		
		$.extend($.fn.validatebox.defaults.rules, {
		    /*必须和某个字段相等*/
		    equalTo: { validator: function (value, param) { return $(param[0]).val() == value; }, message: '字段不匹配' }
		   });
	});
	
	
</script>


</head>
	
	<div style="width: 400px; padding: 30px 60px;">
		<form id="ff" method="post" action="./adminServlet?method=studentAdd">
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox theme-textbox-radius " id="id" name="id" style="width: 100%"
					data-options="required:true, label:'学号:'" />
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox theme-textbox-radius " id="name" name="name" style="width: 100%"
					data-options="required:true, label:'姓名:'" />
			</div>
			<div style="margin-bottom:20px">
	            <input class="easyui-datebox theme-textbox-radius" id="date" name="date" required style="width:100%" data-options="label:'入学日期:'" >
        	</div>
			<input id="password" name="password" type="hidden" />
		</form>
		<div id="password-panel">
			<div style="margin-bottom: 20px" >
				<input class="easyui-passwordbox easyui-validatebox theme-textbox-radius" iconWidth="28" id="ipassword1" validType="length[8,16]" style="width: 100%" data-options="required:true,label:'密码:'" />
			</div>
			<div style="margin-bottom:20px" >
				<input class="easyui-passwordbox easyui-validatebox theme-textbox-radius" iconWidth="28" id="ipassword2" style="width: 100%" data-options="label:'重复密码:'"  validType="equalTo['#ipassword1']" invalidMessage="两次输入密码不匹配"/>
			</div>
		</div>
		<div style="padding: 5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" id="on-submit" style="width: 80px; float:right">插入用户</a>
		</div>
	</div>
</html>