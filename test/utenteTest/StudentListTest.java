package utenteTest;

import businessLogic.utente.StudentList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class StudentListTest{
	
	
	@Test
	public void testToSqlQuery() {
		System.out.println("Testing: query per tutti gli studenti presenti nel DB");
		
		String oracle = "SELECT U.nome, U.cognome, S.*, U.password FROM utente U JOIN studente S ON U.email = S.email";
		
		StudentList query = new StudentList();
		String actualObj = query.toSqlQuery();
		
		assertEquals("Le query sono diverse", oracle, actualObj);
	}
}