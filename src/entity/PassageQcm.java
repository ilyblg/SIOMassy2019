package entity;

import java.time.LocalDateTime;

public class PassageQcm {
	private int id;
	private LocalDateTime datePassage;
	private int idQcm;
	private int idPersonne;

	public PassageQcm(int id, LocalDateTime datePassage, int idQcm, int idPersonne) {
		this.id = id;
		this.datePassage = datePassage;
		this.idQcm = idQcm;
		this.idPersonne = idPersonne;
	}
	
	public PassageQcm(int idQcm, LocalDateTime datePassage,  int idPersonne) {
		this.idQcm = idQcm;
		this.datePassage = datePassage;
		this.idPersonne = idPersonne;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDatePassage() {
		return datePassage;
	}

	public void setDatePassage(LocalDateTime datePassage) {
		this.datePassage = datePassage;
	}

	public int getIdQcm() {
		return idQcm;
	}

	public void setIdQcm(int idQcm) {
		this.idQcm = idQcm;
	}

	public int getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}

	@Override
	public String toString() {
		return "PassageQcm [id=" + id + ", datePassage=" + datePassage + ", idQcm=" + idQcm + ", idPersonne="
				+ idPersonne + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datePassage == null) ? 0 : datePassage.hashCode());
		result = prime * result + id;
		result = prime * result + idPersonne;
		result = prime * result + idQcm;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PassageQcm other = (PassageQcm) obj;
		if (datePassage == null) {
			if (other.datePassage != null)
				return false;
		} else if (!datePassage.equals(other.datePassage))
			return false;
		if (id != other.id)
			return false;
		if (idPersonne != other.idPersonne)
			return false;
		if (idQcm != other.idQcm)
			return false;
		return true;
	}


}
