package comunicazioneTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import businessLogic.comunicazione.ListaSegnalazioni;

public class ListaSegnalazioniTest {

	@Test
	public void testToSqlQuery() {

		String oracle = "SELECT * FROM segnalazione";
		ListaSegnalazioni actual = new ListaSegnalazioni();
		
		assertEquals(oracle, actual.toSqlQuery());
	}

}
