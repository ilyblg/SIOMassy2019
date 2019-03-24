package entity;

import java.sql.Timestamp;

public class Personne {
	String nom;
	String prenom;
	String adresse;
	int cp;
	String ville;
	String telephone;
	String mail;
	String password;
	boolean estFormateur;
	boolean estAdmin;
	Timestamp dateInscription;

	Session session;	
	
	// Sans isFormateur et isAdmin
	public Personne(String nom, String prenom, String adresse,
			 int cp, String ville, String telephone, String mail, String password, Timestamp dateInscrip) {		
		setNom(nom);
		setPrenom(prenom);
		setAdresse(adresse);
		setCp(cp);
		setVille(ville);
		setTelephone(telephone);
		setMail(mail);
		setPassword(password);
		setDateInscription(dateInscrip);
	}
	
	// Full parameters
	public Personne(String nom, String prenom, String adresse,
			 int cp, String ville, String telephone, String mail, String password,
			 boolean estFormateur, boolean estAdmin, Timestamp dateInscrip) {		
		setNom(nom);
		setPrenom(prenom);
		setAdresse(adresse);
		setCp(cp);
		setVille(ville);
		setTelephone(telephone);
		setMail(mail);
		setPassword(password);
		setEstFormateur(estFormateur);
		setEstAdmin(estAdmin);
		setDateInscription(dateInscrip);
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

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		
		// CRYPTER ICI
		this.password = password;
	}

	public boolean isEstFormateur() {
		return estFormateur;
	}

	public void setEstFormateur(boolean estFormateur) {
		this.estFormateur = estFormateur;
	}

	public boolean isEstAdmin() {
		return estAdmin;
	}

	public void setEstAdmin(boolean estAdmin) {
		this.estAdmin = estAdmin;
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
		return "Personne [nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", cp=" + cp + ", ville="
				+ ville + ", telephone=" + telephone + ", mail=" + mail + ", estFormateur=" + estFormateur
				+ ", estAdmin=" + estAdmin + ", dateInscription=" + dateInscription + "]";
	}
}
