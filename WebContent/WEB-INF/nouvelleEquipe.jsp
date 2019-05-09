<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>nouvelle_equipe</title>
<link rel="stylesheet" href="css/style.css"/>
</head>
<body>
	<header>Creer une nouvelle equipe pour un projet</header>
	
		<c:forEach items="${equipe}" var="equipe">
		<li>${user.getIdUser}</li>
		<li>${projet.getIdProjet}</li>
		<li>${equipe.getIdEquipe}</li>
		<li>${createur.getIdCreateur};</li>
	</c:forEach>
				
			
			<input type="submit" value="Valider"><br>
		</form>

</body>
</html>