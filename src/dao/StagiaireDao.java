package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import entity.Stagiaire;

public class StagiaireDao  {
	
	
	public Stagiaire getByIdPersonne(int idPersonne) throws SQLException {
		Stagiaire result = null;
		Connection connection = Database.getConnection();
		Statement requete = connection.createStatement();
		String sql = "SELECT id_personne FROM stagiaire WHERE id_personne = " + idPersonne;
		ResultSet rs = requete.executeQuery(sql);
		if (rs.next()) {// Une ligne trouve
			result = new Stagiaire(idPersonne,
					rs.getString("nom"),
					rs.getString("prenom"));

		}
		return result;
	}
	
	public Stagiaire getByNom(String nom) throws SQLException {
		Stagiaire result = null;
		Connection connection = Database.getConnection();
		Statement requete = connection.createStatement();
		String sql = "SELECT nom FROM stagiaire WHERE nom = " + nom;
		ResultSet rs = requete.executeQuery(sql);
		if(rs.next()) {
			result = new Stagiaire(rs.getInt("id_personne"),
					nom,
					rs.getString("prenom"));
		}
		return result;
	}
	
	public Stagiaire getByPrenom(String prenom) throws SQLException {
		Stagiaire result = null;
		Connection connection = Database.getConnection();
		Statement requete = connection.createStatement();
		String sql = "SELECT prenom FROM stagiaire WHERE prenom= " + prenom;
		ResultSet rs = requete.executeQuery(sql);
		if (rs.next()) {
			result = new Stagiaire (rs.getInt("idPersonne"),
					rs.getString("nom"),
					prenom);
		}
		return result;
	}
	
	public Stagiaire getByNomPrenom (String nom , String prenom) throws SQLException{
		Stagiaire result = null;
		Connection connection = Database.getConnection();
		Statement requete = connection.createStatement();
		String sql = "SELECT * FROM stagiaire WHERE Nom='" + nom + "' AND prenom='" + prenom + "'";
		ResultSet rs = requete.executeQuery(sql);
		if (rs.next()) {// Une ligne trouve
			result = new Stagiaire(rs.getInt("Id"),
					nom,
					prenom);
		}
		return result;
	}

		
		
	}

