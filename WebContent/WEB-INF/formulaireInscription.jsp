<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>formulaireInscription</title>
	<link rel="stylesheet" href="style.css"/>
</head>
<body>
	<form method="post" action="inscription">
		<label for="nom">Nom :</label>
		<input type="text" placeholder="nom" name="nom" id="nom" value="${param['nom'] }" />
		<br/>

		<label for="prenom">Prenom :</label>
		<input type="text" placeholder="penom" name="prenom" id="prenom"  value="${param['prenom'] }" />
		<br/>
		
		<label for="mail">Mail :</label>
		<input type="text" placeholder="mail" name="mail" id="mail" value="${param['mail'] }" />
		<br/>
		
		<label for="tel">Téléphone :</label>
		<input type="text" placeholder="tel" name="tel" id="tel" value="${param['tel'] }" />
		<br/>
		
		<label for="adresse">Adresse :</label>
		<input type="text" placeholder="adresse" name="adresse" id="adresse" value="${param['adresse'] }" />
		<br/>
		
		<label for="cp">Code Postal :</label>
		<input type="text" placeholder="code postal" name="codePostal" id="cp" value="${param['codePostal'] }" />
		<br/>
		
		<label for="ville">ville :</label>
		<input type="text" placeholder="ville" name="ville" id="ville" value="${param['ville'] }" />
		<br/>
		
		<c:set var="checked" value=""/>
		<c:if test="${Boolean.parseBoolean(param['estFormateur'])}">
			<c:set var="checked" value="checked"/>
		</c:if>
		<label for="estFormateur">EstFormateur :</label>
		<input type="checkbox" name="estFormateur" id="estFormateur" ${checked} value="true"/>
		<br/>
		
		<c:set var="checked" value=""/>
		<c:if test="${Boolean.parseBoolean(param['estAdministration'])}">
			<c:set var="checked" value="checked"/>
		</c:if>
		<label for="estAdministration">EstAdministration :</label>
		<input type="checkbox" name="estAdministration" id="estAdministration" ${checked} value="true"/>
		<br/>
		
		<label for="motDePasse">Mot de passe :</label>
		<input type="password" placeholder="mot de passe" name="motDePasse" id="motDePasse" >
		<span class="erreur">${erreurMotDePasse}</span>
		<br/>   
		              
		<label for="password">Confirmation mot de passe :</label>
		<input type="password" placeholder="confirmation" name="motDePasse2" id="motDePasse2"  >
		<span class="erreur">${erreurMotDePasse2}</span>
		<br/>
		
	   	<button type="submit">Valider</button>
	   	<br/>
	   	<div class="erreur">${erreurDao}</div>
	</form>

</body>
</html>