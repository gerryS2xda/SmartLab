package utenteTest;

import businessLogic.utente.StudentList;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentListTest{
	
	@Before
	public void setUp() throws Exception{
		
	}
	
	@After
	public void tearDown() throws Exception{
		
	}
	
	@Test
	public void testToSqlQuery() {
		System.out.println("Testing: query per tutti gli studenti presenti nel DB");
		
		String oracle = "SELECT U.nome, U.cognome, S.*, U.password \" +\r\n"
				+ "\"FROM utente U JOIN studente S ON U.email = S.email;";
		
		StudentList query = new StudentList();
		String actualObj = query.toSqlQuery();
		
		assertEquals("Le query sono diverse", oracle, actualObj);
	}
}