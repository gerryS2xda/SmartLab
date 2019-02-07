package utenteTest;

import businessLogic.utente.StudenteLoginSQL;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentLoginSQLTest{
	
	@Before
	public void setUp() throws Exception{
		
	}
	
	@After
	public void tearDown() throws Exception{
		
	}
	
	@Test
	public void testToSqlQuery() {
		System.out.println("Testing: query per selezionare gli studenti in base ad email e password");
		
		String oracle = "SELECT U.nome, U.cognome, S.*, U.password "
				+ "FROM utente U JOIN studente S ON U.email = S.email WHERE U.email='teststud@studenti.unisa.it' AND U.password='12345678'";
		
		StudenteLoginSQL query = new StudenteLoginSQL("teststud@studenti.unisa.it", "12345678");
		String actualObj = query.toSqlQuery();
		
		assertEquals("Le query sono diverse", oracle, actualObj);
	}
}