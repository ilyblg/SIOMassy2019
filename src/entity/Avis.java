package entity;

import java.sql.Date;
import java.time.LocalDateTime;

public class Avis<LocaDateTime> {
	private int idPersonne;
	private int idQcm;
	private String commentaire;
	private int note;
	private LocalDateTime dateAvis;

	public Avis(int idPersonne, int idQcm, String commentaire, int note, LocalDateTime dateAvis) {
		super();
		this.idPersonne = idPersonne;
		this.idQcm = idQcm;
		this.commentaire = commentaire;
		this.note = note;
		this.dateAvis = dateAvis;
	}

	public int getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(int id) {
		this.idPersonne = id;
	}

	public int getIdQcm() {
		return idQcm;
	}

	public void setIdQcm(int idQcm) {
		this.idQcm = idQcm;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public LocalDateTime getDateAvis() {
		return dateAvis;
	}

	public void setDateAvis(LocalDateTime dateInscription) {
		this.dateAvis = dateAvis;
	}

}
