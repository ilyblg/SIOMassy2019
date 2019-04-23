package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import entity.PassageQcm;

public class PassageQcmDao {

	private static final String SELECT = "SELECT * FROM passage_qcm WHERE id_passage_qcm=?";
	private static final String INSERT = "INSERT INTO passage_qcm(date_passage, id_qcm, id_personne)"
			+ " VALUES(?, ?, ?)";

	public PassageQcm getById(int id) throws SQLException {
		PassageQcm result = null;

		Connection connection = Database.getConnection();
		PreparedStatement ps = connection.prepareStatement(SELECT);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			result = new PassageQcm(id, rs.getTimestamp("date_passage").toLocalDateTime(), rs.getInt("id_qcm"),
					rs.getInt("id_personne"));
		}
		return result;
	}

	public void insert(PassageQcm passage) throws SQLException {
		Connection connection = Database.getConnection();
		PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
		ps.setTimestamp(1, Timestamp.valueOf(passage.getDatePassage()));
		ps.setInt(2, passage.getIdQcm());
		ps.setInt(3, passage.getIdPersonne());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		passage.setId(rs.getInt(1));
		System.out.println("passageQcm bien ajouté");
	}
}