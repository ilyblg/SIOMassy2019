package entity;

public class ReponseDonnee {
	private int idQuestion;
	private int idPassageQcm;
	private int idReponsePossible;
	
	public ReponseDonnee(int idQuestion, int idPassageQcm, int idReponsePossible) {
		this.idQuestion = idQuestion;
		this.idPassageQcm = idPassageQcm;
		this.idReponsePossible = idReponsePossible;
	}

	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public int getIdPassageQcm() {
		return idPassageQcm;
	}

	public void setIdPassageQcm(int idPassageQcm) {
		this.idPassageQcm = idPassageQcm;
	}

	public int getIdReponsePossible() {
		return idReponsePossible;
	}

	public void setIdReponsePossible(int idReponsePossible) {
		this.idReponsePossible = idReponsePossible;
	}
}
