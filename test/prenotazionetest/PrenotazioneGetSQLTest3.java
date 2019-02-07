package prenotazionetest;

import businessLogic.prenotazione.PrenotazioneGetSQL;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrenotazioneGetSQLTest3 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToSqlQuery() {
		System.out.println("Testing: query per selezionare le prenotazioni in base all'ora di inizio, ora di fine, numero postazione e idLaboratorio");
		
		//query che si vuole ottenere
		String oracle = "SELECT * FROM Prenotazione WHERE ora_inizio = '09:00' AND ora_fine = '11:00' AND postazione = 100 AND Laboratorio = 0";

		//query da testare
		PrenotazioneGetSQL query = new PrenotazioneGetSQL("09:00", "11:00", 100, "0");
		String actualObj = query.toSqlQuery();
		
		assertEquals("Le query sono diverse", oracle, actualObj);
	}
}
