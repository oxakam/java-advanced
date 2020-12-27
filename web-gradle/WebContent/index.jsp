<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JSP Form</title>

<style>
form {
	border: 1px solid gray;
	width: 350px;
	margin-left: 50px;
	margin-right: auto;
	margin-top: 50px;
	padding: 20px;
	display: grid;
	grid-template-columns: 1fr 1fr;
	grid-gap: 0.5em 0.5em;
}	
#submit {
	grid-column: 2/3; 
}	
label {
	justify-self: right; 
}
</style>
</head>

<body>
	<form action="${pageContext.servletContext.contextPath}/users" method="post">
	
		<label for="name">Name:</label>
		<input type="text" id="nameId" name="nameName" />
			
		<label for="password">Password:</label>
		<input type="password" id="passId" name="passName" />
		
		<input type="submit" id="submit" value="Submit" />		
	</form>
</body>
</html>