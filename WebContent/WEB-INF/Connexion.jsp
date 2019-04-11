<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="style.css">
<title>initialisation de mot de passe</title>
</head>
<body>
	<div id="bloc">
		<%@ include file="header.jsp" %>	
		<fieldset>
			<legend> CONNECTION </legend>
			<p>Veuillez saisir vos informations :</p>
			<form action="Connecter" method="get">
			<p>
				<label for="email">email : </label><input type="text" name="email" id="email">
				<!-- span class="erreur"></span-->
				</p>
				<p>
					<label for="mdp">Mot de passe :</label><input type="text" name="mdp" id="mdp" >
				</p>
				<p>	<a href="#"> Mot de passe oublié.</a>
				</p>	
				<input type="submit" value="Se connecter"  class="button">
			</form>
		</fieldset>
		<%@ include file="footer.jsp" %>
	</div>
</body>
</html>
