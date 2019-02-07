package addettoTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataAccess.storage.SqlSpecification;
import businessLogic.addetto.*;

public class AddettoSQLTest implements SqlSpecification {
	
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
		
		StudentList query = new AddettoSQL();
		String actualObj = query.toSqlQuery();
		
		assertEquals("Le query sono diverse", oracle, actualObj);
	}
	
}
