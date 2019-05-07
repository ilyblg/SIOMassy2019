package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Projet;

public class ProjetDao {
	
	public Projet getByIdProjet(int idProjet) throws SQLException {
		Projet result = null;
		Connection connection = Database.getConnection();
		Statement requete = connection.createStatement();
		String sql = "SELECT id_projet From projet WHERE id_projet = " + idProjet;
		ResultSet rs = requete.executeQuery(sql);
		if(rs.next()){
			result = new Projet (idProjet,
					rs.getString("titre"));
		}
		return result;
		
	}
	
	public Projet getByTitre(String titre) throws SQLException {
		Projet result = null;
		Connection connection = Database.getConnection();
		Statement requete = connection.createStatement();
		String sql = "SELECT titre FROM projet WHERE titre = " + titre;
		ResultSet rs = requete.executeQuery(sql);
		if (rs.next()) {
			result = new Projet (rs.getInt("id_projet"),
					titre);
		}
		return result;
		
	}
	public Projet getByIdProjetTitre(int idProjet,String titre) throws SQLException{
		Projet result = null;
		Connection connection = Database.getConnection();
		Statement requete = connection.createStatement();
		String sql = "SELECT * FROM projet WHERE id_projet = '" + idProjet + "'AND titre ='" + titre+ "'";
		ResultSet rs = requete.executeQuery(sql);
		if(rs.next()) {
			result =  new Projet(idProjet,titre);
			
		}
		return result;
		
	}
}
