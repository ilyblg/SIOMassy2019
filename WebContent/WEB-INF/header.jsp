<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<h1>Agriotes</h1>
	<nav>
		<ul>
			<li><a href="accueil" class="index"> Accueil </a></li>
			<c:if test="${sessionScope['user'] == null }">
				<!-- 
				<li><a href=" # "> Pr�sentation </a>
					<ul>
						<li><a href=" # "> Le centre </a></li>
						<li><a href=" # "> Les EQUIPES </a></li>
						<li><a href=" # "> L'administration </a></li>
					</ul></li>
				<li><a href=" # "> Formations </a>
					<ul>
						<li><a href=" # "> BTS SIO </a></li>
						<li><a href=" # "> BTS SSIR </a></li>

					</ul></li>
 -->
				<li><a href="inscription"> S'inscrire </a></li>
				<li><a href="connexion"> Se connecter </a></li>
			</c:if>
			<c:if test="${sessionScope['user'] != null}">
				<c:if test="${sessionScope['user'].estFormateur}">
					<li>EVALUATION
						<ul>
							<li><a href="creationQcm">Cr�er un QCM</a></li>
<!-- 						<li><a href="">Cr�er une �valuation</a></li> -->
							<li><a href="evaluations">Liste �valuations</a></li>
							<li><a href=""> R�sultat QCM </a></li>
						</ul></li>
					<li><a href=" # "> PROJET </a>
						<ul>
<!-- 						<li><a href="">En cours</a></li> -->
<!-- 						<li><a href="">Projet</a></li> -->
<!-- 						<li><a href="">Equipes</a></li> -->
						</ul></li>
				</c:if>

				<c:if test="${sessionScope['user'].estAdministration}">
					<li><a href=" # "> GESTION ELEVE</a>
						<ul>
<!-- 						<li><a href="">Changer �tat candidature</a></li> -->
<!-- 						<li><a href=" # "> Sessions </a></li> -->
							<li><a href="candidatures"> Candidatures </a></li>
						</ul>
					</li>
				</c:if>
				<c:if
					test="${!sessionScope['user'].estFormateur && ! sessionScope['user'].estAdministration}">
					<li>EVALUATION
						<ul>
							<li><a href="passageQcm"> QCM </a></li>
							<li><a href="avis">Mon avis</a></li>
						</ul></li>
					<li><a href=" # "> PROJET </a>
						<ul>
<!-- 						<li><a href=""> En cours </a></li> -->
							<li><a href="creerEquipe"> Cr�er �quipes </a></li>
						</ul></li>
				</c:if>
				<li><a href=" # "> PROFIL </a>
					<ul>
<!-- 					<li><a href=" # "> Afficher </a></li> -->
<!-- 					<li><a href=" # "> Modifier </a> -->
<!-- 					<li><a href=""> Changer mot de passe </a></li> -->
						<li><form action="deconnexion" method="post">
								<button type="submit">Se d�connecter</button>
							</form></li>
					</ul></li>
				</c:if>
		</ul>
	</nav>
</header>
