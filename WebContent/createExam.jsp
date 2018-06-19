<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Exam</title>
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
                <li>
                    <a href="createExam.jsp">Create Exam</a>
                </li>
                <li>
                    <a href="Exam?form=viewResult">View Results</a>
                </li>
                <li>
                    <a href="Exam?form=viewExam">View Exams</a>
                </li>
                <li>
                   <a href="postResult.jsp">Post Result</a>
                </li>
                
                
            </ul>
        </div>
<jsp:include page="menu.jsp"></jsp:include>
<div>
<center>
<h3>Create Exam</h3>
<% String msg = (String)request.getAttribute("msg");
if(msg != null){ %>

<p style="color:blue">
<%= msg %>

</p>
<% }
%>

<form action="Exam?form=createExam" method="post">
<table>
<tr>
<td>Name:</td>
<td><input name="name" placeholder="Exam Name"></td>
</tr>
<tr>
<td>Date:</td>
<td><input name="date" placeholder="Date"></td>
</tr>
<tr>
<td>Link:</td>
<td><input name="link" placeholder="Link"></td>
</tr>
<tr>
<td colspan="2"><input type="submit" value="Create"></td>
</tr>
</table>
</form>
</center>
</div>
</div>
</body>
</html>