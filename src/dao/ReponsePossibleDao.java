package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.ReponsePossible;

public class ReponsePossibleDao {
	private static final String SELECT = "SELECT * FROM reponse_possible rp inner join reponse_donnee rd"
			+ " on rp.id_reponse_possible = rd.id_reponse_possible "
			+ "WHERE rd.id_passage_qcm=?";
	private static final String INSERT = "INSERT INTO reponse_possible(reponse, est_correcte, id_question) VALUES(?, ?, ?), (?, ?, ?), (?, ?, ?), (?, ?, ?)";

	public void insert(List<ReponsePossible> reponsesPossibles) throws SQLException {
		Connection connection = Database.getConnection();
		PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
		
		int c = 0;
		for (ReponsePossible r : reponsesPossibles) {
			ps.setString(++c, r.getReponse());
			ps.setBoolean(++c, r.isEstCorrect());
			ps.setInt(++c, r.getIdQuestion());
		}
		ps.executeUpdate();
		
		/*
		ResultSet rs = ps.getGeneratedKeys();
		
		c = 0;
		while (rs.next()) {
			reponsesPossibles.get(c++).setId(rs.getInt(1));
		}
		*/
		
		System.out.println("Reponses possibles bien ajoutées");
	}

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
