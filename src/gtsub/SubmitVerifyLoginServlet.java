package gtsub;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bean.ElectiveRecord;
import com.bean.Elective_time;
import com.bean.Student;
import com.inter.ElectiveServer;
import com.server.SystemServer;

import sdk.GeetestLib;

/**
 * 使用post方式，返回验证结果, request表单中必须包含challenge, validate, seccode
 */
@WebServlet("/student/ajax-validate")
public class SubmitVerifyLoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		GeetestLib gtSdk = new GeetestLib(GeetestSubmitConfig.getGeetest_id(), GeetestSubmitConfig.getGeetest_key(),
				GeetestSubmitConfig.isnewfailback());

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
			choose(request, response);
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

	// 选课操作方法
	private void choose(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		SystemServer server = new SystemServer();
		
		if (!server.isOpen()) {
			PrintWriter out = response.getWriter();
			 JSONObject data = new JSONObject();
			 try {
			 data.put("success", "success");
			 data.put("message", "选课未开始");
			 } catch (JSONException e) {
			 e.printStackTrace();
			 }
			 out.println(data.toString());
			 return;
		}
		
		String choose = request.getParameter("choose");
		List<String> courses = new ArrayList<String>();
		
		if (choose != null && choose.length() > 0) {
			try {
				JSONArray array = new JSONArray(choose);
				for (int i = 0, len = array.length(); i < len; i++) {
					JSONObject object = array.getJSONObject(i);
					courses.add(object.getInt("course_id") + "");
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		
		//获取student
		Student student = (Student) request.getSession().getAttribute("student");
		//这里使用过滤器，肯定不为空
		
		//遍历传过来的课程，进行选中，并构造返回信息
		
		//判断是否在选课时间内
		//获取所有选课时间
		List<Elective_time> times = server.getElective_Times();
		//遍历时间
		Elective_time chooseTime = null;
		for (Elective_time time : times) {
			//选课时间中有关于该年级学生的时间计划
			if (time.getRegister_date().getYear() == student.getRegister_date().getYear()) {
				chooseTime = time;
				break;
			}
		}
		//得出结论
		//不在选课范围内
		if (chooseTime == null) {
			//构造不在选课时间内返回信息
			PrintWriter out = response.getWriter();
			 JSONObject data = new JSONObject();
			 try {
			 data.put("success", "success");
			 data.put("message", "你不在本次选课计划学生范围内");
			 } catch (JSONException e) {
			 e.printStackTrace();
			 }
			 out.println(data.toString());
			 return;
		}
		//选课时间未到
		if (chooseTime.getStart_time().after(new Date()) || chooseTime.getEnd_time().before(new Date())) {
			//构造不在选课时间内返回信息
			PrintWriter out = response.getWriter();
			 JSONObject data = new JSONObject();
			 try {
			 data.put("success", "success");
			 data.put("message", "你的选课时间为"+chooseTime.getStart_time()+"-"+chooseTime.getEnd_time());
			 } catch (JSONException e) {
			 e.printStackTrace();
			 }
			 out.println(data.toString());
			 return;
		}
		//在选课时间内,进行选课
		List<ElectiveRecord> selected = (List<ElectiveRecord>) request.getSession().getAttribute("selected");
		String results = "";
		for (String course : courses) {
			String result = server.getCourse(course).getCourse_name() + "选课:";
			//判断选课个数是否超了
			if (selected.size() >= chooseTime.getMax()) {
				result += "失败,个数超限制,最多" + chooseTime.getMax() + "个\r\n";
				results += result;
				continue;
			}
			
			//判断该课程是否已经选过了
			boolean f1 = false;
			for (ElectiveRecord record : selected) {
				if (record.getCourse().getCourse_name().equals(server.getCourse(course).getCourse_name())) {
					result += "失败,你已选过该课\r\n";
					f1 = true;
					break;
				}
				if (record.getCourse().getClass_time() == server.getCourse(course).getClass_time()) {
					result += "失败,课程时间冲突\r\n";
					f1 = true;
					break;
				}
			}
			if (f1) {
				results += result;
				continue;
			}
			
			//选该课
			//获取选课结果
			String choceResult = server.getElectiveServer(course).choice(student.getStu_id().toString());
			
			//根据选课结构构造返回信息
			if (choceResult.equals(ElectiveServer.success)) {
				result += "成功\r\n";
				results += result;
				selected.add(new ElectiveRecord(student, server.getCourse(course)));
				continue;
			}
			
			result += "失败,再接再励\r\n";
			
			results += result;
		}
		
		PrintWriter out = response.getWriter();
		 JSONObject data = new JSONObject();
		 try {
		 data.put("success", "success");
		 data.put("message", results);
		 } catch (JSONException e) {
		 e.printStackTrace();
		 }
		 out.println(data.toString());
	}
}
