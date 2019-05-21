<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="stylesheet" href="css/style.css"/>
<title>Résultat QCM</title>
</head>
<body>

<div  id="bloc">
	<%@ include  file="./header.jsp"%>
		<section id="contain">

			<h3>Vous avez obtenu ${score} points sur ${max} (${percentage}%)</h3>

		</section>
	<%@ include  file="./footer.jsp"%>
</div >

</body>
</html>