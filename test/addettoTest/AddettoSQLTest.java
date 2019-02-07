package addettoTest;

import static org.junit.Assert.assertEquals;

import businessLogic.addetto.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddettoSQLTest {
	
	@Before
	public void setUp() throws Exception{
		
	}
	
	@After
	public void tearDown() throws Exception{
		
	}
	
	@Test
	public void testToSqlQuery() {
		System.out.println("Testing: query per tutte le sospensioni presenti nel DB");
		
		String oracle = "SELECT U.nome, U.cognome, A.*, U.password FROM utente U JOIN addetto A ON U.email = A.email WHERE A.email='esempio1@unisa.it';";
		
		AddettoSQL query = new AddettoSQL("esempio1@unisa.it");
		String actualObj = query.toSqlQuery();
		
		assertEquals("Le query sono diverse", oracle, actualObj);
	}
	
}
