<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.PersonneDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>Connexion</title>
	<link rel="stylesheet" href="css/style.css"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="style.css">
<title>initialisation de mot de passe</title>
</head>
<body>
	<div  id = "bloc" >
		<%@ include  file="./header.jsp"%>
			<fieldset>
				<legend>CONNEXION</legend><br>
				<p>Veuillez saisir vos information</p><br>
				<form action="connexion" method="post">
					<p>
					<label For="mail">Email : </label> 
					<input type="text" name="mail" value="${param['mail'] }">
					</p>

					<p>
					<label For="mdp">Mot de passe : </label> 
					<input type="password" name="mdp">
					</p>

					<button type="submit" class="button">Se connecter</button>
					<div class="erreur">${msgConnexion}</div>
				</form>
			</fieldset>
		<%@ include  file="./footer.jsp"%>
	</div >
</body>
</html>