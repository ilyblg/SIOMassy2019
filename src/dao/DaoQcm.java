package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Qcm;

public interface DaoQcm {
	public boolean cree(String titre, int idFormateur) throws SQLException;
	public List<Qcm> getByIdFormateur(int idFormateur) throws SQLException;
	public List<Qcm> getAll() throws SQLException;
}
