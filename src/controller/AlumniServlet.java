package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AlumniDAO;

/**
 * Servlet implementation class AlumniServlet
 */
@WebServlet("/AlumniServlet")
public class AlumniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlumniServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String form = request.getParameter("form");
		if(form.equals("add")){
			String detail = request.getParameter("detail");
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String msg = null;
			ArrayList<String[]> al = null;
			AlumniDAO ad = new AlumniDAO();
			if(!name.isEmpty() && !detail.isEmpty()){
				try{
					msg = ad.addAlumni(name, detail, id);
					al = ad.alumniInfo();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}else{
				msg = "Field(s) empty !!";
			
			
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("addAlumni.jsp").forward(request, response);
			}
			request.setAttribute("list", al);
			request.getRequestDispatcher("alumni.jsp").forward(request, response);
			
		}
		
		if(form.equals("alumniInfo")){
			AlumniDAO ad = new AlumniDAO();
			ArrayList<String[]> al = null;
			try{
				al = ad.alumniInfo();
			}catch(SQLException e){
				e.printStackTrace();
			}
			request.setAttribute("list", al);
			request.getRequestDispatcher("alumni.jsp").forward(request, response);
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
