package entity;

import java.util.List;

public class Question {
	private int id;
	private String enonce;
	private List<Reponse> reponsesPossibles;
	private int idQcm;
	
	public Question(int id, String enonce) {
		this.id = id;
		this.enonce = enonce;
	}

	public Question(int id, String enonce, int idQcm) {
		this.id = id;
		this.enonce = enonce;
		this.setIdQcm(idQcm);
	}
	
	public Question(int id, String enonce, List<Reponse> reponsesPossibles) {
		this.id = id;
		this.enonce = enonce;
		this.reponsesPossibles = reponsesPossibles;
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

	public List<Reponse> getReponsesPossibles() {
		return reponsesPossibles;
	}
	public void setReponsesPossibles(List<Reponse> reponsesPossibles) {
		this.reponsesPossibles = reponsesPossibles;
	}

	public int getIdQcm() {
		return idQcm;
	}

	public void setIdQcm(int idQcm) {
		this.idQcm = idQcm;
	}
}
