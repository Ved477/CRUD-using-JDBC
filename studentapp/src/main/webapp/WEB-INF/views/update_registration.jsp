<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet"%>
<%@ include file="menu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Registration</title>
</head>
<body>

	<h2>Update Registration here</h2>

	<form action="update" method="post">
		<pre>
		
		<%
		ResultSet result = (ResultSet) request.getAttribute("result");
		while(result.next()){
		%>
	Name <input type="text" name="name" value="<%=result.getString(1)%>" />
	City <input type="text" name="city" value="<%=result.getString(2)%>" />
	Email <input type="text" name="email" value="<%=result.getString(3)%>" />
	Mobile <input type="text" name="mobile" value="<%=result.getString(4)%>" />
		<%} %>
	<input type="submit" value="Update" />
	</pre>
	
	</form>
	

</body>
</html>