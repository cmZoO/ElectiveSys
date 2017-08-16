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
	<div id="master-layout">
        <div data-options="region:'north',border:false,bodyCls:'theme-header-layout'">
        	<div class="theme-navigate">
				<div class="right">
					<a href="#" class="easyui-menubutton theme-navigate-user-button"
						data-options="menu:'.theme-navigate-user-panel'">${sessionScope.teacher.teac_name }</a>

					<div class="theme-navigate-user-panel">
						<dl>
							<dd>
								<img src="../themes/insdep/images/portrait86x86.png" width="86"
									height="86"> <b class="badge-prompt">${sessionScope.teacher.teac_name }</b> 
									<span>id:&nbsp;${sessionScope.teacher.teac_id }</span>
							</dd>
							<dt>
								<a class="theme-navigate-user-modify">修改资料</a>
								<a class="theme-navigate-user-logout" href="../login/login?method=logout">注销</a>
							</dt>
						</dl>
					</div>
				</div>
			</div>
        </div>

        <!--开始左侧菜单-->
        <div data-options="region:'west',border:false,bodyCls:'theme-left-layout'" style="width:200px;">
            <!--正常菜单--> 
            <div class="theme-left-normal">

                <!--start class="easyui-layout"-->
                <div class="easyui-layout" data-options="border:false,fit:true"> 

                    <!--start region:'center'-->
                    <div data-options="region:'center',border:false" id="menu">

                        <!--start easyui-accordion--> 
                        <div class="easyui-accordion" data-options="border:false,fit:true">   
                            <div title="课程">   
                                <ul class="easyui-datalist" data-options="border:false,fit:true">
                                    <li><a  id="myCourse" href="#" rel="myCourse.jsp">我的课程</a></li>
                                    <li><a  id="historyCourse" href="#" rel="historyCourse.jsp">历史课程</a></li>
                                </ul>  
                            </div>   
                           
                   
                        </div>
                        <!--end easyui-accordion--> 

                    </div>
                    <!--end region:'center'-->
                </div>  
                <!--end class="easyui-layout"-->

            </div>

        </div>
        <!--结束左侧菜单-->

		<div region="center">
			<div id="tabs" class="easyui-tabs" fit="true" border="false">
			</div>
		</div>

		<div id="userWindow" class="easyui-window" title="修改资料" data-options="modal:true,collapsible:false,closed:true,maximizable:false,minimizable:false,resizable:false" style="width:550px;height:400px;padding:10px;">
			<div style="width: 400px; padding: 30px 60px;">
				<form id="ff" method="post"
					action="./teacherServlet?method=userChange">
					<div style="margin-bottom: 20px">
						<input
							class="easyui-textbox theme-textbox-radius validatebox-readonly"
							readonly="readonly" value="${sessionScope.teacher.teac_id }" name="id" id="id"
							style="width: 100%" data-options="label:'ID:'" />
					</div>
					<div style="margin-bottom: 20px">
						<input class="easyui-textbox theme-textbox-radius " id="name"
							name="name" value="${sessionScope.teacher.teac_name }" style="width: 100%"
							data-options="required:true, label:'姓名:'" />
					</div>
					<input id="password" name="password" type="hidden" />
				</form>
				<div id="password-panel">
					<div style="margin-bottom: 20px">
						<input
							class="easyui-passwordbox easyui-validatebox theme-textbox-radius"
							id="ipassword1" validType="length[8,16]"  iconWidth="28" style="width: 100%"
							data-options="label:'密码:'" />
					</div>
					<div style="margin-bottom: 20px">
						<input
							class="easyui-passwordbox easyui-validatebox theme-textbox-radius"
							id="ipassword2" style="width: 100%"  iconWidth="28" data-options="label:'重复密码:'"
							validType="equalTo['#ipassword1']" invalidMessage="两次输入密码不匹配" />
					</div>
				</div>
				<div style="padding: 5px 0">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						style="width: 80px; float: left" id="changePassword">修改密码</a> <a
						href="javascript:void(0)" class="easyui-linkbutton" id="on-submit"
						style="width: 80px; float: right">提交更改</a>
				</div>
			</div>
		</div>
    </div>



    <script>
    	$(function(){
    		$('#ff').form({      
    	        success:function(data){     // 提交成功后的回调函数。  
    	            //alert(data);  
    	            var data = eval('(' + data + ')');  // change the JSON string to javascript object      
    	            if (data.success){  
    	                alert(data.message);  
    	            	if (data.message == "修改成功") {
    	            		location.reload();
    	            	}
    	            }  
    	        }  
    	    }); 
			/*布局部分*/
			$('#master-layout').layout({
				fit:true/*布局框架全屏*/
			});   
			
			    $("#menu a").click(function(){
			        var title=$(this).text();
			        var url=$(this).attr("rel");
			        var tabId = $(this).attr("id") + "tab";
			        var name = $(this).attr("id") + "tab_name";
			        f_addTab(name,tabId,title,url);
			        return false;    //使超链接的单击事件失效
			    });


            /*右侧菜单控制部分*/

            var left_control_status=true;
            var left_control_panel=$("#master-layout").layout("panel",'west');

            $(".left-control-switch").on("click",function(){
                if(left_control_status){
                    left_control_panel.panel('resize',{width:70});
                    left_control_status=false;
                    $(".theme-left-normal").hide();
                    $(".theme-left-minimal").show();
                }else{
                    left_control_panel.panel('resize',{width:200});
                    left_control_status=true;
                    $(".theme-left-normal").show();
                    $(".theme-left-minimal").hide();
                }
                $("#master-layout").layout('resize', {width:'100%'})
            });

            /*右侧菜单控制结束*/




	//userful
			$(".theme-navigate-user-modify").on("click",function(){
                $('.theme-navigate-user-panel').menu('hide');
                $("#password-panel").hide();
                $('#userWindow').window('open');
            }); 
			//$.insdep.control("list.html");
			
			

			var cc1=$('#cc1').combo('panel');
			cc1.panel({cls:"theme-navigate-combobox-panel"}); 
			var cc2=$('#cc2').combo('panel');
			cc2.panel({cls:"theme-navigate-combobox-panel"}); 
			
			

			/*$("#open-layout").on("click",function(){
					var option = {
						"region":"west",
						"split":true,
                        "title":"title",
						"width":180
					};
					$('#master-layout').layout('add', option);
					
			});*/
			
			 
		});
		function doSearch(value,name){
				alert('You input: ' + value+'('+name+')');
			}
		
		
		
		$("#changePassword").click(function() {
			$("#password-panel").slideToggle("slow");
		});
		
		$("#on-submit").click(function() {
			if ($("#name").val().length < 1) {
				alert("姓名非法");
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
		
		$('#tabs').tabs({
		    onSelect:function(title){
		    	if (title == '我的课程')
		    		$.post("../SystemServlet?method=isLoad",function(data){
						 var data = eval('(' + data + ')');  // change the JSON string to javascript object      
			                if (data.success){  
			                    if (data.message == false) {
			                    	alert("当前未挂载选课计划,请联系管理员");
			                    	$('#tabs').tabs('close', title);
			                    }  
			                }  
					  });
				
		    }
		});
		
		function f_addTab(name,tabId,title,url){
			if (url == null) {
				return;
			}
			 if ($("#tabs").tabs('exists', title)) {
			  	$('#tabs').tabs('select', title);
			 } else {
			  $('#tabs').tabs('add',{
				   id:tabId,
				   title:title,
				   content:'<iframe name="'+name+'" src="'+url+'" frameborder="0" style="height:100%;width:100%;" "></iframe>',
				   closable:true,//tab显示关闭键
				   cache:true //设置缓存，如果为false，在每次选中所选的tab时，都会加载一次页面内容
			  	});
			 } 
		}
		
	
    </script>

</body>
</html>