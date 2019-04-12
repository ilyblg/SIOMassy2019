package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import dao.Database;

public class Evaluation {
	String id_evaluation;
	String id_session_formation;
	String id_module;
	String id_formateur;
	Timestamp date_debut;
	String nbr_minutes;
	String titre;
	
	// Constructor
	public Evaluation() {
	}

	// Constructor
	public Evaluation(String idEval, String isSessionForm, String idModule, String idFormateur, Timestamp dateDebut, String nbrMinutes, String titre) {
		this.id_evaluation = idEval;
		this.id_session_formation = isSessionForm;
		this.id_module = idModule;
		this.id_formateur = idFormateur;
		this.date_debut = dateDebut;
		this.nbr_minutes = nbrMinutes;
		this.titre = titre;
	}

	public String getId_evaluation() {
		return id_evaluation;
	}

	public void setId_evaluation(String id_evaluation) {
		this.id_evaluation = id_evaluation;
	}

	public String getId_session_formation() {
		return id_session_formation;
	}

	public void setId_session_formation(String id_session_formation) {
		this.id_session_formation = id_session_formation;
	}

	public String getId_module() {
		return id_module;
	}

	public void setId_module(String id_module) {
		this.id_module = id_module;
	}

	public String getId_formateur() {
		return id_formateur;
	}

	public void setId_formateur(String id_formateur) {
		this.id_formateur = id_formateur;
	}

	public Timestamp getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Timestamp date_debut) {
		this.date_debut = date_debut;
	}

	public String getNbr_minutes() {
		return nbr_minutes;
	}

	public void setNbr_minutes(String nbr_minutes) {
		this.nbr_minutes = nbr_minutes;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
}
