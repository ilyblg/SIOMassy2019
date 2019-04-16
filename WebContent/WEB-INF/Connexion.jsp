<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.DaoPersonne" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="css/style.css"/>
</head>
<body>
	<fieldset>
		<legend>CONNEXION</legend><br>
		<p>Veuillez saisir vos information</p><br>
		<form action="connexion" method="post">
			<label For="mail">Email : </label> 
			<input type="text" name="mail" value="${param['mail'] }">
			<br/>
			
			<label For="mdp">Mot de passe : </label> 
			<input type="password" name="mdp">
			<br/>
			
			<button type="submit">Se connecter</button>
			<div class="erreur">${msgConnexion}</div>
		</form>
	</fieldset>
</body>
</html>