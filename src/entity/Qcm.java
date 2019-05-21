package entity;

import java.time.LocalDateTime;

public class Qcm {
	private int id;
	private LocalDateTime dateQcm;
	private String titre;
	private int idFormateur;
	
	public Qcm(int id, String titre) {
		this.id = id;
		this.titre = titre;
	}
	
	public Qcm(int id, String titre, int idFormateur) {
		this.id = id;
		this.titre = titre;
		this.idFormateur = idFormateur;
	}
	
	public Qcm(LocalDateTime dateQcm, String titre, int idFormateur) {
		this.dateQcm = dateQcm;
		this.titre = titre;
		this.idFormateur = idFormateur;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public int getIdFormateur() {
		return idFormateur;
	}
	public void setIdFormateur(int idFormateur) {
		this.idFormateur = idFormateur;
	}
	public LocalDateTime getDateQcm() {
		return dateQcm;
	}
	public void setDateQcm(LocalDateTime dateQcm) {
		this.dateQcm = dateQcm;
	}
}
