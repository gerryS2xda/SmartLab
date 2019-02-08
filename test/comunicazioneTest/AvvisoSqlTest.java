package comunicazioneTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import businessLogic.comunicazione.AvvisoSql;

public class AvvisoSqlTest {

	@Test
	public void testToSqlQuery() {
		String oracle = "SELECT * FROM avviso WHERE idAvviso = 1";
		AvvisoSql actual = new AvvisoSql(1);
		
		assertEquals(oracle, actual.toSqlQuery());
	}

}
