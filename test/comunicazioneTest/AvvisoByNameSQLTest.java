package comunicazioneTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import businessLogic.comunicazione.AvvisoByNameSQL;


public class AvvisoByNameSQLTest {

	@Test
	public void testToSqlQuery() {
		String oracle = "SELECT * FROM avviso WHERE titolo = 'titoloTest'";
		AvvisoByNameSQL actual = new AvvisoByNameSQL("titoloTest");
		
		assertEquals(oracle, actual.toSqlQuery());
	}

}
