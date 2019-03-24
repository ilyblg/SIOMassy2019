package dao;

import entity.Personne;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.Database;

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
				"\" AND prenom =\""+personne.getPrenom()+"\"";
		ResultSet rs = stmt.executeQuery(sql);
		
		if (rs.next()) {
			ifValid = true;
		}
		rs.close();
		return ifValid;		
	}
	
	/**
	 * Indique la presence ou nom d'un courriel dans la liste en base des personnes enregistrés
	 * @param mail
	 * @return
	 * @throws SQLException
	 */
	public boolean checkEmail(String mail) throws SQLException {
		boolean ifValid = false;
		
		Statement stmt = Database.getConnection().createStatement();
		String sql = "SELECT * FROM personne WHERE mail=\""+mail+"\"";
		ResultSet rs = stmt.executeQuery(sql);
		if (!rs.next()) {
			ifValid = true;
		}
		rs.close();
		return ifValid;
	}
	
	/**
	 * Permet d'insérer en base de données une nouvelles personne
	 * @param personne
	 * @return
	 * @throws SQLException
	 */
	public boolean insertInDB(Personne personne) throws SQLException {
		boolean result = false;
		// ECRYPTER LE PASS
		String cryptedPassword = personne.getPassword();
		
		Statement stmt = Database.getConnection().createStatement();
		String sql = "INSERT INTO personne(prenom, nom, mail, tel, adresse, code_postal,"
				+ "ville, mot_de_passe, est_formateur, est_administration, date_inscription)"
				+"VALUES (\""+personne.getPrenom()+"\", \""+personne.getNom()+"\", \""+personne.getMail()+"\","
				+ " \""+personne.getTelephone()+"\",\""+personne.getAdresse()+"\",\""+personne.getCp()+"\", "
				+ "\""+personne.getVille()+"\",\""+cryptedPassword+"\", "+personne.isEstFormateur()+","
						+ ""+personne.isEstAdmin()+", \""+personne.getDateInscription()+"\")";

		// Renvoie le nombre de ligne affectées, si 1 alors insertion réalisée 
		if(stmt.executeUpdate(sql) == 1) {
			result = true;
		}
		return result;
	}
}
