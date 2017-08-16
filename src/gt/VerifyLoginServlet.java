package gt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Admin;
import com.bean.Student;
import com.bean.Teacher;
import com.server.AdminServer;
import com.server.StudentServer;
import com.server.TeacherServer;

import sdk.GeetestLib;

/**
 * 使用post方式，返回验证结果, request表单中必须包含challenge, validate, seccode
 */
@WebServlet("/gt/ajax-validate")
public class VerifyLoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),
				GeetestConfig.isnewfailback());

		String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
		String validate = request.getParameter(GeetestLib.fn_geetest_validate);
		String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);

		// 从session中获取gt-server状态
		int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);

		// 从session中获取userid
		String userid = (String) request.getSession().getAttribute("userid");

		int gtResult = 0;

		if (gt_server_status_code == 1) {
			// gt-server正常，向gt-server进行二次验证

			gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, userid);
//			System.out.println(gtResult);
		} else {
			// gt-server非正常情况下，进行failback模式验证

//			System.out.println("failback:use your own server captcha validate");
			gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
//			System.out.println(gtResult);
		}
		
//		int gtResult = 1;
		
		if (gtResult == 1) {
			// 验证成功
			// PrintWriter out = response.getWriter();
			// JSONObject data = new JSONObject();
			// try {
			// data.put("status", "success");
			// data.put("version", gtSdk.getVersionInfo());
			// } catch (JSONException e) {
			// e.printStackTrace();
			// }
			// out.println(data.toString());
			
			//跳转登陆操作
			login(request, response);
		} else {
			// // 验证失败
			// JSONObject data = new JSONObject();
			// try {
			// data.put("status", "fail");
			// data.put("version", gtSdk.getVersionInfo());
			// } catch (JSONException e) {
			// e.printStackTrace();
			// }
			// PrintWriter out = response.getWriter();
			// out.println(data.toString());
			response.sendRedirect("../errorpage/VerifyError.html");
		}

	}

	// 登陆操作方法
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取身份认证
		String identity = request.getParameter("identity");

		//不同身份执行不同方法
		if (identity != null) {
			switch (identity) {
			case "0":
				//登陆成功,跳转对应页面并退出
				if (adminLogin(request, response)) {
					//跳转页面
					response.sendRedirect("../admin/adminMain.jsp");
					return;
				}
				break;
			case "1":
				if (studentLogin(request, response)) {
					//跳转页面
					response.sendRedirect("../student/studentMain.jsp");
					return;
				}
				break;
			case "2":
				if (teacherLogin(request, response)) {
					//跳转页面
					response.sendRedirect("../teacher/teacherMain.jsp");
					return;
				}
				break;
			case "3":
				if (anybodyLogin(request, response)) {
					//跳转页面
					response.sendRedirect("../showcourse.jsp");
					return;
				}
				break;
			default:
				break;
			}
		}
		
		//登陆失败,跳转登陆错误界面
		response.sendRedirect("../errorpage/LoginError.jsp");
	}
	
	/** 
	* @Title: adminLogin 
	* @Description: TODO(管理员登陆方法) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private boolean adminLogin(HttpServletRequest request, HttpServletResponse response) {
//		System.out.println("管理员登陆方法");
		//获取用户名
		String username = request.getParameter("username");
		//获取密码
		String password = request.getParameter("password");
		
		//用户名密码合法性判断
		if (username == null || password == null) {
			return false;
		}
		
		//在数据库中查询该用户
		Admin admin = new AdminServer().getUserById(username);
		
		//处理结果
		//没有用户名为username的用户
		if (admin == null) {
			return false;
		}
		
		//用户名密码错误
		if (!admin.getPassword().equals(password)) {
			return false;
		}
		
		//将用户放到session中
		request.getSession().setAttribute("admin", admin);
		
		return true;
	}

	/** 
	* @Title: studentLogin 
	* @Description: TODO(学生登陆方法) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private boolean studentLogin(HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("学生登陆方法");
		//获取用户名
		String username = request.getParameter("username");
		//获取密码
		String password = request.getParameter("password");

		//用户名密码合法性判断
		if (username == null || password == null) {
			return false;
		}

		//在数据库中查询该用户
		Student student = new StudentServer().getUserById(username);

		//处理结果
		//没有用户名为username的用户
		if (student == null) {
			return false;
		}

		//用户名密码错误
		if (!student.getPassword().equals(password)) {
			return false;
		}

		//将用户放到session中
		request.getSession().setAttribute("student", student);

		return true;
	}
	
	/** 
	* @Title: teacherLogin 
	* @Description: TODO(老师登陆方法) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private boolean teacherLogin(HttpServletRequest request, HttpServletResponse response) {
	//	System.out.println("老师登陆方法");
		//获取用户名
		String username = request.getParameter("username");
		//获取密码
		String password = request.getParameter("password");

		//用户名密码合法性判断
		if (username == null || password == null) {
			return false;
		}

		//在数据库中查询该用户
		Teacher teacher = new TeacherServer().getUserById(username);

		//处理结果
		//没有用户名为username的用户
		if (teacher == null) {
			return false;
		}

		//用户名密码错误
		if (!teacher.getPassword().equals(password)) {
			return false;
		}

		//将用户放到session中
		request.getSession().setAttribute("teacher", teacher);

		return true;
	}
	
	/** 
	* @Title: anybodyLogin 
	* @Description: TODO(游客登陆方法) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private boolean anybodyLogin(HttpServletRequest request, HttpServletResponse response) {
		return true;
	}
}
