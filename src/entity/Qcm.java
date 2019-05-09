package entity;

public class Qcm {
	private int id;
	private String titre;
	
	public Qcm(int id, String titre) {
		this.id = id;
		this.titre = titre;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
}
