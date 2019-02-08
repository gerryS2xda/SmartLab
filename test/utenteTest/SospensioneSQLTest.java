package utenteTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import businessLogic.utente.SospensioneSQL;

public class SospensioneSQLTest {
	
	@Test
	public void testToSqlQuery() {
		System.out.println("Testing: query per una specifica sospensione presente nel DB");
		
		String oracle = "SELECT * FROM sospensione WHERE IDsospensione=1";
		
		SospensioneSQL query = new SospensioneSQL(1);
		String actualObj = query.toSqlQuery();
		
		assertEquals("Le query sono diverse", oracle, actualObj);
	}
	
}