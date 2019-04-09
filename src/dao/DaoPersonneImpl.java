package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DaoPersonneImpl implements DaoPersonne {

	@Override
	public void getByNom(String nom) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getByPrenom(String prenom) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean estFormateur(int idPersonne) throws SQLException {
		boolean result = false;
		
	    Connection connection = Database.getConnection();
	    Statement requete = connection.createStatement();
	    
        String sql = "select * from personne WHERE id = "+ idPersonne;
        
        ResultSet rs = requete.executeQuery(sql);
        
        if (rs.next()) {
        	result = rs.getInt("estFormateur") == 0 ? false : true;
        }
		
		return result;
	}
}
