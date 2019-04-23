package entity;

public class Candidature {
	private int idPersonne;
	private String prenom, nom, mail;
	private int idSession, idEtatCandidature;
	private String etat;

	public Candidature(int idPersonne, String prenom, String nom, String mail,
			int idSession, int idEtatCandidature, String etat) {
		this.idPersonne = idPersonne;
		this.prenom = prenom;
		this.nom = nom;
		this.mail = mail;
		this.idSession = idSession;
		this.idEtatCandidature = idEtatCandidature;
		this.etat = etat;
	}

	public int getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getIdSession() {
		return idSession;
	}

	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}

	public int getIdEtatCandidature() {
		return idEtatCandidature;
	}

	public void setIdEtatCandidature(int idEtatCandidature) {
		this.idEtatCandidature = idEtatCandidature;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

}
