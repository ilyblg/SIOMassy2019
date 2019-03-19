package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class DatabaseTest {

	@Test
	public void testGetConnection() throws SQLException {
		assertNotNull(Database.getConnection());
	}
	
	@Test
	public void testServerOff() {
		try {
			Database.getConnection();
		}
		catch (SQLException exc) {
			assertEquals(Database.SERVER_OFF, exc.getErrorCode());
		}
	}

}
