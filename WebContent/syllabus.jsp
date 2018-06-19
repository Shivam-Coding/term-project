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
                
            </ul>
        </div>
<jsp:include page="menu.jsp"></jsp:include>
<div>
<center>
<% ArrayList<String[]> al = (ArrayList<String[]>)request.getAttribute("list"); 
String det = (String)request.getAttribute("det");
if(al != null){ %>

<table width="80%">
<tr>
<th>Course ID</th>
<th>Course Name</th>
<th></th>
</tr>
<%	
for(int i =0;i<al.size();i++){
String d[] = al.get(i);
%>
<tr>
<td><%=d[0] %></td>
<td><%=d[1] %></td>
<td><form action="Course">
<input type="hidden" name="form" value="postDetails">
<input type="hidden" name="detail" value="<%=det%>">
<input type="hidden" name="courseid" value="<%=d[0]%>">
<table>
<tr>
<td><input name="data" placeholder="<%=det%>"></td>
<%if(det.equals("ta")) {%>
</tr>
<tr><td><input name="email" placeholder="email"></td>
</tr><tr>
<td><input name="officehour" placeholder="Office Hours"></td>
</tr><tr>
<%} %>
<td><input type="submit" value="Post" ></td>
</tr>
</table>
</form></td>
</tr>

<%}} %>


</table>
</center>
</div>

</div>
 <script src="js/jquery.js"></script>

    
</body>
</html>