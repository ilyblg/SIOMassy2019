package entity;

import java.time.LocalDateTime;

public class Personne {
	private int id;
	private String nom;
	private String prenom;
	private String mail;
	private String tel;
	private String adresse;
	private String codePostal;
	private String ville;
	private String motDePasse;
	private boolean estFormateur;
	private boolean estAdministration;
	private LocalDateTime dateInscription;

	public Personne(int id, String nom, String prenom, String mail, String tel, String adresse, String codePostal,
			String ville, String motDePasse, boolean estFormateur, boolean estAdministration,
			LocalDateTime dateInscription) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.tel = tel;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.estFormateur = estFormateur;
		this.estAdministration = estAdministration;
		this.dateInscription = dateInscription;
	}	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}	

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}	

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public boolean isEstFormateur() {
		return estFormateur;
	}

	public void setEstFormateur(boolean estFormateur) {
		this.estFormateur = estFormateur;
	}

	public boolean isEstAdministration() {
		return estAdministration;
	}

	public void setEstAdministration(boolean estAdministration) {
		this.estAdministration = estAdministration;
	}

	public LocalDateTime getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(LocalDateTime dateInscription) {
		this.dateInscription = dateInscription;
	}
	

}
