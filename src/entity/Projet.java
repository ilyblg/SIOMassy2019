package entity;

public class Projet {
	int idProjet;
	int idFormateur;
	int idSession;
	String titre;
	String dateDebut;
	String dateButoire;
	
	public int getIdProjet() {
		return idProjet;
	}
	public void setIdProjet(int idProjet) {
		this.idProjet = idProjet;
	}
	public int getIdFormateur() {
		return idFormateur;
	}
	public void setIdFormateur(int idFormateur) {
		this.idFormateur = idFormateur;
	}
	public int getIdSession() {
		return idSession;
	}
	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	public String getDateButoire() {
		return dateButoire;
	}
	public void setDateButoire(String dateButoire) {
		this.dateButoire = dateButoire;
	}
	public Projet(int idProjet, int idFormateur, int idSession, String titre, String dateDebut, String dateButoire) {
		super();
		this.idProjet = idProjet;
		this.idFormateur = idFormateur;
		this.idSession = idSession;
		this.titre = titre;
		this.dateDebut = dateDebut;
		this.dateButoire = dateButoire;
	}
	public Projet(int idProjet, String titre) {
		super();
		this.idProjet = idProjet;
		this.titre = titre;
	}
	
	

}
