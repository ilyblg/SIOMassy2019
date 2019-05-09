<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Agriotes 2019</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<div id="bloc">
		<%@ include file="header.jsp"%>
		<c:if test="${sessionScope['user'] == null }">
			<h2>BIENVENUE</h2>
		</c:if>
		<c:if test="${sessionScope['user'] != null}">
			<c:if test="${sessionScope['user'].estFormateur}">
				<h2>FORMATEUR</h2>
				<section>
					<p>Bonjour ${sessionScope['user'].prenom}</p>
				</section>
			</c:if>
			<c:if test="${sessionScope['user'].estAdministration}">
				<h2>ADMINISTRATION</h2>
				<section>
					<p>Bonjour ${sessionScope['user'].prenom}</p>
				</section>
			</c:if>
			<c:if
				test="${!sessionScope['user'].estFormateur && ! sessionScope['user'].estAdministration}">
				<h2>STAGIAIRE</h2>
				<section>
					<p>Bonjour ${sessionScope['user'].prenom}</p>
				</section>
			</c:if>
		</c:if>
		<!-- Banniere informations et news diverses -->
		<div class="banniere">
			<img src="images/banner_1.jpg" title="banniere d'informations">
		</div>

		<!-- Présentaions du site inetrnet et des formations -->
		<section>
			<h2>Présentation</h2>
			<p>
				Contrary to popular belief, Lorem Ipsum is not simply random text.
				It has roots in a piece of classical Latin literature from 45 BC,
				making it over 2000 years old. Richard McClintock, a Latin professor
				at Hampden-Sydney College in Virginia, looked up one of the more
				obscure Latin words, consectetur, from a Lorem Ipsum passage, and
				going through the cites of the word in classical literature,
				discovered the undoubtable source. Lorem Ipsum comes from sections
				1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes
				of Good and Evil) by Cicero, written in 45 BC. This book is a
				treatise on the theory of ethics, very popular during the
				Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit
				amet..", comes from a line in section 1.10.32.<br> The standard
				chunk of Lorem Ipsum used since the 1500s is reproduced below for
				those interested. Sections 1.10.32 and 1.10.33 from "de Finibus
				Bonorum et Malorum" by Cicero are also reproduced in their exact
				original form, accompanied by English versions from the 1914
				translation by H. Rackham.
			</p>
		</section>

		<section class="inbox">
			<img src="images/img01.jpg" alt="" title="image_de_programmation">
			<h3>Brevet Technicien Supérieur Option : programmation (SIO)</h3>
			<p>
				Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
				eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
				ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
				aliquip ex ea commodo consequat.<br> <a href="#">En savoir
					plus</a>
			</p>
		</section>

		<section class="inbox">
			<img src="images/img02.jpg" alt="" title="image_cable_reseaux">
			<h3>Brevet Technicien Supérieur Option : Réseaux (SSIR)</h3>
			<p>
				Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
				eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
				ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
				aliquip ex ea commodo consequat.<br> <a href="#">En savoir
					plus</a>
			</p>
		</section>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>