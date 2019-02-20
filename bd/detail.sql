	-- Ordre d'insertion en fonction des contraintes referentielles
	
	etat_candidature
	evenement
	formation
	module
	personne

	echange -- personne
	invitation -- personne, evenement
	qcm -- personne
	session_formation -- formation

	avis -- personne, qcm
	candidature -- session_formation, personne
	evaluation -- session_formation, personne
	passage_qcm -- personne, qcm
	projet -- personne, session_formation
	question -- qcm
	seance -- personne, session_formation, module

	equipe -- personne, projet
	note -- evaluation, personne
	presence -- personne, seance
	reponse -- question, passage_qcm

	membre_equipe -- personne, equipe