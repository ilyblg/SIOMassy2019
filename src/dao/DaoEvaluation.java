package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Evaluation;

public class DaoEvaluation {

	public DaoEvaluation() {
		// TODO Auto-generated constructor stub
	}
	
	public Evaluation getEvaluationById(int id) throws SQLException {
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
