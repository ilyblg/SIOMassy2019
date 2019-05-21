<%@ page language="java" contentType="text/html; charset=Utf-8"
    pageEncoding="Utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="Utf-8">
<title>Les projets</title>
</head>
<body>
	<c:forEach items="${projets}" var="projet">
		<li>${projet.titre}</li>
	</c:forEach>
</body>
</html>