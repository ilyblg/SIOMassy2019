<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="css/style.css"/>
</head>
<body>
	<div  id="bloc">
		<%@ include  file="./header.jsp"%>
			<section id="contain">
			
			<!-- zone de connexion -->     
		    <form id="inscrip" action="inscription" method="POST">
		    	<fieldset>
			 		<legend>Ajouter un eleve</legend>
		    	    <label for="nom">Nom</label>
		            <input type="text" placeholder="Nom" name="nom" value="${nom}">
		            <span class="erreur">${msgNom}</span>
		            <br>
		        	<label for="prenom">Prenom</label>
		            <input type="text" placeholder="Prenom" name="prenom" value="${prenom}">
		            <span class="erreur">${msgPrenom}</span>
		            <br>
		            <label for="adresse">adresse</label>
		            <input type="text" placeholder="Numero et rue" name="adresse" value="${adresse}">
		            <span class="erreur">${msgAdresse}</span>
		            <br>
		            <label for="cp">Code postal</label>
		            <input type="text" placeholder="Code postal" name="cp" value="${cp}">
		            <span class="erreur">${msgCodePostal}</span>
		            <br>
		            <label for="ville">Ville</label>
		            <input type="text" placeholder="Ville" name="ville" value="${ville}">
		            <span class="erreur">${msgVille}</span>
		            <br>
		            <label for="telephone">Telephone</label>
		            <input type="text" placeholder="Telephone" name="telephone" value="${telephone}">
		            <span class="erreur">${msgTel}</span>
		            <br>
		            <label for="mail">Email</label>
	            	<input type="text" placeholder="votre-email@exemple.fr" name="mail" value="${mail}">
	            	<span class="erreur">${msgEmail}</span>
		            <br>
		            <label for="mailVerif">Email pour vérification</label>
		            <input type="text" placeholder="Repetez votre email" name="mailVerif">
		            <span class="erreur">${msgEmailVerif}</span>
		            <br>
		            <label for="password">Mot de passe</label>
		            <input type="text" placeholder="Mot de passe" name="password" value="${password}">
		            <span class="erreur">${msgMotDePasse}</span>
		            <br>
		
		           	<button type="submit">Ajouter</button>
		     	</fieldset>
		     </form>
		</section>
		<h2 class="erreur">${message}</h2>
		<%@ include  file="./footer.jsp"%>
	</div >
</body>
</html>