<%@ page language="java" contentType="text/html; charset=Utf-8"
	pageEncoding="Utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>nouvelle equipe</title>
<link rel="stylesheet" href="css/style.css"/>
</head>
<body>
	<div id="bloc">
		<h2>Equipe ${idEquipe}</h2>
		<table border="1">
			<tr>
				<th>Equipe</th>
				<th>Projet</th>
				<th>Créateur</th>
			</tr>
			<c:forEach items="${equipe}" var="ligne">
				<tr>
					<td>${ligne.idEquipe }</td>
					<td>${ligne.idProjet }</td>
					<td>${ligne.idUser }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
</body>
</html>