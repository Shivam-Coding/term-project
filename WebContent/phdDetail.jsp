<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PhD Status</title>
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
                        PhD Status
                    </a>
                </li>
                <li>
                    <a href=""></a>
                </li>
                <li>
                    <a href=""></a>
                </li>
                <li>
                    <a href=""></a>
                </li>
                <li>
                    <a href=""></a>
                </li>
                <li>
                    <a href=""></a>
                </li>
                <li>
                    <a href="#"></a>
                </li>
                
            </ul>
        </div>
<jsp:include page="menu.jsp"></jsp:include>
<div>
<center>
<% String id = (String)request.getAttribute("id"); %>
<h3>Status Update</h3>
<form name="myForm" action="PhdStatus?form=phdUpdate&id=<%=id%>" method="post" onsubmit="return validateForm();">
<input type="hidden" name="id">
<table>
<tr>
<td>Semester:</td>
<td><input name="semester" placeholder="Semester"></td>
</tr>
<tr>
<td>Research:</td>
<td><input name="research" placeholder="Research"></td>
</tr>
<tr>
<td colspan="2"><input type="submit" value="Update" ></td>
</tr>
</table>
</form>
<br>
<script type="text/javascript">

function validateForm() {
    var x = document.forms["myForm"]["research"].value;
    var y = document.forms["myForm"]["semester"].value;
    if ((x == null || x == "") && (y == null || y =="")) {
        alert("Name must be filled out");
        return false;
    }
}
</script>

<h3>Status</h3>

<% ArrayList<String[]> al = (ArrayList<String[]>)request.getAttribute("list"); 
if(al != null){ %>
<table width="60%">
<tr>
<th>Name</th>
<th>Reseacher</th>
<th>Semester</th>
</tr>
<% 
for(int i =0; i<al.size();i++){
	String d[] = al.get(i); %>
	
	
<tr>
<td><%=d[1] %></td>
<td><%=d[3] %></td>
<td><%=d[2] %></td>
</tr>

<% }} %>
</table>
</center>
</div>
</div>


</body>
</html>