SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema agriotes2019
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `agriotes2019` ;

-- -----------------------------------------------------
-- Schema agriotes2019
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `agriotes2019` DEFAULT CHARACTER SET utf8 ;
USE `agriotes2019` ;

-- -----------------------------------------------------
-- Table `agriotes2019`.`personne`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`personne` (
  `id_personne` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  `prenom` VARCHAR(45) NOT NULL,
  `mail` VARCHAR(45) NOT NULL,
  `tel` VARCHAR(10) NOT NULL,
  `adresse` VARCHAR(45) NULL,
  `code_postal` INT NULL,
  `ville` VARCHAR(45) NULL,
  `mot_de_passe` VARCHAR(255) NOT NULL,
  `est_formateur` TINYINT(1) NOT NULL DEFAULT 0,
  `est_administration` TINYINT(1) NOT NULL DEFAULT 0,
  `date_inscription` DATETIME NOT NULL,
  PRIMARY KEY (`id_personne`),
  UNIQUE INDEX `id_personne_UNIQUE` (`id_personne` ASC),
  UNIQUE INDEX `mail_UNIQUE` (`mail` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`formation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`formation` (
  `id_formation` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  PRIMARY KEY (`id_formation`),
  UNIQUE INDEX `id_candidature_UNIQUE` (`id_formation` ASC),
  UNIQUE INDEX `nom_UNIQUE` (`nom` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`session_formation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`session_formation` (
  `id_session_formation` INT NOT NULL AUTO_INCREMENT,
  `id_formation` INT NOT NULL,
  `date_debut` DATETIME NOT NULL,
  `date_fin` DATETIME NOT NULL,
  PRIMARY KEY (`id_session_formation`),
  UNIQUE INDEX `id_session_formation_UNIQUE` (`id_session_formation` ASC),
  INDEX `fk_session_formation_formation_idx` (`id_formation` ASC),
  CONSTRAINT `fk_session_formation_formation`
    FOREIGN KEY (`id_formation`)
    REFERENCES `agriotes2019`.`formation` (`id_formation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`etat_candidature`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`etat_candidature` (
  `id_etat_candidature` INT NOT NULL AUTO_INCREMENT,
  `libelle` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_etat_candidature`),
  UNIQUE INDEX `id_etat_candidature_UNIQUE` (`id_etat_candidature` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`candidature`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`candidature` (
  `id_personne` INT NOT NULL,
  `id_session_formation` INT NOT NULL,
  `id_etat_candidature` INT NOT NULL,
  `date_effet` DATE NULL,
  PRIMARY KEY (`id_personne`, `id_session_formation`),
  INDEX `fk_candidature_personne1_idx` (`id_personne` ASC),
  INDEX `fk_candidature_session_formation1_idx` (`id_session_formation` ASC),
  CONSTRAINT `fk_candidature_etat_candidature1`
    FOREIGN KEY (`id_etat_candidature`)
    REFERENCES `agriotes2019`.`etat_candidature` (`id_etat_candidature`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_candidature_personne1`
    FOREIGN KEY (`id_personne`)
    REFERENCES `agriotes2019`.`personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_candidature_session_formation1`
    FOREIGN KEY (`id_session_formation`)
    REFERENCES `agriotes2019`.`session_formation` (`id_session_formation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`module`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`module` (
  `id_module` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_module`),
  UNIQUE INDEX `nom_UNIQUE` (`nom` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`seance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`seance` (
  `id_seance` INT NOT NULL AUTO_INCREMENT,
  `id_session_formation` INT NOT NULL,
  `id_formateur` INT NOT NULL,
  `id_module` INT NOT NULL,
  `jour` DATE NOT NULL,
  `creneau` TINYINT NOT NULL COMMENT '1 pour le matin, 2 pour l\'après-midi',
  PRIMARY KEY (`id_seance`),
  INDEX `fk_seance_session_formation1_idx` (`id_session_formation` ASC),
  INDEX `fk_seance_module1_idx` (`id_module` ASC),
  UNIQUE INDEX `seance_unique_session_jour_creneau` (`id_session_formation` ASC, `jour` ASC, `creneau` ASC),
  UNIQUE INDEX `seance_unique_formateur_jour_creneau` (`id_formateur` ASC, `jour` ASC, `creneau` ASC),
  CONSTRAINT `fk_seance_session_formation1`
    FOREIGN KEY (`id_session_formation`)
    REFERENCES `agriotes2019`.`session_formation` (`id_session_formation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_seance_formateur1`
    FOREIGN KEY (`id_formateur`)
    REFERENCES `agriotes2019`.`personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_seance_module1`
    FOREIGN KEY (`id_module`)
    REFERENCES `agriotes2019`.`module` (`id_module`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`presence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`presence` (
  `id_seance` INT NOT NULL,
  `id_personne` INT NOT NULL,
  `est_present` TINYINT(1) NULL,
  PRIMARY KEY (`id_seance`, `id_personne`),
  INDEX `fk_candidature_has_seance_seance1_idx` (`id_seance` ASC),
  INDEX `fk_presence_personne1_idx` (`id_personne` ASC),
  CONSTRAINT `fk_candidature_has_seance_seance1`
    FOREIGN KEY (`id_seance`)
    REFERENCES `agriotes2019`.`seance` (`id_seance`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_presence_personne1`
    FOREIGN KEY (`id_personne`)
    REFERENCES `agriotes2019`.`personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`evaluation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`evaluation` (
  `id_evaluation` INT NOT NULL AUTO_INCREMENT,
  `id_session_formation` INT NOT NULL,
  `id_module` INT NOT NULL,
  `id_formateur` INT NOT NULL,
  `date_debut` DATETIME NOT NULL,
  `nb_minutes` INT NULL,
  `titre` VARCHAR(45) NULL,
  PRIMARY KEY (`id_evaluation`),
  INDEX `fk_evaluation_module1_idx` (`id_module` ASC),
  INDEX `fk_evaluation_session_formation1_idx` (`id_session_formation` ASC),
  INDEX `fk_evaluation_formateur_idx` (`id_formateur` ASC),
  CONSTRAINT `fk_evaluation_module`
    FOREIGN KEY (`id_module`)
    REFERENCES `agriotes2019`.`module` (`id_module`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_evaluation_formateur`
    FOREIGN KEY (`id_formateur`)
    REFERENCES `agriotes2019`.`personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_evaluation_session_formation`
    FOREIGN KEY (`id_session_formation`)
    REFERENCES `agriotes2019`.`session_formation` (`id_session_formation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`note`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`note` (
  `id_personne` INT NOT NULL,
  `id_evaluation` INT NOT NULL,
  `note` DECIMAL(3,1) NULL,
  PRIMARY KEY (`id_personne`, `id_evaluation`),
  INDEX `fk_candidature_has_evaluation_evaluation1_idx` (`id_evaluation` ASC),
  INDEX `fk_note_personne1_idx` (`id_personne` ASC),
  CONSTRAINT `fk_candidature_has_evaluation_evaluation`
    FOREIGN KEY (`id_evaluation`)
    REFERENCES `agriotes2019`.`evaluation` (`id_evaluation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_note_personne`
    FOREIGN KEY (`id_personne`)
    REFERENCES `agriotes2019`.`personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`evenement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`evenement` (
  `id_evenement` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  `date_effet` DATE NOT NULL,
  PRIMARY KEY (`id_evenement`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`echange`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`echange` (
  `id_echange` INT NOT NULL AUTO_INCREMENT,
  `id_personne` INT NOT NULL,
  `instant` DATETIME NOT NULL,
  `texte` TEXT NOT NULL,
  PRIMARY KEY (`id_echange`),
  INDEX `fk_echange_personne1_idx` (`id_personne` ASC),
  CONSTRAINT `fk_echange_personne1`
    FOREIGN KEY (`id_personne`)
    REFERENCES `agriotes2019`.`personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`invitation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`invitation` (
  `id_personne` INT NOT NULL,
  `id_evenement` INT NOT NULL,
  PRIMARY KEY (`id_personne`, `id_evenement`),
  INDEX `fk_personne_has_evenement_evenement1_idx` (`id_evenement` ASC),
  INDEX `fk_personne_has_evenement_personne1_idx` (`id_personne` ASC),
  CONSTRAINT `fk_personne_has_evenement_personne1`
    FOREIGN KEY (`id_personne`)
    REFERENCES `agriotes2019`.`personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personne_has_evenement_evenement1`
    FOREIGN KEY (`id_evenement`)
    REFERENCES `agriotes2019`.`evenement` (`id_evenement`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`projet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`projet` (
  `id_projet` INT NOT NULL AUTO_INCREMENT,
  `id_formateur` INT NOT NULL,
  `id_session_formation` INT NOT NULL,
  `titre` VARCHAR(45) NOT NULL,
  `date_creation` DATETIME NOT NULL,
  `date_butoir` DATETIME NOT NULL,
  PRIMARY KEY (`id_projet`),
  INDEX `fk_projet_session_formation1_idx` (`id_session_formation` ASC),
  INDEX `fk_projet_formateur_idx` (`id_formateur` ASC),
  CONSTRAINT `fk_projet_formateur`
    FOREIGN KEY (`id_formateur`)
    REFERENCES `agriotes2019`.`personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_projet_session_formation1`
    FOREIGN KEY (`id_session_formation`)
    REFERENCES `agriotes2019`.`session_formation` (`id_session_formation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`qcm`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`qcm` (
  `id_qcm` INT NOT NULL AUTO_INCREMENT,
  `date_creation` DATETIME NOT NULL,
  `titre` VARCHAR(45) NOT NULL,
  `id_formateur` INT NOT NULL,
  PRIMARY KEY (`id_qcm`),
  INDEX `fk_questionnaire_personne1_idx` (`id_formateur` ASC),
  CONSTRAINT `fk_questionnaire_personne1`
    FOREIGN KEY (`id_formateur`)
    REFERENCES `agriotes2019`.`personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`avis`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`avis` (
  `id_personne` INT NOT NULL,
  `id_qcm` INT NOT NULL,
  `commentaire` TEXT NULL,
  `note` TINYINT NOT NULL,
  `date_avis` DATETIME NOT NULL,
  PRIMARY KEY (`id_personne`, `id_qcm`),
  INDEX `fk_avis_questionnaire1_idx` (`id_qcm` ASC),
  CONSTRAINT `fk_avis_personne1`
    FOREIGN KEY (`id_personne`)
    REFERENCES `agriotes2019`.`personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_avis_questionnaire1`
    FOREIGN KEY (`id_qcm`)
    REFERENCES `agriotes2019`.`qcm` (`id_qcm`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`equipe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`equipe` (
  `id_equipe` INT NOT NULL AUTO_INCREMENT,
  `id_projet` INT NOT NULL,
  `id_createur` INT NOT NULL,
  PRIMARY KEY (`id_equipe`),
  INDEX `fk_equipe_projet1_idx` (`id_projet` ASC),
  INDEX `fk_equipe_personne1_idx` (`id_createur` ASC),
  CONSTRAINT `fk_equipe_projet1`
    FOREIGN KEY (`id_projet`)
    REFERENCES `agriotes2019`.`projet` (`id_projet`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_equipe_personne1`
    FOREIGN KEY (`id_createur`)
    REFERENCES `agriotes2019`.`personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`membre_equipe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`membre_equipe` (
  `id_personne` INT NOT NULL,
  `id_equipe` INT NOT NULL,
  PRIMARY KEY (`id_personne`, `id_equipe`),
  INDEX `fk_personne_has_equipe_equipe1_idx` (`id_equipe` ASC),
  INDEX `fk_personne_has_equipe_personne1_idx` (`id_personne` ASC),
  CONSTRAINT `fk_personne_has_equipe_personne1`
    FOREIGN KEY (`id_personne`)
    REFERENCES `agriotes2019`.`personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personne_has_equipe_equipe1`
    FOREIGN KEY (`id_equipe`)
    REFERENCES `agriotes2019`.`equipe` (`id_equipe`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`question` (
  `id_question` INT NOT NULL AUTO_INCREMENT,
  `enonce` VARCHAR(255) NOT NULL,
  `id_qcm` INT NOT NULL,
  PRIMARY KEY (`id_question`),
  INDEX `fk_question_questionnaire1_idx` (`id_qcm` ASC),
  CONSTRAINT `fk_question_questionnaire1`
    FOREIGN KEY (`id_qcm`)
    REFERENCES `agriotes2019`.`qcm` (`id_qcm`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`passage_qcm`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`passage_qcm` (
  `id_passage_qcm` INT NOT NULL AUTO_INCREMENT,
  `date_passage` DATETIME NOT NULL,
  `id_qcm` INT NOT NULL,
  `id_personne` INT NOT NULL,
  PRIMARY KEY (`id_passage_qcm`),
  INDEX `fk_passage_questionnaire_questionnaire1_idx` (`id_qcm` ASC),
  INDEX `fk_passage_questionnaire_personne1_idx` (`id_personne` ASC),
  CONSTRAINT `fk_passage_questionnaire_questionnaire1`
    FOREIGN KEY (`id_qcm`)
    REFERENCES `agriotes2019`.`qcm` (`id_qcm`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_passage_questionnaire_personne1`
    FOREIGN KEY (`id_personne`)
    REFERENCES `agriotes2019`.`personne` (`id_personne`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)question
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`reponse_possible`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`reponse_possible` (
  `id_reponse_possible` INT NOT NULL AUTO_INCREMENT,
  `reponse` VARCHAR(255) NOT NULL,
  `est_correcte` TINYINT(1) NOT NULL,
  `id_question` INT NOT NULL,
  PRIMARY KEY (`id_reponse_possible`),
  INDEX `fk_reponse_possible_question1_idx` (`id_question` ASC),
  CONSTRAINT `fk_reponse_possible_question`
    FOREIGN KEY (`id_question`)
    REFERENCES `agriotes2019`.`question` (`id_question`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agriotes2019`.`reponse_donnee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`reponse_donnee` (
  `id_passage_qcm` INT NOT NULL,
  `id_reponse_possible` INT NOT NULL,
  PRIMARY KEY (`id_passage_qcm`, `id_reponse_possible`),
  INDEX `fk_question_has_passage_questionnaire_passage_questionnaire_idx` (`id_passage_qcm` ASC),
  INDEX `fk_reponse_donnee_reponse_possible1_idx` (`id_reponse_possible` ASC),
  CONSTRAINT `fk_question_has_passage_questionnaire_passage_questionnaire1`
    FOREIGN KEY (`id_passage_qcm`)
    REFERENCES `agriotes2019`.`passage_qcm` (`id_passage_qcm`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reponse_donnee_reponse_possible1`
    FOREIGN KEY (`id_reponse_possible`)
    REFERENCES `agriotes2019`.`reponse_possible` (`id_reponse_possible`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `agriotes2019` ;

-- -----------------------------------------------------
-- Placeholder table for view `agriotes2019`.`stagiaire`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `agriotes2019`.`stagiaire` (`id_personne` INT, `nom` INT, `prenom` INT, `mail` INT, `tel` INT, `adresse` INT, `code_postal` INT, `ville` INT, `mot_de_passe` INT, `est_formateur` INT, `est_administration` INT, `date_inscription` INT, `id_session_formation` INT, `id_formation` INT, `date_debut` INT, `date_fin` INT);

-- -----------------------------------------------------
-- View `agriotes2019`.`stagiaire`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agriotes2019`.`stagiaire`;
USE `agriotes2019`;
CREATE  OR REPLACE VIEW stagiaire AS
SELECT p.*, sf.*
FROM 
  personne p
    INNER JOIN
  candidature c ON p.id_personne = c.id_personne
    INNER JOIN
  session_formation sf ON c.id_session_formation = sf.id_session_formation
WHERE 
  c.id_etat_candidature = 6;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;