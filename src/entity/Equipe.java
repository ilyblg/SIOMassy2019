package entity;

public class Equipe {
	private int idEquipe;
	private int idProjet;
	private int idCreateur;



	public Equipe() {
		
	}


	public int getIdEquipe() {
		return idEquipe;
	}


	public void setIdEquipe(int idEquipe) {
		this.idEquipe = idEquipe;
	}


	public int getIdCreateur() {
		return idCreateur;
	}


	public void setIdCreateur(int idCreateur) {
		this.idCreateur = idCreateur;
	}
	public int getIdProjet() {
		return idProjet;
	}


	public void setIdProjet(int idProjet) {
		this.idProjet = idProjet;
	}

	public Equipe(int idEquipe, int idProjet, int idCreateur) {
		super();
		this.idEquipe = idEquipe;
		this.idProjet = idProjet;
		this.idCreateur = idCreateur;
	}


	@Override
	public String toString() {
		return "Equipe [idEquipe=" + idEquipe + ", idProjet=" + idProjet + ", idCreateur=" + idCreateur + "]";
	}


	public void setId(int int1, int int2, int int3) {
		// TODO Auto-generated method stub
		
	}


	
	
	

}
