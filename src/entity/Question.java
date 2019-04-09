package entity;

public class Question {
	private int id;
	private String enonce;
	
	public Question(int id, String enonce) {
		this.id = id;
		this.enonce = enonce;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getEnonce() {
		return enonce;
	}
	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}
}
