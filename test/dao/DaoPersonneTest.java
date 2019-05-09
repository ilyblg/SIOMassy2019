package dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import entity.Personne;

public class DaoPersonneTest {
	private LocalDateTime dateEffet = LocalDateTime.of(2019, 4, 29, 15, 10, 0);

	@Before
	public void reset() throws SQLException {
		Database.reset(dateEffet);
	}

	/**
	 * Fournit la bonne implémentation de l'inscription : Vérifie si l'insertion est
	 * correcte, évite les doublons Objet Personne est la première occurence en BDD
	 */
	@Test
	public void testInsererDoublon() {
		PersonneDao dao = new PersonneDao();
		// 1ere personne de la base
		Personne personne = new Personne(-1, "Moreau", "Théo", "t.moreau@proboite.net", "0691564808",
				"82, Cours Marechal-Joffre", "78180", "MONTIGNY-LE-BRETONNEUX", "admin", false, true,
				Timestamp.valueOf(LocalDateTime.now()));
		try {
			dao.inserer(personne);
			fail("l'exception n'est pas levée"); // Si le test est raté : il insère malgré tout le doublon
		} catch (SQLException exc) {
			exc.printStackTrace();
			assertEquals(Database.DOUBLON, exc.getErrorCode()); // Si doublon test Okay
		}
	}

	@Test
	public void testGetByLoginMdp() throws SQLException {
		PersonneDao dao = new PersonneDao();
		LocalDateTime date = dateEffet.minusYears(1).plusDays(2);
		// 1ere personne de la base
		Personne expected = new Personne(1, "Moreau", "Théo", "t.moreau@proboite.net", "0691564808",
				"82, Cours Marechal-Joffre", "78180", "MONTIGNY-LE-BRETONNEUX", "*4ACFE3202A5FF5CF467898FC58AAB1D615029441", false, true, Timestamp.valueOf(date));
		Personne result = dao.getByLoginMdp("t.moreau@proboite.net", "admin");
		System.out.println("expected : " + expected);
		System.out.println("got : " + result);
		assertEquals(expected, result);
	}
}
