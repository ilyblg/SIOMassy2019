<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Bienvenue sur votre accueil</title>
<link rel="stylesheet" href="css/style.css"/>
</head>
<body>
	<div  id = "bloc" >
		<%@ include  file="./headerStagiaire.jsp"%>
			<h2>STAGIAIRE</h2>
			<section>
				<p>Bonjour ${sessionScope['user'].prenom}</p>
			</section>
		<%@ include  file="./footer.jsp"%>
	</div >
</body>
</html>