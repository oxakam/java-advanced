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
	<c:out value="Hello World JSTL" />
	<p><c:out value="${ pageContext.servletContext.contextPath }" /></p>
	<p><c:url value="/test" /></p>
	<br><br>
	
	<form>
		<label for="name">Name:</label>
		<input type="text" id="nameId" name="name" />
			
		<label for="password">Password:</label>
		<input type="password" id="passId" name="password" />
		
		<input type="submit" id="submit" value="Submit" />		
	</form>
	
	
	<!-- using JSTL context -->	
	<br><br>
	<p>
		<strong> <%="Now Date - "%> <%=new java.util.Date()%></strong>
	</p>
	<p>
		<%="getContextPath - " + pageContext.getServletContext().getContextPath()%>
	</p>
	
	${ pageContext.servletContext.contextPath }

</body>
</html>