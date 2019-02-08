package comunicazioneTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import businessLogic.comunicazione.SegnalazioneByOggetto;

public class SegnalazioneByOggettoTest {

	@Test
	public void testToSqlQuery() {
		String oracle = "SELECT * FROM segnalazione WHERE oggetto = 'oggettoTest'";
		SegnalazioneByOggetto actual = new SegnalazioneByOggetto("oggettoTest");
		
		assertEquals(oracle, actual.toSqlQuery());
	}

}
