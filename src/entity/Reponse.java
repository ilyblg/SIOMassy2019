package entity;

public class Reponse {
	private int id;
	private String reponse;

	public Reponse(int id, String reponse) {
		this.id = id;
		this.reponse = reponse;
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
}
