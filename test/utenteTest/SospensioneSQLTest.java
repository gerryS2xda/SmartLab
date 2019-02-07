package utenteTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataAccess.storage.SqlSpecification;
import businessLogic.utente.*;

public class SospensioneSQLTest implements SqlSpecification {
	
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
		
		StudentList query = new SospensioneSQL();
		String actualObj = query.toSqlQuery();
		
		assertEquals("Le query sono diverse", oracle, actualObj);
	}
	
}