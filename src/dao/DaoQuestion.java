package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Question;

public interface DaoQuestion {
	public boolean cree(String enonce,int idQuestionnaire) throws SQLException;
	public List<Question> getByIdQuestionnaire(int idQuestionnaire) throws SQLException;
	public List<Question> getAll() throws SQLException;
}
