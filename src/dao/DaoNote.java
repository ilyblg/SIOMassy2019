package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class DaoNote {

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
}
