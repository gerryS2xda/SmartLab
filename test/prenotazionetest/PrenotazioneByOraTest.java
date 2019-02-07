package prenotazionetest;

import businessLogic.prenotazione.PrenotazioneByOra;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrenotazioneByOraTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToSqlQuery() {
		System.out.println("Testing: query per selezionare le prenotazioni in base all'oraInizio, oraFine e idLaboratorio");
		
		//query che si vuole ottenere
		String oracle = "SELECT * FROM Prenotazione WHERE ora_inizio = '09:00' "
				+ "AND ora_fine = '11:00' AND Laboratorio = 0";

		//query da testare
		PrenotazioneByOra query = new PrenotazioneByOra("09:00", "11:00", "0");
		String actualObj = query.toSqlQuery();
		
		assertEquals("Le query sono diverse", oracle, actualObj);
	}

}
