package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Resource;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ResourceDAO;

/**
 * Servlet implementation class ResourceServlet
 */
//@WebServlet("/Resource")
public class ResourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResourceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String form = request.getParameter("form");
		if(form.equals("create")){
			HttpSession se = request.getSession(false);
		String name = request.getParameter("name");
		String resourceType = request.getParameter("resourceType");
		String detail = request.getParameter("detail");
		Resource resource = new Resource();
		resource.setDetails(detail);
		resource.setName(name);
		resource.setType(resourceType);
		ResourceDAO rd = new ResourceDAO();
		String msg = null;
		if(!name.isEmpty() && !resourceType.isEmpty() && !detail.isEmpty()){
		try {
			msg = rd.insert(resource,(String)se.getAttribute("netid"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else{
			msg="Field(s) is empty !!";
		}
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("resource.jsp").forward(request, response);
		}
	 
		if(form.equals("viewList")){
			ResourceDAO rd = new ResourceDAO();
			ArrayList<Resource> al = null;
			try{
				al = rd.select();
			}catch(Exception e){
				e.printStackTrace();
			}
			request.setAttribute("list", al);
			request.getRequestDispatcher("resourcesList.jsp").forward(request, response);
		}
		
		
		if(form.equals("reserveList")){
			String name = request.getParameter("name");
			ResourceDAO rd = new ResourceDAO();
			ArrayList<String[]> al = null;
			try{
				al = rd.reserveResourceList(name);
			}catch(Exception e){
				e.printStackTrace();
			}
			request.setAttribute("list", al);
			request.setAttribute("name", name);
			request.getRequestDispatcher("reserveResource.jsp").forward(request, response);
		}
		
		if(form.equals("reserveSlot")){
			String date = request.getParameter("date");
			String name = request.getParameter("name");
			ResourceDAO rd = new ResourceDAO();
			ArrayList<String> al = null;
			ArrayList<String[]> al1 = null;
			try{
				al = rd.reserveSlot1(name, date);
				al1 = rd.reserveResourceList(name);
			}catch(Exception e){
				e.printStackTrace();
			}
			request.setAttribute("list1", al);
			request.setAttribute("list", al1);
			request.setAttribute("name", name);
			request.setAttribute("date", date);
			request.getRequestDispatcher("reserveResource.jsp").forward(request, response);
		}
			
		if(form.equals("reserveSlot1")){
			HttpSession se = request.getSession(false);
			String date = request.getParameter("date");
			String name = request.getParameter("name");
			String slots[] = request.getParameterValues("slots");
			ArrayList<String> al = null;
			ArrayList<String[]> al1 = null;
			ResourceDAO rd = new ResourceDAO();
			String msg = null;
			if(!date.isEmpty() && !name.isEmpty() && slots.length != 0){
			try {
				rd.reserveSlot2(name, date, slots,(String)se.getAttribute("firstname"),(String)se.getAttribute("netid"));
				al = rd.reserveSlot1(name, date);
				al1 = rd.reserveResourceList(name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else{
				msg = "Please select slot(s) !!";
			}
			
			request.setAttribute("list1", al);
			request.setAttribute("list", al1);
			request.setAttribute("name", name);
			request.setAttribute("date", date);
			request.getRequestDispatcher("reserveResource.jsp").forward(request, response);
			
		}
		
		if(form.equals("myResources")){
			HttpSession se = request.getSession(false);
			ResourceDAO rd = new ResourceDAO();
			ArrayList<String[]> al = null;
			try{
				al = rd.myResourceList((String)se.getAttribute("netid"));
			}catch(Exception e){
				e.printStackTrace();
			}
			request.setAttribute("list", al);
			
			request.getRequestDispatcher("myResource.jsp").forward(request, response);
		}
		
		if(form.equals("cancelReservation")){
			HttpSession se = request.getSession(false);
			String[] d = request.getParameterValues("selectList");
			ResourceDAO rd = new ResourceDAO();
			ArrayList<String[]> al = null;
			String msg = null;
			try{
			if(d != null && d.length!=0 ){
			
				rd.cancelReservation((String)se.getAttribute("netid"),d);
				al = rd.myResourceList((String)se.getAttribute("netid"));
				msg = d.length+" Reservations cancelled !!";
			
			}else{
				al = rd.myResourceList((String)se.getAttribute("netid"));
				msg = "Please select !!";
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			request.setAttribute("msg", msg);
			request.setAttribute("list", al);
			
			request.getRequestDispatcher("myResource.jsp").forward(request, response);
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
