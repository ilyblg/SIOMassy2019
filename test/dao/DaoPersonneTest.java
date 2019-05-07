package dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Test;

import entity.Personne;

public class DaoPersonneTest {

	/**
	 * Fournit la bonne implémentation de l'inscription :
	 * Vérifie si l'insertion est correcte, évite les doublons
	 * Objet Personne est la première occurence en BDD
	 */
	@Test
	public void testInserer() {
		DaoPersonne dao = new DaoPersonne();
		// 1ere personne de la base
		Personne personne = new Personne(-1, "Moreau", "Théo", "t.moreau@proboite.net", 
				"0691564808", "82, Cours Marechal-Joffre", "78180", "MONTIGNY-LE-BRETONNEUX", "condimentum", false, true,
				Timestamp.valueOf(LocalDateTime.now()));
		try {
			dao.inserer(personne);
			fail("l'exception n'est pas levée"); // Si le test est raté : il insère malgré tout le doublon
		} catch (SQLException exc) {
			exc.printStackTrace();
			assertEquals(Database.DOUBLON, exc.getErrorCode()); // Si doublon test Okay
		}
	}

}
