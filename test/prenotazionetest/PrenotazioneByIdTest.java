package prenotazionetest;

import businessLogic.prenotazione.PrenotazioneById;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrenotazioneByIdTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToSqlQuery() {
		System.out.println("Testing: query per selezionare una prenotazione in base all'ID");
		
		//query che si vuole ottenere
		String oracle = "SELECT * FROM Prenotazione WHERE IDprenotazione = 1";
		
		//query da testare
		PrenotazioneById query = new PrenotazioneById(1);
		String actualObj = query.toSqlQuery();
		
		assertEquals("Le query sono diverse", oracle, actualObj);
	}

}
