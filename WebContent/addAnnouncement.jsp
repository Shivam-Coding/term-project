<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Announcement</title>
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
                        Announcement
                    </a>
                </li>
                <li>
                    <a href="addAnnouncement.jsp">Post Announcement</a>
                </li>
                <li>
                    <a href="Announcement?form=announcement">View Announcement</a>
                </li>
                
            </ul>
        </div>
<jsp:include page="menu.jsp"></jsp:include>
<div>
<center>

<h3>Announcement</h3>
<%String msg = (String)request.getAttribute("msg"); 
String id = (String)request.getParameter("id");
String cat = (String)request.getParameter("cat");
if(msg != null){ %>

<p style="color:blue">
<%= msg %>

</p>
<% }
%>
<form action="Announcement" method="post">
<input type="hidden" name="form" value="add">
<input type="hidden" name="id" value = "<%=id%>">
<table width="30%">
<tr>
<td>
Details:
</td>

<td>
<textarea col="30"  row="50" name="detail"></textarea>
</td>
</tr>
<tr>
<td>Category:</td>
<td>
<%if(id == null){ %>

<select id="dropdown" name="category" onchange="myFunction()">
  <option value="News">News</option>
  <option value="Events">Events</option>
  <option value="Job">Jobs</option>
</select>

<%}else{ if(cat !=null){out.print(cat);%> 
<input type="hidden" name="category" value="<%=cat %>">
<% }
}	%>


</td>
</tr>
<tr><td colspan ="2" align="center">
<div id="studentform" <%if(cat !=null && cat.equals("Job")){}else{out.print("style='display: none;'");} %>>
<table>
<tr>
<td>Link:</td>
<td><input name="link" placeholder="http://www.xyz.com"></td>
</tr>
<tr>
<td>e-mail:</td>
<td><input name="email" placeholder="email@xyz.com"></td>
</tr>
</table>
</div>
</td>
<tr>
<td colspan="2" width="30%">
<input type="submit" value="Submit">
</td>
</tr>
</table>
</form>




</center>
</div>
</div>
<script type="text/javascript">
function myFunction(){
var el = document.getElementById("studentform");
var dd = document.getElementById("dropdown");
var selectedText = dd.options[dd.selectedIndex].value;
if(selectedText == "Job"){
el.style.display="block";
}else{
	el.style.display="none";
}
}
</script>
</body>
</html>