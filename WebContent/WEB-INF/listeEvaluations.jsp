<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css"/>
<title>Liste des évaluations</title>

</head>
<body>

	<div  id="bloc">
		<%@ include  file="./headerFormateur.jsp"%>
		
		
	<section id="contain">
		<h3>Liste des évaluations disponibles</h3>
		<table>
			<tr>
    			<th>ID</th>
    			<th>Titre</th>
    			<th>Session-ID</th> 
    			<th>Module-ID</th>
    			<th>Débute le</th>
    			<th>Durée (mins)</th>
			<c:forEach items="${listeEvaluations}" var="evaluation">
				<tr>
					<td>${evaluation["id_evaluation"]}</td>
					<td>${evaluation["titre"]}</td>
					<td>${evaluation["id_session_formation"]}</td>
					<td>${evaluation["id_module"]}</td>
					<td>${evaluation["date_debut"]}</td>
					<td>${evaluation["nbr_minutes"]}</td>
					<td>
						<form action="noter-stagiaire" method="GET">
							<input style="display: none;" type="text" name="id_evaluation" value="${evaluation['id_evaluation']}">
							<button type="submit">Noter élèves</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	
	</section>
		<%@ include  file="./footer.jsp"%>
	</div >
</body>
</html>