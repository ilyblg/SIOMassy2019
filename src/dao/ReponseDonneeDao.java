package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import entity.ReponseDonnee;

public class ReponseDonneeDao {

	private static final String SELECT = "SELECT * FROM reponse_donee WHERE id_question=?";
	private static final String SELECTALL = "SELECT * FROM reponse_donnee";
	private static final String UPDATE = "update reponse_donnee set id_reponse_possible = ? where id_question = ? AND id_passage_qcm = ? ";


	public void insert(ReponseDonnee reponseDonnee) throws SQLException {
		Connection connection = Database.getConnection();
		PreparedStatement ps = connection.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, reponseDonnee.getIdReponsePossible());
		ps.setInt(2, reponseDonnee.getIdQuestion());
		ps.setInt(3, reponseDonnee.getIdPassageQcm());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		//System.out.println("reponse donnée bien ajouté");
	}
}
