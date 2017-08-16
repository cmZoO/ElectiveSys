//启动gt的js
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
			$("#password").textbox('setValue',$.md5($("#password").val()));//赋值
			$("#form").submit();
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
	url : "/ElectiveSys/gt/register?t=" + (new Date()).getTime(), // 加随机数防止缓存
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