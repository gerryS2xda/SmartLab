package utenteTest;

import businessLogic.utente.StudenteSQL;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentSQLTest{
	
	@Before
	public void setUp() throws Exception{
		
	}
	
	@After
	public void tearDown() throws Exception{
		
	}
	
	@Test
	public void testToSqlQuery() {
		System.out.println("Testing: query per tutti gli studenti registrati");
		
		String oracle = "SELECT U.nome, U.cognome, S.*, U.password FROM utente U JOIN studente S ON U.email = S.email WHERE U.email='teststud@studenti.unisa.it'";
		
		StudenteSQL query = new StudenteSQL("teststud@studenti.unisa.it");
		String actualObj = query.toSqlQuery();
		
		assertEquals("Le query sono diverse", oracle, actualObj);
	}
}