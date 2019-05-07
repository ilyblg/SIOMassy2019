package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Equipe;

public class EquipeDao {
	
	private static final String INSERT = "INSERT INTO equipe (id_projet,id_createur) VALUES (?,?)";
	
	public Equipe getbyIdEquipe(int idEquipe) throws SQLException{
		Equipe result = null;
		Connection connection = Database.getConnection();
		Statement requete = connection.createStatement();
		String sql ="SELECT id_equipe FROM equipe WHERE id_equipe = ";
		ResultSet rs = requete.executeQuery(sql);
		if (rs.next()) {
			result = new Equipe (idEquipe,
					rs.getInt("id_Projet"),
					rs.getInt("id_createur"));
		}
	
		return result;
	

	}
	public Equipe getByIdCreateur(int idCreateur) throws SQLException {
		Equipe result = null;
		Connection connection = Database.getConnection();
		Statement requete = connection.createStatement();
		String sql = "Select id_createur FROM equipe WHERE id_createur = ";
		ResultSet rs = requete.executeQuery(sql);
		if(rs.next()) {
			result = new Equipe(rs.getInt("id_equipe"),
					rs.getInt("id_projet"),
					idCreateur);
		}
		return result;
	}
	public Equipe getByIdEquipeIdCreateur(int idEquipe,int idCreateur) throws SQLException {
		Equipe result = null;
		Connection connection = Database.getConnection();
		Statement requete = connection.createStatement();
		String sql = "SELECT * FROM equipe WHERE id_equipe ='" + idEquipe + "' AND id_createur'";
		ResultSet rs = requete.executeQuery(sql);
		if (rs.next()) {
			result = new Equipe(idEquipe,idCreateur,
					rs.getInt("id_projet"));
		}
		
	return result;
	}

	public void insert(Equipe equipe) throws SQLException {
		Connection connection = Database.getConnection();
		PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1,equipe.getIdProjet());
		ps.setInt(-1, equipe.getIdEquipe());
		ps.setInt(2,equipe.getIdCreateur());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		equipe.setId(rs.getInt(1),
				rs.getInt(-1),
				rs.getInt(2));
		System.out.println("Equipe bien ajouté");
	}
}	

