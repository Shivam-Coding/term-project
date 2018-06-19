package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseDAO;
import dao.PhdStatus;

/**
 * Servlet implementation class PhdStatusServlet
 */
@WebServlet("/PhdStatus")
public class PhdStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhdStatusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String form = request.getParameter("form");
		if(form.equals("phdList")){
			PhdStatus ph = new PhdStatus();
			
			ArrayList<String[]> al = null;
			try{
				al = ph.phdList();
			}catch(Exception e){
				e.printStackTrace();
			}
			request.setAttribute("list", al);
			request.getRequestDispatcher("phdList.jsp").forward(request, response);
		}
		
		if(form.equals("phdDetail")){
			String id = request.getParameter("id");
			PhdStatus ph = new PhdStatus();
			ArrayList<String[]> al = null;
			try{
				al = ph.phdDetail(id);
			}catch(Exception e){
				e.printStackTrace();
			}
			request.setAttribute("list", al);
			request.setAttribute("id", id);
			request.getRequestDispatcher("phdDetail.jsp").forward(request, response);
		}
		
		if(form.equals("phdUpdate")){
			String id = request.getParameter("id");
			String semester = request.getParameter("semester");
			String research = request.getParameter("research");
			PhdStatus ph = new PhdStatus();
			ArrayList<String[]> al = null;
			try{
				ph.phdUpdate(id, semester, research);
				al = ph.phdDetail(id);
			}catch(Exception e){
				e.printStackTrace();
			}
			request.setAttribute("list", al);
			request.setAttribute("id", id);
			request.getRequestDispatcher("phdDetail.jsp").forward(request, response);
		
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
