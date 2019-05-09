package entity;

public class ReponsePossible {
	private int id;
	private String reponse;
	private boolean estCorrect;

	public ReponsePossible(String reponse, boolean estCorrect) {
		this.reponse = reponse;
		this.estCorrect = estCorrect;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getReponse() {
		return reponse;
	}
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	public boolean isEstCorrect() {
		return estCorrect;
	}
	public void setEstCorrect(boolean estCorrect) {
		this.estCorrect = estCorrect;
	}
}
