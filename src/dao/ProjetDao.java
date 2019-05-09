package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;

import entity.Projet;

public class ProjetDao {

	public Projet getByIdProjet(int idProjet) throws SQLException {
		Projet result = null;
		Connection connection = Database.getConnection();
		Statement requete = connection.createStatement();
		String sql = "SELECT id_projet From projet WHERE id_projet = " + idProjet;
		ResultSet rs = requete.executeQuery(sql);
		if (rs.next()) {
			result = new Projet(idProjet, rs.getString("titre"));
		}
		return result;
	}

	public Projet getByTitre(String titre) throws SQLException {
		Projet result = null;
		Connection connection = Database.getConnection();
		Statement requete = connection.createStatement();
		// utiliser un preparedStatement
		String sql = "SELECT titre FROM projet WHERE titre = " + titre;
		ResultSet rs = requete.executeQuery(sql);
		if (rs.next()) {
			result = new Projet(rs.getInt("id_projet"), titre);
		}
		return result;
	}

	
	public Projet getByIdProjetTitre(int idProjet, String titre) throws SQLException {
		Projet result = null;
		Connection connection = Database.getConnection();
		String sql = "SELECT * FROM projet WHERE id_projet=? AND titre=?";
		PreparedStatement requete = connection.prepareStatement(sql);
		requete.setInt(1, idProjet);
		requete.setString(2, titre);
		ResultSet rs = requete.executeQuery();
		if (rs.next()) {
			result = new Projet(idProjet, titre);
		}
		return result;
	}

	public List<Projet> getByIdStagiaire(int idStagiaire) throws SQLException {
		List<Projet> result = new ArrayList<>();
		Connection connection = Database.getConnection();
		String sql = 
				"SELECT * FROM projet WHERE id_session_formation IN"
				+ " (  SELECT id_session_formation FROM candidature"
				+ "   WHERE id_personne=? AND id_etat_candidature=6)";
		PreparedStatement requete = connection.prepareStatement(sql);
		requete.setInt(1, idStagiaire);
		ResultSet rs = requete.executeQuery();
		if (rs.next()) {
			result.add(new Projet(rs.getInt("id_projet"), rs.getString("titre")));
		}
		return result;
	}
}
