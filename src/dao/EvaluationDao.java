package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.Evaluation;

public class EvaluationDao {
	
	/**
	 * Renvoie la liste de toutes les évaluation en cours pour un formateur donné
	 * @param id du formateur
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Evaluation> getListByIdFormateur(int idFormateur) throws SQLException {
		ArrayList<Evaluation> evaluationList = new ArrayList<>();
		Connection db = Database.getConnection();		
		final String sql = "SELECT * FROM evaluation WHERE id_formateur = ?";
		
		PreparedStatement stmt = db.prepareStatement(sql);
		stmt.setInt(1, idFormateur);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			evaluationList.add(new Evaluation( rs.getString("id_evaluation"), rs.getString("id_session_formation"),
													rs.getString("id_module"), rs.getString("id_formateur"),
													rs.getTimestamp("date_debut"), rs.getString("nb_minutes"), rs.getString("titre")) );
		}
		return evaluationList;
	}
}
