ackage prenotazionetest;

import businessLogic.prenotazione.PrenByStudPost;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrenByStudPostTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToSqlQuery() {
		System.out.println("Testing: query per selezionare le prenotazioni in base all'email dello studente, numero postazione e idLaboratorio");
		
		//query che si vuole ottenere
		String oracle = "SELECT * FROM Prenotazione WHERE Studente = 'teststud@studenti.unisa.it' "
				+ "AND Postazione = 100 AND Laboratorio = 0";

		//query da testare
		PrenByStudPost query = new PrenByStudPost("teststud@studenti.unisa.it", "100", "0");
		String actualObj = query.toSqlQuery();
		
		assertEquals("Le query sono diverse", oracle, actualObj);
	}

}
