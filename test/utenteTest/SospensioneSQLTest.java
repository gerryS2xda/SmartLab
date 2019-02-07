package utenteTest;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businessLogic.utente.SospensioneSQL;

public class SospensioneSQLTest {
	
	@Before
	public void setUp() throws Exception{
		
	}
	
	@After
	public void tearDown() throws Exception{
		
	}
	
	@Test
	public void testToSqlQuery() {
		System.out.println("Testing: query per una specifica sospensione presente nel DB");
		
		String oracle = "SELECT * FROM sospensione WHERE id=1;\"";
		
		SospensioneSQL query = new SospensioneSQL(1);
		String actualObj = query.toSqlQuery();
		
		assertEquals("Le query sono diverse", oracle, actualObj);
	}
	
}