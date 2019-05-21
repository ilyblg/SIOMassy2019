<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<link rel="stylesheet" href="css/style.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://riversun.github.io/jsframe/jsframe.js"></script>
<title>Passage QCM intitulé: ${titreQcm}</title>
</head>
<body>

<div  id="bloc">
	<%@ include  file="./header.jsp"%>
		<section id="contain">
			<h3>Passage QCM intitulé: ${titreQcm}</h3>

			<form action="ResultatServlet" id="formPassageQcm" method="post">
				<input type="hidden" id="idPassageQcm" name="idPassageQcm" value="${idPassageQcm}">
				<c:if test="${questions != null}">
					<c:forEach items="${questions}" var="question">
						<P><b>${question.getEnonce()}</b><BR>
						<c:forEach items="${question.getReponsesPossibles()}" var="reponsePossible">
							<input type="radio" id="reponseDonnee" name="${question.getId()}" value="${reponsePossible.getId()}">${reponsePossible.getReponse()}<BR>
						</c:forEach>
						</p>
					</c:forEach>
				</c:if>
				<c:if test="${questions == null}">
					<h1>Aucune question pour ce QCM</h1>
				</c:if>
				<button type="submit" id="termineQcm" name="termineQcm">Terminer le QCM et afficher le résultat</button>
			</form>

		</section>
	<%@ include  file="./footer.jsp"%>
</div>

<script type="text/javascript">
$(document).ready(function()
{
	$('input:radio[id="reponseDonnee"]').change(function()
	{		
		var idQuestion = $(this).attr("name");
		var idReponse =  $(this).val();
		var idPassageQcm =  $("#idPassageQcm").val();
		
		//alert("idQuestion = "+idQuestion+"\n"+"idReponse = "+idReponse);
		
		$.ajax
		({
			type: "POST",
			url: "ReponsesServlet",
			data: {
				idQuestion1: idQuestion,
				idReponse1: idReponse,
				idPassageQcm1: idPassageQcm
			},
			cache: false,
			success: function(responseText)
			{
			    const jsFrame = new JSFrame();
			    jsFrame.showToast({
			        html: responseText, align: 'top', duration: 2000
			    });
			}
		});
	});
	
	$('#termineQcm').click(function()
	{
		$("#formPassageQcm").submit(function() {
			var idPassageQcm =  $("#idPassageQcm").val();
			var groups = [];
		    $('input:radio[id="reponseDonnee"]').each(function() {
		        if (groups.indexOf(this.name) < 0) {
		            groups.push(this.name);
		        }
		    });
		    var count = groups.length;
			
			if ($('input:radio[id="reponseDonnee"]:checked').length !== count) {
				alert("Faut repondre à toutes les questions!");
				return false;
			}
			
			return true;
	    });
	});
});
</script>

</body>
</html>


