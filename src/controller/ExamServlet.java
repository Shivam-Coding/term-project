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
import dao.ExamDAO;

/**
 * Servlet implementation class ExamServlet
 */
@WebServlet("/Exam")
public class ExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String form = request.getParameter("form");
			if(form.equals("createExam")){
				String name = request.getParameter("name");
				String date = request.getParameter("date");
				String link = request.getParameter("link");
				ExamDAO ed = new ExamDAO();
				String msg = null;
				if(!name.isEmpty() && !date.isEmpty() && !link.isEmpty()){
				try {
					msg = ed.createExam(name, date, link);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}else{
					msg = "Fiel(s) empty !!";
				}
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("createExam.jsp").forward(request, response);
				}
		
			
			if(form.equals("postresults")){
				HttpSession s = request.getSession();
				String name = request.getParameter("name");
				String result[] = request.getParameter("result").split(",");
				String netid = (String)s.getAttribute("netid");
				ExamDAO ed = new ExamDAO();
				String msg = null;
				if(!name.isEmpty() && result.length !=0){
				try {
					msg = ed.postResult(name, result, netid);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}else{
					msg="Fiel(s) empty !!";
				}
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("postResult.jsp").forward(request, response);
			}
			
			
			if(form.equals("viewResult")){
				ExamDAO ed = new ExamDAO();
				HttpSession se = request.getSession();
				String role = (String)se.getAttribute("role");
				ArrayList<String[]> al = null;
				try{
					if(role.equals("student")){
						al = ed.viewResultStudent((String)se.getAttribute("netid"));
					}else{
						al = ed.viewResults();
					}
					
					
				}catch(Exception e){
					e.printStackTrace();
				}
				request.setAttribute("list", al);
				request.getRequestDispatcher("viewResult.jsp").forward(request, response);
			}
			
			if(form.equals("updateResult")){
				HttpSession s = request.getSession();
				String name = request.getParameter("name");
				String result[] = request.getParameter("result").split(",");
				String netid = (String)s.getAttribute("netid");
             
				ExamDAO ed = new ExamDAO();
				String msg = null;
				if(!name.isEmpty() && !request.getParameter("result").isEmpty()){
				try {
					msg = ed.updateResult(result, name, netid);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}else{
					msg="Fiel(s) empty !!";
				}
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("editResult.jsp").forward(request, response);
			}
			
			if(form.equals("deleteResult")){
				String name = request.getParameter("name");
				ExamDAO ed = new ExamDAO();
				String msg = null;
				ArrayList<String[]> al = null;
				try {
					ed.deleteResult(name);
					al = ed.viewResults();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("list", al);
				request.getRequestDispatcher("viewResult.jsp").forward(request, response);
			}
			
			if(form.equals("viewExamResults")){
				String name = request.getParameter("name");
                 ExamDAO ed = new ExamDAO();
				ArrayList<String[]> al = null;
				try{
					al = ed.viewExamResults(name);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				request.setAttribute("name", name);
				request.setAttribute("list", al);
				request.getRequestDispatcher("viewExamResult.jsp").forward(request, response);
			}
			
			if(form.equals("viewExam")){
				ExamDAO ed = new ExamDAO();
				ArrayList<String[]> al = null;
				HttpSession se = request.getSession();
				String r = (String)se.getAttribute("role");
				try{
					if(r.equals("student")){
						al= ed.viewExamStudent((String)se.getAttribute("netid"));
					}else{
						al = ed.viewExam();
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				request.setAttribute("list", al);
				request.getRequestDispatcher("viewExam.jsp").forward(request, response);
			}
			
			if(form.equals("examRegister")){
				
				String name = request.getParameter("exam");
				HttpSession se = request.getSession();
				String r = (String)se.getAttribute("role");
				ArrayList<String[]> al = null;
				ExamDAO ed = new ExamDAO();
				try{
					
					ed.registerExam(name, (String)se.getAttribute("netid"));
					
					
					if(r.equals("student")){
						al= ed.viewExamStudent((String)se.getAttribute("netid"));
					}else{
						al = ed.viewExam();
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
				request.setAttribute("list", al);
				request.getRequestDispatcher("viewExam.jsp").forward(request, response);
				
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
