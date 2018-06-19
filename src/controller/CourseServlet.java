package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CourseDAO;
import dao.ResourceDAO;
import model.Resource;
import sun.java2d.pipe.RenderQueue;

/**
 * Servlet implementation class CourseServlet
 */
//@WebServlet("/Course")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String form = request.getParameter("form");
		if(form.equals("add")){
			String name = request.getParameter("name");
			String courseid = request.getParameter("courseid");
			CourseDAO cd = new CourseDAO();
			String msg = null;
			if(!name.isEmpty() && !courseid.isEmpty()){
			try {
				msg = cd.addCourse(courseid, name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else{
				msg = "Field(s) empty !!";
			}
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("addCourse.jsp").forward(request, response);
			}
		
		if(form.equals("viewallcourses")){
			CourseDAO cd = new CourseDAO();
			
			ArrayList<String[]> al = null;
			try{
				al = cd.allCourses();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			request.setAttribute("list", al);
			request.getRequestDispatcher("viewCourses.jsp").forward(request, response);
		}
		
		if(form.equals("courseTeach")){
			String courseid = request.getParameter("courseid");
			HttpSession session = request.getSession(false);
			String netid = (String)session.getAttribute("netid");
			String name = (String)session.getAttribute("firstname");
			ArrayList<String[]> al = null;
			CourseDAO cd = new CourseDAO();
			try {
				 cd.courseTeach(name, netid, courseid);
				 al = cd.allCourses();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("list", al);
			request.getRequestDispatcher("viewCourses.jsp").forward(request, response);
			
		}
		
		if(form.equals("mycourses")){
           CourseDAO cd = new CourseDAO();
			HttpSession session = request.getSession(false);
			ArrayList<String[]> al = null;
			try{
				al = cd.myCourses((String)session.getAttribute("netid"));
			}catch(Exception e){
				e.printStackTrace();
			}
			request.setAttribute("list", al);
			request.getRequestDispatcher("viewCourses.jsp").forward(request, response);
		}
		
		
		if(form.equals("mycourses1")){
			String op = request.getParameter("det");
	           CourseDAO cd = new CourseDAO();
				HttpSession session = request.getSession(false);
				ArrayList<String[]> al = null;
				try{
					al = cd.myCourses((String)session.getAttribute("netid"));
				}catch(Exception e){
					e.printStackTrace();
				}
				request.setAttribute("det", op);
				request.setAttribute("list", al);
				request.getRequestDispatcher("syllabus.jsp").forward(request, response);
			}
		
		if(form.equals("postDetails")){
			String op = request.getParameter("detail");
			String courseid = request.getParameter("courseid");
			String data = request.getParameter("data");
			HttpSession session = request.getSession(false);
			CourseDAO cd = new CourseDAO();
			ArrayList<String[]> al = null;
			System.out.println(data);
			try{
				if(data!=null && !data.isEmpty()){
					String email = null;
					String office = null;
					if(op.equals("ta")){
						email = request.getParameter("email");
						office = request.getParameter("officehour");
					}
				cd.postDetails(op, courseid, data, email, office);
				}
				al = cd.myCourses((String)session.getAttribute("netid"));
			}catch(SQLException e){
				e.printStackTrace();
			}
			request.setAttribute("det", op);
			request.setAttribute("list", al);
			request.getRequestDispatcher("syllabus.jsp").forward(request, response);
		
		}
		
		if(form.equals("enroll")){
			String coursename = request.getParameter("course");
			HttpSession se = request.getSession();
			CourseDAO cd = new CourseDAO();
			String msg = null;
			ArrayList<String[]> al = null;
			try{
				msg = cd.enroll(coursename, (String)se.getAttribute("netid"));
				al = cd.allCourses();
			}catch(SQLException e){
				e.printStackTrace();
				msg = "Failed to entroll";
			}
			request.setAttribute("msg", msg);
			request.setAttribute("list", al);
			request.getRequestDispatcher("viewCourses.jsp").forward(request, response);
		}
		
		
		if(form.equals("studentmycourse")){
			HttpSession se = request.getSession();
			ArrayList<String[]> al =null;
			CourseDAO cd = new CourseDAO();
			try{
				al = cd.viewMyCourse((String)se.getAttribute("netid"));
			}catch(SQLException e){
				e.printStackTrace();
			}
			request.setAttribute("list", al);
			request.getRequestDispatcher("showMyCourse.jsp").forward(request, response);
		}
		if(form.equals("detailsCourse")){
			String cid = request.getParameter("cid");
			String flag = request.getParameter("flag");
			CourseDAO cd= new CourseDAO();
			ArrayList<String> al = null;
			try{
				al=cd.displayDetail(cid, flag);
			}catch(SQLException e){
				e.printStackTrace();
			}
			request.setAttribute("list", al);
			request.getRequestDispatcher("viewdetails.jsp").forward(request, response);
		}
		
	     
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
