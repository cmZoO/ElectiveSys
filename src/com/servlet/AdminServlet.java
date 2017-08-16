/**   
* @Title: AdminServlet.java 
* @Package com.servlet 
* @Description: TODO(描述AdminServlet类) 
* @author zx583   
* @date 2017年4月26日 上午10:42:41 
* @version V1.0   
*/
package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bean.Admin;
import com.bean.Course;
import com.bean.Elective_time;
import com.bean.Plan;
import com.bean.Student;
import com.bean.Teacher;
import com.server.AdminServer;
import com.server.CourseServer;
import com.server.PlanServer;
import com.server.RecordServer;
import com.server.StudentServer;
import com.server.SystemServer;
import com.server.TeacherServer;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;
import com.util.CheckUtil;
import com.util.DateUtil;
import com.util.Page;
import com.util.RegexUtil;

/** 
* @ClassName: AdminServlet 
* @Description: TODO(管理员控制器) 
* @author zx583 
* @date 2017年4月26日 上午10:42:41 
*  
*/
@WebServlet("/admin/adminServlet")
public class AdminServlet extends HttpServlet {
	/* (非 Javadoc) 
	* Title: service
	* Description:
	* @param arg0
	* @param arg1
	* @throws ServletException
	* @throws IOException 
	* @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse) 
	*/
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
//		System.out.println(method);
		if (method != null) {
			switch (method) {
			case "userChange":
				userChange(request, response);
				break;
			case "userAdd":
				userAdd(request, response);
				break;
			case "getAdminJson":
				getAdminJson(request, response);
				break;
			case "deleteAdmin":
				deleteAdmin(request, response);
				break;
			case "teacherAdd":
				teacherAdd(request, response);
				break;
			case "getTeacherJson":
				getTeacherJson(request, response);
				break;
			case "teacherChange":
				teacherChange(request, response);
				break;
			case "studentAdd":
				studentAdd(request, response);
				break;
			case "getStudentJson":
				getStudentJson(request, response);
				break;
			case "studentChange":
				studentChange(request, response);
				break;
			case "planAdd":
				planAdd(request, response);
				break;
			case "getPlanJson":
				getPlanJson(request, response);
				break;
			case "planChange":
				planChange(request, response);
				break;
			case "planLoad":
				planLoad(request, response);
				break;
			case "timeChange":
				timeChange(request, response);
				break;
			case "courseChange":
				courseChange(request, response);
				break;
			case "CourseTeacherChange":
				CourseTeacherChange(request, response);
				break;
			case "planStart":
				planStart(request, response);
				break;
			case "planClose":
				planClose(request, response);
				break;
			case "getAllCourse":
				getAllCourse(request, response);
				break;
			case "deleteRecord":
				deleteRecord(request, response);
				break;
			case "downLine":
				downLine(request, response);
				break;
			default:
				break;
			}
		}
	}
	
	/**
	 * @throws IOException  
	* @Title: deleteAdmin 
	* @Description: TODO(删除一位管理员) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void deleteAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取id
		String id = request.getParameter("admin_id");
		
		//合法性判断
		if (id == null || !RegexUtil.isAllNum(id)) {
			PrintWriter out = response.getWriter();
			 JSONObject data = new JSONObject();
			 try {
			 data.put("success", "success");
			 data.put("message", "操作非法");
			 } catch (JSONException e) {
			 e.printStackTrace();
			 }
			 out.println(data.toString());
			 return;
		}
		
		//删除
		boolean result = new AdminServer().delete(id);
		
		//返回结果
		String message = "删除成功";
		
		if (!result) {
			message = "删除失败";
		}
		
		PrintWriter out = response.getWriter();
		 JSONObject data = new JSONObject();
		 try {
		 data.put("success", "success");
		 data.put("message", message);
		 } catch (JSONException e) {
		 e.printStackTrace();
		 }
		 out.println(data.toString());
	}

	/** 
	* @Title: downLine 
	* @Description: TODO(系统计划下线) 
	* @param @param request
	* @param @param response
	* @param @throws IOException - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void downLine(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取系统服务
		SystemServer server = new SystemServer();
		
		//系统无在线计划，返回错误
		if (!server.isLoaded()) {
			PrintWriter out = response.getWriter();
			 JSONObject data = new JSONObject();
			 try {
			 data.put("success", "success");
			 data.put("message", "系统当前未挂载计划");
			 } catch (JSONException e) {
			 e.printStackTrace();
			 }
			 out.println(data.toString());
			 return;
		}
		
		//下线
		server.deLoadPlan();
		
		//返回成功
		PrintWriter out = response.getWriter();
		 JSONObject data = new JSONObject();
		 try {
		 data.put("success", "success");
		 data.put("message", "下线成功");
		 } catch (JSONException e) {
		 e.printStackTrace();
		 }
		 out.println(data.toString());
	}

	/**
	 * @throws IOException  
	* @Title: deleteRecord 
	* @Description: TODO(删除记录) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void deleteRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取课程id
		String course_id = request.getParameter("course_id");
		
		//课程id合法性判断
		if (course_id == null || !RegexUtil.isAllNum(course_id)) {
			PrintWriter out = response.getWriter();
			 JSONObject data = new JSONObject();
			 try {
			 data.put("success", "success");
			 data.put("message", "操作非法");
			 } catch (JSONException e) {
			 e.printStackTrace();
			 }
			 out.println(data.toString());
			 return;
		}
		
		//获取服务
		RecordServer server = new RecordServer();
		
		//执行删除
		server.deleteRecordOfCourse(course_id);
		
		//处理结果并返回
		PrintWriter out = response.getWriter();
		 JSONObject data = new JSONObject();
		 try {
		 data.put("success", "success");
		 data.put("message", "删除成功");
		 } catch (JSONException e) {
		 e.printStackTrace();
		 }
		 out.println(data.toString());
	}

	/**
	 * @throws IOException  
	* @Title: getAllCourse 
	* @Description: TODO(获取选修课程) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void getAllCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取选修课程
		//如果系统有挂载选课计划则返回计划课程
		//否则返回所有
		List<Course> courses = null;
		if (!new SystemServer().isLoaded()) {
			courses = new CourseServer().getAllCourse();
		} else {
			courses = new SystemServer().getCourses();
		}
		
		//构造json
		JSONArray resultArray = new JSONArray();
		try {
			for (Course course : courses) {
				JSONObject json = new JSONObject();
				json.put("plan_id", course.getPlan().getPlan_id());
				json.put("plan_name", course.getPlan().getPlan_name());
				json.put("course_id", course.getCourse_id());
				json.put("course_name", course.getCourse_name());
				json.put("class_time", course.getClass_time());
				json.put("class_place", course.getClass_place());
				json.put("total", course.getTotal());
				String course_teac = "";
				for (Teacher t : course.getCourse_teac()) {
					if (course_teac.length() == 0) {
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
	* @Title: planClose 
	* @Description: TODO(计划结束方法) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void planClose(HttpServletRequest request, HttpServletResponse response) throws IOException {
		new SystemServer().changOpen(false);
		
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
	* @Title: planStart 
	* @Description: TODO(计划开始方法) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void planStart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		new SystemServer().changOpen(true);
		
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
	* @Title: CourseTeacherChange 
	* @Description: TODO(修改一个课程的教师) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void CourseTeacherChange(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取课程id与教师id
		String teachers = request.getParameter("teachersid");
		String course_id = request.getParameter("selectCourse");
		
		//合法性判断
		if (teachers == null || teachers.length() < 1 || course_id == null || !RegexUtil.isAllNum(course_id)) {
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
		
		//获取教师id
		String[] ts = teachers.split(",");
		CourseServer server = new CourseServer();
		List<Teacher> teacherList = new ArrayList<Teacher>();
		for (String s : ts) {
			teacherList.add(new Teacher(Integer.parseInt(s)));
		}
		
		//执行修改操作
		server.deleteTeacherForCourse(new Course(Integer.parseInt(course_id)));
		server.addTeachersForCourse(teacherList, new Course(Integer.parseInt(course_id)));
		
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
		 new SystemServer().reload();
	}

	/**
	 * @throws IOException  
	* @Title: courseChange 
	* @Description: TODO(修改选课课程) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void courseChange(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SystemServer server = new SystemServer();
		//判断系统是否挂载计划
		if (!server.isLoaded()) {
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
		
		//获取修改的课程
		String coursesString= request.getParameter("courses");
		
		List<Course> courses = new ArrayList<Course>();
		
		if (coursesString != null && coursesString.length() > 0) {
			try {
				JSONArray array = new JSONArray(coursesString);
				for (int i = 0, len = array.length(); i < len; i++) {
					JSONObject object = array.getJSONObject(i);
					Course course = new Course();
					
					String id = object.get("course_id").toString();
					if (id.length() > 0 && RegexUtil.isAllNum(id)) {
						course.setCourse_id(Integer.parseInt(id));
					}
					
					course.setCourse_name(object.getString("course_name"));
					
					String time = object.get("class_time").toString();
					if (time.length() == 1 && RegexUtil.isAllNum(time)) {
						course.setClass_time(Integer.parseInt(time));
					}
					course.setClass_place(object.getString("class_place"));
					
					String total = object.get("total").toString();
					if (total.length() > 0 && RegexUtil.isAllNum(total)) {
						course.setTotal(Integer.parseInt(total));
					}
					course.setPlan(new Plan(server.getPlan_id()));
					courses.add(course);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		//获取需要删除的课程以及需要更新的课程以及需要插入的课程
		List<Course> deleteCourse = server.getCourses();
		List<Course> updateCourse = new ArrayList<Course>();
		//遍历删除的课程以及需要插入的课程，提取出需要更新的课程
		for (int i = 0; i < deleteCourse.size(); i++) {
			Course cd = deleteCourse.get(i);
			for (int j = 0; j < courses.size(); j++) {
				Course ci = courses.get(j);
				if (cd.getCourse_id() == ci.getCourse_id()) {
					updateCourse.add(ci);
					deleteCourse.remove(i--);
					courses.remove(j--);
				}
			}
		}
		
		RecordServer recordServer = new RecordServer();
		for (Course c : deleteCourse) {
			if (recordServer.getRecordByCourse(c.getCourse_id().toString()).size() > 0) {
				PrintWriter out = response.getWriter();
				 JSONObject data = new JSONObject();
				 try {
				 data.put("success", "success");
				 data.put("message",  c.getCourse_name() + "存在选课记录，无法执行，请先将该课程的选课记录清空");
				 } catch (JSONException e) {
				 e.printStackTrace();
				 }
				 out.println(data.toString());
				 return;
			}
		}
		
		CourseServer courseServer = new CourseServer();
		//删除需要删除的课程
		for (Course c : deleteCourse) {
			courseServer.deleteCourseById(c.getCourse_id().toString());
		}
		
		//插入需要插入的课程
		for (Course c : courses) {
			courseServer.insertCourse(c);
		}
		
		//更新需要更新的课程
		for (Course c : updateCourse) {
			courseServer.updateCourse(c, c.getCourse_id().toString());
		}
		
		
		//输出结果
		PrintWriter out = response.getWriter();
		JSONObject data = new JSONObject();
		try {
			data.put("success", "success");
			data.put("message", "修改成功");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.println(data.toString());
		server.reload();
	}

	/**
	 * @throws IOException  
	* @Title: timeChange 
	* @Description: TODO(修改选课计划时间) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void timeChange(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SystemServer server = new SystemServer();
		
		//判断系统是否挂载计划
		if (!server.isLoaded()) {
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
		
		//获取时间
		String times = request.getParameter("times");
		
		List<Elective_time> elective_Times = new ArrayList<Elective_time>();
		
		if (times != null && times.length() > 0) {
			try {
				JSONArray array = new JSONArray(times);
				for (int i = 0, len = array.length(); i < len; i++) {
					JSONObject object = array.getJSONObject(i);
					Elective_time t = new Elective_time();
					t.setRegister_date(DateUtil.getDate(object.getString("register_date")));
					t.setStart_time(DateUtil.getTimestamp(object.getString("start_time")));
					t.setEnd_time(DateUtil.getTimestamp(object.getString("end_time")));
					t.setMax(object.getInt("max"));
					elective_Times.add(t);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		PlanServer planServer = new PlanServer();
		Integer plan_id = server.getPlan_id();
		server.deLoadPlan();
		
		planServer.deleteElectiveTimesForPlan(new Plan(plan_id));
		
		if (planServer.insertElectiveTimesForPlan(elective_Times, new Plan(plan_id))) {
			PrintWriter out = response.getWriter();
			JSONObject data = new JSONObject();
			try {
				data.put("success", "success");
				data.put("message", "修改成功");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.println(data.toString());
			server.onLoadPlan(plan_id.toString());
			return;
		}
		
		
		PrintWriter out = response.getWriter();
		 JSONObject data = new JSONObject();
		 try {
		 data.put("success", "success");
		 data.put("message", "修改失败");
		 } catch (JSONException e) {
		 e.printStackTrace();
		 }
		 out.println(data.toString());
		 server.onLoadPlan(plan_id.toString());
	}

	/**
	 * @throws IOException  
	* @Title: planLoad 
	* @Description: TODO(加载计划) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void planLoad(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取id
		String plan_id = request.getParameter("plan_id");
		//非法验证
		if (plan_id ==  null || !RegexUtil.isAllNum(plan_id)) {
			PrintWriter out = response.getWriter();
			 JSONObject data = new JSONObject();
			 try {
			 data.put("success", "success");
			 data.put("message", "加载失败");
			 } catch (JSONException e) {
			 e.printStackTrace();
			 }
			 out.println(data.toString());
			 return;
		}
		
		//加载计划
		new SystemServer().deLoadPlan();
		new SystemServer().onLoadPlan(plan_id);
		
		//返回结果
		PrintWriter out = response.getWriter();
		 JSONObject data = new JSONObject();
		 try {
		 data.put("success", "success");
		 data.put("message", "加载计划" + new SystemServer().getPlan_name() + "成功");
		 } catch (JSONException e) {
		 e.printStackTrace();
		 }
		 out.println(data.toString());
	}

	/**
	 * @throws IOException  
	* @Title: planChange 
	* @Description: TODO(修改选课计划) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void planChange(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String plan_id = request.getParameter("plan_id");
		String plan_name = request.getParameter("plan_name");
		String delay_time = request.getParameter("delay_time");

		//构造对应的对象
		Plan plan = new Plan();

		if (plan_name != null && plan_name.length() > 0) {
			plan.setPlan_name(plan_name);
		}
		if (delay_time != null && RegexUtil.isAllNum(delay_time)) {
			Integer num = Integer.parseInt(delay_time);
			if (num % 30 == 0) {
				plan.setDelay_drop_time(num);
			}
		}
		
		if (new PlanServer().updatePlan(plan, plan_id)) {
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

	/**
	 * @throws IOException  
	* @Title: getPlanJson 
	* @Description: TODO(获取计划的JSON数据) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void getPlanJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PlanServer server = new PlanServer();
		
		//获取分页数据
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		Page p = new Page(null, rows, server.getTotal());
		page = Math.min(p.getTotalPage(), page);
		p.setCurrentPage(page);
		
//		List<Plan> students = ;
//		
//		JSONArray resultArray = new JSONArray();
//		
//		if (students != null) {
//			for (Student student : students) {
//				resultArray.put(new JSONObject(student));
//			}
//		}
//		
//		JSONObject json = new JSONObject();
//		
//		try {
//			json.put("total", p.getTotalRows());
//			json.put("rows", resultArray);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		response.getWriter().write(json.toString());
		
		List<Plan> plans = server.getPagePlan(p);

		JSONArray resultArray = new JSONArray();

		if (plans != null) {
			try {
				for (Plan plan : plans) {
					JSONObject json = new JSONObject();
					json.put("plan_id", plan.getPlan_id());
					json.put("plan_name", plan.getPlan_name());
					json.put("delay_drop_time", plan.getDelay_drop_time());
					json.put("timeNum", plan.getElective_Times().size());
					json.put("coursesNum", new CourseServer().getCourseByPlan(plan.getPlan_id().toString()).size());
					
					resultArray.put(json);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		JSONObject json = new JSONObject();
		
		try {
			json.put("total", p.getTotalRows());
			json.put("rows", resultArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.getWriter().write(json.toString());
	}

	/**
	 * @throws IOException  
	* @Title: planAdd 
	* @Description: TODO(添加计划方法) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void planAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取值
		String plan_id = request.getParameter("plan_id");
		String plan_name = request.getParameter("plan_name");
		String delay_time = request.getParameter("delay_time");
		String times = request.getParameter("times");
		
		//值合法判断,并构造对象
		Plan plan = new Plan();
		if (plan_id != null && RegexUtil.isAllNum(plan_id)) {
			Integer num = Integer.parseInt(plan_id);
			plan.setPlan_id(num);
		} 
		
		if (plan_name != null && plan_name.length() > 0) {
			plan.setPlan_name(plan_name);
		}
		
		if (delay_time != null && RegexUtil.isAllNum(delay_time)) {
			Integer num = Integer.parseInt(delay_time);
			if (num % 10 == 0) {
				plan.setDelay_drop_time(num);
			}
		}
		
		if (times != null && times.length() > 0) {
			try {
				List<Elective_time> elective_Times = new ArrayList<Elective_time>();
				JSONArray array = new JSONArray(times);
				for (int i = 0, len = array.length(); i < len; i++) {
					JSONObject object = array.getJSONObject(i);
					Elective_time t = new Elective_time();
					t.setRegister_date(DateUtil.getDate(object.getString("register_date")));
					t.setStart_time(DateUtil.getTimestamp(object.getString("start_time")));
					t.setEnd_time(DateUtil.getTimestamp(object.getString("end_time")));
					t.setPlan(plan);
					elective_Times.add(t);
				}
				plan.setElective_Times(elective_Times);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		if (CheckUtil.nullBeanCheck(plan)) {
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
		
		//添加并处理结果
		if (new PlanServer().insertPlan(plan)) {
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

	/**
	 * @throws IOException  
	* @Title: studentChange 
	* @Description: TODO(学生信息修改) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void studentChange(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String date = request.getParameter("date");
		//构造对应的对象
		Student student = new Student();
		if (id != null && RegexUtil.isAllNum(id)) {
			student.setStu_id(Integer.parseInt(id));
		}
		if (name != null && name.length() > 0) {
			student.setStu_name(name);
		}
		if (password != null && password.length() > 0) {
			student.setPassword(password);
		}
		if (date != null) {
			String[] d = date.split("-");
			if (d.length == 3 && RegexUtil.isAllNum(d[0]) && RegexUtil.isAllNum(d[1]) && RegexUtil.isAllNum(d[2])) {
				student.setRegister_date(new Date(Integer.parseInt(d[0]) - 1900, Integer.parseInt(d[1]) - 1, Integer.parseInt(d[2])));
			}
		}
		
		
		if (new StudentServer().update(student, id)) {
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

	/**
	 * @throws IOException  
	* @Title: getStudentJson 
	* @Description: TODO(获取所有teacher用户的JSON格式数据) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void getStudentJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StudentServer server = new StudentServer();
		
		//获取分页数据
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		Page p = new Page(null, rows, server.getTotal());
		page = Math.min(p.getTotalPage(), page);
		p.setCurrentPage(page);
		
		List<Student> students = server.getPageUser(p);
		
		JSONArray resultArray = new JSONArray();
		
		if (students != null) {
			for (Student student : students) {
				resultArray.put(new JSONObject(student));
			}
		}
		
		JSONObject json = new JSONObject();
		
		try {
			json.put("total", p.getTotalRows());
			json.put("rows", resultArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.getWriter().write(json.toString());
	}

	/**
	 * @throws IOException  
	* @Title: studentAdd 
	* @Description: TODO(增加学生) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void studentAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取用户名和密码
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String date = request.getParameter("date");

		//构造对应的对象
		Student student = new Student();
		if (id != null && RegexUtil.isAllNum(id)) {
			student.setStu_id(Integer.parseInt(id));
		}
		if (name != null && name.length() > 0) {
			student.setStu_name(name);
		}
		if (password != null && password.length() > 0) {
			student.setPassword(password);
		}
		if (date != null) {
			String[] d = date.split("-");
			if (d.length == 3 && RegexUtil.isAllNum(d[0]) && RegexUtil.isAllNum(d[1]) && RegexUtil.isAllNum(d[2])) {
				student.setRegister_date(new Date(Integer.parseInt(d[0]) - 1900, Integer.parseInt(d[1]) - 1, Integer.parseInt(d[2])));
			}
		}

		if (new StudentServer().register(student)) {
			PrintWriter out = response.getWriter();
			JSONObject data = new JSONObject();
			try {
				data.put("success", "success");
				data.put("message", "添加成功");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.println(data.toString());
		} else {
			PrintWriter out = response.getWriter();
			JSONObject data = new JSONObject();
			try {
				data.put("success", "success");
				data.put("message", "添加失败");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.println(data.toString());
		}
	}

	/**
	 * @throws IOException  
	* @Title: teacherChange 
	* @Description: TODO(教师信息修改) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void teacherChange(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

	/**
	 * @throws IOException  
	* @Title: getTeacherJson 
	* @Description: TODO(获取所有teacher用户的JSON格式数据) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void getTeacherJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
		TeacherServer server = new TeacherServer();
		
		//获取分页数据
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		Page p = new Page(null, rows, server.getTotal());
		page = Math.min(p.getTotalPage(), page);
		p.setCurrentPage(page);
		
		List<Teacher> teachers = server.getPageUser(p);
		
		JSONArray resultArray = new JSONArray();
		
		if (teachers != null) {
			for (Teacher teacher : teachers) {
				resultArray.put(new JSONObject(teacher));
			}
		}
		
		JSONObject json = new JSONObject();
		
		try {
			json.put("total", p.getTotalRows());
			json.put("rows", resultArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.getWriter().write(json.toString());
	}

	/**
	 * @throws IOException  
	* @Title: teacherAdd 
	* @Description: TODO(添加教师) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void teacherAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取用户名和密码
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		//构造对应的对象
		Teacher teacher = new Teacher();
		if (id != null && RegexUtil.isAllNum(id)) {
			teacher.setTeac_id(Integer.parseInt(id));
		}
		if (name != null && name.length() > 0) {
			teacher.setTeac_name(name);
		}
		if (password != null && password.length() > 0) {
			teacher.setPassword(password);
		}
		
		if (new TeacherServer().register(teacher)) {
			PrintWriter out = response.getWriter();
			JSONObject data = new JSONObject();
			try {
				data.put("success", "success");
				data.put("message", "添加成功");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.println(data.toString());
		} else {
			PrintWriter out = response.getWriter();
			JSONObject data = new JSONObject();
			try {
				data.put("success", "success");
				data.put("message", "添加失败");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.println(data.toString());
		}
	}

	/**
	 * @throws IOException  
	* @Title: getAdminJson 
	* @Description: TODO(获取所有admin用户的JSON格式数据) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void getAdminJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AdminServer server = new AdminServer();
		
		//获取分页数据
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		Page p = new Page(null, rows, server.getTotal());
		page = Math.min(p.getTotalPage(), page);
		p.setCurrentPage(page);
		
		List<Admin> admins = server.getPageUser(p);
		
		JSONArray resultArray = new JSONArray();
		
		if (admins != null) {
			for (Admin admin : admins) {
				resultArray.put(new JSONObject(admin));
			}
		}
		
		JSONObject json = new JSONObject();
		
		try {
			json.put("total", p.getTotalRows());
			json.put("rows", resultArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.getWriter().write(json.toString());
	}

	/**
	 * @throws IOException  
	* @Title: userAdd 
	* @Description: TODO(添加用户) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void userAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取用户名和密码
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		//构造对应的对象
		Admin admin = new Admin();
		if (name != null && name.length() > 0) {
			admin.setAdmin_name(name);
		}
		if (password != null && password.length() > 0) {
			admin.setPassword(password);
		}
		
		if (new AdminServer().register(admin)) {
			 PrintWriter out = response.getWriter();
			 JSONObject data = new JSONObject();
			 try {
			 data.put("success", "success");
			 data.put("message", "添加成功");
			 } catch (JSONException e) {
			 e.printStackTrace();
			 }
			 out.println(data.toString());
		} else {
			PrintWriter out = response.getWriter();
			 JSONObject data = new JSONObject();
			 try {
			 data.put("success", "success");
			 data.put("message", "添加失败");
			 } catch (JSONException e) {
			 e.printStackTrace();
			 }
			 out.println(data.toString());
		}
	}

	/**
	 * @throws IOException  
	* @Title: userChange 
	* @Description: TODO(用户信息修改) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void userChange(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		Admin admin = new Admin();
		if (name != null && name.length() > 0) {
			admin.setAdmin_name(name);
		}
		if (password != null && password.length() > 0) {
			admin.setPassword(password);
		}
		
		if (new AdminServer().update(admin, id)) {
			request.getSession().setAttribute("admin", admin);
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
