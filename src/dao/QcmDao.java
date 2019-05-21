package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.Qcm;

public class QcmDao {
	private static final String INSERT = "INSERT INTO qcm(date_creation, titre, id_formateur)"
			+ " VALUES(?, ?, ?)";

	public void insert(Qcm qcm) throws SQLException {
		Connection connection = Database.getConnection();
		PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
		ps.setTimestamp(1, Timestamp.valueOf(qcm.getDateQcm()));
		ps.setString(2, qcm.getTitre());
		ps.setInt(3, qcm.getIdFormateur());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		qcm.setId(rs.getInt(1));
		System.out.println("Qcm bien ajouté");
	}

	public boolean cree(String titre, int idFormateur) throws SQLException {
		Connection connection = Database.getConnection();

        String query = "insert into qcm (titre, id_formateur)" + " values ("+titre+", "+idFormateur+")";
        
        PreparedStatement preparedStmt = connection.prepareStatement(query);

        preparedStmt.execute();
        
		return true;
	}

	public List<Qcm> getByIdFormateur(int idFormateur) throws SQLException {
		List<Qcm> result = new ArrayList<>();
		int id;
		String titre;
		
	    Connection connection = Database.getConnection();
	    Statement requete = connection.createStatement();
	    
        String sql = "select * from qcm WHERE id_formateur = "+ idFormateur;
        
        ResultSet rs = requete.executeQuery(sql);
        
        while (rs.next()) {
        	id = rs.getInt("id");
        	titre = rs.getString("titre");
        	result.add(new Qcm(id, titre));
        }
		
		return result;
	}

	public List<Qcm> getAll() throws SQLException {
		List<Qcm> result = new ArrayList<>();
		int id;
		String titre;
		
	    Connection connection = Database.getConnection();
	    Statement requete = connection.createStatement();
	    
        String sql = "select * from qcm";
        
        ResultSet rs = requete.executeQuery(sql);
        
        while (rs.next()) {
        	id = rs.getInt("id_qcm");
        	titre = rs.getString("titre");
        	result.add(new Qcm(id, titre));
        }
		
		return result;
	}

}
