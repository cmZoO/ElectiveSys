package gtsub;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sdk.GeetestLib;


/**
 * 使用Get的方式返回challenge和capthca_id,此方式以实现前后端完全分离的开发模式
 *
 */
@WebServlet("/student/register")
public class StartSubmitCaptchaServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		GeetestLib gtSdk = new GeetestLib(GeetestSubmitConfig.getGeetest_id(), GeetestSubmitConfig.getGeetest_key(), 
				GeetestSubmitConfig.isnewfailback());

		String resStr = "{}";
		
		//自定义userid
		String userid = "test";

		//进行验证预处理
		int gtServerStatus = gtSdk.preProcess(userid);
		
		//将服务器状态设置到session中
		request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
		//将userid设置到session中
		request.getSession().setAttribute("userid", userid);
		
		resStr = gtSdk.getResponseStr();

		PrintWriter out = response.getWriter();
		out.println(resStr);

	}
}