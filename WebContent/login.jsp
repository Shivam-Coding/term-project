<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
<script type="text/javascript">

function validateForm() {
    var x = document.forms["myForm"]["netid"].value;
    var y = document.forms["myForm"]["password"].value;
   
    if ((x == null || x == "") && (y == null || y =="")) {
        alert("Name must be filled out");
        return false;
    }
}
</script>
<center>
<%String msg = (String)request.getAttribute("msg"); %>
<h3>Login</h3>
<p style="color:red;">
<%if(msg!=null){out.print(msg);} %>
</p>
<form action="Register" method="post" onsubmit="return validateForm()">
<input type="hidden" name="form" value="login">
<table>
<tr>
<td>Netid:</td>
<td><input name="netid" placeholder="Net ID" required></td>
</tr>
<tr>
<td>Password:</td>
<td><input type="password" name ="password" placeholder="Password" required></td>
</tr>
<tr >
<td colspan="2"><a href="registration.jsp">Register</a></td></tr>
<tr >
<td colspan="2"><input type="submit" value="Login"></td>
</tr>
</table>
</form>
</center>

</body>
</html>