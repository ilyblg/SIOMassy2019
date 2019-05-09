package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Candidature;

public class SessionDao {
	// Defini une seule fois => les concatenations aussi
	private static final String CANDIDATURES_PAR_SESSION =
			" SELECT " +
			"   p.id_personne, p.prenom, p.nom, p.mail, " +
			"   c.id_session_formation, c.id_etat_candidature, " +
			"   e.libelle AS etat " +
			" FROM " +
			"   candidature c " +
			"     INNER JOIN " +
			"   personne p ON c.id_personne = p.id_personne " +
			"     INNER JOIN " +
			"   etat_candidature e ON c.id_etat_candidature = e.id_etat_candidature " +
			" WHERE c.id_session_formation = ?";
	
	private static final String TOUTES_LES_SESSIONS = 
			"SELECT id_session_formation, concat(nom, ' ', MONTH(date_debut), '/', YEAR(date_debut)) AS nom"
			+ " FROM session_formation sf"
			+ "   INNER JOIN"
			+ " formation f ON sf.id_formation = f.id_formation"
			+ " ORDER BY date_debut DESC";
	
	public List<Candidature> getCandidatures(int idSession) throws SQLException {
		ArrayList<Candidature> result = new ArrayList<>();
		Connection connection = Database.getConnection();
		PreparedStatement ps = connection.prepareStatement(CANDIDATURES_PAR_SESSION);
		ps.setInt(1, idSession);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Candidature ligne = new Candidature(
					rs.getInt("id_personne"),
					rs.getString("prenom"),
					rs.getString("nom"),
					rs.getString("mail"),
					idSession,
					rs.getInt("id_etat_candidature"),
					rs.getString("etat"));
			result.add(ligne);
		}
		return result;
	}
	
	public Map<Integer, String> getToutesLesSessions() throws SQLException {
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		Connection connection = Database.getConnection();
		PreparedStatement ps = connection.prepareStatement(TOUTES_LES_SESSIONS);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			result.put(rs.getInt("id_session_formation"), rs.getString("nom"));
		}
		return result;
	}
}
