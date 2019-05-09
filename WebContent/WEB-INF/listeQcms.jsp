<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Liste QCMS</title>
</head>
<body>

<form action="PassageQcmServlet" method="post">
	<label for="sidq">Choisissez le QCM que vous voulez passer: </label>
	
	<select name='qcmApasser'>
	    <c:forEach items="${qcms}" var="qcm">
	        <option value="${qcm.id}">${qcm.titre}</option>
	    </c:forEach>
	</select>
	
	<input type="submit" value="Valider">
</form>

</body>
</html>