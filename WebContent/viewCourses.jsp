<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Course</title>
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
                        Course
                    </a>
                </li>
                <%HttpSession se = request.getSession();
                String role = (String)se.getAttribute("role");
                if(!role.equals("student")){%>
                
                
                
                <li>
                    <a href="addCourse.jsp">Add Course</a>
                </li>
                <li>
                    <a href="Course?form=mycourses">View My Courses</a>
                </li>
                <li>
                    <a href="Course?form=viewallcourses">View Courses</a>
                </li>
                <li>
                    <a href="Course?form=mycourses1&det=syllabus">Course Syllabus</a>
                </li>
                <li>
                    <a href="Course?form=mycourses1&det=ta">Course TA</a>
                </li>
                <li>
                    <a href="Register?form=officeHours">My Office Hours</a>
                </li>
                
                <%}else{%>
                	<li> <a href="Course?form=studentmycourse">My Courses </a>
                	</li>
                	
               <% }%>
                
            </ul>
        </div>
<jsp:include page="menu.jsp"></jsp:include>
<div>
<center>
<h3>Courses</h3>

<% 
String msg = (String)request.getAttribute("msg");
if(msg != null){%>
	<p style="color: blue;"><%=msg %></p>
<% }

ArrayList<String[]> al = (ArrayList<String[]>)request.getAttribute("list"); 
if(al != null){ %>

<table width="80%">
<tr>
<th>Course ID</th>
<th>Course Name</th>
<th>Detail</th>
<th>Teaching Assistant</th>
<th>Instructor</th>
<th></th>
</tr>
<%	
for(int i =0;i<al.size();i++){
String d[] = al.get(i);
%>
<tr>
<td><%=d[0] %></a></td>
<td><%=d[1] %></td>
<td><%if( d[4] != null && !d[4].isEmpty()){out.print(d[4]);} %></td>
<td><a href="Course?form=detailsCourse&flag=ta&cid=<%=d[0]%>"><%if( d[5] != null && !d[5].isEmpty()){out.print(d[5]);} %></a></td>
<td><%if(d[2] != null) %>
	<a href="Course?form=detailsCourse&flag=ins&cid=<%=d[0]%>"><%if( d[3] != null && !d[3].isEmpty()){out.print(d[3]);} %></a></td>
	<% if(!role.equals("student")){%>
<td><%if(d[2] == null) {%><a href="Course?form=courseTeach&courseid=<%=d[0]%>">Teach</a><%} %></td>
<%}else{ %>

<td><a href="Course?form=enroll&course=<%=d[1]%>">Enroll</a></td>

<%} %>
</tr>

<%}} %>


</table>
</center>
</div>

</div>
 <script src="js/jquery.js"></script>

    
</body>
</html>