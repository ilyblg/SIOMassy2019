package entity;

public class Contact extends Personne {
	int numero;
	String rue;
	String ville;
	int cp;
	String telephoneMobile;
	String telephoneFixe;
	String mail;
	
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

	public Contact() {
		super();
	}

	public Contact(int numero, String rue, String ville, int cp, String telephoneMobile, String telephoneFixe, String mail) {
		super();
		this.numero = numero;
		this.rue = rue;
		this.ville = ville;
		this.cp = cp;
		this.telephoneMobile = telephoneMobile;
		this.telephoneFixe = telephoneFixe;
		this.mail = mail;
	}
}
