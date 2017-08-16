package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bean.Course;
import com.bean.ElectiveRecord;
import com.bean.Plan;
import com.bean.Student;
import com.bean.Teacher;
import com.server.CourseServer;
import com.server.RecordServer;
import com.server.SystemServer;
import com.server.TeacherServer;
import com.util.RegexUtil;

/**
 * Servlet implementation class TeacherServlet
 */
@WebServlet("/teacher/teacherServlet")
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherServlet() {
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
			case "userChange":
				userChange(request, response);
				break;
			case "getMyCourse":
				getMyCourse(request, response);
				break;
			case "getAllCourse":
				getAllCourse(request, response);
				break;
			case "getMyStudent":
				getMyStudent(request, response);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * @throws IOException  
	* @Title: getMyStudent 
	* @Description: TODO(获取的教师的某个课程的学生) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void getMyStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取课程名称
		String course_id = request.getParameter("course_id");
		
		//课程名称合法性判断
		if (course_id == null || !RegexUtil.isAllNum(course_id)) {
			return;
		}
		
		//根据课程名称查询结果
		List<ElectiveRecord> records = new RecordServer().getRecordByCourse(course_id);
		
		//处理结果
		if (records == null) {
			return;
		}

		//构造json返回
		JSONArray resultArray = new JSONArray();
		
		for (ElectiveRecord record : records) {
			resultArray.put(new JSONObject(record.getStudent()));
		}
		
		response.getWriter().write(resultArray.toString());
	}

	/**
	 * @throws IOException  
	* @Title: getAllCourse 
	* @Description: TODO(获取我的所有课程) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void getAllCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取课程服务
		CourseServer server = new CourseServer();

		//根据自己的id和计划id查出自己的课程
		Integer id = ((Teacher) request.getSession().getAttribute("teacher")).getTeac_id();
		List<Course> courses = server.getCourseByTeacPlan(new Teacher(id), null);

		//构造成JSON返回
		//初始化结果
		JSONArray resultArray = new JSONArray();
		try {
			for (Course course : courses) {
				JSONObject json = new JSONObject();

				json.put("course_id", course.getCourse_id());
				json.put("course_name", course.getCourse_name());
				json.put("plan_name", course.getPlan().getPlan_name());
				json.put("class_time", course.getClass_time());
				json.put("class_place", course.getClass_place());
				json.put("total", course.getTotal());
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
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.getWriter().write(resultArray.toString());
	}

	/**
	 * @throws IOException  
	* @Title: getMyCourse 
	* @Description: TODO(获取当前选课计划的我的课程) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void getMyCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取课程服务
		CourseServer server = new CourseServer();
		
		//根据自己的id和计划id查出自己的课程
		Integer id = ((Teacher) request.getSession().getAttribute("teacher")).getTeac_id();
		List<Course> courses = server.getCourseByTeacPlan(new Teacher(id), new Plan(new SystemServer().getPlan_id()));
		
		//构造成JSON返回
		//初始化结果
		JSONArray resultArray = new JSONArray();
		try {
			for (Course course : courses) {
				JSONObject json = new JSONObject();

				json.put("course_id", course.getCourse_id());
				json.put("course_name", course.getCourse_name());
				json.put("class_time", course.getClass_time());
				json.put("class_place", course.getClass_place());
				json.put("total", course.getTotal());
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
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.getWriter().write(resultArray.toString());
	}

	/**
	 * @throws IOException  
	* @Title: userChange 
	* @Description: TODO(用户信息修改方法) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void userChange(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		Teacher teacher = new Teacher();
		if (name != null && name.length() > 0) {
			teacher.setTeac_name(name);
		}
		if (password != null && password.length() > 0) {
			teacher.setPassword(password);
		}
		
		if (new TeacherServer().update(teacher, id)) {
			request.getSession().setAttribute("teacher", teacher);
			PrintWriter out = response.getWriter();
			 JSONObject data = new JSONObject();
			 try {
			 data.put("success", "success");
			 data.put("message", "修改成功");
			 } catch (JSONException e) {
			 e.printStackTrace();
			 }
			 out.println(data.toString());
		} else {
			PrintWriter out = response.getWriter();
			 JSONObject data = new JSONObject();
			 try {
			 data.put("success", "success");
			 data.put("message", "修改失败");
			 } catch (JSONException e) {
			 e.printStackTrace();
			 }
			 out.println(data.toString());
		}
	}

}
