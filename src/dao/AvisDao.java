package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import entity.Avis;

public class AvisDao {

	private final String INSERT = "INSERT INTO avis(id_pesonne, id_qcm, commentaire, note, date_avis) VALUES(?, ?, ?, ?, ?)";

	public <resultSet> void insert(Avis avis) throws SQLException {
		System.out.println("on essaye d'insérer dans la bd");
		Connection connection = Database.getConnection();
		// Inserer la ligne dans la base
		// les numéros représentent les valeurs des ? dans l'ordre
		PreparedStatement stmt = connection.prepareStatement(INSERT);
		stmt.setInt(1, avis.getIdPersonne());
		stmt.setInt(2, avis.getIdQcm());
		stmt.setString(3, avis.getCommentaire());
		stmt.setInt(4, avis.getNote());
		stmt.setTimestamp(5, Timestamp.valueOf(avis.getDateAvis()));
		stmt.execute();
	}

}
