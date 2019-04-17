package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Personne;

public class DaoFormateur extends DaoPersonne{
	
	/**
	 * Fournit la Personne Formateur par son ID
	 * Après vérification qu'il/elle est formateur
	 * 
	 * @param id
	 * @return Object Personne
	 * @throws SQLException
	 */
	@Override
	public Personne getById(int id) throws SQLException {
		Personne result = null;
		Statement stmt = Database.getConnection().createStatement();
		String sql = "SELECT * FROM personne WHERE id_personne=" + id;
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			if(rs.getBoolean("est_formateur")) {
				result = new Personne(-1, rs.getString("nom"), rs.getString("prenom"), rs.getString("mail"),
						rs.getString("telephone"), rs.getString("adresse"), rs.getString("code_postal"), rs.getString("ville"),
						rs.getString("mot_de_passe"), rs.getBoolean("est_formateur"), rs.getBoolean("est_administration"),
						rs.getTimestamp("date_inscription"));
			}
		}
		return result;
	}

	
	
}
