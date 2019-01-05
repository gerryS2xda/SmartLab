package prenotazioneTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businessLogic.prenotazione.PrenotazioneById;

public class PrenotazioneByIdTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToSqlQuery() {
		System.out.println("Testing: query sql");
		
		//query che si vuole ottenere
		String oracle = "SELECT * FROM Prenotazione WHERE IDprenotazione = 1";
		
		//query da testare
		PrenotazioneById query = new PrenotazioneById(1);
		String actualObj = query.toSqlQuery();
		
		assertEquals("Le query sono diverse", oracle, actualObj);
	}

}
