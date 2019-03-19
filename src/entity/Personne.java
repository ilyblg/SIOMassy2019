package entity;

public class Personne {
	String nom;
	String prenom;
	int numero;
	String rue;
	String ville;
	int cp;
	String telephoneMobile;
	String telephoneFixe;
	String mail;
	String mdp;
	
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
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String getRue() {
		return rue;
	}
	
	public void setRue(String rue) {
		this.rue = rue;
	}
	
	public String getVille() {
		return ville;
	}
	
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public int getCp() {
		return cp;
	}
	
	public void setCp(int cp) {
		this.cp = cp;
	}
	
	public String getTelephoneMobile() {
		return telephoneMobile;
	}
	
	public void setTelephoneMobile(String telephoneMobile) {
		this.telephoneMobile = telephoneMobile;
	}
	
	public String getTelephoneFixe() {
		return telephoneFixe;
	}
	
	public void setTelephoneFixe(String telephoneFixe) {
		this.telephoneFixe = telephoneFixe;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getMdp() {
		return mdp;
	}
	
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	public Personne() {
		
	}
	
	public Personne(String prenom, String mdp) {
		this.prenom = prenom;
		this.mdp = mdp;
	}

	public Personne(String nom, String prenom, int numero, String rue, String ville, int cp, String telephoneMobile, String telephoneFixe, String mail, String mdp) {
		this.nom = nom;
		this.prenom = prenom;
		this.numero = numero;
		this.rue = rue;
		this.ville = ville;
		this.cp = cp;
		this.telephoneMobile = telephoneMobile;
		this.telephoneFixe = telephoneFixe;
		this.mail = mail;
		this.mdp = mdp;
	}

	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", prenom=" + prenom + ", numero=" + numero + ", rue=" + rue + ", ville="
				+ ville + ", cp=" + cp + ", telephoneMobile=" + telephoneMobile + ", telephoneFixe=" + telephoneFixe
				+ ", mail=" + mail + ", mdp=" + mdp + "]";
	}
	
	
	

}
