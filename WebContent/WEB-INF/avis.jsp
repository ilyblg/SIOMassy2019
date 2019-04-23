<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Avis QCM</title>
</head>
<body>
	Avis sur un QCM
	<form method = "post" action="avis">
		<%-- <label for = "idPersonne">ID Personne</label>
		<input type="text" placeholder="ID Personne" name="idPersonne" id="idPersonne" value="${param['idPersonne'] }"/> --%>
		<br/>		
		<label for = "idQcm">ID QCM</label>
		<input type="text" placeholder="ID QCM" name="idQcm" id="idQcm" value="${param['idQcm'] }"/>
		<br/>
		<label for = "commentaire">Commentaire</label>
		<input type="text" placeholder="Commentaire" name="commentaire" id="commentaire" value="${param['commentaire'] }"/>
		<br/>
		<label for = "note">Note</label>
		<input type="text" placeholder="Note" name="note" id="note" value="${param['note'] }"/>
		<br/>
		<button type="submit">Valider</button>	
		<div class="erreur">${erreurDao}</div>	
	</form>
</body>
</html>