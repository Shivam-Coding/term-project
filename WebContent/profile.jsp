<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile</title>
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
                        Profile
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
<%String msg = (String)request.getAttribute("msg"); 

HttpSession se = request.getSession(false);
String netid = (String)se.getAttribute("netid");
String role = (String )se.getAttribute("role");
%>
<h3>Update</h3>
<p style="color:red;">
<%if(msg!=null){out.print(msg);} %>
</p>
<form action="Register" method="post">
<input type="hidden" name="form" value="update">
<table>
<tr>
<td>First Name:</td>
<td><input name="firstName" placeholder="First Name"></td>
</tr>
<tr>
<td>Last Name:</td>
<td><input name="lastName" placeholder="Last Name"></td>
</tr>
<tr>
<td>E-mail:</td>
<td><input name="email" placeholder="e-mail" ></td>
</tr>
<tr>
<td>NetID:</td>
<td><input name="netid" placeholder="NetID" value="<%=netid %>"  readonly></td>
</tr>
<tr>
<td>Password:</td>
<td><input type="password" name="password" placeholder="Password"></td>
</tr>
<tr>
<td>Role:</td>
<td>
<input type="hidden" name="role" value="<%=role%>"><%=role %>
</td>
</tr>
<tr><td colspan ="2">

<script type="text/javascript">
function myFunction(){
var el = document.getElementById("studentform");
var dd = document.getElementById("dropdown");
var selectedText = dd.options[dd.selectedIndex].value;
if(selectedText == "student"){
el.style.display="block";
}else{
	el.style.display="none";
}
}
</script>
<div id="studentform" <%if(!role.equals("student")){out.print("style='display: none;'");} %>>
<table>
<tr>
<td>Year Joined:</td>
<td><select name="year">
  <option value="2016">2016</option>
  <option value="2015">2015</option>
  <option value="2014">2014</option>
</select></td>
</tr>
<tr>
<td>Program:</td>
<td><select name="program">
  <option value="bs">BS</option>
  <option value="ba">BA</option>
  <option value="ms">MS</option>
  <option value="phd">PhD</option>
</select></td>
</tr>
<tr>
<td>Major:</td>
<td><input name="major" placeholder="Major"></td>
</tr>

</table>
</div>
</td>
</tr>

<td colspan="2"><input type="submit" value="Update"></td></tr>
</table>
</form>
</center>
</div></div>
</body>
</html>