<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

Formulaire d'inscription <br>
			<section id="contain">
			<!-- zone de connexion -->     
		    <form action="InscriptionServlet" method="POST">
		    	<fieldset>
			 		<legend>Ajouter un eleve</legend>
		    	    <label for="nom">Nom</label>
		            <input type="text" placeholder="" name="nom" >
		            <br>
		        	<label for="prenom">Prenom</label>
		            <input type="text" placeholder="" name="prenom" required>
		            <br>
		            <label for="adresse">adresse</label>
		            <input type="text" placeholder="" name="adresse" required>
		            <br>
		            <label for="cp">Code postal</label>
		            <input type="text" placeholder="" name="cp" required>
		            <br>
		            <label for="ville">Ville</label>
		            <input type="text" placeholder="" name="ville" required>
		            <br>
		            <label for="telephone">Telephone</label>
		            <input type="text" placeholder="" name="telephone" required>
		            <br>
		            <label for="mail">Email</label>
	            	<input type="text" placeholder="maioldjasopdoipsa" name="mail" required>
		            <br>
		            <label for="mailVerif">Email pour vérification</label>
		            <input type="text" placeholder="" name="mailVerif" required>
		            <br>
		            <label for="password">Mot de passe</label>
		            <input type="text" placeholder="" name="password" required>
		            <br>	            
		
		           	<button type="submit">Ajouter</button>
		     	</fieldset>
		     </form>
		</section>
</body>
</html>