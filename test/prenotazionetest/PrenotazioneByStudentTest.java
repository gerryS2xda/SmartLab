package prenotazionetest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businessLogic.prenotazione.PrenotazioneByStudent;

public class PrenotazioneByStudentTest {

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
		String oracle = "SELECT * FROM Prenotazione WHERE Studente = 'teststud@studenti.unisa.it'";
		
		//query da testare
		PrenotazioneByStudent query = new PrenotazioneByStudent("teststud@studenti.unisa.it");
		String actualObj = query.toSqlQuery();
		
		assertEquals("Le query sono diverse", oracle, actualObj);

	}

}
