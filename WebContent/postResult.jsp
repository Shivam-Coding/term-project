<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
               <%HttpSession se2 = request.getSession();
                String role = (String)se2.getAttribute("role");
                if(role.equals("faculty")){%>
                <li>
                    <a href="postResult.jsp">Post Result</a>
                </li>
                <%} %>
                
                
            </ul>
        </div>
<jsp:include page="menu.jsp"></jsp:include>
<div>
<center>
<h3>Post Result</h3>
<% String msg = (String)request.getAttribute("msg");
if(msg != null){ %>

<p style="color:blue">
<%= msg %>

</p>
<% }
%>
<form action="Exam?form=postresults" method="post">
<table>
<tr><td>Name:</td>
<td><input name="name" placeholder="Exam Name"></td></tr>
<tr><td>Result:</td>
<td><input name="result" placeholder="Student exam id: result,.."></td></tr>
<tr><td colspan="2">
<input type="submit" value="Post"></td></tr>
</table>
</form>
</center>
</div>
</div>
</body>
</html>