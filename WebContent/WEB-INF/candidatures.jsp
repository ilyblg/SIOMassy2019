<%@ page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<link rel="stylesheet" href="css/style.css" />
<title>Candidatures à la session n° ${idSession}</title>
<link type="text/css" rel="stylesheet" href="form.css" />
</head>
<body>
	<div id="bloc">
		<%@ include file="header.jsp"%>
		<c:if test="${!candidatures.isEmpty()}">
			<h2>Candidatures à la session n° ${idSession}</h2>
			<table border="1">
				<tr>
					<th>Id</th>
					<th>Prénom</th>
					<th>Nom</th>
					<th>Email</th>
					<th>Etat</th>
				</tr>
				<c:forEach items="${candidatures}" var="candidature">
					<tr>
						<td>${candidature.idPersonne}</td>
						<td>${candidature.prenom}</td>
						<td>${candidature.nom}</td>
						<td>${candidature.mail}</td>
						<td>${candidature.etat}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${candidatures.isEmpty()}">
			<h1>Aucune candidature pour la session n° ${idSession}</h1>
		</c:if>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>
