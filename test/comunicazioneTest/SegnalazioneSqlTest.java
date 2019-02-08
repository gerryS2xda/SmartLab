package comunicazioneTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import businessLogic.comunicazione.SegnalazioneSql;

public class SegnalazioneSqlTest {

	@Test
	public void testToSqlQuery() {
		String oracle = "SELECT * FROM segnalazione WHERE idSegnalazione = 1";
		SegnalazioneSql actual = new SegnalazioneSql(1);
		
		assertEquals(oracle, actual.toSqlQuery());
	}

}
