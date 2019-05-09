<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Page création QCM</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" /> -->
</head>
<body>
	<form action="AfficherClients" method="post">
		<table>
			<thead>
				<tr><th scope="col" colspan="4"><b>Titre qcm:</b></th></tr>
				<tr><th scope="row" colspan="4"><input type="text" name="titreqcm" /></th></tr>
			</thead>
			<tbody id="item_table">					
				<tr>
					<th scope="col"><b>Question 1:</b></th>
					<th colspan="2"><input type="text" name="enonce_question1" /></th>
					<th><button type="button" name="enleveQuestion" id="enleveQuestionBtn">-</button></th>
				</tr>
				<tr><th></th><th>Correct</th><th>Choice</th></tr>
				
				<tr><td>A</td><td><input type="radio" name="correct1" value="1"></td><td><input type="text" name="reponse_possible1" /></td></tr>
				<tr><td>B</td><td><input type="radio" name="correct1" value="2"></td><td><input type="text" name="reponse_possible2" /></td></tr>
				<tr><td>C</td><td><input type="radio" name="correct1" value="3"></td><td><input type="text" name="reponse_possible3" /></td></tr>
				<tr><td>D</td><td><input type="radio" name="correct1" value="4"></td><td><input type="text" name="reponse_possible4" /></td></tr>
			</tbody>
			<tfoot>
				<tr><th colspan="3"></th><th><button type="button" name="ajouteQuestion" id="ajouteQuestionBtn">+</button></th></tr>
			</tfoot>
       	</table>
		<button type="submit">Valider</button>
	</form>
	
	<script type="text/javascript">
	$(document).ready(function()
	{
		$("#ajouteQuestionBtn").click(function()
		{
			var html = '<tr>';
			  html += '<tr class="divider"><th scope="col"><b>Question 1:</b></th>';
			  html += '<th colspan="2"><input type="text" name="enonce_question1" /></th>';
			  html += '<th><button type="button" name="enleveQuestion" id="enleveQuestionBtn">-</button></th>';
			  html += '</tr>';
			  html += '<tr><th></th><th>Correct</th><th>Choice</th></tr>';
			  
			  html += '<tr><td>A</td><td><input type="radio" name="correct1" value="1"></td><td><input type="text" name="reponse_possible1" /></td></tr>';
			  html += '<tr><td>B</td><td><input type="radio" name="correct1" value="2"></td><td><input type="text" name="reponse_possible2" /></td></tr>';
			  html += '<tr><td>C</td><td><input type="radio" name="correct1" value="3"></td><td><input type="text" name="reponse_possible3" /></td></tr>';
			  html += '<tr><td>D</td><td><input type="radio" name="correct1" value="4"></td><td><input type="text" name="reponse_possible4" /></td></tr>';
			  $('#item_table').append(html);
			
			$.ajax
			({
				/*
				type: "POST",
				url: "comptabilite",
				data: {idCategorie:category_id},
				cache: false,
				success: function(fonctions)
				{
					$response = $(fonctions).find('#response').html(); // This works if because div is not TOP level (layout.php, ...)
					//$response = $(fonctions).filter('#response').html(); // This works if div is TOP level
					// console.log($response);
					$("#id_fonction_<?= $companyName ?>").html($response);
				}
				*/
			});
		});
		
		$("#enleveQuestionBtn").click(function() {
			//$(this).parent().parent().prepand();
			$(this).parents('#item_table').remove();

			$.ajax({
				/*
				type: "POST",
				url: "comptabilite",
				data: {idCategorie:category_id},
				cache: false,
				success: function(fonctions)
				{
					$response = $(fonctions).find('#response').html(); // This works if because div is not TOP level (layout.php, ...)
					//$response = $(fonctions).filter('#response').html(); // This works if div is TOP level
					// console.log($response);
					$("#id_fonction_<?= $companyName ?>").html($response);
				}
				*/
			});
		});
	});
	</script>
</body>
</html>