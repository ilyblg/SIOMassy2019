<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<link rel="stylesheet" href="css/style.css"/>
<title>Noter les stagiaires</title>

<script type="text/javascript">
	/*
		Script Ajax transmettant à la Servlet 'ModifierNote' la nouvelle note saisie par le formateur
		Afin de l'insérer en base de donnée (DaoNote).
		Gère les erreur d'exécution lors de l'envoie et affiche un message en fonction. Un span est prévue.
	*/
	// What to do when page loading is complete
	function soumettreNote(idPersonne) {
		$.ajax({
			type : "POST",
			url : "soumettreNote",
			
			// Body parameters to send
			data : {
				"idPersonne" : idPersonne, 
				"idEvaluation" : ${idEvaluation},
				"note" : $("#note" + idPersonne).val(),
			},
			
			beforeSend : function(xhr) {
				$("#note" + idPersonne).css("background-color", "#F6E8B1");
			},
			
			// What to do in case of failure (400-599)
			error : function(xhr, string) {
				$("#note" + idPersonne).css("background-color", "#ff0c58");
				$("#erreur" + idPersonne).html(xhr.status + " " + xhr.messageText);
				console.log("pb");
			},
			
			// What to do if success (200-299)
			success : function(data) {
				$("#note" + idPersonne).css("background-color", "#B0CC99");
				console.log("ok");
			}
		});
	}
</script>

</head>
<body>

	<div  id="bloc">
		<%@ include  file="./headerFormateur.jsp"%>
		
		
	<section id="contain">
	<h3>Liste de notes des stagiaires de l'évaluation n° ${idEvaluation}</h3>
	<p>Veuillez indiquer une note (les décimales prennent une virgule), puis valider avec la touche 'Entrée' ou le bouton.</p>
		<table>
		  <tr>
    			<th>ID</th>
    			<th>Nom</th> 
    			<th>Prenom</th>
    			<th>Note</th>
  			</tr>
			<c:forEach items="${notes}" var="note">
				<tr>
					<td>${note["id_personne"]}</td>
					<td>${note["nom"]}</td>
					<td>${note["prenom"]}</td>
					<td>
						<form action="javascript: soumettreNote(${note['id_personne']})">
							<input type="number"  step="0.5"placeholder="note"
								id="note${note['id_personne']}" value="${note['note']}">
							<button type="submit">Envoyer</button>
							<span id="erreur${note['id_personne']}"></span>
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