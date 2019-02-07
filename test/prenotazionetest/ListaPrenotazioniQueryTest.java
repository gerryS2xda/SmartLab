package prenotazionetest;

import businessLogic.prenotazione.ListaPrenotazioniQuery;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListaPrenotazioniQueryTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToSqlQuery() {
		System.out.println("Testing: query per tutte le prenotazioni presenti nel DB");
		
		//query che si vuole ottenere
		String oracle = "SELECT * FROM Prenotazione";

		//query da testare
		ListaPrenotazioniQuery query = new ListaPrenotazioniQuery();
		String actualObj = query.toSqlQuery();
		
		assertEquals("Le query sono diverse", oracle, actualObj);
	}

}
