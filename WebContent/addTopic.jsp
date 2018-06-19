<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Discussion</title>
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
                        Discussion
                    </a>
                </li>
                <li>
                    <a href="addTopic.jsp">Add Topic</a>
                </li>
                <li>
                    <a href="Discussion?form=mytopic">My Topics</a>
                </li>
                <li>
                    <a href="Discussion?form=topics">View Topics</a>
                </li>
                
                
            </ul>
        </div>
<jsp:include page="menu.jsp"></jsp:include>
<div>
<center>
<h3> Add Topic</h3>
<%String msg = (String)request.getAttribute("msg"); %>
<p style="color:blue;">
<% if(msg!= null){out.print(msg);} %>
</p>
<form action="Discussion" method="post">
<input type="hidden" name="form" value="add">
<table width="27%" height="30%">
<tr>
<td>Topic Name: </td>
<td><input name="topic" placeholder="Topic"></td>
</tr>
<tr>
<td colspan="2"><input type="submit" value="Add"></td>
</tr>

</table>
</form>
</center>
</div>


</div>
</body>
</html>