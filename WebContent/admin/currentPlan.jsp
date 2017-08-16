<%@page import="com.bean.ElectiveSys"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
<script type="text/javascript" src="../js/justgage.js"></script>
<script type="text/javascript" src="../js/raphael-2.1.4.min.js"></script>
<script type="text/javascript">
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
	
	$("#start").click(function() {
		$.post("./adminServlet?method=planStart",function(data){
			 var data = eval('(' + data + ')');  // change the JSON string to javascript object      
	           if (data.success){  
	               alert(data.message);
	               location.reload();
	           }  
		  });
	});
	$("#close").click(function() {
		$.post("./adminServlet?method=planClose",function(data){
			var data = eval('(' + data + ')');  // change the JSON string to javascript object      
	        if (data.success){  
	            alert(data.message);
	            location.reload();
	        }  
		  });
	});
	$("#downLine").click(function() {
		$.post("./adminServlet?method=downLine",function(data){
			var data = eval('(' + data + ')');  // change the JSON string to javascript object      
	        if (data.success){  
	            alert(data.message);
	            alert("当前未挂载选课计划,请前往计划列表中挂载");
	            window.parent.tabsClose();
	        }  
		  });
	})
});



</script>

</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
     <div data-options="region:'north',border:false" style="height:40%">
                <div class="theme-user-info-panel">
                    <div class="right">
                    	
                          <style>
						  .gauge {
							width: 130px;
							height: 130px;
						  }
						  </style>
                            <script>
							$(function(){
							
								var dflt = {
								  min: 0,
								  donut: true,
								  gaugeWidthScale: 0.6,
								  counter: true,
								  hideInnerShadow: true
								}
								var gg1 = new JustGage({
								  id: 'gg1',
								  max: 5,
								  defaults: dflt
								});
							
								var gg2 = new JustGage({
								  id: 'gg2',
								  max: 50,
								  defaults: dflt
								});
							
							  });
							  </script>
                    	<ul>
                        	<li><div id="gg1" class="gauge"  data-value="<%=ElectiveSys.plan==null?null:ElectiveSys.plan.getElective_Times().size() %>"></div><span>选课时间数</span></li>
                            <li><div id="gg2" class="gauge"  data-value="<%=ElectiveSys.courses==null?null:ElectiveSys.courses.size() %>"></div><span>课程总数</span></li>
                        </ul>
                    </div>
                    <div class="center">
                        <h1 style="font-size:50px"><%=ElectiveSys.plan==null?null:ElectiveSys.plan.getPlan_name() %><span id="downLine" class="color-warning badge">下线</span></h1>
                        <h2 style="font-size:20px">开始状态:<%=ElectiveSys.isOpen %></h2>
                        <br />
                        <ul>
                            <li><a href="javascript:void(0)" class="easyui-linkbutton" id="start" style="width: 80px;">开始</a></li>
                            <li><a href="javascript:void(0)" class="easyui-linkbutton" id="close" style="width: 80px;">关闭</a></li>
                        </ul>
                    </div>
                	
                </div>
     </div>
     </div>
</body>
</html>