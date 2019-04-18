package dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class DaoNoteTest {

	/**
	 * Verifier si nous recuperons bien une liste de note pour l'évaluation indiquée
	 * @throws SQLException
	 */
	@Test
	public void testGetNoteByIdEvaluation() throws SQLException {
		System.out.println("getNoteByIdEvaluation  - TEST");
		DaoNote dao = new DaoNote();
		assertEquals(4, dao.getNoteByIdEvaluation(3).size());
	}

	/**
	 * Tester si l'insertion de la nouvelle note pour le stagaire est valide
	 */
	@Test
	public void test() {
		System.out.println("insertNoteStagiaire - TEST");
		DaoNote dao = new DaoNote();
		try {
			assertTrue("Insertion réussie.", dao.insertNoteStagiaire("4", "1", 0));

		} catch (SQLException exc) {
			fail("Insertion non valide.");
			exc.printStackTrace();
		}
	}

}
