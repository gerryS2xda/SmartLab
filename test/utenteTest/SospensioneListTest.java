package utenteTest;

import businessLogic.utente.*;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SospensioneListTest {
	
	@Before
	public void setUp() throws Exception{
		
	}
	
	@After
	public void tearDown() throws Exception{
	}
	
	@Test
	public void testToSqlQuery() {
		System.out.println("Testing: query per tutte le sospensioni presenti nel DB");
		
		String oracle = "SELECT * FROM sospensione";
		
		SospensioneList query = new SospensioneList();
		String actualObj = query.toSqlQuery();
		
		assertEquals("Le query sono diverse", oracle, actualObj);
	}
	
}