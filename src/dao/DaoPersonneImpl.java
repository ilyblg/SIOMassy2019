package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Personne;

public class DaoPersonneImpl{

	public Personne seConnecter(String prenom, String mdp)  throws SQLException {
		// TODO Auto-generated method stub
		Personne result = null;
		Connection connection = Database.getConnection();
		Statement requete = connection.createStatement();
		String sql = " SELECT prenom, mot_de_passe FROM personne WHERE prenom = " + prenom + " AND mot_de_passe = " + mdp;
		ResultSet rs = requete.executeQuery(sql);
		if (rs.next()) {
			result = new Personne(prenom, mdp);
		}
		return result;
	}

}
