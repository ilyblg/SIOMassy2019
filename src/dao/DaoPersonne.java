package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import entite.Personne;

public class DaoPersonne {

	private final String INSERT = "INSERT INTO personne(nom, prenom, mail, tel, adresse, code_postal, ville, mot_de_passe, est_formateur, est_administration, date_inscription)"
			+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public void insert(Personne personne) throws SQLException {
		System.out.println("on essaie d'inserer dans la bd");
		Connection connection = Database.getConnection();
		// Inserer la ligne dans la base
		// les numéros représentent les valeurs des ? dans l'ordre
		PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, personne.getNom());
		stmt.setString(2, personne.getPrenom());
		stmt.setString(3, personne.getMail());
		stmt.setString(4, personne.getTel());
		stmt.setString(5, personne.getAdresse());
		stmt.setString(6, personne.getCodePostal());
		stmt.setString(7, personne.getVille());
		stmt.setString(8, personne.getMotDePasse());
		stmt.setBoolean(9, personne.isEstFormateur());
		stmt.setBoolean(10, personne.isEstAdministration());		
		stmt.setTimestamp(11, Timestamp.valueOf(personne.getDateInscription()));
		stmt.execute();
		// Recuperer le id qui a ete auto-incremente
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			personne.setId(rs.getInt(1));
		}
	}

}
