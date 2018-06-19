<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Courses</title>
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
<h3>Program Requirement</h3>
</center>
<h4>B.A.</h4>
&nbsp;<b>Computer Systems and Science Core (18 credits)</b>: ICSI 201, 210, 310, 333, and 404<br>
&nbsp;<b>Programming Principles and Practice (3 credits)</b>: one of ICSI 311 or 405<br>
&nbsp;<b>Intensive Software Development (3 credits)</b>: one of ICSI 402 or 418 or other course with intensive software development as approved by the department<br>
&nbsp;<b>Mathematics (11 credits)</b>: AMAT 111 or 112 or 118; 113 or 119; and 367<br>
&nbsp;<b>Electives (6 credits)</b>: two I CSI courses numbered 400-470 or 500-550 or specially approved by the department<br>
<h4>B.S.</h4>
&nbsp;<b>Computer Systems and Science Core (24 credits)</b>: I CSI 201, 210, 310, 333, 403, 404 and 409<br>
&nbsp;<b>Programming Language Principles (3 credits)</b>: ICSI 311<br>
&nbsp;<b>Intensive System Software Development (3 credits)</b>: I CSI 402<br>
&nbsp;<b>Mathematics (17 credits)</b>: AMAT 111 or 112 or 118; 113 or 119; 220; 367; <br>&nbsp;AMAT 214 or 3 credits from any AMAT courses numbered 300 or above<br>
&nbsp;<b>Physics and Laboratory Science (8 credits)</b>: A PHY 140 or 141; 145; 150 or 151; and 155. <br>&nbsp;Students who took Physics I or II without a laboratory can substitute 1 credit of other laboratory work for each of the A PHY 145 and A PHY 155 requirements<br>
&nbsp;<b>Science Sequence (6 credits)</b>: one pair of related major biological, physical, or engineering science courses (not in mathematics or computer science) as approved by the department.<br>&nbsp;Approved pairs include A BIO 120 and 121, A PHY 240 and 250, two courses from A PHY 353, 415, and 454, or others as advised<br> 
&nbsp;<b>Social Aspects of Computing (3 credits)</b>: I CSI 300Z<br>
&nbsp;<b>Computer Science Electives (9 credits)</b>: 6-9 credits must be from I CSI courses numbered 300-470 or 500-550 or specially approved.<br>&nbsp;3 credits may be in A PHY 353 or 454 in digital hardware, or A PHI 432 in advanced logic<br>

<h4>M.S.</h4>

A proficiency examination in Discrete Mathematics is given at the beginning of the first semester of graduate study. Students who fail this examination are required to pass a departmentally approved remedial program.<br>

Computer Science core (13-14 credits): CSI 503 and CSI 518 plus two courses chosen from CSI 500, CSI 508, CSI 509, or CSI 519.  A 3.0 average must be attained in these core courses. Full-time students are expected to complete these courses in the first year, or as soon as possible if undergraduate deficiencies are being made up.<br>

Elective subjects (15 credits): Computer Science courses or courses in other academic departments as approved by the department. Approval of non-departmental electives is based on the individual student's overall academic program and preparation.<br>

Master's Project, Thesis, or Internship (3 credits): Satisfactory completion of (a) any Master's Project course (CSI 68X). The project includes the design and implementation of a computer program of significant scope, unless this is waived by the project faculty supervisor for a student who has sufficient programming experience. The project must include a written report.; (b) CSI 699, Master's Thesis; or  (c) CSI 698, Internship.<br>

<center><h3>Courses for each concentration</h3></center>
<h4>Applications</h4>
<b>CSI 508</b>: Database System<br>
<b>CSI 535</b>: Artificial Intelligence (I)<br>
<b>CSI 635</b>: Artificial Intelligence (II)<br>

<h4>Systems</h4>
<b>CSI 500</b>: Operating System<br>
<b>CSI 504</b>: Computer Organization<br>
<b>CSI 517</b>: Compiler Design (I)<br>

<h4>Theory</h4>
<b>CSI 509</b>: Theory of Computation<br>
<b>CSI 530</b>: Intro to Mathematical logic<br>
<b>CSI 630</b>: Computational Logic<br>

<center>
<h3>My Courses</h3>

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
<td><%=d[0] %></td>
<td><%=d[1] %></td>
<td><%if( d[4] != null && !d[4].isEmpty()){out.print(d[4]);} %></td>
<td><a href="Course?form=detailsCourse&flag=ta&cid=<%=d[0]%>"><%if( d[5] != null && !d[5].isEmpty()){out.print(d[5]);} %></a></td>
<td><%if(d[2] != null) %>
	<a href="Course?form=detailsCourse&flag=ins&cid=<%=d[0]%>"><%if( d[3] != null && !d[3].isEmpty()){out.print(d[3]);} %></a></td>
	<% if(!role.equals("student")){%>
<td><%if(d[2] == null) {%><a href="Course?form=courseTeach&courseid=<%=d[0]%>">Teach</a><%} %></td>
<%}%>
</tr>

<%}} %>


</table>
</center>
</div>

</div>
 <script src="js/jquery.js"></script>

    
</body>
</html>