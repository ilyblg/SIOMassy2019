package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.PassageQcm;
import entity.Qcm;
import entity.Question;
import entity.Reponse;

public class QuestionDao {
	private static final String SELECT = "SELECT * FROM question WHERE id_question=?";
	
	public boolean cree(String enonce,int idQuestionnaire) throws SQLException {
		Connection connection = Database.getConnection();

        String query = "insert into question (enonce, id_questionnaire, est_correcte)" + " values ("+enonce+", "+idQuestionnaire+")";
        
        PreparedStatement preparedStmt = connection.prepareStatement(query);

        preparedStmt.execute();
        
		return true;
	}

	public Question getById(int id) throws SQLException {
		Question result = null;
		
		Connection connection = Database.getConnection();
		PreparedStatement ps = connection.prepareStatement(SELECT);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			result = new Question(id, 
					rs.getString("enonce"), 
					rs.getInt("id_qcm"));
		}
		
		return result;
	}
	
	public List<Question> getByIdQcm(int idQcm) throws SQLException {
		List<Question> result = new ArrayList<>();
		List<Reponse> reponses = null;
		int id;
		String enonce;
		
	    Connection connection = Database.getConnection();
	    Statement requete = connection.createStatement();
	    
        String sql = "SELECT q.*, rp.* FROM reponse_possible rp inner join question q on q.id_question = rp.id_question where q.id_qcm = "+ idQcm;
        
        ResultSet rs = requete.executeQuery(sql);
        boolean condNext = rs.next();
        
        while (condNext) {
        	id = rs.getInt("q.id_question");
        	enonce = rs.getString("q.enonce");
        	
        	reponses = new ArrayList<>();
        	
        	while (rs.getInt("rp.id_question") == id) {
        		reponses.add(new Reponse(rs.getInt("rp.id_reponse_possible"), rs.getString("rp.reponse")));
        		condNext = rs.next();
        		
        		if (!condNext) {
        			break;
        		}
        	}

        	result.add(new Question(id, enonce, reponses));
        }
		
		return result;
	}
	
	public String getTitreQcmByIdQcm(int idQcm) throws SQLException {
	    Connection connection = Database.getConnection();
	    Statement requete = connection.createStatement();
	    
	    ResultSet rs = requete.executeQuery("select titre from qcm WHERE id_qcm = "+ idQcm);
	    rs.next();
	    
		return rs.getString(1);
	}
}
