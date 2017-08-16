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

import com.bean.Course;
import com.bean.Elective_time;
import com.bean.Teacher;
import com.inter.ElectiveServer;
import com.server.SystemServer;
import com.util.DateUtil;
import com.util.Page;

/**
 * Servlet implementation class SystemServlet
 */
@WebServlet("/SystemServlet")
public class SystemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SystemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
    	// TODO Auto-generated method stub
    	String method = request.getParameter("method");
		if (method != null) {
			switch (method) {
			case "isLoad":
				isLoad(request, response);
				break;
			case "getElectiveTimes":
				getElectiveTimes(request, response);
				break;
			case "getCourse":
				getCourse(request, response);
				break;
			case "isOpen":
				isOpen(request, response);
				break;
			case "getElectiveJson":
				getElectiveJson(request, response);
				break;
			default:
				break;
			}
		}
    }

	/**
	 * @throws IOException  
	* @Title: getElectiveJson 
	* @Description: TODO(获取选课对象json) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void getElectiveJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//合法性判断
		SystemServer sys = new SystemServer();
		if (!sys.isOpen()) {
			return;
		}
		
		//获取选课服务列表
		List<ElectiveServer> servers = sys.getElective_servers();
		
		//初始化结果
		JSONArray resultArray = new JSONArray();
		
		//获取分页数据
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		Page p = new Page(null, rows, servers.size());
		page = Math.min(p.getTotalPage(), page);
		p.setCurrentPage(page);
		
		//构造JSON返回
		//构造JSON
		try {
			for (int index = p.getFirstRowOfCurrentPage() - 1, i = 0; i < rows && index + i < servers.size(); i++) {
				ElectiveServer server = servers.get(index + i);
				JSONObject json = new JSONObject();
				Course course = sys.getCourse(server.getCourse_id());
				
				json.put("course_id", course.getCourse_id());
				json.put("course_name", course.getCourse_name());
				json.put("class_time", course.getClass_time());
				json.put("class_place", course.getClass_place());
				json.put("course_size", course.getTotal());
				json.put("total", server.getTotal());
				json.put("nexttotal", server.getNextToal());
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

		JSONObject json = new JSONObject();
		
		try {
			json.put("total", servers.size());
			json.put("rows", resultArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.getWriter().write(json.toString());
		
	}

	/**
	 * @throws IOException  
	* @Title: isOpen 
	* @Description: TODO(开始选课计划) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void isOpen(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		 JSONObject data = new JSONObject();
		 try {
		 data.put("success", "success");
		 data.put("message", new SystemServer().isOpen());
		 } catch (JSONException e) {
		 e.printStackTrace();
		 }
		 out.println(data.toString());
	}

	/**
	 * @throws IOException  
	* @Title: getCourse 
	* @Description: TODO(获取当前选修计划的所有课程的JSON) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void getCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//初始化结果
		JSONArray resultArray = new JSONArray();
		
		//获取选修课程
		List<Course> courses = new ArrayList<Course>();
		
		SystemServer server = new SystemServer();
		if (server.isLoaded()) {
			courses = server.getCourses();
		}
		
		//构造JSON
		try {
			for (Course course : courses) {
				JSONObject json = new JSONObject();

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
	* @Title: getElectiveTimes 
	* @Description: TODO(获取选课时间的JSON数据) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void getElectiveTimes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//初始化结果
		JSONArray resultArray = new JSONArray();
	
		//获取选课时间
		List<Elective_time> times = new ArrayList<Elective_time>();
		
		SystemServer server = new SystemServer();
		if (server.isLoaded()) {
			times = server.getElective_Times();
		}
		
		//构造JSON
		try {
			for (Elective_time time : times) {
				JSONObject json = new JSONObject();
				
				json.put("register_date", time.getRegister_date());
				json.put("start_time", time.getStart_time());
				json.put("end_time", time.getEnd_time());
				json.put("max", time.getMax());
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
	* @Title: isLoad 
	* @Description: TODO(获取系统是否加载计划) 
	* @param @param request
	* @param @param response - 设定文件 
	* @return void - 返回类型 
	* @throws 
	*/
	private void isLoad(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		 JSONObject data = new JSONObject();
		 try {
		 data.put("success", "success");
		 data.put("message", new SystemServer().isLoaded());
		 } catch (JSONException e) {
		 e.printStackTrace();
		 }
		 out.println(data.toString());
	}
}
