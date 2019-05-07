package entity;

public class Stagiaire {
	private int idPersonne;
	private String nom;
	private String prenom;
	
	
	public Stagiaire(int idPersonne, String nom, String prenom) {
		super();
		this.idPersonne = idPersonne;
		this.nom = nom;
		this.prenom = prenom;
	}


	public Stagiaire() {
		
	}


	public int getIdPersonne() {
		return idPersonne;
	}


	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
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


	@Override
	public String toString() {
		return "Stagiaire [idPersonne=" + idPersonne + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	
	

}
