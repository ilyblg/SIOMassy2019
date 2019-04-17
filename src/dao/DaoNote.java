package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

public class DaoNote {

	/**
	 * 
	 * @param idEvaluation
	 * @return List<HashMap<String, Object>>
	 * @throws SQLException
	 */
	public List<HashMap<String, Object>> getNoteByIdEvaluation(int idEvaluation) throws SQLException {
		Connection db = Database.getConnection();
		String sql = 	" SELECT n.id_personne AS idPersonne, p.nom, p.prenom, n.note" + 
						" FROM note AS n" +
						" INNER JOIN personne AS p" + 
						" ON n.id_personne = p.id_personne" + 
						" WHERE n.id_evaluation = " + idEvaluation;
		PreparedStatement stmt = db.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(sql);
		return Database.getAsList(rs);
	}
	
	public boolean insertNoteStagiaire(String idPersonne, String idEvaluation, double note) throws SQLException {	
		/**
		 * Insère ou met à jour la nouvelle note pour l'élève seletionné par ID
		 * Sur une évaluation dont l'ID est fournis en TABLE "note".
		 * @param idPersonne, idEvaluation, note
		 * @return boolean
		 * @throws SQLException
		 */
		boolean result = false;
		Connection db = Database.getConnection();
		String sql = "UPDATE note SET note = ? WHERE id_personne = ? AND id_evaluation = ?";
		PreparedStatement stmt = db.prepareStatement(sql);
		stmt.setDouble(1, note);
		stmt.setString(2, idPersonne);
		stmt.setString(3, idEvaluation);
		/*
		String sql = "INSERT INTO note "
					+ " VALUES (SELECT id_personne FROM personne WHERE id_personne = ?),"
					+ " (SELECT id_evaluation FROM evaluation WHERE id_evaluation = ?), ?)"
					+ " ON DUPLICATE KEY UPDATE note = ?;";

		PreparedStatement stmt = db.prepareStatement(sql);
		stmt.setString(1, idPersonne);
		stmt.setString(2, idEvaluation);
		stmt.setDouble(3, note);
		stmt.setDouble(4, note);
		*/

		// Renvoie le nombre de ligne affectées, si 1 alors insertion réalisée
		if (stmt.executeUpdate() > 0) {
			result = true;
		}
		return result;
	}
}
