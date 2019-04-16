<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Noter les stagiaires</title>
</head>
<body>
	<section id="contain">
		<table>
			<c:forEach items="${notes}" var="note">
			<tr>
				<th>Propriétaire</th>
				<td>${note["id_personne"]}</td>
				<td>${note["nom"]}</td>
				<td>${note["prenom"]}</td>
				<td><input type="note" placeholder="note" name="note" value="${note['note']}"></td>
			</tr>
			</c:forEach>
		</table>
	</section>
</body>
</html>