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
import javax.servlet.http.HttpSessionBindingEvent;

import dao.DiscussionDAO;

/**
 * Servlet implementation class DiscussionServlet
 */
@WebServlet("/Discussion")
public class DiscussionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiscussionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String form = request.getParameter("form");
		
		if(form.equals("add")){
			String topic = request.getParameter("topic");
			DiscussionDAO dd = new DiscussionDAO();
			HttpSession se = request.getSession();
			String msg = null;
			if(!topic.isEmpty()){
				try{
					msg = dd.addTopic(topic, (String)se.getAttribute("firstname"), (String)se.getAttribute("netid"));
				}catch(SQLException e){
					e.printStackTrace();
				}
			}else{
				msg = "Field(s) empty !!";
			}
			
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("addTopic.jsp").forward(request, response);
		}
		
		if(form.equals("topics")){
			ArrayList<String[]> al =null;
			DiscussionDAO dd = new DiscussionDAO();
			try{
				al = dd.topics();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			request.setAttribute("list", al);
			request.getRequestDispatcher("topicsList.jsp").forward(request, response);
			
		}
		
		
		if(form.equals("topicDetail")){
			String id = request.getParameter("topicid");
			String name = request.getParameter("name");
			ArrayList<String[]> al =null;
			ArrayList<String[]> al1 = null;
			DiscussionDAO dd = new DiscussionDAO();
			try{
				al = dd.comment(id);
				al1 = dd.reply(id);
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			request.setAttribute("topicid", id);
			request.setAttribute("name", name);
			request.setAttribute("list", al);
			request.setAttribute("list1", al1);
			request.getRequestDispatcher("discussion.jsp").forward(request, response);
		}
		
		if(form.equals("addcomment")){
			String topicid = request.getParameter("topicid");
			String comment = request.getParameter("comment");
			DiscussionDAO dd = new DiscussionDAO();
			ArrayList<String[]> al =null;
			ArrayList<String[]> al1 = null;
			try{
				dd.addComment(topicid, comment);
				
				al = dd.comment(topicid);
				al1 = dd.reply(topicid);
			}catch(SQLException e){
				e.printStackTrace();
			}
			request.setAttribute("topicid", topicid);
			String name = request.getParameter("name");
			request.setAttribute("name", name);
			request.setAttribute("list", al);
			request.setAttribute("list1", al1);
			request.getRequestDispatcher("discussion.jsp").forward(request, response);
			
		}
		
		if(form.equals("addreply")){
			String topicid = request.getParameter("topicid");
			String reply = request.getParameter("reply");
			String commentid = request.getParameter("commentid");
			DiscussionDAO dd = new DiscussionDAO();
			ArrayList<String[]> al =null;
			ArrayList<String[]> al1 = null;
			try{
				dd.addReply(topicid, commentid, reply);
				
				al = dd.comment(topicid);
				al1 = dd.reply(topicid);
			}catch(SQLException e){
				e.printStackTrace();
			}
			request.setAttribute("topicid", topicid);
			String name = request.getParameter("name");
			request.setAttribute("name", name);
			request.setAttribute("list", al);
			request.setAttribute("list1", al1);
			request.getRequestDispatcher("discussion.jsp").forward(request, response);
			
		}
		if(form.equals("deletecomment")){
			String topicid = request.getParameter("topicid");
			String commentid = request.getParameter("commentid");
			DiscussionDAO dd = new DiscussionDAO();
			ArrayList<String[]> al =null;
			ArrayList<String[]> al1 = null;
			try{
				dd.deleteComment(commentid);
				
				al = dd.comment(topicid);
				al1 = dd.reply(topicid);
			}catch(SQLException e){
				e.printStackTrace();
			}
			request.setAttribute("topicid", topicid);
			String name = request.getParameter("name");
			request.setAttribute("name", name);
			request.setAttribute("list", al);
			request.setAttribute("list1", al1);
			request.getRequestDispatcher("discussion.jsp").forward(request, response);
			
		}
		
		
		
		
		if(form.equals("deletereply")){
			String topicid = request.getParameter("topicid");
			String replyid = request.getParameter("replyid");
			DiscussionDAO dd = new DiscussionDAO();
			ArrayList<String[]> al =null;
			ArrayList<String[]> al1 = null;
			try{
				dd.deleteReply(replyid);
				
				al = dd.comment(topicid);
				al1 = dd.reply(topicid);
			}catch(SQLException e){
				e.printStackTrace();
			}
			request.setAttribute("topicid", topicid);
			String name = request.getParameter("name");
			request.setAttribute("name", name);
			request.setAttribute("list", al);
			request.setAttribute("list1", al1);
			request.getRequestDispatcher("discussion.jsp").forward(request, response);
			
		}
		
		
		if(form.equals("deletetopic")){
			String topicid = request.getParameter("topicid");
			DiscussionDAO dd = new DiscussionDAO();
			ArrayList<String[]> al =null;
			try{
				dd.deleteTopic(topicid);
				
				al = dd.topics();
			}catch(SQLException e){
				e.printStackTrace();
			}
			request.setAttribute("list", al);
			request.getRequestDispatcher("topicsList.jsp").forward(request, response);
		}
		
		if(form.equals("mytopic")){
			ArrayList<String[]> al =null;
			HttpSession se = request.getSession(false);
			DiscussionDAO dd = new DiscussionDAO();
			try{
				al = dd.mytopics((String)se.getAttribute("netid"));
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			request.setAttribute("list", al);
			request.getRequestDispatcher("topicsList.jsp").forward(request, response);
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
