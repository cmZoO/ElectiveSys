<%@page import="com.bean.ElectiveSys"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@page import="java.text.SimpleDateFormat"%>
   <%@page import="com.server.SystemServer"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生后台</title>
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

<!-- gt 脚本 -->
<script src="http://static.geetest.com/static/tools/gt.js"></script> 

<script type="text/javascript" src="http://www.gongjuji.net/Content/files/jquery.md5.js"></script>

</head>
<body>
	<div id="master-layout">
		<div data-options="region:'north',border:false,bodyCls:'theme-header-layout'">
        	<div class="theme-navigate">
				<div class="right">
					<span id="time" class="badge color-default" style="margin:50px;font-size:12px"></span>
					<a href="#" class="easyui-menubutton theme-navigate-user-button"
						data-options="menu:'.theme-navigate-user-panel'">${sessionScope.student.stu_name }</a>

					<div class="theme-navigate-user-panel">
						<dl>
							<dd>
								<img src="../themes/insdep/images/portrait86x86.png" width="86"
									height="86"> <b class="badge-prompt">${sessionScope.student.stu_name }</b> 
									<span>id:&nbsp;${sessionScope.student.stu_id }</span>
							</dd>
							<dt>
								<a class="theme-navigate-user-modify">历史选修记录</a>
								<a class="theme-navigate-user-logout" id="logout" href="../login/login?method=logout">注销</a>
								<a class="theme-navigate-user-logout" id = "passwordChange">修改密码</a>
							</dt>
						</dl>
					</div>
				</div>
			</div>
        </div>
        
        <div data-options="region:'center',border:false"  id="control" style="background:#fff;">
			<div class="easyui-layout" data-options="fit:true">
				<div style=" height:35%" data-options="region:'north',split:true,border:false" >
					<div class="easyui-layout" data-options="fit:true">
						<div style=" width:50%" data-options="region:'west',split:true,border:false" >
							<div class="easyui-panel" data-options="title:'已选课程',border:false,fit:true">
							<table id="weektable" class="table table-celled" style="width: 100%;height:100%;text-align:center;" >
								<thead>
									<tr>
										<th>星期一</th>
										<th>星期二</th>
										<th>星期三</th>
										<th>星期四</th>
										<th>星期五</th>
										<th>星期六</th>
										<th>星期天</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td id="tb61"></td>
										<td id="tb71"></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td  id="tb62"></td>
										<td  id="tb72"></td>
									</tr>
									<tr>
										<td  id="tb13"></td>
										<td  id="tb23"></td>
										<td  id="tb33"></td>
										<td  id="tb43"></td>
										<td  id="tb53"></td>
										<td  id="tb63"></td>
										<td  id="tb73"></td>
									</tr>
								</tbody>
							</table>
							</div>
						</div>
						<div style=" width:50%" data-options="region:'center',split:true,border:false" >
							<table id="selectdg" class="easyui-datagrid" title="选中课程列表" style="width:100%;height:auto"
							data-options="rownumbers:true,singleSelect:true">
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
							</tr>
							</thead>
							</table>
							
							<form id="form" method = "post" action="./ajax-validate">
							<input type="hidden" name="choose" id="choose" />
							<table style="width: 100%;">
								<tbody>
									<tr>
										<td style="width:50%">
										<div id="captcha">
											<p id="wait" class="show">正在加载验证码......</p>
										</div></td>
										<td style="width:50%">
											<p id="notice" class="hide" style="color: red">请先完成验证</p>
											<a class="easyui-linkbutton c8" id="submit" style="width:100%">提交选课</a>
										</td>
									</tr>
								</tbody>
							</table>
						</form>
						</div>
					</div>
				</div>
				<div data-options="region:'center',border:false">
					<table id="dg" class="easyui-datagrid" title="课程列表" style="width:100%;height:auto"
							data-options="rownumbers:true,singleSelect:true,pagination:true,url:'../SystemServlet?method=getElectiveJson',method:'get'">
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
								<th data-options="field:'course_size',width:100">课程容量</th>
								<th data-options="field:'total',width:100">当前段可选</th>
								<th data-options="field:'nexttotal',width:100">下阶段可选</th>
							</tr>
						</thead>
					</table>
					
				</div>
			</div>
        </div>
	</div>
	
	<div id="passwordWindow" class="easyui-window" title="修改密码" data-options="modal:true,collapsible:false,closed:true,maximizable:false,minimizable:false,resizable:false" style="width:550px;height:400px;padding:10px;">
			<div style="width: 400px; padding: 30px 60px;">
				<form id="pwform" method="post"
					action="./studentServlet?method=passwordChange">
					<input id="password" name="password" type="hidden" />
					<input id="oldpassword" name="oldpassword" type="hidden" />
				</form>
				<div id="password-panel">
					<div style="margin-bottom: 20px">
						<input
							class="easyui-passwordbox easyui-validatebox theme-textbox-radius"
							id="oldpw" validType="length[8,16]"  iconWidth="28" style="width: 100%"
							data-options="label:'旧密码:'" />
					</div>
					<div style="margin-bottom: 20px">
						<input
							class="easyui-passwordbox easyui-validatebox theme-textbox-radius"
							id="ipassword1" validType="length[8,16]"  iconWidth="28" style="width: 100%"
							data-options="label:'新密码:'" />
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
						style="width: 80px; float: left" id="changePassword">修改密码</a>
				</div>
			</div>
		</div>
	
	<div id="userWindow" class="easyui-window" title="历史选课信息" data-options="modal:true,collapsible:false,closed:true,maximizable:false,minimizable:false,resizable:false" style="width:850px;height:400px;padding:10px;">
			<table id="historydg" class="easyui-datagrid" title="选课课程列表"
			style="width: 100%; height: auto"
			data-options="
				singleSelect: true,
				fitColumns: true,
				autoRowHeight:false,
			">
			<thead>
				<tr>
					<th width="120"
						data-options="field:'plan_name',align:'center'">
						选修计划名称
					</th>
					<th width="120"
						data-options="field:'course_name',align:'center'">
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
						}">
						选修上课时间
					</th>
					<th width="120"
						data-options="field:'class_place',align:'center'">
						选修上课地点
					</th>
					<th width="120"
						data-options="field:'course_teac',align:'center'">
						任课老师
					</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<script>
		function formsubmit() {
			//构造json
		var jsonstr="[]";
		var jsonarray = eval('('+jsonstr+')');
		
		for (var i = 0; i < $('#selectdg').datagrid('getRows').length; i++) {
		    $('#selectdg').datagrid('selectRow',i);// 关键在这里
			var course_id = $('#selectdg').datagrid('getSelected').course_id;
			var arr  =
		     {
		         "course_id" : course_id,
		     }
			jsonarray.push(arr);
		}
		
		//传输给后台处理
		$("#choose").val(JSON.stringify(jsonarray));
		$("#form").submit();
		
		};
		
		$('#form').form({      
	        success:function(data){     // 提交成功后的回调函数。  
	            //alert(data);  
	            var data = eval('(' + data + ')');  // change the JSON string to javascript object      
	            if (data.success){  
	                alert(data.message);  
	            }  
	            window.location.reload();
	        }  
	    }); 
		
		
		function dechoise(name) {
			var course_id;
			for (var i = 0; i < $('#dg').datagrid('getRows').length; i++) {
			    $('#dg').datagrid('selectRow',i);// 关键在这里
				var course_name = $('#dg').datagrid('getSelected').course_name;
			    if (name == course_name) {
			    	course_id = $('#dg').datagrid('getSelected').course_id;
			    	break;
			    }
			}
			$.post("./studentServlet?method=deChoose", { course_id: course_id},function(data){
				 var data = eval('(' + data + ')');  // change the JSON string to javascript object      
	                if (data.success){  
	                    alert(data.message);
	                    window.location.reload();
	                }  
			});
		}
		
		<%-- dt = new Date(<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())%>);
		 --%>
		 var dt =  new Date('<%=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date())%>');
		 function setTime() {
			 dt.setSeconds(dt.getSeconds() + 1);
			var h=dt.getHours();
		    var m=dt.getMinutes();
		    var s=dt.getSeconds();
    		$("#time").html("现在的时间为:"+h+"时"+m+"分"+s+"秒");
    		setTimeout(setTime,1000); 
    		
		} 
		
		 $(function(){
    		setTime();
    		
    		$("#passwordChange").click(function() {
    			$('#passwordWindow').window('open');
			});
    		
    		$.extend($.fn.validatebox.defaults.rules, {
			    /*必须和某个字段相等*/
			    equalTo: { validator: function (value, param) { return $(param[0]).val() == value; }, message: '字段不匹配' }
			   });
    		
    		$('#pwform').form({      
                success:function(data){     // 提交成功后的回调函数。  
                    //alert(data);  
                    var data = eval('(' + data + ')');  // change the JSON string to javascript object      
                    if (data.success){  
                    	if (data.message == "修改成功") {
	                        alert(data.message + " 请重新登陆");  
	                        window.location.href='../login/login?method=logout';
                    	} else {
                    		alert(data.message);
                    	}
                    }  
                }  
            }); 
    		
			$("#changePassword").click(function() {
				if ($("#oldpw").val().length < 8 || $("#oldpw").val().length > 16) {
					alert("旧密码长度非法");
					return;
				}
				if ($("#ipassword1").val().length < 8 || $("#ipassword1").val().length > 16) {
					alert("新密码长度非法");
					return;
				}
				if ($("#ipassword1").val() != $("#ipassword2").val()) {
					alert("两次密码不一致");
					return;
				}
				
				$("#oldpassword").val($.md5($("#oldpw").val()))
				
				$("#password").val($.md5($("#ipassword1").val()));
				
				$('#pwform').submit();
			}); 		
    		
    		
    		$("#weektable td").click(function(){
		        var title=$(this).text();
		        if (title.length == 0) {
		        	return;
		        }
		        $.messager.confirm('警告', '请确定退选课程<br />' + title, function(r){
					if (r){
						dechoise(title);
					}
				});
		    });
    		
    		var handler1 = function(captchaObj) {
    			$("#submit").click(function(e) {
    				var result = captchaObj.getValidate();
    				if (!result) {
    					$("#notice").show();
    					setTimeout(function() {
    						$("#notice").hide();
    					}, 2000);
    					e.preventDefault();
    				} else {
    					formsubmit();
    				}
    			});
    			// 将验证码加到id为captcha的元素里，同时会有三个input的值用于表单提交
    			captchaObj.appendTo("#captcha");
    			captchaObj.onReady(function() {
    				$("#wait").hide();
    			});
    			// 更多接口参考：http://www.geetest.com/install/sections/idx-client-sdk.html
    		};
    		$.ajax({
    			url : "./register?t=" + (new Date()).getTime(), // 加随机数防止缓存
    			type : "get",
    			dataType : "json",
    			success : function(data) {
    				// 调用 initGeetest 初始化参数
    				// 参数1：配置参数
    				// 参数2：回调，回调的第一个参数验证码对象，之后可以使用它调用相应的接口
    				initGeetest({
    					gt : data.gt,
    					challenge : data.challenge,
    					new_captcha : data.new_captcha, // 用于宕机时表示是新验证码的宕机
    					offline : !data.success, // 表示用户后台检测极验服务器是否宕机，一般不需要关注
    					product : "popup", // 产品形式，包括：float，popup
    					width : "100%"
    				// 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
    				}, handler1);
    			}
    		});
    		$.post("./studentServlet?method=getChoose",function(data){
				 var data = eval('(' + data + ')');  // change the JSON string to javascript object  
				 for(var i=0,l=data.length;i<l;i++){
					 if (data[i]['class_time'] == 1) {
						 $("#tb13").html(data[i]['course_name']);
						 $("#tb13").css('background-color','yellow');
					 } else if (data[i]['class_time'] == 2) {
						 $("#tb23").html(data[i]['course_name']);
						 $("#tb23").css('background-color','yellow');
					 } else if (data[i]['class_time'] == 3) {
						 $("#tb33").html(data[i]['course_name']);
						 $("#tb33").css('background-color','yellow');
					 } else if (data[i]['class_time'] == 4) {
						 $("#tb43").html(data[i]['course_name']);
						 $("#tb43").css('background-color','yellow');
					 } else if (data[i]['class_time'] == 5) {
						 $("#tb53").html(data[i]['course_name']);
						 $("#tb53").css('background-color','yellow');
					 } else if (data[i]['class_time'] == 6) {
						 $("#tb61").html(data[i]['course_name']);
						 $("#tb61").css('background-color','yellow');
					 } else if (data[i]['class_time'] == 7) {
						 $("#tb62").html(data[i]['course_name']);
						 $("#tb62").css('background-color','yellow');
					 } else if (data[i]['class_time'] == 8) {
						 $("#tb63").html(data[i]['course_name']);
						 $("#tb63").css('background-color','yellow');
					 } else if (data[i]['class_time'] == 9) {
						 $("#tb71").html(data[i]['course_name']);
						 $("#tb71").css('background-color','yellow');
					 } else if (data[i]['class_time'] == 10) {
						 $("#tb72").html(data[i]['course_name']);
						 $("#tb72").css('background-color','yellow');
					 } else if (data[i]['class_time'] == 11) {
						 $("#tb73").html(data[i]['course_name']);
						 $("#tb73").css('background-color','yellow');
					 }
				}
			  });
    		
    		$.post("../SystemServlet?method=isOpen",function(data){
				 var data = eval('(' + data + ')');  // change the JSON string to javascript object      
	                if (data.success){  
	                    if (data.message == false) {
	                    	$.messager.alert('警告', '当前无选课计划');
	                    }  
	                }  
			  });
    		
			/*布局部分*/
			$('#master-layout').layout({
				fit:true/*布局框架全屏*/
			});   
			

	//userful
			$(".theme-navigate-user-modify").on("click",function(){
				$("#historydg").datagrid("load",'./studentServlet?method=getHistory');
                $('#userWindow').window('open');
            }); 
			//$.insdep.control("list.html");
			
			function append(course_id, course_name, class_time, class_place, course_teac){
				$('#selectdg').datagrid('appendRow',{course_id:course_id,
												course_name:course_name,
												class_time:class_time,
												class_place:class_place,
												course_teac:course_teac
				});
			}
		
			$("#dg").datagrid({  
		        onClickRow: function (index, row) {  //easyui封装好的时间（被单机行的索引，被单击行的值）
		        	course_id = $('#dg').datagrid('getSelected').course_id;
		        	
		        	for (var i = 0; i < $('#selectdg').datagrid('getRows').length; i++) {
		    		    $('#selectdg').datagrid('selectRow',i);// 关键在这里
		    			var id = $('#selectdg').datagrid('getSelected').course_id;
		    			if (course_id == id) {
		    				return;
		    			}
		    		}
		        
		        	course_name = $('#dg').datagrid('getSelected').course_name;
		        	class_time = $('#dg').datagrid('getSelected').class_time;
		        	class_place = $('#dg').datagrid('getSelected').class_place;
		        	course_teac = $('#dg').datagrid('getSelected').course_teac;
		        	
		        	append(course_id, course_name, class_time, class_place, course_teac);
		        }
		    });
			
			$('#selectdg').datagrid({  
		        onClickRow: function (index, row) {  //easyui封装好的时间（被单机行的索引，被单击行的值）
		        	$('#selectdg').datagrid('deleteRow', index);
		        }
		    });
			
		});
	
    </script>
</body>
</html>