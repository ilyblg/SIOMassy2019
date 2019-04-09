package entity;

import dao.DaoPersonne;

public class Personne {
	String nom;
	String prenom;
	Contact contact;
	Session session;
	Fonction fonction;
	DaoPersonne dao;
	int id;
	
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
	
	public Contact getContact() {
		return contact;
	}
	
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public Session getSession() {
		return session;
	}
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	public Fonction getFonction() {
		return fonction;
	}
	
	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}
	
	public Personne() {
		
	}
	
	public Personne(String nom, String prenom, int numero, String rue, String ville, int cp, String telephoneMobile, String telephoneFixe, String mail, String login, String mdp, int id, String nomF) {
		this.nom = nom;
		this.prenom = prenom;
		
		contact = new Contact();
		contact.setNumero(numero);
		contact.setRue(rue);
		contact.setVille(ville);
		contact.setCp(cp);
		contact.setTelephoneMobile(telephoneMobile);
		contact.setTelephoneFixe(telephoneFixe);
		contact.setMail(mail);
		
		session = new Session();
		session.setLogin(login);
		session.setMdp(mdp);
		
		fonction = new Fonction();
		fonction.setId(id);
		fonction.setNom(nomF);
	}

	public void creeQcm() {
		if (dao.estFormateur(id)) {
			
		}
	}
	
}
