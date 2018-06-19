<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body>

<%String msg = (String)request.getAttribute("msg"); %>

<center>
<div style="box-shadow: 10px 10px 5px #888888;">
<h3>Register</h3>
<p style="color:red;">
<%if(msg!=null){out.print(msg);} %>
</p>
<form action="Register" method="post" onsubmit="return validateForm();">
<input type="hidden" name="form" value="register">
<table>
<tr>
<td>First Name:</td>
<td><input name="firstName" placeholder="First Name" required></td>
</tr>
<tr>
<td>Last Name:</td>
<td><input name="lastName" placeholder="Last Name" required></td>
</tr>
<tr>
<td>E-mail:</td>
<td><input name="email" placeholder="e-mail" required></td>
</tr>
<tr>
<td>NetID:</td>
<td><input name="netid" placeholder="NetID" required></td>
</tr>
<tr>
<td>Password:</td>
<td><input type="password" name="password" placeholder="Password" required></td>
</tr>
<tr>
<td>Role:</td>
<td><select id="dropdown" name="role" onchange="myFunction()">
  <option value="faculty">Faculty</option>
  <option value="staff">Staff</option>
  <option value="student">Student</option>
</select>
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
<div id="studentform" style="display: none;">
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
<tr >
<td colspan="2"><a href="login.jsp">Login</a></td></tr>
<tr >
<td colspan="2"><input type="submit" value="Register"></td></tr>
</table>
</form>
</div>
</center>


<script type="text/javascript">

function validateForm() {
    var x = document.forms["myForm"]["firstName"].value;
    var y = document.forms["myForm"]["lastName"].value;
    var z = document.forms["myForm"]["netid"].value;
    var q = document.forms["myForm"]["password"].value;
    if ((x == null || x == "") && (y == null || y =="")  && (z == null || z =="")  && (q == null || q =="")) {
        alert("Name must be filled out");
        return false;
    }
}
</script>
</body>
</html>