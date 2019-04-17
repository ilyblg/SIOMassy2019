package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Evaluation;

public class DaoEvaluation {

	public DaoEvaluation() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Recupère le titre d'une évaluation par son id (ici à REVOIR, RENVOIE OBJET)
	 */
	public Evaluation getTitleEvaluationById(int id) throws SQLException {
		Evaluation result = null;
		Statement stmt = Database.getConnection().createStatement();
		String sql = "SELECT * FROM evaluation WHERE id_evaluation=" + id;
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			result = new Evaluation(null, null, null, null, null, null, rs.getString("titre"));
		}
		return result;
	}
}
