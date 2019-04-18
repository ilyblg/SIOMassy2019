package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Candidature;

public class DaoSession {

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
	
	/**
	 * OU SONT VOS COMMENTAIRES ?
	 * @param idSession
	 * @return
	 * @throws SQLException
	 */
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
}
