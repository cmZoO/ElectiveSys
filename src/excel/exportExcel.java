package excel;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class exportExcel
 */
@WebServlet("/exportExcel")
public class exportExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public exportExcel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.reset();  
         try {  
             request.setCharacterEncoding("UTF-8");  
         } catch (UnsupportedEncodingException e1) {  
         // TODO Auto-generated catch block  
             e1.printStackTrace();  
         }  
         String hf=request.getParameter("hfs");  
         String type=request.getParameter("type");  
         String exportname="grid";  
         try {  
             if(type.equals("excel"))  
             {  
                 exportname+=".xls";  
                 response.setHeader("Content-disposition", "attachment; filename="+java.net.URLEncoder.encode(exportname, "UTF-8")+"");  
                 response.setContentType("application/msexcel;charset=utf-8");  
             }  
             else if(type.equals("word"))  
             {  
                 exportname+=".doc";  
                 response.setHeader("Content-disposition", "attachment; filename="+java.net.URLEncoder.encode(exportname, "UTF-8")+"");  
                 response.setContentType("application/ms-word;charset=UTF-8");  
             }  
         } catch (UnsupportedEncodingException e) {  
         // TODO Auto-generated catch block  
             e.printStackTrace();  
         }  
         PrintWriter out;  
         try {  
             out = response.getWriter();  
             out.println(hf);  
             out.close();  
         } catch (IOException e) {  
             // TODO Auto-generated catch block  
             e.printStackTrace();  
         }  
         
         response.sendRedirect("./errorpage/exportError.jsp");
	}

}
