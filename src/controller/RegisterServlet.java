package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Register;

/**
 * Servlet implementation class Register
 */
//@WebServlet(name = "RegisterServlet", urlPatterns = { "/Register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String form = request.getParameter("form");
		
		if(form.equals("register")){
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String netid = request.getParameter("netid");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		String year = request.getParameter("year");
		String program = request.getParameter("program");
		String major = request.getParameter("major");
		Register r = new Register();
		r.firstName = firstName;
		r.lastName = lastName;
		r.major = major;
		r.netid = netid;
		r.password = password;
		r.program = program;
		r.role = role;
		r.year = year;
		r.email = email;
		String msg;
		try {
			msg = r.registerUser();
			if(msg.equals("success")){
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else{
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("registration.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		if(form.equals("login")){
			String netid = request.getParameter("netid");
			String password = request.getParameter("password");
			String msg;
			String name;
			Register r = new Register();
			try {
				msg = r.login(netid, password);
				name = r.firstName;
				if(msg.equals("success")){ 
					HttpSession session = request.getSession();
					session.setAttribute("netid", netid);
					session.setAttribute("firstname", name);
					session.setAttribute("role", r.role);
					request.getRequestDispatcher("home.jsp").forward(request, response);
				}else{
					request.setAttribute("msg", msg);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(form.equals("logout")){
			HttpSession session = request.getSession(false);
			session.invalidate();
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		
		if(form.equals("update")){
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String netid = request.getParameter("netid");
			String password = request.getParameter("password");
			String role = request.getParameter("role");
			String year = request.getParameter("year");
			String program = request.getParameter("program");
			String major = request.getParameter("major");
			String email = request.getParameter("email");
			Register r = new Register();
			r.firstName = firstName;
			r.lastName = lastName;
			r.major = major;
			r.netid = netid;
			r.password = password;
			r.program = program;
			r.role = role;
			r.year = year;
			r.email = email;
			HttpSession se = request.getSession();
			String msg=null;
			if(true){
			try {
				msg = r.update();
				if(firstName!=null && !firstName.isEmpty()){
				se.setAttribute("firstname", firstName);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("profile.jsp").forward(request, response);
		}
		
		if(form.equals("officeHours")){
			HttpSession se = request.getSession();
			Register r = new Register();
			String msg=null;
			try{
				msg = r.officehour((String)se.getAttribute("netid"));
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			request.setAttribute("off", msg);
			request.getRequestDispatcher("officehours.jsp").forward(request, response);
			
		}
		
		if(form.equals("updateoffice")){
			String off = request.getParameter("office");
			HttpSession se = request.getSession();
			Register r = new Register();
			String msg=null;
			if(!off.isEmpty()){
			try{
				r.updateOffice((String)se.getAttribute("netid"),off);
				msg = r.officehour((String)se.getAttribute("netid"));
			}catch(SQLException e){
				e.printStackTrace();
			}
			}else{
			msg = "Field empty !!";
			}
			
			
			request.setAttribute("off", msg);
			request.getRequestDispatcher("officehours.jsp").forward(request, response);
			
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
