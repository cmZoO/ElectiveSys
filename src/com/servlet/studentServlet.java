package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSpinnerUI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bean.Course;
import com.bean.ElectiveRecord;
import com.bean.Student;
import com.bean.Teacher;
import com.inter.ElectiveServer;
import com.server.RecordServer;
import com.server.StudentServer;
import com.server.SystemServer;

/**
 * Servlet implementation class studentServlet
 */
@WebServlet("/student/studentServlet")
public class studentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
//		System.out.println(method);
		if (method != null) {
			switch (method) {
			case "getHistory":
				getHistory(request, response);
				break;
			case "getChoose":
				getChoose(request, response);
				break;
			case "deChoose":
				deChoose(request, response);
				break;
			case "passwordChange":
				passwordChange(request, response);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * @throws IOException  
	* @Title: passwordChange 
	* @Description: TODO(修改密码) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void passwordChange(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取传过来的密码
		String oldpassword = request.getParameter("oldpassword");
		String password = request.getParameter("password");
		
		//合法性判断
		if (oldpassword == null || password == null || oldpassword.length() < 1 || password.length() < 1) {
			PrintWriter out = response.getWriter();
			JSONObject data = new JSONObject();
			try {
				data.put("success", "success");
				data.put("message", "修改失败");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.println(data.toString());
			return;
		}
		
		//修改密码
		if (!oldpassword.equals(password)) {
			Student student = (Student) request.getSession().getAttribute("student");
			
			if (student == null || !student.getPassword().equals(oldpassword)) {
				PrintWriter out = response.getWriter();
				JSONObject data = new JSONObject();
				try {
					data.put("success", "success");
					data.put("message", "旧密码错误，请重新输入");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				out.println(data.toString());
				return;
			}
			
			new StudentServer().changePassword(student.getStu_id().toString(), password);
		}
		
		//返回结果
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			data.put("success", "success");
			data.put("message", "修改成功");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.println(data.toString());
	}

	/**
	 * @throws IOException  
	* @Title: deChoose 
	* @Description: TODO(学生退选一门课) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void deChoose(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SystemServer server = new SystemServer();
		
		//获取退选的课程的id
		String course_id = request.getParameter("course_id");
		
		
		//合法性判断
		if (course_id == null || server.getCourse(course_id) == null) {
			PrintWriter out = response.getWriter();
			JSONObject data = new JSONObject();
			try {
				data.put("success", "success");
				data.put("message", "退选失败");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.println(data.toString());
			return;
		}
		
		//获取退选服务
		//进行退选操作
		Student student = (Student) request.getSession().getAttribute("student");
		String result = server.getElectiveServer(course_id).dechoice(student.getStu_id().toString());
		
		//构造结果返回
		if (result.equals(ElectiveServer.fail)) {
			PrintWriter out = response.getWriter();
			JSONObject data = new JSONObject();
			try {
				data.put("success", "success");
				data.put("message", "退选失败");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.println(data.toString());
			return;
		}
		
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			data.put("success", "success");
			data.put("message", "退选成功");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.println(data.toString());
		return;
	}

	/**
	 * @throws IOException  
	* @Title: getChoose 
	* @Description: TODO(获取当前计划该学生已选课程) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void getChoose(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		if (!new SystemServer().isLoaded()) {
			return;
		}
		
		//获取数据
		RecordServer server = new RecordServer();
		List<ElectiveRecord> courseList = server.getRecordByStu(((Student)request.getSession().getAttribute("student")).getStu_id().toString());
		
		request.getSession().setAttribute("selected", courseList);
		
		//构造JSON返回
		//初始化结果
		JSONArray resultArray = new JSONArray();
		Integer plan_id = new SystemServer().getPlan_id();
		for (int i = 0; i < courseList.size(); i++) {
			ElectiveRecord record = courseList.get(i);
			if (record.getCourse().getPlan().getPlan_id().compareTo(plan_id) == 0) {
				Course course = record.getCourse();
				JSONObject json = new JSONObject();
				try {
					json.put("course_name", course.getCourse_name());
					json.put("class_time", course.getClass_time());
					
					resultArray.put(json);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				courseList.remove(i--);
			}
		}
		

		response.getWriter().write(resultArray.toString());
	}

	/**
	 * @throws IOException  
	* @Title: getHistory 
	* @Description: TODO(获取历史记录) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void getHistory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取数据
		RecordServer server = new RecordServer();
		List<ElectiveRecord> courseList = server.getRecordByStu(((Student)request.getSession().getAttribute("student")).getStu_id().toString());
		
		//构造JSON返回
		//初始化结果
		JSONArray resultArray = new JSONArray();
		Integer plan_id = new SystemServer().getPlan_id();
		for (ElectiveRecord record : courseList) {
			if (record.getCourse().getPlan().getPlan_id().compareTo(plan_id) == 0) {
				continue;
			}
			Course course = record.getCourse();
			JSONObject json = new JSONObject();
			try {
				json.put("plan_name", course.getPlan().getPlan_name());
				json.put("course_name", course.getCourse_name());
				json.put("class_time", course.getClass_time());
				json.put("class_place", course.getClass_place());
				String course_teac = null;
				for (Teacher t : course.getCourse_teac()) {
					if (course_teac == null) {
						course_teac = t.getTeac_name();
					} else {
						course_teac += "," + t.getTeac_name();
					}
				}
				json.put("course_teac", course_teac);
				
				resultArray.put(json);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		response.getWriter().write(resultArray.toString());
	}

}
