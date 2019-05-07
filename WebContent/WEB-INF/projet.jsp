<%@ page language="java" contentType="text/html; charset=Utf-8"
	pageEncoding="Utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="Utf-8">
<title>Insert title here</title>
</head>
<body>
	<div id="bloc">
		<h2>PROJET ${idProjet }</h2>
		<table border="1">
			<tr>
				<th>Projet</th>
				<th>Createur</th>
				<th>Equipe</th>
			</tr>
			<c:forEach items="${data}" var="ligne">
				<tr>
					<td>${ligne.idProjet }</td>
					<td>${ligne.idCreateur }</td>
					<td>${ligne.idEquipe }</td>
				</tr>
			</c:forEach>
	</div>
</body>
</html>