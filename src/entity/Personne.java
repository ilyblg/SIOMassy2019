package entity;

import java.sql.Timestamp;

public class Personne {
	int id;
	String nom;
	String prenom;
	String adresse;
	String codePostal;
	String ville;
	String telephone;
	String mail;
	String motDePasse;
	boolean estFormateur;
	boolean estAdministration;
	Timestamp dateInscription;

	Session session;	
	
	// Full parameters
	public Personne(int idPersonne, String nom, String prenom,
			 String mail, String telephone, String adresse, String cp, String ville,
			 String motDePasse, boolean estFormateur, boolean estAdmin, Timestamp dateInscription) {	
		this.id = idPersonne;
		setNom(nom);
		setPrenom(prenom);
		setAdresse(adresse);
		setCodePostal(cp);
		setVille(ville);
		setTelephone(telephone);
		setMail(mail);
		setMotDePasse(motDePasse);
		setEstFormateur(estFormateur);
		setEstAdministration(estAdmin);
		setDateInscription(dateInscription);
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
	
	
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String cp) {
		this.codePostal = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		
		// CRYPTER ICI
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

	public void setEstAdministration(boolean estAdmin) {
		this.estAdministration = estAdmin;
	}

	public Session getSession() {
		return session;
	}
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	public Timestamp getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(Timestamp dateInscription) {
		this.dateInscription = dateInscription;
	}	

	public Personne() {
		
	}

	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", codePostal=" + codePostal + ", ville="
				+ ville + ", telephone=" + telephone + ", mail=" + mail + ", estFormateur=" + estFormateur
				+ ", estAdministration=" + estAdministration + ", dateInscription=" + dateInscription + "]";
	}
}
