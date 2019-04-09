package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Question;

public class DaoQauestionImpl implements DaoQuestion {

	@Override
	public boolean cree(String enonce, int idQuestionnaire) throws SQLException {
		Connection connection = Database.getConnection();

        String query = "insert into question (enonce, id_questionnaire)" + " values ("+enonce+", "+idQuestionnaire+")";
        
        PreparedStatement preparedStmt = connection.prepareStatement(query);

        preparedStmt.execute();
        
		return true;
	}

	@Override
	public List<Question> getByIdQuestionnaire(int idQuestionnaire) throws SQLException {
		List<Question> result = new ArrayList<>();
		int id;
		String enonce;
		
	    Connection connection = Database.getConnection();
	    Statement requete = connection.createStatement();
	    
        String sql = "select * from question WHERE id_questionnaire = "+ idQuestionnaire;
        
        ResultSet rs = requete.executeQuery(sql);
        
        while (rs.next()) {
        	id = rs.getInt("id");
        	enonce = rs.getString("enonce");
        	result.add(new Question(id, enonce));
        }
		
		return result;
	}

	@Override
	public List<Question> getAll() throws SQLException {
		List<Question> result = new ArrayList<>();
		int id;
		String enonce;
		
	    Connection connection = Database.getConnection();
	    Statement requete = connection.createStatement();
	    
        String sql = "select * from question";
        
        ResultSet rs = requete.executeQuery(sql);
        
        while (rs.next()) {
        	id = rs.getInt("id");
        	enonce = rs.getString("enonce");
        	result.add(new Question(id, enonce));
        }
		
		return result;
	}

}
