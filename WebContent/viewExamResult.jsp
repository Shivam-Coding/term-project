<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Result</title>
<link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/simple-sidebar.css" rel="stylesheet">
</head>
<body>
<div id="wrapper">
<div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="#">
                        Exam
                    </a>
                </li>
                <%HttpSession se1 = request.getSession();
                String role1 = (String)se1.getAttribute("role");
                if(!role1.equals("student")){%>
                <li>
                    <a href="createExam.jsp">Create Exam</a>
                </li>
                <%} %>
                <li>
                    <a href="Exam?form=viewResult">View Results</a>
                </li>
                <li>
                    <a href="Exam?form=viewExam">View Exams</a>
                </li>
               
                <% if(!role1.equals("student")){%>
               <li>
                    <a href="postResult.jsp">Post Result</a>
                </li>
                <%} %>
                
                
            </ul>
        </div>
<jsp:include page="menu.jsp"></jsp:include>
<div>
<center>
<h3>Results</h3>
<%String name = (String)request.getAttribute("name");
ArrayList<String[]> al = (ArrayList<String[]>)request.getAttribute("list");%>


<%if(al != null){ %>
	<table width="30%">
	<tr>
	<th> Name </th>
	<th>Student Exam id</th>
	<th> Result</th>
	</tr>
	
<% for(int i=0;i<al.size();i++) {
	String[] d = al.get(i); %>
	
	<tr>
	<td><%=name %></td>
	<td><%=d[0] %></td>
	<td><%=d[1] %></td>
	</tr>
	

<%}} %>
</center>
</div>
</div>
</body>
</html>