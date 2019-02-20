DELIMITER $$

DROP PROCEDURE IF EXISTS agriotes2019_reset $$
CREATE PROCEDURE agriotes2019_reset(date_effet DATETIME)
BEGIN
	IF date_effet IS NULL THEN
		SET date_effet = now();
	END IF;

  SET FOREIGN_KEY_CHECKS=0;
	TRUNCATE TABLE avis;
	TRUNCATE TABLE candidature;
	TRUNCATE TABLE echange;
	TRUNCATE TABLE equipe;
	TRUNCATE TABLE etat_candidature;
	TRUNCATE TABLE evaluation;
	TRUNCATE TABLE evenement;
	TRUNCATE TABLE formation;
	TRUNCATE TABLE invitation;
	TRUNCATE TABLE membre_equipe;
	TRUNCATE TABLE module;
	TRUNCATE TABLE note;
	TRUNCATE TABLE passage_qcm;
	TRUNCATE TABLE personne;
	TRUNCATE TABLE presence;
	TRUNCATE TABLE projet;
	TRUNCATE TABLE question;
	TRUNCATE TABLE qcm;
	TRUNCATE TABLE reponse;
	TRUNCATE TABLE seance;
	TRUNCATE TABLE session_formation;
  SET FOREIGN_KEY_CHECKS=1;

	-- Ordre d'insertion en fonction des contraintes referentielles
	/*
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
	*/
	INSERT INTO etat_candidature(id_etat_candidature, libelle) VALUES
    (1, 'non traitée'),
    (2, 'refusé'),
    (3, 'reçu'),
    (4, 'liste d''attente'),
    (5, 'accepté'),
    (6, 'inscrit'),
    (7, 'desisté');
    

	INSERT INTO evenement(id_evenement, nom, date_effet) VALUES
    (1, 'Remise des Diplome', date_effet - INTERVAL 2 YEAR),
    (2, 'Remise des Diplome', date_effet - INTERVAL 1 YEAR),
    (3, 'Remise des Diplome', date_effet - INTERVAL 1 YEAR),
    (4, 'Remise des Diplome', date_effet - INTERVAL 2 MONTH),
    (5, 'Réunion d''inscription', date_effet - INTERVAL 1 MONTH);
    

    INSERT INTO formation(id_formation, nom, description) VALUES
    (1, 'SIO SLAM', 'BTS SIO option programmation'),
    (2, 'SIO SISR', 'BTS SIO option réseaux');



	INSERT INTO module(id_module, nom) VALUES
	(1, 'Math'),
    (2, 'Réseau'),
	(3, 'Bases de données'),
    (4, 'Android'),
    (5, 'Culture Générale'),
    (6, 'Economie - droit');
	

	INSERT INTO personne(id_personne, nom, prenom, mail, tel, adresse, code_postal, ville, mot_de_passe, est_formateur, est_administrateur, date_inscription) VALUES
    (1,'Théo','Moreau',         't,moreau@proboite.net',       '0691564808','82, Cours Marechal-Joffre','78180','MONTIGNY-LE-BRETONNEUX','condimentum', 0, 1,   date_effet - INTERVAL 1 YEAR + INTERVAL 2 DAY), # administrateur
    (2,'Malik','Aubert',        'm.aubert@freelance.edu',      '0758143009','98, Avenue De Marlioz','95100','ARGENTEUIL','aubertfraterie', 1, 0,                date_effet - INTERVAL 7 MONTH + INTERVAL 8 DAY), # Formateur
    (3,'Jérémy','Leclercq',     'j.leclercq@amasone.com',      '0610602483','84, avenue Voltaire','78600','MAISONS-LAFFITTE','motdepasserigoureux', 0, 0,       date_effet - INTERVAL 6 MONTH + INTERVAL 15 DAY), # formateur
    (4,'Mirabelle','Huppé',     'm.huppé@gougleu.com',         '0646971033','56, rue Jean-Monnet','95190','GOUSSAINVILLE','gousshupp', 0, 0,                    date_effet - INTERVAL 6 MONTH + INTERVAL 3 DAY),
    (5,'Silvia','Gonzalez',     's.gonzalez@gmale.edu',        '0648369680','53, rue du Faubourg National','95150','TAVERNY','rictusmaloficus', 0, 0,           date_effet - INTERVAL 6 MONTH + INTERVAL 1 DAY),
    (6,'Dylan','Briar',         'd.briar@classprepa.edu',      '0187396561','52, boulevard Bryas','92400','COURBEVOIE','12340000', 0, 0,                        date_effet - INTERVAL 6 MONTH + INTERVAL 3 DAY),
    (7,'Alexandre','Morel',     'a.lexandre@gougleu.com',      '0783484403','56, rue des Soeurs','LA CELLE-SAINT-CLOUD','Sed', 1, 0,                            date_effet - INTERVAL 5 MONTH + INTERVAL 8 DAY), # formateur
    (8,'Montoya','Lester',      'm.lester@amasone.com',        '0182954239','73, rue du Château','78100','SAINT-GERMAIN-EN-LAYE','lorem', 0, 0,                 date_effet - INTERVAL 4 MONTH + INTERVAL 4 DAY),
    (9,'Figueroa','Christian',  'f.christian@gmale.fr',        '0114629593','90, rue du Président Roosevelt','91600','SAVIGNY-SUR-ORGE ','dictum', 0, 0,        date_effet - INTERVAL 4 MONTH + INTERVAL 2 DAY),
    (10,'Marwane','Roche',      'm.roche@gmale.com',           '0128645819','71, rue La Boétie','75015','PARIS','risus', 0, 0,                                  date_effet - INTERVAL 4 MONTH + INTERVAL 2 DAY),
    (11,'Justice','Charles',    'j.charles@freelance.edu',     '0131323259','35, rue Lenotre','93240','STAINS','pede', 0, 0,                                    date_effet - INTERVAL 4 MONTH + INTERVAL 1 DAY), # formateur
    (12,'Nicolas','Colin',      'n.colin@formation.net',       '0133847860','33, rue de Groussay','93230','ROMAINVILLE','venenatis', 0, 1,                      date_effet - INTERVAL 3 MONTH + INTERVAL 1 DAY),
    (13,'Didier','Vasseur',     'd.vasseur@classprepa.edu',    '0771775852','18, rue Victor Hugo','91940','LES ULIS','risus', 1, 0,                             date_effet - INTERVAL 2 MONTH + INTERVAL 3 DAY),
    (14,'Stephanie','Platano',  's.platano@proboite.net',      '0642156271','88, rue du Fossé des Tanneurs','75008','PARIS','sed', 1, 1,                        date_effet - INTERVAL 2 MONTH + INTERVAL 4 DAY), # formateur & admin
    (15,'Brigitte','Masson',    'b.masson@gougleu.com',        '0103946453','15, rue du Clair Bocage','75013','PARIS','ligula', 0, 0,                           date_effet - INTERVAL 2 MONTH + INTERVAL 2 DAY),
    (16,'Othmane','Fernandez',  'o.fernandez@gmale.net',       '0742542658','23, rue des six frères Ruellan','75018','PARIS','nisl', 0, 0,                      date_effet - INTERVAL 2 MONTH + INTERVAL 1 DAY),
    (17,'Samuel','Tisserand', 's.tisserand@formation.net',     '0645507077','86, Place Napoléon','59130','LAMBERSART','passwordsecured', 0, 1,                  date_effet - INTERVAL 1 MONTH); # administrateur
    
    INSERT INTO echange(id_echange, id_personne, instant, texte) VALUES
    (1, 2, date_effet - INTERVAL 2 YEAR + INTERVAL 5 MONTH, 'Demande d''information concernant la formation'),
    (2, 2, date_effet - INTERVAL 2 YEAR + INTERVAL 7 MONTH, 'Demande d''inscription'),
    (3, 2, date_effet - INTERVAL 2 YEAR + INTERVAL 7 MONTH, 'Confirmation de l''invitation a la journée d''information'),
    (4, 22, date_effet - INTERVAL 2 YEAR + INTERVAL 7 MONTH + INTERVAL 1 DAY, 'Contact 1'),
    (5, 22, date_effet - INTERVAL 2 YEAR + INTERVAL 7 MONTH + INTERVAL 3 DAY, 'Contact 2'),
    (6, 22, date_effet - INTERVAL 2 YEAR + INTERVAL 7 MONTH + INTERVAL 5 DAY, 'Contact 3'),
    (7, 22, date_effet - INTERVAL 2 YEAR + INTERVAL 8 MONTH, 'Contact 4'),
    (8, 22, date_effet - INTERVAL 2 YEAR + INTERVAL 8 MONTH + INTERVAL 1 DAY, 'Contact 5'),
    (9, 2, date_effet - INTERVAL 2 DAY, 'Demande d''information'),
    (10, 2, date_effet, 'Demande d''annulation d''inscription');
   
    INSERT INTO invitation(id_personne, id_evenement) VALUES
    (3, 1),
    (8, 1),
    (13, 2),
    (16, 2);

    INSERT INTO qcm(id_qcm, date_creation, titre, id_formateur) VALUES
    (1, date_effet, 'Les pratiques utils', 7),
    (2, date_effet, 'Les questions sans questionnements', 2),
    (3, date_effet, 'Pratiques de la véracités des propos', 24),
    (4, date_effet, 'Test de langue parlementaire', 13);

    INSERT INTO session_formation(id_session_formation, id_formation, date_debut, date_fin) VALUES
    (1, 1, date_effet - INTERVAL 2 YEAR, date_effet - INTERVAL 1 YEAR), -- passé
    (2, 2, date_effet - INTERVAL 8 MONTH, date_effet + INTERVAL 6 MONTH), -- en cours
    (3, 1, date_effet - INTERVAL 2 MONTH, date_effet + INTERVAL 1 YEAR), -- en cours
    (4, 2, date_effet + INTERVAL 6 MONTH, date_effet + INTERVAL 2 YEAR); -- ouverte a candidature
    
    INSERT INTO avis(id_personne, id_questionnaire, commentaire, note, date_avis) VALUES
    (2,  1, 'Commentaire inutile', 6,  date_effet - INTERVAL 2 YEAR - INTERVAL 5 MONTH),
    (1,  3, 'Commentaire inutile', 12, date_effet - INTERVAL 2 YEAR - INTERVAL 4 MONTH),
    (16, 2, 'Commentaire inutile', 3,  date_effet - INTERVAL 1 YEAR - INTERVAL 2 MONTH),
    (8,  4, 'Commentaire inutile', 16, date_effet - INTERVAL 1 YEAR - INTERVAL 2 MONTH),
    (12, 1, 'Commentaire inutile', 7,  date_effet - INTERVAL 1 YEAR - INTERVAL 1 MONTH),
    (1,  3, 'Commentaire inutile', 14, date_effet - INTERVAL 8 MONTH - INTERVAL 5 DAY),
    (10, 2, 'Commentaire inutile', 8,  date_effet - INTERVAL 6 MONTH - INTERVAL 2 DAY),
    (6,  4, 'Commentaire inutile', 10, date_effet - INTERVAL 4 MONTH - INTERVAL 1 DAY),

    INSERT INTO candidature(id_personne, id_session_formation, id_etat_candidature, date_effet) VALUES
    (1, 1, 6, date_effet - INTERVAL 2 YEAR - INTERVAL 2 MONTH),
    (2, 1, 6, date_effet - INTERVAL 2 YEAR - INTERVAL 2 MONTH + INTERVAL 2 DAY),
    (3, 1, 6, date_effet - INTERVAL 2 YEAR - INTERVAL 2 MONTH + INTERVAL 8 DAY),
    (4, 1, 6, date_effet - INTERVAL 2 YEAR - INTERVAL 2 MONTH + INTERVAL 12 DAY),
    (5, 1, 6, date_effet - INTERVAL 2 YEAR - INTERVAL 2 MONTH + INTERVAL 19 DAY),
    (6, 2, 6, date_effet - INTERVAL 2 YEAR - INTERVAL 2 MONTH + INTERVAL 23 DAY),
    (7, 2, 6, date_effet - INTERVAL 2 YEAR - INTERVAL 2 MONTH + INTERVAL 24 DAY),
    (8, 2, 6, date_effet - INTERVAL 2 YEAR - INTERVAL 2 MONTH + INTERVAL 28 DAY),
    (9, 2, 6, date_effet - INTERVAL 8 MONTH - INTERVAL 2 MONTH + INTERVAL 2 DAY),
    (10, 2, 6, date_effet - INTERVAL 8 MONTH - INTERVAL 2 MONTH + INTERVAL 8 DAY),
    (11, 2, 6, date_effet - INTERVAL 8 MONTH - INTERVAL 2 MONTH + INTERVAL 12 DAY),
    (12, 2, 3, date_effet - INTERVAL 8 MONTH - INTERVAL 2 MONTH + INTERVAL 23 DAY),
    (13, 3, 6, date_effet - INTERVAL 2 MONTH - INTERVAL 2 MONTH + INTERVAL 9 DAY),
    (14, 3, 7, date_effet - INTERVAL 2 MONTH - INTERVAL 2 MONTH + INTERVAL 12 DAY),
    (15, 3, 6, date_effet - INTERVAL 2 MONTH - INTERVAL 2 MONTH + INTERVAL 19 DAY),
    (16, 3, 7, date_effet - INTERVAL 2 MONTH - INTERVAL 2 MONTH + INTERVAL 23 DAY),
    (17, 3, 3, date_effet - INTERVAL 2 MONTH - INTERVAL 2 MONTH + INTERVAL 28 DAY),
    (18, 4, 6, date_effet - INTERVAL 1 MONTH + INTERVAL 2 DAY),
    (19, 4, 6, date_effet - INTERVAL 1 MONTH + INTERVAL 5 DAY),
    (20, 4, 6, date_effet - INTERVAL 1 MONTH + INTERVAL 8 DAY),
    (21, 4, 2, date_effet - INTERVAL 1 MONTH + INTERVAL 12 DAY),
    (22, 4, 3, date_effet - INTERVAL 1 MONTH + INTERVAL 16 DAY);

    INSERT INTO evaluation(id_evaluation, id_session_formation, id_module, id_formateur, date_debut, nb_minutes, titre) VALUES
    (1, 1, 1, 23, date_effet - INTERVAL 1 YEAR - INTERVAL 4 MONTH, 120, 'Evaluation finale de mathématique'),
    (2, 1, 2, 24, date_effet - INTERVAL 1 YEAR - INTERVAL 4 MONTH, 240, 'Evaluation mise en place d''un serveur LAMP'),
    (3, 2, 2, 24, date_effet, 60, 'Evaluation réseau 2'),
    (4, 3, 2, 24, date_effet + INTERVAL 4 MONTH, 60, 'Evaluation réseau 3'),
    (5, 3, 1, 23, date_effet, 60, 'Math 2'),
    (6, 3, 1, 23, date_effet + INTERVAL 4 MONTH, 120, 'Math 3');

    INSERT INTO passage_qcm(id_passage_qcm, date_passage, id_qcm, id_personne) VALUES
    (1, date_effet - INTERVAL 1 YEAR - INTERVAL 3 MONTH, 1, 4),
    (2, date_effet - INTERVAL 1 YEAR - INTERVAL 4 MONTH, 1, 3),
    (3, date_effet, 2, 6),
    (4, date_effet, 2, 8),
    (5, date_effet, 2, 10),
    (6, date_effet + INTERVAL 3 MONTH, 3, 5),
    (7, date_effet + INTERVAL 3 MONTH, 3, 9),
    (8, date_effet + INTERVAL 4 MONTH, 4, 16),

    INSERT INTO projet(id_projet, id_formateur, id_session_formation, titre, date_creation, date_butoir) VALUES
    (1, 23, 1, 'Maintient d''un site web', date_effet - INTERVAL 1 YEAR - INTERVAL 3 MONTH, date_effet - INTERVAL 1 YEAR - INTERVAL 3 MONTH),
    (2, 24, 1, 'Calcule de matrice', date_effet - INTERVAL 1 YEAR - INTERVAL 3 MONTH, date_effet - INTERVAL 1 YEAR - INTERVAL 3 MONTH),
    (3, 23, 1, 'Maintient d''un site web', date_effet - INTERVAL 1 YEAR - INTERVAL 3 MONTH, date_effet - INTERVAL 1 YEAR - INTERVAL 3 MONTH),
    (4, 23, 1, 'Maintient d''un site web', date_effet - INTERVAL 1 YEAR - INTERVAL 3 MONTH, date_effet - INTERVAL 1 YEAR - INTERVAL 3 MONTH),
    (5, 23, 1, 'Maintient d''un site web', date_effet - INTERVAL 1 YEAR - INTERVAL 3 MONTH, date_effet - INTERVAL 1 YEAR - INTERVAL 3 MONTH),
    (6, 23, 1, 'Maintient d''un site web', date_effet - INTERVAL 1 YEAR - INTERVAL 3 MONTH, date_effet - INTERVAL 1 YEAR - INTERVAL 3 MONTH);



    # =================================================================================================================================
    #  TOP = OK, BOTTOM = NOT IMPLEMENTED YET
    # =================================================================================================================================



    INSERT INTO seance(id_seance, id_session_formation, id_formateur, id_module, jour, creneau) VALUES
    (1, 1, 23, 1, date_effet - INTERVAL 2 YEAR + INTERVAL 4 MONTH, 1),
    (2, 2, 23, 1, date_effet - INTERVAL 2 YEAR + INTERVAL 4 MONTH, 2),
    (3, 3, 23, 2, date_effet - INTERVAL 2 YEAR + INTERVAL 4 MONTH + INTERVAL 1 DAY, 1),
    (4, 1, 23, 2, date_effet - INTERVAL 2 YEAR + INTERVAL 4 MONTH + INTERVAL 1 DAY, 2),
    (5, 2, 24, 1, date_effet - INTERVAL 2 YEAR + INTERVAL 4 MONTH, 1),
    (6, 3, 24, 2, date_effet - INTERVAL 2 YEAR + INTERVAL 4 MONTH, 2),
    (7, 1, 23, 1, date_effet - INTERVAL 1 YEAR, 2),
    (8, 1, 24, 2, date_effet - INTERVAL 1 YEAR, 4),
    (9, 2, 24, 2, date_effet, 2),
    (10, 3, 24, 2, date_effet + INTERVAL 3 MONTH, 4),
    (11, 3, 23, 1, date_effet, 2),
    (12, 3, 23, 1, date_effet + INTERVAL 3 MONTH + INTERVAL 1 DAY, 1);
        
    INSERT INTO note(id_personne, id_evaluation, note) VALUES
    (1, 1, 14),
    (2, 1, 8),
    (3, 1, 11),
    (4, 1, null),
    (5, 1, null),
    (1, 2, null),
    (2, 2, null),
    (3, 2, 3),
    (4, 2, null),
    (5, 2, 18),
    (6, 3, null),
    (7, 3, null),
    (8, 3, null),
    (9, 3, null),
    (10, 3, null),
    (11, 3, null),
    (13, 4, null),
    (14, 4, null),
    (15, 4, null),
    (16, 4, null),
    (13, 5, null),
    (14, 5, null),
    (15, 5, null),
    (16, 5, null),
    (13, 6, null),
    (14, 6, null),
    (15, 6, null),
    (16, 6, null);
    
    -- La table presence est alimentee par un déclencheur sur seance, qui gere des lignes
    -- de valeur est-present à NULL.
    -- Nous positionnons ainsi les valeurs par des UPDATE
    UPDATE presence SET est_present=1
    WHERE (id_seance, id_personne) IN
	(
		(1, 1), (1, 2), (1,3), (1, 4),
        (2, 6), (2, 7), (2, 10), (2, 11),
        (3, 14), (3, 15), (3, 16),
        (4, 3), (4, 4), (4, 4),
        (5, 6), (5, 7), (5,8), (5, 9), (5, 11),
        (6, 14), (6, 15),
        (7, 3), (7, 5),
        (8, 3), (8, 5)
	);
    UPDATE presence SET est_present=0
    WHERE (id_seance, id_personne) IN
	(
		(1, 5),
        (2, 8), (2, 9),
        (3, 13),
        (4, 1), (4, 2), (4, 5),
        (5, 10),
        (6, 13), (6, 16),
        (7, 1), (6, 2), (6, 3), (6, 4), (6, 5),
        (8, 1), (8, 2)
	);
   

    
    INSERT INTO type_echange(id_type_echange, libelle) VALUES
    (1, 'Email reçu'),
    (2, 'Email envoyé'),
    (3, 'Appel tel reçu'),
    (4, 'Appel tel émis'),
    (5, 'Présence');

    INSERT INTO document(id_document, id_proprietaire, nom, chemin, date_depot) VALUES
    (1, 23, 'document_1', '/agriotes2018/documents/', date_effet - INTERVAL 5 MONTH),
    (2, 23, 'HTML pour les nuls', '/agriotes2018/documents/', date_effet - INTERVAL 2 YEAR + INTERVAL 3 MONTH),
    (3, 24, 'Calcule des matrice', '/agriotes2018/documents/', date_effet - INTERVAL 8 MONTH),
    (4, 25, 'L_art_de_la_gestion_de_stock',  '/agriotes2018/documents/', date_effet - INTERVAL 1 YEAR + INTERVAL 6 MONTH),
    (5, 24, 'Bescherelle', '/agriotes2018/documents/Bescherelle', date_effet);

    INSERT INTO droit_sur_document(id_document, id_session_formation) VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 1),
    (2, 2),
    (3, 1),
    (3, 2),
    (3, 3),
    (4, 2),
    (5, 1);

END$$

CALL agriotes2018_reset(now());