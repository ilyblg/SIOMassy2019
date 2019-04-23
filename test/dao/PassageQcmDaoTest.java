package dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import entity.PassageQcm;

public class PassageQcmDaoTest {

	@Before
	public void resetBd() throws SQLException {
		Database.reset(LocalDateTime.of(2018, 1, 16, 11, 55, 42));
	}
	
	@Test
	public void testGetById() throws SQLException {
		System.out.println("getById");
		PassageQcmDao instance = new PassageQcmDao();
		PassageQcm expected = new PassageQcm(1, LocalDateTime.of(2016, 10, 16, 11, 55, 42), 1, 4);
		assertEquals(expected, instance.getById(1));
	}

	@Test
	public void testInsert() throws SQLException {
		PassageQcmDao dao = new PassageQcmDao();
		LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
		PassageQcm passage = new PassageQcm(-1, now, 1, 3);
		dao.insert(passage);
		// Verifions
		assertEquals(2, passage.getId());
		PassageQcm expected = new PassageQcm(2, now, 1, 3);
		assertEquals(expected, dao.getById(2));
	}
}
