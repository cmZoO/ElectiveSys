//登陆界面js
$(function() {

	$(".QRcode").on("click", function() {
		$(".QRcode-layout").removeClass("hide");

	});
	$(".QRcode-layout-close").on("click", function() {
		$(".QRcode-layout").addClass("hide");
	});

	$.extend($.fn.validatebox.defaults.tipOptions, {
		onShow : function() {
			$(this).tooltip("tip").css({
				backgroundColor : "#ff7e00",
				border : "none",
				color : "#fff"
			});

		}
	})

	/*布局部分*/
	$('#theme-login-layout').layout({
		fit : true
	/*布局框架全屏*/
	});

	/*表单定义*/
	$('#theme-login-select').combobox({
		/*通过ajax取数据
		url:'userclass.json',    
		valueField:'id',
		textField:'text'
		 */
		editable : false
	/*不允许用户通过输入选择*/

	});
	var selects = $('#theme-login-select').combobox('panel');
	selects.panel({
		cls : "theme-login-select-panel"
	});

	$('#username').textbox({
		prompt : '学号或者职工编号',
		required : true,
		missingMessage : "请输入用户名"
	});
	$('#password').textbox({
		type : "password",
		prompt : 'Password',
		required : true,
		missingMessage : "请输入密码"
	});

	$('.submit').linkbutton({

	});

})
