<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<h3> Add Course</h3>
<%String msg = (String)request.getAttribute("msg"); %>
<p style="color:blue;">
<% if(msg!= null){out.print(msg);} %>
</p>
<form action="Course" method="post">
<input type="hidden" name="form" value="add">
<table width="27%" height="30%">
<tr>
<td>Course ID: </td>
<td><input name="courseid" placeholder="Course ID"></td>
</tr>
<tr>
<td>Course Name: </td>
<td><input name="name" placeholder="Course Name"></td>
</tr>
<tr>
<td colspan="2"><input type="submit" value="Create"></td>
</tr>

</table>
</form>
</center>
</div>


</div>
 <script src="js/jquery.js"></script>

   

</body>
</html>