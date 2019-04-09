package dao;

import java.sql.SQLException;

public interface DaoPersonne {
	public void getByNom(String nom);
	public void getByPrenom(String prenom);
	public boolean estFormateur(int idPersonne) throws SQLException;
}
