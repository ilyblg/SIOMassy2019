package dao;

import entity.Personne;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.Database;

public class DaoPersonne {

	/**
	 * Fournit la Personne par son ID
	 * 
	 * @param id
	 * @return Object Personne
	 * @throws SQLException
	 */
	public Personne getById(int id) throws SQLException {
		Personne result = null;
		Statement stmt = Database.getConnection().createStatement();
		String sql = "SELECT * FROM personne WHERE id_personne=" + id;
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			result = new Personne(rs.getString("nom"), rs.getString("prenom"), rs.getString("mail"),
					rs.getString("telephone"), rs.getString("adresse"), rs.getString("code_postal"), rs.getString("ville"),
					rs.getString("mot_de_passe"), rs.getBoolean("est_formateur"), rs.getBoolean("est_administration"),
					rs.getTimestamp("date_inscription"));
		}
		return result;
	}

	/**
	 * Permet d'insérer en base de données une nouvelles personne
	 * 
	 * @param personne
	 * @return
	 * @throws SQLException
	 */
	public boolean inserer(Personne personne) throws SQLException {
		boolean result = false;
		// ECRYPTER LE PASS
		String cryptedPassword = personne.getPassword();

		Statement stmt = Database.getConnection().createStatement();
		String sql = "INSERT INTO personne(prenom, nom, mail, tel, adresse, code_postal,"
				+ "ville, mot_de_passe, est_formateur, est_administration, date_inscription)" + "VALUES (\""
				+ personne.getPrenom() + "\", \"" + personne.getNom() + "\", \"" + personne.getMail() + "\"," + " \""
				+ personne.getTelephone() + "\",\"" + personne.getAdresse() + "\",\"" + personne.getCp() + "\", " + "\""
				+ personne.getVille() + "\",\"" + cryptedPassword + "\", " + personne.isEstFormateur() + "," + ""
				+ personne.isEstAdmin() + ", \"" + personne.getDateInscription() + "\")";

		// Renvoie le nombre de ligne affectées, si 1 alors insertion réalisée
		if (stmt.executeUpdate(sql) > 0) {
			result = true;
		}
		return result;
	}
}
