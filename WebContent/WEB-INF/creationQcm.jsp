<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="stylesheet" href="css/style.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://riversun.github.io/jsframe/jsframe.js"></script>

<!-- jQuery Modal -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
 
<title>Création QCM</title>
</head>
<body>

<div  id="bloc">
	<%@ include  file="./header.jsp"%>
		<section id="contain">
			<center>
			    <form action="CreerQcmServlet" method="post">
			    	<input type="hidden" id="idQcm" name="idQcm" value="">
			        <table border="1" style="border-spacing:0">
			            <tbody>
			                <tr>
			                    <td id="item_table">
			                        <b>Titre de QCM:</b><br>
			                        <input type="text" name="titreQcm" id="titreQcm">
			                    </td>
			                    <td>
			                    	<div align="center"><button type="button" name="envoyerQcmBtn" id="envoyerQcmBtn">Enregistrer le QCM</button></div>
			                    </td>
			                </tr>
			                <tr>
			                	<td colspan="2">
			                		<div align="center">
			                			<a href="#myModal" rel="modal:open">
			                				<button type="button" rel="modal:open" name="ajouteQuestionBtn" id="ajouteQuestionBtn" href="#myModal" rel="modal:open" disabled>
			                					Ajouter une question
			                				</button>
			                			</a>
			                		</div>
			                	</td>
			                </tr>
			            </tbody>
			        </table><br>
					
			    </form>
			</center>
			
			<div id="myModal" class="modal">
				<form>
				<table border="1" style="border-spacing:0">
	                <tbody>
	                    <tr>
	                        <td colspan="3"><b>Question: </b><input type="text" name="enonceQuestion" id="enonceQuestion" value=""></td>
	                    </tr>
	                    <tr>
	                        <td><b>#</b></td><td><b>réponse possible</b></td><td><b>est correcte?</b></td>
	                    </tr>
	                    <tr>
	                        <td>1</td><td><input type="text" name="reponsePossible1" id="reponsePossible1" value=""></td><td><input type="radio" name="reponseCorrect" id="reponseCorrect" value="1"></td>
	                    </tr>
	                    <tr>
	                        <td>2</td><td><input type="text" name="reponsePossible2" id="reponsePossible2" value=""></td><td><input type="radio" name="reponseCorrect" id="reponseCorrect" value="2"></td>
	                    </tr>
	                    <tr>
	                        <td>3</td><td><input type="text" name="reponsePossible3" id="reponsePossible3" value=""></td><td><input type="radio" name="reponseCorrect" id="reponseCorrect" value="3"></td>
	                    </tr>
	                    <tr>
	                        <td>4</td><td><input type="text" name="reponsePossible3" id="reponsePossible4" value=""></td><td><input type="radio" name="reponseCorrect" id="reponseCorrect" value="4"></td>
	                    </tr>
	                    <tr>
	                        <td colspan="3">
	                        	<div align="center">
	                        		<a href="#" rel="modal:close">
	                        			<button type="submit" name="envoyerQuestionBtn" id="envoyerQuestionBtn">Enregistrer la question</button>
	                        		</a>
	                        	</div>
	                        </td>
	                    </tr>
	                </tbody>
	            </table>
	            </form>
			</div>

		</section>
	<%@ include  file="./footer.jsp"%>
</div >

<script type="text/javascript">

$(document).ready(function()
{
	$("#envoyerQcmBtn").click(function() {
		var titreQcm =  $("#titreQcm").val();
		$(this).attr("disabled", true);
		$("#titreQcm").attr("disabled", true);
		$("#ajouteQuestionBtn").removeAttr("disabled");
		
		//alert(titreQcm);

		$.ajax({
			type: "POST",
			url: "CreationQcmServlet",
			data: {titreQcm1:titreQcm},
			cache: false,
			success: function(responseText)
			{
			    const jsFrame = new JSFrame();
				var message = "";
				
				if (responseText == "-1") {
					message = "Erreur dans la procédure ajouter le QCM!";
					toastError(jsFrame, message);
				}
				else {
					message = "QCM bien ajouté!";
					toastSuccess(jsFrame, message);
				    $('#idQcm').val(responseText);
				}
			}
		});
	});
	
	$("#envoyerQuestionBtn").click(function() {
		var idQcm =  $('#idQcm').val();
		var enonceQuestion =  $("#myModal #enonceQuestion").val();
		var reponsePossible1 =  $("#myModal #reponsePossible1").val();
		var reponsePossible2 =  $("#myModal #reponsePossible2").val();
		var reponsePossible3 =  $("#myModal #reponsePossible3").val();
		var reponsePossible4 =  $("#myModal #reponsePossible4").val();
		var reponseCorrect =  $("#myModal #reponseCorrect:checked").val();
		
		$("#myModal").find('form').trigger('reset');
		
		/*
		alert("idQcm = "+idQcm+"\nenonceQuestion = "+enonceQuestion+"\nreponsePossible1 = "
				+reponsePossible1+"\nreponsePossible2 = "+reponsePossible2+"\nreponsePossible3 = "
				+reponsePossible3+"\nreponsePossible4 = "+reponsePossible4+"\nreponseCorrect = "+reponseCorrect);
		*/
		
		$.ajax({
			type: "POST",
			url: "CreationQuestionsServlet",
			data: {
				idQcm1: idQcm,
				enonceQuestion1: enonceQuestion,
				reponsePossible11: reponsePossible1,
				reponsePossible22: reponsePossible2,
				reponsePossible33: reponsePossible3,
				reponsePossible44: reponsePossible4,
				reponseCorrect1: reponseCorrect
			},
			cache: false,
			success: function(responseText)
			{
			    const jsFrame = new JSFrame();
				var message = "";
				
				if (responseText == "-1") {
					message = "Erreur dans la procédure ajouter la question et rélatives reponses!";
					toastError(jsFrame, message);
				}
				else {
					message = "Question bien ajouté!";
					toastSuccess(jsFrame, message);
				}
			}
		});
	});
});

function toastSuccess(jsFrame, message) {
    jsFrame.showToast({
        width: 260,
        height: 100,
        duration: 2000,
        align: 'top',
        style: {
            borderRadius: '2px',
            backgroundColor: 'rgba(50,205,50,0.9)',
        },
        html: '<span style="color:darkgreen;font-size:25px;fon-weight:600;">'+message+'</span>',
        closeButton: true,
        closeButtonColor: 'darkgreen'
    });
};

function toastError(jsFrame, message) {
    jsFrame.showToast({
        width: 260,
        height: 100,
        duration: 2000,
        align: 'top',
        style: {
            borderRadius: '2px',
            backgroundColor: 'rgba(255,0,0,0.9)',
        },
        html: '<span style="color:maroon;font-size:25px;fon-weight:600;">'+message+'</span>',
        closeButton: true,
        closeButtonColor: 'maroon'
    });
};

</script>

</body>
</html>
