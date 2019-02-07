package addettoTest;

import businessLogic.addetto.*;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddettoListTest{
	
	@Before
	public void setUp() throws Exception{
		
	}
	
	@After
	public void tearDown() throws Exception{
		
	}
	
	@Test
	public void testToSqlQuery() {
		System.out.println("Testing: query per tutte le sospensioni presenti nel DB");
		
		String oracle = "SELECT * FROM addetto a join utente u on a.email = u.email WHERE tipo = 'false';";
		
		AddettoList query = new AddettoList();
		String actualObj = query.toSqlQuery();
		
		assertEquals("Le query sono diverse", oracle, actualObj);
	}
	
}