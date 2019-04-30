DELIMITER $$
DROP TRIGGER IF EXISTS seance_after_insert_trg $$
CREATE TRIGGER seance_after_insert_trg
AFTER INSERT ON seance
FOR EACH ROW
BEGIN
    DECLARE termine BOOLEAN DEFAULT FALSE;
    DECLARE v_id_personne INT;
    -- stagiaires de la session associee a la seance
    DECLARE lignes CURSOR FOR
    SELECT id_personne
    FROM stagiaire
    WHERE id_session_formation=NEW.id_session_formation;
    -- inserer une ligne de presence vide pour chaque stagiaire
    OPEN lignes;
    BEGIN
        DECLARE EXIT HANDLER FOR NOT FOUND SET termine = TRUE;
        REPEAT
            FETCH lignes INTO v_id_personne;
            INSERT INTO presence(id_personne, id_seance, est_present)
            VALUES (v_id_personne, NEW.id_seance, NULL);
        UNTIL termine END REPEAT; 
    END;
    CLOSE lignes;
END$$

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
    TRUNCATE TABLE reponse_possible;
    TRUNCATE TABLE reponse_donnee;
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
    
    INSERT INTO personne(id_personne, prenom, nom, mail, tel, adresse, code_postal, ville, mot_de_passe, est_formateur, est_administration, date_inscription) VALUES
    (1,'Théo','Moreau',         't.moreau@proboite.net',       '0691564808','82, Cours Marechal-Joffre','78180','MONTIGNY-LE-BRETONNEUX', PASSWORD('admin'), 0, 1,   date_effet - INTERVAL 1 YEAR + INTERVAL 2 DAY), # administrateur
    (2,'Malik','Aubert',        'm.aubert@freelance.edu',      '0758143009','98, Avenue De Marlioz','95100','ARGENTEUIL', PASSWORD('formateur'), 1, 0,                date_effet - INTERVAL 7 MONTH + INTERVAL 8 DAY), # Formateur - 2
    (3,'Jérémy','Leclercq',     'j.leclercq@amasone.com',      '0610602483','84, avenue Voltaire','78600','MAISONS-LAFFITTE', PASSWORD('stagiaire'), 0, 0,       date_effet - INTERVAL 6 MONTH + INTERVAL 15 DAY), # formateur - 3
    (4,'Mirabelle','Huppé',     'm.huppé@gougleu.com',         '0646971033','56, rue Jean-Monnet','95190','GOUSSAINVILLE','gousshupp', 0, 0,                    date_effet - INTERVAL 6 MONTH + INTERVAL 3 DAY),
    (5,'Silvia','Gonzalez',     's.gonzalez@gmale.edu',        '0648369680','53, rue du Faubourg National','95150','TAVERNY','rictusmaloficus', 0, 0,           date_effet - INTERVAL 6 MONTH + INTERVAL 1 DAY),
    (6,'Dylan','Briar',         'd.briar@classprepa.edu',      '0187396561','52, boulevard Bryas','92400','COURBEVOIE','1234', 0, 0,                        date_effet - INTERVAL 6 MONTH + INTERVAL 3 DAY),
    (7,'Alexandre','Morel',     'a.lexandre@gougleu.com',      '0783484403','56, rue des Soeurs','78012', 'LA CELLE-SAINT-CLOUD','Sed', 1, 0,                            date_effet - INTERVAL 5 MONTH + INTERVAL 8 DAY), # formateur - 7
    (8,'Montoya','Lester',      'm.lester@amasone.com',        '0182954239','73, rue du Château','78100','SAINT-GERMAIN-EN-LAYE','lorem', 0, 0,                 date_effet - INTERVAL 4 MONTH + INTERVAL 4 DAY),
    (9,'Figueroa','Christian',  'f.christian@gmale.fr',        '0114629593','90, rue du Président Roosevelt','91600','SAVIGNY-SUR-ORGE ','dictum', 0, 0,        date_effet - INTERVAL 4 MONTH + INTERVAL 2 DAY),
    (10,'Marwane','Roche',      'm.roche@gmale.com',           '0128645819','71, rue La Boétie','75015','PARIS','risus', 0, 0,                                  date_effet - INTERVAL 4 MONTH + INTERVAL 2 DAY),
    (11,'Justice','Charles',    'j.charles@freelance.edu',     '0131323259','35, rue Lenotre','93240','STAINS','pede', 0, 0,                                    date_effet - INTERVAL 4 MONTH + INTERVAL 1 DAY), # formateur - 11
    (12,'Nicolas','Colin',      'n.colin@formation.net',       '0133847860','33, rue de Groussay','93230','ROMAINVILLE','venenatis', 0, 1,                      date_effet - INTERVAL 3 MONTH + INTERVAL 1 DAY),
    (13,'Didier','Vasseur',     'd.vasseur@classprepa.edu',    '0771775852','18, rue Victor Hugo','91940','LES ULIS','risus', 1, 0,                             date_effet - INTERVAL 2 MONTH + INTERVAL 3 DAY),
    (14,'Stephanie','Platano',  's.platano@proboite.net',      '0642156271','88, rue du Fossé des Tanneurs','75008','PARIS','sed', 1, 1,                        date_effet - INTERVAL 2 MONTH + INTERVAL 4 DAY), # formateur & admin - 14
    (15,'Brigitte','Masson',    'b.masson@gougleu.com',        '0103946453','15, rue du Clair Bocage','75013','PARIS','ligula', 0, 0,                           date_effet - INTERVAL 2 MONTH + INTERVAL 2 DAY),
    (16,'Othmane','Fernandez',  'o.fernandez@gmale.net',       '0742542658','23, rue des six frères Ruellan','75018','PARIS','nisl', 0, 0,                      date_effet - INTERVAL 2 MONTH + INTERVAL 1 DAY),
    (17,'Samuel','Tisserand', 's.tisserand@formation.net',     '0645507077','86, Place Napoléon','59130','LAMBERSART','passwordsecured', 0, 1,                  date_effet - INTERVAL 1 MONTH); # administrateur
    
    INSERT INTO echange(id_echange, id_personne, instant, texte) VALUES
    (1, 15, date_effet - INTERVAL 2 YEAR + INTERVAL 5 MONTH, 'Demande d''information concernant la formation'),
    (2, 15, date_effet - INTERVAL 2 YEAR + INTERVAL 7 MONTH, 'Demande d''inscription'),
    (3, 15, date_effet - INTERVAL 2 YEAR + INTERVAL 7 MONTH, 'Confirmation de l''invitation a la journée d''information'),
    (4, 16, date_effet - INTERVAL 2 YEAR + INTERVAL 7 MONTH + INTERVAL 1 DAY, 'Contact 1'),
    (5, 16, date_effet - INTERVAL 2 YEAR + INTERVAL 7 MONTH + INTERVAL 3 DAY, 'Contact 2'),
    (6, 16, date_effet - INTERVAL 2 YEAR + INTERVAL 7 MONTH + INTERVAL 5 DAY, 'Contact 3'),
    (7, 17, date_effet - INTERVAL 2 YEAR + INTERVAL 8 MONTH, 'Contact 4'),
    (8, 17, date_effet - INTERVAL 2 YEAR + INTERVAL 8 MONTH + INTERVAL 1 DAY, 'Contact 5');
   
    INSERT INTO invitation(id_personne, id_evenement) VALUES
    (3, 1),
    (8, 1),
    (13, 2),
    (16, 2);

    INSERT INTO qcm(id_qcm, date_creation, titre, id_formateur) VALUES
    (1, date_effet, 'Les pratiques utils', 7),
    (2, date_effet, 'Les questions sans questionnements', 7),
    (3, date_effet, 'Pratiques de la véracités des propos', 2),
    (4, date_effet, 'Test de langue parlementaire', 2);

    INSERT INTO session_formation(id_session_formation, id_formation, date_debut, date_fin) VALUES
    (1, 1, date_effet - INTERVAL 2 YEAR, date_effet - INTERVAL 1 YEAR), -- passé
    (2, 2, date_effet - INTERVAL 8 MONTH, date_effet + INTERVAL 6 MONTH), -- en cours
    (3, 1, date_effet - INTERVAL 2 MONTH, date_effet + INTERVAL 1 YEAR), -- en cours
    (4, 2, date_effet + INTERVAL 6 MONTH, date_effet + INTERVAL 2 YEAR); -- ouverte a candidature
    
    INSERT INTO avis(id_personne, id_qcm, commentaire, note, date_avis) VALUES
    (2,  1, 'Commentaire inutile', 6,  date_effet - INTERVAL 2 YEAR - INTERVAL 5 MONTH),
    (1,  3, 'Commentaire inutile', 12, date_effet - INTERVAL 2 YEAR - INTERVAL 4 MONTH),
    (16, 2, 'Commentaire inutile', 3,  date_effet - INTERVAL 1 YEAR - INTERVAL 2 MONTH),
    (8,  4, 'Commentaire inutile', 16, date_effet - INTERVAL 1 YEAR - INTERVAL 2 MONTH),
    (12, 1, 'Commentaire inutile', 7,  date_effet - INTERVAL 1 YEAR - INTERVAL 1 MONTH),
    (10, 2, 'Commentaire inutile', 8,  date_effet - INTERVAL 6 MONTH - INTERVAL 2 DAY),
    (6,  4, 'Commentaire inutile', 10, date_effet - INTERVAL 4 MONTH - INTERVAL 1 DAY);

    INSERT INTO candidature(id_personne, id_session_formation, id_etat_candidature, date_effet) VALUES
    (4, 1, 6, date_effet - INTERVAL 2 YEAR - INTERVAL 2 MONTH + INTERVAL 12 DAY),
    (5, 1, 6, date_effet - INTERVAL 2 YEAR - INTERVAL 2 MONTH + INTERVAL 19 DAY),
    (6, 2, 6, date_effet - INTERVAL 2 YEAR - INTERVAL 2 MONTH + INTERVAL 23 DAY),
    (8, 2, 6, date_effet - INTERVAL 2 YEAR - INTERVAL 2 MONTH + INTERVAL 28 DAY),
    (9, 2, 6, date_effet - INTERVAL 8 MONTH - INTERVAL 2 MONTH + INTERVAL 2 DAY),
    (10, 2, 6, date_effet - INTERVAL 8 MONTH - INTERVAL 2 MONTH + INTERVAL 8 DAY),
    (12, 2, 3, date_effet - INTERVAL 8 MONTH - INTERVAL 2 MONTH + INTERVAL 23 DAY),
    (13, 3, 6, date_effet - INTERVAL 2 MONTH - INTERVAL 2 MONTH + INTERVAL 9 DAY),
    (15, 3, 6, date_effet - INTERVAL 2 MONTH - INTERVAL 2 MONTH + INTERVAL 19 DAY),
    (16, 3, 7, date_effet - INTERVAL 2 MONTH - INTERVAL 2 MONTH + INTERVAL 23 DAY),
    (17, 3, 3, date_effet - INTERVAL 2 MONTH - INTERVAL 2 MONTH + INTERVAL 28 DAY);

    INSERT INTO evaluation(id_evaluation, id_session_formation, id_module, id_formateur, date_debut, nb_minutes, titre) VALUES
    (1, 1, 1, 2, date_effet - INTERVAL 1 YEAR - INTERVAL 4 MONTH, 120, 'Evaluation finale de mathématique'),
    (2, 1, 2, 2, date_effet - INTERVAL 1 YEAR - INTERVAL 4 MONTH, 240, 'Evaluation mise en place d''un serveur LAMP'),
    (3, 2, 2, 7, date_effet, 60, 'Evaluation réseau 2'),
    (4, 3, 2, 7, date_effet + INTERVAL 4 MONTH, 60, 'Evaluation réseau 3'),
    (5, 3, 1, 7, date_effet, 60, 'Math 2'),
    (6, 3, 1, 7, date_effet + INTERVAL 4 MONTH, 120, 'Math 3');

    INSERT INTO projet(id_projet, id_formateur, id_session_formation, titre, date_creation, date_butoir) VALUES
    (1, 2, 1, 'Maintient d''un site web', date_effet - INTERVAL 1 YEAR - INTERVAL 3 MONTH, date_effet - INTERVAL 1 YEAR - INTERVAL 3 MONTH),
    (2, 2, 1, 'Calcul de matrice', date_effet - INTERVAL 1 YEAR - INTERVAL 3 MONTH, date_effet - INTERVAL 1 YEAR - INTERVAL 3 MONTH),
    (3, 2, 1, 'Maintient d''un site web', date_effet - INTERVAL 1 YEAR - INTERVAL 3 MONTH, date_effet - INTERVAL 1 YEAR - INTERVAL 3 MONTH);
    
    INSERT INTO question(id_question, enonce, id_qcm) VALUES
    (1, 'Quel est la couleur du cheval blanc d''Henry IV ?', 1),
    (2, 'Quel est la couleur de l''oie blanche du Parc Venteux ?', 1),
    (3, 'Quel est la couleur de mon pull ?', 1),
    (4, 'Quel est la couleur de l''automobile du formateur ?', 2),
    (5, 'Quel est le résultat de 2+2 = ?', 2),
    (6, 'Quel est ma question ? (Plusieurs choix possibles)', 3),
    (7, 'Quelle est la bonne longueur pour sauter jusqu''à là ?', 3),
    (8, 'Combien de pommes pour une tarte ?', 4),
    (9, 'Quel est la commande permettant d''ajouter une valeur en base de donnée ?', 4),
    (10, 'Quelles sont les congruances pour 4 ?', 4),
    (11, 'Quel est la couleur de la chêvre ?', 4);

    INSERT INTO reponse_possible(id_reponse_possible, reponse, est_correcte, id_question) VALUES
    (1, 'bleu', 0, 1),
    (2, 'rouge', 0, 1),
    (3, 'vert', 0, 1),
    (4, 'blanc', 1, 1),

    (5, 'bleu', 0, 2),
    (6, 'rouge', 0, 2),
    (7, 'vert', 1, 2),
    (8, 'blanc', 0, 2),

    (9, 'bleu', 0, 3),
    (10, 'rouge', 1, 3),
    (11, 'vert', 0, 3),
    (12, 'blanc', 0, 3),

    (13, 'bleu', 1, 4),
    (14, 'rouge', 0, 4),
    (15, 'vert', 0, 4),
    (16, 'blanc', 0, 4),

    (17, '4', 1, 5),
    (18, '15', 0, 5),
    (19, '0', 0, 5),
    (20, '2', 0, 5),

    (21, 'question1', 0, 6),
    (22, 'question2', 0, 6),
    (23, 'question3', 0, 6),
    (24, 'question4', 1, 6),

    (25, '145', 0, 7),
    (26, '16', 0, 7),
    (27, '3', 0, 7),
    (28, '496', 1, 7),

    (29, '1', 0, 8),
    (30, '2', 0, 8),
    (31, '3', 1, 8),
    (32, '4', 0, 8);

    INSERT INTO passage_qcm(id_passage_qcm, date_passage, id_qcm, id_personne) VALUES
    (1, date_effet - INTERVAL 1 YEAR - INTERVAL 3 MONTH, 1, 4);

    INSERT INTO reponse_donnee(id_passage_qcm, id_reponse_possible) VALUES
    (1, 2),
    (1, 5),
    (1, 11);
  
    INSERT INTO seance(id_seance, id_session_formation, id_formateur, id_module, jour, creneau) VALUES
    (1, 1, 2, 1, date_effet - INTERVAL 2 YEAR + INTERVAL 4 MONTH, 1),
    (2, 2, 7, 1, date_effet - INTERVAL 2 YEAR + INTERVAL 4 MONTH, 2),
    (3, 3, 3, 2, date_effet - INTERVAL 2 YEAR + INTERVAL 4 MONTH + INTERVAL 1 DAY, 1),
    (4, 1, 11, 1, date_effet - INTERVAL 2 YEAR + INTERVAL 4 MONTH + INTERVAL 1 DAY, 2),
    (5, 2, 11, 3, date_effet - INTERVAL 2 YEAR + INTERVAL 4 MONTH, 1),
    (6, 3, 14, 4, date_effet - INTERVAL 2 YEAR + INTERVAL 4 MONTH, 2),
    (7, 1, 3, 3, date_effet - INTERVAL 1 YEAR, 2),
    (8, 1, 3, 4, date_effet - INTERVAL 1 YEAR, 4),
    (9, 2, 2, 2, date_effet, 2),
    (10, 3, 11, 6, date_effet + INTERVAL 3 MONTH, 4),
    (11, 3, 7, 5, date_effet, 2),
    (12, 3, 7, 5, date_effet + INTERVAL 3 MONTH + INTERVAL 1 DAY, 1);
  
    INSERT INTO equipe(id_equipe, id_projet, id_createur) VALUES
    (1, 1, 4),
    (2, 1, 5),
    (3, 1, 6),
    (4, 2, 4),
    (5, 2, 6);

    INSERT INTO note(id_personne, id_evaluation, note) VALUES
    (4, 1, 16.5),
    (5, 1, 2.50),
    (4, 2, 12),
    (5, 2, 18),
    (6, 3, 14.50),
    (8, 3, 1.50),
    (9, 3, 12.50),
    (10, 3, 12.50),
    (13, 4, 14),
    (10, 4, 14.50),
    (15, 4, 14.50),
    (16, 4, 16),
    (13, 5, 12),
    (15, 5, 14),
    (16, 5, 15),
    (13, 6, 12),
    (10, 6, 16),
    (15, 6, 14),
    (16, 6, 0);
 

    -- La table presence est alimentee par un déclencheur sur seance, qui gere des lignes
    -- de valeur est-present à NULL.
    -- Nous positionnons ainsi les valeurs par des UPDATE
    UPDATE presence SET est_present=1
    WHERE (id_seance, id_personne) IN
    (
        (1, 4), (1, 8), (1,10), (1, 12),
        (2, 6), (2, 4), (2, 10), (2, 10),
        (3, 5), (3, 15), (3, 16),
        (4, 9), (4, 4), (4, 6),
        (5, 6), (5, 5), (5,8), (5, 9), (5, 13),
        (6, 5), (6, 15),
        (7, 9), (7, 5),
        (8, 9), (8, 5)
    );
    UPDATE presence SET est_present=0
    WHERE (id_seance, id_personne) IN
    (
        (1, 5),
        (2, 8), (2, 9),
        (3, 13),
        (4, 8), (4, 2), (4, 5),
        (5, 10),
        (6, 13), (6, 16),
        (7, 1), (6, 2), (6, 3), (6, 4), (6, 5),
        (8, 1), (8, 2)
    );

END$$

CALL agriotes2019_reset(now())$$