<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JSP/JSTL practice</title>
</head>
<body>
<c:out value="Hello World JSTL" />

<p><strong>
<%= "Now Date - " %>
<%= new java.util.Date() %>
</strong></p>

<p>
<%= "getContextPath - " + 
	pageContext.getServletContext().getContextPath() %>
</p>

${ pageContext.servletContext.contextPath }	
	
</body>
</html>