package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.ReponsePossible;

public class ReponsePossibleDao {
	private static final String SELECT = "SELECT * FROM reponse_possible rp inner join reponse_donnee rd"
			+ " on rp.id_reponse_possible = rd.id_reponse_possible "
			+ "WHERE rd.id_passage_qcm=?";

	public List<ReponsePossible> getReponsesPossiblesByIdPassageQcm(int idPassageQcm) throws SQLException {
		List<ReponsePossible> result = new ArrayList<>();
		
		Connection connection = Database.getConnection();
		PreparedStatement ps = connection.prepareStatement(SELECT);
		ps.setInt(1, idPassageQcm);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			result.add(new ReponsePossible(rs.getString("rp.reponse"), rs.getBoolean("rp.est_correcte")));
		}
		
		return result;
	}
}
