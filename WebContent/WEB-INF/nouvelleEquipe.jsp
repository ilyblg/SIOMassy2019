<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>nouvelle_equipe</title>
</head>
<body>
	<header>Creer une nouvelle equipe pour un projet</header>
		
		<form action="CreerEquipe" method="post">
			<label>Nom : </label><input type="text" name="leNom"><br>
			<label>Prenom : </label><input type="text" name="lePrenom"><br>
			<div><label>Projet : </label><br>
				<table class="table">
					<tr>
						<td class="projet" type="text" name="nomProjet">
						<% String t = (String) session.getAttribute("VarProjet");
						out.print(t);%>
						</td><td class="radioBouton"><INPUT type="radio"></td>
				</table>
			</div>
				
			
			<input type="submit" value="Valider"><br>
		</form>

</body>
</html>