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
			 		<legend>Ajouter un client</legend>
		    	    <label for="nom">Nom</label>
		            <input type="text" placeholder="" name="nom" required>
		            <br>
		            
		            <label for="prenom">Prenom</label>
		            <input type="text" placeholder="" name="prenom" required>
		            <br>
		            
		<label for = "mail"> Mail : </label>
		<input type = "text" name = "mail" id="mail"/> <br>
		           	<button type="submit">Ajouter</button>
		     	</fieldset>
		     </form>
		</section>
</body>
</html>