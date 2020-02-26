package com.durgasoft.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.durgasoft.beans.Student;
import com.durgasoft.factory.StudentServiceFactory;
import com.durgasoft.service.StudentService;


@WebServlet("*.do")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		StudentService stdService = StudentServiceFactory.getStudentService();
		
		RequestDispatcher rd = null;
		if(requestURI.endsWith("add.do")) {
			String sid = "";
			String sname = "";
			String sclass = "";
			String s1 = "";
			String s2 = "";
			String s3 = "";
			String s4 = "";
			String s5 = "";
			String s6 = "";
			String total = "";
			Student std = new Student();
			String status = "";
			sid = request.getParameter("sid");
			sname = request.getParameter("sname");
			sclass = request.getParameter("cls");
			s1 = request.getParameter("s1");
			s2 = request.getParameter("s2");
			s3 = request.getParameter("s3");
			s4 = request.getParameter("s4");
			s5 = request.getParameter("s5");
			s6 = request.getParameter("s6");
			total = request.getParameter("total");
			
			
			std.setSid(sid);
			std.setName(sname);
			std.setCls(sclass);
			std.setS1(s1);
			std.setS2(s2);
			std.setS3(s3);
			std.setS4(s4);
			std.setS5(s5);
			std.setS6(s6);
			std.setTotal(total);
			status = stdService.addStudent(std);
			
			if(status == "existed") {
				rd = request.getRequestDispatcher("existed.html");
				rd.forward(request, response);
			}
			
			if(status == "success") {
				rd = request.getRequestDispatcher("success.html");
				rd.forward(request, response);
			}
			
			if(status == "failure") {
				rd = request.getRequestDispatcher("failure.html");
				rd.forward(request, response);
			}

		}
		
		
		if(requestURI.endsWith("search.do")) {
			String sid = "";
			sid = request.getParameter("sid");
			Student std = new Student();
			std = stdService.searchStudent(sid);
			
			if(std != null) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				
				out.println("<html><body>");
				out.println("<table align='center' border='2'>");
				out.println("<h2>");
				out.println("<tr><td>SID:</td><td>"+std.getSid()+"</td></tr>");
				out.println("<tr><td>SNAME:</td><td>"+std.getName()+"</td></tr>");
				out.println("<tr><td>CLASS:</td><td>"+std.getCls()+"</td></tr>");
				out.println("<tr><td>S1:</td><td>"+std.getS1()+"</td></tr>");
				out.println("<tr><td>S2:</td><td>"+std.getS2()+"</td></tr>");
				out.println("<tr><td>S3:</td><td>"+std.getS3()+"</td></tr>");
				out.println("<tr><td>S4:</td><td>"+std.getS4()+"</td></tr>");
				out.println("<tr><td>S5:</td><td>"+std.getS5()+"</td></tr>");
				out.println("<tr><td>S6:</td><td>"+std.getS6()+"</td></tr>");
				out.println("<tr><td>TOTAL:</td><td>"+std.getTotal());
				out.println("</h2>");
				out.println("</table>");
				out.println("</body></html>");
			}
			else {
				rd = request.getRequestDispatcher("notexisted.html");
				rd.forward(request, response);
			}
			
		}
		
		
		if(requestURI.endsWith("editform.do")) {
		
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String sid = request.getParameter("sid");
			
			
			Student std = stdService.searchStudent(sid);
			if(std != null) {
			out.println("<html>");
			out.println("<head>"); 
			out.println("<script type='text/javascript'>");
			out.println("function sum(){");
			out.println("var s1 = document.getElementById('s1').value;");
			out.println("var s2 = document.getElementById('s2').value;");
			out.println("var s3 = document.getElementById('s3').value;");
			out.println("var s4 = document.getElementById('s4').value;");
			out.println("var s5 = document.getElementById('s5').value;");
			out.println("var s6 = document.getElementById('s6').value;");
			out.println("var result=parseInt(s1)+parseInt(s2)+parseInt(s3)+parseInt(s4)+parseInt(s5)+parseInt(s6);");
			out.println("if(!isNaN(result)){");
			out.println("document.getElementById('total').value = result;");
			out.println("}");
			out.println("}");
			out.println("</script>"); 
			out.println("</head>");
			out.println("<body>");
			out.println("<form method='post' action='update.do'>");
			out.println("<table align='center'>");
			out.println("<tr><td>Student Id:</td><td>"+sid+"</td></tr>");
			out.println("<input type='hidden' name='sid' value='"+sid+"'/>");
			out.println("<tr><td>Student Name</td><td><input type='text' name='sname' value='"+std.getName()+"'/></td></tr>");
			out.println("<tr><td>Student Class</td><td><input type='text' name='sclass' value='"+std.getCls()+"'/></td></tr>");
			out.println("<tr><td>S1</td><td><input type='text' name='s1' id='s1' onkeyup='sum()' value='"+std.getS1()+"'/></td></tr>");
			out.println("<tr><td>S2</td><td><input type='text' name='s2' id='s2' onkeyup='sum()' value='"+std.getS2()+"'/></td></tr>");
			out.println("<tr><td>S3</td><td><input type='text' name='s3' id='s3' onkeyup='sum()' value='"+std.getS3()+"'/></td></tr>");
			out.println("<tr><td>S4</td><td><input type='text' name='s4' id='s4' onkeyup='sum()' value='"+std.getS4()+"'/></td></tr>");
			out.println("<tr><td>S5</td><td><input type='text' name='s5' id='s5' onkeyup='sum()' value='"+std.getS5()+"'/></td></tr>");
			out.println("<tr><td>S6</td><td><input type='text' name='s6' id='s6' onkeyup='sum()' value='"+std.getS6()+"'/></td></tr>");
			out.println("<tr><td>TOTAL</td><td><input type='text' name='total' id='total' value='"+std.getTotal()+"'/></td></tr>");
			out.println("<tr><td><input type='submit' value='Update'/></td></tr>");
			out.println("</table></form>");
			out.println("</body></html>");
			}else {
				rd = request.getRequestDispatcher("notexisted.html");
				rd.forward(request, response);
			}
		}
		
		if(requestURI.endsWith("update.do")) {
			stdService = StudentServiceFactory.getStudentService();
			Student std = new Student();
			String sid = request.getParameter("sid");
			String sname = request.getParameter("sname");
			String sclass = request.getParameter("sclass");
			String s1 = request.getParameter("s1");
			String s2 = request.getParameter("s2");
			String s3 = request.getParameter("s3");
			String s4 = request.getParameter("s4");
			String s5 = request.getParameter("s5");
			String s6 = request.getParameter("s6");
			String total = request.getParameter("total");
			
			/*System.out.println(sid);
			System.out.println(sname);
			System.out.println(sclass);
			System.out.println(s1);
			System.out.println(s2);
			System.out.println(s3);
			System.out.println(s4);
			System.out.println(s5);
			System.out.println(s6);
			System.out.println(total);*/
			
			std.setSid(sid);
			std.setName(sname);
			std.setCls(sclass);
			std.setS1(s1);
			std.setS2(s2);
			std.setS3(s3);
			std.setS4(s4);
			std.setS5(s5);
			std.setS6(s6);
			std.setTotal(total);
								
			String status = stdService.updateStudent(std);
			if(status == "success") {
				rd = request.getRequestDispatcher("success.html");
				rd.forward(request, response);
			}
			
			if(status == "failure") {
				rd = request.getRequestDispatcher("failure.html");
				rd.forward(request, response);
			}	
		}
		
		
		if(requestURI.endsWith("delete.do")) {
			String sid = request.getParameter("sid");
			stdService = StudentServiceFactory.getStudentService();
			String status = stdService.deleteStudent(sid);
			
			if(status == "success") {
				rd = request.getRequestDispatcher("success.html");
				rd.forward(request, response);
			}
			
			if(status == "failure") {
				rd = request.getRequestDispatcher("failure.html");
				rd.forward(request, response);
			}
			if(status == "notexisted") {
				rd = request.getRequestDispatcher("notexisted.html");
				rd.forward(request, response);
			}
			
		}
	}

}	
