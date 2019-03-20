package dao;

import entity.Personne;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

import dao.Database;
import entity.Personne;

public class DaoPersonne{

	public DaoPersonne() throws SQLException {
	}

	public void getByNom(String nom) {
		// TODO Auto-generated method stub
		
	}

	public void getByPrenom(String prenom) {
		// TODO Auto-generated method stub
		
	}
	
	/** Fournit la Personne par son ID
	 * @param id
	 * @return Object Personne
	 * @throws SQLException 
	 */
	public Personne GetById(int id) throws SQLException{
		Personne result = null;
		Statement stmt = Database.getConnection().createStatement();
		String sql = "SELECT * FROM personne WHERE id_personne=" + id;
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			result = new Personne(
					rs.getString("nom"),
					rs.getString("prenom"),
					rs.getString("adresse"),
					rs.getInt("code_postal"),
					rs.getString("ville"),
					rs.getString("telephone"),					
					rs.getString("mail"),
					rs.getString("mot_de_passe"),
					rs.getBoolean("est_formateur"),
					rs.getBoolean("est_administration"),
					rs.getTimestamp("date_inscription")
					);
		}
		return result;
	}
	
	/** Indique si une Personne est présente en BDD
	 *  Par son "nom", "prénom" et "mail"
	 * @param Object Personne
	 * @return booleqn
	 * @throws SQLException 
	 */
	public boolean ifExist(Personne personne) throws SQLException {
		boolean ifValid = false;
		
		Statement stmt = Database.getConnection().createStatement();
		String sql = "SELECT nom, prenom, mail FROM personne WHERE nom=\""+personne.getNom()+
				"\" AND prenom =\""+personne.getPrenom()+"\" AND mail=\""+personne.getMail()+"\"";
		ResultSet rs = stmt.executeQuery(sql);
		
		if (!rs.next()) {
			ifValid = true;			
		}
		return ifValid;		
	}
	
	public boolean checkEmail(String mail) throws SQLException {
		boolean isValid = false;
		
		boolean yesOrNot = false;
		
		Statement stmt = Database.getConnection().createStatement();
		String sql = "SELECT * FROM personne WHERE mail=\""+mail+"\"";
		ResultSet rs = stmt.executeQuery(sql);
		
		if (!rs.next()) {
			yesOrNot = true;			
		}
		return isValid;
		
	}
}
