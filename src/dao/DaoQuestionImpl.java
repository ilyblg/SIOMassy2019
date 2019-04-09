package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoQuestionImpl implements DaoQuestion {

	@Override
	public boolean cree(String enonce,int idQuestionnaire) throws SQLException {
		Connection connection = Database.getConnection();

        String query = "insert into question (enonce, id_questionnaire, est_correcte)" + " values ("+enonce+", "+idQuestionnaire+")";
        
        PreparedStatement preparedStmt = connection.prepareStatement(query);

        preparedStmt.execute();
        
		return true;
	}

}
