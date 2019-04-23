<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css"/>
</head>
<body>
	<c:if test="${sessionScope['user'].estFormateur}">
		<%@ include file="./formateurVueConnexion.jsp"%>
	</c:if>

	<c:if test="${sessionScope['user'].estAdministration}">
		<%@ include file="./administrationVueConnexion.jsp"%>
	</c:if>

	<c:if
		test="${!sessionScope['user'].estFormateur && ! sessionScope['user'].estAdministration}">
		<%@ include file="./stagiaireVueConnexion.jsp"%>
	</c:if>
</body>
</html>