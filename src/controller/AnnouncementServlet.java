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

import dao.AnnouncementDAO;

/**
 * Servlet implementation class AnnouncementServlet
 */
@WebServlet("/Announcement")
public class AnnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnnouncementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String form = request.getParameter("form");
		if(form.equals("add")){
			String cat = request.getParameter("category");
			String det = request.getParameter("detail");
			String link = request.getParameter("link");
			String email = request.getParameter("email");
			String id = request.getParameter("id");
			HttpSession se = request.getSession();
			AnnouncementDAO ad = new AnnouncementDAO();
			String msg=null;
			ArrayList<String[]> al =null;
			if(!det.isEmpty() && !cat.isEmpty()){
				try{
					msg =  ad.addAnnouncement(det, id, cat, link, email, (String)se.getAttribute("netid"));
					al = ad.announcementList();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}else{
				msg = "Field(s) empty !!";
			}
			
			
			if(id != null){
				request.setAttribute("list", al);
				request.getRequestDispatcher("announcementList.jsp").forward(request, response);
			}else{
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("addAnnouncement.jsp").forward(request, response);
			}
		}
		
		if(form.equals("announcement")){
			ArrayList<String[]> al =null;
			AnnouncementDAO ad = new AnnouncementDAO();
			try{
				al = ad.announcementList();
			}catch(SQLException e){
				e.printStackTrace();
			}
			request.setAttribute("list", al);
			request.getRequestDispatcher("announcementList.jsp").forward(request, response);
		}
		
		if(form.equals("delete")){
			HttpSession se = request.getSession();
			String id = request.getParameter("id");
			AnnouncementDAO ad = new AnnouncementDAO();
			ArrayList<String[]> al =null;
			try{
				ad.deleteAnnouncement(id, (String)se.getAttribute("netid"));
				al = ad.announcementList();
			}catch(SQLException e){
				e.printStackTrace();
			}
			request.setAttribute("list", al);
			request.getRequestDispatcher("announcementList.jsp").forward(request, response);
		}
		
		if(form.equals("applyJob")){
			HttpSession se = request.getSession();
			String jobid = request.getParameter("id");
			AnnouncementDAO ad = new AnnouncementDAO();
			ArrayList<String[]> al =null;
			String msg = null;
			try{
				msg = ad.applyJob((String)se.getAttribute("netid"), jobid);
				al = ad.announcementList();
			}catch(SQLException e){
				e.printStackTrace();
			}
			request.setAttribute("msg", msg);
			request.setAttribute("list", al);
			request.getRequestDispatcher("announcementList.jsp").forward(request, response);
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
