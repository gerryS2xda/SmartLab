package prenotazionetest;

import businessLogic.prenotazione.PrenotazioneGetSQL;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrenotazioneGetSQLTest1 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToSqlQuery() {
		System.out.println("Testing: query per selezionare le prenotazioni in base all'ora di inizio");
		
		//query che si vuole ottenere
		String oracle = "SELECT * FROM Prenotazione WHERE ora_inizio = '09:00'";

		//query da testare
		PrenotazioneGetSQL query = new PrenotazioneGetSQL("09:00", "", 0, "");
		String actualObj = query.toSqlQuery();
		
		assertEquals("Le query sono diverse", oracle, actualObj);
	}
}
